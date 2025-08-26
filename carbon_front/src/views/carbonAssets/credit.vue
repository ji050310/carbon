
<!--v.industryCodeName = v.industryCodeName ? v.industryCodeName : "&#45;&#45;";-->
<!--let time = v.issuingDate.split(" ");-->
<!--v.issuingDate = time[0];-->
<!--for (var i in v) {-->
<!--v[i] = v[i] ? v[i] : "&#45;&#45;";-->
<!--}-->
<!--});-->
<!--},-->
<!--(err) => {}-->
<!--);-->
<!--},-->
<!--onEdit() {},-->
<!--onClickPublish() {},-->
<!--onClickDelete() {},-->
<!--onClickOffline() {},-->
<!--handleSizeChange(val) {-->
<!--this.pageSize = val;-->
<!--this.getList(1);-->
<!--},-->
<!--handleCurrentChange(val) {-->
<!--this.current = val;-->
<!--this.getList(val);-->
<!--},-->
<!--// 监听页面宽度变化，刷新表格-->
<!--handleResize() {-->
<!--if (this.infoList) this.$refs.visitChart.handleResize();-->
<!--},-->
<!--//场内交易-->
<!--insideTransaction() {-->
<!--this.$router.push("/trade/account/exchange");-->
<!--},-->
<!--update() {-->
<!--const data = {-->
<!--certificationCriteria: this.seletedStandard[0], //当前选择的标准-->
<!--transactionStatus: this.seletedStatus[0], //当前选择的状态-->
<!--issuingDateStart: this.selectDate,-->
<!--projectName: this.searchProjectKeyword,-->
<!--methodName: this.searchMethodKeyword,-->
<!--assetsStatus: this.seletedAssetsStatus[0],-->
<!--projectScopeCode: this.seletedIndustry[0],-->
<!--issuingDateEnd: this.selectEndDate,-->
<!--assetsStatus: this.seletedAssetsStatus[0],-->
<!--};-->
<!--loadCarbonCreditPageList(data)-->
<!--.then((res) => {-->
<!--this.list = res.data.records;-->
<!--this.total = Number(res.data.total);-->
<!--this.current = Number(res.data.current);-->
<!--this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);-->
<!--this.list.map((v) => {-->
<!--v.industryCodeName = v.industryCodeName ? v.industryCodeName : "&#45;&#45;";-->
<!--if (v.issuingDate) {-->
<!--let time = v.issuingDate.split(" ");-->
<!--v.issuingDate = time[0];-->
<!--}-->
<!--for (var i in v) {-->
<!--v[i] = v[i] ? v[i] : "&#45;&#45;";-->
<!--}-->
<!--});-->
<!--})-->
<!--.catch((errror) => {});-->
<!--},-->
<!--getList(page) {-->
<!--const data = {-->
<!--asc: true,-->
<!--current: page,-->
<!--size: this.pageSize,-->
<!--// "status": 0,-->
<!--// "type": 0-->
<!--};-->
<!--loadCarbonCreditPageList(data)-->
<!--.then((res) => {-->
<!--if (res.data.records) {-->
<!--this.list = res.data.records;-->
<!--}-->
<!--this.total = Number(res.data.total);-->
<!--// console.log(res.data.total);-->
<!--this.current = Number(res.data.current);-->
<!--this.pageCount = Math.ceil(parseInt(this.total) / this.pageSize);-->
<!--this.list.map((v) => {-->
<!--//遍历表格数据-->
<!--// v.industryCodeName = v.industryCodeName ? v.industryCodeName : "&#45;&#45;";-->
<!--if (v.issuingDate) {-->
<!--let time = v.issuingDate.split(" ");-->
<!--v.issuingDate = time[0];-->
<!--}-->
<!--// v.projectName = v.projectName?v.projectName:"&#45;&#45;"-->
<!--// v.certificationCriteriaName = v.certificationCriteriaName?v.certificationCriteriaName:"&#45;&#45;"-->
<!--// v.transactionStatusName = v.transactionStatusName?v.transactionStatusName:"&#45;&#45;"-->
<!--for (var i in v) {-->
<!--v[i] = v[i] ? v[i] : "&#45;&#45;";-->
<!--if (v[i] == " ") {-->
<!--v[i] = "&#45;&#45;";-->
<!--}-->
<!--}-->
<!--});-->
<!--})-->
<!--.catch((errror) => {});-->
<!--},-->

