<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="width: 390px" class="selectbox-root">
            <a class="selectbox-hint cursor-mi">搜索</a>
            <div class="selectbox-deliver" />
            <input
              class="selectbox-input"
              v-model="searchKeyword"
              placeholder="输入账户名或交易所名称"
              @keyup.enter.native="onClickSearch"
              clearable
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
          <button
            style="margin-left: 16px"
            class="light-blue-btn"
            @click="onClickOpenAccount"
          >
            +开户
          </button>
          <button
            style="margin-left: 16px; width: 120px"
            class="normal-white-btn"
            @click="onClickBindAccount"
          >
            绑定交易账号
          </button>
        </div>
        <div>
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
            :cell-style="cellStyle"
            style="width: 100%"
            @row-click="handle"
          >
            <el-table-column min-width="10"></el-table-column>
            <el-table-column label="序号" align="left" min-width="60">
              <template slot-scope="scope"
              ><span>{{ getCurListNo(scope.$index) }}</span></template
              >
            </el-table-column>
            <el-table-column
              prop="accountName"
              label="账号名"
              min-width="100"
            />
            <el-table-column
              prop="carbonExchangeName"
              label="交易所"
              min-width="120"
            />
            <el-table-column
              style="padding-right: 5px"
              prop="createdTime"
              label="开户时间"
              min-width="80"
            />
            <el-table-column
              prop="businessScope"
              label="业务范围"
              min-width="300"
            />
            <el-table-column label="操作" align="center" min-width="50">
              <template slot-scope="scope">
                <a class="list-green-text" @click="readAccout(scope.row)"
                >查看</a
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
    </div>
  </div>
</template>
<script>
import { getTradeAccountList, searchTradeAccountInfo } from "@/api/tradeApi";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { cursor } from "@/libs/element-table";
import { setListNo } from "@/libs/public";
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
  // mounted() {},
  methods: {
    cellStyle(data) {
      return cursor(data);
    },
    // 开户
    onClickOpenAccount() {
      this.$router.push({ path: "/trade/account/exchange" });
    },
    // 绑定交易账号
    onClickBindAccount() {
      this.$router.push({ path: "/account/info/", query: { path: "second" } });
    },
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
    },
    //按关键字搜索交易所
    onClickSearch() {
      if (this.searchKeyword === "") {
        this.getList(0);
        return;
      }

      let data = {
        asc: true,
        current: 0,
        keyWords: this.searchKeyword,
        size: this.pageSize,
        sortField: "",
      };

      searchTradeAccountInfo(data)
        .then((res) => {
          debugger;
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
        })
        .catch((err) => {});
    },
    handle(row) {
      openUrlInNewWindow(row.url);
    },
    toDetail(url) {
      openUrlInNewWindow(url);
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
        current: page,
        size: this.pageSize,
        sortField: "",
        accountStatus: "",
        keyWords: this.searchKeyword,
      };
      getTradeAccountList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
        })
        .catch((errror) => {});
    },
    readAccout(row) {
      debugger;
      openUrlInNewWindow(row.url);
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

.cursor-mi {
  cursor: default;
}
</style>
