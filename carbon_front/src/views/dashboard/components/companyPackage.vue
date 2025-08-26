
<template>
  <div class="divBox">
    <div class="card">
      <div class="titlebar">
        <div class="container">
          <a class="text">资产收入</a>
          <a class="subtext">上月({{ assetsIncome.statDate }})</a>
        </div>
        <div class="container">
          <a class="text">资金收入</a>
          <a class="subtext">上月({{ fundIncome.statDate }})</a>
        </div>
      </div>
      <div class="el-card-bg" :bordered="false" dis-hover>
        <div class="content-container">
          <div class="content-pannel">
            <div>
              <span class="sumAmout">{{ assetsIncome.totalIncome }}</span>
              <span class="unit">{{ assetsIncomeUnit }}</span>
            </div>
            <div class="rate-bar">
              <div class="rate-container">
                <span class="hint">环比</span>
                <div class="rate">
                  <i
                    :class="
                      Number(assetsIncome.monthOnMonthRatio) >= 0
                        ? 'positive'
                        : 'negetive'
                    "
                  >{{ assetsIncome.monthOnMonthRatio }}%</i
                  >
                  <img
                    class="arrow-icon"
                    v-if="Number(assetsIncome.monthOnMonthRatio) >= 0"
                    src="@/assets/imgs/icon_rate_up.png"
                  />
                  <img
                    class="arrow-icon"
                    v-if="Number(assetsIncome.monthOnMonthRatio) < 0"
                    src="@/assets/imgs/icon_rate_down.png"
                  />
                </div>
              </div>
              <div class="rate-container">
                <span class="hint">同比</span>
                <div class="rate">
                  <i
                    :class="
                      Number(assetsIncome.yearOnYearRatio) >= 0
                        ? 'positive'
                        : 'negetive'
                    "
                  >{{ assetsIncome.yearOnYearRatio }}%</i
                  >
                  <img
                    class="arrow-icon"
                    v-if="Number(assetsIncome.yearOnYearRatio) >= 0"
                    src="@/assets/imgs/icon_rate_up.png"
                  />
                  <img
                    class="arrow-icon"
                    v-if="Number(assetsIncome.yearOnYearRatio) < 0"
                    src="@/assets/imgs/icon_rate_down.png"
                  />
                </div>
              </div>
              <div class="rate-container">
                <span class="hint">总收入</span>
                <div class="rate">
                  <i :class="sumincome">¥ {{ assetsIncome.totalIncome }}</i>
                </div>
              </div>
            </div>
          </div>

          <div style="display: flex; flex-direction: row; width: 100%">
            <div class="p-diliver" />
            <div style="margin-left: 30px" class="content-pannel">
              <div>
                <span class="sumAmout">{{ fundIncome.monthIncome }}</span>
                <span class="unit">¥ {{ capitalIncomeUnit }}</span>
              </div>
              <div class="rate-bar">
                <div class="rate-container">
                  <span class="hint">环比</span>
                  <div class="rate">
                    <i
                      :class="
                        Number(fundIncome.monthOnMonthRatio) >= 0
                          ? 'positive'
                          : 'down'
                      "
                    >{{ fundIncome.monthOnMonthRatio }}%</i
                    >
                    <img
                      class="arrow-icon"
                      v-if="Number(fundIncome.monthOnMonthRatio) >= 0"
                      src="@/assets/imgs/icon_rate_up.png"
                    />
                    <img
                      class="arrow-icon"
                      v-if="Number(fundIncome.monthOnMonthRatio) < 0"
                      src="@/assets/imgs/icon_rate_down.png"
                    />
                  </div>
                </div>
                <div class="rate-container">
                  <span class="hint">同比</span>
                  <div class="rate">
                    <i
                      :class="
                        Number(fundIncome.yearOnYearRatio) >= 0
                          ? 'positive'
                          : 'down'
                      "
                    >{{ fundIncome.yearOnYearRatio }}%</i
                    >
                    <img
                      class="arrow-icon"
                      v-if="Number(fundIncome.yearOnYearRatio) >= 0"
                      src="@/assets/imgs/icon_rate_up.png"
                    />
                    <img
                      class="arrow-icon"
                      v-if="Number(fundIncome.yearOnYearRatio) < 0"
                      src="@/assets/imgs/icon_rate_down.png"
                    />
                  </div>
                </div>
                <div class="rate-container">
                  <span class="hint">总收入</span>
                  <div class="rate">
                    <i :class="sumincome">¥ {{ fundIncome.totalIncome }}</i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <el-divider class="diliver"></el-divider>

        <div class="bottom-bar">
          <div class="bottom-content-container">
            <span class="bottom-hint">碳信用：</span>
            <span class="bottom-value">{{ carbonCredit }}</span>
            <span class="bottom-value">{{ carbonCreditUnit }}</span>
          </div>
          <div class="bottom-content-container">
            <span class="bottom-hint">碳配额：</span>
            <span class="bottom-value">{{ carbonQuota }}</span>
            <span class="bottom-value">{{ carbonQuotaUnit }}</span>
          </div>
          <!-- <div class="bottom-content-container">
            <span class="bottom-hint">绿证：</span>
            <span class="bottom-value"> {{ greenScore }}</span>
            <span class="bottom-value"> {{ greenCertUnit }}</span>
          </div> -->
        </div>
      </div>
    </div>

    <div
      @click="goUserInfo"
      style="margin-left: 16px; cursor: pointer"
      class="card"
    >
      <div class="titlebar">
        <div class="container">
          <a class="text" style="cursor: pointer">账户</a>
        </div>
      </div>
      <div class="el-card-bg" :bordered="false" dis-hover>
        <div class="top-container">
          <div class="content-container">
            <div class="content-pannel">
              <div style="display:flex;align-items:center">
                <img class="account-logo" :src="accountVo.avatar" />
                <span class="account-name">{{ accountVo.accountName }}</span>
                <span v-if="accountVo.roleNames" class="icon-qy-status">
                  <img
                    class="icon-ec-status-logo"
                    src="@/assets/icon/icon-qy-time.png"
                  />
                  <span class="qy-text">{{accountVo.roleNames[0]}}</span>
                </span>

                <!-- 试用版 -->
                <span v-if="accountVo.productVersion=='0400000001'" class="icon-ec-status">
                  <img
                    class="icon-ec-status-logo"
                    src="@/assets/icon/blue-time.png"
                  />
                  <span class="ec-text">{{ accountVo.productVersionName }}</span>
                </span>
                <!-- 基础版 -->
                <span v-if="accountVo.productVersion=='0400000002'" class="icon-ec-status">
                  <img
                    class="icon-ec-status-logo"
                    src="@/assets/icon/icon-qy-me.png"
                  />
                  <span class="ec-text">{{ accountVo.productVersionName }}</span>
                </span>
                <!-- 通用版 -->
                <span v-else-if="accountVo.productVersion=='0400000003'" class="icon-ec-status">
                  <img
                    class="icon-ec-status-logo"
                    src="@/assets/icon/icon-qy-major.png"
                  />
                  <span class="ec-text">{{ accountVo.productVersionName }}</span>
                </span>
                <!-- 定制版 -->
                <span v-else-if="accountVo.productVersion=='0400000004'" class="icon-ec-status">
                  <img
                    class="icon-ec-status-logo"
                    src="@/assets/icon/icon-qy-f-vip.png"
                  />
                  <span class="ec-text">{{ accountVo.productVersionName }}</span>
                </span>
              </div>
              <span class="account-company">{{
                  accountVo.enterpriseName
                }}</span>
            </div>
          </div>
          <img
            class="icon_account_flag"
            src="@/assets/imgs/icon_account_flag.png"
          />
        </div>

        <el-divider class="diliver"></el-divider>

        <div class="bottom-bar">
          <div class="bottom-content-container">
            <span class="bottom-hint">碳中和比例：</span>
            <span class="bottom-value">{{ accountVo.carbonNeutralRatio || '--'}}</span>
          </div>
          <div class="bottom-content-container">
            <span class="bottom-hint">ESG评分：</span>
            <span class="bottom-value">{{ accountVo.esgScore || '--' }}</span>
          </div>
          <div class="bottom-content-container">
            <span class="bottom-hint">账户有效期：</span>
            <span v-if="accountVo.expired" class="account-validate-text">
              已过期</span>
            <span v-else class="account-validate-text">
              {{ accountVo.accountValidity | formatData }}</span>
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
      capitalIncomeUnit: "",
    };
  },
  props: {
    accountVo: {},
    assetsIncome: {},
    fundIncome: {},
    carbonCredit: 0,
    carbonQuota: 0,
    greenScore: 0,
  },
  mounted() {},
  methods: {
    sumincome() {
      return "sumincome";
    },
    goUserInfo() {
      this.$router.push({
        path: "/account/info",
      });
    },
  },
  created() {},
};
</script>
<style lang="scss" scoped>
.divBox {
  margin-top: 5px;
  display: flex;
  flex-direction: row;
  padding: 10px 26px 0px 20px;
}