<!--// checkbox start-->
<!--signCheckChange() {-->
<!--let allCheckedFlag = true;-->
<!--let allReset = true;-->
<!--this.articals.forEach((item) => {-->
<!--if (item.checked == true) {-->
<!--allReset = false;-->
<!--} else {-->
<!--allCheckedFlag = false;-->
<!--}-->
<!--});-->
<!--if (allCheckedFlag || allReset) {-->
<!--this.indeterminateFlag = false;-->
<!--if (allCheckedFlag) {-->
<!--this.allchecked = true;-->
<!--} else {-->
<!--this.allchecked = false;-->
<!--}-->
<!--} else {-->
<!--this.indeterminateFlag = true;-->
<!--}-->
<!--this.reRender = !this.reRender;-->
<!--},-->

<!--//render-header方法-->
<!--renderCheckHeader(h, { column, $index }) {-->
<!--return h("span", {}, [-->
<!--h("el-checkbox", {-->
<!--props: {-->
<!--checked: this.allchecked,-->
<!--indeterminate: this.indeterminateFlag, //表头复选框状态-->
<!--},-->
<!--on: {-->
<!--change: this.updateAllSelected, // 选中事件-->
<!--},-->
<!--}),-->
<!--]);-->
<!--},-->
<!--// 格式化验证标准字典-->
<!--formatCertification(data) {-->
<!--data.map((v) => {-->
<!--let CertificationItem = {-->
<!--label: "",-->
<!--};-->
<!--if (v.name == "全部") {-->
<!--CertificationItem.label = v.name;-->
<!--} else {-->
<!--CertificationItem.value = v.value;-->
<!--CertificationItem.label = v.name;-->
<!--}-->
<!--this.optionsStandard.push(CertificationItem);-->
<!--});-->
<!--},-->
<!--// 格式化行业类型字典-->
<!--formatIndustory(data) {-->
<!--data.map((v) => {-->
<!--let CertificationItem = {-->
<!--label: "",-->
<!--};-->
<!--if (v.name == "全部") {-->
<!--CertificationItem.label = v.name;-->
<!--} else {-->
<!--CertificationItem.value = v.value;-->
<!--CertificationItem.label = v.name;-->
<!--}-->
<!--this.optionsIndustory.push(CertificationItem);-->
<!--});-->
<!--},-->
<!--// 格式化状态类型字典-->
<!--formatStatus(data) {-->
<!--data.map((v) => {-->
<!--let CertificationItem = {-->
<!--label: "",-->
<!--};-->
<!--if (v.name == "全部") {-->
<!--CertificationItem.label = v.name;-->
<!--} else {-->
<!--CertificationItem.value = v.value;-->
<!--CertificationItem.label = v.name;-->
<!--}-->
<!--this.optionsOnlines.push(CertificationItem);-->
<!--});-->
<!--},-->
<!--// 格式化状态类型字典-->
<!--formatAssetStatus(data) {-->
<!--data.map((v) => {-->
<!--let CertificationItem = {-->
<!--label: "",-->
<!--};-->
<!--if (v.name == "全部") {-->
<!--CertificationItem.label = v.name;-->
<!--} else {-->
<!--CertificationItem.value = v.value;-->
<!--CertificationItem.label = v.name;-->
<!--}-->
<!--this.optionsAssetStatus.push(CertificationItem);-->
<!--});-->
<!--},-->


<!--},-->
<!--created() {-->
<!--// this.handleChangeVisitType();-->
<!--},-->

