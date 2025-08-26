<template>
  <div>
    <el-dialog
      :title="title"
      :visible.sync="show"
      width="720px"
      top="5px"
      class="spec-dialog"
      :before-close="clickClose"
    >
      <div class="content-div">
        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item prop="institutionName" label-width="20%">
              <label slot="label"
              >采购机构<span style="color: red">*</span></label
              >
              <el-autocomplete
                v-model="subForm.institutionName"
                size="medium"
                style="padding-left: 0px"
                class="input-text"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入采购机构"
                @select="handleSelect"
              >
              </el-autocomplete>
            </el-form-item>
          </el-form>
        </div>
        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item prop="name" label-width="20%">
              <label slot="label"
              >采购联系人<span style="color: red">*</span></label
              >
              <el-input
                v-model="subForm.name"
                class="input-text"
                placeholder="请输入采购联系人"
              />
            </el-form-item>
          </el-form>
        </div>
        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item prop="phone" label-width="20%">
              <label slot="label"
              >联系电话<span style="color: red">*</span></label
              >
              <el-input
                v-model="subForm.phone"
                class="input-text"
                placeholder="请输入联系电话"
              />
            </el-form-item>
          </el-form>
        </div>
        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item label="邮箱" prop="email" label-width="20%">
              <el-input
                v-model="subForm.email"
                class="input-text"
                placeholder="请输入邮箱"
              />
            </el-form-item>
          </el-form>
        </div>
        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item label="项目领域" prop="email" label-width="20%">
              <el-cascader
                class="selectbox-root half margleft"
                placeholder="全部"
                v-model="subForm.projectScopeCode"
                :options="projectAreaList"
                clearable
              >
              </el-cascader>
            </el-form-item>
          </el-form>
        </div>
        <el-form
          label-position="left"
          ref="subForm"
          :rules="formRules"
          :model="subForm"
          style="width: 100%"
        >
          <el-form-item label="资产类型" prop="email" label-width="20%">
            <el-cascader
              class="selectbox-root margleft"
              placeholder="全部"
              v-model="subForm.assetType"
              :options="assetsTypeList"
              clearable
              @change="changeAssetType"
            >
            </el-cascader>
          </el-form-item>
        </el-form>

        <el-form
          label-position="left"
          ref="subForm"
          :rules="formRules"
          :model="subForm"
          style="width: 100%"
        >
          <el-form-item label="项目类型" prop="email" label-width="20%">
            <el-cascader
              class="selectbox-root margleft"
              placeholder="全部"
              v-model="subForm.projectType"
              :options="projectTypeList"
              clearable
              :disabled="isProjectTypeDisable"
            >
            </el-cascader>
          </el-form-item>
        </el-form>

        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item prop="tradeQuantity" label-width="20%">
              <label slot="label"
              >采购数量(tCO2e)<span style="color: red">*</span></label
              >
              <el-input
                v-model="subForm.tradeQuantity"
                class="input-text"
                placeholder="请输入采购数量"
              />
            </el-form-item>
          </el-form>
        </div>

        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item prop="negotiatedPrice" label-width="20%">
              <label slot="label">询价(¥)</label>
              <el-input
                v-model="subForm.assetUnitPrice"
                class="input-text"
                placeholder="请输入询价"
              />
            </el-form-item>
          </el-form>
        </div>

        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%"
          >
            <el-form-item prop="expirationDate" label-width="20%">
              <label slot="label">截止采购日期</label>
              <el-date-picker
                class="time-bg"
                style="width: 100%; padding-right: 10px"
                v-model="subForm.expirationDate"
                type="datetime"
                value-format="yyyy-MM-dd"
                format="yyyy-MM-dd"
                placeholder="请选择日期"
                align="right"
                :picker-options="expirationPickerOptions"
                size="medium"
              >
              </el-date-picker>
            </el-form-item>
          </el-form>
        </div>

        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%"
          >
            <el-form-item
              label="期望交割日期"
              prop="deliveryTime"
              label-width="20%"
            >
              <el-date-picker
                class="time-bg"
                style="width: 100%; padding-right: 10px"
                v-model="subForm.deliveryTime"
                type="datetime"
                value-format="yyyy-MM-dd"
                format="yyyy-MM-dd"
                placeholder="请选择日期"
                align="right"
                :picker-options="pickerOptions"
                size="medium"
              >
              </el-date-picker>
            </el-form-item>
          </el-form>
        </div>

        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%"
          >
            <el-form-item
              label="期望交割方式"
              prop="deliveryTime"
              label-width="20%"
            >
              <el-cascader
                class="selectbox-root half margleft"
                placeholder="全部"
                v-model="subForm.deliveryMethod"
                :options="deliverMethodList"
                clearable
              >
              </el-cascader>
            </el-form-item>
          </el-form>
        </div>

        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%"
          >
            <el-form-item
              label="期望交割场所"
              prop="deliveryTime"
              label-width="20%"
            >
              <el-cascader
                class="selectbox-root half margleft"
                placeholder="全部"
                v-model="subForm.deliveryExchange"
                :options="exchangeList"
                clearable
              >
              </el-cascader>
            </el-form-item>
          </el-form>
        </div>

        <div class="item-div">
          <el-form
            label-position="left"
            ref="subForm"
            :rules="formRules"
            :model="subForm"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item label="备注" prop="deliveryTime" label-width="20%">
              <el-input
                type="textarea"
                placeholder="请输入内容"
                rows="3"
                v-model="subForm.mark"
                class="input-mark"
              >
              </el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          @click="clickSubmit"
          class="light-green-btn"
        >{{ btnText }}</el-button
        >
      </div>
    </el-dialog>

    <!-- 确定dialog -->
    <orderResultVue
      :title="tipTitle"
      :content="tipConetent"
      :bottomTxt="tipBottomTxt"
      :img="tipImg"
      :dialogFormVisible="comformDialogFormVisible"
      @changeComfromDialogVisible="listenComfromDialogVisible"
    />
  </div>
