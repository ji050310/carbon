<template>
  <div class="root">
    <div class="header">
      <!-- <img class="head-icon" src="@/assets/imgs/icon_accoun_logo.jpg" /> -->
      <img v-if="baseInfo.avatar" class="head-icon" :src="baseInfo.avatar" />
      <span class="baseInfo">基本信息</span>
      <div class="div-holder" />
      <button class="light-green-btn btn-modify-psw" @click="editBaseInfo(1)">
        修改密码
      </button>
    </div>

    <div class="second-line-div">
      <div class="single-item">
        <span class="secon-line-hint" style="text-align: left">用户名</span>
        <input class="input-css" v-model="baseInfo.accountName" disabled />
      </div>

      <div class="single-item">
        <span class="secon-line-hint" style="text-align: left">姓名</span>
        <input class="input-css" v-model="baseInfo.famName" disabled />
      </div>

      <div class="single-item">
        <div class="div-holder" />
        <span class="secon-line-hint" style="text-align: left">联系电话</span>
        <input class="input-css" v-model="baseInfo.phone" disabled />
      </div>
      <div class="single-item">
        <span class="secon-line-hint" style="text-align: left">系统角色</span>
        <input
          class="input-css"
          v-model="baseInfo.accountRole.roleNames"
          disabled
        />
      </div>

      <div class="single-item">
        <span class="secon-line-hint" style="text-align: left">电子邮箱</span>
        <input class="input-css" v-model="baseInfo.email" disabled />
      </div>

      <div class="single-item">
        <div class="div-holder" />
        <span class="secon-line-hint" style="text-align: left">注册时间</span>
        <input class="input-css" v-model="baseInfo.createdTime" disabled />
      </div>
    </div>

    <div class="deliver" />
    <div style="margin-bottom:28px">
      <el-tabs v-model="activeName">
        <el-tab-pane label="登录相关账号" name="first" :key="'first'">
          <div class="card-container">
            <div class="card-div">
              <img class="icon" src="@/assets/imgs/icon_user.png" />
              <span class="tap-secon-line-hint-f ml10" style="text-align: left"
                >账号</span
              >
              <input class="input-css" v-model="baseInfo.accountName" disabled />
              <span class="second-line-final-hint">系统自动生成，可用于登录</span>
              <div class="div-holder" />
            </div>
            <div class="card-div">
              <img class="icon" src="@/assets/imgs/icon_mobile.png" />
              <span class="tap-secon-line-hint-f ml10" style="text-align: left"
                >手机号</span
              >
              <input class="input-css" v-model="baseInfo.phone" disabled />
              <span class="second-line-final-hint"
                >绑定手机号，可用于登录、重置密码或其他安</span
              >
              <div class="div-holder" />
              <button
                class="normal-white-btn btn-modify-head"
                @click="editBaseInfo(3)"
              >
                修改
              </button>
            </div>
            <div class="card-div">
              <img class="icon" src="@/assets/imgs/icon_email.png" />
              <span class="tap-secon-line-hint-f ml10" style="text-align: left"
                >邮箱</span
              >
              <input class="input-css" v-model="baseInfo.email" disabled />
              <span class="second-line-final-hint"
                >绑定邮箱，可用于登录、重置密码或其他安全</span
              >
              <div class="div-holder" />
              <button
                class="light-green-btn btn-modify-psw"
                @click="editBaseInfo(4)"
              >
                绑定
              </button>
            </div>
          </div>
        </el-tab-pane>
        <!-- 交易账号相关 -->
        <el-tab-pane
          label="交易相关账号"
          name="second"
          :key="'second'"
          size="mini"
        >
          <div class="card-container">
            <el-table
              :header-cell-style="{
                background: '#F2F5F7',
                border: '0px solid #DDDDDD',
                color: '#242B35',
                height: '64px',
              }"
              :show-header="true"
              :row-style="{ height: '64px' }"
              :cell-style="cellStyle_one"
              style="width: 100%"
              :row-class-name="tableRowClassName"
              :data="cardContainerList"
              row-key="id"
              :tree-props="{
                children: 'accountInfoList',
                hasChildren: 'hasChildren',
              }"
            >
              <el-table-column
                label="平台"
                align="left"
                min-width="110"
                height="40px"
                prop="exchangeName"
              >
                <template slot-scope="scope">
                  <span seachFrom.startDate>{{ scope.row.exchangeName }}</span>
                </template>
              </el-table-column>

              <el-table-column
                label="账号"
                align="left"
                min-width="60"
                height="40px"
                prop="accountName"
              >
                <template slot-scope="scope">
                  <span seachFrom.startDate>{{
                    scope.row.accountName || "--"
                  }}</span>
                </template>
              </el-table-column>

              <el-table-column
                label="账户状态"
                align="left"
                min-width="56"
                height="40px"
                prop="accountStatusName"
              >
                <template slot-scope="scope">
                  <div slot="empty" class="name-wrapper">
                    <span seachFrom.startDate>{{
                      scope.row.accountStatusName || "--"
                    }}</span>
                  </div>
                </template>
              </el-table-column>

              <el-table-column
                label="最近登录"
                align="left"
                min-width="120"
                height="40px"
                prop="loginTime"
              >
                <template slot-scope="scope">
                  <div slot="empty" class="name-wrapper">
                    <span size="medium">{{
                      scope.row.loginTime || "--"
                    }}</span>
                  </div>
                </template>
              </el-table-column>

              <el-table-column
                label="绑定时间"
                align="left"
                min-width="120"
                height="40px"
                prop="bindingTime"
              >
                <template slot-scope="scope">
                  <div slot="empty" class="name-wrapper">
                    <span size="medium">{{
                      scope.row.bindingTime || "--"
                    }}</span>
                  </div>
                </template>
              </el-table-column>

              <el-table-column
                label="操作"
                width="auto "
                height="40px"
                align="center"
                min-width="124"
              >
                <template slot-scope="scope">
                  <a
                    v-if="scope.row.is_disable == 1"
                    class="list-green-text"
                    @click="addAccountUser(scope.row)"
                  >
                    添加账号</a
                  >
                  <a
                    v-if="scope.row.is_disable == 0"
                    class="list-blue-text"
                    style="margin-left: 10px"
                    @click="copy(scope.row.accountName)"
                    >复制账号</a
                  >
                  <a
                    v-if="scope.row.is_disable == 0"
                    style="margin-left: 10px"
                    class="list-red-text mgn-r-16"
                    :class="{ afterSubmitOffline: scope.row.is_disable }"
                    @click="unboundWeChat(scope.row, scope.row.id, scope.$index)"
                    >解绑</a
                  >
                </template>
              </el-table-column>
            </el-table>
            <a href="#accout"></a>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
    <!-- 修改密码 -->
    <el-dialog title="修改密码" :visible.sync="padShow" width="40%">
      <el-form
        label-position="left"
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="当前密码" prop="oldPassword">
          <el-input
            type="password"
            v-model="ruleForm.oldPassword"
            autocomplete="off"
            placeholder="请输入原密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input
            type="password"
            v-model="ruleForm.newPassword"
            autocomplete="off"
            placeholder="请输入新密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="重复新密码" prop="isCheckPass">
          <el-input
            type="password"
            v-model.number="ruleForm.isCheckPass"
            placeholder="请确认新密码"
          ></el-input>
        </el-form-item>
        <div class="btn-box">
          <div></div>
          <div class="btn-close" @click="editBaseInfo(1)">取消</div>
          <div class="btn-true" @click="putAccountPwd()">保存</div>
        </div>
      </el-form>
    </el-dialog>

    <!-- 修改手机号 -->
    <el-dialog title="修改绑定的手机号" :visible.sync="ephShow" width="40%">
      <el-form
        label-position="left"
        :model="phoForm"
        status-icon
        :rules="rulePhoForm"
        ref="phoForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="新手机号" prop="newPhone">
          <el-input
            type="munber"
            v-model="phoForm.newPhone"
            autocomplete="off"
            placeholder="请输入新手机号码"
          ></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="checkCode">
          <el-input
            type="munber"
            v-model="phoForm.checkCode"
            autocomplete="off"
            placeholder="请输入验证码"
          >
            <a
              style="color: #24a776"
              v-if="times"
              type="primary"
              slot="append"
              :cell-style="cellStyle"
              >{{
                times == 0
                  ? "发送验证码"
                  : (times < 10 ? "0" + times : times) + "秒"
              }}</a
            >
            <a
              style="color: #24a776"
              v-else
              type="primary"
              @click="sendCodeSet(60)"
              :cell-style="cellStyle"
              slot="append"
              >{{
                times == 0
                  ? "发送验证码"
                  : (times < 10 ? "0" + times : times) + "秒"
              }}</a
            >
          </el-input>
        </el-form-item>

        <div class="btn-box">
          <div></div>
          <div class="btn-close" @click="editBaseInfo(3)">取消</div>
          <div class="btn-true" @click="putAccountPhone()">保存</div>
        </div>
      </el-form>
    </el-dialog>
    <!-- 绑定邮箱 -->
    <el-dialog title="绑定邮箱" :visible.sync="emShow" width="40%">
      <el-form
        label-position="left"
        :model="emailForm"
        status-icon
        :rules="emailFormRules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm text-f-ppc"
      >
        <el-form-item class="table-l" label="登录密码" prop="emailFormPassword">
          <el-input
            label-position="left"
            type="password"
            v-model="emailForm.emailFormPassword"
            autocomplete="off"
            placeholder="请输入原密码"
            class="text-f-ppc"
          ></el-input>
        </el-form-item>
        <el-form-item class="table-l text-f-ppc" label="邮箱" prop="email">
          <el-input
            type="text"
            v-model="emailForm.email"
            autocomplete="off"
            placeholder="请输入邮箱"
          ></el-input>
        </el-form-item>
        <!-- 验证码 -->
        <div class="testCode">
          <!-- 图片验证 -->
        </div>
        <div class="btn-box">
          <div></div>
          <div class="btn-close" @click="editBaseInfo(4)">取消</div>
          <div class="btn-true" @click="putAccountSendEmail()">保存</div>
        </div>
      </el-form>
    </el-dialog>
    <!-- 添加交易账户 -->
    <el-dialog title="添加交易账户" :visible.sync="carbonUploadDlg" width="40%">
      <div class="content-div">
        <div class="item-div">
          <el-form
            label-position="left"
            ref="addUserFrom"
            :rules="addUserFromRules"
            :model="addUserFrom"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item prop="carbonExchangeId" label-width="20%">
              <label slot="label"
                >交易所<span style="color: red">*</span></label
              >
              <el-cascader
                class="selectbox-root half"
                placeholder="全部"
                v-model="addUserFrom.carbonExchangeId"
                :options="exchangeList"
                clearable
              >
              </el-cascader>
            </el-form-item>
          </el-form>
        </div>
        <div class="item-div">
          <el-form
            label-position="left"
            ref="addUserFrom"
            :rules="addUserFromRules"
            :model="addUserFrom"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item prop="carbonExchangeName" label-width="20%">
              <label slot="label"
                >账号名<span style="color: red">*</span></label
              >
              <el-input
                v-model="addUserFrom.accountName"
                class="input-text"
                placeholder=""
              />
            </el-form-item>
          </el-form>
        </div>

        <div class="item-div">
          <el-form
            label-position="left"
            ref="addUserFrom"
            :rules="addUserFromRules"
            :model="addUserFrom"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item label-width="20%">
              <label slot="label"
                >账户凭证<span style="color: red">*</span></label
              >
              <el-upload
                class="upload-demo"
                ref="upload2"
                :action="upLoadParam.url"
                :file-list="tranFileList"
                :auto-upload="true"
                style="width: 180px"
                :headers="{ token: upLoadParam.token }"
                :on-change="handleTranChange"
                :on-remove="removeFileList"
                :on-success="upLoadParamTranFile"
                :on-preview="tranFilehandlePreview"
              >
                <div class="light-green-btn line-h-32">上传</div>
              </el-upload>
            </el-form-item>
          </el-form>
        </div>
        <div class="item-div">
          <el-form
            label-position="left"
            ref="addUserFrom"
            :rules="addUserFromRules"
            :model="addUserFrom"
            style="width: 100%; margin-right: 10px"
          >
            <el-form-item label="备注" prop="deliveryTime" label-width="20%">
              <label slot="label">备注</label>
              <el-input
                type="textarea"
                placeholder="请输入内容"
                rows="3"
                v-model="addUserFrom.remarks"
                class="input-mark"
              >
              </el-input>
            </el-form-item>
          </el-form>
        </div>
        <div slot="footer" class="footer-btn">
          <div></div>
          <div @click="clickSubmit" class="light-green-btn line-h-32">
            {{ btnText }}
          </div>
        </div>
      </div>
    </el-dialog>
    <!--  -->
  </div>
