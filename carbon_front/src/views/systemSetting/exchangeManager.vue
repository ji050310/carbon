<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="width: 390px" class="selectbox-root">
            <span class="selectbox-hint" style="width: 60px">搜索</span>
            <div class="selectbox-deliver" />

            <el-input v-model="searchKeyword" placeholder="输入名称" clearable size="medium"
                      @keyup.enter.native="onClickSearch" />
          </div>
          <!-- <div style="flex-grow:1"/> -->
          <button style="margin-left: 16px" class="light-green-btn" @click="onClickSearch">
            查询
          </button>
        </div>
        <div>
          <!-- <button style="margin-top:0px;margin-bottom:20px;width:103px" class="normal-white-btn"
            @click="onClickAdd">+添加交易所</button> -->
          <el-table :header-cell-style="{
            background: '#F2F5F7',
            border: '0px solid #DDDDDD',
            color: '#242B35',
            height: '64px',
          }" :show-header="true" :data="list" :row-style="{ height: '64px' }" stripe style="width: 100%">
            <!-- <el-table-column label="checkbox" align="center" width="40">
              <template slot="header" slot-scope="{ column }">
                <el-checkbox
                  v-model="column.checked"
                  :indeterminate="indeterminateFlag"
                  :checked="allchecked"
                  label=""
                  @change="updateAllSelected"
                ></el-checkbox>
              </template>
              <template slot-scope="scope">
                <el-checkbox
                  @change="signCheckChange"
                  v-model="scope.row.checked"
                ></el-checkbox>
              </template>
            </el-table-column> -->
            <el-table-column min-width="10"></el-table-column>
            <el-table-column label="序号" align="left" min-width="60">
              <template slot-scope="scope"><span>{{ getCurListNo(scope.$index) }}</span></template>
            </el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="name" label="交易所名称" align="left" width="150" />
            <el-table-column align="left" prop="city" label="城市" width="100" />
            <el-table-column align="left" prop="businessScope" label="业务范围" width="500" />
            <el-table-column align="center" prop="status" label="状态" width="100" />
            <el-table-column label="操作" align="center" min-width="170">
              <template slot-scope="scope">
                <a class="list-blue-text" @click="handle(scope.row.detailUrl)">查看</a>
                <a :class="editStyleChange(scope.row.status)" style="margin-left: 10px"
                   @click="onEdit(scope.row.website)">编辑</a>
                <a style="margin-left: 10px" :class="publishStyleChange(scope.row.status)"
                   @click="onClickPublish(scope.row.id)">发布</a>
                <a style="margin-left: 10px" @click="onClickOffline(scope.row.id)"
                   :class="offlineStyleChange(scope.row.status)">下线</a>
                <a style="margin-left: 10px" class="list-red-text" @click="onClickDelete(scope.row.id)">删除</a>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
        <div style="flex-grow: 1" />
        <a style="margin: auto" class="pageBox-total-num">共{{ total }}条</a>
        <el-pagination style="margin: auto" background @size-change="handleSizeChange"
                       @current-change="handleCurrentChange" :current-page="current" :page-size="pageSize" :page-count="pageCount"
                       layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>
