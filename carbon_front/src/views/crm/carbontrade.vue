<template>
  <div class="divBox">
    <div class="root">
      <div>
        <div id="your-mount-point"></div>
      </div>
    </div>
  </div>
</template>
<script>
import "@/api/feishuSdk.js";
import {getAccoutBaseInfo} from "@/api/tenant";
import Cookies from 'js-cookie'
import {
  getFeishuInformation,
  putFeishuUpdateByNum,
  getFeishuFormInformation
} from "@/api/feishuApi";
import { getAppId } from "@/config/dictHelper";

export default {
  name: "carbontrade",
// 碳交易客户
  data() {
    return {
      baseInfo:{},
      app_id: "cli_a21e705fcc38500c",
      code: "",//测试写死
      openId: "",
      signature: "",
      appId: "",
      timestamp: "",
      nonceStr: "",
      url: "",
      jsApiList: ["DocsComponent"],
      lang: "",
      isShow: false,
      fileUrl: "https://zfx2bso66l.feishu.cn/sheets/shtcnwqyEqtaFuSch4ZsE4E6RMS",
      fileToken: "doccn7M7yrHoUrB3KLDa15RYBNg",
      //  replaceTextList 高量区数据 textList替换数据  后面再字典加
      positionList:[],
      position:"",
      templateNum:'',

    };
  },

  methods: {
     getBaseInfo() {
      let info = JSON.parse(Cookies.get('JavaInfo'))
      getAccoutBaseInfo(parseInt(info.accountId))
        .then((res) => {
          this.baseInfo = res;
        })
        .catch((err) => {});
    },

    sendCode() {
      let redirect_uri =  window.location.protocol + "//" + window.location.host + this.$route.path; // 重定向url
      redirect_uri = encodeURIComponent(redirect_uri);
      let url = `https://open.feishu.cn/open-apis/authen/v1/index?redirect_uri=${redirect_uri}&app_id=${this.app_id}&state=200`;
       window.location.href=url
    },
     getFeishuAppId(){
      let data = getAppId(this.$store);
      let value = "0410000001";
      for (let i = 0; i < data.length; i++) {
        if(data[i].value == value){
          this.app_id = data[i].name;
        }
      }
    },

  },
  mounted() {
    this.getFeishuAppId();
    if(this.$store.getters.sidebar.opened){
      this.$store.dispatch('app/toggleSideBar')
    }
    if(window.localStorage.getItem("is_new_window")){
      window.localStorage.removeItem("is_new_window")
    }
    this.getBaseInfo();
    let code = this.$route.query.code;
    var myComponent;
      if (!code) {
        if(localStorage.getItem("refreshToken")){
          localStorage.removeItem("refreshToken");
        }
       this.sendCode();
      } else {
          let openId, signature, appId, timestamp, nonceStr, url, jsApiList, lang;
          let that = this;
          let datas = {
             "code":code,
             "templateNum":"0420000007",
             "refreshToken":localStorage.getItem("refreshToken"), 
          }
          getFeishuFormInformation(datas).then((res) => {
            this.app_id = res.appId;
            that.fileUrl = res.textUrl; //模板url
            that.fileToken = res.textToken; //模板标识
            openId = res.openId;
            signature = res.signature;
            appId = res.appId;
            timestamp = res.timestamp;
            nonceStr = res.nonceStr;
            url = res.url;
            jsApiList = ["DocsComponent"];
            // jsApiList = [...res.jsApiList];
            lang = res.lang;
            localStorage.setItem("refreshToken",res.refreshToken)

            window.webComponent
            .config({
              openId, // 当前登录用户的open id，要确保与生成 signature 使用的 user_access_token 相对应，使用 app_access_token 时此项不填。
              signature, // 签名
              appId, // 应用 appId
              timestamp, // 时间戳（毫秒）
              nonceStr, // 随机字符串
              url, // 参与签名加密计算的url
              jsApiList, // 指定要使用的组件，请根据对应组件的开发文档填写。如云文档组件，填写['DocsComponent']
              lang, // 指定组件的国际化语言：en-英文、zh-中文、ja-日文
            })
            .then((res) => {
              // 组件动态渲染
             myComponent = window.webComponent.render(
                "DocsComponent",
                {
                  //组件参数
                  src: that.fileUrl,//后端提供
                  minHeight: '700px',
                  width: "100%",
                },
                document.querySelector("#your-mount-point") // 将组件挂在到哪个元素上
              );

            myComponent.config.setFeatureConfig({
              HEADER: {
                enable: true, //  隐藏头部
              },
              MODAL: {
                outerMask: true,
              },
            });
            myComponent.event.onFullScreenModeChange(function(isFullScreen) {
            });

            myComponent.event.onMountSuccess(function() {
             //加载成功
            //  console.log("加载成功");
            });
            myComponent.event.onMountTimeout(function() {
              // 挂载文档超时
              // console.log("挂载文档超时");
            });
            
            // myComponent.unmount()

            });
          }).catch(err=>{});
          //立即更新高亮内容
          // this.updateFeishu();
      }
  },
  created() {
  //  this.templateNum = this.$route.query.templateNum;
  },
};
</script>
<style scoped>
.root {
  padding-left: 30px;
  padding-right: 30px;
  background: #ffffff;
  box-shadow: 0px 2px 8px 0px #eaf0f3;
  border-radius: 8px;
  /* margin: 10px 28px; */
  overflow: auto;
  font-family: PingFangSC-Regular, PingFang SC;
}
.top-title{
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin: 10px 0px;
  /* height: 24px; */

}
.pro-title{
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #24A776;
  line-height: 18px;
}
.feishu-btn{
  background: #26B581;
  border-radius: 3px;
  padding: 9px 12px;
  color: #FFFFFFFF;
  border: none;
}
.feishu-btn:active{
  opacity: 0.8;
}
.span-text > span{
  font-size: 13px;
  padding-left: 4px;
 
  font-weight: 400;
  color: #808EA5;
  line-height: 13px;
}
.ff-ppc{
 font-family: PingFangSC-Regular, PingFang SC;
}
.mag-rl{
  margin: 0px 8px ;
}

</style>