</template>

<script>
import {
  getListByTenant,
  getFeiShuUpLoadProjectParams,
  putAddExchangeAccount,
  getAccoutBaseInfo,
  putAccountUpdatePassword,
  putAccountSend,
  putAccountUpdatePhone,
  getAccoutUpdateCode,
  getAccoutUpdateBaseInfo,
  getAccoutUpdateAvatar,
  getUpLoadFileParams,
} from "@/api/tenant";
import { openUrlInNewWindow } from '@/libs/OpenHelper'
import { putCarbonExchangeUnbind } from "@/api/carbonAssetApi";
import { getTenantList } from "@/api/systemadmin";
import { cursor } from "@/libs/element-table";
import Cookies from "js-cookie";
import md5 from "js-md5";
import { log } from "console";

export default {
  name: "index",
  components: {},
  data() {
    var oldPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入原密码"));
      } else {
        callback();
      }
    };
    var newPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入新密码"));
      }
      callback();
    };
    var isCheckPass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value != this.ruleForm.newPassword) {
        callback(new Error("两次输入密码不一致!"));
      }
      callback();
    };
    var newPhone = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入新手机号"));
      } else {
        callback();
      }
    };
    var checkCode = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入验证码"));
      } else {
        callback();
      }
    };
    var emailFormEmail = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入邮箱"));
      } else {
        callback();
      }
    };
    var emailFormPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入登录密码"));
      } else {
        callback();
      }
    };

    return {
      upLoadParams: {}, //图片上传
      upLoadParam: {}, //文件上传
      baseInfo: {
        id: "--",
        accountName: "--",
        famName: "--",
        phone: "--",
        role: "--",
        email: "--",
        createdTime: "--",
        tenantId: "--",
        accountRole: {
          roleNames: "",
        },
        avatar: "",
      },
      padShow: false,
      emShow: false,
      ephShow: false,
      dialogTip: false,
      carbonUploadDlg: false,
      btnText: "提交",
      submitUploadText:"确认上传",
      //默认第一个选项卡
      activeName: "first",
      /* 修改密码 */
      ruleForm: {
        id: 0,
        oldPassword: "",
        newPassword: "",
        isCheckPass: "",
      },
      phoForm: {
        id: 0, //账户id
        accountName: "", //账号名
        email: "", // 	邮箱
        phone: "", //	手机号
        newPhone: "", //新手机号
        checkCode: "", //验证码
      },
      emailForm: {
        email: "",
        emailFormPassword: "",
      },
      rulePhoForm: {
        //rules 的值
        newPhone: [{ validator: newPhone, trigger: "blur" }],
        checkCode: [{ validator: checkCode, trigger: "blur" }],
      },
      emailFormRules: {
        //rules 的值
        email: [{ validator: emailFormEmail, trigger: "blur" }],
        emailFormPassword: [{ validator: emailFormPassword, trigger: "blur" }],
      },
      addUserFromRules: {},
      rules: {
        oldPassword: [{ validator: oldPassword, trigger: "blur" }],
        newPassword: [{ validator: newPassword, trigger: "blur" }],
        isCheckPass: [{ validator: isCheckPass, trigger: "blur" }],
      },
      imageUrl: "",
      byTenantList: [], //碳交易所列表

      fileList: [],
      tranFileList: [], //账户凭证
      tranFileUrl: "",
      fileListurl: "",
      times: 0,
      loading: false,
      addUserFrom: {
        //添加交易账号
        carbonExchangeName: "", //交易所名称
        accountName: "", //账户名
        accountStatus: 0, //	账户状态 1 未登录 0 已登录
        accountType: 0, //账户类型：0.管理员 1.普通账户
        bindingTime: "", //	绑定时间
        brokerId: 0, //	交易经纪人ID
        carbonAmount: 0, //	碳配额余额
        carbonExchangeId: 0, //	碳交易所ID
        ccerAmount: 0, //ccer余额额
        createdTime: "", //账户cookie
        creatorId: 0, //创建人ID
        cookie: "", //账户cookie
        id: 0, //	主键
        loginTime: "", //最近登录时间
        password: "", //	密码
        remarks: "", //	备注
        tenantId: 0, //	所属租户
        updatedId: 0, //	更新人ID
        updatedTime: "", //	更新时间
      },
      accountNameList: [], //账户列表
      exchangeList: [], //交易所列表
      cardContainerList: [], //新交易所列表
    };
  },
  mounted() {
    // this.getTenantUserList();
    this.getBaseInfo();
    this.getListByTenantInfo();
    this.upLoadParams = getUpLoadFileParams();
    this.upLoadParam = getFeiShuUpLoadProjectParams();
    if (
      this.$route &&
      this.$route.query &&
      this.$route.query.path &&
      this.$route.query.path !== ""
    ) {
      this.activeName = this.$route.query.path;
      debugger;
    }
  },
  methods: {
    cellStyle(data) {
      cursor(data);
    },
    cellStyle_one({ row, column, rowIndex, columnIndex }) {
      if(row.is_disable){
        return "padding-left:16px;cursor:pointer;";
      }else{
        return "padding-left:16px;cursor:default;";
      }
    },
    copy(text) {
      if (!text) {
        return this.$message.success("未绑定账号");
      }
      const oInput = document.createElement("input");
      oInput.value = text;
      document.body.appendChild(oInput);
      oInput.select(); // 选择对象;
      document.execCommand("Copy"); // 执行浏览器复制命令
      this.$message.success("复制成功");
      oInput.remove();
    },
    submitUpload() {
      if (!this.fileListurl) {
        return this.$message("请选择图片！");
      }
      this.submitUploadText="上传中";
      this.$message("上传中，请稍后...")
      this.$refs.upload.submit();
    },

    upLoadParamTranFile(response, file, fileList) {
      if (response) {
        this.tranFileUrl = response.msg;
        // let info = JSON.parse(Cookies.get("JavaInfo")); 
        // let datas = {
        //   accountName: this.addUserFrom.accountName, //账户名
        //   carbonExchangeId: this.addUserFrom.carbonExchangeId[0], //	碳交易所ID
        //   remarks: this.addUserFrom.remarks, //	备注
        //   tenantId: parseInt(info.accountId), //	所属租户
        //   fileUrl: response.msg,
        // };
        // putAddExchangeAccount(datas).then((res) => {
        //   this.$message.success("操作成功");
        //   this.getListByTenantInfo();
        //   this.editBaseInfo(5);
        // });
      }
    },
    sendCodeSet(num) {
      if (!this.phoForm.newPhone) {
        return this.$message("请输入新手机号");
      }
      if (this.phoForm.newPhone == this.baseInfo.phone) {
        return this.$message("新手机号与当前绑定的手机号一致!");
      }
      let r = setInterval(() => {
        if (num > 0) {
          num -= 1;
          this.times = num;
        } else {
          this.times = 0;
          clearInterval(r);
        }
      }, 1000);
      getAccoutUpdateCode(this.phoForm.newPhone);
    },
    getBaseInfo() {
      let info = JSON.parse(Cookies.get("JavaInfo"));
      getAccoutBaseInfo(parseInt(info.accountId))
        .then((res) => {
          this.baseInfo = res;
          if (!this.baseInfo.avatar || this.baseInfo.avatar == "" || (this.baseInfo.avatar).match(/^[ ]*$/)) {
            this.baseInfo.avatar = "/static/img/icon_accoun_logo.e4db51e4.jpg";
          }
          this.baseInfo.email = res.email || "--";
          this.baseInfo.famName = res.username || "--";
        })
        .catch((err) => {});
    },
    getListByTenantInfo() {
      let data = {};
      getListByTenant(data).then((res) => {
        let resLen = res.length;
        for (var i = 0; i < resLen; i++) {
          let exchangeName = res[i]["exchangeName"];
          res[i]["is_disable"] = 1;
          if (res[i]["accountInfoList"]) {
            for (var j = 0; j < res[i]["accountInfoList"].length; j++) {
              res[i]["accountInfoList"][j]["exchangeName"] = "账号"+(j+1);
              res[i]["accountInfoList"][j]["is_disable"] = 0;
            }
          }
        }
        this.cardContainerList = res;
        let resData = [],
          len = res.length;
        this.exchangeList = [];
        for (var i = 0; i < len; i++) {
          // let { carbonExchangeId,exchangeName } = res[i];
          let carbonExchangeId = res[i]["id"];
          let exchangeName = res[i]["exchangeName"];

          let item = {
            value: "",
            label: "",
          };
          item.value = carbonExchangeId;
          item.label = exchangeName;
          this.exchangeList.push(item);

          if (res[i]["accountInfoList"]) {
            for (var j = 0; j < res[i]["accountInfoList"].length; j++) {
              resData.push({
                carbonExchangeId,
                exchangeName,
                ...res[i]["accountInfoList"][j],
              });
            }
          } else {
            resData.push({ carbonExchangeId, exchangeName });
          }
        }
        this.byTenantList = resData;
      });
    },
    editBaseInfo(type) {
      switch (type) {
        case 1:
          (this.ruleForm.id = 0),
            (this.ruleForm.oldPassword = ""),
            (this.ruleForm.newPassword = ""),
            (this.ruleForm.isCheckPass = ""),
            (this.padShow = !this.padShow);
          break;
        case 3:
          this.phoForm.newPhone = null;
          this.phoForm.checkCode = null;
          this.ephShow = !this.ephShow;
          break;
        case 4:
          this.emailForm.email = null;
          this.emailForm.emailFormPassword = null;
          this.emShow = !this.emShow;
          break;
        case 5:
          this.carbonUploadDlg = !this.carbonUploadDlg;
          break;
        default:
          break;
      }
    },
    putAccountPwd(ruleForm) {
      if (!this.ruleForm.oldPassword) {
        return this.$message.error("密码不能为空");
      }
      if (!this.ruleForm.newPassword) {
        return this.$message.error("新密码不能为空");
      }
      if (this.ruleForm.isCheckPass != this.ruleForm.newPassword) {
        return this.$message.error("两次输入密码不一致!");
      }
      let datas = {
        id: this.baseInfo.id,
        newPassword: md5(this.ruleForm.newPassword),
        oldPassword: md5(this.ruleForm.oldPassword),
      };
      putAccountUpdatePassword(datas).then((res) => {
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.padShow = !this.padShow;
      });
    },

    putAccountPhone(phoForm) {
      //修改手机号
      // this.$refs[phoForm].validate((valid) => {
      // if (valid) {
      let phoDatas = {
        id: this.baseInfo.id,
        phone: this.phoForm.newPhone,
        code: this.phoForm.checkCode,
      };
      putAccountUpdatePhone(phoDatas)
        .then((res) => {
          if (res.code == 200) {
            this.getBaseInfo();
          }
          this.editBaseInfo(3);
        })
        .catch((err) => {});
      // } else {
      //   return false;
      // }
      // });
    },
    putAccountSendEmail() {
      let datas = {
        email: this.emailForm.email,
        id: this.baseInfo.id,
        password: md5(this.emailForm.emailFormPassword),
      };
      putAccountSend(datas).then((res) => {
        if (res.code == 200) {
          this.$message({
            showClose: true,
            message: res.msg,
            type: "success",
          });
          this.getBaseInfo();
        }
        this.emShow = false;
      });
    },

    resetForm(ruleForm) {},


    tranFilehandlePreview(file){
      let tranFileUrl = file.response.msg
      openUrlInNewWindow(tranFileUrl);
    },

    handleTranChange(file, fileList) {
      this.tranFileList = [];
      this.tranFileList.push(fileList[fileList.length - 1])
    },
    removeFileList(file, fileList) {
      this.tranFileList = [];
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 === 0) {
        return "warning-row";
      } else {
        return "success-row";
      }
      return "";
    },
    unboundWeChat(row, id, index) {
      if (!id) {
        return this.$message("未绑定账号");
      }
      this.$confirm(`确定解绑交易账户？`, "温馨提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      })
        .then(() => {
          putCarbonExchangeUnbind(id).then((res) => {
            if (res.code == 200) {
              this.$message(res.msg);
              this.getListByTenantInfo();
              // this.byTenantList.splice(index, 1);
            }
          });
        })
        .catch(() => {});
    },
    getTenantUserList() {
      getTenantList().then((res) => {
        if (res) {
          res.map((v) => {
            let item = {
              value: "",
              label: "",
            };
            item.value = v.id;
            item.label = v.contactsName;
            this.accountNameList.push(item);
          });
        }
      });
    },
    addAccountUser(row) {
      this.addUserFrom.accountName = null;
      this.addUserFrom.remarks = null;
      if (row.is_disable) {
        this.addUserFrom.carbonExchangeId = row.id;
      } else {
        this.addUserFrom.carbonExchangeId = row.carbonExchangeId;
      }
      this.tranFileList = [];
      this.tranFileUrl = "";
      this.editBaseInfo(5);
    },
    clickSubmit() {
      if (!this.addUserFrom.accountName) {
        return this.$message("账户名不能为空！");
      }
      if (!this.tranFileList || this.tranFileList.length == 0) {
        return this.$message("请上传账户凭证！");
      }
      let info = JSON.parse(Cookies.get("JavaInfo"));
      let datas = {
        accountName: this.addUserFrom.accountName, //账户名
        carbonExchangeId: Array.isArray(this.addUserFrom.carbonExchangeId) ? this.addUserFrom.carbonExchangeId[0]
          : this.addUserFrom.carbonExchangeId, //	碳交易所ID
        remarks: this.addUserFrom.remarks, //	备注
        tenantId: parseInt(info.accountId), //	所属租户
        accountCredentials: this.tranFileUrl,
      };
      putAddExchangeAccount(datas).then((res) => {
        this.$message.success("操作成功");
        this.getListByTenantInfo();
        this.editBaseInfo(5);
      });
    },
  },
  created() {},
};
</script>
<style scoped lang="scss">
.root {
  padding-left: 30px;
  padding-right: 30px;
  background: #ffffff;
  box-shadow: 0px 2px 8px 0px #eaf0f3;
  border-radius: 8px;
  margin: 28px;
  overflow: auto;
}
.header {
  display: flex;
  flex-direction: row;
  padding-top: 40px;
}
.head-icon {
  align-content: center;
  width: 56px;
  height: 56px;
  border-radius: 28px;
  margin-right: 20px;
}
.baseInfo {
  text-align: center;
  margin: auto;
  /* margin-top: 18px; */
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #242b35;
}
.div-holder {
  flex-grow: 1;
}
.btn-modify-psw {
  margin: auto;
}
.btn-modify-head {
  margin-left: 42px;
  margin: auto;
}

