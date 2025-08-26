<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="width: 200px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 80px">核证标准</span>
            <div class="selectbox-deliver" />
            <el-cascader placeholder="全部" @change="update" class="selectbox-input" v-model="selectedCertification"
              :options="Certification" clearable>
            </el-cascader>
          </div>
          <div style="margin-left: 16px; width: 200px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 60px">领域</span>
            <div class="selectbox-deliver" />
            <el-cascader placeholder="全部" @change="update" class="selectbox-input" v-model="selectedArea"
              :options="ProjectAreaDict" clearable>
            </el-cascader>
          </div>
          <div style="margin-left: 16px; width: 200px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 60px">行业</span>
            <div class="selectbox-deliver" />
            <el-cascader placeholder="全部" @change="update" class="selectbox-input" v-model="selectedIndustry"
              :options="IndustryList" clearable>
            </el-cascader>
          </div>
          <div style="margin-left: 16px; width: 200px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 60px">状态</span>
            <div class="selectbox-deliver" />
            <el-cascader placeholder="全部" @change="update" class="selectbox-input" v-model="selectedStatus"
              :options="statusList" clearable>
            </el-cascader>
          </div>
          <div style="width: 470px; margin-left: 16px; padding: 0 0 0 0" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 80px">立项日期</span>
            <div class="selectbox-deliver" />
            <el-date-picker v-model="selectDate" type="datetime" @change="update" placeholder="选择开始时间" align="right"
              size="medium" :picker-options="pickerOptions">
            </el-date-picker>
            <el-date-picker v-model="selectEndDate" type="datetime" @change="update" placeholder="选择结束时间" align="right"
              size="medium" :picker-options="pickerOptions">
            </el-date-picker>
          </div>
        </div>
        <div style="clear: both; height: 10px"></div>
        <div class="container" style="margin-top: 0">
          <div style="margin-right: 15px; width: 565px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 120px">按项目搜索</span>
            <div class="selectbox-deliver" />
            <el-input v-model="searchProjectName" placeholder="输入名称或编号" clearable size="medium" @clear="onClickSearch"
              @keyup.enter.native="onClickSearch" />
          </div>
          <button class="light-green-btn" @click="onClickSearch">查询</button>
          <div style="margin-right: 15px; width: 565px; margin-left: 15px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 120px">按方法学搜索</span>
            <div class="selectbox-deliver" />
            <el-input v-model="searchMethodName" placeholder="输入名称" clearable size="medium" @clear="onClickSearch"
              @keyup.enter.native="onClickSearch" />
          </div>
          <button class="light-green-btn" @click="onClickSearch">查询</button>
        </div>
      </div>
      <div>
        <el-table :header-cell-style="{
          background: '#F2F5F7',
          border: '0px solid #DDDDDD',
          color: '#242B35',
          height: '64px',
        }" :show-header="true" :data="list" stripe :row-style="{ height: '64px' }" style="width: 100%">
          <el-table-column min-width="10"></el-table-column>
          <el-table-column label="序号" align="left" min-width="40">
            <template slot-scope="scope"><span>{{ getCurListNo(scope.$index) }}</span></template>
          </el-table-column>
          <el-table-column :show-overflow-tooltip="true" prop="projectName" label="项目名称" min-width="100" />
          <el-table-column align="left" prop="fieldCodeName" label="类型" min-width="80" />
          <el-table-column align="left" prop="certificationCriteriaName" label="核证标准" min-width="100" />
          <el-table-column align="left" prop="projectStatusName" label="状态" min-width="80" />
          <el-table-column align="left" prop="ownerName" label="业主名称" min-width="80" />
          <el-table-column align="left" prop="approvalDate" label="审定日期" min-width="80" />
          <el-table-column align="left" prop="recordFilingDate" label="备案日期" min-width="100" />
          <el-table-column align="left" prop="certifiedDate" label="核证日期" min-width="100" />
          <el-table-column align="left" prop="issuingDate" label="签发日期" min-width="100" />
          <el-table-column label="操作" min-width="150" align="center">
            <template slot-scope="scope">
              <a class="list-green-text" @click="toDetail(scope.row.id)" style="margin-left: 10px">查看</a>
              <a class="list-blue-text" @click="toFile(scope.row)" style="margin-left: 10px">文档</a>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
      <div style="flex-grow: 1" />
      <el-pagination style="margin: auto" background @size-change="handleSizeChange"
        @current-change="handleCurrentChange" :current-page="current" :page-size="pageSize" :page-count="pageCount"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { getFeishuProjectFile } from "@/api/feishuApi";
