<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="width: 390px" class="selectbox-root">
            <span class="selectbox-hint" style="width: 60px">搜索</span>
            <div class="selectbox-deliver" />
            <el-input
              v-model="searchKeyword"
              placeholder="名称、编号、关键字等"
              clearable
              size="medium"
              @keyup.enter.native="onClickSearch"
            />
          </div>
          <!-- <div style="flex-grow:1"/> -->
          <button
            style="margin-left: 16px"
            class="light-green-btn"
            @click="onClickSearch"
          >
            查询
          </button>
        </div>
        <div>
          <!-- <button style="margin-top:0px;margin-bottom:20px;width:103px" class="normal-white-btn"
            @click="onClickAdd">+添加交易所</button> -->
          <el-table
            :header-cell-style="{
              background: '#F2F5F7',
              border: '0px solid #DDDDDD',
              color: '#242B35',
              height: '64px',
            }"
            :show-header="true"
            :data="list"
            :row-style="{ height: '64px' }"
            stripe
            style="width: 100%"
          >
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
              <template slot-scope="scope"
                ><span>{{ getCurListNo(scope.$index) }}</span></template
              >
            </el-table-column>
            <el-table-column
              :show-overflow-tooltip="true"
              prop="name"
              label="交易所名称"
              align="left"
              width="150"
            />
            <el-table-column
              align="center"
              prop="city"
              label="城市"
              width="100"
            />
            <el-table-column
              align="left"
              prop="businessScope"
              label="业务范围"
              width="600"
            />
            <!-- <el-table-column
              align="center"
              prop="status"
              label="状态"
              width="100"
            /> -->
            <el-table-column label="操作" align="center" min-width="170">
              <template slot-scope="scope">
                <!-- <a :class="editStyleChange(scope.row.status)" @click="onEdit"
                  >编辑</a
                > -->
                <a
                  style="margin-left: 10px"
                  class="list-blue-text"
                  @click="toDetail(scope.row)"
                  >查看</a
                >
                <a
                  style="margin-left: 10px"
                  @click="toExchange(scope.row.website, 0)"
                  class="list-green-text"
                  >开户</a
                >
                <a
                  style="margin-left: 10px"
                  class="list-green-text"
                  @click="toExchange(scope.row.website, 1)"
                  >交易</a
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
        <div style="flex-grow: 1" />
        <a style="margin: auto" class="pageBox-total-num">共{{ total }}条</a>
        <el-pagination
          style="margin: auto"
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="current"
          :page-size="pageSize"
          :page-count="pageCount"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </div>
      <el-dialog title="提示" :visible.sync="dialogVisible" width="560px">
        <span class="tips">{{ dialogVisibleText }}</span>
        <el-button
          type="primary"
          @click="openAccount"
          class="light-green-btn"
          style="float: right; margin-right: 30px"
        >
          确 定</el-button
        >
        <div style="clear: both"></div>
        <div class="dialog-footer">
          <span class="footer-tip"
            >如需帮助，可添加交易专员企业微信，为您做开户引导服务</span
          ><img src="@/assets/icon/icon_qrcode.jpeg" alt="" class="img" />
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import { loadCarbonExchangeList } from "@/api/carbonAssetApi";
import { delCarbonExchanger } from "@/api/carbonAssetApi";
import { updateCarbonExchanger } from "@/api/carbonAssetApi";
import { setListNo } from "@/libs/public";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { searchCarbonExchanger } from "@/api/carbonAssetApi";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
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
      dialogVisible: false, //开户弹框显示
      dialogVisibleText: "是否确认开户？",
      pageCount: 1,
      pageSize: 10,
      value: "",
      exchangesUrl:
        "https://carbonmsger.feishu.cn/drive/folder/fldcn66yo6D4OoXwZqEMHL6OQSg?from=space_persnoal_filelist",
      openUrl: "",
    };
  },
  mounted() {},
  methods: {
    cellStyle(data) {
      return cursor(data);
    },
    handle(row, column, cell, event) {
      if (column.label != "操作") {
        if (row.detailUrl) {
          openUrlInNewWindow(row.detailUrl);
        } else {
          this.$message.warning("没有对应的url");
        }
      }
      // openUrlInNewWindow(row.sourceFileUrl)
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
          status: 1,
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
              v.name = v.name ? v.name : "--";
              v.status = v.status === 2 ? "未发布" : "已发布";
              v.businessScope = v.businessScope ? v.businessScope : "--";
              v.city = v.city ? v.city : "--";
            });
            this.$message.success("查询成功");
          })
          .catch((errror) => {
            this.$message.success("不存在此交易所");
          });
      }
    },
    onEdit() {
      openUrlInNewWindow(this.exchangesUrl);
    },
    toExchange(url, type) {
      if (type) {
        this.dialogVisibleText = "是否确认交易？";
      } else {
        this.dialogVisibleText = "是否确认开户？";
      }
      this.dialogVisible = true;
      this.openUrl = url;
    },
    toDetail(row) {
      if (row.detailUrl) {
        openUrlInNewWindow(row.detailUrl);
      } else {
        this.$message.warning("没有对应的url");
      }
    },
    openAccount() {
      this.dialogVisible = false;
      openUrlInNewWindow(this.openUrl);
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
    // 加载交易所列表
    getList(page) {
      const data = {
        asc: true,
        current: this.current,
        name: "",
        size: this.pageSize,
        sortField: "",
        status: 1,
        // "type": 0
      };

      loadCarbonExchangeList(data)
        .then((res) => {
          debugger;
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            v.checked = false;
            v.name = v.name ? v.name : "--";
            v.status = v.status === 2 ? "未发布" : "已发布";
            v.businessScope = v.businessScope ? v.businessScope : "--";
            v.city = v.city ? v.city : "--";
          });
        })
        .catch((errror) => {
          debugger;
        });
    },
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
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

>>> .el-cascader .el-input .el-input__inner,
>>> .el-cascader .el-input.is-focus .el-input__inner {
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

.tips {
  width: 112px;
  height: 16px;
  
  
  font-weight: 400;
  color: #424c5c;
  
}

// .el-dialog {
//   padding: 0 0 0 0;
// }

.dialog-footer {
  width: 560px;
  height: 110px;
  background: rgba(38, 181, 129, 0.1);
  border-radius: 0px 0px 8px 8px;
  margin-top: 20px;
  position: relative;
  left: -20px;
  bottom: -20px;
}

.footer-tip {
  width: 416px;
  height: 16px;
  
  
  font-weight: 400;
  color: #24a776;
  margin-left: 20px;
  position: relative;
  // top: 10px;
  
}

.img {
  width: 60px;
  height: 60px;
  position: relative;
  top: 25px;
  left: 30px;
}
</style>