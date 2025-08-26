<template>
<div>
     <el-dialog top="15px" v-if="isInquiry" :title="inquiryForm.title" :visible.sync="show" width="720px" :before-close="clickClose">
         <el-form
          label-position="left"
          label-width="130px"
          :model="inquiryForm"
          :rules="rules">
          <el-form-item label="采购方联系人姓名" prop="institutionName">
            <el-input
              disabled
              v-model="inquiryForm.institutionName"
              size="medium"
              class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="资产类型" prop="assetTypeName">
            <el-input
                disabled 
                v-model="inquiryForm.assetTypeName"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="项目类型" prop="projectTypeName">
              <el-input
                disabled
                v-model="inquiryForm.projectTypeName"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="采购数量(tCO2e)" prop="quantity">
            <el-input
                disabled
                v-model="inquiryForm.quantity"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="询价(¥)" prop="price">
            <el-input
                disabled
                v-model="inquiryForm.price"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="采购截止日期" prop="expirationDate">
            <el-input
                disabled
                v-model="inquiryForm.expirationDate"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="期望交割日期" prop="deliveryTime">
            <el-input
                disabled
                v-model="inquiryForm.deliveryTime"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
           <el-form-item label="期望交割方式" prop="deliveryMethodName">
            <el-input
                disabled
                v-model="inquiryForm.deliveryMethodName"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="期望交割场所" prop="deliveryExchangeName">
             <el-input
                disabled
                v-model="inquiryForm.deliveryExchangeName"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitInquery" class="light-green-btn">报价</el-button>
        </div>
     </el-dialog>

     <el-dialog top="15px" v-else :title="inquiryForm.title" :visible.sync="show" width="720px" :before-close="clickClose">
         <el-form
          label-position="left"
          label-width="130px"
          :model="inquiryForm"
          :rules="rules">
          <el-form-item label="供应方联系人姓名" prop="institutionName">
            <el-input
              disabled
              v-model="inquiryForm.institutionName"
              size="medium"
              class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="资产类型" prop="assetTypeName">
            <el-input
                disabled 
                v-model="inquiryForm.assetTypeName"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="项目类型" prop="projectTypeName">
              <el-input
                disabled
                v-model="inquiryForm.projectTypeName"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="供应数量(tCO2e)" prop="quantity">
            <el-input
                disabled
                v-model="inquiryForm.quantity"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="报价(¥)" prop="price">
            <el-input
                disabled
                v-model="inquiryForm.price"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="出售截止日期" prop="deliveryTime">
            <el-input
                disabled
                v-model="inquiryForm.expirationDate"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="期望交割日期" prop="deliveryTime">
            <el-input
                disabled
                v-model="inquiryForm.deliveryTime"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
           <el-form-item label="期望交割方式" prop="deliveryMethodName">
            <el-input
                disabled
                v-model="inquiryForm.deliveryMethodName"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
          <el-form-item label="期望交割场所" prop="deliveryExchangeName">
             <el-input
                disabled
                v-model="inquiryForm.deliveryExchangeName"
                size="medium"
                class="contentItem">
              </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitOffer" class="light-green-btn"
            >询价</el-button>
        </div>
     </el-dialog>
</div>
</template>
<script>
import {getDiliveryMethodeDict, getExchangeDict} from "@/config/dictHelper";
import { updateCarbonTradePriceDetail } from "@/api/carbonAssetApi";
import { error } from 'console';
export default {
  name: "companyPackage",
  props: {
      data:{},
      dialogFormVisible: false,
  },
  data() {
    return {
      seletedDeliverMethod:'',
      deliverMethodList:[],
      seletedExchange:'',
      exchangeList:[],
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
      detailData:{},
      show: false,
      detailData:{},
      isInquiry:true,
      inquiryForm: {
        title:'',
        institutionName: "",
        assetTypeName:"",
        projectTypeName: "",
        quantity: 0,
        price: 0,
        expirationDate: '',
        deliveryMethodName: '',
        deliveryTime: ''
      }
    };
  },
  watch: {
      data() {
        this.initParams()
      },
      dialogFormVisible() {
        this.show = this.dialogFormVisible;
      }
  },
  mounted() {
    this.initParams()
  },
  methods: {
    initParams() {
        this.detailData = this.data;
        this.inquiryForm.assetTypeName = this.detailData.assetTypeName
        this.inquiryForm.projectTypeName = this.detailData.projectTypeName
        this.inquiryForm.expirationDate = this.detailData.expirationDate.split(' ')[0]
        debugger
        this.inquiryForm.quantity = this.detailData.tradeQuantity
        this.inquiryForm.price = this.detailData.assetUnitPrice
        this.inquiryForm.deliveryMethodName = this.detailData.deliveryMethodName
        this.inquiryForm.deliveryTime = this.detailData.deliveryTime.split(' ')[0]
        this.inquiryForm.deliveryExchangeName =  this.detailData.deliveryExchangeName
        if (this.detailData.tradeRole == '0270000001') {
            this.isInquiry = true
            this.inquiryForm.institutionName = this.detailData.buyerName
            this.inquiryForm.title = "报价单";
            // this.inquiryForm.title = "报价单-" +  this.detailData.buyerName
        } else {
            this.isInquiry = false
            this.inquiryForm.institutionName = this.detailData.sellerName
            this.inquiryForm.title = "供应单-" +  this.detailData.sellerName
        }
    },
    clickClose() {
       this.$emit('changeDialogFormVisible', false);
       this.show = false;
       this.detailData = {}
    },
    submitInquery() {
       this.clickClose()
       debugger
        let data = {
          id: this.detailData.id,
          inquiryPriceQuantity: this.inquiryForm.quantity,
          inquiryPrice: this.inquiryForm.price,
          inquiryExpirationDate: this.inquiryForm.expirationDate,
          deliveryMethod: this.inquiryForm.deliveryMethod,
          deliveryTime: this.inquiryForm.deliveryTime
       }
       updateCarbonTradePriceDetail(data).then(res=> {
         this.$message(res.msg)
       }).catch(error => {
         this.$message(res.msg)
       })
    },
    submitOffer() {
       this.clickClose()
       let data = {
          id: this.detailData.id,
          offerPriceQuantity: this.inquiryForm.quantity,
          offerPrice: this.inquiryForm.price,
          inquiryExpirationDate: this.inquiryForm.expirationDate,
          deliveryMethod: this.inquiryForm.deliveryMethod,
          deliveryTime: this.inquiryForm.deliveryTime
      }
       updateCarbonTradePriceDetail(data).then(res=> {
         this.$message(res.msg)
       }).catch(error => {
         this.$message(res.msg)
       })
    }
  },
  created() {
   
  },
  mounted() {
    this.initParams()
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
  top: -5px
 }
</style>