<template>
<div class="pageContainer">
<div>
  <img class="logo" :src="loginLogo" />
</div>
<div class="loginContiner">
  <div>
    <img class="loginLeftPic" :src="loginLeftPic" />
  </div>
  <div class="loginInputContainer" :class="[fullWidth > 768 ? 'containerSamll' : 'containerBig']">
    <div class="index_from page-account-container">
      <div class="page-account-top">
        <!-- <div class="page-account-top-logo">
        <img :src="loginLogo" alt="logo">
      </div> -->
        <img :src="loginCompanyIcon" />
      </div>
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on"
               label-position="left" @keyup.enter="handleLogin">
        <div class="login-input">
          <span class="txt1">用户名</span>
          <img class="label1" referrerpolicy="no-referrer" src="@/assets/imgs/label1.jpg" />
          <input class="user-input" ref="account" v-model="loginForm.account" name="username" type="text"
                 placeholder="请输入用户名" autocomplete="on" />
        </div>

        <div class="login-input">
          <span class="txt1">密&nbsp;&nbsp;&nbsp;码</span>
          <img class="label1" referrerpolicy="no-referrer" src="@/assets/imgs/label1.jpg" />
          <input class="pwd-input" :key="passwordType" ref="pwd" v-model="loginForm.pwd" :type="passwordType"
                 placeholder="请输入密码" name="pwd" auto-complete="on" />
          <span class="show-pwd" @click="showPwd">
                <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
              </span>
        </div>

        <div class="section6">
          <el-checkbox label="记住我" @change="handleChecked" v-model="loginForm.rememberMe"></el-checkbox>
        </div>

        <!-- <el-form-item prop="code" class="captcha">
        <div class="captcha">
          <el-input
            ref="username"
            v-model="loginForm.code"
            style="width: 218px;"
            prefix-icon="el-icon-message"
            placeholder="验证码"
            name="username"
            type="text"
            tabindex="3"
            autocomplete="on"
          />
          <div class="imgs" style="margin-left:10px" @click="getCaptcha()"><img src="captchatImg"/></div>
        </div>
      </el-form-item> -->

        <div class="acea-row">
          <el-button :loading="loading" class="login" style="
                  width: 100%;
                  margin-bottom: 30px;
                  backgroud: #2ec28b;
                  height: 50px;

                " @click.native.prevent="handleLogin">登录
          </el-button>
        </div>
        <div class="acea-row" style="display: flex; justify-content: space-between">
          <div style="display: flex">
            <a class="acss">还没有账号？</a>
            <el-link type="success" class="login-text" href="/reg">去注册</el-link>
          </div>
          <el-link type="info" href="/forgetPassWord">忘记密码</el-link>
        </div>
      </el-form>
    </div>
  </div>
</div>
</div>
</template>

