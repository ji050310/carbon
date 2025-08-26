<template>
  <div>
    <div class="root" style="position: relative">
      <div class="search-container">
        <div style="width: 280px; margin-right: 16px" class="selectbox-root">
          <a class="selectbox-hint cursor-mi" style="width: 70px">资产类型</a>
          <div class="selectbox-deliver" />
          <el-cascader
            style="width: 120px"
            class="selectbox-input"
            v-model="seletedAssetStatus"
            :options="assetStatusList"
            @change="onClickSearch"
            clearable
          >
          </el-cascader>
        </div>
        <div style="flex-grow: 1; margin-right: 16px" class="selectbox-root">
          <a class="selectbox-hint cursor-mi" style="min-width: 70px"
          >项目领域</a
          >
          <div class="selectbox-deliver" />
          <el-cascader
            style="width: 120px"
            placeholder="全部"
            class="selectbox-input"
            v-model="seletedField"
            @change="onClickSearch"
            :options="projectField"
            clearable
          >
          </el-cascader>
        </div>
        <div style="flex-grow: 1; margin-right: 16px" class="selectbox-root">
          <a class="selectbox-hint cursor-mi" style="min-width: 70px"
          >项目类型</a
          >
          <div class="selectbox-deliver" />
          <el-cascader
            style="width: 120px"
            placeholder="全部"
            @change="onClickSearch"
            class="selectbox-input"
            v-model="seletedProject"
            :options="projectList"
            clearable
          >
          </el-cascader>
        </div>
        <div style="width: 430px" class="selectbox-root">
          <a class="selectbox-hint cursor-mi" style="min-width: 120px"
          >供应截止日期</a
          >
          <div class="selectbox-deliver" />
          <el-date-picker
            style="width: 210px"
            v-model="selectDate"
            type="date"
            placeholder="请选择日期时间"
            @change="onClickSearch"
            align="right"
            :picker-options="pickerOptions"
            size="medium"
          >
          </el-date-picker>
          <el-date-picker
            v-model="selectEndDate"
            type="date"
            placeholder="请选择结束日期"
            @change="onClickSearch"
            align="right"
            size="medium"
            :picker-options="pickerOptions"
          />
        </div>
      </div>
      <div class="search-container" style="margin-top: 0">
        <div style="width: 600px; margin-right: 16px" class="selectbox-root">
          <!-- <a class="selectbox-hint cursor-mi" style="width: 90px">供应机构搜索</a>
          <div class="selectbox-deliver" /> -->
          <el-input
            style="width: 600px"
            class="selectbox-input"
            v-model="searchKeyWord"
            @keyup.enter.native="onClickSearch"
            clearable
            @clear="onClickSearch"
          />
        </div>
        <button class="light-green-btn" @click="onClickSearch">搜索</button>
      </div>
      <div class="content-container">
        <div>
          <el-table
            :header-cell-style="{
              background: '#F2F5F7',
              border: '0px solid #DDDDDD',
              color: '#F2F5F7;',
              height: '64px',
            }"
            :show-header="true"
            :data="list"
            :row-style="{ height: '64px' }"
            :cell-style="cellStyle"
            style="width: 100%"
          >
            <el-table-column min-width="3%"></el-table-column>
            <el-table-column label="序号" align="left" min-width="10%">
              <template slot-scope="scope"
              ><span>{{ getCurListNo(scope.$index) }}</span></template
              >
            </el-table-column>
            <el-table-column
              align="left"
              prop="assetTypeName"
              label="资产类型"
              min-width="15%"
            />
            <el-table-column
              align="left"
              prop="projectTypeName"
              label="项目类型"
              min-width="15%"
            />
            <el-table-column
              align="left"
              prop="projectScope"
              label="项目领域"
              min-width="15%"
            />
            <el-table-column
              :show-overflow-tooltip="true"
              prop="publisherName"
              label="机构名称"
              align="left"
              min-width="30%"
            />

            <el-table-column
              align="left"
              prop="tradeQuantity"
              label="出售量(tCO2e)"
              min-width="15%"
            />
            <el-table-column
              align="left"
              prop="assetUnitPrice"
              label="报价(元/tCO2e)"
              min-width="15%"
            />
            <el-table-column
              align="left"
              prop="expirationDate"
              label="出售截止日期"
              min-width="10%"
            />
            <el-table-column label="操作" align="center" min-width="15%">
              <template slot-scope="scope">
                <a
                  style="margin-left: 10px"
                  class="list-blue-text"
                  v-on:click.stop="clickDetail(scope.row)"
                >查看</a
                >
                <a
                  style="margin-left: 10px"
                  v-on:click.stop="clickSell(scope.row)"
                  class="list-green-text"
                >{{ btnText }}</a
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
        <div style="flex-grow: 1" />
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
    <!-- 报价dialog -->
    <sell-order
      :form="orderForm"
      :title="title"
      :dialogFormVisible="orderDialogFormVisible"
      @changeDialogFormVisible="changeOrderDialogFormVisible"
      @successSubmit="showOrderResult"
    />
    <sell-detail
      :form="form"
      :title="title"
      :dialogFormVisible="detailDialogFormVisible"
      @changeDialogFormVisible="changeDetailDialogFormVisible"
      @successSubmit="closeDetail"
    />
    <!-- 确定dialog -->
    <order-result-vue
      :title="tipTitle"
      :content="tipConetent"
      :bottomTxt="tipBottomTxt"
      :img="tipImg"
      :dialogFormVisible="comformDialogFormVisible"
      @changeComfromDialogVisible="listencomformDialogFormVisible"
    />
  </div>
