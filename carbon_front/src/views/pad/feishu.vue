<template>
  <div class="divBox">
    <div class="root">
      <div class="top-title">
        <div class="pro-title">
           <span class="ff-ppc">{{ fileInfo.projectName }}</span>
        </div>
        <div class="span-text">
          <!-- <span>{{ baseInfo.accountName }}</span>
          <span>2021-10-10 10:00:00</span> <span>创建</span> 
          <span>{{ baseInfo.accountName }}</span>
          <span>2021-10-10 10:00:00</span> <span>更新</span> -->
    
          <span>{{ fileInfo.createAccountName }}</span>
          <span>{{ fileInfo.createdTime }}</span> <span>创建</span> 
          <span>{{ fileInfo.updatedAccountName }}</span>
          <span>{{ fileInfo.updatedTime}}</span> <span>更新</span>
            <button class="feishu-btn ff-ppc mag-rl"   @click="showXcarbon">
            xCarbon
          </button>
          <button class="feishu-btn ff-ppc mag-rl">确认终稿</button>
        </div>
      </div>
      <div>
        <div id="your-mount-point"></div>
      </div>
    </div>
    <xcarbon
      :dialogTableVisible="changeXcarbon"
      @changeXcarbonShow="changeShow"
    />
  </div>
</template>
<script>
import "../../api/feishuSdk.js";
import { getAccoutBaseInfo } from "@/api/tenant";
import Cookies from "js-cookie";
import {
  getFeishuInformation,
  putFeishuUpdateByNum,
  getFeishuFileDate
} from "@/api/feishuApi";
import { getAppId } from "@/config/dictHelper";
import xcarbon from "@/views/xcarbon/index.vue";
import feishu from "./feishu";
export default {
  name: "edit",
  components: { feishu, xcarbon },
  data() {
    return {
      baseInfo:{
        accountName:'',
      },
      app_id: "",
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
      projectName:'碳信使',
      changeXcarbon: false,

      fileUrl: "https://abcwjpcjl2.feishu.cn/docs/doccn7M7yrHoUrB3KLDa15RYBNg",
      fileToken: "doccn7M7yrHoUrB3KLDa15RYBNg",
      //  replaceTextList 高量区数据 textList替换数据  后面再字典加
      positionList:[],
      position:"",
      templateNum:'',
      fileInfo:{
        projectName:"",
        createdTime:'2020-02-10',
        updatedTime:'2020-02-16',
        createAccountName:'asa',
        updatedAccountName:'aa',
      }

    };
  },

  methods: {
    getFeishuAppId(){
      let data = getAppId(this.$store);
      let value = "0410000002";
      for (let i = 0; i < data.length; i++) {
        if(data[i].value == value){
          this.app_id = data[i].name;
        }
      }
    },
    showXcarbon(){
      this.changeXcarbon = true;
    },
     changeShow(res) {
      this.changeXcarbon = res;
    },
    getBaseInfo() {
      let info = JSON.parse(Cookies.get('JavaInfo'))
      getAccoutBaseInfo(parseInt(info.accountId)).then((res) => {
        console.log("res",res);
          this.baseInfo.accountName = res.accountName;
        }).catch((err) => {});
    },
    getFeishuFileInfo(){
      if(localStorage.getItem("templateNum")){
        let templateNum = localStorage.getItem("templateNum");
          getFeishuFileDate(templateNum).then((res) =>{
            console.log("res",res);
            this.fileInfo.createdTime = res.createdTime
            this.fileInfo.updatedTime = res.updatedTime
            this.fileInfo.createAccountName = res.createAccountName,
            this.fileInfo.updatedAccountName = res.updatedAccountName;
          }).catch((err)=>{
            console.log("err",err);
          })
        }
    },

    sendCode() {
      let redirect_uri =  window.location.protocol + "//" + window.location.host + this.$route.path; // 重定向url
      redirect_uri = encodeURIComponent(redirect_uri);
     
      let url = `https://open.feishu.cn/open-apis/authen/v1/index?redirect_uri=${redirect_uri}&app_id=${this.app_id}&state=200`;
      // let url = "https://open.feishu.cn/open-apis/authen/v1/index?redirect_uri=http%3A%2F%2Fsaas.xcarbon.cc%2Flogin%3Fredirect%3D%252Fdashboard&app_id=cli_a2e906a1c238d00c&state=200https://open.feishu.cn/open-apis/authen/v1/index?redirect_uri=http%3A%2F%2Fsaas.xcarbon.cc%2Flogin%3Fredirect%3D%252Fdashboard&app_id=cli_a2e906a1c238d00c&state=200";
      window.location.href=url// 测试先屏蔽
      // window.open(url)
    },
    updateShow() {
      // this.isShow = !this.isShow;
      this.updateFeishu();
    },
   
    updateFeishu() {
      let datas = localStorage.getItem("data");//this.$route.query 其它页面传过来的参数
      const data = new URLSearchParams();
      data.append("fileToken", this.fileToken);// 文档的唯一标识
      data.append("replaceText", localStorage.getItem("replaceText"));
      data.append("templateNum", localStorage.getItem("templateNum"));
      data.append("position", localStorage.getItem("position"));
      data.append("text", localStorage.getItem("text"));
      data.append("datas", datas);
     
      putFeishuUpdateByNum(data).then((res) => {
        // updateShow();
      }).catch(err => {

      });
    },

  },
  mounted() {
    this.getFeishuAppId();
    if(this.$store.getters.sidebar.opened){
      this.$store.dispatch('app/toggleSideBar')
    }
    this.getBaseInfo();
    this.getFeishuFileInfo();
    let code = this.$route.query.code
    var myComponent;
      if (!code) {
        //跳转弹出客户授权
        if(localStorage.getItem("refreshToken")){
          localStorage.removeItem("refreshToken")
        }
        localStorage.setItem("data",JSON.stringify(this.$route.query));//
        localStorage.setItem("replaceText",this.$route.query.projectName);
        localStorage.setItem("text",this.$route.query.text);
        this.position = this.$route.query.position ? this.$route.query.position : "DischargeLine";
        this.templateNum = this.$route.query.templateNum ? this.$route.query.templateNum : (Math.floor(Math.random() * (3 - 1 + 1)) + 1)
        this.projectName = this.$route.query.projectName;
        localStorage.setItem("position",this.position);
        localStorage.setItem("templateNum",this.templateNum);
        this.sendCode();
      } else {
          this.projectName = localStorage.getItem("replaceText");
          let openId, signature, appId, timestamp, nonceStr, url, jsApiList, lang,refreshToken;
          let that = this;
          let datas = {
             "code":code,
             "templateNum":localStorage.getItem("templateNum"), 
             "refreshToken":localStorage.getItem("refreshToken"), 
          }
          getFeishuInformation(datas).then((res) => {
            that.fileUrl = res.textUrl; //模板url
            that.fileToken = res.textToken; //模板标识
            openId = res.openId;
            signature = res.signature;
            appId = res.appId;
            timestamp = res.timestamp;
            nonceStr = res.nonceStr;
            url = res.url;
            jsApiList = ["DocsComponent"];
            lang = res.lang;
            refreshToken = refreshToken

            localStorage.setItem("refreshToken",res.refreshToken)

            that.updateFeishu();

            window.webComponent.config({
              openId, // 当前登录用户的open id，要确保与生成 signature 使用的 user_access_token 相对应，使用 app_access_token 时此项不填。
              signature, // 签名
              appId, // 应用 appId
              timestamp, // 时间戳（毫秒）
              nonceStr, // 随机字符串
              url, // 参与签名加密计算的url
              jsApiList, // 指定要使用的组件，请根据对应组件的开发文档填写。如云文档组件，填写['DocsComponent']
              lang, // 指定组件的国际化语言：en-英文、zh-中文、ja-日文
              refreshToken,
            })
            .then((res) => {
              // 组件动态渲染
            myComponent = window.webComponent.render(
                "DocsComponent",
                {
                  //组件参数
                  src: that.fileUrl,//后端提供
                  minHeight: "500px",
                  width: "100%",
                },
                document.querySelector("#your-mount-point") // 将组件挂在到哪个元素上
              );
            myComponent.config.setFeatureConfig({
              COMMENT: {
                open: false,
                partial:false,
              },
            });

            });
          }).catch(err=>{});
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
