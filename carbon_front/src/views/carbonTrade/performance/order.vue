<template>
   <el-dialog style="padding:0px" :title="subTitle" :visible.sync="show" width="560px" :before-close="clickClose">
      <div class="root">
         <div class="contentDiv">
           <a class="content verticalCenter cursor-mi">{{mycontent}}</a>
           <button class="light-green-btn verticalCenter" style="margin-left: 20px;" @click="clickOk">确定</button>
         </div>
         <div class="bottomDiv">
            <a class="bottomTxt cursor-mi">{{mybottomTxt}}</a>
            <img class="image marleft30px verticalCenter" src="@/assets/icon/icon_qrcode.jpeg"/>
         </div>
      </div>
   </el-dialog>
</template>
<script>
import {performance} from "@/api/carbonAssetApi";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
export default {
  name: "companyPackage",
  props: {
      dialogFormVisible: false,
      data: {}
  },
  data() {
    return {
       show: false,
       subTitle: '下单提示',
       mycontent: '是否确认下单？确定为您跳转交易所网站，完成履约。',
       mybottomTxt:'如需帮助，可添加交易专员企业微信，为您做交易引导服务',
       orderData: {}
    }
  },
  watch: {
      dialogFormVisible() {
        this.show = this.dialogFormVisible
      },
      data() {
          this.orderData = this.data
      }
  },
  mounted() {
    this.show = this.dialogFormVisible
    this.orderData = this.data
  },
  methods: {
    clickClose() {
       this.$emit('changeComfromDialogVisible', false)
       this.show = false;
       this.orderData = null
    },
    clickOk() {
      performance(this.orderData.id)
      .then(res => {
          this.$message(res.msg + ", 即将为您转跳交易所网站")
          setTimeout(() => {
            openUrlInNewWindow(res.data.exchangeWebsite)
          }, 3000);
          // this.clickClose()
      }).catch(res => {
          this.$message(res.msg)
      })
      this.clickClose()
    }
  },
};
</script>
<style lang="scss" scoped>
.root {
  display: flex;
  flex-direction: column;
 }
 .contentDiv{
   padding: 20px;
   padding-bottom: 30px;
   display: flex;
   flex-direction: row;
 }
 .bottomDiv{
   padding: 20px;
   display: flex;
   flex-direction: row;
   height: 110px;
   background: rgba(38, 181, 129, 0.1);
   border-radius: 0px 0px 8px 8px;
 }
 .context{
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #424C5C;
    line-height: 24px;
    flex-grow: 1;
 }
 .bottomTxt{
    margin-top: auto;
    margin-bottom: auto;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #24A776;
    line-height: 16px;
    flex-grow: 1;
 }
 .verticalCenter{
    margin-top: auto;
    margin-bottom: auto;
 }
 .image{
   width: 80px;
   height: 80px;
 }
 .marleft30px{
   margin-left: 30px;
 }

 .cursor-mi {
  cursor: default;
}

 /deep/.el-dialog__body {
    padding: 0px !important;
  }
</style>>