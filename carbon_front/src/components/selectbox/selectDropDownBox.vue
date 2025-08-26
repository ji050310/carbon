<template>
    <div class="selectbox-root">
      <span class="selectbox-hint">{{hint}}</span>
      <div class="deliver"/>
       <el-input  class="selectbox-dropbox" v-model="inputModle" placeholder="holder" clearable>
       </el-input>
    </div>
</template>

<script>
export default {
    props: {
        hint:'sss',
        holder:'',
        inputModle:''
    }
}
</script>

<style scoped>
.selectbox-root {
    height: 38px;
    background: #FFFFFF;
    border-radius: 4px;
    border: 1px solid #E3E7EB;
    display: flex;
    flex-direction: row;
}
.selectbox-hint{
    align-content: center;
    height: 14px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #242B35;
    line-height: 14px;
}
.selectbox-deliver {
    align-content: center;
    margin-left: 15px;
    /* margin-right: 15px; */
    width: 1px;
    height: 14px;
    border: 1px solid #DADEE5;
}
.selectbox-dropbox{
    flex-grow: 1;
    align-content: center;
    height: 14px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #808EA5;
    line-height: 14px;
}
</style>