<template>
  <el-dialog :title="title" :visible.sync="show" width="720px" top="5px" class="spec-dialog" :before-close="clickClose">
    <div class="content-div">
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="sellerName" label-width="20%">
            <label slot="label">供应方机构名称<span style="color:red">*</span></label>
            <el-input placeholder="请输入供应方机构名称" v-model="detailData.sellerName" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="sellerContacts" label-width="20%">
            <label slot="label">供应方联系人姓名<span style="color:red">*</span></label>
            <el-input placeholder="请输入供应方联系人姓名" v-model="detailData.sellerContacts" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="sellerPhone" label-width="20%">
            <label slot="label">供应方联系电话<span style="color:red">*</span></label>
            <el-input placeholder="请输入供应方联系电话" v-model="detailData.sellerPhone" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="sellerEmail" label-width="20%">
            <label slot="label">供应方邮箱<span style="color:red">*</span></label>
            <el-input placeholder="请输入供应方邮箱" v-model="detailData.sellerEmail" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="buyerName" label-width="20%">
            <label slot="label">采购方机构名称<span style="color:red">*</span></label>
            <el-input placeholder="请输入采购方机构名称" v-model="detailData.buyerName" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="buyerContacts" label-width="20%">
            <label slot="label">采购方联系人姓名<span style="color:red">*</span></label>
            <el-input placeholder="请输入采购方联系人姓名" v-model="detailData.buyerContacts" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="buyerPhone" label-width="20%">
            <label slot="label">采购方联系电话<span style="color:red">*</span></label>
            <el-input placeholder="请输入采购方联系电话" v-model="detailData.buyerPhone" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="buyerEmail" label-width="20%">
            <label slot="label">采购方邮箱<span style="color:red">*</span></label>
            <el-input placeholder="请输入采购方邮箱" v-model="detailData.buyerEmail" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>

      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData" style="width:100%;">
          <el-form-item prop="assetTypeName" label-width="20%">
            <label slot="label">资产类型<span style="color:red">*</span></label>
            <el-cascader class="selectbox-root margleft" placeholder="全部" v-model="detailData.assetType"
                         :options="assetsTypeList" clearable @change="updateType">
            </el-cascader>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData" style="width:100%;">
          <el-form-item prop="projectTypeName" label-width="20%">
            <label slot="label">项目类型<span style="color:red">*</span></label>
            <el-cascader :class="projectTypeClass(isQuota)" placeholder="全部" v-model="detailData.projectType"
                         :options="projectTypeList" clearable :disabled="isQuota">
            </el-cascader>
          </el-form-item>
        </el-form>
      </div>

      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="tradeQuantity" label-width="20%">
            <label slot="label">交易量(tCO2e)<span style="color:red">*</span></label>
            <el-input placeholder="请输入交易量" v-model="detailData.tradeQuantity" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="item-div">
        <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
                 style="width:100%;margin-right: 10px;">
          <el-form-item prop="assetUnitPrice" label-width="20%">
            <label slot="label">协议价格(¥)<span style="color:red">*</span></label>
            <el-input placeholder="请输入协议价格" v-model="detailData.assetUnitPrice" size="medium" class="input-text">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
    </div>

    <div class="item-div">
      <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData" style="width:100%">
        <el-form-item prop="deliveryTime" label-width="20%">
          <label slot="label">交割日期</label>
          <el-date-picker class="time-bg" style="width:100%;padding-right: 10px;" v-model="detailData.deliveryTime"
                          type="datetime" placeholder="请选择日期" align="right" :picker-options="pickerOptions" size="medium">
          </el-date-picker>
        </el-form-item>
      </el-form>
    </div>
    <div class="item-div">
      <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData" style="width:100%">
        <el-form-item prop="deliveryMethod" label-width="20%">
          <label slot="label">交割方式<span style="color:red">*</span></label>
          <el-cascader class="selectbox-root half margleft" placeholder="全部" v-model="detailData.deliveryMethod"
                       :options="deliverMethodList" clearable>
          </el-cascader>
        </el-form-item>
      </el-form>
    </div>

    <div class="item-div">
      <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData" style="width:100%">
        <el-form-item prop="deliveryExchange" label-width="20%">
          <label slot="label">交割场所<span style="color:red">*</span></label>
          <el-cascader class="selectbox-root half margleft" placeholder="全部" v-model="detailData.deliveryExchange"
                       :options="exchangeList" clearable>
          </el-cascader>
        </el-form-item>
      </el-form>
    </div>

    <div class="item-div">
      <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData" style="width:100%">
        <el-form-item prop="expirationDate" label-width="20%">
          <label slot="label">截止交易日期</label>
          <el-date-picker class="time-bg" style="width:100%;padding-right: 10px;" v-model="detailData.expirationDate"
                          type="datetime" placeholder="请选择日期" align="right" :picker-options="expirationPickerOptions" size="medium">
          </el-date-picker>
        </el-form-item>
      </el-form>
    </div>

    <div class="item-div">
      <el-form label-position="left" ref="detailData" :rules="formRules" :model="detailData"
               style="width:100%;margin-right: 10px;">
        <el-form-item label="备注" label-width="20%">
          <el-input type="textarea" placeholder="请输入内容" rows="3" v-model="detailData.mark" class="input-mark">
          </el-input>
        </el-form-item>
      </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="clickSubmit" class="light-green-btn">{{ btnText }}</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { MessageBox } from 'element-ui'
