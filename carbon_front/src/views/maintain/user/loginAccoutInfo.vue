<template>
     <div class="card-container">
           <div class="card-div">
                <img class="icon" src="@/assets/imgs/icon_user.png"/>
                <span class="secon-line-hint">账号</span>
                <input class="input-css" v-model="baseInfo.accountName" disabled/>
                <span class="second-line-final-hint">系统自动生成，可用于登录</span>
                 <div class="div-holder"/>
           </div>
           <div class="card-div">
                <img class="icon" src="@/assets/imgs/icon_mobile.png"/>
                <span class="secon-line-hint">手机号</span>
                <input class="input-css" v-model="baseInfo.phone" disabled/>
                <span class="second-line-final-hint">绑定手机号，可用于登录、重置密码或其他安</span>
                 <div class="div-holder"/>
                <button class="normal-white-btn btn-modify-head">修改</button>
           </div>
           <div class="card-div">
                <img class="icon" src="@/assets/imgs/icon_email.png"/>
                <span class="secon-line-hint">邮箱</span>
                <input class="input-css" v-model="baseInfo.email" disabled/>
                <span class="second-line-final-hint">绑定邮箱，可用于登录、重置密码或其他安全</span>
                 <div class="div-holder"/>
                <button class="light-green-btn btn-modify-psw">绑定</button>
           </div>
        </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        baseInfo: {
          accout: "ssss",
          mobile:"135****4161",
          email:"akshdj@xcarbon.cc",
       },
      }

    },
    methods: {

    },
    props: {
      baseInfo: {}
    }
  }
</script>

<style scoped>
.root{
    width: 100%;
    display: flex;
    flex-direction: row;
}
.card-container{
    display: flex;
    flex-direction: column;
}
.card-div{
    margin-top: 20px;
    display: flex;
    flex-direction: row;
}
.icon {
    width: 18px;
    height: 18px;
    margin: auto;
}
.secon-line-hint{
  text-align: center;
  align-self: center;
  width: 64px;
  font-size: 16px;
  margin-right: 30px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3A4063;
}
.second-line-text{
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808EA5;
}
.second-line-final-hint{
    margin: auto;
    margin-left: 100px;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #5E6C84;
}
.btn-modify-psw {
  margin: auto;
}
.btn-modify-head{
  margin: auto;
}
.div-holder {
  flex-grow: 1;
}
</style>