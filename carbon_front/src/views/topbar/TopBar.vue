<template>
    <div class="container">
     <!-- <img class="logo" src="@/assets/imgs/icon_home_logo.png"/> -->
     <div class="navitext-containner">
       <el-link style="margin-right:20px;" href="/" >首页</el-link>
       <el-link style="margin-right:20px;" href="/assets" >中和资产管理</el-link>
       <el-link style="margin-right:20px;" href="/account" >碳用户管理</el-link>
       <el-link style="margin-right:20px;" href="/trade" >碳交易管理</el-link>
       <el-link style="margin-right:20px;" href="/greenCertManager" >绿度认证</el-link>
       <el-link style="margin-right:20px;" href="/carbonNewsManager" >碳资讯</el-link>
       <el-link style="margin-right:20px;" href="/carbonFinaManager" >碳金融</el-link>
     </div>
   </div>

</template>

<style scoped>
.container {
     display: flex;
     flex-direction: row;
     background: #fff;
     height: 70px;
     justify-content:space-between
}
.logo {
   margin-left:10px;
   width: 230px; 
   height: 70px
}
.navitext-containner {
 margin-right:20px;
 display:flex;
 align-items:center
}
</style>