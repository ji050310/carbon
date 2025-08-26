<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <button style="margin-top: 0px; margin-bottom: 20px; width: 103px" class="normal-white-btn" @click="addFather">
          +添加一级菜单
        </button>
        <div class="container">
          <el-table :data="list" :row-style="{ height: '64px' }" :cell-style="cellStyle" stripe
                    :header-cell-style="headerStyle" row-key="id" style="width: 100%">
            <el-table-column prop="menuName" label="菜单名称" min-width="80" align="left" />
            <el-table-column align="left" prop="menuType" label="菜单类型" min-width="60" />
            <el-table-column align="left" prop="menuIcon" label="icon" min-width="80" />
            <el-table-column align="left" prop="menuUrl" label="路径" min-width="100" />
            <el-table-column align="left" prop="orderNum" label="排序" min-width="60" />
            <el-table-column align="left" prop="statusName" label="状态" min-width="60" />
            <el-table-column label="操作" min-width="130" fixed="right" align="center">
              <template slot-scope="scope">
                <template v-if="scope.row.isDel">
                  <span>-</span>
                </template>
                <template v-else>
                  <a class="list-blue-text" style="margin-left: 10px" @click="addChild(scope.row)">添加</a>
                  <a class="list-blue-text" style="margin-left: 10px" @click="onEdit(scope.row, true)">编辑</a>
                  <a class="list-red-text" style="margin-left: 10px" @click="onClickDelete(scope.row)">删除</a>
                  <a class="list-green-text" style="margin-left: 10px" :class="startStyleChange(scope.row.status)"
                     @click="changeStatus(scope.row, 0)">启用</a>
                  <a class="list-red-text" style="margin-left: 10px" :class="banStyleChange(scope.row.status)"
                     @click="changeStatus(scope.row, 1)">禁用</a>
                </template>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <!-- <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
        <div style="flex-grow: 1" />
        <a style="margin: auto" class="pageBox-total-num">共{{ total }}条</a>
        <el-pagination style="margin: auto" background @size-change="handleSizeChange"
          @current-change="handleCurrentChange" :current-page="current" :page-size="pageSize" :page-count="pageCount"
          layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </div> -->
      <!-- Form -->

      <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="菜单名称" :label-width="60">
            <span class="require">*</span>
            <el-input v-model="form.menuName" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单icon" :label-width="60">
            <el-input v-model="form.menuIcon" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单路径" :label-width="60">
            <span class="require">*</span>
            <el-input v-model="form.menuUrl" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单排序" :label-width="60">
            <el-input v-model="form.orderNum" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单状态" :label-width="60">
            <span class="require">*</span>
            <el-select v-model="form.status" placeholder="启用/禁用" size="medium">
              <el-option label="启用" value="0"></el-option>
              <el-option label="禁用" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" class="normal-white-btn">取 消</el-button>
          <el-button type="primary" @click="submitChange" class="light-green-btn">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 添加父菜典 -->
      <el-dialog title="添加父菜单" :visible.sync="addDictShow" width="30%">
        <el-form :model="addForm">
          <el-form-item label="菜单名称" :label-width="60">
            <span class="require">*</span>
            <el-input v-model="addForm.menuName" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单路径" :label-width="60">
            <span class="require">*</span>
            <el-input v-model="addForm.menuUrl" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单icon" :label-width="60">
            <el-input v-model="addForm.menuIcon" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单排序" :label-width="60">
            <el-input v-model="addForm.orderNum" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单状态" :label-width="60">
            <span class="require">*</span>
            <el-select v-model="addForm.status" placeholder="启用/禁用" size="medium">
              <el-option label="启用" value="0"></el-option>
              <el-option label="禁用" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDictShow = false" class="normal-white-btn">取 消</el-button>
          <el-button type="primary" @click="addSubmit" class="light-green-btn">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 添加子菜单 -->
      <el-dialog title="添加子菜单" :visible.sync="addChildShow" width="30%">
        <el-form>
          <el-form-item label="菜单名称" label-width="60"><span class="require">*</span>
            <el-input v-model="addForm.menuName" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单路径" label-width="60">
            <span class="require">*</span>
            <el-input v-model="addForm.menuUrl" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单icon" label-width="60">
            <el-input v-model="addForm.menuIcon" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单排序" label-width="60">
            <el-input v-model="addForm.orderNum" autocomplete="off" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="菜单状态" label-width="60">
            <span class="require">*</span>
            <el-select v-model="addForm.status" placeholder="启用/禁用" size="medium">
              <el-option label="启用" value="0"></el-option>
              <el-option label="禁用" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addChildShow = false" class="normal-white-btn">取 消</el-button>
          <el-button type="primary" @click="addChildSubmit" class="light-green-btn">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import * as systemAdminApi from "@/api/systemadmin.js";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { cursor } from "@/libs/element-table";