<script>
import { validUsername } from "@/utils/validate";
import { getLoginPicApi, captchaApi, codeCheckApi, login } from "@/api/user";
import { getStoreStaff } from "@/libs/public";
import { getWXCodeByUrl, loginByWxCode } from "@/libs/wechat";
import { getWechatConfig } from "@/api/wxApi";
import { getToken, removeToken, setToken } from "@/utils/auth";
import Cookies from "js-cookie";
export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error("Please enter the correct user name"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6 || value.length > 12) {
        callback(new Error("密码位数为6-12位"));
      } else {
        callback();
      }
    };
    return {
      loginLogo: require("@/assets/imgs/login_logo.png"),
      captchatImg: require("@/assets/imgs/no.png"),
      loginLeftPic: require("@/assets/imgs/login_left_pic.png"),
      loginCompanyIcon: require("@/assets/imgs/icon_login_company.png"),
      swiperList: [],
      fullWidth: document.body.clientWidth,
      swiperOption: {
        pagination: {
          el: ".pagination",
        },
        autoplay: {
          enabled: true,
          disableOnInteraction: false,
          delay: 3000,
        },
      },
      loginForm: {
        account: "", // admin
        pwd: "",
        key: "",
        code: "",
        wxCode: "",
        rememberMe: false,
      },
      loginRules: {
        account: [{ required: true, trigger: "blur" }], // validator: validateUsername
        pwd: [{ required: true, trigger: "blur", validator: validatePassword }],
        code: [
          { required: true, message: "请输入正确的验证码", trigger: "blur" },
        ],
      },
      passwordType: "password",
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {},
    };
  },
  watch: {
    fullWidth(val) {
      // 为了避免频繁触发resize函数导致页面卡顿，使用定时器
      if (!this.timer) {
        // 一旦监听到的screenWidth值改变，就将其重新赋给data里的screenWidth
        this.screenWidth = val;
        this.timer = true;
        const that = this;
        setTimeout(function () {
          // 打印screenWidth变化的值
          that.timer = false;
        }, 400);
      }
    },
    $route: {
      handler: function (route) {
        const query = route.query;
        if (query) {
          this.redirect = query.redirect;
          this.otherQuery = this.getOtherQuery(query);
        }
      },
      immediate: true,
    },
  },
  created() {
    const _this = this;
    document.onkeydown = function (e) {
      if (_this.$route.path.indexOf("login") !== -1) {
        const key = window.event.keyCode;
        if (key === 13) {
          _this.handleLogin();
        }
      }
    };
    window.addEventListener("resize", this.handleResize);
  },
  mounted() {
    // this.getInfo()
    if (this.loginForm.accountName === "") {
      this.$refs.accountName.focus();
    } else if (this.loginForm.password === "") {
      this.$refs.password.focus();
    }
    // this.getCaptcha()
    // this.agentWeiXinLogin()
  },
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  beforeDestroy: function () {
    window.removeEventListener("resize", this.handleResize);
  },
  methods: {
    handleChecked() {
      // this.$store.dispatch('user/name', this.loginForm.accountName)
      this.rememberMe = !this.rememberMe;
    },
    clickReg() {
      let routeData = this.$router.resolve({ path: "reg" });
      window.open(routeData.href, "_blank");
    },
    clickforgetPassWord() {
      let routeData = this.$router.resolve({ path: "forgetPassWord" });
      window.open(routeData.href, "_blank");
    },

    agentWeiXinLogin() {
      // 判断是否需要微信公众号登陆
      const _isWechat = this.$wechat.isWeixin();
      if (_isWechat) {
        let code = this.$route.query.code;
        let state = this.$route.query.state;
        let wxAuthPath = location.origin + "/login";
        //  如果没有code 去获取
        if (null == code) {
          getWXCodeByUrl(wxAuthPath, "step1");
        }
        // 如果有state=step1 根据code去登陆
        if (state === "step1") {
          loginByWxCode(code)
            .then((res) => {
              sessionStorage.setItem("token", res.token);
              this.$router.push({
                path: this.redirect || "/",
                query: this.otherQuery,
              });
            })
            .catch((err) => {
              // 如果登陆失败，那么输入账号登陆，重新获取code传递给后端做绑定
              getWXCodeByUrl(wxAuthPath, "step2");
            });
        } else if (state === "step2") {
          this.loginForm.wxCode = code;
        }
      }
    },
    onWechat() {
      let url = this.$route.query.redirect
        ? this.$route.query.redirect
        : "/dashboard";
      this.$wechat.oAuth(url, "login");
    },
    handleResize(event) {
      this.fullWidth = document.body.clientWidth;
    },
    getInfo() {
      // getLoginPicApi().then(res => {
      //   this.swiperList = res.banner
      //   this.loginLogo = res.logo
      //   this.backgroundImages = res.backgroundImage
      //   // Cookies.set('MerInfo', JSON.stringify(data))
      // })
    },
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.pwd.focus()
      })
    },
    handleLogin() {
      const code = this.$route.query.code;
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          // this.$store.dispatch("user/setRemember",this.rememberMe)
          this.loading = true
          this.$router.push({ path: '' })
          // if(this.$wechat.isWeixin()){
          //   this.loginForm.wxCode = code
          // }
          // this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
          this.$store.dispatch('user/login', this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
              if (this.loginForm.rememberMe) {
                localStorage.setItem(
                  "login-form",
                  JSON.stringify(this.loginForm)
                );
              }
              // getStoreStaff()
              this.loading = false
            }).catch((err) => {
            this.loading = false
            if (err && err.code == 40006) {//未开户
              this.$router.push({ path: '/loginErrSwiper' })
            } else if (err && err.code == 40005) {//账号禁用
              this.$router.push({ path: '/loginErrSwiper' })
            } else {
              this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
            }
            // if(this.$wechat.isPhone()) this.$dialog.error(err.message);
            // this.getCaptcha()
          })
        } else {
          return false
        }
      })

      // this.$refs.loginForm.validate(valid => {
      //   if (valid) {
      //     // this.loading = true
      //     var data = {
      //       accountName: "admin",
      //       password: "123456"
      //     }
      //     login(data).then(() => {
      //       this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
      //         // getStoreStaff()
      //         // this.loading = false
      //       }).catch((err) => {
      //         alert(err)
      //       // this.loading = false
      //       // if(this.$wechat.isPhone()) this.$dialog.error(err.message);
      //       // this.getCaptcha()
      //     })
      //     }
      // })
    },
    getCaptcha() {
      captchaApi()
        .then((data) => {
          this.captchatImg = data.code;
          this.loginForm.key = data.key;
        })
        .catch(({ message }) => {
          this.$message.error(message);
        });
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== "redirect") {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    },
  },
};
</script>