<!--mounted() {-->
<!--this.getList(1);-->
<!--this.exchangeList = getExchangeDict(this.$store);-->
<!--this.tradeMethods = getDiliveryMethodeDict(this.$store);-->
<!--let Certification = getCertificationCriteriaDict(this.$store);-->
<!--this.formatCertification(Certification);-->
<!--this.formatIndustory(getProjectAreaDict(this.$store));-->
<!--this.formatStatus(getAssetTradeStatusDict(this.$store));-->
<!--this.formatAssetStatus(getAssetStatusDict(this.$store));-->
<!--},-->
<!--};-->
<!--</script>-->
<template>
  <div class="root">
    <div class="divBox">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <div class="container">
            <el-form :inline="true" size="small">
              <el-form-item label="关键字：">
                <el-input v-model="filters.keyword" placeholder="请输入项目名/机构/ID" clearable class="selWidth" />
              </el-form-item>
              <el-form-item label="认证标准：">
                <el-select v-model="filters.certificationCriteria" clearable placeholder="全部" style="width:160px">
                  <el-option v-for="item in optionsCertification" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-form-item>
              <el-form-item label="行业类型：">
                <el-select v-model="filters.industryCode" clearable placeholder="全部" style="width:160px">
                  <el-option v-for="item in optionsIndustryType" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-form-item>
              <el-form-item label="项目领域：">
                <el-select v-model="filters.projectScopeCode" clearable placeholder="全部" style="width:160px">
                  <el-option v-for="item in optionsProjectScope" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-form-item>
              <el-form-item label="资产状态：">
                <el-select v-model="filters.assetsStatus" clearable placeholder="全部" style="width:160px">
                  <el-option v-for="item in optionsAssetStatus" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-form-item>
              <el-form-item label="项目状态：">
                <el-select v-model="filters.projectStatus" clearable placeholder="全部" style="width:160px">
                  <el-option v-for="item in optionsProjectStatus" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-form-item>
              <el-form-item label="方法学：">
                <el-input v-model="filters.methodName" placeholder="方法学名称" clearable style="width:160px" />
              </el-form-item>
              <el-form-item label="交易状态：">
                <el-select v-model="filters.transactionStatus" clearable placeholder="全部" style="width:160px">
                  <el-option v-for="item in optionsTradeStatus" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-form-item>
              <el-form-item label="签发日期：">
                <el-date-picker v-model="filters.issuingDateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="searchList">查询</el-button>
              </el-form-item>
            </el-form>
            <div style="flex:1"></div>
            <el-button type="primary" @click="refresh" size="small">刷新</el-button>
          </div>
        </div>

        <el-table :data="tableData" v-loading="listLoading" style="width:100%" size="mini" highlight-current-row>
          <el-table-column prop="id" label="ID" width="90" />
          <el-table-column prop="projectName" label="项目名称" />
          <el-table-column prop="certificationCriteriaName" label="验证标准" width="140" />
          <el-table-column prop="industryCodeName" label="行业" width="120" />
          <el-table-column prop="total" label="总量(tCO2e)" width="120" align="center" />
          <el-table-column prop="availableAmount" label="可用量(tCO2e)" width="120" align="center" />
          <el-table-column prop="assetsStatusName" label="资产状态" width="120" align="center" />
          <el-table-column label="操作" fixed="right" width="160" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="viewDetail(scope.row.id)">查看</el-button>
              <el-button type="text" size="small" @click="listToExchange(scope.row)">上架</el-button>
            </template>
          </el-table-column>
        </el-table>

        <div class="block" style="padding:12px 16px; text-align:right">
          <el-pagination :page-size="pageSize" :current-page="current" :total="total" layout="total, sizes, prev, pager, next, jumper" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { loadCarbonCreditPageList, delCredit, getCreditData } from '@/api/carbonAssetApi'
