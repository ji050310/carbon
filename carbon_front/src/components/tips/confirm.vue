<template>
  <el-dialog
    :visible.sync="dialogVisible"
    width="30%"
    :before-close="handleClose"
  >
    <span>{{ title }}</span>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false" class="normal-white-btn"
        >取 消</el-button
      >
      <el-button class="light-green-btn" @click="dialogVisible = false"
        >确 定</el-button
      >
    </span>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: true,
    };
  },
  props: ["title"],
};
</script>

<style lang="scss" scoped>
</style>