.second-line-div {
  display: grid;
  grid: auto / auto auto auto;
  grid-gap: 20px;
}
.single-item {
  margin-top: 20px;
  display: flex;
  flex-direction: row;
}
.secon-line-hint {
  text-align: center;
  align-self: center;
  width: 64px;
  font-size: 16px;
  margin-right: 30px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3a4063;
}
.second-line-text {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
}

.deliver {
  margin-top: 40px;
  margin-bottom: 40px;
  height: 1px;
  border: 1px dashed #dadee5;
}
.tap-secon-line-hint-f {
  text-align: center;
  align-self: center;
  width: 64px;
  font-size: 16px;
  margin-right: 30px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3a4063;
}
/*  */
.card-container {
  display: flex;
  flex-direction: column;
}
.card-div {
  margin-top: 20px;
  display: flex;
  flex-direction: row;
}
.icon {
  width: 18px;
  height: 18px;
  margin: auto;
}
.second-line-final-hint {
  margin: auto;
  margin-left: 100px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #5e6c84;
}
.btn-modify-psw {
  margin: auto;
}
.btn-modify-head {
  margin: auto;
}
.div-holder {
  flex-grow: 1;
}
.secon-line-hint-sec {
  text-align: center;
  align-self: center;
  width: 144px;
  font-size: 16px;
  margin-right: 30px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3a4063;
}
.bon {
  width: 118px;
  height: 40px;
  background: #ffffff;
  border-radius: 4px;
  border: 1px solid #e3e7eb;
  padding: 13px 24px;
}
.bot-text {
  width: 70px;
  height: 14px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #24a776;
  line-height: 14px;
}
.box-input {
  max-width: 396px;
  height: 40px;
  background: #ffffff;
  border-radius: 4px;
  border: 1px solid #e3e7eb;
  padding-left: 12px;
}

