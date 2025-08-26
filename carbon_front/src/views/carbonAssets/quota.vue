<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="myassets-div">
          <img class="icon" src="@/assets/icon/icon_my_assets.png" />
          <span style="margin-left: 8px" class="list-blue-text text-left"
          >我的碳配额资产</span
          >
        </div>
        <div class="myassets-container">
          <span class="assets-hint">持仓总量</span>
          <span class="assets-text">{{ setNumber(topData.total) }}(tCO2e)</span>
          <div class="assets-line" />
          <span class="assets-hint">可用数量</span>
          <span class="assets-text">{{ setNumber(topData.availableAmount) }}(tCO2e)</span>
          <div class="assets-line" />
          <span class="assets-hint">锁定数量</span>
          <span class="assets-text">{{ setNumber(topData.lockedAmount) }}(tCO2e)</span>
          <div class="assets-line" />
          <span class="assets-hint">冻结数量</span>
          <span class="assets-text">{{ setNumber(topData.frozenAmount) }}(tCO2e)</span>
          <div class="empty-holder" />

          <button
            style="width: 68px; margin-left: 16px"
            class="normal-blue-btn"
            @click="onClickUpload"
          >
            上传
          </button>
          <button
            style="width: 68px; margin-left: 16px"
            class="normal-white-btn vertical-center"
            @click="onClickBuy"
          >
            我想买
          </button>
        </div>

        <div class="container">
          <div style="width: 270px; margin-right: 16px" class="selectbox-root">
            <a class="selectbox-hint">资产状态</a>
            <div class="selectbox-deliver" />
            <el-cascader
              style="width: 120px"
              placeholder="全部"
              class="selectbox-input"
              v-model="seletedAssetStatus"
              :options="assetStatusList"
              clearable
              @change="update"
            >
            </el-cascader>
          </div>
          <div
            style="margin-right: 16px; padding-right: 0px"
            class="selectbox-root"
          >
            <a class="selectbox-hint" style="width: 100px">有效日期</a>
            <div class="selectbox-deliver" />
            <el-date-picker
              v-model="selectDate"
              type="datetime"
              placeholder="选择开始时间"
              align="right"
              :picker-options="pickerOptions"
              @change="update"
              size="medium"
              value-format="yyyy-MM-dd HH:mm:ss"
            >
            </el-date-picker>
            <el-date-picker
              v-model="selectEndDate"
              type="datetime"
              placeholder="选择结束时间"
              align="right"
              :picker-options="pickerOptions"
              @change="update"
              size="medium"
              value-format="yyyy-MM-dd HH:mm:ss"
            >
            </el-date-picker>
          </div>

          <div
            style="flex-grow: 1; margin-left: 16px; margin-right: 16px"
            class="selectbox-root"
          >
            <a class="selectbox-hint" style="min-width: 100px">关键词搜索</a>
            <div class="selectbox-deliver" />
            <el-input
              v-model="searchKeyWord"
              placeholder="请输入名称"
              clearable
              size="medium"
              @clear="onclickSearch"
              @keyup.enter.native="onclickSearch"
            />
          </div>
          <button
            style="margin: auto"
            class="light-green-btn"
            @click="onclickSearch"
          >
            查询
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
            stripe
            @cell-click="handle"
            :row-style="{ height: '64px' }"
            :cell-style="cellStyle"
            style="width: 100%"
          >
            <!-- <el-table-column label="" align="center" min-width="30">
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
            <el-table-column label="序号" align="left" min-width="40">
              <template slot-scope="scope"
              ><span>{{ getCurListNo(scope.$index) }}</span></template
              >
            </el-table-column>
            <el-table-column
              :show-overflow-tooltip="true"
              prop="agencyName"
              align="left"
              label="一级市场持有机构"
              min-width="120"
            />
            <el-table-column
              align="left"
              prop="total"
              label="持仓量(tCO2e)"
              min-width="90"
            >
              <template slot-scope="scope">
                <span>{{ setNumber(scope.row.total) }}</span>
              </template>
            </el-table-column>
            <!-- <el-table-column
              align="left"
              prop="availableAmount"
              label="可用量(tCO2e)"
              min-width="90"
            /> -->
            <el-table-column
              align="left"
              prop="valuation"
              label="资产估值(¥)"
              min-width="90"
            >
              <template slot-scope="scope">
                <span>{{ setNumber(scope.row.valuation) }}</span>
              </template>
            </el-table-column>
            <el-table-column
              align="left"
              prop="assetsStatusName"
              label="资产状态"
              min-width="60"
            />
            <!-- <el-table-column
              align="left"
              prop="transactionStatusName"
              label="交易状态"
              min-width="60"
            /> -->
            <el-table-column
              align="left"
              prop="expiryDate"
              label="有效日期"
              min-width="60"
            />
            <el-table-column label="操作" min-width="150" align="center">
              <template slot-scope="scope">
                <a class="list-blue-text" @click="toDetail(scope.row.id)"
                >查看</a
                >
                <a
                  style="margin-left: 10px"
                  class="list-blue-text"
                  @click="OutsizeTransaction(scope.row)"
                >场外报价</a
                >
                <a
                  style="margin-left: 10px"
                  class="list-green-text"
                  @click="insideTransaction"
                >场内交易</a
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
      <!-- 场外交易按钮弹出页面 -->
      <el-dialog :title="title" :visible.sync="dialogFormVisible" width="720px">
        <el-form label-position="left" label-width="130px" :model="form">
          <el-form-item label="出售数量(tCO2e)" prop="tradeQuantity">
            <span class="require">*</span>
            <el-input
              v-model="form.tradeQuantity"
              size="medium"
              style="width: 268px; top: -5px"
            ></el-input>
          </el-form-item>
          <el-form-item label="出售单价(￥)" prop="negotiatedPrice">
            <el-input
              v-model="form.assetUnitPrice"
              size="medium"
              style="width: 268px; top: -5px"
            ></el-input>
          </el-form-item>
          <el-form-item label="出售截止时间" prop="expirationDate">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              size="medium"
              v-model="form.expirationDate"
              value-format="yyyy-MM-dd HH:mm:ss"
              style="width: 268px; top: -5px"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="期望交割时间" prop="deliveryTime">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              size="medium"
              v-model="form.deliveryTime"
              value-format="yyyy-MM-dd HH:mm:ss"
              style="width: 268px; top: -5px"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="期望交割方式" prop="deliveryMethod">
            <el-select
              v-model="form.deliveryMethod"
              placeholder="协议转入、竞价交易、定价交易"
              size="medium"
              style="width: 536px; top: -5px"
            >
              <el-option
                v-for="(item, index) in tradeMethods"
                :key="index"
                :label="item.name"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="期望交割场所" prop="deliveryExchange">
            <el-select
              v-model="form.deliveryExchange"
              placeholder="全国碳排放权交易中心、北京环境交易所、上海环境能源交易所"
              size="medium"
              style="width: 536px; top: -5px"
            >
              <el-option
                v-for="(item, index) in exchangeList"
                :key="index"
                :label="item.name"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button
            type="primary"
            @click="submit('form')"
            class="light-green-btn"
          >确 定</el-button
          >
        </div>
      </el-dialog>
      <el-dialog title="上架成功" :visible.sync="showQuotation" width="30%">
        <span
        >您的采购单已提交，可在供需行情中查看。确定为您跳转供需行情沟通</span
        >
        <span slot="footer" class="dialog-footer">
          <el-button @click="showQuotation = false">取 消</el-button>
          <el-button @click="toQuotation" type="primary">确 定</el-button>
        </span>
      </el-dialog>
      <BuyAssets
        :dialogFormVisible="buyAssetsDlg"
        @changeBuyAssetsDialogFormVisible="changeDialogFormVisible"
      />
      <carbonUploadVue
        :dialogFormVisible="carbonUploadDlg"
        :selData = list
        :isCredit="false"
        title="碳配额项目上传"
        @changeVisible="changeCarbonVisible"
        @submit="submited"
      />
    </div>
  </div>
