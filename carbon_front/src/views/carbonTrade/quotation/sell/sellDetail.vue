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
      :rules="rules"
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
      <el-form-item label="资产类型" prop="assetTypeName">
        <el-input
          disabled
          v-model="subForm.assetTypeName"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="项目类型" prop="projectTypeName">
        <el-input
          disabled
          v-model="subForm.projectTypeName"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="供应数量(tCO2e)" prop="tradeQuantity">
        <el-input
          disabled
          v-model="subForm.tradeQuantity"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="报价(¥)" prop="assetUnitPrice">
        <el-input
          disabled
          v-model="subForm.assetUnitPrice"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="期望交割日期" prop="deliveryTime">
        <el-input
          disabled
          v-model="subForm.deliveryTime"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="期望交割方式" prop="deliveryMethodName">
        <el-input
          disabled
          v-model="subForm.deliveryMethodName"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="期望交割场所" prop="deliveryExchangeName">
        <el-input
          disabled
          v-model="subForm.deliveryExchangeName"
          size="medium"
          class="contentItem"
        >
        </el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="clickClose" class="light-green-btn">{{
          btnText
        }}</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { getDiliveryMethodeDict, getExchangeDict } from "@/config/dictHelper";
import { getAccoutEnterPriseInfo } from "@/api/tenant";
export default {
  name: "companyPackage",
  props: {
    form: {
      institutionName: "",
      assetType: "",
      projectType: "",
      tradeQuantity: "",
      negotiatedPrice: "",
      deliveryTime: "",
      contactsName: "",
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
      btnText: "确定",
      subForm: {},
      subTitle: "",
      show: false,
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
    clickClose() {
      this.$emit("changeDialogFormVisible", false);
      this.show = false;
    },
    checkParam(value) {
      return !value || value === "" || value === "--" || value === "/";
    },
    initParams() {
      // this.subForm.assetTypeName = this.checkParam(this.subForm.assetType) ? '全部':this.subForm.assetType
      debugger;
      // this.subForm.projectTypeName = this.checkParam(this.subForm.projectType) ? '全部':this.subForm.projectType
      // this.subForm.tradeQuantity = this.checkParam(this.subForm.tradeQuantity) ? '--':this.subForm.tradeQuantity
      this.subForm.assetUnitPrice = this.checkParam(this.subForm.assetUnitPrice)
        ? "0"
        : this.subForm.assetUnitPrice;
      // this.subForm.deliveryTime = this.subForm.deliveryTime !== null && this.subForm.deliveryTime !== '' ? this.subForm.deliveryTime.split(' ')[0]:''
      // this.subForm.deliveryTime = this.checkParam(this.subForm.deliveryTime) ? '--':this.subForm.deliveryTime
      // this.subForm.deliveryMethodName = this.checkParam(this.subForm.deliveryMethod) ? '全部':this.subForm.deliveryMethod
      // this.subForm.deliveryExchangeName = this.checkParam(this.subForm.deliveryExchange) ? '全部':this.subForm.deliveryExchange

      if(this.form && this.form.publisherId) {
        getAccoutEnterPriseInfo(this.form.publisherId).then((res) => {
          this.subForm.institutionName = res.tenantName;
        });
      }
    },
  },
  created() {},
  mounted() {
    this.subTitle = this.title;
    this.subForm = this.form;
    this.initParams();
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
  },
};
</script>
<style scoped>
.contentItem {
  width: 100%;
  top: -5px;
}
</style>
