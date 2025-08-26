<template>
  <div class="divBox">
    <div>
      <a class="nav-title">常用功能</a>
    </div>
    <div class="dashboard-console-grid">
      <router-link :to="{ path: '/carbon/projectCreate' }">
        <div class="grid-item-div">
          <div class="icon-text-container">
            <img class="my-icon" src="@/assets/imgs/icon_usage_add.png" />
            <a class="my-text">项目立项</a>
          </div>
          <a class="hint-text">查看项目立项，提供碳信用项目立项报表模板</a>
        </div>
      </router-link>
      <router-link :to="{ path: '/carbon/methodology' }">
        <div class="grid-item-div">
          <div class="icon-text-container">
            <img class="my-icon" src="@/assets/imgs/icon_usage_project.png" />
            <a class="my-text">项目方法学</a>
          </div>
          <a class="hint-text">涵盖国内外各类碳信用开发方法学，最全方法</a>
        </div>
      </router-link>

      <router-link :to="{ path: '/trade/quotation' }">
        <div class="grid-item-div">
          <div class="icon-text-container">
            <img class="my-icon" src="@/assets/imgs/icon_usage_quote.png" />
            <a class="my-text">交易行情</a>
          </div>
          <a class="hint-text">国内碳交易行情纵览，随时掌握交易动态 </a>
        </div>
      </router-link>
      <router-link :to="{ path: '/trade/account/exchange' }">
        <div class="grid-item-div">
          <div class="icon-text-container">
            <img class="my-icon" src="@/assets/imgs/icon_usage_open.png" />
            <a class="my-text">交易所开户</a>
          </div>
          <a class="hint-text">国内外权威碳交易所列目，支持各大交易所开户</a>
        </div>
      </router-link>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      grid: {
        xl: 3,
        lg: 6,
        md: 6,
        sm: 8,
        xs: 8
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.ivu-mb {
  margin-bottom: 10px;

  .el-card__body {
    padding: 0px;
  }
}

.divBox {
  margin-top: 40px;
  padding: 0 20px !important;
}

.dashboard-console-grid {
  margin-top: 6px;
  display: grid;
  grid: auto/ auto auto auto auto;
  // grid-gap: 10px;
  grid-gap: 10px;

  // text-align: center;
  .ivu-card-body {
    padding: 0;
  }

  // i {
  //
  // }
  // a {
  //   display: block;
  //   color: inherit;
  // }
  // p {
  //   margin-top: 8px;
  // }
}

/deep/.el-card__body {
  padding: 0px;
  background-image: url('../../../assets/imgs/bg_commom_usage.png');
  background-repeat: repeat;
  background-size: cover;
  border: 0px;
  border-radius: 0px;
}

.grid-item-div {
  padding: 34px;
  // width: 295px;
  display: flex;
  flex-direction: column;
  background-image: url('../../../assets/imgs/bg_commom_usage.png');
  // background-repeat: repeat;
  background-size: cover;
  // width: 295px;
  // flex-grow: 1;
  height: 150px;
}

.icon-text-container {
  // margin-top: 20px;
  // padding: 10px;
  display: flex;
  flex-direction: row;
}

.my-icon {
  width: 30px;
  height: 30px;
}

.my-text {
  margin-left: 5px;
  margin-top: 8px;


  font-weight: 500;
  color: #424C5C;
}

.hint-text {
  margin-top: 8px;
  margin-left: 4px;


  font-weight: 400;
  color: #5E6C84;

}

.nav-title {
  margin-top: 20px;


  font-weight: 500;
  color: #080B0D;
  cursor: default;
}
</style>
