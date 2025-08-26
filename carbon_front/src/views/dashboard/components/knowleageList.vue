<template>
  <el-table height="400px" :show-header="false" :data="records" style="width: 100%" @row-click="handle" :cell-style="cellStyle">
    <el-table-column width="24px" label="日期">
      <template slot-scope="scope">
        <div>
          <!-- <img class="icon" :src="scope.row.imageUrl"> -->
          <img class="icon" v-if="Number(scope.row.browseNum) >= 10" src="@/assets/imgs/icon_new_hot.png" />
          <img class="icon" v-if="Number(scope.row.browseNum) < 10" src="@/assets/imgs/icon_new_jin.png" />
        </div>
      </template>
    </el-table-column>
    <el-table-column :show-overflow-tooltip="true" min-width="70%" prop="title" label="文章名称">
    </el-table-column>
    <el-table-column width="160px" prop="updatedTime" label="日期">
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
    dataFormat(time){
      var datetime = new Date();
      datetime.setTime(parseInt(time));
      var year = datetime.getFullYear();
      var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
      var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
      var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
      var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
      var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
      return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
    },
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
        "categoryId": "0180000002", // 分类ID: 1.咨询 2.知识库 3.公告 4.常见问题
        // "current": "",
        // "size": 9,
        // "sortField": ""
      }
      artical.getCarbonArticles(data).then(res => {
        this.records = res.records;
        debugger
        // this.total = res.total
        // this.current = res.current
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