.card {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.el-card-bg {
  padding: 15px;
  background: white;
  border-radius: 6px;
  background-image: url("../../../assets/imgs/bg_card.png");
  background-repeat: no-repeat;
  // background-size:cover;
  background-position: top right;
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
    flex-grow: 1;
  }

  .text {
    height: 18px;


    font-weight: 500;
    color: #080b0d;

    cursor:default;
  }

  .subtext {
    margin-top: 4px;
    margin-left: 6px;
    height: 14px;


    font-weight: 500;
    color: #5e6c84;

  }
}

.content-container {
  display: flex;
  flex-direction: row;
  flex-grow: 1;

  .content-pannel {
    display: flex;
    flex-direction: column;
    width: 100%;

    .sumAmout {
      width: 100%;
      height: 32px;


      font-weight: 500;
      color: #27a777;

      background: linear-gradient(135deg, #2ec28b 0%, #0065ff 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }

    .unit {
      height: 16px;


      font-weight: 500;
      color: #27a777;

    }

    .rate-bar {
      display: flex;
      flex-direction: row;
    }

    .rate-container {
      display: flex;
      flex-direction: column;
      margin-top: 16px;
      margin-right: 22px;

      .hint {
        height: 15px;


        font-weight: 400;
        color: #5e6c84;

      }

      .rate {
        display: flex;
        flex-direction: row;
        margin-top: 6px;
        min-width: 28px;
        height: 16px;


        font-weight: 500;


        .positive {
          color: #fa5252;
        }

        .negetive {
          color: #15aabf;
        }

        .arrow-icon {
          width: 14px;
          height: 14px;
        }
      }

      .sumincome {
        height: 16px;


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
    margin-top: 10px;
    width: 1px;
    height: 68px;
    border: 1px #eef0f3;
    border-style: dotted solid double dashed;
  }
}

.bottom-bar {
  width: 100%;
  display: flex;
  flex-direction: row;

  .bottom-content-container {
    display: flex;
    flex-direction: row;
    flex-grow: 1;

    .bottom-hint {


      font-weight: 400;
      color: #242b35;
    }

    .bottom-value {
      margin-left: 6px;


      font-weight: 500;
      color: #27a777;
    }
  }
}

.account-container {
  display: flex;
  flex-direction: row;
}

.account-logo {
  width: 40px;
  height: 40px;
  border-radius: 20px;
  vertical-align: middle;
}

.account-name {
  margin-left: 16px;


  font-weight: 400;
  color: #242b35;

  vertical-align: middle;
}

.account-unative-logo {
  margin-left: 16px;
  height: 20px;
  vertical-align: middle;
}

.account-company-active-logo {
  margin-left: 16px;
  height: 20px;
  vertical-align: middle;
}

.account-company {
  margin-top: 22px;
  // margin-bottom:px;
  height: 21px;


  font-weight: 500;
  color: #424c5c;

}

.top-container {
  display: flex;
  flex-direction: row;
}

.icon_account_flag {
  width: 84px;
  height: 84px;
  vertical-align: middle;
}

.account-validate-text {
  height: 14px;


  font-weight: 400;
  color: #5e6c84;

  position: relative;
  top: 3px;
}
.icon-qy-status {
  height: 22px;
  margin-left: 16px;
  background-image: url("../../../assets/icon/bg-icon-01.png");
  background-repeat: no-repeat;
  background-size:100% 100%;
  background-position: center;
  padding: 4px 10px 4px 10px;
}
.qy-text{


  font-weight: 400;
  color: #FD7E14;

  vertical-align:middle;
}
.icon-ec-status{
  height: 22px;
  margin-left: 16px;
  background-image: url("../../../assets/icon/bg-icon-02.png");
  background-repeat: no-repeat;
  background-size:100% 100%;
  background-position: center;
  padding: 4px 10px 4px 10px;
}
.icon-ec-status-logo{
  width: 14px;
  height: 14px;
  vertical-align:middle;
}
.ec-text{


  font-weight: 400;
  color: #4C6EF5;

  vertical-align:middle;
}
</style>
