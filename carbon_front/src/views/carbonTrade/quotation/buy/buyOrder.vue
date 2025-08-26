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
      <el-form-item label="采购方" prop="institutionName">
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
        <!-- <el-input
                v-model="subForm.assetTypeName"
                size="medium"
                class="contentItem">
              </el-input> -->
        <el-cascader
          class="selectbox-root"
          style="width: 100%; top: -5px"
          v-model="subForm.assetType"
          :options="assetTypeList"
          clearable
        >
        </el-cascader>
      </el-form-item>
      <el-form-item prop="assetTypeName">
        <label slot="label">项目领域</label>
        <!-- <el-input
                v-model="subForm.assetTypeName"
                size="medium"
                class="contentItem">
              </el-input> -->
        <el-cascader
          class="selectbox-root"
          style="width: 100%; top: -5px"
          v-model="subForm.projectScopeCode"
          @change="selectAssetType"
          :options="projetcAreaList"
          clearable
        >
        </el-cascader>
      </el-form-item>
      <el-form-item prop="projectTypeName">
        <label slot="label">项目类型<span style="color: red">*</span></label>
        <!-- <el-input
                disabled
                v-model="subForm.projectTypeName"
                size="medium"
                class="contentItem">
              </el-input> -->
        <el-cascader
          class="selectbox-root"
          style="width: 100%; top: -5px"
          v-model="subForm.projectType"
          :options="projectTypeList"
          clearable
          :disabled="isProjectTypeDisable"
        >
        </el-cascader>
      </el-form-item>
      <el-form-item prop="tradeQuantity">
        <label slot="label"
        >供应数量(tCO2e)<span style="color: red">*</span></label
        >
        <el-input
          v-model="subForm.tradeQuantity"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="assetUnitPrice">
        <label slot="label">报价(¥)<span style="color: red">*</span></label>
        <el-input
          v-model="subForm.assetUnitPrice"
          size="medium"
          class="contentItem"
          type="number"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="期望交割时间" prop="deliveryTime">
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
import { isProjectTypeDisable } from "@/libs/public";
import {
  getDiliveryMethodeDict,
  getExchangeDict,
  getAssetTypeDict,
  getProjectTypeDict,
  getProjectAreaDict,
} from "@/config/dictHelper";
import { getAccoutEnterPriseInfo } from "@/api/tenant";
export default {
  name: "companyPackage",
  props: {
    form: {
      // id: '',
      // institutionName:'',
      // assetType:'',
      // projectType:'',
      // tradeQuantity:'',
      // negotiatedPrice:'',
      // deliveryTime:'',
      // deliveryMethod:'',
      // deliveryExchange:'',
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
      projetcAreaList: [],
      assetTypeList: [],
      projectTypeList: [],
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
      btnText: "报价",
      subForm: {
        institutionName: "",
        deliveryExchange: "",
        deliveryMethod: "",
        assetUnitPrice: "",
        assetType: "",
        projectScopeCode: "",
        projectType: "",
        tradeQuantity: "",
        // assetUnitPrice: "",
      },
      subTitle: "",
      show: false,
      isProjectTypeDisable: false,
      formRules: {
        assetUnitPrice: [
          { pattern: /^[0-9]+(.[0-9]{1,4})?$/, message: "只能输入数字" },
          { required: true, message: "请输入报价价格", trigger: "blur" },
        ],
        assetTypeName: [
          { required: true, message: "请选择资产类型", trigger: "change" },
        ],
        projectTypeName: [
          { required: true, message: "请选择项目类型", trigger: "change" },
        ],
        tradeQuantity: [
          { pattern: /^[0-9]*$/, message: "只能输入数字" },
          { required: true, message: "请输入报价价格", trigger: "blur" },
        ],
      },
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
      this.subForm.assetType = this.checkParam(this.subForm.assetType)
        ? "0140000000"
        : this.subForm.assetType;
      this.subForm.projectType = this.checkParam(this.subForm.projectType)
        ? "0040000000"
        : this.subForm.projectType;
      this.subForm.assetUnitPrice = this.checkParam(this.subForm.assetUnitPrice)
        ? 0
        : this.subForm.assetUnitPrice;
      if (this.subForm.deliveryTime) {
        this.subForm.deliveryTime =
          this.subForm.deliveryTime !== null && this.subForm.deliveryTime !== ""
            ? this.subForm.deliveryTime.split(" ")[0]
            : "";
      }
      if (this.form && this.form.publisherId) {
        getAccoutEnterPriseInfo(this.form.publisherId).then((res) => {
          this.subForm.institutionName = res.tenantName;
        });
      }
      this.subTitle = this.title;
      this.subForm = this.form;
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
      this.projetcAreaList = [];
      data = getProjectAreaDict(this.$store);
      data.map((v) => {
        let item = {
          value: "",
          label: "",
        };
        item.value = v.value;
        item.label = v.name;
        this.projetcAreaList.push(item);
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
      if (this.subForm.tradeQuantity <= 0) {
        this.$message("请输入正确的报价数量");
        return;
      }
      if (this.subForm.assetUnitPrice <= 0) {
        this.$message("请输入正确的报价价格");
        return;
      }

      // if(this.subForm.deliveryTime === '') {
      //   this.$message('期待交割日期不能为空')
      //   return
      // }

      // if(this.subForm.deliveryMethodName === '') {
      //   this.$message('期待交割方式不能为空')
      //   return
      // }

      // if(this.subForm.deliveryExchangeName === '') {
      //   this.$message('期待交割场所不能为空')
      //   return
      // }

      this.clickClose();
      // this.$message("提交询价中...")
      debugger;
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
        deliveryTime: this.selectDate ? this.subForm.selectDate : "",
        tradeQuantity: this.subForm.tradeQuantity,
        tradeQuoteId: this.subForm.id,
      };

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
