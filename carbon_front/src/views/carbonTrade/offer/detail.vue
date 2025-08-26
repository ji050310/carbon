<template>
  <div>
    <el-dialog v-if="isInquiry" :title="inquiryForm.title" :visible.sync="show" width="720px"
               :before-close="clickClose">
      <el-form label-position="left" label-width="130px" :model="inquiryForm" :rules="rules">
        <el-form-item label="采购方" prop="institutionName">
          <el-input disabled v-model="inquiryForm.institutionName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="资产类型" prop="assetTypeName">
          <el-input disabled v-model="inquiryForm.assetTypeName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="项目类型" prop="projectTypeName">
          <el-input disabled v-model="inquiryForm.projectTypeName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="采购数量(tCO2e)" prop="quantity">
          <el-input disabled v-model="inquiryForm.quantity" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="询价(¥)" prop="price">
          <el-input disabled v-model="inquiryForm.price" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="采购截止日期" prop="expirationDate">
          <el-input disabled v-model="inquiryForm.expirationDate" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="期望交割日期" prop="deliveryTime">
          <el-input disabled v-model="inquiryForm.deliveryTime" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="期望交割方式" prop="deliveryMethodName">
          <el-input disabled v-model="inquiryForm.deliveryMethodName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="期望交割场所" prop="deliveryExchangeName">
          <el-input disabled v-model="inquiryForm.deliveryExchangeName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitInquery" class="light-green-btn">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog v-else :title="inquiryForm.title" :visible.sync="show" width="720px" :before-close="clickClose">
      <el-form label-position="left" label-width="130px" :model="inquiryForm">
        <el-form-item label="供应方" prop="institutionName">
          <el-input disabled v-model="inquiryForm.institutionName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="资产类型" prop="assetTypeName">
          <el-input disabled v-model="inquiryForm.assetTypeName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="项目类型" prop="projectTypeName">
          <el-input disabled v-model="inquiryForm.projectTypeName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="供应数量(tCO2e)" prop="quantity">
          <el-input disabled v-model="inquiryForm.quantity" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="报价(¥)" prop="price">
          <el-input disabled v-model="inquiryForm.price" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="出售截止日期" prop="deliveryTime">
          <el-input disabled v-model="inquiryForm.expirationDate" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="期望交割日期" prop="deliveryTime">
          <el-input disabled v-model="inquiryForm.deliveryTime" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="期望交割方式" prop="deliveryMethodName">
          <el-input disabled v-model="inquiryForm.deliveryMethodName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
        <el-form-item label="期望交割场所" prop="deliveryExchangeName">
          <el-input disabled v-model="inquiryForm.deliveryExchangeName" size="medium" class="contentItem">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitOffer" class="light-green-btn">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { getDiliveryMethodeDict, getExchangeDict } from "@/config/dictHelper";
import { startTrading } from "@/api/carbonAssetApi";
export default {
  name: "detail",
  props: {
    data: {},
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
      // detailData: {},
      show: false,
      isInquiry: true,
      inquiryForm: {
        // id: "",
        // title: "",
        // institutionName: "",
        // assetTypeName: "",
        // projectTypeName: "",
        // quantity: 0,
        // contactsName: "",
        // price: 0,
        // expirationDate: "",
        // deliveryMethodName: "",
        // deliveryTime: "",
      },
    };
  },
  watch: {
    data() {
      debugger;
      this.initParams();
    },
    dialogFormVisible() {
      this.show = this.dialogFormVisible;
    },
  },
  // mounted() {
  //   // this.initParams();
  // },
  methods: {
    initParams() {
      debugger;
      // this.detailData = this.data;
      // this.inquiryForm.id = this.data.id;
      // this.inquiryForm.assetTypeName = this.data.assetTypeName;
      // this.inquiryForm.projectTypeName = this.data.projectTypeName;
      // this.inquiryForm.expirationDate = this.data.expirationDate? this.data.expirationDate.split(" ")[0]:'--';
      // if (this.data.tradeRole == "0270000001") {
      //   this.isInquiry = true;
      //   this.inquiryForm.institutionName = this.data.buyerName;
      //   this.inquiryForm.title = "报价单-" + this.data.buyerName;
      //   this.inquiryForm.quantity = this.data.buyerTradeQuantity;
      //   this.inquiryForm.price = this.data.buyerUnitPrice;
      //   this.inquiryForm.contactsName = this.data.contactsName;
      //   this.inquiryForm.deliveryMethodName =
      //                      this.data.buyerDeliveryMethodName !== ' ' && this.data.buyerDeliveryMethodName != null? this.data.buyerDeliveryMethodName : '全部';
      //   this.inquiryForm.deliveryTime = this.data.buyerDeliveryTime;
      //   this.inquiryForm.deliveryExchangeName =
      //               this.data.buyerDeliveryExchange !== ' ' && this.data.buyerDeliveryExchange != null? this.data.buyerDeliveryExchange : '全部';
      // } else {
      //   debugger
      //   this.isInquiry = false;
      //   this.inquiryForm.institutionName = this.data.sellerName;
      //   this.inquiryForm.title = "供应单-" + this.data.sellerName;
      //   this.inquiryForm.quantity = this.data.sellerTradeQuantity;
      //   this.inquiryForm.price = this.data.sellerUnitPrice;
      //   this.inquiryForm.deliveryMethodName =
      //            this.data.sellerDeliveryMethodName !== ' ' && this.data.sellerDeliveryMethodName != null? this.data.sellerDeliveryMethodName : '全部';
      //   this.inquiryForm.contactsName = this.data.contactsName;
      //   this.inquiryForm.deliveryTime = this.data.sellerDeliveryTime;
      //   this.inquiryForm.deliveryExchangeName =
      //               this.data.sellerDeliveryExchange !== ' ' && this.data.sellerDeliveryExchange != null? this.data.sellerDeliveryExchange : '全部';
      // }
    },
    clickClose() {
      this.$emit("changeDialogFormVisible", false);
      this.show = false;
      this.detailData = {};
    },
    submitInquery() {
      this.clickClose();
      // let data = {
      //   assetUnitPrice: this.inquiryForm.price,
      //   deliveryExchange:this.inquiryForm.deliveryExchange,
      //   deliveryMethod: this.inquiryForm.deliveryMethod,
      //   deliveryTime:this.inquiryForm.deliveryTime,
      //   tradeQuantity:this.inquiryForm.quantity,
      //   tradeQuoteId: this.inquiryForm.id,
      // }
      // debugger
      // startTrading(data).then(res => {
      //     this.$message(res.msg)
      //     this.$emit('successSubmit', false);
      // }).catch(error => {
      //   this.$message(error.msg)
      // })
    },
    submitOffer() {
      this.clickClose();
    },
  },
  created() { },
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