import {getListByTenant} from "@/api/tenant"
import { intendedTransaction } from "@/api/carbonAssetApi";
import { getTenantInfo } from "@/api/systemadmin";
import { verifyPhoneNumber,emailVerify} from '@/libs/public';
import { getDiliveryMethodeDict, getExchangeDict, getAssetTypeDict, getProjectTypeDict } from "@/config/dictHelper";
export default {
  name: "companyPackage",
  props: {
    dialogFormVisible: false,
    data: {}
  },
  data() {
    return {
      title: '意向成交',
      isQuota: false,
      detailData: {
        assetType: "",
        assetUnitPrice: 0,
        buyerContacts: "",
        buyerEmail: "",
        buyerId: "",
        buyerName: "",
        buyerPhone: "",
        deliveryExchange: "",
        deliveryMethod: "",
        deliveryTime: "",
        expirationDate: "",
        projectType: "",
        sellerContacts: "",
        sellerEmail: "",
        sellerId: "",
        sellerName: "",
        sellerPhone: "",
        tradeQuantity: 0,
        tradeQuoteId: "",
        // mark:''
      },
      seletedDeliverMethod: '',
      assetsTypeList: [],
      projectTypeList: [],
      deliverMethodList: [],
      exchangeList: [],
      seletedExchange: '',
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
      btnText: '提交',
      show: false,
      formRules: {
        buyerName: [
          { required: true, message: "请输入采购方机构名称", trigger: "blur" }
        ],
        buyerContacts: [
          { required: true, message: "请输入采购方联系人姓名", trigger: "blur" }
        ],
        buyerPhone: [
          { required: true, message: "请输入采购方联系电话", trigger: "blur" }
        ],
        buyerEmail: [
          { required: true, message: "请输入采购方邮箱", trigger: "blur" }
        ],
        sellerName: [
          { required: true, message: "请输入供应方机构名称", trigger: "blur" }
        ],
        sellerContacts: [
          { required: true, message: "请输入供应方联系人姓名", trigger: "blur" }
        ],
        sellerPhone: [
          { required: true, message: "请输入供应方联系电话", trigger: "blur" }
        ],
        sellerEmail: [
          { required: true, message: "请输入供应方邮箱", trigger: "blur" }
        ],
        assetTypeName: [
          { required: true, message: "请选择资产类型", trigger: "blur" }
        ],
        projectTypeName: [
          { required: true, message: "请选择项目类型", trigger: "blur" }
        ],
        tradeQuantity: [
          { required: true, message: "请输入交易量", trigger: "blur" }
        ],
        assetUnitPrice: [
          { required: true, message: "请输入交易量" }
        ],
        deliveryMethod: [
          { required: true, message: "请选择交割方式", trigger: "blur" }
        ],
        deliveryExchange: [
          { required: true, message: "请选择交易所", trigger: "blur" }
        ]
      },
      byTenantList:{},
    };
  },
  watch: {
    dialogFormVisible() {
      this.show = this.dialogFormVisible;
    },
    data() {
      this.initParams()
    }
  },
  methods: {
    getListByTenantInfo() {
      let data = {};
      getListByTenant(data).then((res) => {
        let resLen = res.length;
        let obj = {};
        for (let i = 0; i < resLen; i++) {
          obj[res[i].exchangeName] = {
            "website":res[i].website
          }
        }
        this.byTenantList = obj;
      });
    },
    initParams() {
      // this.detailData = this.data;

      this.detailData.projectType = this.data.projectType
      this.detailData.buyerId = this.data.buyerId
      this.detailData.sellerId = this.data.sellerId
      if (this.data.tradeRole === '0270000001') {
        this.detailData.tradeQuantity = this.data.buyerTradeQuantity
        this.detailData.assetUnitPrice = (this.data.buyerUnitPrice && this.data.buyerUnitPrice != "--") ? this.data.buyerUnitPrice : 0
        if (this.data.buyerDeliveryTime != "--") {
          this.detailData.deliveryTime = this.data.buyerDeliveryTime
        } else {
          this.detailData.deliveryTime = ""
        }
        this.detailData.deliveryMethod = this.data.buyerDeliveryMethod
        this.detailData.deliveryExchange = this.data.buyerDeliveryExchange
      } else {
        this.detailData.tradeQuantity = this.data.sellerTradeQuantity
        this.detailData.assetUnitPrice = this.data.sellerUnitPrice
        if (this.data.sellerDeliveryTime != "--") {
          this.detailData.deliveryTime = this.data.sellerDeliveryTime
        } else {
          this.detailData.deliveryTime = ""
        }
        this.detailData.deliveryMethod = this.data.sellerDeliveryMethod
        this.detailData.assetType = this.data.assetType
        this.detailData.deliveryExchange = this.data.sellerDeliveryExchange
      }

      getTenantInfo(this.data.buyerId).then(res => {
        this.detailData.buyerName = res.tenantName
        this.detailData.buyerContacts = res.tenantName
        this.detailData.buyerPhone = res.telephone
      }).catch(err => {

      })
      getTenantInfo(this.data.sellerId).then(res => {
        this.detailData.sellerName = res.tenantName
        this.detailData.sellerContacts = res.tenantName
        this.detailData.sellerPhone = res.telephone
        // this.detailData.sellerEmail = res.data.tenantEmail
      }).catch(err => {

      })
    },
    /*
     *@Description: 选中资产类型改变时执行,把项目类型不可选
     *@MethodAuthor: liuboting
     *@Date: 2022-07-02 19:51:31
    */
    /*
     *@Description: 资产类型为“碳配额”，不作“项目类型”的强制提示
     *@MethodAuthor: lanfu
     *@Date: 2022-10-14 19:51:31
    */
    updateType() {
      if (this.detailData.assetType[0] == "0140000002" || this.detailData.assetType[0] == "0140000003") {
        // if(this.detailData.assetType[0] == "0140000003") {
        this.isQuota = true
      } else {
        this.isQuota = false
      }
    },
    //项目类型的样式
    projectTypeClass(isQuota) {
      if (isQuota) {
        return "selectbox-root margleft colorType"
      } else {
        return "selectbox-root margleft"
      }
    },
    clickClose() {
      this.$emit('changeDialogFormVisible', false);
      this.show = false;
    },
    clickSubmit() {
      let isVerfy = true;
      if(this.detailData.sellerEmail){
        isVerfy = emailVerify(this.detailData.sellerEmail)
        if(!isVerfy){
          return this.$message('供应方邮箱格式错误，请输入正确的邮箱！')
        }
      }
      if(this.detailData.buyerEmail){
        isVerfy = emailVerify(this.detailData.buyerEmail)
        if(!isVerfy){
          return this.$message('采购方邮箱格式错误，请输入正确的邮箱！')
        }
      }

      if(this.detailData.buyerPhone){
        isVerfy = verifyPhoneNumber(this.detailData.buyerPhone)
        if(!isVerfy){
          return this.$message('采购方电话格式错误，请输入正确的电话号码！')
        }
      }
      if(this.detailData.sellerPhone){
        isVerfy = verifyPhoneNumber(this.detailData.sellerPhone)
        if(!isVerfy){
          return this.$message('供应方电话格式错误，请输入正确的电话号码！')
        }
      }
      if (this.detailData.assetUnitPrice === '--' || this.detailData.assetUnitPrice === '0') {
        this.$message('请检查协议价格')
        return
      }


      if (!this.detailData.assetType || this.detailData.assetType === '--') {
        this.$message('请检选择资产类型')
        return
      }

      if (!this.detailData.projectType || this.detailData.projectType === '--') {
        this.$message('请选择项目类型')
        return
      }

      MessageBox.confirm('', '提示', {
        message: '提交意向成交前，请确保已与交易方达成一致。是否确认提交？',
        confirmButtonText: '确定',
      }).then(() => {
        this.clickClose()
        let tradeContract = {
          assetType: this.detailData.assetType ? this.detailData.assetType[0] : '',
          assetUnit: this.detailData.assetUnit,
          assetUnitPrice: this.detailData.assetUnitPrice,
          buyerContacts: this.detailData.buyerContacts,
          buyerEmail: this.detailData.buyerEmail,
          buyerId: this.detailData.buyerId,
          buyerName: this.detailData.buyerName,
          buyerPhone: this.detailData.buyerPhone,
          deliveryExchange: this.detailData.deliveryExchange ? this.detailData.deliveryExchange[0] : '',
          deliveryMethod: this.detailData.deliveryMethod ? this.detailData.deliveryMethod[0] : '',
          deliveryTime: this.detailData.deliveryTime,
          expirationDate: this.detailData.expirationDate,
          projectType: this.detailData.projectType ? this.detailData.projectType[0] : '',
          sellerContacts: this.detailData.sellerContacts,
          sellerEmail: this.detailData.sellerEmail,
          sellerId: this.detailData.sellerId,
          sellerName: this.detailData.sellerName,
          sellerPhone: this.detailData.sellerPhone,
          tradeQuantity: this.detailData.tradeQuantity,
          tradeQuoteId: this.data.tradeQuoteId,
        }

        let data = {
          tradeContract: tradeContract,
          tradePriceId: this.data.id
        };

        intendedTransaction(data).then(res => {
          if (res.code == 200) {
            let exchangeName = "";
            for (let i = 0; i < this.exchangeList.length; i++) {
              if (this.detailData.deliveryExchange == this.exchangeList[i].value) {
                exchangeName = this.exchangeList[i].label;
              }
            }
            let href
            if(this.byTenantList[exchangeName]){
              href = this.byTenantList[exchangeName].website;
            }
            if (href) {
              localStorage.setItem("goExchange",href)
            }
          }
          this.$message.success(res.msg);
          this.$emit('successSubmit', true);
          // this.detailData=[]
        })
          .catch(err => {
            this.$emit('successSubmit', false);
            //  this.detailData=[]
          });
      })
    }
  },
  created() {

  },
  mounted() {
    this.getListByTenantInfo();
    this.show = this.dialogFormVisible;
    // this.initParams();

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
    debugger

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
    debugger

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
  top: -5px
}

>>>.el-cascader .el-input .el-input__inner,
>>>.el-cascader .el-input.is-focus .el-input__inner {
  border-color: transparent;
}

.content-div {
  display: flex;
  flex-direction: column;
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
  color: #242B35;
}

.input-text {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 10px;
  height: 38px;
  background: #FFFFFF;
  border-radius: 4px;
  border: 1px solid #E3E7EB;


  font-weight: 400;
  color: #242B35;
}

.half {
  flex-grow: 1;
}

.half-w {
  width: 120px;
}

.right-text {
  // text-align: right;
  margin-top: auto;
  margin-bottom: auto;
  margin-right: 0px;
  min-width: 100px;


  font-weight: 400;
  color: #242B35;
}

.padding-right {
  padding-right: 10px;
}

.input-mark {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 10px;
  background: #FFFFFF;
  border-radius: 4px;
  border: 1px solid #E3E7EB;


  font-weight: 400;
  color: #242B35;
}

.margleft {
  margin-left: 10px;
  border-radius: 4px;
  border: 2px solid #DCDFE6;
}

.colorType {
  background-color: #f1f2f3;
}

.selectbox-root {
  height: 40px;
  border-radius: 4px;
  border: 2px solid #DCDFE6;
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

  >>>.el-input__inner {
    border: 2px solid #DCDFE6 !important;
    height: 40px;
  }
}

>>>.el-form-item.is-required:not(.is-no-asterisk)>.el-form-item__label:before,
.el-form-item.is-required:not(.is-no-asterisk) .el-form-item__label-wrap>.el-form-item__label:before {
  content: '';
  color: transparent !important;
  margin-right: 0px;
}

>>>.el-form-item__error {
  color: #ff4949;


  padding-top: 4px;
  position: absolute;
  top: 100%;
  left: 10px;
}

.spec-dialog {
  >>>.el-dialog__body {
    padding: 3px 30px;
    overflow-y: auto;
    height: calc(100vh - 140px);
  }
}
</style>
