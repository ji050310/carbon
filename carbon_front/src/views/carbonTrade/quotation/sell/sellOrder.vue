<template>
  <el-dialog
    top="15px"
    :title="title"
    :visible.sync="show"
    width="720px"
    :before-close="clickClose"
  >
    <el-form
      label-position="left"
      label-width="130px"
      :model="subForm"
      :rules="formRules"
    >
      <el-form-item label="供应方" prop="institutionName">
        <el-input
          disabled
          v-model="subForm.institutionName"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="assetTypeName">
        <label slot="label">资产类型<span style="color: red">*</span></label>
        <el-cascader
          class="selectbox-root"
          style="width: 100%; top: -5px; background-color: #f5f7fa"
          v-model="subForm.assetType"
          :options="assetTypeList"
          @change="selectAssetType"
          disabled
        >
        </el-cascader>
      </el-form-item>
      <el-form-item prop="assetTypeName">
        <label slot="label">项目领域<span style="color: red">*</span></label>
        <el-cascader
          class="selectbox-root"
          style="width: 100%; top: -5px; background-color: #f5f7fa"
          v-model="subForm.projectScopeCode"
          :options="projectAreaList"
          disabled
        >
        </el-cascader>
      </el-form-item>
      <el-form-item prop="projectTypeName">
        <label slot="label">项目类型<span style="color: red">*</span></label>
        <el-cascader
          class="selectbox-root"
          style="width: 100%; top: -5px; background-color: #f5f7fa"
          v-model="subForm.projectType"
          :options="projectTypeList"
          disabled
        >
        </el-cascader>
      </el-form-item>
      <el-form-item prop="tradeQuantity">
        <label slot="label"
        >采购数量(tCO2e)<span style="color: red">*</span></label
        >
        <el-input
          v-model="subForm.tradeQuantity"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="assetUnitPrice">
        <label slot="label">询价(¥)<span style="color: red">*</span></label>
        <el-input
          v-model="subForm.assetUnitPrice"
          size="medium"
          class="contentItem"
          type="number"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="期望交割日期" prop="deliveryTime">
        <el-date-picker
          class="contentItem"
          v-model="selectDate"
          type="datetime"
          placeholder="选择日期时间"
          align="right"
          :picker-options="pickerOptions"
          size="medium"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="期望交割方式" prop="deliveryMethodName">
        <el-cascader
          class="selectbox-root"
          style="width: 100%; top: -5px"
          placeholder="全部"
          v-model="subForm.deliveryMethod"
          :options="deliverMethodList"
          clearable
        >
        </el-cascader>
      </el-form-item>
      <el-form-item label="期望交割场所" prop="deliveryExchangeName">
        <el-cascader
          class="selectbox-root"
          style="width: 100%; top: -5px"
          placeholder="全部"
          v-model="subForm.deliveryExchange"
          :options="exchangeList"
          clearable
        >
        </el-cascader>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="clickSubmit" class="light-green-btn">{{
          btnText
        }}</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { startTrading } from "@/api/carbonAssetApi";
