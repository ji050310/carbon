<template>
  <div class="services-container">
    <div class="wscn-http404">
      <div>
        <div></div>
        <div style="margin-top: 56px"></div>
        <div>
          <p>
            <img
              class="icon"
              src="@/assets/imgs/title_logo_01.png"
              align="absmiddle"
              alt=""
            />
          </p>
          <div class="content">
            <div>
              <img class="content_img" src="@/assets/imgs/qrcode.png" alt="" />
            </div>
            <div>
              <img
                class="content_img"
                src="@/assets/imgs/title_img_01.png"
                alt=""
              />
            </div>
          </div>
          <div style="margin-top: 60px"></div>
          <div class="btn__content tip__btn" @click="goLogout">
            <template>
              <span>立即体验</span>
            </template>
          </div>
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
  name: "loginServiceTips",

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
  background-image: url(../../assets/imgs/slices_01.png);
  background-repeat: no-repeat;
  background-position: right bottom;
  background-size: 900px;
}
.wscn-http404 {
  position: relative;
  width: 358px;
  top: 25%;
  left: 1%;
  padding: 0 50px;
  // overflow: hidden;
}
.content {
  display: flex;
  justify-content: space-between;
  margin-top: 50px;

  .content_img {
    width: 128px;
    height: 120px;
    margin: 0px 4px;
  }
}

.services {
  width: 1234px;
  height: 720px;
}
.title {
  width: 115px;
  height: 30px;
  font-size: 20px;
  font-family: SourceHanSansCN-Bold, SourceHanSansCN;
  font-weight: bold;
  color: #24a776;
  line-height: 30px;
  vertical-align: middle;
}
.title__content {
  font-size: 14px;
  font-family: SourceHanSansCN-Medium, SourceHanSansCN;
  font-weight: 500;
  color: #5e6c84;
  line-height: 26px;
  margin-top: 10px;
}
.btn__content {
  font-size: 20px;
  font-family: SourceHanSansCN-Bold, SourceHanSansCN;
  font-weight: bold;
  color: #27a777;
  cursor: pointer;
  // margin: 0px auto;
}
.tip__btn {
  width: 187px;
  height: 56px;
  border-radius: 28px;
  border: 1px solid #26b581;
  text-align: center;
  line-height: 56px;
}
.icon {
  width: 270px;
  height: 97px;
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
    top: 40%;
    left: 1%;
    padding: 0 10px;
    // overflow: hidden;
  }
  .content {
    display: flex;
    justify-content: start;
    margin-top: 50px;

    .content_img {
      width: 128px;
      height: 120px;
      margin: 0px 4px;
    }
  }
  .services {
    width: 1234vh;
    height: 720vh;
  }
  .title {
    width: 115px;
    height: 30px;
    font-size: 20px;
    font-family: SourceHanSansCN-Bold, SourceHanSansCN;
    font-weight: bold;
    color: #24a776;
    line-height: 30px;
  }
  .title__content {
    width: 360px;
    height: 26px;
    font-size: 14px;
    font-family: SourceHanSansCN-Medium, SourceHanSansCN;
    font-weight: 500;
    color: #5e6c84;
    line-height: 26px;
    margin-top: 10px;
  }
  .btn__content {
    width: 80px;
    height: 20px;
    font-size: 20px;
    font-family: SourceHanSansCN-Bold, SourceHanSansCN;
    font-weight: bold;
    color: #27a777;
    cursor: pointer;
    // line-height: 30px;
  }
  .tip__btn {
    width: 187px;
    height: 56px;
    border-radius: 28px;
    border: 1px solid #26b581;
    text-align: center;
    line-height: 56px;
  }
}
</style>
