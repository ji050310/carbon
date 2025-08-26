<template>
  <div class="login-container">
    <div>
      <img class="logo" :src="loginLogo" />
    </div>
    <div class="loginContiner">
      <div class="loginInputContainer" :class="[ fullWidth > 768 ? 'containerSamll':'containerBig']">
        <div class="login-form">
          <div v-show="regShow">
            <el-form :model="regForm" ref="regForm" label-position="left">
              <div class="login-input" style="margin-top: 0px;">
                <span class="txt1">用&nbsp;户&nbsp;名</span>
                <img class="label1" referrerpolicy="no-referrer" src="@/assets/imgs/label1.jpg" />
                <input class="user-input" ref="accountName" v-model="regForm.accountName" name="accountName" type="text"
                       placeholder="请输入用户名" />
              </div>


              <div class="login-input">
                <span class="txt1">手&nbsp;机&nbsp;号</span>
                <img class="label1" referrerpolicy="no-referrer" src="@/assets/imgs/label1.jpg" />
                <input class="user-input" ref="mobile" v-model="regForm.mobile" name="mobile" type="number"
                       placeholder="请输入11位手机号" @mouseover="getIsAccountName" />
              </div>


              <div class="login-input">
                <span class="txt1">验&nbsp;证&nbsp;码</span>
                <img class="label1" referrerpolicy="no-referrer" src="@/assets/imgs/label1.jpg" />
                <input class="user-input" ref="verificationCode" v-model="regForm.verificationCode"
                       name="verificationCode" type="text" placeholder="请输入验证码" />
                <button v-show="sendAuthCode" style="cursor:pointer" class="codeBtn"
                        @click.prevent="handlecode">获取验证码</button>
                <button v-show="!sendAuthCode" class="codeBtn" disabled>{{auth_time}}s</button>
              </div>


              <div class="login-input">
                <span class="txt1">设置密码</span>
                <img class="label1" referrerpolicy="no-referrer" src="@/assets/imgs/label1.jpg" />
                <input class="pwd-input" :key="passwordType" :type="passwordType" ref="password" clearable
                       v-model="regForm.password" placeholder="请输入6-16位密码" name="password" />
              </div>


              <div class="login-input">
                <span class="txt1">确认密码</span>
                <img class="label1" referrerpolicy="no-referrer" src="@/assets/imgs/label1.jpg" />
                <input class="pwd-input" :key="passwordType" ref="confirmPassword" :type="passwordType" clearable
                       v-model="regForm.confirmPassword" placeholder="请输入确认密码" name="confirmPassword" />
              </div>


              <div class="btn_reg">
                <el-button :class="setButtonClass()" type="success"
                           style="width:100%;margin-top:30px;backgroud:#2EC28B;height:50px;font-size: 15px;" :loading="loading"
                           @click.prevent="handleNext">注册</el-button>
              </div>
              <!-- <div>
            <el-link @click="clickLogin">已有账号</el-link>
          </div> -->
            </el-form>
          </div>
          <div class="hasaccount">
            <a class="acss">已有账户，</a>
            <el-link type="success" href="/" @click="handleLogin">去登陆 ></el-link>
          </div>
        </div>
      </div>
    </div>


  </div>
</template>


<script>
// import { checkPhone, checkPassword, checkEmail } from './../../filters/index'
import { register, login, logout, regCode ,getAuthCenterAuthVerify} from '@/api/user'
import { MessageBox } from 'element-ui'
import md5 from 'js-md5'
// import shajs from 'sha.js'


