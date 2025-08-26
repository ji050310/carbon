
<template>
  <div class="divBox">
    <div class="card">
      <div class="titlebar">
        <div class="container">
          <a class="text">供应量</a>
          <a class="subtext">上月({{ monthSupply.statDate }})</a>
        </div>
      </div>
      <div class="el-card-bg" :bordered="false" dis-hover>
        <div class="content-container">
          <div class="content-pannel">
            <div class="content-container-left">
              <img class="flag-icon" src="@/assets/imgs/icon_capital_one.png" />
              <span class="sumAmout">{{ monthSupply.carbonSupply }}</span>
              <span class="unit">{{ assetsIncomeUnit }}</span>
            </div>
          </div>
          <div class="p-diliver" />
          <div class="content-container-right">
            <div class="content-pannel">
              <div class="rate-bar">
                <div class="rate-container">
                  <span class="hint">碳信用：</span>
                  <div class="rate">
                    <i :class="sumincome">{{ monthSupply.carbonCredit }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ carbonCreditUnit }}</i>
                  </div>
                </div>
                <div class="rate-container">
                  <span class="hint">碳配额：</span>
                  <div class="rate">
                    <i :class="sumincome">{{ monthSupply.carbonQuota }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ carbonQuotaUnit }}</i>
                  </div>
                </div>
                <div class="rate-container">
                  <span class="hint">绿&nbsp;&nbsp;&nbsp;证：</span>
                  <div class="rate">
                    <i :class="sumincome">{{ monthSupply.greenScore }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ greenCertUnit }}</i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <el-divider class="diliver"></el-divider>

        <div class="bottom-bar">
          <div class="bottom-content-container">
            <div class="bottom-hint">总供应量：</div>
            <div class="bottom-value">{{
                monthSupply.carbonSupplyTotal
              }}</div>
            <div class="bottom-value">&nbsp;{{ assetsIncomeUnit }}</div>
          </div>
          <div class="empty-div" />
          <div class="bottom-content-container">
            <div class="bottom-hint">碳资产估值：</div>
            <div class="bottom-value">¥{{ monthSupply.carbonValuation }}</div>
          </div>
        </div>
      </div>
    </div>

    <div class="card">
      <div class="titlebar">
        <div class="container">
          <a class="text">开发量</a>
          <a class="subtext">上月({{ monthDevelopment.statDate }})</a>
        </div>
      </div>
      <div class="el-card-bg" :bordered="false" dis-hover>
        <div class="content-container">
          <div class="content-pannel">
            <div class="content-container-left">
              <img class="flag-icon" src="@/assets/imgs/icon_capital_two.png" />
              <span class="sumAmout">{{ monthDevelopment.carbonSupply }}</span>
              <span class="unit">{{ assetsIncomeUnit }}</span>
            </div>
          </div>
          <div class="p-diliver" />
          <div class="content-container-right">
            <div class="content-pannel">
              <div class="rate-bar">
                <div class="rate-container">
                  <span class="hint">碳信用：</span>
                  <div class="rate">
                    <i :class="sumincome">{{
                        monthDevelopment.carbonCredit
                      }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ carbonCreditUnit }}</i>
                  </div>
                </div>
                <div class="rate-container">
                  <span class="hint">碳配额：</span>
                  <div class="rate">
                    <i :class="sumincome">{{ monthDevelopment.carbonQuota }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ carbonQuotaUnit }}</i>
                  </div>
                </div>
                <div class="rate-container">
                  <span class="hint">绿&nbsp;&nbsp;&nbsp;证：</span>
                  <div class="rate">
                    <i :class="sumincome">{{ monthDevelopment.greenScore }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ greenCertUnit }}</i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <el-divider class="diliver"></el-divider>

        <div class="bottom-bar">
          <div class="bottom-content-container">
            <span class="bottom-hint">总供应量：</span>
            <span class="bottom-value">{{
                monthDevelopment.carbonSupplyTotal
              }}</span>
            <span class="bottom-value">&nbsp;{{ assetsIncomeUnit }}</span>
          </div>
          <div class="empty-div" />
          <div class="bottom-content-container">
            <span class="bottom-hint">碳资产估值：</span>
            <span class="bottom-value"
            >¥ {{ monthDevelopment.carbonValuation }}</span
            >
          </div>
        </div>
      </div>
    </div>

    <div class="card">
      <div class="titlebar">
        <div class="container">
          <a class="text">销售量</a>
          <a class="subtext">上月({{ monthSales.statDate }})</a>
        </div>
      </div>
      <div class="el-card-bg" :bordered="false" dis-hover>
        <div class="content-container">
          <div class="content-pannel">
            <div class="content-container-left">
              <img
                class="flag-icon"
                src="@/assets/imgs/icon_capital_three.png"
              />
              <span class="sumAmout">{{ monthSales.carbonSupply }}</span>
              <span class="unit">{{ assetsIncomeUnit }}</span>
            </div>
          </div>
          <div class="p-diliver" />
          <div class="content-container-right">
            <div class="content-pannel">
              <div class="rate-bar">
                <div class="rate-container">
                  <span class="hint">碳信用：</span>
                  <div class="rate">
                    <i :class="sumincome">{{ monthSales.carbonCredit }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ carbonCreditUnit }}</i>
                  </div>
                </div>
                <div class="rate-container">
                  <span class="hint">碳配额：</span>
                  <div class="rate">
                    <i :class="sumincome">{{ monthSales.carbonQuota }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ carbonQuotaUnit }}</i>
                  </div>
                </div>
                <div class="rate-container">
                  <span class="hint">绿&nbsp;&nbsp;&nbsp;证：</span>
                  <div class="rate">
                    <i :class="sumincome">{{ monthSales.greenScore }}</i>
                    <i :class="sumAmoutUnit">&nbsp;{{ greenCertUnit }}</i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <el-divider class="diliver"></el-divider>
        <div class="bottom-bar">
          <div class="bottom-content-container">
            <span class="bottom-hint">总供应量：</span>
            <span class="bottom-value">{{ monthSales.carbonSupplyTotal }}</span>
            <span class="bottom-value">&nbsp;{{ assetsIncomeUnit }}</span>
          </div>
          <div class="empty-div" />
          <div class="bottom-content-container">
            <span class="bottom-hint">碳资产估值：</span>
            <span class="bottom-value">¥ {{ monthSales.carbonValuation }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { greenUnit, creditUnit, qoutaUnit, cncUnit } from "@/config/ComConfig";
export default {
  name: "companyPackage",
  data() {
    return {
      lastMonth: "2月",
      assetsIncomeUnit: cncUnit,
      carbonCreditUnit: creditUnit,
      carbonQuotaUnit: qoutaUnit,
      greenCertUnit: greenUnit,
    };
  },
  props: {
    monthSupply: {},
    monthDevelopment: {},
    monthSales: {},
  },
  mounted() {},
  methods: {
    sumAmoutUnit() {
      return "sumAmoutUnit";
    },
    sumincome() {
      return "sumincome";
    },
  },
  created() {},
};
</script>
<style lang="scss" scoped>
.divBox {
  display: flex;
  flex-direction: row;
  display: grid;
  grid: auto / auto auto auto;
  grid-gap: 20px;
}
.card {
  display: flex;
  flex-direction: column;
}
.el-card-bg {
  background: white;
  padding-left: 16px;
  padding-right: 16px;
  padding-top: 16px;
  padding-bottom: 10px;
  border-radius: 6px;
  // background-image: url('../../../assets/imgs/bg_card.png');
  // background-size:cover;
}
.el-divider--horizontal {
  margin: 15px 0 10px 0;
}
.titlebar {
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
  .container {
    display: flex;
    flex-direction: row;
    // flex-grow: 1;
  }
  .text {
    height: 18px;


    font-weight: 500;
    color: #080b0d;

    cursor: default;
  }
  .subtext {
    margin-top: 4px;
    margin-left: 6px;
    height: 14px;


    font-weight: 500;
    color: #5e6c84;

    cursor:default;
  }
}
.content-container-right {
  display: flex;
  flex-direction: row;
  vertical-align: middle;
  // flex-flow: 2;
}
.content-container-left {
  display: flex;
  flex-direction: row;
  margin: auto;
  // flex-flow: 3;
}
.content-container {
  display: flex;
  flex-direction: row;
  .content-pannel {
    display: flex;
    flex-direction: column;
    vertical-align: middle;
    .flag-icon {
      vertical-align: middle;
    }
    .sumAmout {
      vertical-align: middle;
      margin-left: 6px;


      font-weight: 500;
      color: #24a776;
    }
    .unit {
      margin-left: 2px;
      // margin-right: 24px;
      margin-top: 15px;
      vertical-align: bottom;


      font-weight: 400;
      color: #24a776;

    }
    .rate-bar {
      display: flex;
      flex-direction: column;
    }
    .rate-container {
      display: flex;
      flex-direction: row;
      margin-top: 9px;
      // margin-right: 22px;
      .hint {


        font-weight: 400;
        color: #5e6c84;
      }
      .rate {
        display: flex;
        flex-direction: row;
        margin-left: 6px;
        min-width: 28px;
        height: 16px;


        font-weight: 500;

      }
      .sumincome {


        font-weight: 500;
        color: #424c5c;
      }
      .sumAmoutUnit {


        font-weight: 500;
        color: #424c5c;
      }
    }
  }
  .diliver {
    width: 100%;
    height: 1px;
    border: 1px solid rgba(238, 240, 243, 0.95);
  }
  .p-diliver {
    margin-left: 10px;
    margin-right: 10px;
    width: 1px;
    height: 70px;
    border: 1px #eef0f3;
    border-style: dotted solid double dashed;
  }
}
.bottom-bar {
  // width: 100%;
  display: flex;
  flex-direction: row;
  .bottom-content-container {
    display: flex;
    flex-direction: row;
    // flex-grow: 1;
  }
  .bottom-hint {
    // height: 14px;


    font-weight: 400;
    color: #424c5c;

  }
  .bottom-value {
    // margin-left: 6px;
    // height: 17px;


    font-weight: 500;
    color: #27a777;

    //
  }
}
.empty-div {
  flex-grow: 1;
}
</style>