</template>
<script>
import {
  getExchangeDict,
  getDiliveryMethodeDict,
  getAssetStatusDict,
} from "@/config/dictHelper";
import carbonUploadVue from "./carbonUpload.vue";
import { setListNo } from "@/libs/public";
import * as quota from "@/api/carbonAssetApi";
import BuyAssets from "@/views/carbonTrade/quotation/buyAssets.vue";
import { setLargeNumber } from "@/libs/public"

export default {
  name: "quota",
  components: { BuyAssets, carbonUploadVue },
  data() {
    return {
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < new Date().setTime(new Date().getTime() - 3600 * 1000 * 24);
        }
      },
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      exchangeList: [], //交易所字典
      tradeMethods: [], //交易方式字典
      searchKeyWord: "",
      assetStatusList: [], //资产状态字典
      dialogFormVisible: false,
      buyAssetsDlg: false,
      list: [],
      total: 0,
      current: 1,
      carbonUploadDlg: false,
      pageCount: 1,
      showQuotation: false,
      sellCarbonTotal: 0,
      flag: 0,
      topData: {
        total:0,
        availableAmount:0,
        lockedAmount:0,
        frozenAmount:0
      },
      form: {
        id: 0,
        tradeQuantity: "", //出售数量
        assetUnitPrice: "", //出售单价
        expirationDate: "", //出售截止时间
        deliveryTime: "", //期望交割时间
        deliveryMethod: "", //期望交割方式
        deliveryExchange: "", //期望交割地点
        projectType: "", //项目类型
        tradeRole: "0270000002", //方向
        status: "",
        projectId: null,
        assetType: "0140000002", //资产类型
        institutionName: "",
      },
      //校验规则
      rules: {
        tradeQuantity: [
          { required: true, message: "请输入出售数量", trigger: "blur" },
        ],
        // negotiatedPrice: [
        //   { required: true, message: "请输入出售单价", trigger: "blur" },
        // ],
        // expirationDate: [
        //   {
        //     type: "date",
        //     required: true,
        //     message: "请选择日期",
        //     trigger: "change",
        //   },
        // ],
        // deliveryTime: [
        //   {
        //     type: "date",
        //     required: true,
        //     message: "请选择时间",
        //     trigger: "change",
        //   },
        // ],
        // deliveryMethod: [
        //   {
        //     required: true,
        //     message: "请选择交割方式",
        //     trigger: "change",
        //   },
        // ],
        // deliveryExchange: [
        //   {
        //     required: true,
        //     message: "请选择交割场所",
        //     trigger: "change",
        //   },
        // ],
      },
      title: "",
      pageSize: 10,
      optionsStandard: [
        {
          value: "",
          label: "",
        },
      ],
      optionsOnlines: [
        {
          value: "",
          label: "",
        },
      ],
      value: "",
      seletedAssetStatus: "", //选中的资产状态
      selectDate: "",
      selectEndDate: "",
    };
  },
  mounted() {
    this.getList(1);
    this.getTopData();
    this.exchangeList = getExchangeDict(this.$store);
    this.tradeMethods = getDiliveryMethodeDict(this.$store);
    this.formatAssetStatus(getAssetStatusDict(this.$store));
    // console.log(this.switchTradeStatus("160000001"));
  },
  methods: {
    setNumber(str){
      return setLargeNumber(str)
    },
    showTip() {
      this.showQuotation = true;
    },
    toQuotation() {
      this.$router.push("/trade/quotation");
    },
    changeCarbonVisible(res) {
      this.carbonUploadDlg = res;
    },
    changeDialogFormVisible(res) {
      this.buyAssetsDlg = res;
    },
    // switchTradeStatus(status) {
    //   switch (status) {
    //     case "0160000000":
    //       return "全部";
    //     case "0160000001":
    //       return "询报价";
    //     case "0160000001":
    //       return "意向成交";
    //     case "0160000003":
    //       return "交易履约";
    //     case "0160000004":
    //       return "已交易";
    //     case "0160000005":
    //       return "已取消";
    //   }
    // },
    cellStyle({ row, column, rowIndex, columnIndex }) {
      if (column.label != "操作") {
        return "cursor:pointer;";
      }
    },
    submited(res) {
      if (res) {
        this.getList(1);
        this.getTopData();
      }
    },
    // 格式化状态类型字典
    formatAssetStatus(data) {
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
        this.assetStatusList.push(CertificationItem);
      });
    },
    onClickBuy() {
      this.buyAssetsDlg = true;
    },
    toDetail(id) {
      this.$router.push({
        path: "/assets/quota/quotaDetail",
        query: { id: id },
      });
    },
    onclickSearch() {
      const data = {
        assetsStatus: this.seletedAssetStatus[0],
        expiryDateStart: this.selectDate,
        expiryDateEnd: this.selectEndDate,
        agencyName: this.searchKeyWord,
      };
      quota.loadCarbonQuotaPageList(data).then(
        (res) => {
          this.list = res.data.records;
          this.total = Number(res.data.total);
          this.current = Number(res.data.current);
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            let time = v.expiryDate.split(" ");
            v.expiryDate = time[0];
            // v.transactionStatusName = this.switchTradeStatus(
            //   v.transactionStatus
            // );
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
            }
          });
        },
        (err) => {
          this.$message.success("查询失败");
        }
      );
    },
    onEdit() {},
    onClickPublish() {},
    onClickDelete() {},
    onClickOffline() {},
    //场外报价按钮
    OutsizeTransaction(row) {
      this.dialogFormVisible = true;
      this.title = "场外报价：" + row.agencyName;
      this.form.id = row.id;
      this.form.projectId = row.carbonProjectId;
      this.form.assetType = row.transactionStatus;
      this.form.institutionName = row.agencyName;
      this.sellCarbonTotal = row.availableAmount;
      // this.form.projectType = row.fieldChildCode;
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getList(this.current);
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getList(val);
    },
    handle(row, column, cell, event) {
      if (column.label != "操作") {
        this.toDetail(row.id);
      }
      // openUrlInNewWindow(row.sourceFileUrl)
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
        // "status": 0,
        // "type": 0
      };
      quota
        .loadCarbonQuotaPageList(data)
        .then((res) => {

          res.data.records.forEach(function(e){
            if(e.assetsStatus="0130000004"){
              e.assetsStatusName="待审核";
            }
            else if(e.assetsStatus="0130000001"){
              e.assetsStatusName="已签发";
            }
            else if(e.assetsStatus="0130000002"){
              e.assetsStatusName="已锁定";
            }else if(e.assetsStatus="0130000003"){
              e.assetsStatusName="已冻结";
            }else if(e.assetsStatus="0130000005"){
              e.assetsStatusName="驳回";
            }
          })
          this.list = res.data.records;
          this.total = Number(res.data.total);
          this.current = Number(res.data.current);
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            if (v.expiryDate) {
              let time = v.expiryDate.split(" ");
              v.expiryDate = time[0];
            }
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
            // v.transactionStatusName = this.switchTradeStatus(
            //   v.transactionStatus
            // );
          });
        })
        .catch((errror) => {});
    },
    update() {
      const data = {
        assetsStatus: this.seletedAssetStatus[0],
        expiryDateStart: this.selectDate,
        expiryDateEnd: this.selectEndDate,
        agencyName: this.searchKeyWord,
      };
      quota
        .loadCarbonQuotaPageList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = Number(res.data.total);
          this.current = Number(res.data.current);
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            v.checked = false;
            v.type = v.type ? v.type : "--";
            let time = v.issuingDate.split(" ");
            v.issuingDate = time[0];
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
            }
          });
        })
        .catch((errror) => {});
    },
    //提交表单按钮
    submit(formName) {
      //修改碳信用状态
      const data = {
        id: this.form.id,
        transactionStatus: "0160000001",
      };
      if (this.form.tradeQuantity > this.sellCarbonTotal) {
        this.$message.warning("出售数量不能大于可用量");
        return;
      }

      if (this.form.tradeQuantity) {
        this.form.assetType = "0140000002";
        quota.addcarbonAssetMarket(this.form).then(
          (res) => {
            quota.changeQuota(data).then(
              (res) => {
                this.$message.success("操作成功");
                this.showTip();
                this.dialogFormVisible = false;
                this.getList(1);
              },
              (err) => {}
            );
          },
          (err) => {
            this.$message.warnning("操作失败");
          }
        );
      } else {
        this.$message.warning("必填项不能为空");
      }
    },
    onClickUpload() {
      this.carbonUploadDlg = true;
    },
    getTopData() {
      if (this.flag == 1) {
        return;
      }
      this.flag == 1;
      quota.getQuotaData().then((res) => {
        this.topData = res.data;
        this.flag = 0;
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
    //场内交易
    insideTransaction() {
      this.$router.push("/trade/account/exchange");
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
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
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
    //导出功能
    onClickExport() {
      this.$message.success("功能即将推出，敬请期待！");
    },
    // checkbox end
  },
  created() {},
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

/deep/.el-date-picker.has-sidebar.has-time {
  background: #0a5857d6;
  color: #fff;
  border: 1px solid #22f4d6;
}

/deep/.el-date-picker__header-label {
  color: #ffffff;
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

.myassets-div {
  width: 184px;
  display: flex;
  flex-direction: row;
}

.icon {
  width: 24px;
  height: 24px;
}

.text-left {
  margin: auto;


  font-weight: 500;
  color: #24a776;
}

.myassets-container {
  display: flex;
  flex-direction: row;
  margin-top: 15px;
  margin-bottom: 20px;
  padding-left: 10px;
  padding-right: 10px;
  height: 54px;
  background: #e3f2ec;
  border-radius: 6px;
  // opacity: 0.1;
}

.assets-hint {
  margin-top: auto;
  margin-bottom: auto;


  font-weight: 400;
  color: #424c5c;

}

.require {
  color: red;

  position: absolute;
  left: -20px;
}

.assets-text {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 6px;


  font-weight: 500;
  color: #24a776;

}

.assets-line {
  margin: auto;
  margin-left: 10px;
  margin-right: 10px;
  width: 1px;
  height: 18px;
  border: 1px solid rgba(38, 181, 129, 0.5);
}

.vertical-center {
  margin-top: auto;
  margin-bottom: auto;
}
</style>
