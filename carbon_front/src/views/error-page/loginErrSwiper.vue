<template>
  <el-carousel height="720px" :interval="5000" indicator-position="outside">
    <el-carousel-item >
     <loginServiceTips/>
    </el-carousel-item>
    <el-carousel-item  >
         <ourServiceTips/>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
import loginServiceTips from "./loginServiceTips";
import ourServiceTips from "./ourServiceTips";
export default {
  name: "loginErrSwiper",
    components: { loginServiceTips,ourServiceTips},
  data() {
    return {};
  },
  computed: {
  },
  methods: {},
};


</script>
<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
   background: #edf9fc;
  }
  
  .el-carousel__item:nth-child(2n+1) {
   background: #edf9fc;
  }
</style>