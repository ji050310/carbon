<template>
  <div class="wscn-http404-container">
    <div class="wscn-http404">
      <div class="pic-404">
        <img class="pic-404__parent" src="@/assets/404_images/404-01.png" alt="404">
      </div>
      <div><p class="tip-title">功能即将推出，敬请期待</p></div>
    </div>
  </div>
</template>

<script>
/* 碳盘查 */
export default {
  name: 'accountWallet',
  data() {
    return {
    }
  },
  computed: {
  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>
.wscn-http404-container{
  transform: translate(-50%,-50%);
  position: absolute;
  top: 50%;
  left: 50%;
}
.wscn-http404 {
  display: flex;
  flex-direction: column;
  align-items:center;
  .tip-title{
    width: 176px;
    height: 16px;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #808EA5;
    line-height: 16px;
    margin: 40px 55px;
  }
}
</style>
