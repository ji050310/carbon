<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <el-row>
            <el-col :span="6">
              <div class="selectbox-root">
                <a class="selectbox-hint cursor-mi" style="width: 100px">账户类型</a>
                <div class="selectbox-deliver" />
                <el-cascader style="width: 120px" placeholder="全部" class="selectbox-input" v-model="selectedType"
                             :options="accountTypeDict" clearable @change="onClickSearch">
                </el-cascader>
              </div>
            </el-col>

            <el-col :span="5">
              <div style="margin-left: 16px" class="selectbox-root">
                <a class="selectbox-hint cursor-mi" style="width: 80px">状态</a>
                <div class="selectbox-deliver" />
                <el-cascader placeholder="全部" class="selectbox-input" v-model="selectedStatus"
                             :options="accountStatusDict" clearable @change="onClickSearch">
                </el-cascader>
              </div>
            </el-col>
            <el-col :span="5">
              <div style="margin-left: 16px" class="selectbox-root">
                <a class="selectbox-hint cursor-mi" style="width: 110px">产品版本</a>
                <div class="selectbox-deliver" />
                <el-cascader placeholder="全部" class="selectbox-input" v-model="selectedVersion"
                             :options="productVerssionDict" clearable @change="onClickSearch">
                </el-cascader>
              </div>
            </el-col>
            <el-col :span="8">
              <div style="margin-left: 10px; margin-right: 10px" class="selectbox-root">
                <a class="selectbox-hint cursor-mi" style="width: 140px">用户搜索</a>
                <div class="selectbox-deliver" />
                <el-input v-model="searchKeyWord" placeholder="输入用户名或公司名" clearable size="medium" @clear="onClickSearch"
                          @keyup.enter.native="onClickSearch" />
              </div>
            </el-col>
          </el-row>

          <button class="light-green-btn" @click="onClickSearch">查询</button>
        </div>
        <div>
          <button style="margin-top: 0px; margin-bottom: 20px; width: 103px" class="normal-white-btn" @click="addUser">
            +新增用户
          </button>
          <el-table :header-cell-style="{
            background: '#F2F5F7',
            border: '0px solid #DDDDDD',
            color: '#242B35',
            height: '64px',
          }" :show-header="true" :data="list" :row-style="{ height: '64px' }" :cell-style="cellStyle" stripe
                    style="width: 100%; cursor: default" @row-click="clickItem">
            <el-table-column min-width="20"></el-table-column>
            <el-table-column label="用户账号" align="left" prop="accountName" min-width="120">
            </el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="username" label="用户名称" align="left" min-width="100" />
            <el-table-column align="left" prop="accountRole.roleNames[0]" label="用户角色" min-width="80" />
            <el-table-column align="left" prop="accountTypeName" label="账户类型" min-width="100" />
            <el-table-column align="left" prop="productVersionName" label="产品版本" min-width="100" />
            <el-table-column align="left" prop="accountStatusName" label="状态" min-width="60" />
            <el-table-column align="left" prop="createdTime" label="开户时间" min-width="120" />
            <el-table-column align="left" prop="validityPeriod" label="账户有效期" min-width="120" />
            <el-table-column align="left" prop="remarks" label="备注" min-width="60" />
            <el-table-column label="操作" min-width="150" align="center">
              <template slot-scope="scope">
                <a class="list-blue-text" v-on:click.stop="onEdit(scope.row)">编辑</a>
                <a style="margin-left: 10px" class="list-red-text" v-on:click.stop="onClickDelete(scope.row.id)">删除</a>
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
      <!-- 查看用户信息 -->
      <el-dialog top="20px" title="用户信息" :visible.sync="readFormVisible" width="50%">
        <el-row>
          <el-col :span="4"><span class="table-text">用户账号:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.accountName" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">用户姓名:</span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.username" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">联系电话:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.phone" autocomplete="off" size="medium" disabled></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">电子邮箱:</span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.email" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">登录密码:</span></el-col>
          <el-col :span="8">
            <el-input v-model="password" type="password" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">确认密码:</span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.password" type="password" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">用户角色:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-select v-model="selectedRole" placeholder="请选择角色" size="medium" disabled style="width: 100%">
              <el-option v-for="(item, index) in roleOptions" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">所属租户:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.tenantName" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">所属行业:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.industry" disabled autocomplete="off" size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">企业地址:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.address" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">企业电话:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.telephone" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">企业传真:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.faxNumber" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">账户类型:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-select v-model="addForm.accountType" placeholder="" size="medium" style="width: 100%" disabled>
              <el-option v-for="(item, index) in accountTypeDict" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">产品版本:<span
            style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-select v-model="addForm.productVersion" placeholder="" disabled size="medium" style="width: 100%">
              <el-option v-for="(item, index) in productVerssionDict" :key="index" :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">开户日期:</span></el-col>
          <el-col :span="8">
            <el-date-picker v-model="addForm.createdTime" type="datetime" placeholder="选择日期时间" align="right"
                            style="width: 100%" disabled size="medium">
            </el-date-picker>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">账户有效日期:</span></el-col>
          <el-col :span="8">
            <el-date-picker v-model="addForm.validityPeriod" type="datetime" placeholder="选择日期时间" align="right"
                            style="width: 100%" disabled size="medium">
            </el-date-picker>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">账户状态:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-select v-model="addForm.accountStatusName" placeholder="启用/禁用" style="width: 100%" disabled
                       size="medium">
              <el-option v-for="(item, index) in optionStatus" :label="item.label" :value="item.value" :key="index">
              </el-option>
            </el-select>
          </el-col>

          <el-col :span="4"><span style="margin-left: 10px" class="table-text">企业介绍:</span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.remarks" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
        </el-row>
        <div slot="footer" class="dialog-footer" align="center">
          <el-button @click="readFormVisible = false" class="light-green-btn">确定</el-button>
        </div>
      </el-dialog>
      <!-- 编辑用户信息 -->
      <el-dialog top="20px" title="编辑用户信息" :visible.sync="editFormVisible" width="50%">
        <el-row>
          <el-col :span="4"><span class="table-text">用户账号:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.accountName" autocomplete="off" size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">用户姓名:</span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.username" autocomplete="off" size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">联系电话:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.phone" autocomplete="off" size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">电子邮箱:</span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.email" autocomplete="off" size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">登录密码:</span></el-col>
          <el-col :span="8">
            <el-input v-model="password" type="password" autocomplete="off" size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">确认密码:</span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.password" type="password" autocomplete="off" @blur="onInputBlur" size="medium">
            </el-input>
          </el-col>
          <el-col :span="20">
            <div style="text-align: right; color: red" v-if="passwordTip">
              输入密码不一致
            </div>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">用户角色:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-select v-model="selectedRole" placeholder="请选择角色" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in roleOptions" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">所属租户:</span></el-col>
          <el-col :span="8">
            <!-- <el-autocomplete class="inline-input" v-model="tenantSelect" size="medium" style="width: 100%"
              @blur="checkOwner" :fetch-suggestions="querySearch" placeholder="请输入内容" @select="handleSelect">
            </el-autocomplete> -->
            <el-select v-model="tenant.id" placeholder="请选择租户" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in tenantList" :key="index" :label="item.tenantName" :value="item.id">
              </el-option>
            </el-select>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">所属行业:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.industry" autocomplete="off" size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">企业地址:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.address" autocomplete="off" size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">企业电话:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.telephone" autocomplete="off" size="medium"></el-input>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">企业传真:</span></el-col>
          <el-col :span="8">
            <el-input v-model="tenant.faxNumber" autocomplete="off" size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">账户类型:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-select v-model="addForm.accountType" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in accountTypeDict" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">产品版本:<span
            style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-select v-model="addForm.productVersion" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in productVerssionDict" :key="index" :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">开户日期:</span></el-col>
          <el-col :span="8">
            <el-date-picker v-model="addForm.createdTime" type="datetime" placeholder="选择日期时间" align="right" disabled
                            style="width: 100%" size="medium">
            </el-date-picker>
          </el-col>
          <el-col :span="4"><span style="margin-left: 10px" class="table-text">账户有效日期:</span></el-col>
          <el-col :span="8">
            <el-date-picker v-model="addForm.validityPeriod" type="datetime" placeholder="选择日期时间" align="right"
                            style="width: 100%" size="medium">
            </el-date-picker>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="4"><span class="table-text">账户状态:<span style="color: red">*</span></span></el-col>
          <el-col :span="8">
            <el-select v-model="addForm.accountStatus" placeholder="全部" style="width: 100%" size="medium">
              <el-option v-for="(item, index) in accountStatusDict" :label="item.label" :value="item.value"
                         :key="index"></el-option>
            </el-select>
          </el-col>

          <el-col :span="4"><span style="margin-left: 10px" class="table-text">企业介绍:</span></el-col>
          <el-col :span="8">
            <el-input v-model="addForm.remarks" autocomplete="off" size="medium"></el-input>
          </el-col>
        </el-row>
        <div slot="footer" class="dialog-footer" align="center">
          <el-button @click="onCancel">取 消</el-button>

          <el-button @click="update" type="primary">保存</el-button>
        </div>
      </el-dialog>
      <!-- 添加用户 -->
      <el-dialog title="用户信息" :visible.sync="addFormVisible" width="40%">
        <el-form :model="form" :label-position="left" label-width="80px">
          <el-form-item label="账户名">
            <span class="require">*</span>
            <el-input v-model="form.accountName" autocomplete="off" style="width: 80%" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="账户状态">
            <span class="require">*</span>
            <el-select v-model="form.accountStatus" placeholder="全部" style="width: 80%" size="medium">
              <el-option v-for="(item, index) in accountStatusDict" :label="item.label" :value="item.value"
                         :key="index"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="邮箱">
            <span class="require" v-show="false">*</span>
            <el-input v-model="form.email" autocomplete="off" style="width: 80%; margin-left: 10px" size="medium">
            </el-input>
          </el-form-item>
          <el-form-item label="账户角色">
            <el-select v-model="selectedRole" placeholder="请选择角色" style="width: 80%; margin-left: 10px" size="medium">
              <el-option v-for="(item, index) in roleOptions" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="账户密码">
            <span class="require">*</span>
            <el-input v-model="form.password" type="password" show-password style="width: 80%" autocomplete="off"
                      size="medium"></el-input>
          </el-form-item>
          <el-form-item label="手机号">
            <span class="require">*</span>
            <el-input v-model="form.phone" autocomplete="off" style="width: 80%" size="medium" maxlength="11">
            </el-input>
          </el-form-item>
          <el-form-item label="备注">
            <span class="require" v-show="false">*</span>
            <el-input v-model="form.remarks" autocomplete="off" style="width: 80%; margin-left: 10px" size="medium">
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer" align="center">
          <el-button @click="addFormVisible = false" class="normal-white-btn">取 消</el-button>
          <el-button type="primary" @click="addSubmit" class="light-green-btn">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import {
  accountList,
  getTenantInfo,
  adminList,
  updatedAccout,
  addAccount,
  delAccout,
  updatedTenant,
  getTenantList,
} from "@/api/systemadmin";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import md5 from "js-md5";
import Cookies from "js-cookie";
import {
  getProductVerssionDict,
  getAccountStatusDict,
  getAccountTypeDict,
} from "@/config/dictHelper";
import { cursor } from "@/libs/element-table";
import { error } from "console";
export default {
  name: "accountManager",
  components: { selectDropDownBox },
  data() {
    return {
      indeterminateFlag: false, //表头复选框状态
      reRender: true, // 重新渲染列表使用
      allchecked: false,
      searchKeyWord: "",
      selectedVersion: "", //被选中的版本
      selectedType: "", //被选中的类型
      selectedStatus: "", //被选中的状态
      list: [],
      visible: false,
      passwordTip: false, //密码提示
      tenantSelect: "", //选中的企业
      editFormVisible: false,
      total: 0,
      isSaved: false,
      tenant: {
        address: "",
        faxNumber: "",
        id: 0,
        industry: "",
        telephone: "",
        tenantName: "",
      },
      current: 0,
      password: "",
      roleOptions: [],
      addFormVisible: false,
      readFormVisible: false,
      pageCount: 1,
      optionStatus: [],
      pageSize: 10,
      tenantOptions: [],
      productVerssionDict: [], //产品版本字典
      accountStatusDict: [], //账户类型字典
      accountTypeDict: [], //账户类型字典
      addForm: {
        accountName: "",
        accountStatus: "",
        email: "",
        id: "",
        password: null,
        phone: "",
        username: "",
        remarks: "",
      },
      selectedRole: "",
      optionsOnlines: [
        {
          label: "全部",
        },
        {
          value: 1,
          label: "启用",
        },
        {
          value: 0,
          label: "禁用",
        },
      ],
      form: {
        accountName: "",
        accountStatus: "",
        email: "",
        id: 0,
        password: "",
        phone: "",
        remarks: "",
        username: "",
        roleIds: [],
      },
      value: "",
      roleList: [],
      tenantList: [], //租户列表
    };
  },
  watch: {},
  methods: {
    clickItem(row) {
      this.readFormVisible = true;
      this.tenant = {};
      this.selectedRole = row.accountRole.roleIds[0];
      this.addForm = row;
      for (var i in this.addForm) {
        if (this.addForm[i] == "--") {
          this.addForm[i] = "";
        }
      }
      if (row.tenantId != "--" && row.tenantId) {
        getTenantInfo(row.tenantId).then(
          (res) => {
            this.tenant = { ...res };
          },
          (err) => {
            this.$message.error(err.msgs);
          }
        );
      }
    },
    onCancel() {
      if (this.addForm.password) {
        if (this.isSaved) {
          this.editFormVisible = false;
        } else {
          this.$message.warning("您还未保存密码");
          this.isSaved = true;
        }
      } else {
        this.editFormVisible = false;
      }
    },
    confirmCancel() {
      this.visible = false;
      this.editFormVisible = false;
    },
    cellStyle({ row, column, rowIndex, columnIndex }) {
      if (column.label != "操作") {
        return "cursor:pointer;";
      }
    },
    /**
     * 作者:
     * 时间: 2022-05-30 16:07:51
     * 功能: 新增用户
     */
    addUser() {
      this.addFormVisible = true;
      this.readFormVisible = false;
      this.selectedRole = "";
    },
    /**
     * 作者:
     * 时间: 2022-06-06 14:27:17
     * 功能: 删除账户
     */
    onClickDelete(id) {
      this.$confirm("确认删除当前账户？").then((res) => {
        delAccout(id).then(
          (res) => {
            this.$message.success("删除成功");
            this.getList(1);
          },
          (err) => {
            this.$message.error(err.msg);
          }
        );
      });
    },
    /**
     * 作者:
     * 时间: 2022-06-17 09:03:26
     * 功能: 当前选择的企业发生变化时执行
     */
    updateTenant() {
      this.tenantList.map((v) => {
        if (v.id == this.tenant.id) {
          this.tenant.faxNumber = v.faxNumber;
          this.tenant.address = v.address;
          this.tenant.tenantName = v.tenantName;
          this.tenant.industry = v.industry;
          this.tenant.telephone = v.telephone;
        }
      });
    },
    querySearch(queryString, cb) {
      var tenants = this.tenantOptions;
      var results = queryString
        ? tenants.filter(this.createFilter(queryString))
        : tenants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (tenant) => {
        return (
          tenant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    /**
     * 作者:
     * 时间: 2022-06-20 08:55:56
     * 功能: 搜索建议下拉框
     */
    handleSelect(item) {
      this.tenant.id = item.id;
      this.tenantList.map((v) => {
        if (v.id == this.tenant.id) {
          this.tenant.faxNumber = v.faxNumber;
          this.tenant.address = v.address;
          this.tenant.tenantName = v.tenantName;
          this.tenant.industry = v.industry;
          this.tenant.telephone = v.telephone;
        }
      });
    },
    /**
     * 作者:
     * 时间: 2022-06-02 15:18:47
     * 功能: 提交用户
     */
    addSubmit() {
      this.form.roleIds = [this.selectedRole];
      if (
        this.form.password &&
        this.form.accountName &&
        this.form.accountStatus &&
        this.form.phone
      ) {
        addAccount(this.form).then(
          (res) => {
            this.$message.success("添加成功");
            this.addFormVisible = false;
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
    /**
     * 作者:
     * 时间: 2022-05-30 15:18:05
     * 功能: 编辑账户
     */
    onEdit(row) {
      this.tenant = {};
      this.isSaved = false;
      this.editFormVisible = true;
      this.readFormVisible = false;
      this.selectedRole = row.accountRole.roleIds[0];
      this.addForm = row;
      for (var i in this.addForm) {
        if (this.addForm[i] == "--") {
          this.addForm[i] = "";
        }
      }
      this.addForm.accountStatus = row.accountStatus;
      if ( row.tenantId && row.tenantId != 0 && row.tenantId != "--") {
        getTenantInfo(row.tenantId).then(
          (res) => {
            this.tenant = { ...res };
            this.tenantSelect = this.tenant.tenantName;
          },
          (err) => {
            this.$message.error(err.msgs);
          }
        );
      } else {
      }
    },
    /**
     * 作者:
     * 时间: 2022-06-02 14:54:52
     * 功能: 提交保存
     */
    update() {
      if (this.passwordTip) {
        this.$message.warning("请检查两次输入的密码是否一致");
        return;
      }
      this.addForm.roleIds = [this.selectedRole];
      if (
        this.addForm.accountName &&
        this.addForm.phone &&
        this.addForm.roleIds &&
        this.addForm.accountType
      ) {
        let data = {
          accountName: this.addForm.accountName,
          accountStatus: this.addForm.accountStatus,
          accountType: this.addForm.accountType,
          createdTime: this.addForm.createdTime,
          email: this.addForm.email,
          id: this.addForm.id,
          password: this.addForm.password ? md5(this.addForm.password) : "",
          phone: this.addForm.phone,
          productVersion: this.addForm.productVersion,
          remarks: this.addForm.remarks,
          roleIds: this.addForm.roleIds,
          tenantId: this.tenant.id,
          username: this.addForm.username,
          validityPeriod: this.addForm.validityPeriod,
        };

        debugger;
        updatedAccout(data).then(
          (res) => {
            let JavaInfo = JSON.parse(Cookies.get("JavaInfo"));
            if (JavaInfo.accountId == this.addForm.id) {
              window.localStorage.setItem(
                "accountName",
                this.addForm.accountName
              );
              this.$store.dispatch("user/updateName", this.addForm.accountName);
            }
            this.$message.success("保存成功");
            this.isSaved = true;
            this.editFormVisible = false;
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
    //当输入的企业与已有企业不匹配时，弹框提示
    checkOwner() {
      // if(this.tenantSelect)
      var checkPass = false
      this.tenantOptions.map(v => {
        if (v.value == this.tenantSelect) {
          checkPass = true
        }
      })
      if (!checkPass) {
        this.$message.warning("请选择所开户的企业！")
      }
    },
    /**
     * 作者:
     * 时间: 2022-06-02 15:32:26
     * 功能: 顶部搜索下拉框变化执行函数
     */
    onUpdate() {
      const search = {
        accountType: this.selectedType[0],
        accountStatus: this.selectedStatus[0],
        productVersion: this.selectedVersion[0],
        async: true,
      };
      accountList(search).then(
        (res) => {
          this.list = res.records;
        },
        (err) => {
          this.$message.error(err.msg);
        }
      );
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
    onClickSearch() {
      const search = {
        async: true,
        keywords: this.searchKeyWord,
        accountType: this.selectedType[0],
        accountStatus: this.selectedStatus[0],
        productVersion: this.selectedVersion[0],
      };
      accountList(search).then(
        (res) => {
          this.list = res.records;
          this.total = Number(res.total);
          this.current = Number(res.current);
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            // if (v.accountStatus == 0) {
            //   v.accountStatusName = "禁用";
            // } else {
            //   v.accountStatusName = "启用";
            // }
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        },
        (err) => {
          this.$message.error(err.msg);
        }
      );
    },
    onInputBlur() {
      if (this.password != this.addForm.password) {
        this.passwordTip = true;
      } else {
        this.passwordTip = false;
      }
    },
    getRoleList() {
      const data2 = {
        asc: true,
        size: 20,
        current: 1,
        // status: 1,
      };
      adminList(data2).then(
        (res) => {
          this.roleList = res.records;
          res.records.map((v) => {
            let role = { label: "", value: "" };
            role.label = v.roleName;
            role.value = v.id;
            this.roleOptions.push(role);
          });
        },
        (err) => { }
      );
    },
    getList(page) {
      const data = {
        asc: true,
        size: this.pageSize,
        current: this.current,
        // status: 1,
      };
      accountList(data)
        .then((res) => {
          this.list = res.records;
          this.total = Number(res.total);
          this.current = Number(res.current);
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          this.list.map((v) => {
            //遍历表格数据
            // if (v.accountStatus == 0) {
            //   v.accountStatusName = "禁用";
            // } else {
            //   v.accountStatusName = "启用";
            // }
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        })
        .catch((error) => { });
      getTenantList()
        .then((res) => {
          this.tenantList = res;
          this.tenantList.map((v) => {
            this.tenantOptions.push({ id: v.id, value: v.tenantName });
          });
          console.log(this.tenantList,111111111111111)
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 格式化产品版本字典
    formatProduct(data) {
      data.map((v) => {
        let CertificationItem = {
          label: "",
        };
        if (v.name == "全部") {
          CertificationItem.label = v.name;
          CertificationItem.value = "";
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.productVerssionDict.push(CertificationItem);
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
          CertificationItem.value = "";
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.accountStatusDict.push(CertificationItem);
      });
    },
    // 格式化类型字典
    formatType(data) {
      data.map((v) => {
        let CertificationItem = {
          label: "",
        };
        if (v.name == "全部") {
          CertificationItem.label = v.name;
          CertificationItem.value = "";
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.accountTypeDict.push(CertificationItem);
      });
    },
  },
  // checkbox end
  created() {
    // this.handleChangeVisitType();

  },
  mounted() {
    this.getList(1);
    this.getRoleList();
    this.formatStatus(getAccountStatusDict(this.$store));
    this.formatType(getAccountTypeDict(this.$store));
    this.formatProduct(getProductVerssionDict(this.$store));
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

.table-text {
  position: relative;
  top: 10px;
}

.require {
  color: red;
  font-size: 16px;
}

.cursor-mi {
  cursor: default;
}
</style>
