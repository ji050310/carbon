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
  name: "projectSchedule",

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
      fileUrl: "https://zfx2bso66l.feishu.cn/sheets/shtcnoHokr9Y571RpgiWODMHaHd",
      fileToken: "ur-27Uqh6i414Ia2kfBGkJ6WlggiBy05hK3pi00hhA00fjB",
      //  replaceTextList 高量区数据 textList替换数据  后面再字典加
      positionList:[],
      position:"",
      templateNum:'',

    };
  },

  methods: {


    sendCode() {
      let url = this.fileUrl;
      window.location.href=url
      //window.open(url);
    },


  },
  mounted() {

    this.sendCode();

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


  font-weight: 500;
  color: #24A776;

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

  padding-left: 4px;

  font-weight: 400;
  color: #808EA5;

}
.ff-ppc{

}
.mag-rl{
  margin: 0px 8px ;
}

</style>