.table-l .el-form-item__label {
  text-align: left;
}

/* 头像  */
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.log-btn-text {
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #5e6c84;
  line-height: 12px;
}

.bon-gre {
  width: 84px;
  height: 14px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #26b581;
  line-height: 14px;
}
.bon-red {
  width: 28px;
  height: 14px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #fa5252;
  line-height: 14px;
}
.bon-mgn-lr {
  margin: 0px 20px;
}
.bon-blu {
  width: 56px;
  height: 14px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #228be6;
  line-height: 14px;
}
.text-center {
  text-align: center;
}
.btn-box {
  display: flex;
  text-align: center;
  line-height: 32px;
  font-family: PingFangSC-Regular, PingFang SC;
}
.btn-box .btn-close {
  width: 80px;
  height: 32px;
  border-radius: 2px;
  border: 1px solid #d0d5de;
  margin: 0 10px;
  cursor: pointer;
  font-family: PingFangSC-Regular, PingFang SC;
}
.btn-box .btn-close:active {
  opacity: 0.8;
}
.btn-box .btn-true {
  width: 80px;
  height: 32px;
  background: #26b581;
  border-radius: 3px;
  color: white;
  cursor: pointer;
  line-height: 34px;
  font-family: PingFangSC-Regular, PingFang SC;
}
.btn-box .btn-true:active {
  opacity: 0.8;
}
.btn-box div:nth-child(1) {
  flex: 1;
}

