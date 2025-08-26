<template>
  <el-table height="176px" :show-header="false" :data="records" style="width: 100%" @row-click="handle" :cell-style="cellStyle">
    <el-table-column :show-overflow-tooltip="true" min-width="100%" prop="title" label="标题">
    </el-table-column>
  </el-table>
</template>

<script>
import * as artical from '@/api/article'
import { openUrlInNewWindow } from '@/libs/OpenHelper'
import { cursor } from '@/libs/element-table'
export default {
  data() {
    return {
      records: [],
    }
  },
  mounted() {
    this.getNewsList()
  },

  methods: {
    cellStyle(data) {
      return cursor(data)
    },
    handle(row) {
      if (!row || !row.url || row.url === '') {
        return
      }
      openUrlInNewWindow(row.url)
    },
    getNewsList() {
      const data = {
        "asc": true,
        "categoryId": "0180000004", // 分类ID: 1.咨询 2.知识库 3.公告 4.常见问题
        // "current": "",
        // "size": 0,
        // "sortField": ""
      }
      artical.getCarbonArticles(data).then(res => {
        this.records = res.records
        debugger
      }).catch(errror => {

      })
    }
  }
};
</script>
<style lang="scss" scoped>
.icon {
  width: 14px;
  height: 14px;
}
</style>
