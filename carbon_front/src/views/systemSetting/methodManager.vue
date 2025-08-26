<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <el-row>
            <el-col :span="6">
              <div class="selectbox-root">
                <span class="selectbox-hint" style="min-width: 80px"
                >核证标准</span
                >
                <div class="selectbox-deliver" />
                <el-cascader
                  style="width: 120px"
                  placeholder="全部"
                  class="selectbox-input"
                  v-model="selectedCertification"
                  :options="Certification"
                  clearable
                  @change="onClickSearch"
                >
                </el-cascader>
              </div>
            </el-col>
            <el-col :span="6">
              <div style="margin-left: 16px" class="selectbox-root">
                <span class="selectbox-hint" style="min-width: 60px">领域</span>
                <div class="selectbox-deliver" />
                <el-cascader
                  placeholder="全部"
                  class="selectbox-input"
                  v-model="selectedField"
                  :options="fieldCodeList"
                  clearable
                  @change="onClickSearch"
                >
                </el-cascader>
              </div>
            </el-col>
            <el-col :span="6">
              <div style="margin-left: 16px" class="selectbox-root">
                <span class="selectbox-hint" style="min-width: 60px">行业</span>
                <div class="selectbox-deliver" />
                <el-cascader
                  placeholder="全部"
                  class="selectbox-input"
                  v-model="selectedIndustry"
                  :options="IndustryList"
                  clearable
                  @change="onClickSearch"
                >
                </el-cascader>
              </div>
            </el-col>
            <el-col :span="6">
              <div style="margin-left: 16px" class="selectbox-root">
                <span class="selectbox-hint" style="min-width: 60px">状态</span>
                <div class="selectbox-deliver" />
                <el-cascader
                  placeholder="全部"
                  class="selectbox-input"
                  v-model="selectedStatus"
                  :options="optionsOnlines"
                  clearable
                  @change="onClickSearch"
                >
                </el-cascader>
              </div>
            </el-col>
          </el-row>
        </div>
        <div style="display: flex">
          <div
            style="margin: 10px 10px 10px 0px; min-width: 600px"
            class="selectbox-root"
          >
            <span class="selectbox-hint" style="min-width: 100px"
            >方法学搜索</span
            >
            <div class="selectbox-deliver" />
            <el-input
              v-model="searchKeyWord"
              placeholder=""
              clearable
              size="medium"
              @keyup.enter.native="onClickSearch"
              @clear="onClickSearch"
            />
          </div>
          <button class="light-green-btn" @click="onClickSearch">查询</button>
        </div>
        <div>
          <button style="margin-top: 0px; margin-bottom: 20px; width: 103px" class="normal-white-btn" @click="onclickAdd">+添加方法学</button>
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
            stripe
            style="width: 100%; cursor: default"
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
            <el-table-column min-width="20"></el-table-column>
            <el-table-column
              label="编号"
              align="left"
              prop="methodCode"
              min-width="60"
            >
            </el-table-column>
            <el-table-column
              :show-overflow-tooltip="true"
              prop="name"
              label="名称"
              align="left"
              min-width="230"
            />
            <el-table-column
              :show-overflow-tooltip="true"
              prop="fieldCodeName"
              label="领域"
              align="left"
              min-width="140"
            />
            <el-table-column
              align="left"
              prop="industryCodeName"
              label="行业"
              min-width="80"
            />
            <el-table-column
              align="left"
              prop="certificationCriteriaName"
              label="核证标准"
              min-width="80"
            />
            <el-table-column
              align="left"
              prop="statusName"
              label="状态"
              min-width="60"
            />
            <el-table-column label="操作" min-width="150" align="center">
              <template slot-scope="scope">
                <a
                  class="list-blue-text"
                  @click="goMethod(scope.row.sourceFileUrl)"
                >查看</a
                >
                <!-- :class="editStyleChange(scope.row.status)" -->
                <a
                  class="list-green-text"
                  style="margin-left: 10px"
                  @click="onEdit(scope.row)"
                >编辑</a
                >
                <a
                  style="margin-left: 10px"
                  :class="publishStyleChange(scope.row.statusCode)"
                  @click="onClickPublish(scope.row.id)"
                >发布</a
                >
                <a
                  style="margin-left: 10px"
                  @click="onClickOffline(scope.row.id)"
                  :class="offlineStyleChange(scope.row.statusCode)"
                >下线</a
                >
                <a
                  style="margin-left: 10px"
                  class="afterSubmitOffline"
                  @click="onClickDelete(scope.row.id)"
                >删除</a
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
        <div style="flex-grow: 1" />
        <span style="margin: auto" class="pageBox-total-num"
        >共{{ total }}条</span
        >
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
      <!-- 添加方法学 -->
      <el-dialog :title="dialogText" :visible.sync="addMethodFormVisible" width="40%">
        <el-row>
          <el-col :span="5"><span class="table-text">方法学编号:<span style="color: red">*</span></span></el-col>
          <el-col :span="19">
            <el-input v-model="methodForm.methodCode" placeholder="请输入方法学编号" autocomplete="off" size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="5"><span class="table-text">方法学字典码:<span style="color: red">*</span></span></el-col>
          <el-col :span="19">
            <el-input v-model="methodForm.dictCode" placeholder="请输入方法学字典码" autocomplete="off" size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="5"><span class="table-text">方法学名称:<span style="color: red">*</span></span></el-col>
          <el-col :span="19">
            <el-input v-model="methodForm.name"  placeholder="请输入方法学名称" autocomplete="off" size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="5"><span class="table-text">领域:<span style="color: red">*</span></span></el-col>
          <el-col :span="19">
            <el-select v-model="methodForm.fieldCode" placeholder="请选择领域" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in fieldCodeList" :key="index" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="5"><span class="table-text">行业:</span></el-col>
          <el-col :span="19">
            <el-select v-model="methodForm.industryCode" placeholder="请选择行业" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in IndustryList" :key="index" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="5"><span class="table-text">类型:</span></el-col>
          <el-col :span="19">
            <el-select v-model="methodForm.fieldChildType" placeholder="请选择类型" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in fieldChildCodeList" :key="index" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="5"><span class="table-text">核证标准:<span style="color: red">*</span></span></el-col>
          <el-col :span="19">
            <el-select v-model="methodForm.certificationCriteria" placeholder="请选择核证标准" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in Certification" :key="index" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="5"><span class="table-text">pdf文档url:<span style="color: red">*</span></span></el-col>
          <el-col :span="19">
            <el-input v-model="methodForm.sourceFileUrl" placeholder="请输入pdf文档url" autocomplete="off" size="medium"></el-input>
          </el-col>
        </el-row>
        <div style="clear: both; height: 20px"></div>
        <el-col :span="5"><span class="table-text">world文档url:</span></el-col>
        <el-col :span="19">
          <el-input v-model="methodForm.wordUrl" placeholder="请输入world文档url"  autocomplete="off" size="medium"></el-input>
        </el-col>
        <div slot="footer" class="dialog-footer" align="rigth">
          <el-button @click="onCancel">取 消</el-button>
          <el-button @click="saveMothod" type="primary">保存</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import { getEscarbonMethodologyByKeyword,addCarbonMethodology,updateCarbonMethodology,synContentCarbonMethodology } from "@/api/carbonAssetApi";
