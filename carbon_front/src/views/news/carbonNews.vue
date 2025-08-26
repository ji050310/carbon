<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="width: 400px" class="selectbox-root">
            <span class="selectbox-hint" style="width: 110px">文章类型</span>
            <div class="selectbox-deliver" />
            <el-cascader
              placeholder="全部"
              class="selectbox-input"
              :options="Articleoptions"
              v-model="catagoryValue"
              clearable
              @change="articalCatagorySelected"
            >
            </el-cascader>
          </div>
          <div style="width: 350px; margin-left: 16px" class="selectbox-root">
            <span class="selectbox-hint" style="width: 110px">文章搜索</span>
            <div class="selectbox-deliver" />
            <el-input
              v-model="searchKeyWord"
              placeholder="输入标题"
              clearable
              size="medium"
              @keyup.enter.native="onClickSearch"
            />
          </div>
          <button
            style="margin-left: 16px"
            class="light-green-btn"
            @click="onClickSearch"
          >
            查询
          </button>
        </div>
        <div>
          <el-table
            :show-header="false"
            :data="articals"
            stripe
            :row-style="{ height: '64px' }"
            :cell-style="cellStyle"
            style="width: 100%"
            @cell-click="handle"
          >
            <el-table-column min-width="60%">
              <template slot-scope="scope">
                <div style="margin-left: 20px">
                  <div class="content-card">
                    <span class="text-line">{{ scope.row.title }} </span>
                    <div class="info-line">
                      <span class="articalCatagory"
                      >#{{ scope.row.categoryIdName }}</span
                      >
                      <span class="date">{{ scope.row.updatedTime }}</span>
                      <span class="author">{{ scope.row.author }}</span>
                    </div>
                  </div>
                  <div class="deliver" />
                </div>
              </template>
            </el-table-column>
            <!--            <el-table-column><div style="width: 1px;height: 44px;border: 1px solid #DADEE5;;"></div></el-table-column> -->
            <el-table-column label="操作" min-width="15%" align="center">
              <template slot-scope="scope">
                <div class="operation-item">
                  <div
                    style="
                      width: 1px;
                      height: 44px;
                      border: 1px solid #dadee5;
                      margin-right: 50px;
                    "
                  ></div>
                  <div class="operation-item" @click="onClickCollect(scope.row.url)">
                    <img
                      class="icon center"
                      src="@/assets/icon/icon_star.png"
                    />
                    <!-- <img
                      class="icon center"
                      v-if="!isCollection"
                      src="@/assets/icon/icon_star.png"
                    />
                    <img
                      class="icon center"
                      v-if="isCollection"
                      src="@/assets/icon/icon_star_selected.png"
                    /> -->
                    <a
                      class="list-green-text center text-left"
                      style="font-size: 14px"
                    >收藏</a
                    >
                  </div>
                  <div style="margin-left: 20px;display:none;" class="operation-item">
                    <img
                      class="icon center"
                      src="@/assets/icon/icon_store.png"
                    />
                    <a
                      class="list-blue-text center text-left"
                      style="font-size: 14px"
                      @click="onClickPublish"
                    >关注</a
                    >
                  </div>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <!-- <div style="margin-top:30px;margin-bottom:10px" class="pageBox">
        <div style="flex-grow:1"/>
        <a style="margin:auto" class="pageBox-total-num">共{{Math.ceil(total/pageSize)}}页</a>
        <el-pagination
          style="margin:auto"
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="current"
          :page-size="pageSize"
          :page-count="pageCount"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
    </div> -->
      <button
        style="display: block; margin: 0 auto"
        class="light-green-btn"
        @click="moreArtical"
      >
        加载更多
      </button>
    </div>
    <!-- 提示 -->
    <el-dialog title="提示" :visible.sync="isCollection" width="560px">
      <span class="tips">{{ isCollectionText || "请进入飞书文档，点击标题右侧按钮收藏！" }}</span>
      <el-button
        type="primary"
        @click="openAccount"
        class="light-green-btn"
        style="float: right; margin-right: 30px"
      >
        确 定</el-button
      >
      <div style="clear: both"></div>
      <!-- <div class="dialog-footer">
        <span class="footer-tip"
          >如需帮助，可添加交易专员企业微信，为您做开户引导服务</span
        ><img src="@/assets/icon/icon_qrcode.jpeg" alt="" class="img" />
      </div> -->
    </el-dialog>
  </div>
