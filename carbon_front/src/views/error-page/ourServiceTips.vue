<template>
  <div class="services-container">
    <div class="wscn-http404">
      <div>
        <div>
          <p>
            <img
              class="icon"
              src="@/assets/imgs/icon_services_01.png"
              align="absmiddle"
              alt=""
            />
            <span class="title">我们的服务</span>
          </p>
          <p>
            <span class="title__content"
              >碳咨询、碳资产开发、碳资产管理、碳交易管理、碳普惠、碳盘查、碳资讯…</span
            >
          </p>
        </div>
        <div style="margin-top: 56px"></div>
        <div>
          <p>
            <img
              class="icon"
              src="@/assets/imgs/icon_services_01.png"
              align="absmiddle"
              alt=""
            />
            <span class="title">服务优势</span>
          </p>
          <p class="title__content">
            1、丰富的专业因子库，内置多种行业碳减排开发方法学
          </p>
          <p class="title__content">
            2、云端协同与大数据驱动，自动生成碳减排项目开发文档
          </p>
          <p class="title__content">
            3、数字化供应链体系，无缝连接全球碳交易市场
          </p>
        </div>
        <div style="margin-top: 60px"></div>
         <div class="btn__content tip__btn"  @click="goLogout">
             <template>
              <span>立即体验</span>
            </template>
             <!-- <router-link :to=" { path: '/login' } " >
                <span>立即体验</span>
            </router-link> -->
          </div>
      </div>
      <div class="bullshit"></div>
    </div>
  </div>
</template>

<script>
import { logout } from "@/api/user";
import Cookies from 'js-cookie';
import {getAccoutBaseInfo} from "@/api/tenant";
export default {
  name: "ourServiceTips",

  data() {
    return {};
  },
  computed: {
    message() {
      return "你不能进入这个页面...";
    },
  },
  methods: {
    goLogout() {
      if(!Cookies.get("JavaInfo")){
        return this.$router.push(`/login?redirect=${this.$route.fullPath}`);
      }
      let info = JSON.parse(Cookies.get("JavaInfo"));
      getAccoutBaseInfo(parseInt(info.accountId)).then((res) => {
          if (res && res.accountStatus != "0390000001") {
            this.$store.dispatch("user/logout");
            this.$router.push(`/login?redirect=${this.$route.fullPath}`);
          }else{
            this.$router.push(`/login?redirect=${this.$route.fullPath}`);
          }
        })
        .catch((err) => {});
    },
  },
};
</script>

<style lang="scss" scoped>
.services-container {
  transform: translate(-50%, -50%);
  position: absolute;
  top: 40%;
  left: 50%;
  background: #edf9fc;
  height: 720px;
  width: 90%;
  background-image: url(../../assets/imgs/services-01.png);
  background-repeat: no-repeat;
  background-position: right bottom;
  background-size: 960px;
}
.wscn-http404 {
  position: relative;
  width: 34%;
  top: 25%;
  left: 1%;
  padding: 0 50px;
  // overflow: hidden;
}
.services {
  width: 1234px;
  height: 720px;
}
.title {
  font-size: 20px;
  font-family: SourceHanSansCN-Bold, SourceHanSansCN;
  font-weight: bold;
  color: #24a776;
  line-height: 30px;
  vertical-align:middle;
}
.title__content {
  font-size: 14px;
  font-family: SourceHanSansCN-Medium, SourceHanSansCN;
  font-weight: 500;
  color: #5e6c84;
  line-height: 30px;
}
.btn__content {
  font-size: 20px;
  font-family: SourceHanSansCN-Bold, SourceHanSansCN;
  font-weight: bold;
  color: #27a777;
  cursor: pointer;
  // margin: 0px auto;
  line-height: 20px;
  max-width: 200px;
}
.tip__btn {
  border-radius: 28px;
  border: 1px solid #26b581;
  text-align: center;
  line-height: 56px;
}
.icon {
  width: 50px;
  height: 35px;
}
@media screen and (max-width: 438px) {
  .services-container {
    transform: translate(-50%, -50%);
    position: absolute;
    top: 50%;
    left: 50%;
    background: #edf9fc;
    background-image: url(../../assets/imgs/services-01.png);
    background-repeat: no-repeat;
    background-position: top left;
    background-size: 380px;
  }
  .wscn-http404 {
    position: relative;
    width: 358px;
    top: 25%;
    left: 1%;
    padding: 0 10px;
    // overflow: hidden;
  }
  .title__content {
    font-size: 14px;
    font-family: SourceHanSansCN-Medium, SourceHanSansCN;
    font-weight: 500;
    color: #5e6c84;
    line-height: 30px;
  }
  .title__content {
  font-size: 12px;
  font-family: SourceHanSansCN-Medium, SourceHanSansCN;
  font-weight: 500;
  color: #5e6c84;
  line-height: 30px;
}
}
</style>
