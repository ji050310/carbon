<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <div style="width: 390px" class="selectbox-root">
            <span class="selectbox-hint" style="width: 100px">字典名称</span>
            <div class="selectbox-deliver" />
            <el-input v-model="searchDictName" placeholder="请输入字典名称" clearable size="medium"
                      @keyup.enter.native="onClickSearchByName" @clear="onClickSearchByName" />
          </div>
          <button style="margin-left: 16px" class="light-green-btn" @click="onClickSearchByName">
            查询
          </button>
          <div style="width: 390px; margin-left: 20px" class="selectbox-root">
            <span class="selectbox-hint" style="width: 100px">字典编号</span>
            <div class="selectbox-deliver" />
            <el-input v-model="searchDictCode" placeholder="请输入字典编号" clearable size="medium"
                      @keyup.enter.native="onClickSearch" @clear="onClickSearch" />
          </div>
          <!-- <div style="flex-grow:1"/> -->
          <button style="margin-left: 16px" class="light-green-btn" @click="onClickSearch">
            查询
          </button>
        </div>
        <div>
          <button style="margin-top: 0px; margin-bottom: 20px" class="normal-white-btn" @click="addDict">
            +添加
          </button>
          <button style="margin-top: 0px; margin-bottom: 20px; margin-left: 20px" class="normal-white-btn">
            导出
          </button>
          <button style="margin-top: 0px; margin-bottom: 20px; margin-left: 20px" class="normal-white-btn">
            导入
          </button>
          <el-table :header-cell-style="{
            background: '#F2F5F7',
            border: '0px solid #DDDDDD',
            color: '#242B35',
            height: '64px',
          }" :show-header="true" :data="list" stripe :row-style="{ height: '64px' }" :cell-style="cellStyle"
                    style="width: 100%">
            <!--             <el-table-column label="" align="center">
              <template slot="header" slot-scope="{column}">
                  <el-checkbox v-model="column.checked" :indeterminate="indeterminateFlag" :checked="allchecked" label="" @change="updateAllSelected"></el-checkbox>
              </template>
              <template slot-scope="scope">
                  <el-checkbox  @change="signCheckChange" v-model="scope.row.checked"></el-checkbox>
              </template>
            </el-table-column> -->
            <el-table-column min-width="1%"></el-table-column>
            <el-table-column prop="dictName" label="字典名称" min-width="10%" align="left" />
            <el-table-column align="left" prop="dictCode" label="字典编码" min-width="10%" />
            <el-table-column align="left" prop="description" label="描述" min-width="20%" />
            <el-table-column align="left" prop="createdTime" label="创建时间" min-width="10%" />
            <el-table-column align="left" prop="updatedTime" label="更新时间" min-width="10%" />
            <el-table-column label="操作" min-width="20%" fixed="right" align="center">
              <template slot-scope="scope">
                <template v-if="scope.row.isDel">
                  <span>-</span>
                </template>
                <template v-else>
                  <a class="list-blue-text" style="margin-left: 10px" @click="onEdit(scope.row, true)">编辑</a>
                  <a class="list-blue-text" style="margin-left: 10px"
                     @click="editDictConfig(scope.row.dictCode)">字典配置</a>
                  <a class="list-red-text" style="margin-left: 10px" @click="onClickDelete(scope.row.id)">删除</a>
                </template>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
        <div style="flex-grow: 1" />
        <!-- <a style="margin: auto" class="pageBox-total-num">共{{ total }}条</a> -->
        <el-pagination style="margin: auto" background @size-change="handleSizeChange"
                       @current-change="handleCurrentChange" :current-page="current" :page-size="pageSize" :page-count="pageCount"
                       layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </div>
      <!-- Form -->

      <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="字典类型" :label-width="60">
            <el-input v-model="form.dictName" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="字典编码" :label-width="60">
            <el-input v-model="form.dictCode" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="描述" :label-width="60">
            <el-input v-model="form.description" autocomplete="off" size="medium"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" class="normal-white-btn">取 消</el-button>
          <el-button type="primary" @click="submit" class="light-green-btn">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 单个字典编码list -->
      <el-dialog title="字典编码" :visible.sync="dictListShow">
        <el-row>
          <el-col :span="10">
            <div style="" class="selectbox-root">
              <a class="selectbox-hint" style="width: 100px">字典名称</a>
              <div class="selectbox-deliver" />
              <el-input v-model="itemCh" placeholder="请输入字典名称" clearable size="medium" @clear="onClickSearchSingDict"
                        @keyup.enter.native="onClickSearchSingDict" />
            </div>
          </el-col>
          <el-col :span="10">
            <div style="margin-left: 15px" class="selectbox-root">
              <a class="selectbox-hint" style="width: 100px">字典编号</a>
              <div class="selectbox-deliver" />
              <el-input v-model="itemValue" placeholder="请输入字典编号" clearable size="medium" @clear="onClickSearchSingDict"
                        @keyup.enter.native="onClickSearchSingDict" />
            </div>
          </el-col>
          <el-col :span="4">
            <button style="margin-left: 16px; margin-top: 3px" class="light-green-btn" @click="onClickSearchSingDict">
              查询
            </button>
          </el-col>
        </el-row>

        <button style="margin-top: 10px" class="normal-white-btn" @click="showChildAdd">
          +添加
        </button>
        <el-table :data="dictList" stripe>
          <el-table-column prop="itemCh" label="字典值" width="180" align="left">
          </el-table-column>
          <el-table-column prop="itemValue" label="字典编码" width="100"></el-table-column>
          <el-table-column prop="description" label="描述" width="200"></el-table-column>
          <el-table-column label="操作" min-width="130" fixed="right" align="center">
            <template slot-scope="scope">
              <template>
                <a class="list-blue-text" style="margin-left: 10px" @click="onEditSingDict(scope.row)">编辑</a>
                <a class="list-red-text" style="margin-left: 10px" @click="delSingDict(scope.row.id)">删除</a>
              </template>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
          <div style="flex-grow: 1" />
          <!-- <a style="margin: auto" class="pageBox-total-num"
            >共{{ dictPageCount }}页</a
          > -->
          <el-pagination style="margin: auto" background @size-change="handleDictSizeChange"
                         @current-change="handleDictCurrentChange" :current-page="dictCurrent" :page-size="dictPageSize"
                         :page-count="dictPageCount" layout="total, sizes, prev, pager, next, jumper" :total="dictTotal">
          </el-pagination>
        </div>
      </el-dialog>
      <!-- 编辑单个字典值 -->
      <el-dialog :title="title" :visible.sync="dictShow" width="30%">
        <el-form :model="form">
          <el-form-item label="名称" :label-width="60">
            <el-input v-model="dict.itemCh" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="字典编码" :label-width="60">
            <el-input v-model="dict.itemValue" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="父级字典编码" :label-width="60">
            <el-input v-model="dict.dictCode" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="描述" :label-width="60">
            <el-input v-model="dict.description" autocomplete="off" size="medium"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dictShow = false" class="normal-white-btn">取 消</el-button>
          <el-button type="primary" @click="onClickEditSingDict" class="light-green-btn">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 添加字典 -->
      <el-dialog title="添加字典" :visible.sync="addDictShow" width="30%">
        <el-form :model="form">
          <el-form-item label="字典名称" :label-width="60">
            <el-input v-model="form.dictName" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="字典编码" :label-width="60">
            <el-input v-model="form.dictCode" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="描述" :label-width="60">
            <el-input v-model="form.description" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="字典类型" :label-width="60">
            <el-select v-model="form.type" placeholder="请选择" size="medium">
              <el-option label="字符串" value="0"> </el-option>
              <el-option label="数字" value="1"> </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDictShow = false" class="normal-white-btn">取 消</el-button>
          <el-button type="primary" @click="addSubmit" class="light-green-btn">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 添加子字典 -->
      <el-dialog title="添加字典" :visible.sync="addDictChildShow" width="30%">
        <el-form :model="childForm">
          <el-form-item label="字典名称" :label-width="60">
            <el-input v-model="childForm.itemCh" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="字典编码" :label-width="60">
            <el-input v-model="childForm.itemValue" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="父级字典编码" :label-width="60">
            <el-input v-model="childForm.dictCode" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="描述" :label-width="60">
            <el-input v-model="childForm.description" autocomplete="off" size="medium"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDictChildShow = false" class="normal-white-btn">取 消</el-button>
          <el-button type="primary" @click="onClickAddChildDict" class="light-green-btn">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import * as systemAdminApi from "@/api/systemadmin.js";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { getAllDiction } from "@/config/dictHelper";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { cursor } from "@/libs/element-table";