</template>
<script>
import { validateIsEmpty } from "@/libs/inputValidate";
import { pushQuote } from "@/api/carbonAssetApi";
import orderResultVue from "./orderResult.vue";
import {
  getDiliveryMethodeDict,
  getExchangeDict,
  getAssetTypeDict,
  getProjectTypeDict,
  getProjectAreaDict,
} from "@/config/dictHelper";
import Cookies from "js-cookie";
import { getTenantInfo } from "@/api/systemadmin";
import { isProjectTypeDisable } from "@/libs/public";
export default {
  name: "companyPackage",
  components: {
    orderResultVue,
  },
  props: {
    dialogFormVisible: false,
    accountInfo: [],
  },
  data() {
    return {
      title: "采购单",
      projectAreaList: [],
      subForm: {
        institutionName: "",
        name: "",
        phone: "",
        email: "",
        assetType: "",
        projectType: "",
        projectScopeCode: "",
        tradeQuantity: "",
        assetUnitPrice: "",
        expirationDate: "",
        deliveryTime: "",
        deliveryMethod: "",
        deliveryExchange: "",
        mark: "",
      },
      isProjectTypeDisable: false,
      seletedDeliverMethod: "",
      assetsTypeList: [],
      projectTypeList: [],
      deliverMethodList: [],
      exchangeList: [],
      seletedExchange: "",
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
      expirationPickerOptions: {
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
      btnText: "提交",
      show: false,
      tipTitle: "提示",
      tipConetent:
        "您的采购单已提交，可在供需行情中查看。确定为您跳转供需行情沟通",
      tipBottomTxt: "如需帮助，可添加交易专员企业微信，为您做开户引导服务",
      tipImg: "@/assets/imgs/head.gif",
      comformDialogFormVisible: false,
      formRules: {
        institutionName: [
          { required: true, message: "请输入采购机构", trigger: "blur" },
        ],
        name: [
          { required: true, message: "请输入采购联系人", trigger: "blur" },
        ],
        phone: [{ required: true, message: "请输入联系电话", trigger: "blur" }],
        tradeQuantity: [
          { required: true, message: "请输入采购数量", trigger: "blur" },
        ],
      },
    };
  },
  watch: {
    dialogFormVisible: {
      immediate: true,
      handler(val) {
        this.show = val;
      },
    },
    accountInfo() {
      this.initParams();
    },
  },
  methods: {
    changeAssetType() {
      this.isProjectTypeDisable = isProjectTypeDisable(
        this.subForm.assetType[0]
      );
    },
    initParams() {
      //  debugger
      //  this.subForm.institutionName = this.accountInfo.institutionName
      //  this.subForm.name = this.accountInfo.name
      //  this.subForm.phone = this.accountInfo.phone
      let JavaInfo = JSON.parse(Cookies.get("JavaInfo"));
      this.subForm.name = JavaInfo.accountName;
      getTenantInfo(JavaInfo.tenantId)
        .then((res) => {
          this.subForm.institutionName = res.tenantName;
          this.subForm.phone = res.telephone;
        })
        .catch((err) => {});
    },
    querySearchAsync(queryString, cb) {
      var restaurants = this.states;
      var results = queryString
        ? restaurants.filter(this.createStateFilter(queryString))
        : restaurants;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 1000 * Math.random());
    },
    handleSelect(item) {
      console.log(item);
    },
    listenComfromDialogVisible(res) {
      this.comformDialogFormVisible = res;
      if (res) {
        this.$emit("changeBuyAssetsDialogFormVisible", true);
      } else {
        this.$emit("changeBuyAssetsDialogFormVisible", false);
      }
      this.show = false;
    },
    clickClose() {
      this.$emit("changeBuyAssetsDialogFormVisible", false);
      this.show = false;
    },
    clickSubmit() {
      let data = {
        assetType: this.subForm.assetType[0],
        assetUnit: "tCO2e",
        assetUnitPrice: this.subForm.assetUnitPrice,
        contactsEmail: this.subForm.email,
        projectScopeCode: this.subForm.projectScopeCode
          ? this.subForm.projectScopeCode[0]
          : this.subForm.projectScopeCode,
        contactsName: this.subForm.name,
        contactsPhone: this.subForm.phone,
        deliveryExchange: this.subForm.deliveryExchange[0],
        deliveryMethod: this.subForm.deliveryMethod[0],
        deliveryTime: this.subForm.deliveryTime,
        expirationDate: this.subForm.expirationDate,
        id: 0,
        institutionName: this.subForm.institutionName,
        projectType: this.subForm.projectType[0],
        publisherId: 0,
        tradeQuantity: this.subForm.tradeQuantity,
        tradeRole: "0270000001",
      };

      if (
        this.subForm.institutionName &&
        this.subForm.name &&
        this.subForm.phone &&
        this.subForm.tradeQuantity
      ) {
        this.show = false;
        // this.comformDialogFormVisible = true;
        pushQuote(data)
          .then((res) => {})
          .catch((error) => {
            this.$message.error(error.msg);
          });
      } else {
        this.$message.warning("必填项不能为空");
      }
    },
  },
  created() {},
  mounted() {
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

    data = getAssetTypeDict(this.$store);
    this.assetsTypeList = [];
    data.map((v) => {
      let item = {
        value: "",
        label: "",
      };
      item.value = v.value;
      item.label = v.name;
      this.assetsTypeList.push(item);
    });

    data = getProjectTypeDict(this.$store);
    this.projectTypeList = [];
    data.map((v) => {
      let item = {
        value: "",
        label: "",
      };
      item.value = v.value;
      item.label = v.name;
      this.projectTypeList.push(item);
    });
  },
};
</script>
<style lang="scss" scoped>
.contentItem {
  width: 100%;
  top: -5px;
}

