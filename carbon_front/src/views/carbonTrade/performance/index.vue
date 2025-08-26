<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="flex-grow: 1" class="selectbox-root">
            <a class="selectbox-hint cursor-mi">交易角色</a>
            <div class="selectbox-deliver" />
            <el-cascader
              style="width: 100px"
              placeholder="全部"
              class="selectbox-input"
              v-model="selectedRole"
              :options="tradeRoleList"
              @change="onClickSearch"
              clearable
            >
            </el-cascader>
          </div>
          <div style="margin-left: 16px; flex-grow: 1" class="selectbox-root">
            <a class="selectbox-hint cursor-mi">资产类型</a>
            <div class="selectbox-deliver" />
            <el-cascader
              style="width: 100px"
              placeholder="全部"
              class="selectbox-input"
              v-model="selectedAsset"
              @change="onClickSearch"
              :options="assetList"
              clearable
            >
            </el-cascader>
          </div>
          <div style="width: 550px; margin-left: 16px" class="selectbox-root">
            <a class="selectbox-hint cursor-mi" style="width: 150px"
              >交易截止时间</a
            >
            <div class="selectbox-deliver" />
            <el-date-picker
              style="flex-grow: 1; margin-left: 5px"
              v-model="selectDate"
              @change="onClickSearch"
              type="datetime"
              placeholder="请选择开始日期"
              align="right"
              size="medium"
              :picker-options="pickerOptions"
            >
            </el-date-picker>
            <el-date-picker
              v-model="selectEndDate"
              type="datetime"
              placeholder="请选择结束日期"
              align="right"
              size="medium"
              :picker-options="pickerOptions"
              @change="onClickSearch"
            />
          </div>
        </div>
        <div class="container" style="margin-top: 0">
          <div style="margin-right: 15px; flex-grow: 1" class="selectbox-root">
            <a class="selectbox-hint cursor-mi" style="width: 120px"
              >卖方机构搜索</a
            >
            <div class="selectbox-deliver" />
            <el-input
              class="selectbox-input"
              v-model="searchSellerName"
              placeholder="输入卖方机构名称"
              clearable
              @keyup.enter.native="onClickSearch"
              @clear="onClickClearSellerKeyword"
            />
          </div>
          <div
            style="margin-right: 15px; flex-grow: 1; margin-left: 15px"
            class="selectbox-root"
          >
            <a class="selectbox-hint cursor-mi" style="width: 120px"
              >买方机构搜索</a
            >
            <div class="selectbox-deliver" />
            <el-input
              class="selectbox-input"
              v-model="searchBuyerName"
              placeholder="输入买方机构名称"
              clearable
              @keyup.enter.native="onClickSearch"
              @clear="onClickClearBuyerKeyword"
            />
          </div>
          <button class="light-green-btn" @click="onClickSearch">查询</button>
        </div>
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
          @row-click="handle"
          style="width: 100%"
        >
          <el-table-column min-width="2%"></el-table-column>
          <el-table-column label="序号" align="left" min-width="10%">
            <template slot-scope="scope"
              ><span>{{ getCurListNo(scope.$index) }}</span></template
            >
          </el-table-column>
          <el-table-column
            align="left"
            :show-overflow-tooltip="true"
            prop="counterparty"
            label="交易对手"
            min-width="15%"
          />
          <el-table-column
            align="left"
            :show-overflow-tooltip="true"
            prop="tradeRoleName"
            label="交易角色"
            min-width="15%"
          />
          <el-table-column
            align="left"
            :show-overflow-tooltip="true"
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
            prop="tradeQuantity"
            label="交易量(tCO2e)"
            min-width="20%"
          />
          <el-table-column
            align="left"
            prop="assetUnitPrice"
            label="协议价格(¥)"
            min-width="15%"
          />
          <el-table-column
            align="left"
            prop="statusName"
            label="履约状态"
            min-width="15%"
          />
          <el-table-column
            align="left"
            prop="deliveryTime"
            label="交割时间"
            min-width="15%"
          />
          <el-table-column
            align="left"
            prop="deliveryMethodName"
            label="交割方式"
            min-width="15%"
          />
          <el-table-column
            align="left"
            prop="expirationDate"
            label="交割截止日期"
            min-width="15%"
          />
          <el-table-column label="操作" min-width="20%" align="center">
            <template slot-scope="scope">
              <!-- <a class="list-blue-text" v-on:click.stop="toDetail(scope.row)" style="margin-left: 10px">查看</a> -->
              <a
                class="list-green-text"
                v-on:click.stop="onContact(scope.row.sourceFileUrl)"
                style="margin-left: 10px"
                >协议转让</a
              >
              <a
                class="list-blue-text"
                v-on:click.stop="toOrder(scope.row)"
                style="margin-left: 10px"
                >下单</a
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
    <detail-vue
      :dialogFormVisible="dialogFormVisible"
      :data="selectedData"
      @changeDialogFormVisible="changeDialogFormVisible"
    />
    <order-vue
      :dialogFormVisible="orderDialogFormVisible"
      :data="selectedData"
      @changeComfromDialogVisible="changeOrderDialogFormVisible"
    />
  </div>