import {
  getDiliveryMethodeDict,
  getExchangeDict,
  getAssetTypeDict,
  getProjectTypeDict,
  getProjectAreaDict,
} from "@/config/dictHelper";
import { isProjectTypeDisable } from "@/libs/public";
import { getAccoutEnterPriseInfo } from "@/api/tenant";
export default {
  name: "companyPackage",
  props: {
    form: {
      id: "",
      institutionName: "",
      assetType: "",
      projectType: "",
      tradeQuantity: "",
      assetUnitPrice: "",
      deliveryTime: "",
      deliveryMethod: "",
      deliveryExchange: "",
    },
    title: "",
    dialogFormVisible: false,
  },
  data() {
    return {
      seletedDeliverMethod: "",
      deliverMethodList: [],
      seletedExchange: "",
      exchangeList: [],
      assetTypeList: [],
      projectTypeList: [],
      projectAreaList: [],
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
      selectDate: "",
      dialogFormVisible: false, // 询价dialog,
      btnText: "询价",
      subForm: {
        institutionName: "",
        deliveryExchange: "",
        deliveryMethod: "",
        assetUnitPrice: "",
        assetType: "",
        projectType: "",
        projectScopeCode: "",
        tradeQuantity: "",
        // assetUnitPrice: "",
      },
      subTitle: "",
      show: false,
      formRules: {
        assetUnitPrice: [
          { pattern: /^[0-9]+(.[0-9]{1,4})?$/, message: "只能输入数字" },
          { required: true, message: "请输入询价价格", trigger: "blur" },
        ],
        assetType: [
          { required: true, message: "请选择资产类型", trigger: "change" },
        ],
        projectTypeName: [
          { required: true, message: "请选择项目类型", trigger: "change" },
        ],
        tradeQuantity: [
          { pattern: /^[0-9]*$/, message: "只能输入数字" },
          { required: true, message: "请输入采购数量", trigger: "blur" },
        ],
      },
      isProjectTypeDisable: false,
    };
  },
  watch: {
    title() {
      this.subTitle = this.title;
    },
    form() {
      this.subForm = this.form;
      this.initParams();
    },
    dialogFormVisible() {
      this.show = this.dialogFormVisible;
    },
  },
  methods: {
    checkParam(value) {
      return !value || value === "" || value === "--" || value === "/";
    },
    initParams() {
      debugger;
      this.subForm.assetType = this.checkParam(this.subForm.assetType)
        ? "0140000000"
        : this.subForm.assetType;
      this.subForm.projectType = this.checkParam(this.subForm.projectType)
        ? "0040000000"
        : this.subForm.projectType;
      debugger;
      this.subForm.tradeQuantity = this.checkParam(this.subForm.tradeQuantity)
        ? "--"
        : this.subForm.tradeQuantity;
      this.subForm.assetUnitPrice = this.checkParam(this.subForm.assetUnitPrice)
        ? 0
        : this.subForm.assetUnitPrice;
      if (this.subForm.deliveryTime) {
        this.subForm.deliveryTime =
          this.subForm.deliveryTime !== null && this.subForm.deliveryTime !== ""
            ? this.subForm.deliveryTime.split(" ")[0]
            : "";
      }
      if (this.subForm.assetUnitPrice == "--") {
        this.subForm.assetUnitPrice = 0;
      }
      this.selectDate = this.checkParam(this.subForm.deliveryTime)
        ? ""
        : this.subForm.deliveryTime;
      this.subForm.deliveryMethodName = this.checkParam(
        this.subForm.deliveryMethod
      )
        ? "全部"
        : this.subForm.deliveryMethod;
      this.subForm.deliveryExchangeName = this.checkParam(
        this.subForm.deliveryExchange
      )
        ? "全部"
        : this.subForm.deliveryExchange;
      if (this.form && this.form.publisherId) {
        getAccoutEnterPriseInfo(this.form.publisherId).then((res) => {
          this.subForm.institutionName = res.tenantName;
        });
      }

      this.subTitle = this.title;
      this.subForm = this.form;

      this.subForm.assetUnitPrice =
        this.subForm.assetUnitPrice == null ||
        this.subForm.assetUnitPrice === "--"
          ? "0"
          : this.subForm.assetUnitPrice;

      this.show = this.dialogFormVisible;
      let data = getDiliveryMethodeDict(this.$store);
      this.deliverMethodList = [];
      data.map((v) => {
        let item = {
          value: "",
          label: "",
        };
        item.value = v.value;
        item.label = v.name;
        this.deliverMethodList.push(item);
      });
      data = getExchangeDict(this.$store);
      this.exchangeList = [];
      data.map((v) => {
        let item = {
          value: "",
          label: "",
        };
        item.value = v.value;
        item.label = v.name;
        this.exchangeList.push(item);
      });
      this.assetTypeList = [];
      data = getAssetTypeDict(this.$store);
      data.map((v) => {
        let item = {
          value: "",
          label: "",
        };
        item.value = v.value;
        item.label = v.name;
        this.assetTypeList.push(item);
      });

      this.projectTypeList = [];
      data = getProjectTypeDict(this.$store);
      data.map((v) => {
        let item = {
          value: "",
          label: "",
        };
        item.value = v.value;
        item.label = v.name;
        this.projectTypeList.push(item);
      });
      this.projectAreaList = [];
      data = getProjectAreaDict(this.$store);
      data.map((v) => {
        let item = {
          value: "",
          label: "",
        };
        item.value = v.value;
        item.label = v.name;
        this.projectAreaList.push(item);
      });
      debugger;
      // if (this.assetTypeList) {
      //   this.assetTypeList.shift();
      // }
      // if (this.projectTypeList) {
      //   this.projectTypeList.shift();
      // }
    },
    selectAssetType() {
      this.isProjectTypeDisable = isProjectTypeDisable(this.form.assetType[0]);
    },
    clickClose() {
      this.$emit("changeDialogFormVisible", false);
      this.show = false;
    },
    clickSubmit() {
      // this.$message("提交报价中...")
      if (this.subForm.tradeQuantity <= 0) {
        this.$message("请输入正确的询价数量");
        return;
      }
      if (this.subForm.assetUnitPrice <= 0) {
        this.$message("请输入正确的询价价格");
        return;
      }
      this.clickClose();
      let data = {
        assetUnitPrice: this.subForm.assetUnitPrice,
        projectScopeCode: this.subForm.projectScopeCode
          ? this.subForm.projectScopeCode[0]
          : this.subForm.projectScopeCode,
        assetType:
          this.subForm.assetType[0] === "0140000000"
            ? ""
            : this.subForm.assetType[0],
        projectType:
          this.subForm.projectType[0] === "0040000000"
            ? ""
            : this.subForm.projectType[0],
        deliveryExchange:
          this.subForm.deliveryExchange[0] === "全部"
            ? ""
            : this.subForm.deliveryExchange[0],
        deliveryMethod:
          this.subForm.deliveryMethod[0] === "全部"
            ? ""
            : this.subForm.deliveryMethod[0],
        deliveryTime: this.selectDate ? this.selectDate : "",
        tradeQuantity: this.subForm.tradeQuantity,
        tradeQuoteId: this.subForm.id,
      };
      debugger;
      startTrading(data)
        .then((res) => {
          // this.$message(res.msg)
          this.$emit("successSubmit", false);
        })
        .catch((error) => {
          this.$message(error.msg);
        });
    },
  },
  created() {},
  mounted() {},
};
</script>
<style lang="scss" scoped>
.contentItem {
  width: 100%;
  top: -5px;
}

>>> .el-form-item.is-required:not(.is-no-asterisk)
> .el-form-item__label:before,
.el-form-item.is-required:not(.is-no-asterisk)
.el-form-item__label-wrap
> .el-form-item__label:before {
  content: "";
  color: transparent !important;
  margin-right: 0px;
}

>>> .el-form-item__error {
  color: #ff4949;


  padding-top: 4px;
  position: absolute;
  top: 100%;
  left: 0px;
}
</style>