>>> .el-cascader .el-input .el-input__inner,
>>> .el-cascader .el-input.is-focus .el-input__inner {
  border-color: transparent;
}

.content-div {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}

.item-div {
  margin-top: 5px;
  display: flex;
  flex-direction: row;
}

.text {
  margin-top: auto;
  margin-bottom: auto;
  min-width: 120px;


  font-weight: 400;
  color: #242b35;
}

.input-text {
  width: 100%;
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 10px;
  height: 38px;
  background: #ffffff;
  border-radius: 4px;
  border: 1px solid #e3e7eb;


  font-weight: 400;
  color: #242b35;
}

.half {
  flex-grow: 1;
}

.half-w {
  width: 120px;
}

.right-text {
  text-align: right;
  margin-top: auto;
  margin-bottom: auto;
  margin-right: 10px;
  min-width: 100px;


  font-weight: 400;
  color: #242b35;
}

.padding-right {
  padding-right: 10px;
}

.input-mark {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 10px;
  background: #ffffff;
  border-radius: 4px;
  border: 1px solid #e3e7eb;


  font-weight: 400;
  color: #242b35;
}

.vertical-center {
  margin-top: auto;
  margin-bottom: auto;
}

.margleft {
  margin-left: 10px;
  border-radius: 4px;
  border: 2px solid #dcdfe6;
  background: transparent !important;
}

.selectbox-root {
  height: 40px;
  background: #ffffff;
  border-radius: 4px;
  border: 2px solid #dcdfe6;
  display: flex;
  flex-direction: row;
  padding-left: 4px;
  padding-right: 0px;
}

.time-bg {
  margin-left: 10px;
  margin-right: 10px;
  border-radius: 4px;
  border: none;
  background: transparent !important;

  >>> .el-input__inner {
    border: 2px solid #dcdfe6 !important;
    height: 40px;
  }
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
  left: 10px;
}

.spec-dialog {
  >>> .el-dialog__body {
    padding: 3px 30px;
    overflow-y: auto;
    height: calc(100vh - 140px);
  }
}
</style>