export default {
  name: "companyPackage",
  components: { selectDropDownBox },
  data() {
    return {
      isAdd: false, //判断是添加操作还是修改操作
      reRender: true, // 重新渲染列表使用
      searchDictName: "",
      searchDictCode: "",
      list: [],
      total: 0,
      current: 1,
      pageCount: 1,
      pageSize: 10,
      value: "",
      dialogFormVisible: false,
      dictListShow: false,
      dictList: [], //弹出框字典编码list
      dictListCode: "",
      dictShow: false,
      addDictShow: false, //添加字典弹框
      addDictChildShow: false, //子字典添加
      dict: {
        itemCh: "",
        id: 0,
        itemValue: "",
        dictCode: "",
        description: "",
      },
      form: {
        dictName: "",
        dictCode: "",
        description: "",
        id: "",
        type: "0",
      },
      childForm: {
        dictCode: "",
        itemCh: "",
        itemValue: "",
        description: "",
        status: 0,
      },
      title: "",
      dictTotal: 0,
      dictPageCount: 0,
      dictCurrent: 1, //子字典列表当前页码
      dictPagesize: 10, //子字典页面大小
      dictCode: "", //当前的字典
      itemValue: "", //字典编码搜索关键词
      itemCh: "",
    };
  },
  methods: {
    cellStyle(data) {
      // return cursor(data);
    },
    getAllDictionary() {
      getAllDiction(this.$store);
    },
    addDict() {
      this.addDictShow = true;
      this.form.dictName = "";
      this.form.id = "";
      this.form.dictCode = "";
      this.form.description = "";
      this.form.type = "0";
    },
    addSubmit() {
      if (this.form.type && this.form.dictCode && this.form.dictName) {
        systemAdminApi.addDict(this.form).then(
          (res) => {
            this.$message.success("添加成功");
            this.getAllDictionary();
            this.addDictShow = false;
            this.getList(1);
          },
          (err) => {
            this.$message.errror(err.msg);
          }
        );
      } else {
        this.$message.warning("字典名称、编码、类型不能为空");
      }
    },
    //删除单个字典
    delSingDict(id) {
      this.$confirm("确认删除当前字典").then(() => {
        systemAdminApi.delChildDict(id).then(
          (res) => {
            this.$message.success("删除成功");
            this.getAllDictionary();
            this.loadSingleDictList(1);
          },
          (err) => {
            this.$message.error(err.msg);
          }
        );
      });
    },
    onClickSearchSingDict() {
      const data = {
        current: 1,
        size: this.dictPagesize,
        itemCh: this.itemCh,
        itemValue: this.itemValue,
        dictCode: this.dictCode,
        asc: true,
      };
      systemAdminApi.getSingleDictList(data).then(
        (res) => {
          this.dictList = res.records;
          this.dictTotal = Number(res.total);
          this.dictCurrent = Number(res.current);
          this.dictPageCount = Math.ceil(
            parseInt(this.dictTotal) / this.dictPagesize
          );
          this.dictList.map((v) => {
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        },
        (err) => { }
      );
    },
    onClickSearchByName() {
      const para = {
        dictName: this.searchDictName,
      };
      systemAdminApi
        .getDictList(para)
        .then((data) => {
          this.list = data.records;
          this.total = Number(data.total);
          this.current = Number(data.current);
          this.pageCount = Math.ceil(parseInt(this.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        })
        .catch((errror) => {
          // debugger
        });
    },
    onClickSearch() {
      const para = {
        dictCode: this.searchDictCode,
      };
      systemAdminApi
        .getDictList(para)
        .then((data) => {
          this.list = data.records;
          this.total = data.total;
          this.current = data.current;
          this.pageCount = Math.ceil(parseInt(this.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        })
        .catch((errror) => {
          // debugger
        });
    },
    //编辑字典类型
    onEdit(row, bool) {
      this.dialogFormVisible = bool;
      if (bool) {
        this.form.dictName = row.dictName;
        this.form.id = row.id;
        this.form.dictCode = row.dictCode;
        this.form.description = row.description;
        this.form.type = row.type == "--" ? 0 : row.type;
      } else {
        this.dict.name = row.name;
        this.dict.value = row.value;
      }
    },
    submit() {
      systemAdminApi.editDictList(this.form).then(
        (res) => {
          this.$message.success("修改成功");
          this.getAllDictionary();
          this.dialogFormVisible = false;
          this.getList(this.current);
        },
        (err) => {
          this.$message.errror("修改失败");
        }
      );
    },
    //编辑单个字典
    onEditSingDict(row) {
      this.dictShow = true;
      this.dict.id = row.id;
      this.dict.itemCh = row.itemCh;
      this.dict.itemValue = row.itemValue;
      this.dict.dictCode = row.dictCode;
      this.dict.description = row.description;
      // this.$message.warning("该功能尚未实现");
      // this.onEdit(row, false)
    },
    onClickEditSingDict() {
      if (this.dict.itemCh && this.dict.itemValue && this.dict.dictCode) {
        systemAdminApi.editChildDict(this.dict).then(
          (res) => {
            this.$message.success("修改成功");
            this.getAllDictionary();
            this.loadSingleDictList(1);
            this.dictShow = false;
          },
          (err) => {
            this.$message.error(res.msg);
          }
        );
      } else {
        this.$message.warning("修改项不能为空");
      }
    },
    onClickPublish() { },
    onClickDelete(id) {
      this.$confirm("确认删除当前字典").then(() => {
        systemAdminApi.delDict(id).then(
          (res) => {
            this.$message.success("删除成功");
            this.getAllDictionary();
            this.getList(this.current);
          },
          (err) => {
            this.$message.errror(err.msg);
          }
        );
      });
    },
    onClickOffline() { },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getList(this.current);
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getList(val);
    },
    handleDictSizeChange(val) {
      this.dictPagesize = val;
      this.loadSingleDictList(this.dictCurrent);
    },
    handleDictCurrentChange(val) {
      this.dictCurrent = val;
      this.loadSingleDictList(val);
    },
    editDictConfig(dictCode) {
      this.itemCh= "",
        this.itemValue= "",
        this.dictCode = dictCode;
      this.dictListCode = dictCode;
      this.loadSingleDictList(1);
      this.dictListShow = true;
    },
    showChildAdd() {
      this.childForm.dictCode = this.dictListCode;
      this.addDictChildShow = true;
    },
    onClickAddChildDict() {
      if (
        this.childForm.itemCh &&
        this.childForm.itemValue &&
        this.childForm.dictCode
      ) {
        systemAdminApi.addChildDict(this.childForm).then(
          (res) => {
            this.$message.success("添加成功");
            this.addDictChildShow = false;
            this.loadSingleDictList(1);
            this.getAllDictionary();
          },
          (err) => {
            this.$message.errror(err.msg);
          }
        );
      } else {
        this.$message.warning("添加项不能为空");
      }
    },
    loadSingleDictList(current) {
      const data = {
        current: current,
        size: this.dictPagesize,
        dictCode: this.dictCode,
        asc: true,
      };
      if(this.itemValue){
        data["itemValue"] = this.itemValue
      }
      if(this.itemCh){
        data["itemCh"] = this.itemCh
      }
      systemAdminApi.getSingleDictList(data).then(
        (res) => {
          this.dictList = res.records;
          this.dictTotal = Number(res.total);
          this.dictCurrent = Number(res.current);
          this.dictPageCount = Math.ceil(
            parseInt(this.dictTotal) / this.dictPagesize
          );
          this.dictList.map((v) => {
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        },
        (err) => { }
      );
    },

    // 获取当前页面list
    getList(page) {
      const para = {
        asc: true,
        current: page,
        size: this.pageSize,
        dictCode: this.searchDictCode,
        dictName: this.searchDictName,
      };
      systemAdminApi
        .getDictList(para)
        .then((data) => {
          this.list = data.records;
          this.total = Number(data.total);
          this.current = Number(data.current);
          this.pageCount = Math.ceil(parseInt(this.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        })
        .catch((errror) => {
          // debugger
        });
    },
  },
  created() { },
  mounted() {
    this.getList(1);
  },
};
</script>
<style lang="scss" scoped>
.dialog-footer {
  text-align: center;
}

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