import { getCarbonProjectDoList } from "@/api/carbonAssetApi";
import { editMethod } from "@/api/carbonAssetApi";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { getCertificationCriteriaDict } from "@/config/dictHelper";
import { getProjectAreaDict } from "@/config/dictHelper";
import { getBusinessDict } from "@/config/dictHelper";
import { setListNo } from "@/libs/public";
import { getProjectStatusDict } from "@/config/dictHelper";
import { cursor } from "@/libs/element-table";
export default {
  name: "companyPackage",
  components: { selectDropDownBox },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      selectedCertification: "", //被选中的标准
      selectedArea: "", //被选中的领域
      selectedIndustry: "", //被选中的行业
      selectedStatus: "", //被选中的状态
      list: [],
      searchProjectName: "", //
      searchMethodName: "", //
      total: 0,
      current: 0,
      pageCount: 1,
      pageSize: 10,
      Certification: [], //核证标准字典
      IndustryList: [], //行业字典
      ProjectAreaDict: [], //项目领域字典
      methodUrl:
        "https://carbonmsger.feishu.cn/drive/folder/fldcn66yo6D4OoXwZqEMHL6OQSg?from=space_persnoal_filelist",
      statusList: [], //项目状态字典,
      value: "",
      selectEndDate: null,
      selectDate: null,
    };
  },
  methods: {
    cellStyle({ row, column, rowIndex, columnIndex }) {
      if (column.label != "操作") {
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
    //查看操作
    toDetail(id) {
      this.$router.push({
        path: "/carbon/projectDetail",
        query: { id: id },
      });
    },
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
    },
    //文档操作
    toFile(data) {
      // if (url) {
      //   openUrlInNewWindow(url);
      // } else {
      //   this.$message.warning("当前项目没有文档url");
      // }
      let id = data.id;
      this.$router.push({
        path: "/carbon/projectFile",
        query: { id: id },
      });
      // let id = data.id;
      // this.$router.push({
      //   path: "/sys/metaregistrydoc",
      //   query: { id: id },
      // });
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
      // debugger
      // this.$router.push({path: '/carbonMethodology/add/'})
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
    update() {
      const data = {
        certificationCriteria: this.selectedCertification
          ? this.selectedCertification[0]
          : "",
        fieldCode: this.selectedArea ? this.selectedArea[0] : "",
        industryCode: this.selectedIndustry ? this.selectedIndustry[0] : "",
        projectStatus: this.selectedStatus ? this.selectedStatus[0] : "",
        initiationDateStart: this.selectDate,
        initiationDateEnd: this.selectEndDate,
      };
      getCarbonProjectDoList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = Number(res.data.total);
          this.current = Number(res.data.current);
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            v.projectStatusName = this.pickProjectName(v.projectStatus);
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        })
        .catch((errror) => { });
    },
    onClickSearch() {
      const data = {
        asc: true,
        size: this.pageSize,
        name: this.searchProjectName,
        current: this.current,
        methodologyName: this.searchMethodName,
        certificationCriteria: this.selectedCertification
          ? this.selectedCertification[0]
          : "",
        fieldCode: this.selectedArea ? this.selectedArea[0] : "",
        industryCode: this.selectedIndustry ? this.selectedIndustry[0] : "",
        projectStatus: this.selectedStatus ? this.selectedStatus[0] : "",
        initiationDateStart: this.selectDate,
      };

      getCarbonProjectDoList(data)
        .then((res) => {
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
        })
        .catch((errror) => { });
    },
    onEdit(url) {
      openUrlInNewWindow(url);
    },
    handle(row, column) {
      if (column.label != "操作") {
        this.toDetail(row.id);
      }

      // debugger
      // openUrlInNewWindow(row.sourceFileUrl)
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
    getList(page) {
      const data = {
        asc: true,
        size: this.pageSize,
        current: this.current,
        certificationCriteria: this.selectedCertification
          ? this.selectedCertification[0]
          : "",
        fieldCode: this.selectedArea ? this.selectedArea[0] : "",
        industryCode: this.selectedIndustry ? this.selectedIndustry[0] : "",
        projectStatus: this.selectedStatus ? this.selectedStatus[0] : "",
        initiationDateStart: this.selectDate,
        initiationDateEnd: this.selectEndDate,
      };
      getCarbonProjectDoList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = Number(res.data.total);
          this.current = Number(res.data.current);
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            for (var i in v) {
              if (v[i] == " ") {
                v[i] = "--";
              }
              v[i] = v[i] ? v[i] : "--";
            }
            v.approvalDate = this.formatTime(v.approvalDate);
            v.initiationDate = this.formatTime(v.initiationDate);
            v.issuingDate = this.formatTime(v.issuingDate);
            v.recordFilingDate = this.formatTime(v.recordFilingDate);
          });
        })
        .catch((errror) => { });
    },
    formatTime(time) {
      var newTime = time.split(" ");
      return newTime[0];
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
    // 格式化标准字典
    formatCertification(data) {
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
        this.Certification.push(CertificationItem);
      });
    },
    // 格式化行业字典
    formatIndustry(data) {
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
        this.IndustryList.push(CertificationItem);
      });
    },
    // 格式化领域字典
    formatArea(data) {
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
        this.ProjectAreaDict.push(CertificationItem);
      });
    },
    // 格式化状态字典
    formatStatus(data) {
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
        this.statusList.push(CertificationItem);
      });
    },
    isProject(status) {
      // if (
      //   status == "0100000002" ||
      //   status == "0100000003" ||
      //   status == "0100000001" ||
      //   status == "0100000004"
      // ) {
      //   return "list-blue-text";
      // } else {
      //   return "afterSubmitPublish";
      // }
      return "afterSubmitPublish";
    },
  },
  // checkbox end
  created() {
    // this.handleChangeVisitType();
  },
  mounted() {
    this.getList(1);
    this.formatCertification(getCertificationCriteriaDict(this.$store));
    this.formatIndustry(getBusinessDict(this.$store));
    this.formatArea(getProjectAreaDict(this.$store));
    this.formatStatus(getProjectStatusDict(this.$store));
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

>>>.el-cascader .el-input .el-input__inner,
>>>.el-cascader .el-input.is-focus .el-input__inner {
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
</style>