<style lang="scss">
$screen-md: 768px;
$font-size-base: 14px;
$animation-time: 0.3s;
$animation-time-quick: 0.15s;
$transition-time: 0.2s;
$ease-in-out: ease-in-out;
$subsidiary-color: #808695;

.footer {
  align-items: center;
  justify-content: center;
  width: 50%;
  height: 36px;
  cursor: pointer;
}

.wechat {
  width: 26px;
  height: 26px;

  img {
    width: 100%;
    height: 100%;
  }
}

.page-account {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  overflow: auto;

  &-container {
    flex: 1;
    padding: 32px 0;
    text-align: center;
    width: 384px;
    margin: 0 auto;

    &-result {
      width: 100%;
    }
  }

  &-tabs {
    .ivu-tabs-bar {
      border-bottom: none;
    }

    .ivu-tabs-nav-scroll {
      text-align: center;
    }

    .ivu-tabs-nav {
      display: inline-block;
      float: none;
    }
  }

  &-top {
    padding: 32px 0;

    &-logo {
      img {
        max-height: 75px;
      }
    }

    &-desc {

      color: $subsidiary-color;
    }
  }

  &-auto-login {
    margin-bottom: 24px;
    text-align: left;

    a {
      float: right;
    }
  }

  &-other {
    margin: 24px 0;
    text-align: left;

    span {

    }

    img {
      width: 24px;
      margin-left: 16px;
      cursor: pointer;
      vertical-align: middle;
      opacity: 0.7;
      transition: all $transition-time $ease-in-out;

      &:hover {
        opacity: 1;
      }
    }
  }

  .ivu-poptip,
  .ivu-poptip-rel {
    display: block;
  }

  &-register {
    float: right;

    &-tip {
      text-align: left;

      &-title {

      }

      &-desc {
        white-space: initial;

        margin-top: 6px;
      }
    }
  }

  &-to-login {
    text-align: center;
    margin-top: 16px;
  }

  &-header {
    text-align: right;
    position: fixed;
    top: 16px;
    right: 24px;
  }
}

.labelPic {
  position: absolute;
  right: 0;
}

@media (min-width: $screen-md) {
  .page-account {
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
  }

  .page-account-container {
    padding: 32px 0 24px 0;
    position: relative;
  }
}

.page-account {
  display: flex;
}

.page-account .code {
  display: flex;
  align-items: center;
  justify-content: center;
}

.page-account .code .pictrue {
  height: 40px;
}

.swiperPross {
  border-radius: 6px 0px 0px 6px;
  overflow: hidden;
}

.swiperPross,
.swiperPic,
.swiperPic img {
  width: 286px;
  height: 100%;
}

.swiperPic img {
  width: 100%;
  height: 100%;
}

// .container {
//   height: 340px !important;
//   padding: 0 !important;
//   /*overflow: hidden;*/
//   border-radius: 6px;
//   z-index: 1;
//   display: flex;
// }

.containerSamll {
  /*width: 56% !important;*/
  background: #fff !important;
}

.containerBig {
  width: auto !important;
  background: #f7f7f7 !important;
}

.index_from {
  padding: 0 40px 32px 40px;
  height: 400px;
  box-sizing: border-box;
}

