<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 80px">核证标准</span>
            <div class="selectbox-deliver" />
            <el-cascader placeholder="全部" class="selectbox-input" @change="update" v-model="selectedCertification"
                         :options="Certification" clearable>
            </el-cascader>
          </div>
          <div style="margin-left: 16px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 60px">领域</span>
            <div class="selectbox-deliver" />
            <el-cascader placeholder="全部" class="selectbox-input" v-model="selectedArea" @change="update"
                         :options="ProjectAreaDict" clearable>
            </el-cascader>
          </div>
          <div style="margin-left: 16px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 60px">行业</span>
            <div class="selectbox-deliver" />
            <el-cascader placeholder="全部" @change="update" class="selectbox-input" v-model="selectedIndustry"
                         :options="IndustryList" clearable>
            </el-cascader>
          </div>
          <div style="margin-left: 16px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 60px">状态</span>
            <div class="selectbox-deliver" />
            <el-cascader placeholder="全部" @change="update" class="selectbox-input" v-model="selectedStatus"
                         :options="statusList" clearable>
            </el-cascader>
          </div>
          <div style="margin-left: 16px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 80px">立项日期</span>
            <div class="selectbox-deliver" />
            <el-date-picker v-model="selectStartDate" @change="update" type="datetime" placeholder="选择开始时间"
                            align="right" size="medium">
            </el-date-picker>
            <el-date-picker v-model="selectEndDate" @change="update" type="datetime" placeholder="选择结束时间" align="right"
                            size="medium">
            </el-date-picker>
          </div>
        </div>
        <div style="clear: both; height: 10px"></div>
        <div class="container" style="margin-top: 0">
          <div style="margin-right: 15px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 120px">按项目搜索</span>
            <div class="selectbox-deliver" />
            <el-input class="selectbox-input" v-model="searchProjectName" clearable placeholder="输入名称"
                      @clear="onClickSearch" @keyup.enter.native="onClickSearch" />
          </div>
          <button class="light-green-btn" @click="onClickSearch">查询</button>
          <div style="margin-right: 15px; margin-left: 15px" class="selectbox-root">
            <span class="selectbox-hint" style="min-width: 120px">按方法学搜索</span>
            <div class="selectbox-deliver" />
            <el-input class="selectbox-input" v-model="searchMethodName" placeholder="输入名称" @clear="onClickSearch"
                      @keyup.enter.native="onClickSearch" clearable />
          </div>
          <button class="light-green-btn" @click="onClickSearch">查询</button>
        </div>
      </div>
      <div>
        <button style="margin-top: 0px; margin-bottom: 20px; width: 103px" class="normal-white-btn" @click="onclickAdd">
          +创建项目
        </button>
        <el-table :header-cell-style="{
          background: '#F2F5F7',
          border: '0px solid #DDDDDD',
          color: '#242B35',
          height: '64px',
        }" :show-header="true" :data="list" stripe :row-style="{ height: '64px' }" style="width: 100%">
          <!-- <el-table-column min-width="40" label="" align="center" width="40">
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
            <template slot-scope="scope"><span>{{ getCurListNo(scope.$index) }}</span></template>
          </el-table-column>
          <el-table-column :show-overflow-tooltip="true" prop="projectName" label="项目名称" min-width="100" align="left" />
          <el-table-column align="left" prop="certificationCriteriaName" label="核证标准" min-width="80" />
          <el-table-column align="left" prop="fieldCodeName" label="领域" min-width="80" />
          <el-table-column align="left" prop="industryCodeName" label="行业" min-width="110" />
          <el-table-column align="left" prop="projectStatusName" label="状态" min-width="50" />
          <el-table-column align="left" prop="address" label="项目所在地" min-width="100" />
          <el-table-column align="left" prop="ownerName" label="业主名称" min-width="100" />
          <el-table-column align="left" prop="initiationDate" label="立项日期" min-width="80" />
          <el-table-column label="操作" min-width="150" align="center">
            <template slot-scope="scope">
              <a class="list-blue-text" @click="toDetail(scope.row.id)" style="margin-left: 10px">查看</a>
              <a :class="editStyleChange(scope.row.projectStatus)"
                 @click="onEdit(scope.row.projectStatus, scope.row.id)" style="margin-left: 10px">修改</a>
              <a :class="publishStyleChange(scope.row.projectStatus)"
                 @click="onClickDelete(scope.row.projectStatus, scope.row.id)" style="margin-left: 10px">删除</a>
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
</template>
<script>
import { getCarbonProjectPageList } from "@/api/carbonAssetApi";
import { editMethod } from "@/api/carbonAssetApi";
import { deleteCarbonProject } from "@/api/carbonAssetApi";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { getCertificationCriteriaDict } from "@/config/dictHelper";
import { getProjectAreaDict } from "@/config/dictHelper";
import { getBusinessDict } from "@/config/dictHelper";
import { getProjectStatusDict } from "@/config/dictHelper";
import { setListNo } from "@/libs/public";
import { cursor } from "@/libs/element-table";
import { log } from "console";
export default {
  name: "companyPackage",
  components: { selectDropDownBox },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchProjectName: "",
      searchMethodName: "",
      selectedCertification: "", //被选中的标准
      selectedArea: "", //被选中的领域
      selectedIndustry: "", //被选中的行业
      selectedStatus: "", //被选中的状态
      selectStartDate: "",
      selectEndDate: "",

      list: [],
      total: 0,
      current: 1,
      pageCount: 1,
      pageSize: 10,
      Certification: [], //核证标准字典
      IndustryList: [], //行业字典
      ProjectAreaDict: [], //项目领域字典
      methodUrl:
        "https://carbonmsger.feishu.cn/drive/folder/fldcn66yo6D4OoXwZqEMHL6OQSg?from=space_persnoal_filelist",
      statusList: [], //项目状态字典,
      value: "",
    };
  },
  methods: {
    cellStyle({ row, column, rowIndex, columnIndex }) {
      if (column.label == "编号" || column.label == "操作") {
        return "margin-left:0px;padding:0 0;";
      } else {
        return "cursor:pointer;";
      }
    },
    //判断是否发布，若发布了则修改样式
    editStyleChange(status) {
      if (status == "0100000001") {
        return "list-yello-text"
      } else {
        return "afterIssueEdit";
      }
    },
    publishStyleChange(status) {
      if (status == "0100000001") {
        return "list-red-text";
      } else {
        return "afterSubmitOffline";
      }
    },
    update() {
      const data = {
        certificationCriteria: this.selectedCertification
          ? this.selectedCertification[0]
          : "",
        fieldCode: this.selectedArea ? this.selectedArea[0] : "",
        industryCode: this.selectedIndustry ? this.selectedIndustry[0] : "",
        projectStatus: this.selectedStatus ? this.selectedStatus[0] : "",
        initiationDateStart: this.selectStartDate,
        initiationDateEnd: this.selectEndDate,
      };
      getCarbonProjectPageList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
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
    offlineStyleChange(status) {
      if (status == 1) {
        return "list-red-text";
      } else {
        return "afterSubmitOffline";
      }
    },
    onEdit(status, id) {
      if (status == "0100000001") {
        this.$router.push({
          path: "/carbon/projectCreate/projectEdit",
          query: { id: id, page: this.current },
        });
      } else {
        this.$message.warning("当前项目已立项，不可修改！");
      }
    },
    onclickAdd() {
      this.$router.push({ path: "/carbon/projectCreate/projectAdd" });
    },
    pickProjectName(status) {
      if (status == "0100000001") {
        return "已创建";
      } else {
        return "已立项";
      }
    },
    onClickDelete(status, id) {
      if (status[9] < 6) {
        this.$confirm("项目删除不可复原，请谨慎操作").then(() => {
          deleteCarbonProject(id).then(
            (res) => {
              this.$message.success("删除成功");
              this.getList(this.current);
            },
            (err) => {
              this.$message.error("删除失败");
            }
          );
        });
      } else {
        this.$message.warning("此项目已立项，不可删除");
      }
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
        initiationDateStart: this.selectStartDate,
        initiationDateEnd: this.selectEndDate
      };

      getCarbonProjectPageList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
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
    toDetail(id) {
      let routeData = this.$router.resolve({ path: "/carbon/projectCreate/projectDetail", query: { id: id },});
      // openUrlInNewWindow(routeData.href);
      this.$router.push({
        path: routeData.href,
        query: { id: id },
      });
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
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
    },
    // 监听页面宽度变化，刷新表格
    // handleResize() {
    //   if (this.infoList) this.$refs.visitChart.handleResize();
    // },
    getList(page) {
      const data = {
        asc: true,
        size: this.pageSize,
        name: this.searchProjectName,
        current: page,
        methodologyName: this.searchMethodName,
        certificationCriteria: this.selectedCertification
          ? this.selectedCertification[0]
          : "",
        fieldCode: this.selectedArea ? this.selectedArea[0] : "",
        industryCode: this.selectedIndustry ? this.selectedIndustry[0] : "",
        projectStatus: this.selectedStatus ? this.selectedStatus[0] : "",
        initiationDateStart: this.selectStartDate,
        initiationDateEnd: this.selectEndDate
      };
      getCarbonProjectPageList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            // v.projectStatusName = this.pickProjectName(v.projectStatus);
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
    addView(){
      this.$store.dispatch('tagsView/addView', this.$route);
      console.log("this.$route",this.$store.state.tagsView.cachedViews);
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
          value: "",
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
          value: "",
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
          value: "",
          label: "",
        };
        if (v.name == "全部") {
          CertificationItem.label = v.name;
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.statusList.push(CertificationItem);
        this.statusList.splice(1, 0, this.statusList[this.statusList.length - 1])
        // this.statusList.sort((a, b) => b - a)
      });
    },
  },
  // checkbox end
  created() {
    // this.handleChangeVisitType();
  },
  mounted() {
    this.addView();
    let page = this.$route.query;
    if (page) {
      this.getList(page.page);
    } else {
      this.getList(1);
    }
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

.selectbox-root {
  .el-date-picker {
    border: none;
  }
}
</style>