<!--注释了mounted() { },-->
<script>
import { loadCarbonExchangeList } from "@/api/carbonAssetApi";
import { delCarbonExchanger } from "@/api/carbonAssetApi";
import { updateCarbonExchanger } from "@/api/carbonAssetApi";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { searchCarbonExchanger } from "@/api/carbonAssetApi";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { setListNo } from "@/libs/public";
import { cursor } from "@/libs/element-table";
export default {
  name: "companyPackage",
  components: { selectDropDownBox },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchKeyword: "",
      list: [],
      total: 0,
      current: 0,
      pageCount: 1,
      pageSize: 10,
      value: "",
      exchangesUrl:
        "https://carbonmsger.feishu.cn/drive/folder/fldcn66yo6D4OoXwZqEMHL6OQSg?from=space_persnoal_filelist",
    };
  },
  // mounted() { },
  methods: {
    cellStyle({ row, column, rowIndex, columnIndex }) {
      if (column.label == "操作") {
        return "margin-left:0px;padding:0 0;";
      } else {
        return "cursor:pointer;";
      }
    },
    onClickAdd() {
      openUrlInNewWindow(this.exchangesUrl);
    },
    //按关键字搜索交易所
    onClickSearch() {
      if (this.searchKeyword === "") {
        this.getList(this.current);
      } else {
        console.log("---------");
        const data = {
          asc: true,
          current: this.current,
          searchKey: this.searchKeyword,
          size: this.pageSize,
          sortField: "",
          // "type": 0
        };
        loadCarbonExchangeList(data)
          .then((res) => {
            this.list = res.data.records;
            this.total = res.data.total;
            this.current = res.data.current;
            this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
            this.list.map((v) => {
              //遍历表格数据
              v.checked = false;
              v.status = v.status === 2 ? "未发布" : "已发布";
              for (var i in v) {
                v[i] = v[i] ? v[i] : "--";
                if (v[i] == " ") {
                  v[i] = "--";
                }
              }
            });
          })
          .catch((errror) => {
            this.$message.success("不存在此交易所");
          });
      }
    },
    onEdit(url) {
      openUrlInNewWindow(url);
    },
    //判断是否发布，若发布了则修改样式
    editStyleChange(status) {
      return "afterSubmitEdit";
      // if (status === "已发布") {
      //   return "afterSubmitEdit";
      // } else {
      //   return "list-green-text";
      // }
    },

    publishStyleChange(status) {
      if (status === "已发布") {
        return "afterSubmitPublish";
      } else {
        return "list-blue-text";
      }
    },
    offlineStyleChange(status) {
      if (status === "已发布") {
        return "list-red-text";
      } else {
        return "afterSubmitOffline";
      }
    },
    onClickPublish(row_id) {
      const data = {
        id: parseInt(row_id),
        status: 1,
      };
      updateCarbonExchanger(data).then(
        (res) => {
          this.$message.success("发布成功");
          this.getList(this.current);
        },
        (err) => {
          this.$message.success("发布失败");
        }
      );
    },
    onClickDelete(id) {
      var id = parseInt(id);
      this.$confirm("删除内容不可复原，请谨慎操作").then(() => {
        delCarbonExchanger(id).then(
          (res) => {
            this.$message.success("删除成功");
            this.getList(this.current);
          },
          (err) => {
            this.$message.success("删除失败");
          }
        );
      });
    },
    handle(url) {
      if (url) {
        openUrlInNewWindow(url);
      } else {
        this.$message.warning("没有对应的url");
      }
      // openUrlInNewWindow(row.sourceFileUrl)
    },
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
    },
    onClickOffline(row_id) {
      const data = {
        // "address": "",
        // "businessArea": "",
        // "businessScope": "",
        // "city": "",
        // "createdTime": "",
        // "creatorId": 0,
        // "email": "",
        // "establishmentTime": "",
        id: parseInt(row_id),
        // "introduction": "",
        // "legalPerson": "",
        // "name": "",
        // "phone": "",
        // "registeredCapital": "",
        status: 2,
        // "type": 0,
        // "updatedId": 0,
        // "updatedTime": "",
        // "website": ""
      };
      updateCarbonExchanger(data).then(
        (res) => {
          this.$message.success("下架成功");
          this.getList(this.current);
        },
        (err) => {
          this.$message.success("下架失败");
        }
      );
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getList(this.current);
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getList(this.current);
    },
    // 监听页面宽度变化，刷新表格
    handleResize() {
      if (this.infoList) this.$refs.visitChart.handleResize();
    },
    getList(page) {
      const data = {
        asc: true,
        current: this.current,
        name: "",
        size: this.pageSize,
        sortField: "",
        // "type": 0
      };

      loadCarbonExchangeList(data)
        .then((res) => {
          debugger;
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(this.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            v.checked = false;
            v.status = v.status === 2 ? "未发布" : "已发布";
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        })
        .catch((errror) => {
          debugger;
        });
    },
    // checkbox start
    signCheckChange() {
      let allCheckedFlag = true;
      let allReset = true;
      this.articals.forEach((item) => {
        if (item.checked == true) {
          allReset = false;
        } else {
          allCheckedFlag = false;
        }
      });
      if (allCheckedFlag || allReset) {
        this.indeterminateFlag = false;
        if (allCheckedFlag) {
          this.allchecked = true;
        } else {
          this.allchecked = false;
        }
      } else {
        this.indeterminateFlag = true;
      }
      this.reRender = !this.reRender;
    },
    updateAllSelected(val) {
      this.indeterminateFlag = false;
      if (val) {
        this.list.forEach((item) => {
          item.checked = true;
        });
      } else {
        this.list.forEach((item) => {
          item.checked = false;
        });
      }
    },
    //render-header方法
    renderCheckHeader(h, { column, $index }) {
      return h("span", {}, [
        h("el-checkbox", {
          props: {
            checked: this.allchecked,
            indeterminate: this.indeterminateFlag, //表头复选框状态
          },
          on: {
            change: this.updateAllSelected, // 选中事件
          },
        }),
      ]);
    },
    // checkbox end
  },
  created() {
    this.handleChangeVisitType();
  },
  mounted() {
    this.getList(0);
  },
};
</script>
<style lang="scss" scoped>
.root {
  background: #f2f5f7;
}

.divBox {
  margin: 20px;
  background: #ffffff;
  box-shadow: 0px 2px 8px 0px #eaf0f3;
  border-radius: 8px;
}

.container {
  margin: 10px 0px 20px 0px;
  display: flex;
  flex-direction: row;
}

.content-container {
  display: flex;
  flex-direction: column;
  width: 100%;
}

>>>.el-cascader .el-input .el-input__inner,
>>>.el-cascader .el-input.is-focus .el-input__inner {
  border-color: transparent;
}

.acea-row {
  /deep/.el-avatar--small {
    width: 22px;
    height: 22px;

  }
}

.checkTime {
  /deep/.el-radio__input {
    display: none;
  }
}

.ivu-pl-8 {
  margin-left: 8px;

}

.dashboard-console-visit {
  /deep/.el-card__header {
    padding: 14px 20px !important;
  }

  ul {
    li {
      list-style-type: none;
      margin-top: 12px;
    }
  }
}

.ivu-mb {
  margin-bottom: 10px;
}

.newsImg {
  width: 30px;
  height: 30px;
  border-radius: 4px;
}
</style>