export default {
  components: {},
  name: 'reg',
  data() {
    // let verificationCode = (rule, value, callback) => {
    //   if (!value) {
    //     return callback(new Error('验证码不能为空'))
    //   } else if (value.length > 6) {
    //     return callback(new Error('请输入六位验证码'))
    //   } else {
    //     callback()
    //   }
    // }
    // const companyName = (rule, value, callback) => {
    //   if (!value) {
    //     return callback(new Error('公司名不能为空'))
    //   } else {
    //     callback()
    //   }
    // }
    // const userName = (rule, value, callback) => {
    //   if (!value) {
    //     return callback(new Error('名称不能为空'))
    //   } else {
    //     callback()
    //   }
    // }
    // const confirmPassword = (rule, value, callback) => {
    //   if (!value) {
    //     callback(new Error('密码不能为空'))
    //   } else if (value !== this.regForm.password) {
    //     callback(new Error('两次输入的密码不一致'))
    //   } else {
    //     callback()
    //   }
    // }


    return {
      regForm: {
        mobile: '',
        verificationCode: '',
        password: '',
        confirmPassword: '',
        company: '',
        accountName: '',
        email: '',
        verifyAccountName:''
      },
      loginRules: {
        // mobile: [{ required: true, trigger: 'blur', validator: checkPhone }],
        // verificationCode: [
        //   { required: true, trigger: 'blur', validator: verificationCode }
        // ],
        // password: [
        //   { required: true, trigger: 'blur', validator: checkPassword }
        // ],
        // confirmPassword: [
        //   { required: true, trigger: 'blur', validator: confirmPassword }
        // ]
      },
      regFormInfo: {
        // email: [{ required: true, trigger: 'blur', validator: checkEmail }],
        // company: [{ required: true, trigger: 'blur', validator: companyName }],
        // username: [{ required: true, trigger: 'blur', validator: userName }]
      },


      passwordType: 'password',
      loading: false,
      showDialog: false,
      regShow: true,
      nextShow: false,
      sendAuthCode: true,
      auth_time: 60,
      loginLogo: require('@/assets/imgs/login_logo.png'),
    }
  },
  methods: {

    getAuthCode: function() {
      this.sendAuthCode = false
      this.auth_time = 60
      var timetimer = setInterval(() => {
        this.auth_time--
        if (this.auth_time <= 0) {
          this.sendAuthCode = true
          clearInterval(timetimer)
        }
      }, 1000)
    },
    // showPwd() {
    //   if (this.passwordType === 'password') {
    //     this.passwordType = ''
    //   } else {
    //     this.passwordType = 'password'
    //   }
    // },
    // 验证码
    verifyPhoneNumberFormat(str){
      var myreg = /^1[3-9]\d{9}$/;
      return myreg.test(str);
    },
    getIsAccountName(){
      // 验证用户名的
      if(!this.regForm.accountName || (this.regForm.accountName == this.verifyAccountName)){
        return
      }
      let param = this.regForm.accountName;
      getAuthCenterAuthVerify(param).then((res)=>{
        if(res && res.code == 200){
          this.$message.error("用户名已被注册，请重新输入用户名")
        }else{
          this.verifyAccountName = this.regForm.accountName
        }
      })
    },
    handlecode() {


      if (this.regForm.mobile !== '') {
        let is_phone = this.verifyPhoneNumberFormat(this.regForm.mobile);
        if(!is_phone){
          return this.$message.error('手机格式错误！')
        }
        this.getAuthCode()
        regCode(this.regForm.mobile).then((res, err) => {
          debugger
          if (err) {
            this.$message.error(error)
          } else {
            this.$message.success(res.msg)
          }
        }).catch(err => {
          this.$message.error(err.msg)
        })
      } else {
        return this.$message.error('请输入手机号')
      }
    },
    // 下一步
    handleNext() {


      this.$refs.regForm.validate(valid => {
        if (valid) {
          if (this.regForm.password !== this.regForm.confirmPassword) {
            this.$message.error("两次输入的密码不一致，请重新输入！")
            return
          }
          var data = {
            "accountName": this.regForm.accountName,
            "code":this.regForm.verificationCode,
            "password": this.regForm.password,
            "confirmPassword": this.regForm.confirmPassword,
            "phone": this.regForm.mobile
          }
          register(data)
            .then((data, err) => {
              if (err) {
                this.$message.error(error)
                return false
              } else {
                MessageBox.alert('', '提示', {
                  message: '恭喜您注册成功，请直接登录。',
                  confirmButtonText: '确定',
                }).then(() => {
                  this.$router.push({ path: '/login' })
                })
                // this.regShow = false
                // this.nextShow = true
              }
            })
            .catch(() => {})
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    setButtonClass(){
      if(this.regForm.accountName && this.regForm.confirmPassword && this.regForm.password && this.regForm.verificationCode && this.regForm.mobile){
        return 'register-text'
      }else{
        return 'no-register-text'
      }
    },
    fullWidth(){},


    handleLogin(callback) {
      this.$router.push({ path: '/login' })
    }
  },
  created() {
    // window.addEventListener('hashchange', this.afterQRScan)
  },
  destroyed() {
    // window.removeEventListener('hashchange', this.afterQRScan)
  }
}
</script>


<style rel="stylesheet/scss" lang="scss">
$bg: #2d3a4b;
$light_gray: #68b0fe;


/* reset element-ui css */
.login-container {
  display: flex;
  flex-direction: column;
  background-color: rgba(237, 249, 252, 1);


  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;


    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;


      &:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
  }


  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(255, 255, 255, 0.7);
    border-radius: 5px;
    color: #454545;
  }
}


.el-form-item__error {
  color: #fff;
}
</style>


<style rel="stylesheet/scss" lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;


.bg {
  height: 100%;
  width: 100%;
  background-color: rgba(237, 249, 252, 1);
}


.login-container {
  position: fixed;
  height: 100%;
  width: 100%;


  .login-form {
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    width: 520px;
    min-height: 400px;
    padding: 35px 35px 15px 35px;
    margin: -260px auto 0;
    background: #fff;
  }


  .tips {
    font-size: 14px;
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


    &_login {
      font-size: 20px;
    }
  }


  .title-container {
    position: relative;


    .title {
      font-size: 26px;
      font-weight: 400;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }


    .set-language {
      color: #fff;
      position: absolute;
      top: 5px;
      right: 0px;
    }
  }


  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }


  .thirdparty-button {
    position: absolute;
    right: 35px;
    bottom: 28px;
  }
}


.loginBtn {
  background: #407ffe;
  height: 64px;
  line-height: 32px;
  font-size: 24px;
}


.el-form-item {
  margin-bottom: 20px;
}


.regInfo {
  text-align: center;
  color: #fff;
  font-size: 16px;
}


.codeBtn {
  background: #E5F9F0;
  color: #0065FF;
  border: none;
  border-width: 0px;
  border-color: transparent;
  align-items: center;
  font-size: 15px;
  margin-right: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 15px;
}


.logo {
  background: transparent;
  width: 134px;
  height: 36px;
  float: left;
  margin-left: 40px;
  margin-top: 30px
}


.login-input {
  margin-top: 32px;
  height: 50px;
  background: #E5F9F0;
  display: flex;
  flex-direction: row;
  font-family: PingFangSC-Medium, PingFang SC;


}


.txt1 {
  width: 55px;
  height: 15px;
  overflow-wrap: break-word;
  text-align: left;
  white-space: nowrap;
  line-height: 15px;
  display: flex;
  align-self: center;
  margin-left: 16px;
  width: 64px;
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #172B4D;
  letter-spacing: 1px;
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
  font-size: 15px;
  line-height: 15px;
  flex-grow: 1;
  background-color: transparent;
  border: 0;
  outline: none;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #172B4D;
}


.verificationCodeContainer {
  // height: 100%;
  display: flex;
}


.pwd-input {
  padding: 5px;
  display: flex;
  align-self: center;
  margin-right: 5px;
  font-size: 15px;
  line-height: 15px;
  flex-grow: 1;
  background-color: transparent;
  border: 0;
  outline: none;
  font-family: PingFangSC-Regular, PingFang SC;
}


.loginContiner {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  display: flex;
  flex-flow: row;
}


.loginInputContainer {
  margin: auto;
  padding-top: 32px;
  height: 519px !important;
  padding: 0 !important;
  /*overflow: hidden;*/
  border-radius: 6px;
  // z-index: 1;
  // display: flex;
}


.hasaccount {
  width: 150px;
  margin-top: 15px;
  display: flex;
  flex-direction: row;
  margin: auto;
  padding-top: 20px;
}


.hasaccountbtn {
  display: flex;
  color: #27A777;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
}


.acss {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #5E6C84;
  line-height: 14px;
}


.register-text {
  font-size: 15px;
  background: #27A777;
  color: #fff;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #FFFFFF;
  line-height: 18px;
  transition: background-color .3s ease-in;
}


.no-register-text {
  background: #76DFB7;
  color: #fff;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #FFFFFF;
  line-height: 18px;
  width: 100%;
  margin-top: 30px;
  height: 50px;
  border: none;
  pointer-events: none;
  transition: background-color .3s ease-in;
}
</style>