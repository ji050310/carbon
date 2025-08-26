<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="cardBody">
          <div id="header">
            <div class="header_left">
              <div class="header_left_title">
                <div>
                  <img class="center" style="width:54px;height:35px;" src="@/assets/imgs/icon_tan_e_01.png" />
                </div>
                <div class="title_text">碳e估</div>
              </div>
              <div class="header_text">
                <span
                  >碳e估是碳信使推出的碳资产线上评估神器。基于现有CCER方法学和已开发碳资产项目数据，利用大数据及人工智能等核心技术及算法，可在线上科学、快速完成碳资产价值评估和分析服务。</span
                >
              </div>
              <div class="btn_box">
                <div class="btn_g">
                  <span class="btn_g_text">立即评估</span>
                </div>
                <div class="btn_w">
                  <span class="btn_w_text">申请试用</span>
                </div>
              </div>
              <div class="qr_title">
                <div class="qr_item">
                  <div>
                    <img
                      class="content_img"
                      style="width: 80px; heigth: 80px; padding-top: 4px"
                      src="@/assets/imgs/qrcode.png"
                      alt=""
                    />
                  </div>
                  <div><p class="titel_tb">扫码关注</p></div>
                </div>
                <div style="margin-left: 25px; width: 108px; heigth: 80px">
                  <p class="qr_titel_text">
                    如需了解更多业务内容请扫二维码与我们联系！
                  </p>
                </div>
              </div>
            </div>
            <div class="header_right"></div>
          </div>
        </div>
        <div id="bottom">
          <div class="bg_w">
            <div class="bottom_item">
              <div class="item_title">
                <img class="icon center" src="@/assets/imgs/icon_tan_e_02.png" />
                <span>资产评估</span>
              </div>
              <div class="item_content">
                <span
                  >您是香拥有碳资产，哪些可以被开发为碳资产有多少价值？碳信使为客户提供科学便捷的评估工具，简单快速完成碳资产价值评估。</span
                >
              </div>
            </div>
          </div>
          <div class="bg_w">
            <div class="bottom_item">
              <div class="item_title">
                <img class="icon center" src="@/assets/imgs/icon_tan_e_03.png" />
                <span>资产开发</span>
              </div>
              <div class="item_content">
                <span>
                  对于已评估的碳资产，碳信使为客户提供从立项到签发的全套碳资产开发管理系统，降低开发成本，提升开发效率，更快更好的完成碳资产开发。
                </span>
              </div>
            </div>
          </div>
          <div class="bg_w">
            <div class="bottom_item">
              <div class="item_title">
                <img
                  class="icon center"
                  src="@/assets/imgs/icon_tan_e_04.png"
                />
                <span>变现引流</span>
              </div>
              <div class="item_content">
                <span
                  >对开发的碳资产怎么快速变现，碳信使提供交易前中后的线上线下服务，并为有需求客户提供用户引流的增值服务，为您有效管理资产保驾护航。</span
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CarbonAssessment",
  data() {
    return {};
  },
  mounted() {},
  methods: {},
};
</script>

<style lang="scss" scoped>
.root {
  background: #f2f5f7;
  font-family: PingFangSC-Regular, PingFang SC;
}
.ar_ss {
  width: 15px;
  height: 80px;
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
  line-height: 20px;
}
.divBox {
  margin: 20px;
  // background: #ffffff;
  box-shadow: 0px 2px 8px 0px #eaf0f3;
  border-radius: 8px;
}

.container {
  margin: 10px 0px 20px 0px;
  display: flex;
  flex-direction: row;
}

.cardBody {
  background: #ffffff;
  padding: 30px 30px 30px 30px;
  // margin: 10px 0px 20px 0px;
}
.title_text {
  flex: 1;
  font-size: 25px;
  font-family: SourceHanSansCN-Bold, SourceHanSansCN;
  font-weight: bold;
  color: #24a776;
  line-height: 38px;
}
.header_left_title {
  display: flex;
  align-items: center;
}
#header {
  // url("../../../assets/imgs/writeOffBg.jpg") no-repeat;
  background: url("../../assets/imgs/bg_CarbonAssessment.png") no-repeat;
  background-position: right bottom;
  background-size: 685px 375px;
  display: flex;
}
.qr_title {
  display: flex;
  justify-content: start;
  margin: 36px 0px 40px 0px;
}
.qr_titel_text {
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
  line-height: 29px;
}
.qr_item {
  display: flex;
}
.content_img {
  width: 80px;
  height: 80px;
}
.header_left {
  width: 43%;
}
.header_right {
  flex: 1;
}
.header_text {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #424c5c;
  line-height: 29px;
  margin: 28px 0px 25px 0px;
}
.btn_box {
  display: flex;
  justify-content: start;
}
.btn_g {
  background: #26b581;
  border-radius: 4px;
  padding: 11px 22px;
  cursor:pointer;
}
.btn_g:active{
  opacity: 0.8;
}
.btn_w {
  border-radius: 4px;
  padding: 11px 22px;
  border: 1px solid #26b581;
  margin-left: 15px;
  cursor:pointer;
}
.btn_w:active{
    opacity: 0.8;
}
.btn_g_text {
  font-size: 15px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  line-height: 16px;
  color: #ffffff;
}
.btn_w_text {
  font-size: 15px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  line-height: 16px;
  color: #26b581ff;
}

.el-divider {
  margin-top: 30px;
}
.center {
  margin: auto;
}
.icon {
  height: 30px;
  width: 30px;
}
.titel_tb {
  writing-mode: tb;
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
  line-height: 20px;
  letter-spacing: 6px;
  padding-top: 8px;
}
#bottom {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  .bg_w {
    width: 32%;
    .bottom_item {
      background: #ffffff;
      background: url("../../assets/imgs/bg_CarbonAssessment_item.png")
        no-repeat;
      background-position: right bottom;
      padding: 0px 30px;

      .item_title {
        display: flex;
        font-size: 17px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: #24a776;
        line-height: 24px;
        vertical-align: middle;
        align-items: center;
		margin: 30px 0 24px 0;
        span {
          flex: 1;
          margin-left: 10px;
        }
      }
      .item_content {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #5e6c84;
        line-height: 29px;
        // margin: 24px 0px 29px 0px;
        margin: 24px 0px 0px 0px;
		padding-bottom: 29px;
      }
    }
  }
}
.bg_w {
  background: #ffffff;
}

@media screen and (min-width: 1360px) {
  .basic-divx {
    width: 95%;
    min-height: 200px;
    background: #f7f9fc;
    border-radius: 6px;
    margin-left: 30px;
    margin-top: 30px;
  }
}

@media screen and (max-width: 1360px) {
  .basic-divx {
    width: 95%;
    min-height: 300px;
    background: #f7f9fc;
    border-radius: 6px;
    margin-left: 30px;
    margin-top: 30px;
  }
}
</style>