</template>
<script>
import { getCarbonTradeQuoteList, searchKeyword } from "@/api/carbonAssetApi";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { cursor } from "@/libs/element-table";
import {
  getAssetTypeDict,
  getProjectTypeDict,
  getProjectAreaDict,
} from "@/config/dictHelper";
import { isProjectTypeDisable } from "@/libs/public";
import { setListNo } from "@/libs/public";
import { endsWith } from "../../../../utils/utils";
import sellOrder from "./sellOrder.vue";
import orderResultVue from "../orderResult.vue";
import SellDetail from "./sellDetail.vue";
export default {
  name: "companyPackage",
  components: {
    selectDropDownBox,
    sellOrder,
    orderResultVue,
    SellDetail,
  },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchKeyword: "",
      list: [],
      total: 0,
      current: 0,
      projectField: [],
      seletedField: "",
      pageCount: 1,
      pageSize: 10,
      value: "",
      orderForm: null,
      seletedAssetStatus: "",
      assetStatusList: [],
      seletedProject: "",
      projectList: [],
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
      selectDate: "",
      selectEndDate: "",
      searchKeyWord: "",
      isSearch: false, //是否在搜索
      exchangesUrl:
        "https://carbonmsger.feishu.cn/drive/folder/fldcn66yo6D4OoXwZqEMHL6OQSg?from=space_persnoal_filelist",
      title: "",
      form: {},
      btnText: "询价",
      tipTitle: "询价提示",
      // tipConetent: "您的询价已提交，可在采购行情中查看，确定为您跳转供需行情",
      tipConetent:
        "您的询价已提交，可在询报价管理中查看，确定为您转跳询报价管理",
      tipBottomTxt: "如需帮助，可添加交易专员企业微信，“为您做交易引导服务",
      tipImg: "@/assets/imgs/head.gif",
      orderDialogFormVisible: false, // 报价dialog,
      comformDialogFormVisible: false, // 报价确认dialog,
      detailDialogFormVisible: false, // 详情dialog,
      isProjectTypeDisable: false,
    };
  },
  // mounted() {},
  methods: {
    changeAssetType() {
      this.isProjectTypeDisable = isProjectTypeDisable(
        this.seletedAssetStatus[0]
      );
      this.onClickSearch();
    },
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
    },
    update() {
      const data = {
        current: this.current,
        assetType: this.seletedAssetStatus[0],
        projectType: this.seletedProject[0],
        expirationDateStart: this.selectDate,
        expirationDateEnd: this.selectEndDate,
        projectScopeCode: this.seletedField[0],
        tradeRole: "0270000002",
      };
      getCarbonTradeQuoteList(data).then((res) => {
        this.list = res.data.records;
        this.total = Number(res.data.total);
        this.current = Number(res.data.current);
        this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
        this.list.map((v) => {
          for (var i in v) {
            v[i] = v[i] ? v[i] : "--";
            if (v[i] == " ") {
              v[i] = "--";
            }
          }
        });
      });
    },
    changeOrderDialogFormVisible(res) {
      this.orderDialogFormVisible = res;
    },
    listencomformDialogFormVisible(res) {
      this.comformDialogFormVisible = res;
      if (res) {
        this.$router.push("/trade/offer");
      }
      // this.$emit("changeTab", "buy");
    },
    changeDetailDialogFormVisible(res) {
      this.detailDialogFormVisible = res;
    },
    showOrderResult(res) {
      this.detailDialogFormVisible = false;
      this.orderDialogFormVisible = false;
      this.comformDialogFormVisible = true;
    },
    closeDetail(res) {
      this.detailDialogFormVisible = res;
    },
    cellStyle(data) {
      // return cursor(data);
    },
    getQuotePageList(data) {
      getCarbonTradeQuoteList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = Number(res.data.total);
          this.current = Number(res.data.current);
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            for (var i in v) {
              if (!v[i]) {
                v[i] = "--";
              }
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        })
        .catch((errror) => {});
    },
    //按关键字搜索交易所
    onClickSearch() {
      if (this.searchKeyWord) {
        this.isSearch = true;
        // const param = {
        //   keyword: this.searchKeyWord,
        //   pageNum: 1,
        //   pageSize: this.pageSize,
        // };
        // searchKeyword(param).then((res) => {
        //   this.list = res.data.records;
        //   this.total = Number(res.data.total);
        //   this.current = Number(res.data.current);
        //   this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
        //   this.list.map((v) => {
        //     for (var i in v) {
        //       v[i] = v[i] ? v[i] : "--";
        //     }
        //   });
        // });
        const data = {
          asc: true,
          assetType: this.seletedAssetStatus[0],
          current: this.current,
          expirationDateEnd: this.selectEndDate,
          expirationDateStart: this.selectDate,
          projectType: this.seletedProject[0],
          size: this.pageSize,
          tradeRole: "0270000002",
          projectScopeCode: this.seletedField[0],
          keyword: this.searchKeyWord,
          pageNum: 1,
          pageSize: this.pageSize,
        };
        this.getQuotePageList(data);
      } else {
        this.getList(1);
        this.isSearch = false;
      }
    },
    handle(row) {
      this.title = "供应单-" + row.institutionName;
      this.form = { ...row };
      this.detailDialogFormVisible = true;
      this.orderDialogFormVisible = false;
      this.comformDialogFormVisible = false;
    },
    clickDetail(row) {
      this.title = "供应单-" + row.institutionName;
      this.form = { ...row };
      this.detailDialogFormVisible = true;
      this.orderDialogFormVisible = false;
      this.comformDialogFormVisible = false;
    },
    clickSell(row) {
      this.title = "询价单-" + row.institutionName;
      this.orderForm = { ...row };
      this.detailDialogFormVisible = false;
      this.orderDialogFormVisible = true;
      this.comformDialogFormVisible = false;
      debugger;
    },
    handleSizeChange(val) {
      this.pageSize = val;
      if (this.isSearch) {
        this.onClickSearch();
      } else {
        this.getList(this.current);
      }
    },
    handleCurrentChange(val) {
      this.current = val;
      //判断是否是在搜索
      if (this.isSearch) {
        this.onClickSearch();
      } else {
        this.getList(this.current);
      }
    },
    // 监听页面宽度变化，刷新表格
    handleResize() {
      if (this.infoList) this.$refs.visitChart.handleResize();
    },
    getList(page) {
      this.isSearch = false;
      const data = {
        asc: true,
        assetType: this.seletedAssetStatus[0],
        current: this.current,
        expirationDateEnd: this.selectEndDate,
        expirationDateStart: this.selectDate,
        projectType: this.seletedProject[0],
        projectScopeCode: this.seletedField[0],
        size: this.pageSize,
        tradeRole: "0270000002",
      };

      this.getQuotePageList(data);
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
    formatFieldDict(data) {
      if (data) {
        data.map((v) => {
          let item = {
            value: "",
            label: "",
          };
          item.value = v.value;
          item.label = v.name;
          this.projectField.push(item);
        });
      }
    },
    // checkbox end
  },
  created() {
    this.handleChangeVisitType();
  },
  mounted() {
    this.getList(1);
    this.formatFieldDict(getProjectAreaDict(this.$store));
    let data = getAssetTypeDict(this.$store);
    this.assetStatusList = [];
    data.map((v) => {
      let item = {
        value: "",
        label: "",
      };
      item.value = v.value;
      item.label = v.name;
      this.assetStatusList.push(item);
    });
    this.assetStatusList[0].value = "";
    data = getProjectTypeDict(this.$store);
    this.projectList = [];
    data.map((v) => {
      let item = {
        value: "",
        label: "",
      };
      item.value = v.value;
      item.label = v.name;
      this.projectList.push(item);
    });
  },
};
</script>
<style lang="scss" scoped>
.root {
  display: flex;
  flex-direction: column;
  background: white;
}

.search-container {
  display: flex;
  flex-direction: row;
  width: 100%;
  margin-top: 10px;
  margin-bottom: 20px;
}

.container {
  height: 50px;
  margin: 10px 0px 20px 0px;
  display: flex;
  flex-direction: row;
}

.content-container {
  flex-grow: 1;
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