</template>
<script>
import { getCarbonTradePerfenceList } from "@/api/carbonAssetApi";
import { editMethod } from "@/api/carbonAssetApi";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { setListNo } from "@/libs/public";
import { getTradeRoleDict } from "@/config/dictHelper";
import { getAssetTypeDict } from "@/config/dictHelper";
import { handleText } from "@/libs/TextUtil";
import { endsWith } from "@/utils/utils";
import detailVue from "./detail.vue";
import orderVue from "./order.vue";
export default {
  name: "companyPackage",
  components: { selectDropDownBox, detailVue, orderVue },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchSellerName: "",
      searchBuyerName: "",
      searchKeyWord: "",
      selectedRole: "", //被选中的交易角色
      selectedAsset: "", //被选中的资产类型
      list: [],
      total: 0,
      current: 0,
      pageCount: 1,
      pageSize: 10,
      tradeRoleList: [], //核证标准字典
      assetList: [], // 资产类型字典
      methodUrl:
        "https://carbonmsger.feishu.cn/drive/folder/fldcn66yo6D4OoXwZqEMHL6OQSg?from=space_persnoal_filelist",
      value: "",
      dialogFormVisible: false,
      orderDialogFormVisible: false,
      selectedData: {},
      contactPLatformUrl: "https://www.qiyuesuo.com/",
      selectDate: "",
      selectEndDate: "",
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
    };
  },
  methods: {
    onClickClearSellerKeyword() {
      debugger;
      this.searchSellerName = "";
      this.onClickSearch();
    },
    onClickClearBuyerKeyword() {
      debugger;
      this.searchBuyerName = "";
      this.onClickSearch();
    },
    changeDialogFormVisible(res) {
      this.dialogFormVisible = false;
      this.orderDialogFormVisible = false;
      this.id = "";
    },
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
    },
    changeOrderDialogFormVisible() {
      this.orderDialogFormVisible = false;
      this.dialogFormVisible = false;
    },
    cellStyle({ row, column, rowIndex, columnIndex }) {
      if (column.label == "编号") {
        return "margin-left:0px;padding:0 0;";
      } else {
        return "cursor:pointer;";
      }
    },
    //判断是否发布，若发布了则修改样式
    editStyleChange(status) {
      if (status == 1) {
        return "afterSubmitEdit";
      } else {
        return "list-green-text";
      }
    },
    publishStyleChange(status) {
      if (status == 1) {
        return "afterSubmitPublish";
      } else {
        return "list-blue-text";
      }
    },
    offlineStyleChange(status) {
      if (status == 1) {
        return "list-red-text";
      } else {
        return "afterSubmitOffline";
      }
    },
    onclickAdd() {
      openUrlInNewWindow(this.methodUrl);
    },
    onClickPublish(row_id) {
      const data = {
        id: row_id,
        status: 1,
      };
      editMethod(data).then(
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
      var id = row_id;
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
    onClickOffline(row_id) {
      const data = {
        id: row_id,
        status: 2,
      };
      editMethod(data).then(
        (res) => {
          this.$message.success("下架成功");
          this.getList(this.current);
        },
        (err) => {
          this.$message.success("下架失败");
        }
      );
    },
    onClickSearch() {
      if (
        this.searchBuyerName === "" &&
        this.searchSellerName === "" &&
        this.selectDate === "" &&
        this.selectedRole[0] === "" &&
        this.selectedAsset[0] === ""
      ) {
        this.getList(1);
      } else {
        const data = {
          asc: true,
          assetType: endsWith(this.selectedAsset[0], "0")
            ? ""
            : this.selectedAsset[0],
          buyerName: this.searchBuyerName,
          current: this.current,
          expirationDateEnd: this.selectEndDate,
          expirationDateStart: this.selectDate,
          projectType: "",
          sellerName: this.searchSellerName,
          size: 0,
          sortField: "",
          tradeRole: endsWith(this.selectedRole[0], "0")
            ? ""
            : this.selectedRole[0],
        };

        getCarbonTradePerfenceList(data)
          .then((res) => {
            this.list = res.data.records;
            this.total = res.data.total;
            this.current = res.data.current;
            this.pageCount = Math.ceil(
              parseInt(res.data.total) / this.pageSize
            );
            this.list.map((v) => {
              v.counterparty = handleText(v.counterparty, "--");
              v.tradeRoleName = handleText(v.tradeRoleName, "--");
              v.assetTypeName = handleText(v.assetTypeName, "全部");
              v.projectTypeName = handleText(v.projectTypeName, "全部");
              v.tradeQuantity = handleText(v.tradeQuantity, "--");
              v.assetUnitPrice = handleText(v.assetUnitPrice, "--");
              v.statusName = handleText(v.statusName, "--");
              v.expirationDate = v.expirationDate
                ? handleText(v.expirationDate.split(" ")[0], "--")
                : "--";
              v.deliveryTime = v.deliveryTime
                ? handleText(v.deliveryTime.split(" ")[0], "--")
                : "--";
              v.deliveryMethodName = handleText(v.deliveryMethodName, "全部");
            });
          })
          .catch((errror) => {});
      }
    },
    toDetail(row) {
      // this.dialogFormVisible = true
      // this.orderDialogFormVisible = false
      // this.selectedData = row
      this.$router.push({
        path: "/trade/performance/orderDetail",
        query: { id: row.id },
      });
    },
    onContact() {
      openUrlInNewWindow(this.contactPLatformUrl);
    },
    toOrder(row) {
      this.selectedData = row;
      this.orderDialogFormVisible = true;
      this.dialogFormVisible = false;
    },
    handle(row) {
      this.$router.push({
        path: "/trade/performance/orderDetail",
        query: { id: row.id },
      });
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getList(1);
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getList(val);
    },
    // 监听页面宽度变化，刷新表格
    handleResize() {
      if (this.infoList) this.$refs.visitChart.handleResize();
    },
    statusName(status) {
      if (status == 1) {
        return "启用";
      } else {
        return "禁用";
      }
    },
    //下选改变之后
    update() {
      const data = {
        certificationCriteria: this.selectedRole ? this.tradeRoleList[0] : "",
        fieldCode: this.selectedAsset ? this.selectedCertification[0] : "",
        industryCode: this.selectedIndustry ? this.selectedIndustry[0] : "",
        projectStatus: this.selectedStatus ? this.selectedStatus[0] : "",
      };
      getCarbonTradePerfenceList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            v.counterparty = handleText(v.counterparty, "--");
            v.tradeRoleName = handleText(v.tradeRoleName, "--");
            v.assetTypeName = handleText(v.assetTypeName, "全部");
            v.projectTypeName = handleText(v.projectTypeName, "全部");
            v.tradeQuantity = handleText(v.tradeQuantity, "--");
            v.assetUnitPrice = handleText(v.assetUnitPrice, "--");
            v.statusName = handleText(v.statusName, "--");
            v.expirationDate = v.expirationDate
              ? handleText(v.expirationDate.split(" ")[0], "--")
              : "--";
            v.deliveryTime = v.deliveryTime
              ? handleText(v.deliveryTime.split(" ")[0], "--")
              : "--";
            v.deliveryMethodName = handleText(v.deliveryMethodName, "全部");
          });
        })
        .catch((errror) => {});
    },
    getList(page) {
      const data = {
        asc: true,
        size: this.pageSize,
        current: page,
        sortField: "",
      };
      getCarbonTradePerfenceList(data)
        .then((res) => {
          debugger;
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            debugger;
            v.counterparty = handleText(v.counterparty, "--");
            v.tradeRoleName = handleText(v.tradeRoleName, "--");
            v.assetTypeName = handleText(v.assetTypeName, "全部");
            debugger;
            v.projectTypeName = handleText(v.projectTypeName, "全部");
            v.tradeQuantity = handleText(v.tradeQuantity, "--");
            v.assetUnitPrice = handleText(v.assetUnitPrice, "--");
            v.statusName = handleText(v.statusName, "--");
            v.expirationDate = v.expirationDate
              ? handleText(v.expirationDate.split(" ")[0], "--")
              : "--";
            v.deliveryTime = v.deliveryTime
              ? handleText(v.deliveryTime.split(" ")[0], "--")
              : "--";
            v.deliveryMethodName = handleText(v.deliveryMethodName, "全部");
          });
        })
        .catch((errror) => {});
    },
    // checkbox start
    signCheckChange() {
      let allCheckedFlag = true;
      let allReset = true;
      this.list.forEach((item) => {
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
    // 格式化交易角色
    formatRoleList(data) {
      data.map((v) => {
        let CertificationItem = {
          label: "",
        };
        if (v.name == "全部") {
          CertificationItem.label = v.name;
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.tradeRoleList.push(CertificationItem);
      });
    },

    // 格式资产类型
    formatAssetsList(data) {
      data.map((v) => {
        let CertificationItem = {
          label: "",
        };
        if (v.name == "全部") {
          CertificationItem.label = v.name;
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.assetList.push(CertificationItem);
      });
    },
  },
  // checkbox end
  created() {
    // this.handleChangeVisitType();
  },
  mounted() {
    this.getList(1);
    this.formatRoleList(getTradeRoleDict(this.$store));
    this.formatAssetsList(getAssetTypeDict(this.$store));
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
  margin: 20px 0px 20px 0px;
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

.newsImg {
  width: 30px;
  height: 30px;
  border-radius: 4px;
}

.cursor-mi {
  cursor: default;
}
</style>