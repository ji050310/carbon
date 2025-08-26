<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="width: 230px" class="selectbox-root">
            <span class="selectbox-hint">文档类型</span>
            <div class="selectbox-deliver" />
            <el-cascader
              style="width: 100px"
              placeholder="全部"
              class="selectbox-input"
              v-model="selectedType"
              :options="DocumentTypeDict"
              clearable
              @clear="update"
              @change="update"
            >
            </el-cascader>
          </div>
          <div style="width: 380px; margin-left: 16px" class="selectbox-root">
            <span class="selectbox-hint" style="width: 100px">完成日期</span>
            <div class="selectbox-deliver" />
            <el-date-picker
              v-model="selectedDate"
              type="date"
              placeholder="选择开始时间"
              align="right"
              size="medium"
              @change="update"
            >
            </el-date-picker>
            <el-date-picker
              v-model="selectedEndDate"
              type="date"
              placeholder="选择结束时间"
              align="right"
              size="medium"
              @change="update"
            >
            </el-date-picker>
          </div>
          <div
            style="margin-left: 16px; margin-right: 15px; width: 350px"
            class="selectbox-root"
          >
            <span class="selectbox-hint" style="width: 120px">按名称搜索</span>
            <div class="selectbox-deliver" />
            <el-input
              v-model="searchKeyWord"
              placeholder="输入名称"
              clearable
              size="medium"
              @clear="onClickSearch"
              @keyup.enter.native="onClickSearch"
            />
          </div>
          <button class="light-green-btn" @click="onClickSearch">查询</button>
        </div>
        <button
          style="margin-top: 0px; margin-bottom: 20px; width: 103px"
          class="normal-white-btn"
          @click="onUpload()"
        >
          +上传文档
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
          :row-style="{ height: '64px' }"
          style="width: 100%"
        >
          <el-table-column min-width="10"></el-table-column>
          <el-table-column label="序号" align="left" width="80">
            <template slot-scope="scope"
            ><span>{{ getCurListNo(scope.$index) }}</span></template
            >
          </el-table-column>
          <el-table-column
            align="left"
            prop="projectRegistryName"
            label="所属项目"
            min-width="180"
          />
          <el-table-column
            align="left"
            prop="title"
            label="文档名称"
            min-width="330"
          />
          <el-table-column
            align="left"
            prop="type"
            label="文档类型"
            min-width="100"
          />

          <el-table-column
            align="left"
            prop="finishTime"
            label="完成日期"
            min-width="100"
          />
          <el-table-column
            align="left"
            prop="updateTime"
            label="更新日期"
            min-width="100"
          />
          <el-table-column label="操作" min-width="150" align="left">
            <template slot-scope="scope">
              <a class="list-blue-text" @click="onEdit(scope.row)">查看</a>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div
      style="margin-top: 30px; margin-bottom: 10px; margin-right: 20px"
      class="pageBox"
    >
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
    <el-dialog title="上传文档" :visible.sync="dialogFormVisible" width="600px">
      <el-form>
        <el-form-item>
          <span class="label"
          >所属项目<span style="color: red; font-size: 16px">*</span></span
          >
          <el-input
            size="medium"
            placeholder="右侧按钮选择项目"
            v-model="projectName"
            disabled
            style="width: 330px"
          ></el-input>
          <el-button
            type="primary"
            style="width: 100px; margin-left: 15px"
            @click="pickProject"
          >选择项目</el-button
          >
        </el-form-item>
        <el-form-item>
          <span class="label"
          >文档类型<span style="color: red; font-size: 16px">*</span></span
          >
          <el-select v-model="form.type" style="width: 330px">
            <el-option
              v-for="item in DocumentTypeDict"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <span class="label"
          >项目文档<span style="color: red; font-size: 16px">*</span></span
          >
          <el-upload
            class="upload-demo"
            ref="upload"
            :action="upLoadParam.url"
            :file-list="fileList"
            :auto-upload="true"
            :limit="1"
            :on-success="uploadSuccess"
            :on-preview="handleFile"
            :headers="{ token: upLoadParam.token }"
            :on-change="handleUploadChange"
          >
            <el-button type="primary">上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="fileSubmit">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="项目列表"
      :visible.sync="dialogTableVisible"
      width="800px"
    >
      <el-table :data="projectList" style="width: 100%" stripe>
        <el-table-column min-width="10"></el-table-column>
        <el-table-column label="编号" align="left" prop="id" min-width="80">
        </el-table-column>
        <el-table-column
          :show-overflow-tooltip="true"
          align="left"
          prop="projectName"
          label="名称"
          min-width="180"
        />
        <el-table-column
          align="left"
          prop="industryCodeName"
          label="行业"
          min-width="120"
        />
        <el-table-column
          align="left"
          prop="certificationCriteriaName"
          label="核证标准"
          min-width="80"
        />
        <el-table-column
          align="left"
          prop="projectStatusName"
          label="状态"
          min-width="60"
        />
        <el-table-column label="操作" min-width="150" align="center">
          <template slot-scope="scope">
            <a class="list-blue-text" @click="pickProjectDone(scope.row)"
            >选择</a
            >
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
        <el-pagination
          style="margin: auto"
          background
          @size-change="handleProjectSizeChange"
          @current-change="handleProjectCurrentChange"
          :current-page="projectCurrent"
          :page-size="projectPageSize"
          :page-count="projectPageCount"
          layout="total, sizes, prev, pager, next, jumper"
          :total="projectTotal"
        >
        </el-pagination>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {
  loadcarbonProjectDoc,
  getCarbonProjectPageList,
  addCarbonProjectFile,
} from "@/api/carbonAssetApi";
import { projectFileManager } from "@/api/systemSetting";
import { getFeiShuUpLoadProjectParams } from "@/api/tenant";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { getDocumentTypeDict } from "@/config/dictHelper";
import { setListNo } from "@/libs/public";
import { error } from "console";
export default {
  name: "projectFile",
  data() {
    return {
      //project
      projectPageSize: 10,
      projectCurrent: 1,
      projectPageCount: null,
      projectTotal: 0,
      dialogTableVisible: false,
      //file
      fileList: [],
      upLoadParam: {
        url: "",
        token: "",
      },
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchKeyWord: "",
      selectedDate: "",
      DocumentTypeDict: [], //项目文档类型字典
      selectedType: "", //选中的项目文档
      list: [],
      projectList: [],
      selectedEndDate: "",
      projectName: "",
      form: {
        carbonProjectId: null,
        title: "",
        type: "",
        url: "",
      },
      registryId: "",
      dialogFormVisible: false,
      total: 0,
      current: 1,
      pageCount: 1,
      pageSize: 10,
      statusList: [], //项目状态字典,
      value: "",
    };
  },
  methods: {
    cellStyle({ row, column, rowIndex, columnIndex }) {
      if (column.label == "编号") {
        return "margin-left:0px;padding:0 0;";
      } else {
        return "cursor:pointer;";
      }
    },
    handleFile(file) {
      let fileUrl = file.response.msg;
      openUrlInNewWindow(fileUrl);
    },
    //上传成功执行
    uploadSuccess(res, file) {
      if (res.code == 200) {
        this.form.url = res.msg;
        this.form.title = file.name;
      } else {
        this.$message.warning("上传失败，请重试");
      }
    },
    //上传改变时执行
    handleUploadChange(file, fileList) {
      this.fileList = fileList;
    },
    handleProjectSizeChange(value) {
      this.projectPageSize = value;
      this.getProjectList(this.projectCurrent);
    },
    handleProjectCurrentChange(value) {
      this.projectCurrent = value;
      this.getProjectList(value);
    },
    //
    pickProject() {
      this.dialogTableVisible = true;
    },
    fileSubmit() {
      if (this.form.title && this.form.carbonProjectId) {
        addCarbonProjectFile(this.form)
          .then((res) => {
            this.$message.success("上传成功");
            this.dialogFormVisible = false;
            this.getList(1);
          })
          .catch((err) => {
            this.$message.error(err);
          });
      } else {
        this.$message.warning("必填项不能为空");
      }
    },
    //选择项目
    pickProjectDone(row) {
      this.form.carbonProjectId = row.id;
      this.projectName = row.projectName;
      this.dialogTableVisible = false;
    },
    //当选择框变动
    update() {
      const data = {
        asc: true,
        type: this.selectedType[0],
        completionDateStart: this.selectedDate,
        completionDateEnd: this.selectedEndDate,
        searchKey: this.searchKeyWord,
        registryId: this.registryId,
      };
      projectFileManager(data).then((res) => {
        this.list = res.records;
        this.total = Number(res.total);
        this.current = Number(res.current);
        this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
        this.list.map((v, i) => {
          v.order = i + 1;
          v.type = String(v.type);
          v.finishTime = v.finishTime
            ? this.formatTime(v.finishTime)
            : v.finishTime;
          v.updateTime = v.updateTime
            ? this.formatTime(v.updateTime)
            : v.updateTime;
          for (var i in v) {
            v[i] = v[i] ? v[i] : "--";
            if (v[i] == " ") {
              v[i] = "--";
            }
          }
        });
      });
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
      // debugger
      // this.$router.push({path: '/carbonMethodology/add/'})
      openUrlInNewWindow(this.methodUrl);
    },
    onUpload() {
      this.dialogFormVisible = true;
    },
    formatTime(time) {
      let newTime = time.split(" ");
      return newTime[0];
    },
    onClickSearch() {
      const data = {
        asc: true,
        size: this.pageSize,
        current: this.current,
        searchKey: this.searchKeyWord,
        type: this.selectedType[0],
        completionDateStart: this.selectedDate,
        completionDateEnd: this.selectedEndDate,
        registryId: this.registryId,
        // status: this.selectedStatus,
        // industryCode: this.selectedIndustry[0],
        // certificationCriteria: this.selectedCertification[0],
        // status: 1,
      };
      let m = this.$message.success("查询中...");
      projectFileManager(data)
        .then((res) => {
          m.close();
          this.list = res.records;
          this.total = Number(res.total);
          this.current = Number(res.current);
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.list.map((v, i) => {
            v.order = i + 1;
            v.type = String(v.type);
            v.typeName = this.docType(v.type);
            v.finishTime = v.finishTime
              ? this.formatTime(v.finishTime)
              : v.finishTime;
            v.updateTime = v.updateTime
              ? this.formatTime(v.updateTime)
              : v.updateTime;
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
          //遍历表格数据
          // v.checked = false;
          // v.statusName = this.statusName(v.status);
          // v.methodCode = v.methodCode ? v.methodCode : "--";
          // v.carbonIndustryName = v.carbonIndustryName
          //   ? v.carbonIndustryName
          //   : "--";
          // v.certificationCriteria = v.certificationCriteriaName
          //   ? v.certificationCriteriaName
          //   : "--";
        })
        .catch((error) => {
          m.close();
          this.$message.error(error);
        });
    },
    onEdit(row) {
      if (row.url != "--") {
        openUrlInNewWindow(row.url);
      } else {
        this.$message.warning("没有对应的url");
      }
    },
    //修改文档
    onClickChange(url) {
      if (url) {
        openUrlInNewWindow(url);
      } else {
        this.$message.warning("没有对应的url");
      }
    },
    handle(row, column) {
      if (column.label != "操作") {
        if (row.url != "--") {
          openUrlInNewWindow(row.url);
        } else {
          this.$message.warning("没有对应的url");
        }
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
    getList(val) {
      console.log(111);
      const data = {
        asc: true,
        size: this.pageSize,
        current: this.current,
        registryId: this.registryId ? this.registryId : "",
        // searchKey: this.searchKeyWord,
        // status: this.selectedStatus,
        // industryCode: this.selectedIndustry[0],
        // certificationCriteria: this.selectedCertification[0],
        // status: 1,
      };
      projectFileManager(data)
        .then((res) => {
          this.list = res.records;
          this.total = Number(res.total);
          this.current = Number(res.current);
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.list.map((v, i) => {
            v.order = i + 1;
            v.type = String(v.type);
            v.finishTime = v.finishTime
              ? this.formatTime(v.finishTime)
              : v.finishTime;
            v.updateTime = v.updateTime
              ? this.formatTime(v.updateTime)
              : v.updateTime;
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
          //遍历表格数据
          // v.checked = false;
          // v.statusName = this.statusName(v.status);
          // v.methodCode = v.methodCode ? v.methodCode : "--";
          // v.carbonIndustryName = v.carbonIndustryName
          //   ? v.carbonIndustryName
          //   : "--";
          // v.certificationCriteria = v.certificationCriteriaName
          //   ? v.certificationCriteriaName
          //   : "--";
        })
        .catch((errror) => {});
    },
    docType(code) {
      switch (code) {
        case "0210000001":
          return "项目设计";
        case "0210000002":
          return "项目审定";
        case "0210000003":
          return "项目监测";
        case "0210000004":
          return "项目核证";
      }
    },
    download(row) {
      if (row.url) {
        openUrlInNewWindow(row.url);
      }
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
    // 格式化项目文档字典
    formatType(data) {
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
        this.DocumentTypeDict.push(CertificationItem);
      });
    },
    getCurListNo(index) {
      let curNo = parseInt(index) + 1;
      let size = this.size || "10";
      let page = this.current - 1;
      let no = setListNo(page, size, curNo);
      return no ? no : 1;
    },
    getProjectList(current) {
      this.projectCurrent = current;
      const data = {
        current: this.projectCurrent,
        size: this.projectPageSize,
        async: true,
      };
      getCarbonProjectPageList(data).then((res) => {
        this.projectList = res.data.records;
        this.projectTotal = Number(res.data.total);
        this.projectCurrent = Number(res.data.current);
        this.projectPageCount = Math.ceil(
          parseInt(res.data.total) / this.pageSize
        );
      });
    },
  },
  // checkbox end
  created() {
    // this.handleChangeVisitType();
  },
  mounted() {
    console.log(11111);
    let data = this.$route.query;
    // if (JSON.stringify(data) != "{}") {
    //   this.registryId = data;
    // }
    // console.log(data.id);
    if (data && data.id) {
      this.registryId = data.id;
      this.update();
    }
    // console.log(data);
    this.getList(1);
    this.getProjectList(1);
    this.upLoadParam = getFeiShuUpLoadProjectParams();
    this.formatType(getDocumentTypeDict(this.$store));
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

.label {
  font-weight: 700;
  width: 80px;
  display: inline-block;
}
</style>
