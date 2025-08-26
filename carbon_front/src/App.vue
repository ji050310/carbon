<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import Cookies from "js-cookie";
import { getToken, setToken, removeToken } from "@/utils/auth";
export default {
  name: "App",
  mounted() {
    // var myToken = window.localStorage.getItem("carbonToken");
    // var myCookie = window.localStorage.getItem("carbonCookie");
    // if (myToken && myCookie) {
    //   Cookies.set("JavaInfo", JSON.stringify(myCookie));
    //   setToken(JSON.stringify(myToken));
    //   this.$store.dispatch("user/setToken", myToken);
    // }
    var loginData = localStorage.getItem("login-form");
    if (loginData) {
      this.$store.dispatch("user/login", JSON.parse(loginData)).then(() => {
        this.$router.push({
          path: this.redirect || "/",
          query: this.otherQuery,
        });
      });
    }
  },
  //   methods:{
  //     async layout(e){
  //       if(!this.$store.state.user.isRemember){
  //         await this.$store.dispatch("user/logout");
  //         window.close()
  //       }
  //     }
  //   }
  // }
};
</script>
