<template>
  <el-dialog style="padding: 0px" :title="subTitle" :visible.sync="show" width="560px" :before-close="clickClose">
    <div class="root">
      <div class="contentDiv" style="display:flex;justify-content:space-between">
        <a class="content verticalCenter cursor-mi">{{ mycontent }}</a>
        <button class="light-green-btn verticalCenter" style="margin-left: 20px" @click="clickOk">
          确定
        </button>
      </div>
      <div class="bottomDiv">
        <a class="bottomTxt cursor-mi">{{ bottomTxt }}</a>
        <img class="image marleft30px verticalCenter" src="@/assets/icon/icon_qrcode.jpeg" />
      </div>
    </div>
  </el-dialog>
</template>
<script>
export default {
  name: "companyPackage",
  props: {
    title: "",
    dialogFormVisible: false,
    content: "",
    bottomTxt: "",
    img: "",
  },
  data() {
    return {
      show: false,
      subTitle: "",
      mycontent: "",
      mybottomTxt: "",
      myimg: "",
    };
  },
  watch: {
    title() {
      this.subTitle = this.title;
    },
    dialogFormVisible() {
      this.show = this.dialogFormVisible;
    },
    content() {
      this.mycontent = this.content;
    },
    bottomTxt() {
      this.mybottomTxt = this.bottomTxt;
    },
    img() {
      this.myimg = this.img;
    },
  },
  mounted() {
    this.subTitle = this.title;
    this.show = this.dialogFormVisible;
    this.mycontent = this.content;
    this.mybottomTxt = this.bottomTxt;
    this.myimg = this.img;
  },
  methods: {
    clickClose() {
      this.$emit("changeComfromDialogVisible", false);
      this.show = false;
    },
    clickOk() {
      this.show = false;
    },
  },
};
</script>
<style lang="scss" scoped>
.root {
  display: flex;
  flex-direction: column;
}

.contentDiv {
  padding: 20px;
  padding-bottom: 30px;
  display: flex;
  flex-direction: row;
}

.bottomDiv {
  padding: 20px;
  display: flex;
  flex-direction: row;
  height: 110px;
  background: rgba(38, 181, 129, 0.1);
  border-radius: 0px 0px 8px 8px;
}

.context {
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #424c5c;
  line-height: 24px;
  flex-grow: 1;
}

.bottomTxt {
  margin-top: auto;
  margin-bottom: auto;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #24a776;
  line-height: 16px;
  flex-grow: 1;
}

.verticalCenter {
  margin-top: auto;
  margin-bottom: auto;
}

.image {
  width: 80px;
  height: 80px;
}

.marleft30px {
  margin-left: 20px;
}

.cursor-mi {
  cursor: default;
}

/deep/.el-dialog__body {
  padding: 0px !important;
}
</style>>