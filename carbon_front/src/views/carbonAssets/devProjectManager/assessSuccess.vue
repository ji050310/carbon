<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="cardBody">
          <img src="@/assets/icon/icon_assess.png" alt="" class="assessIcon" />
          <span class="header">碳e估</span>
          <!-- <span class="createTips" style="margin-left: 10px">{{ this.form.creatorId }} {{ this.form.createdTime }}
            创建</span>
          <span class="updateTips">{{ this.form.updatedId }} {{ this.form.updatedTime }} 更新</span> -->
          <button style="float: right" class="normal-white-btn">
            立即分享
          </button>
          <button style="float: right; margin-right: 16px;display:none;" class="light-green-btn" @click="returnCal">
            重新评估
          </button>
          <el-divider></el-divider>
        </div>
      </div>
      <div class="cardBody-top">
        <div class="headerTips">
          碳e估是碳信使推出的碳资产线上评估神器。基于现有CCER方法学和已开发碳资产项目数据，利用大数据及人工智能等核心技术及算法，可在线上科学、快速完成碳资产价值评估和分析服务。
        </div>
        <div style="height: 30px"></div>
        <div class="card">
          <span class="card-top">预估碳减排量</span>
          <span class="card-bottom">100,000,000 <span style="font-size: 15px"> tCO2e</span></span>
        </div>
        <div class="card" style="margin-left: 20px">
          <span class="card-top">碳资产估值</span>
          <span class="card-bottom">100,000,000<span style="font-size: 15px"> 元人民币</span></span>
        </div>
        <div style="height: 30px"></div>
        <div class="headerTips" style="
            font-size: 12px;
            font-family: PingFangSC-Regular, PingFang SC;
            font-weight: 400;
            color: #808ea5;
          ">
          说明：根据现有数据和系统内方法学大数据进行估算，最终开发量以实际签发为准。在估算量基础上，以现有碳行情价格估算价值，最终以实际成交价格为准。
        </div>
        <div style="height: 30px"></div>
        <div class="scanCode">
          <div class="scanImg"></div>
        </div>
        <div class="scanTip">扫码关注</div>
        <div class="scanCode" style="margin-left: 30px">
          <div class="scanImg"></div>
        </div>
        <div class="scanTip">扫码评估</div>
      </div>
    </div>
    <div class="divBox2">
      <div class="little-card" style="">
        <img src="@/assets/icon/icon_assess_01.png" alt="" style="position: relative;top:9px;right:2px" />
        <span class="little-card-title">资产评估</span>
        <div class="headerTips" style="color: #5E6C84;margin-top:20px;width:auto;height: auto;">
          您是否拥有碳资产，哪些可以被开发为碳资产有多少价值？碳信使为客户提供科学便捷的评估工具，简单快速完成碳资产价值评估。</div>
      </div>
      <div class="little-card" style=""> <img src="@/assets/icon/icon_assess_01.png" alt=""
          style="position: relative;top:9px;right:2px" />
        <span class="little-card-title">资产开发</span>
        <div class="headerTips" style="color: #5E6C84;margin-top:20px;width:auto;height: auto;">
          对于已评估的碳资产，碳信使为客户提供从立项到签发的全套碳资产开发管理系统，降低开发成本，提升开发效率，更快更好的完成碳资产开发。</div>
      </div>
      <div class="little-card"> <img src="@/assets/icon/icon_assess_01.png" alt=""
          style="position: relative;top:9px;right:2px" />
        <span class="little-card-title">变现引流</span>
        <div class="headerTips" style="color: #5E6C84;margin-top:20px;width:auto;height: auto;">
          对开发的碳资产怎么快速变现，碳信使提供交易前中后的线上线下服务，并为有需求客户提供用户引流的增值服务，为您有效管理资产保驾护航。</div>
      </div>
    </div>
  </div>
</template>

<script>
import { getProjectAreaDict } from "@/config/dictHelper";
export default {
  name: "AssessSuccess",
  data() {
    return {};
  },
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      vm.fromPath = from.path; //获取上一级路由的路径
    });
  },
  watch: {},
  methods: {
    returnCal() {
      this.$router.push("/carbon/projectAssess");
    }
    // onSubmit() {
    //   // if (this.onSave()) {
    //     this.$router.push({
    //       path: "/carbon/ownerAdd",
    //       query: { id: this.form.id },
    //     });
    //   // }
  },
  mounted() { },
};
</script>

<style lang="scss" scoped>
.root {
  background: #f2f5f7;
}

.headerTips {
  width: 613px;
  height: 54px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #424c5c;
  line-height: 26px;
}

.divBox {
  margin: 20px;
  background: #ffffff;
  box-shadow: 0px 2px 8px 0px #eaf0f3;
  border-radius: 8px;
}

.divBox2 {
  margin: 20px;
  box-shadow: 0px 2px 8px 0px #eaf0f3;
  border-radius: 8px;
  flex-wrap: wrap;
  display: flex;
  justify-content: space-between;
}

.little-card {
  // height: 174px;
  height: auto;
  background: white;
  padding: 20px;
  border-radius: 6px;
  // flex: 1;
  width: 32%;
  // display: inline-block;
}

.assessItem {
  font-size: 16px;
  font-family: PingFangSC-Medium;
  padding: 10px 5px 10px 5px;
}

// .container {
//   margin: 10px 0px 20px 0px;
//   display: flex;
//   flex-direction: row;
// }
.delete-btn {
  width: 20px;
  height: 20px;
  margin-left: 5px;
  margin-right: 5px;
  margin-top: 10px;
  align-self: center;
  cursor: pointer;
}

.cardBody {
  margin: 30px 30px 30px 30px;
}

