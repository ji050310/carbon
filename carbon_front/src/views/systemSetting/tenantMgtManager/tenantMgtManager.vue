<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="container">
          <el-row>
            <el-col :span="6">
              <div class="selectbox-root">
                <a class="selectbox-hint cursor-mi" style="width: 100px">租户类型</a>
                <div class="selectbox-deliver" />
                <el-cascader style="width: 120px" placeholder="全部" class="selectbox-input" v-model="selectedType" :options="tenantTypeDict" clearable @change="onClickSearch"></el-cascader>
              </div>
            </el-col>
            <el-col :span="5">
              <div style="margin-left: 16px" class="selectbox-root">
                <a class="selectbox-hint cursor-mi" style="width: 100px">状态</a>
                <div class="selectbox-deliver" />
                <el-cascader placeholder="全部" class="selectbox-input" v-model="selectedStatus" :options="accountStatusDict" clearable @change="onClickSearch"></el-cascader>
              </div>
            </el-col>
            <!-- <el-col :span="5">
              <div style="margin-left: 16px" class="selectbox-root">
                <a class="selectbox-hint cursor-mi" style="width: 110px">产品版本</a>
                <div class="selectbox-deliver" />
                <el-cascader placeholder="全部" class="selectbox-input" v-model="selectedVersion"
                  :options="productVerssionDict" clearable @change="onClickSearch">
                </el-cascader>
              </div>
            </el-col> -->
            <el-col :span="13">
              <div style="margin-left: 10px; margin-right: 10px" class="selectbox-root">
                <a class="selectbox-hint cursor-mi" style="width: 260px">租户搜索</a>
                <div class="selectbox-deliver" />
                <el-input v-model="searchKeyWord" placeholder="输入公司名" clearable size="medium" @clear="onClickSearch"
                          @keyup.enter.native="onClickSearch" />
              </div>
            </el-col>
          </el-row>

          <button class="light-green-btn" @click="onClickSearch">查询</button>
        </div>
        <div>
          <button style="margin-top: 0px; margin-bottom: 20px; width: 103px" class="normal-white-btn" @click="addTenant">
            +新增租户
          </button>
          <el-table :header-cell-style="{
            background: '#F2F5F7',
            border: '0px solid #DDDDDD',
            color: '#242B35',
            height: '64px',
          }" :show-header="true" :data="list" :row-style="{ height: '64px' }" stripe
                    style="width: 100%; cursor: default">
            <!-- style="width: 100%; cursor: default" @row-click="clickItem"> -->
            <el-table-column min-width="20"></el-table-column>

            <el-table-column align="left" label="序号" min-width="80">
              <template slot-scope="scope">
                <span>  {{ getCurListNo(scope.$index) }}</span>
              </template>
            </el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="tenantName" label=" 租户" align="left" min-width="100" />
            <el-table-column align="left" prop="type" label="类型" min-width="80">
              <template slot-scope="scope">
                <span>  {{ getTenantTypeName(scope.row.type) || '--' }}</span>
              </template>
            </el-table-column>
            <el-table-column align="left" prop="tenantStatus" label="状态" min-width="80">
              <template slot-scope="scope">
                <span>  {{ getTenantStatusName(scope.row.tenantStatus) || '--' }}</span>
              </template>
            </el-table-column>
            <el-table-column align="left" prop="telephone" label="联系电话" min-width="100" />
            <el-table-column align="left" prop="contactsEmail" label="联系邮箱" min-width="100" />
            <el-table-column align="left" prop="contactsName" label="联系人" min-width="120" />
            <!-- <el-table-column align="left" prop="contactsName" label="联系人电话" min-width="120" /> -->
            <el-table-column align="left" prop="createdTime" label="开户日期" min-width="120" />
            <el-table-column align="left" prop="validityTime" label="租户有效期" min-width="120" />
            <el-table-column align="left" prop="industry" label="行业" min-width="80" />
            <el-table-column label="操作" min-width="150" align="center">
              <template slot-scope="scope">
                <a class="list-blue-text" v-on:click.stop="clickItem(scope.row)">查看</a>
                <a class="list-blue-text" style="margin-left: 10px" v-on:click.stop="onEdit(scope.row)">编辑</a>
                <a style="margin-left: 10px" class="list-red-text" v-on:click.stop="onClickDelete(scope.row.id)">删除</a>
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
      <!-- 查看企业信息 -->
      <el-dialog top="20px" title="企业信息" :visible.sync="readFormVisible" width="75%">
        <el-row>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">租户<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.tenantName" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">联系人<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.contactsName" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>

          <el-col :span="2"><span style="margin-left: 10px" class="table-text">联系电话<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.telephone" autocomplete="off" disabled size="medium"></el-input>
          </el-col>

          <el-col :span="2"><span style="margin-left: 10px" class="table-text">联系邮箱<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.contactsEmail" autocomplete="off" disabled size="medium"></el-input>
          </el-col>

          <div style="clear: both; height: 20px"></div>

          <el-col :span="2"><span style="margin-left: 10px" class="table-text">	联系地址<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.address" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">企业传真<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.faxNumber" autocomplete="off" disabled size="medium"></el-input>
          </el-col>

          <div style="clear: both; height: 20px"></div>

          <el-col :span="2"><span style="margin-left: 10px" class="table-text">行业<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.industry" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">状态<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.tenantStatusName" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">	开户日期<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.createdTime" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">	租户有效期<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.validityTime" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">租户类型<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.tenantTypeName" autocomplete="off" disabled size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">	企业介绍</span></el-col>
          <el-col :span="21">
            <el-input type="textarea" disabled v-model="addForm.introduction" autocomplete="off" size="medium"></el-input>
          </el-col>
        </el-row>
        <div slot="footer" class="dialog-footer" align="center">
          <el-button @click="readFormVisible = false" class="light-green-btn">确定</el-button>
        </div>
      </el-dialog>
      <!-- 编辑企业信息 -->
      <el-dialog top="20px" title="编辑企业信息" :visible.sync="editFormVisible" width="75%">
        <el-row>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">租户<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.tenantName" autocomplete="off"  size="medium"></el-input>
          </el-col>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">	联系人<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.contactsName" autocomplete="off"  size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>

          <el-col :span="2"><span style="margin-left: 10px" class="table-text">联系电话<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.telephone" autocomplete="off"  size="medium"></el-input>
          </el-col>

          <el-col :span="2"><span style="margin-left: 10px" class="table-text">联系邮箱<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.contactsEmail" autocomplete="off" size="medium"></el-input>
          </el-col>

          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">	地址<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.address" autocomplete="off"  size="medium"></el-input>
          </el-col>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">	传真号<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.faxNumber" autocomplete="off"  size="medium"></el-input>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">行业<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-input v-model="addForm.industry" autocomplete="off"  size="medium"></el-input>
          </el-col>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">企业状态<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-select v-model="addForm.tenantStatus" placeholder="" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in accountStatusDict" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-col>

          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">开户日期</span></el-col>
          <el-col :span="9">
            <el-date-picker v-model="addForm.createdTime" type="datetime" placeholder="选择日期时间" align="right"
                            style="width: 100%" disabled size="medium">
            </el-date-picker>
          </el-col>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">有效日期<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-date-picker v-model="addForm.validityTime" type="datetime" placeholder="选择日期时间" align="right"
                            style="width: 100%" size="medium">
            </el-date-picker>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">租户类型<span style="color: red">*</span></span></el-col>
          <el-col :span="9">
            <el-select v-model="addForm.type" placeholder="" size="medium" style="width: 100%">
              <el-option v-for="(item, index) in tenantTypeDict" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <div style="clear: both; height: 20px"></div>
          <el-col :span="2"><span style="margin-left: 10px" class="table-text">	企业介绍</span></el-col>
          <el-col :span="20">
            <el-input type="textarea" v-model="addForm.introduction" autocomplete="off"  size="medium"></el-input>
          </el-col>
        </el-row>
        <div slot="footer" class="dialog-footer" align="center">
          <el-button @click="onCancel">取 消</el-button>
          <el-button @click="update" type="primary">保存</el-button>
        </div>
      </el-dialog>
      <!-- 添加企业 -->
      <el-dialog title="新增租户" :visible.sync="addFormVisible" width="60%">
        <el-form :model="form" label-width="120px" :inline="true">
          <el-form-item  label="">
            <label slot="label">租户<span style="color: red">*</span></label>
            <el-input v-model="form.tenantName" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">联系电话<span style="color: red">*</span></label>
            <el-input v-model="form.telephone" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>

          <el-form-item>
            <label slot="label">联系邮箱<span style="color: red">*</span></label>
            <el-input v-model="form.contactsEmail" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">联系地址</label>
            <el-input v-model="form.address" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">状态<span style="color: red">*</span></label>
            <el-select v-model="form.tenantStatus" size="medium"  style="width: 230px">
              <el-option v-for="(item, index) in accountStatusDict" :key="index" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <label slot="label">有效期<span style="color: red">*</span></label>
            <el-date-picker v-model="form.validityTime" type="datetime" placeholder="选择日期时间" align="right"  style="width: 230px" size="medium">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <label slot="label">租户编码<span style="color: red">*</span></label>
            <el-input v-model="form.orgName" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">联系人姓名</label>
            <el-input v-model="form.contactsName" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">联系人手机</label>
            <el-input v-model="form.contactsPhone" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">联系人职位</label>
            <el-input v-model="form.contactsPosition" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>

          <el-form-item>
            <label slot="label">传真号</label>
            <el-input v-model="form.faxNumber" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <!-- <el-form-item>
             <label slot="label">绿度积分</label>
            <el-input v-model="form.greennessIntegral" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item> -->
          <!-- <el-form-item>
            <label slot="label">绿度等级</label>
            <el-input v-model="form.greennessLevel" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item> -->
          <el-form-item>
            <label slot="label">行业</label>
            <el-input v-model="form.industry" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">法人代表</label>
            <el-input v-model="form.legalRepresentative" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <!-- <el-form-item>
            <label slot="label">备注</label>
            <el-input v-model="form.remarks" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item> -->
          <el-form-item>
            <label slot="label">信用代码</label>
            <el-input v-model="form.socialCreditCode" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">租户类型<span style="color: red">*</span></label>
            <el-select v-model="form.type" size="medium"  style="width: 230px">
              <el-option v-for="(item, index) in tenantTypeDict" :key="index" :label="item.label" :value="item.value"> </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <label slot="label">营业执照</label>
            <el-input v-model="form.businessLicense" autocomplete="off"  style="width: 230px" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
            <label slot="label">企业介绍</label>
            <el-input type="textarea" v-model="form.introduction" autocomplete="off" style="width: 596px;" size="medium"></el-input>
          </el-form-item>
          <!-- <el-form-item label="更新人ID">
            <span class="require">*</span>
            <el-input v-model="form.updatedId" autocomplete="off" style="width: 80%" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="更新时间">
            <span class="require">*</span>
            <el-input v-model="form.updatedTime" autocomplete="off" style="width: 80%" size="medium"></el-input>
          </el-form-item>
          <el-form-item label="有效天数">
            <span class="require">*</span>
            <el-input v-model="form.validityDayNum" autocomplete="off" style="width: 80%" size="medium"></el-input>
          </el-form-item> -->
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
  getTenantPageList,
  getTenantInfo,
  adminList,
  updatedAccout,
  addAccount,
  delTenantById,
  updatedTenant,
  addTenantUser
} from "@/api/systemadmin";
import selectDropDownBox from "@/components/selectbox/selectDropDownBox.vue";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import md5 from "js-md5";
import { setListNo,emailVerify,verifyPhoneNumber} from '@/libs/public'
import Cookies from "js-cookie";
import {
  getProductVerssionDict,
  getAccountStatusDict,
  getAccountTypeDict,
} from "@/config/dictHelper";
import { cursor } from "@/libs/element-table";
import { error } from "console";
export default {
  name: "tenantMgtManager",
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
      list: [],//xu==
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
      tenantTypeDict: [], //租户类型字典
      tenantStatusDict: [
        {
          value: 1,
          label: "启用",
        },
        {
          value: 0,
          label: "禁用",
        },
      ], //企业状态
      addForm: {    //20220824
        address: "",
        businessLicense: "",
        contactsEmail: "",
        contactsName: "",
        contactsPhone: "",
        contactsPosition: "",
        createdTime: "",
        creatorId: 0,
        faxNumber: "",
        greennessIntegral: 0,
        greennessLevel: 0,
        id: 0,
        industry: "",
        introduction: "",
        legalRepresentative: "",
        orgName: "",
        remarks: "",
        socialCreditCode: "",
        telephone: "",
        tenantName: "",
        tenantStatus: '全部',
        type: '全部',
        updatedId: 0,
        updatedTime: "",
        validityDayNum: 0,
        validityTime:"",
        tenantStatusName:"",
        tenantTypeName:""
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
        tenantName:'',                       //租户
        address:'',                          //地址
        businessLicense:'',                  //营业执照
        contactsEmail:'',                    //联系人邮箱
        contactsName:'',                     //联系人姓名
        contactsPhone:'',                    //联系人手机
        contactsPosition:'',                 //联系人职位
        createdTime:'',                      //创建时间
        creatorId: 1,                        //创建人ID
        faxNumber:'',                        //传真号
        greennessIntegral:'',                //绿度积分
        greennessLevel:'',                   //绿度等级
        industry:'',                         //行业
        orgName:'',                          //租户机构唯一编码
        introduction:'',                     //企业介绍
        legalRepresentative:'',              //法人代表
        remarks:'',                          //备注
        socialCreditCode:'',                 //统一社会信用代码
        telephone:'',                        //电话
        tenantStatus:'',                     //状态
        type:'',                             //类型
        updatedId:'',                        //更新人ID
        updatedTime:'',                      //更新时间
        validityDayNum:'',                   //有效天数
        validityTime:'',                     //有效期
      },
      accountStatusObj:{},
      accountTypeObj:{},
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
      // this.selectedRole = row.accountRole.roleIds[0];
      this.addForm = row;
      this.addForm.tenantStatusName = this.getTenantStatusName(row.tenantStatus);
      this.addForm.tenantTypeName = this.getTenantTypeName(row.type);
      for (var i in this.addForm) {
        if (this.addForm[i] == "") {
          this.addForm[i] = "--";
        }
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
     * 时间: 2022-08-26 16:07:51
     * 功能: 新增租户
     */
    addTenant() {
      this.addFormVisible = true;
      this.readFormVisible = false;
      this.selectedRole = "";
    },
    /**
     * 作者:
     * 时间: 2022-08-26 14:27:17
     * 功能: 删除账户
     */
    onClickDelete(id) {
      this.$confirm("确认删除当前租户？").then((res) => {
        delTenantById(id).then(
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

    addSubmit() {
      if(this.form.tenantName && this.form.telephone && this.form.contactsEmail && this.form.tenantStatus && this.form.type && this.form.orgName && this.form.validityTime){
        // if(this.form.tenantName && this.form.type && this.form.validityTime && this.form.contactsPhone && this.form.address && this.form.contactsEmail ){
        let verifyPhone = verifyPhoneNumber(this.form.telephone);
        if(!verifyPhone){
          return this.$message("请输入正确的手机号！");
        }
        let verifyEma = emailVerify(this.form.contactsEmail);
        if(!verifyEma){
          return this.$message("请输入正确的邮箱！");
        }
        addTenantUser(this.form).then( (res) => {
            this.$message.success("添加成功");
            this.addFormVisible = false;
            this.getList(1);
          },
          (err) => {
            this.$message.error(err.msg);
          }
        );
      } else{
        this.$message.warning("必填项不能为空");
      }
    },
    /**
     * 作者:
     * 时间: 2022-05-30 15:18:05
     * 功能: 编辑租户
     */
    onEdit(row) {
      this.tenant = {};
      this.isSaved = false;
      this.editFormVisible = true;
      this.readFormVisible = false;
      this.addForm.id = row.id;
      this.addForm = row;
      for (var i in this.addForm) {
        if (this.addForm[i] == "--") {
          this.addForm[i] = "";
        }
      }
      this.addForm.accountStatus = row.accountStatus;
      if (row.tenantId != "--" && row.tenantId) {
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
    getTenantStatusName(row){
      if(row && row != '--'){
        let accountStatusObj = this.accountStatusObj;
        return accountStatusObj[row] || '--';
      }else{
        return row;
      }
    },
    getTenantTypeName(row){
      if(row && row != '--'){
        let accountTypeObj = this.accountTypeObj;
        return accountTypeObj[row] || '--';
      }else{
        return row;
      }
    },
    getCurListNo(index){
      let curNo = parseInt(index) + 1;
      let size = this.size || '10';
      let page = this.current - 1;
      let no = setListNo(page,size,curNo);
      return no ? no : 1;
    },
    /**
     * 作者:
     * 时间: 2022-08-26 14:54:52
     * 功能: 提交保存
     */
    update() {
      if(!(this.addForm.address && this.addForm.tenantName && this.addForm.faxNumber
        && this.addForm.contactsEmail && this.addForm.contactsName && this.addForm.telephone && this.addForm.industry
        && this.addForm.tenantStatus && this.addForm.validityTime)
      ){
        return this.$message.warning("必填项不能为空");
      }

      let verifyPhone = verifyPhoneNumber(this.addForm.telephone);
      if(!verifyPhone){
        return this.$message("请输入正确的手机号！");
      }
      let verifyEma = emailVerify(this.addForm.contactsEmail);
      if(!verifyEma){
        return this.$message("请输入正确的邮箱！");
      }

      if(this.addForm) {
        let info = JSON.parse(Cookies.get("JavaInfo"));
        this.addForm.creatorId = parseInt(info.accountId)
        let data = {
          address: this.addForm.address,
          businessLicense: this.addForm.businessLicense,
          contactsEmail: this.addForm.contactsEmail,
          contactsName: this.addForm.contactsName,
          contactsPhone:  this.addForm.contactsPhone,
          contactsPosition: this.contactsPosition,
          createdTime:  this.addForm.createdTime,
          creatorId:  this.addForm.creatorId,//创建人id
          faxNumber:  this.addForm.faxNumber,
          greennessIntegral:  this.addForm.greennessIntegral,
          greennessLevel:  this.addForm.greennessLevel,
          id: this.addForm.id,
          industry:  this.addForm.industry,
          introduction:  this.addForm.introduction,
          legalRepresentative:  this.addForm.legalRepresentative,
          // orgName:  this.addForm.orgName,
          remarks:  this.addForm.remarks,
          socialCreditCode:  this.addForm.socialCreditCode,
          telephone:  this.addForm.telephone,
          tenantName: this.addForm.tenantName,
          tenantStatus:  this.addForm.tenantStatus,
          type:  this.addForm.type,
          updatedId:  this.addForm.updatedId,
          updatedTime:  this.addForm.updatedTime,
          validityTime:  this.addForm.validityTime,
          validityDayNum:  this.addForm.validityDayNum
        };
        // return console.log("data",data);
        updatedTenant(data).then(
          (res) => {
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
      getTenantPageList(search).then(
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
        searchKey: this.searchKeyWord,
        type: this.selectedType[0],
        productVersion: this.selectedVersion[0],
      };

      if(this.selectedStatus[0]){
        search["tenantStatus"] = this.selectedStatus[0];
      }
      getTenantPageList(search).then(
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
      debugger
      const data = {
        asc: true,
        size: this.pageSize,
        current: this.current,
        // status: 1,
      };
      getTenantPageList(data)
        .then((res) => {
          this.list = res.records;
          this.total = Number(res.total);
          this.current = Number(res.current);
          this.pageCount = Math.ceil(parseInt(res.total) / this.pageSize);
          // return console.log("res",res);
          this.list.map((v) => {
            for (var i in v) {
              v[i] = v[i] ? v[i] : "--";
              if (v[i] == " ") {
                v[i] = "--";
              }
            }
          });
        })
        .catch((error) => { });
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
        this.accountStatusObj[v.value] = v.name;
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
        this.accountTypeObj[v.value] = v.name;
        this.tenantTypeDict.push(CertificationItem);
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
//  /deep/.el-form-item__label{
//     text-align: left !important;
//   }
</style>