</template>
<!--mounted函数注释了-->
<script>
import echartsFrom from "@/components/echarts/index";
import * as artical from "@/api/article";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { cursor } from "@/libs/element-table";
export default {
  name: "companyPackage",
  components: { echartsFrom, selectDropDownBox },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchKeyWord: "",
      articals: [],
      total: 0,
      isCollection: false, //是否收藏
      current: 1,
      pageCount: 1,
      pageSize: 10,
      isCollectionText:"请进入飞书文档，点击标题右侧按钮收藏！",
      isCollectionTip:true,
      isCollectionUrl:"",
      Articleoptions: [
        {
          value: "0180000000",
          label: "全部",
        },
        {
          value: "0180000001",
          label: "行业动态",
        },
        {
          value: "0180000002",
          label: "行业知识库",
        },
        {
          value: "0180000004",
          label: "常见问题",
        },
        {
          value: "0180000003",
          label: "平台公告",
        },
      ],
      value: "",
      catagoryValue: 0,
    };
  },
  // mounted() {},
  methods: {
    cellStyle(data) {
      return cursor(data);
    },
    // 收藏按钮
    onClickCollect(url) {
      if(url){
        this.isCollectionUrl = url;
      }
      //  this.isCollectionTip = !tihs.isCollectionTip
      this.isCollection = !this.isCollection;
    },
    openAccount(){
      if(this.isCollectionUrl){
        openUrlInNewWindow(this.isCollectionUrl);
      }
      this.isCollection = !this.isCollection;
    },
    //加载更多文章
    moreArtical() {
      if (this.current === this.pageCount) {
        this.$message.success("没有更多了！");
      } else {
        const data = {
          asc: true,
          // "categoryId": this.catagoryValue[0],
          current: this.current++,
          size: this.pageSize,
          sortField: "",
        };
        artical
          .getCarbonArticles(data)
          .then((res) => {
            this.articals.push(...res.records);
            this.total = res.total;
            this.current = this.current++;
            this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
            this.articals.map((v) => {
              //遍历表格数据
              v.checked = false;
              v.categoryName = v.categoryName ? "# " + v.categoryName : "--";
              v.author = v.author ? v.author : "--";
              let time = v.updatedTime.split(" ");
              v.updatedTime = time[0];
            });
          })
          .catch((errror) => {});
      }
    },
    handle(row, column) {
      if (column.label != "操作") {
        openUrlInNewWindow(row.url);
      }
    },
    onClickSearch() {
      const data = {
        asc: true,
        categoryId: this.catagoryValue[0],
        current: 0,
        searchKeywords: this.searchKeyWord,
        size: 0,
        sortField: "",
      };
      artical
        .getCarbonArticles(data)
        .then((res) => {
          this.articals = res.records;
          this.total = res.total;
          this.current = res.current;
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.articals.map((v) => {
            //遍历表格数据
            v.checked = false;
            v.author = v.author ? v.author : "--";
            let time = v.updatedTime.split(" ");
            v.updatedTime = time[0];
          });
        })
        .catch((errror) => {});
    },
    //当前选择的文章类型
    articalCatagorySelected() {
      const data = {
        asc: true,
        categoryId: this.catagoryValue[0],
        searchKeywords: this.searchKeyWord,
        current: 0,
        size: 0,
        sortField: "",
      };
      artical
        .getCarbonArticles(data)
        .then((res) => {
          this.articals = res.records;
          this.total = res.total;
          this.current = res.current;
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.articals.map((v) => {
            //遍历表格数据
            v.checked = false;
            v.author = v.author ? v.author : "--";
            let time = v.updatedTime.split(" ");
            v.updatedTime = time[0];
          });
        })
        .catch((errror) => {});
    },
    onEdit() {},
    onClickPublish() {
      this.$alert("功能即将推出，敬请期待！", {
        confirmButtonText: "确定",
        type: "warning",
      }).then(() => {});
    },
    onClickDelete() {},
    onClickOffline() {},
    handleSizeChange(val) {
      this.pageSize = val;
      this.loadArticels();
    },
    handleCurrentChange(val) {
      this.current = val;
      this.loadArticels();
    },
    // 监听页面宽度变化，刷新表格
    handleResize() {
      if (this.infoList) this.$refs.visitChart.handleResize();
    },
    loadArticels() {
      const data = {
        asc: true,
        current: this.current,
        size: this.pageSize,
        sortField: "",
        status: "0260000002",
      };
      artical
        .getCarbonArticles(data)
        .then((res) => {
          this.articals = res.records;
          this.total = res.total;
          this.current = res.current;
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.articals.map((v) => {
            //遍历表格数据
            v.checked = false;
            v.categoryName = v.categoryName ? "# " + v.categoryName : "--";
            v.author = v.author ? v.author : "--";
            let time = v.updatedTime.split(" ");
            v.updatedTime = time[0];
          });
        })
        .catch((errror) => {});
    },
    // checkbox start
    updateAllSelected(val) {
      this.indeterminateFlag = false;
      if (val) {
        this.articals.forEach((item) => {
          item.checked = true;
        });
      } else {
        this.articals.forEach((item) => {
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
  created() {},
  mounted() {
    this.loadArticels();
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
  margin: 20px 0px 20px 10px;
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
    line-height: 22px;
  }
}

.checkTime {
  /deep/.el-radio__input {
    display: none;
  }
}

.ivu-pl-8 {
  margin-left: 8px;
  font-size: 14px;
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

.content-card {
  display: flex;
  flex-direction: column;
}

.text-line {
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #424c5c;
}

.info-line {
  display: flex;
  flex-direction: row;
}

.tag {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #24a776;
}

.date {
  margin-left: 20px;
  font-size: 14px;
  font-family: Barlow-Regular, Barlow;
  font-weight: 400;
  margin-top: 10px;
  color: #5e6c84;
}

.author {
  margin-left: 20px;
  font-size: 14px;
  margin-top: 10px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #5e6c84;
}

.diliver {
  width: 1px;
  height: 44px;
  border: 1px solid #dadee5;
}

.operation-item {
  display: flex;
  flex-direction: row;
}

.icon {
  width: 24px;
  height: 24px;
  margin-right: 10px;
  cursor: pointer;
}

.center {
  margin: auto;
}

.text-left {
  margin-left: 10px;
}

.moreBtn {
  margin: 0 auto;
}

.articalCatagory {
  // border: #24a776 1px solid;
  // width: 70px;
  // height: 14px;
  margin-top: 15px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #24a776;
  line-height: 14px;
}
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
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #24a776;
  margin-left: 20px;
  position: relative;
  // top: 10px;
  line-height: 16px;
}

.img {
  width: 60px;
  height: 60px;
  position: relative;
  top: 25px;
  left: 30px;
}
</style>

