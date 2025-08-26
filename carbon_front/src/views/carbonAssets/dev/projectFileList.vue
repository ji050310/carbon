<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="header">
          <span class="title"
            >项目名称:<span style="margin-left: 10px">{{ title }}</span></span
          >
          <button
            style="
              display: inline-block;
              width: 100px;
              float: right;
              margin-right: 100px;
            "
            class="normal-white-btn"
            @click="onUpload"
          >
            上传文档
          </button>
        </div>
        <div style="margin-top: 20px">
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

            <el-table-column label="编号" align="center" prop="id" width="50">
            </el-table-column>
            <el-table-column
              align="center"
              prop="title"
              label="文档名称"
              min-width="60"
            />
            <el-table-column
              align="center"
              prop="typeName"
              label="文档类型"
              min-width="80"
            />
            <el-table-column
              align="center"
              prop="createdTime"
              label="上传日期"
              min-width="100"
            />
            <el-table-column label="操作" min-width="150" align="center">
              <template slot-scope="scope">
                <a
                  class="list-green-text"
                  @click="onEdit(scope.row.url)"
                  style="margin-left: 10px"
                  >查看</a
                >
                <a
                  class="list-blue-text"
                  @click="onClickChange(scope.row.url)"
                  style="margin-left: 10px"
                  >修改</a
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
import { loadcarbonDoc } from "@/api/carbonAssetApi";
import { editMethod } from "@/api/carbonAssetApi";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { getDocumentTypeDict } from "@/config/dictHelper";
import { cursor } from "@/libs/element-table";
import { stringify } from "querystring";
export default {
  name: "companyPackage",
  components: { selectDropDownBox },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchKeyWord: "",
      selectedDate: "",
      DocumentTypeDict: [], //项目文档类型字典
      selectedType: "", //选中的项目文档
      list: [],
      total: 0,
      title: "",
      current: 1,
      pageCount: 1,
      pageSize: 10,
      methodUrl:
        "https://carbonmsger.feishu.cn/drive/folder/fldcn66yo6D4OoXwZqEMHL6OQSg?from=space_persnoal_filelist",
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
    //当选择框变动
    update() {
      this.selectedType = this.selectedType[0];
      // const data ={
      //   type:this.selectedType,

      // }
      // loadcarbonProjectDoc(data)
      //   .then((res) => {
      //     this.list = res.data.records;
      //     this.total = res.data.total;
      //     this.current = res.data.current;
      //     this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
      //     this.list.map((v) => {
      //       v.type = String(v.type)
      //       v.typeName = this.docType(v.type)
      //     })
      //     //遍历表格数据
      //     // v.checked = false;
      //     // v.statusName = this.statusName(v.status);
      //     // v.methodCode = v.methodCode ? v.methodCode : "--";
      //     // v.carbonIndustryName = v.carbonIndustryName
      //     //   ? v.carbonIndustryName
      //     //   : "--";
      //     // v.certificationCriteria = v.certificationCriteriaName
      //     //   ? v.certificationCriteriaName
      //     //   : "--";
      //   })
      //   .catch((errror) => { });
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
    onClickSearch() {
      const data = {
        asc: true,
        size: this.pageSize,
        searchKey: this.searchKeyWord,
        status: this.selectedStatus,
        industryCode: this.selectedIndustry[0],
        certificationCriteria: this.selectedCertification[0],
      };

      loadMethodList(data)
        .then((res) => {
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            v.checked = false;
            v.statusName = this.statusName(v.status);
            v.methodCode = v.methodCode ? v.methodCode : "--";
            v.carbonIndustryName = v.carbonIndustryName
              ? v.carbonIndustryName
              : "--";
            v.certificationCriteria = v.certificationCriteriaName
              ? v.certificationCriteriaName
              : "--";
          });
        })
        .catch((errror) => {});
    },
    onEdit(url) {
      if (url) {
        openUrlInNewWindow(url);
      } else {
        this.$message.warning("没有对应的url");
      }
    },
    onUpload(row) {
      openUrlInNewWindow(this.methodUrl);
    },
    //修改文档
    onClickChange(url) {
      if (url) {
        openUrlInNewWindow(url);
      } else {
        this.$message.warning("没有对应的url");
      }
    },
    handle(row) {
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
    getList(id) {
      loadcarbonDoc(id)
        .then((res) => {
          this.list = res.data.records;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            v.type = String(v.type);
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
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
  },
  // checkbox end
  created() {
    // this.handleChangeVisitType();
  },
  mounted() {
    let data = this.$route.query;
    this.title = data.title;
    this.getList(data.id);
    this.formatType(getDocumentTypeDict(this.$store));
  },
};
</script>
<style lang="scss" scoped>
.root {
  background: #f2f5f7;
}

.title {
  width: 108px;
  height: 18px;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #242b35;
  line-height: 18px;
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
</style>