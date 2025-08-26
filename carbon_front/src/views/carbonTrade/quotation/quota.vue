<template>
  <div class="newDivBox" style='position: relative;'>
    <el-tabs v-model="activeName" :before-leave="moreState">
      <el-tab-pane class="lable1" label="采购行情" name="buy" :key="'buy'">
        <buy-quote-vue @changeTab="gotoSell" />
      </el-tab-pane>
      <el-tab-pane class="lable2" label="供应行情" name="sell" :key="'sell'">
        <sell-quote-vue @changeTab="gotoBuy" />
      </el-tab-pane>
    </el-tabs>
    <button class="normal-white-blue-text-btn" style='position: absolute;right:20px;top:30px;'
            @click="clickBuy">我想买</button>
    <buy-assets-vue :dialogFormVisible="buyAssetsDlg" @changeBuyAssetsDialogFormVisible="changeDialogFormVisible" />
  </div>
</template>
<script>
import buyQuoteVue from "./buy/buyQuote.vue"
import sellQuoteVue from "./sell/sellQuote.vue"
import buyAssetsVue from "./buyAssets.vue"
import orderResultVue from "./orderResult.vue"
export default {
  components: {
    buyQuoteVue,
    sellQuoteVue,
    buyAssetsVue,
    orderResultVue
  },
  data() {
    return {
      //默认第一个选项卡
      activeName: "buy",
      curTab: '',
      buyAssetsDlg: false,
      tenantInfo: {
        institutionName: '',
        name: '',
        phone: ''
      },
    }
  },
  methods: {
    moreState(tab, event) {
      if (tab == 'more') {
        this.curTab = ''
        return false;
      } else {
        this.curTab = tab
        this.activeName = tab
      }
    },
    clickBuy() {
      this.buyAssetsDlg = true
    },
    changeDialogFormVisible(res) {
      if (res) {
        this.buyAssetsDlg = true
      } else {
        this.buyAssetsDlg = false
      }

      // if (res) {
      //   this.$router.push('/trade/offer')
      // }
      // this.$emit('changeTab', "sell");
    },
    gotoSell() {
      this.activeName = "sell"
    },
    gotoBuy() {
      this.activeName = "buy"
    }
  }
}
</script>
<!--display类型不匹配改成inline-->
<style  lang="scss" scoped>
.lable1 {
  margin-bottom: 10px;
  color: rgb(0, 0, 0);
}

.more-btn {
  display: inline;
  text-align: right;
}

/deep/ #tab-more {
  width: calc(100% - 10px);
  text-align: right;
}

/deep/ .el-tabs__nav {
  width: calc(100% - 200px);
}

.el-tabs {
  flex: 1;

  /deep/ .el-tabs__item.is-active {
    color: #26a872;
  }

  /deep/ .el-tabs__item:hover {
    color: #26a872;
  }

  /deep/ .el-tabs__active-bar.is-top {
    background: #26a872;
  }

  /deep/ .el-tabs__item {


    font-weight: 400;
    color: #5E6C84;
  }
}
</style>