import { editMethod } from "@/api/carbonAssetApi";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { getCertificationCriteriaDict } from "@/config/dictHelper";
import { getBusinessDict } from "@/config/dictHelper";
import { getMethodStatusDict, getProjectAreaDict,getProjectTypeDict } from "@/config/dictHelper";
import { cursor } from "@/libs/element-table";
export default {
  name: "companyPackage",
  components: { selectDropDownBox },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchKeyWord: "",
      selectedCertification: "", //被选中的标准
      selectedIndustry: "", //被选中的行业
      selectedStatus: "", //被选中的状态
      fieldCodeList: [], //领域字典
      selectedField: "", //领域
      list: [],
      total: 0,
      current: 0,
      pageCount: 1,
      pageSize: 10,
      Certification: [], //核证标准字典
      IndustryList: [], //行业字典
      methodUrl:
        "https://carbonmsger.feishu.cn/drive/folder/fldcnaUxGTDbmMBqgOuMUkD5Pxh",
      optionsOnlines: [],
      value: "",
      /* 添加方法学 */
      fieldChildCodeList: [],//项目类型
      addMethodFormVisible:false,
      dialogText:"添加方法学",//
      methodForm:{
        id:"",
        dictCode:"",
        fieldCode:'',//领域
        certificationCriteria:'',//	核证标准（字典：007）
        industryCode:'',//	行业编码（字典：002）
        methodCode:'',//	方法学编码
        name:'',//	方法学名称
        pdfUrl:"",//	飞书pdf路径
        wordUrl:'',//	飞书word路径
        fieldChildType:"",//项目类型
        sourceFileUrl:"",////	飞书pdf路径
      }
    };
  },
  methods: {
    cellStyle({ row, column, rowIndex, columnIndex }) {},
    //判断是否发布，若发布了则修改样式
    editStyleChange(status) {
      return "afterSubmitEdit";
      // if (status == 1) {
      //   return "afterSubmitEdit";
      // } else {
      //   return "list-green-text";
      // }
    },
    publishStyleChange(status) {
      if (status == "0450000002") {
        return "afterSubmitPublish";
      } else {
        return "list-blue-text";
      }
    },
    offlineStyleChange(status) {
      if (status == "0450000002") {
        return "list-red-text";
      } else {
        return "afterSubmitOffline";
      }
    },
    onclickAdd() {
      this.dialogText = "添加方法学";
      this.methodForm["id"] = "";
      this.methodForm["dictCode"] = "";
      this.methodForm["fieldCode"] = "";
      this.methodForm["certificationCriteria"] = "";
      this.methodForm["industryCode"] = "";
      this.methodForm["methodCode"] = "";
      this.methodForm["name"] = "";
      this.methodForm["pdfUrl"] = "";
      this.methodForm["wordUrl"] = "";
      this.methodForm["fieldChildType"] = "";
      this.methodForm["sourceFileUrl"] = "";
      this.addMethodFormVisible = !this.addMethodFormVisible;

    },
    syncContentCarbonMethodology(data){
      let datas = {
        methodCode:data.methodCode,
        wordUrl:data.wordUrl,
      }
      synContentCarbonMethodology(datas).then(res=>{
        this.getList(1)
        this.$message.success("操作成功")
      })
    },
    onCancel(){
      this.addMethodFormVisible = false;
    },
    onclickSync(){
      this.$message("开发中!")
    },
    // 发布
    onClickPublish(row_id) {
      const data = {
        id: row_id,
        statusCode: "0450000002",
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
    // 删除
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
      //20220816 id 改传 methodId
      // 20220922 改传 id
      const data = {
        id: row_id,
        statusCode: "0450000003",
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
        // searchKey: this.searchKeyWord,
        statusCode: this.selectedStatus[0],
        fieldCode: this.selectedField[0],
        industryCode: this.selectedIndustry[0],
        certificationCriteria: this.selectedCertification[0],
      };
      if (this.searchKeyWord) {
        data["searchKey"] = this.searchKeyWord;
      }
      let m = this.$message.success("查询中...");
      getEscarbonMethodologyByKeyword(data)
        .then((res) => {
          // this.list = res.data.records;
          m.close();
          if(res.data){
            this.list = res.data.data;
            this.total = parseInt(res.data.total);
            this.current = res.data.current;
            this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
            this.list.map((v) => {
              //遍历表格数据
              v.checked = false;
              v.statusName = this.statusName(v.statusCode);
              for (var i in v) {
                v[i] = v[i] ? v[i] : "--";
              }
            });
          }
        })
        .catch((error) => {
          m.close();
          this.$message.error(error);
        });
    },
    onEdit(row){
      this.dialogText = "修改方法学";
      this.addMethodFormVisible = true;
      let datas = {...row};
      this.methodForm = datas;
      console.log("this.methodForm",this.methodForm);
    },
    saveMothod(){
      if(!this.methodForm["methodCode"]) {
        return this.$message("请输入方法学编码!")
      }
      if(!this.methodForm["name"]) {
        return this.$message("请输入方法学名称!")
      }
      if(!this.methodForm["fieldCode"]) {
        return this.$message("请选择方法学领域!")
      }
      if(!this.methodForm["certificationCriteria"]) {
        return this.$message("请选择方法学核证标准!")
      }
      if(!this.methodForm["sourceFileUrl"]) {
        return this.$message("请输入方法学飞书pdf路径!")
      }

      let data = {...this.methodForm};
      if(this.methodForm.id){//调用修改接口
        for (let i in data) {
          if(data[i] == "--"){
            data[i] = ""
          }
        }
        data.createdTime=null;
        data.pdfUrl = this.methodForm.sourceFileUrl
        updateCarbonMethodology(data).then(res=>{
          if(res && res.code == 200){
            this.$message.success("操作成功！")
            this.addMethodFormVisible = false;
            if(this.methodForm["wordUrl"]){
              this.syncContentCarbonMethodology(this.methodForm)
            }
          }
        }).catch(err=>{
          this.$message.error(err.msg)
        })
      }else{//调用添加方法学接口
        delete data["id"]
        data.pdfUrl = data.sourceFileUrl;
        addCarbonMethodology(data).then(res=>{
          if(res && res.code == 200){
            this.$message.success("添加成功！")
            this.addMethodFormVisible = false
            // if(this.methodForm["wordUrl"]){
            //   this.syncContentCarbonMethodology(this.methodForm)
            // }
          }
        }).catch(err=>{
          this.$message.error(err.msg)
        })
      }
    },

    goMethod(url) {
      openUrlInNewWindow(url);
    },
    //列表项点击事件，，跳转到对应url
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
      if (status == "0450000002") {
        return "已发布";
      }
      if (status == "0450000001") {
        return "未发布";
      }
      if (status == "0450000003") {
        return "已下线";
      }
    },
    getList(page) {
      const data = {
        asc: true,
        size: this.pageSize,
        current: this.current,
        // searchKey: this.searchKeyWord,
        statusCode: this.selectedStatus[0],
        industryCode: this.selectedIndustry[0],
        fieldCode: this.selectedField[0],
        certificationCriteria: this.selectedCertification[0],
      };
      if (this.searchKeyWord) {
        data["searchKey"] = this.searchKeyWord;
      }
      getEscarbonMethodologyByKeyword(data)
        .then((res) => {
          // this.list = res.data.records;
          this.list = res.data.data;
          this.total =parseInt(res.data.total);
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            v.statusName = this.statusName(v.statusCode);
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
    update() {
      const data = {
        asc: true,
        size: this.pageSize,
        // searchKey: this.searchKeyWord,
        industryCode: this.selectedIndustry[0],
        fieldCode: this.selectedField[0],
        certificationCriteria: this.selectedCertification[0],
        current: this.current,
        statusCode: this.selectedStatus[0],
      };
      if (this.searchKeyWord) {
        data["searchKey"] = this.searchKeyWord;
      }
      getEscarbonMethodologyByKeyword(data)
        .then((res) => {
          // this.list = res.data.records;
          this.list = res.data.data;
          this.total = res.data.total;
          this.current = res.data.current;
          this.pageCount = Math.ceil(parseInt(res.data.total) / this.pageSize);
          this.list.map((v) => {
            v.statusName = this.statusName(v.statusCode);
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
            }
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
    formatStatus(data) {
      let list = [];
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
        list.push(CertificationItem);
      });
      return list;
    },
    // 格式化方法学状态字典
    formatMethodStatus(data) {
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
        this.optionsOnlines.push(CertificationItem);
      });
    },
    // 格式化领域字典
    formatFieldCode(data) {
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
        this.fieldCodeList.push(CertificationItem);
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

  },
  // checkbox end
  created() {
    // this.handleChangeVisitType();
  },
  mounted() {
    this.getList(1);
    this.formatIndustry(getBusinessDict(this.$store));
    this.formatMethodStatus(getMethodStatusDict(this.$store));
    this.formatFieldCode(getProjectAreaDict(this.$store));
    this.Certification = this.formatStatus(getCertificationCriteriaDict(this.$store));
    this.fieldChildCodeList = this.formatStatus(getProjectTypeDict(this.$store));
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
/deep/ .el-dialog__header{
  background: #fcfcfc;
}
/deep/ .el-dialog__title{
  color: #000 !important;
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
.require {
  color: red;
  font-size: 16px;
  position: relative;
  right: 20px;
}
.table-text {
  position: relative;
  top: 10px;
  margin-left: 10px;
}
</style>