import { getAllDict } from '@/api/systemConfig'
import {
  getCertificationCriteriaDict,
  getProjectAreaDict,
  getAssetStatusDict,
  getAssetTradeStatusDict,
  getBusinessDict,
  getIndustryDict,
  getProjectStatusDict
} from '@/config/dictHelper'
export default {
  name: 'carbonCredit',
  data() {
    return {
      listLoading: false,
        tableData: [],
        pageSize: 10,
        current: 1,
        total: 0,
        filters: {
        keyword: '',
        certificationCriteria: '',
        industryCode: '',
        projectScopeCode: '',
        assetsStatus: '',
        transactionStatus: '',
        projectStatus: '',
        methodName: '',
        issuingDateRange: []
      },
      optionsCertification: [],
      optionsIndustryType: [],
      optionsProjectScope: [],
      optionsAssetStatus: [],
      optionsTradeStatus: [],
      optionsProjectStatus: []
    }
  },
  mounted() {
  // 先请求全部字典（若未缓存），再初始化下拉
  getAllDict().then(res => {
    try {
      const payload = res && res.data ? res.data : res
      localStorage.setItem('dircs', JSON.stringify(payload || []))
    } catch (e) {}
  }).catch(() => {}).finally(() => {
    // 初始化字典下拉
    this.optionsCertification = this.formatDict(getCertificationCriteriaDict(this.$store))
    // 优先使用行业（Business），回退到 Industry
    const biz = getBusinessDict(this.$store)
    this.optionsIndustryType = this.formatDict(biz && biz.length ? biz : getIndustryDict(this.$store))
    this.optionsProjectScope = this.formatDict(getProjectAreaDict(this.$store))
    this.optionsAssetStatus = this.formatDict(getAssetStatusDict(this.$store))
    this.optionsTradeStatus = this.formatDict(getAssetTradeStatusDict(this.$store))
    this.optionsProjectStatus = this.formatDict(getProjectStatusDict(this.$store))
    this.getList(1)
  })
  },
  methods: {
    refresh() {
      this.getList(this.current)
    },
    searchList() {
      this.getList(1)
    },
    viewDetail(id) {
      // 跳转到碳信用详情页（路由定义为 /assets/credit/assetDetail）
      this.$router.push({ path: '/assets/credit/assetDetail', query: { id } })
    },
    listToExchange(row) {
      // 简化：跳转到 assetDetail 再进行上架或交易
      this.viewDetail(row.id)
    },
    getList(page) {
      this.listLoading = true
      const data = {
        asc: true,
        current: page || 1,
        size: this.pageSize,
        sortField: ''
      }

      // 将 filters 中非空字段添加到请求体
      const f = this.filters || {}
  // trim 字符串以避免空白字符影响
  if (f.keyword && typeof f.keyword === 'string') f.keyword = f.keyword.trim()
  if (f.methodName && typeof f.methodName === 'string') f.methodName = f.methodName.trim()
  if (f.keyword) data.projectName = f.keyword
  if (f.certificationCriteria) data.certificationCriteria = f.certificationCriteria
  if (f.industryCode) data.industryCode = f.industryCode
  if (f.projectScopeCode) data.projectScopeCode = f.projectScopeCode
  if (f.assetsStatus) data.assetsStatus = f.assetsStatus
  if (f.transactionStatus) data.transactionStatus = f.transactionStatus
  if (f.projectStatus) data.projectStatus = f.projectStatus
  if (f.methodName) data.methodName = f.methodName
      // 处理日期范围（issuingDateRange 为 array），后端期望 Date，补全时间部分以涵盖整天
      if (f.issuingDateRange && f.issuingDateRange.length === 2) {
        let start = f.issuingDateRange[0]
        let end = f.issuingDateRange[1]
        // 如果没有时间部分则补全
        if (typeof start === 'string' && start.indexOf(' ') === -1) start = start + ' 00:00:00'
        if (typeof end === 'string' && end.indexOf(' ') === -1) end = end + ' 23:59:59'
        data.issuingDateStart = start
        data.issuingDateEnd = end
      }
  // 调试日志：打印最终请求体，便于定位哪些字段被发送
  console.debug('carbonCredit:getPageList payload ->', data)
  // 使用碳信用分页接口（静态导入）
      loadCarbonCreditPageList(data).then(res => {
        this.listLoading = false
        if (res && res.code === 200) {
          this.tableData = res.data.records || []
          this.total = parseInt(res.data.total || 0)
          this.current = parseInt(res.data.current || 1)
        } else {
          this.$message.error(res.msg || '获取列表失败')
        }
      }).catch(() => {
        this.listLoading = false
      })
    },

    // 将 dictHelper 返回的字典数据格式化成 {label,value} 数组
    formatDict(list) {
      if (!list) return []
      try {
        // list 可能是数组或对象，支持常见结构
        if (Array.isArray(list)) {
          return list.map(i => ({ label: i.name || i.itemCh || i.item_ch || i.label || i.labelName || i.dictName, value: i.value || i.itemValue || i.item_value || i.dictValue || i.dictCode }))
        }
        // 如果是对象（如本地存储的 map），转成数组
        return Object.keys(list).map(k => ({ label: list[k].name || list[k].itemCh || list[k].label || list[k].dictName || list[k].dict_value, value: list[k].value || list[k].itemValue || list[k].dictValue || k }))
      } catch (e) {
        return []
      }
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.getList(1)
    },
    handleCurrentChange(val) {
      this.current = val
      this.getList(val)
    }
  }
}
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


  cursor: default;
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

.center-vertical {
  margin-top: auto;
  margin-bottom: auto;
}

.require {
  color: red;

  position: relative;
  right: 20px;
}
</style>