.page-account-top {
  padding: 20px 0 !important;
  box-sizing: border-box !important;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.page-account-container {
  border-radius: 0px 6px 6px 0px;
}

.btn {
  background: linear-gradient(90deg,
    rgba(25, 180, 241, 1) 0%,
    rgba(14, 115, 232, 1) 100%) !important;
}

/deep/.el-input__inner {
  display: flex;
  align-self: center;
  background: transparent !important;
  margin-right: 5px;
  border: #00ee76;
}
</style>

<style lang="scss">
.captcha {
  display: flex;
  align-items: flex-start;
}

$bg: #2d3a4b;
$dark_gray: #889aa4;
$dark_green: #00ee76;
$light_gray: #eee;

.imgs {
  img {
    height: 36px;
  }
}

.login-form {
  position: relative;
  max-width: 100%;
  margin: 0 auto;
  overflow: hidden;
}

.tips {

  color: #fff;
  margin-bottom: 10px;

  span {
    &:first-of-type {
      margin-right: 16px;
    }
  }
}

.svg-container {
  padding: 6px 5px 6px 15px;
  color: $dark_gray;
  vertical-align: middle;
  width: 30px;
  display: inline-block;
}

.thirdparty-button {
  position: absolute;
  right: 0;
  bottom: 6px;
}

.login-title {

  color: $dark_green;
}

// .top-bar {
//   background: #889aa4;
//   height: 30px;
// }
.top-nav {
  background: #fff;
  height: 50px;
}

.pageContainer {
  background-color: rgba(237, 249, 252, 1);
  display: flex;
  flex-direction: column;
}

.logo {
  background: transparent;
  width: 134px;
  height: 36px;
  float: left;
  margin-left: 40px;
  margin-top: 30px;
}

.loginContiner {
  width: 100%;
  display: flex;
  flex-flow: row;
}

.loginLeftPic {
  flex-grow: 1;
  height: 705px;
}

.loginInputContainer {
  margin-top: 84px;
  margin-right: 90px;
  height: 419px !important;
  padding: 0 !important;
  /*overflow: hidden;*/
  border-radius: 6px;
  z-index: 1;
  display: flex;
}

.section3 {
  height: 1px;
  background: #2ec28b 50%;
}

.info1 {
  background-image: linear-gradient(135deg,
    rgba(46, 194, 139, 1) 0,
    rgba(0, 101, 255, 1) 100%);
  width: 290px;
  height: 30px;
  overflow-wrap: break-word;
  color: rgba(39, 167, 119, 1);


  text-align: center;
  white-space: nowrap;

  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  display: block;
}

.word1 {
  background-image: linear-gradient(135deg,
    rgba(46, 194, 139, 1) 0,
    rgba(0, 101, 255, 1) 100%);
  // width: 273px;
  height: 14px;
  overflow-wrap: break-word;
  color: rgba(39, 167, 119, 1);

  letter-spacing: 1.399999976158142px;
  text-transform: uppercase;

  text-align: center;
  white-space: nowrap;

  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  display: block;
  margin: 7px 0 0 0px;
}

.login-input {
  margin-top: 32px;
  height: 50px;
  background: #e5f9f0;
  display: flex;
  flex-direction: row;
}

.txt1 {
  width: 45px;
  height: 15px;
  overflow-wrap: break-word;
  color: rgba(23, 43, 77, 1);


  text-align: left;
  white-space: nowrap;

  display: flex;
  align-self: center;
  margin-left: 16px;
}

.label1 {
  width: 1px;
  height: 16px;
  display: flex;
  align-self: center;
  margin-left: 9px;
  margin-right: 9px;
}

.user-input {
  padding: 5px;
  display: flex;
  align-self: center;
  margin-right: 25px;


  flex-grow: 1;
  background-color: transparent;
  border: 0;
  outline: none;
}

.pwd-input {
  padding: 5px;
  display: flex;
  align-self: center;
  margin-right: 5px;


  flex-grow: 1;
  background-color: transparent;
  border: 0;
  outline: none;
}

// input::-webkit-input-placeholder {
//     /* WebKit browsers */
//     color: transparent;
// }
// input:-moz-placeholder {
//     /* Mozilla Firefox 4 to 18 */
//     color: transparent;
// }
// input::-moz-placeholder {
//     /* Mozilla Firefox 19+ */
//     color: transparent;
// }
// input:-ms-input-placeholder {
//     /* Internet Explorer 10+ */
//     color: transparent;
// }
// input:-webkit-autofill {
//  box-shadow: transparent;
// }
// input:-webkit-autofill:focus {
//  box-shadow:transparent;
// }
// input:-internal-autofill-previewed,
// input:-internal-autofill-selected {
//     -webkit-text-fill-color: #000 !important;
//     transition: background-color 5000s ease-in-out 0s !important;
// }
.show-pwd {
  display: flex;
  align-self: center;
  margin-right: 5px;

  color: $dark_gray;
  cursor: pointer;
  user-select: none;

  /deep/.svg-icon {
    vertical-align: 0.3em;
  }
}

.section6 {
  margin-top: 10px;
  margin-bottom: 10px;
  display: flex;

  .group1 {
    display: flex;
    align-self: center;
    border-radius: 4px;
    width: 16px;
    height: 16px;
    border: 1px solid rgba(179, 186, 197, 1);
  }

  .word5 {
    margin-left: 5px;
    display: flex;
    align-self: center;
    color: rgba(94, 108, 132, 1);

  }
}

.acss {
  color: #5e6c84;


}

.login-text {
  color: rgb(255, 0, 0);


}

.login {
  background-color: #26b581;
  width: 80px;
  height: 32px;
  border-radius: 3px;
  text-align: center;
  margin-top: auto;
  margin-bottom: auto;
  border-radius: 2px;
  cursor: pointer;
  color: white;


  border: 1px solid #26b581;

  &:hover {
    background: #30b08f;
    color: white;
    color: white;


  }
}
</style>