.more-btn {
  display: a;
  text-align: right;
}
.select-pic {
  width: 80px;
  height: 32px;
  text-align: center;
  margin-top: auto;
  margin-bottom: auto;
  border-radius: 2px;
  cursor: pointer;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  border: 1px solid #26b581;
  color: #f8f8f8;
  background: #26b581;
  border-radius: 3px;
  line-height: 32px;
  &:active {
    color: #26b581;
    background-color: #e5f9f0;
  }
}
.line-h-32 {
  line-height: 32px;
}
.footer-btn {
  display: flex;
  justify-content: space-between;
}

/deep/ #tab-more {
  width: calc(100% - 10px);
  text-align: right;
}
/deep/ .el-dialog__title {
  font-size: 20px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #3a4063;
  line-height: 20px;
}
/deep/ .el-form-item__label {
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3a4063;
}
/deep/ .el-input__inner {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
}
/deep/ .text-f-ppc {
  font-family: PingFangSC-Regular, PingFang SC;
}

/deep/ .el-tabs__nav {
  width: calc(100% - 200px);
}
/deep/ .el-table th.el-table__cell > .cell {
  padding-left: 16px;
}
/deep/ .mgn-l-16 {
  padding-left: 16px;
}
/deep/ .mgn-l-3 {
  margin-left: 3px;
}
/deep/ .mgn-r-16 {
  padding-right: 16px;
}
.el-tabs {
  flex: 1;
  /deep/ .el-tabs__item.is-active {
    color: #26a872;
  }
  /deep/ .el-tabs__item:hover {
    color: #26a872;
  }
  /deep/ .el-tabs__active-bar.is-top {
    background: #26a872;
  }
  /deep/ .el-tabs__item {
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #5e6c84;
  }
}
</style>