.body {
  width: 65px;
  height: 25px;
  font-size: 25px;
  font-family: SourceHanSansCN-Bold, SourceHanSansCN;
  font-weight: bold;
  color: #24a776;
  line-height: 38px;
}

.header {
  width: 65px;
  height: 25px;
  font-size: 25px;
  font-family: SourceHanSansCN-Bold, SourceHanSansCN;
  font-weight: bold;
  color: #24a776;
  line-height: 38px;
}

.createTips {
  color: rgba(128, 142, 165, 1);
  font-size: 13px;
  float: right;
}

.updateTips {
  overflow-wrap: break-word;
  color: rgba(128, 142, 165, 1);
  font-size: 13px;
  float: right;
  margin-right: 20px;
}

.el-divider {
  margin-top: 20px;
}

.outer20 {
  background-color: rgba(38, 181, 129, 1);
  height: 38px;
  width: 140px;
  cursor: pointer;
  margin-left: 20px;
  float: left;
  margin-top: 20px;

  .info5 {
    width: 123px;
    height: 14px;
    overflow-wrap: break-word;
    color: rgba(255, 255, 255, 1);
    font-size: 14px;
    text-align: center;
    white-space: nowrap;
    line-height: 14px;
    // display: block;
    margin: 20px 0 0 35px;
    position: relative;
    top: 12px;
  }
}

.basic-div {
  // width: 933px;
  height: 174px;
  background: #f7f9fc;
  border-radius: 6px;
  // margin-left: 30px;
  // margin-top: 10px;
}

.textInfo {
  font-size: 14px;
  font-family: PingFangSC-Medium;
}

.outer21 {
  background-color: rgba(38, 181, 129, 0.5);
  height: 38px;
  width: 140px;
  float: left;
  cursor: pointer;
  margin-left: 20px;
  margin-top: 20px;

  .word9 {
    width: 126px;
    height: 14px;
    overflow-wrap: break-word;
    color: rgba(255, 255, 255, 1);
    font-size: 14px;
    text-align: center;
    white-space: nowrap;
    line-height: 14px;
    // display: block;
    margin: 20px 0 0 33px;
    position: relative;
    top: 12px;
  }
}

.changeLine {
  clear: both;
  height: 40px;
}

.icon {
  height: 16px;
  width: 22px;
}

.asset-little-title {
  width: 64px;
  height: 16px;
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #424c5c;
  line-height: 16px;
  margin-left: 10px;
}

.country-span {
  width: 200px;
}

// .left {
//   float: left;
//   // width: 10px;
//   height: auto;
// }

.right {
  // float: left;
  width: 100%;
  margin-left: 30px;
}

.pic2 {
  height: 180px;
  width: 2px;
  position: absolute;
  top: 335px;
  left: 80px;
}

.cardBody-top {
  background-image: url("../../../assets/imgs/bg_CarbonAssessment.png");
  width: 100%;
  height: 500px;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  padding-left: 30px;
  padding-right: 30px;
  // height: 100%;
}

.pic3 {
  height: 210px;
  width: 2px;
  position: absolute;
  top: 550px;
  left: 80px;
}

.projectInfo {
  height: 100%;
  width: 100%;
  background: #f7f9fc;
  border-radius: 6px;
  border: 0px solid;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
  line-height: 21px;
  text-align: justify;
}

.table-item {
  height: 50px;
  border-bottom: 1px solid #e3e7eb;
}

.table-item-left {
  width: 160px;
  height: 50px;
  line-height: 50px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #242b35;
  text-align: left;
  padding-left: 20px;
  // border: 1px solid #e3e7eb;
  border-bottom: 1px solid #e3e7eb;
  background: #f7f9fc;
  float: left;
}

.table-item-right {
  float: left;
  // line-height: 50px;
  // font-size: 14px;
  // font-family: PingFangSC-Regular, PingFang SC;
  // font-weight: 400;
  // color: #242b35;
  // text-align: center;
  width: 80%;
  height: 50px;
}

.inputText {
  height: 50px;
  width: 100%;
  border: 0;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  padding-left: 20px;
  padding-right: 20px;
  color: #242b35;
}

.table-row2 {
  float: left;
  display: flex;
  flex-direction: row;
  // width: 80%;
  flex-grow: 1;
  height: 50px;
}

.card {
  width: 220px;
  height: 94px;
  background: rgba(38, 181, 129, 0.06);
  border-radius: 4px;
  display: inline-block;
  padding: 15px 0px 15px 20px;
}

.card-top {
  width: 100%;
  display: inline-block;
  height: 18px;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 600;
  color: #242b35;
  line-height: 18px;
}

.scanCode {
  width: 140px;
  height: 140px;
  display: inline-block;
}

.scanImg {
  background-image: url("../../../assets/imgs/qrcode.png");
  width: 140px;
  height: 140px;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  display: inline-block;
}

.card-bottom {
  width: 100%;
  display: inline-block;
  height: 20px;
  font-size: 20px;
  margin-top: 15px;
  font-family: Barlow-Medium, Barlow;
  font-weight: 500;
  color: #24a776;
  line-height: 20px;
}

.table-item1 {
  height: 50px;
  display: flex;
  flex-direction: row;
  width: 100%;
  border-bottom: 1px solid #e3e7eb;
}

.assessIcon {
  height: 35px;
  width: 54px;
  position: relative;
  top: 8px;
}

.scanTip {
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
  margin: 0 auto;
  display: inline-block;
  position: relative;
  top: -37px;
  width: 20px;
}



.little-card-title {
  font-size: 17px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #24a776;
}

/* 按钮样式 */
</style>