export default {
  name: "menuManager",
  components: { selectDropDownBox },
  data() {
    return {
      isAdd: false, //判断是添加操作还是修改操作
      reRender: true, // 重新渲染列表使用
      addChildShow: false, //控制子菜单添加是否显示
      searchDictName: "",
      searchDictCode: "",
      list: [],
      addForm: {
        menuName: "",
        menuIcon: "",
        menuUrl: "",
        orderNum: "",
        menuLevel: 1, //1最高级别
        parentId: 0,
        status: "",
      },
      total: 0,
      current: 1,
      pageCount: 1,
      levelNum: "",
      pageSize: 10,
      value: "",
      dialogFormVisible: false,
      dictListShow: false,
      dictList: [], //弹出框字典编码list
      dictShow: false,
      addDictShow: false, //添加字典弹框
      dict: {
        name: "",
        value: "",
      },
      row: {},
      form: {
        menuName: "",
        menuUrl: "",
        menuIcon: "",
        orderNum: null,
        id: 0,
        status: "0",
        parentId: 0,
        menuLevel: 0,
      },
      title: "",
    };
  },
  methods: {
    cellStyle({ row, column, rowIndex, columnIndex }) {
      // return cursor(data);
      if (column.label == "菜单名称") {
        return "padding-left:20px";
      }
    },
    headerStyle({ row, column, rowIndex, columnIndex }) {
      // if (row == 0) {
      //   return "padding-left:15px";
      // }

      if (columnIndex == 0) {
        return "padding-left:20px;background:#F2F5F7;border:0px solid #DDDDDD;color:#242B35;height:64px";
      } else {
        return "background:#F2F5F7;border:0px solid #DDDDDD;color:#242B35;height:64px";
      }
    },
    addFather() {
      this.addDictShow = true;
      this.addForm.menuLevel = 1;
      this.addForm.status = "0";
    },
    startStyleChange(status) {
      if (status == 0) {
        return "afterSubmitEdit";
      } else {
        return "list-green-text";
      }
    },
    banStyleChange(status) {
      if (status == 0) {
        return "list-red-text";
      } else {
        return "afterSubmitOffline";
      }
    },
    addDict() {
      this.addDictShow = true;
      this.form.dictName = "";
      this.form.id = "";
      this.form.dictCode = "";
      this.form.description = "";
      this.form.type = "";
    },
    //提交父菜单
    addSubmit() {
      this.addForm.parentId = 0;
      if (
        this.addForm.menuName &&
        this.addForm.menuUrl &&
        this.addForm.status
      ) {
        if (!this.addForm.orderNum) {
          this.addForm.orderNum = this.list.length + 2;
        }
        if (!this.addForm.menuIcon) {
          this.addForm.menuIcon = "/";
        }
        systemAdminApi.addMenu(this.addForm).then(
          (res) => {
            this.$message.success("添加成功，请刷新页面！");
            this.addDictShow = false;
            this.getList(1);
          },
          (err) => {
            this.$message.error(err.msg);
          }
        );
      } else {
        this.$message.warning("必填项不能为空");
      }
    },
    /*
     *@Description: 启用禁用按钮
     *@MethodAuthor: liuboting
     *@Date: 2022-05-29 12:59:58
     */
    changeStatus(row, status) {
      const data = {
        id: row.id,
        status: status,
      };
      systemAdminApi.editMenuStatus(data).then(
        (res) => {
          this.$message.success("修改成功，请刷新页面！");
          this.getList(1);
        },
        (err) => {
          this.$message.error(err.msg);
        }
      );
    },
    /**
     * 作者:
     * 时间: 2022-06-02 09:26:23
     * 功能: 当排序为空时，计算当前最大排序
     */
    // calMenuOrder() {
    //   if (!this.addForm.orderNum) {
    //     switch (this.addForm.menuLevel) {
    //       case 1:
    //         this.addForm.orderNum = this.list.length + 2;
    //         break;
    //       case 2:
    //         if (this.row.children) {
    //           this.addForm.orderNum = this.row.children.length + 2;
    //         } else {
    //           this.addForm.orderNum = 1;
    //         }
    //         break;
    //       case 3:
    //         if (this.row.children) {
    //           this.addForm.orderNum = this.row.children.length + 2;
    //         } else {
    //           this.addForm.orderNum = 1;
    //         }
    //         break;
    //     }
    //   }
    //   if (!this.addForm.menuIcon) {
    //     this.addForm.menuIcon = "/";
    //   }
    // },
    //提交子菜单
    addChildSubmit() {
      if (
        this.addForm.menuName &&
        this.addForm.menuUrl &&
        this.addForm.menuLevel &&
        this.addForm.status
      ) {
        // this.calMenuOrder();
        // if (this.addForm.orderNum.length > 1) {
        //   this.addForm.orderNum = this.addForm.orderNum.charAt(
        //     this.addForm.orderNum.length - 1
        //   );
        // }
        systemAdminApi.addMenu(this.addForm).then(
          (res) => {
            this.$message.success("添加成功，请刷新页面！");
            this.addChildShow = false;
          },
          (err) => {
            this.$message.error(err.msg);
          }
        );
      } else {
        this.$message.warning("必填项不能为空！");
      }
    },
    //删除单个字典
    delSingDict() {
      this.$message.warning("该功能尚未实现");
    },
    //编辑字典类型
    onEdit(row, bool) {
      this.dialogFormVisible = bool;
      this.form.id = row.id;
      this.form.menuName = row.menuName;
      this.form.menuIcon = row.menuIcon;
      this.form.menuLevel = row.menuLevel;
      this.form.menuUrl = row.menuUrl;
      this.form.orderNum = row.orderNum;
      this.form.parentId = row.parentId;
      this.form.status = row.status + "";
      this.row = row;
    },
    submitChange() {
      // this.form.orderNum = this.form.orderNum.charAt(
      //   this.form.orderNum.length - 1
      // );
      if (this.form.menuIcon && this.form.menuName && this.form.menuUrl) {
        systemAdminApi.editMenu(this.form).then(
          (res) => {
            this.$message.success("修改成功");
            this.dialogFormVisible = false;
            debugger;
            location.reload();
            // this.getList(this.current);
          },
          (err) => {
            this.$message.error("修改失败");
          }
        );
      } else {
        this.$message.warning("修改项不能为空");
      }
    },
    onClickPublish() { },
    onClickDelete(row) {
      this.$confirm("确认删除当前菜单项？").then(() => {
        systemAdminApi.delMenu(row.id).then(
          (res) => {
            this.$message.success("删除成功");
            location.reload();
          },
          (err) => {
            this.$message.error(err.msg);
          }
        );
      });
    },
    addChild(row) {
      for (var i in this.addForm) {
        this.addForm[i] = null;
      }
      this.addForm.menuLevel = row.menuLevel + 1;
      this.row = row;
      this.addChildShow = true;
      this.addForm.parentId = row.id;
      this.addForm.menuUrl = row.menuUrl
      this.addForm.status = '0'
      if (row.children) {
        this.addForm.orderNum = row.children.length + 1
      } else {
        this.addForm.orderNum = 1
      }

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
    loadSingleDictList(dictCode) {
      this.dictListShow = true;
      systemAdminApi.getSingleDictList(dictCode).then(
        (res) => {
          this.dictList = res;
        },
        (err) => { }
      );
    },

    // 获取当前页面list
    getList(page) {
      const para = {
        hidden: false,
        menuLevel: 1,
      };
      systemAdminApi
        .getMenuList(para)
        .then((data) => {
          this.list = data;
          this.total = Number(data.total);
          this.current = Number(data.current);
          this.pageCount = Math.ceil(parseInt(this.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            v.menuType = v.menuType ? v.menuType : "菜单";
            // v.orderNum = v.id;
            // if (v.children !== []) {
            // }
            if (v.status == 0) {
              v.statusName = "启用";
            } else {
              v.statusName = "禁用";
            }
            if (v.children) {
              v.children.map((value, index) => {
                value.menuType = "子菜单";
                // value.orderNum = Number(v.orderNum) + "." + (index + 1);
                value.menuIcon = value.menuIcon ? value.menuIcon : "--";
                if (value.status == 0) {
                  value.statusName = "启用";
                } else {
                  value.statusName = "禁用";
                }
                if (value.children) {
                  value.children.map((s, i) => {
                    s.menuType = "子菜单";
                    // s.orderNum = value.orderNum + "." + (i + 1);
                    s.menuIcon = s.menuIcon ? s.menuIcon : "--";
                    if (s.status == 0) {
                      s.statusName = "启用";
                    } else {
                      s.statusName = "禁用";
                    }
                  });
                }
              });
            }
          });
        })
        .catch((errror) => {
          // debugger
        });
      console.log(this);
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

.require {
  color: red;
  font-size: 16px;
  position: relative;
  right: 8px;
  top: 2px;
}
</style>
