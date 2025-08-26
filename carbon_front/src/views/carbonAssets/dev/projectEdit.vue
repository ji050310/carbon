<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="cardBody">
          <span class="header">修改项目</span
          ><span class="asset-title">{{ this.form.projectName }}</span>
          <span class="createTips" style="margin-left: 10px"
          >{{ this.form.creatorName }} {{ this.form.createdTime }} 创建</span
          >
          <span class="updateTips"
          >{{ this.form.updatedName }} {{ this.form.updatedTime }} 更新</span
          >

          <el-divider></el-divider>
          <div class="outer20 flex-col">
            <span class="info5">1&nbsp;填写项目基本信息</span>
          </div>
          <div class="outer21 flex-col" @click="toChangePage">
            <span class="word9">2&nbsp;上传项目业主资料</span>
          </div>
          <div class="changeLine"></div>
          <button
            style="
              margin-top: 0px;
              margin-bottom: 20px;
              width: 119px;
              height: 36px;
            "
            class="normal-white-btn"
            @click="toMethodManager"
          >
            选择方法学
          </button>
          <el-input
            v-model="form.carbonMethodologyName"
            size="medium"
            style="width: 350px; margin-left: 16px"
            disabled
          >
          </el-input>
          <br />
          <br />
          <img src="@/assets/icon/icon_plant.png" alt="" class="icon" />
          <span class="asset-little-title">项目信息</span>
          <br />
          <br />
          <div class="right">
            <el-row>
              <el-col :span="12">
                <div
                  style="margin-right: 15px; padding: 0 0 0 0"
                  class="selectbox-root"
                >
                  <span class="selectbox-hint" style="width: 80px"
                  >项目<span style="color: red">*</span></span
                  >
                  <div class="selectbox-deliver" />
                  <el-input
                    size="medium"
                    class="selectbox-input"
                    v-model="form.projectName"
                    placeholder="输入项目名称"
                    clearable
                  />
                </div>
              </el-col>
              <el-col :span="12">
                <div style="padding: 0 0 0 0" class="selectbox-root">
                  <span class="selectbox-hint" style="width: 120px"
                  >业主名称<span style="color: red">*</span></span
                  >
                  <div class="selectbox-deliver" />
                  <el-autocomplete
                    v-model="form.ownerName"
                    size="medium"
                    style="width: 80%"
                    :fetch-suggestions="querySearchAsync"
                    placeholder="请输入内容"
                    @select="handleSelect"
                  ></el-autocomplete>
                </div>
              </el-col>
            </el-row>
            <div style="clear: both; height: 40px"></div>
            <el-row>
              <el-col :span="8">
                <div style="padding: 0 0 0 0" class="selectbox-root">
                  <span class="selectbox-hint" style="width: 70px"
                  >国家<span style="color: red">*</span></span
                  >
                  <div class="selectbox-deliver" />
                  <el-cascader
                    placeholder="全部"
                    class="selectbox-input"
                    v-model="form.country"
                    :options="countryList"
                    @change="changeCity"
                    clearable
                    size="medium"
                  >
                  </el-cascader>
                </div>
              </el-col>
              <el-col :span="8">
                <div
                  style="margin-left: 17px; padding: 0 0 0 0"
                  class="selectbox-root"
                >
                  <span class="selectbox-hint" style="width: 80px"
                  >省份<span style="color: red">*</span></span
                  >
                  <div class="selectbox-deliver" />
                  <el-cascader
                    style=""
                    placeholder="全部"
                    class="selectbox-input"
                    v-model="form.province"
                    :options="provinceList"
                    @change="changeProvince"
                    clearable
                    size="medium"
                  >
                  </el-cascader>
                </div>
              </el-col>
              <el-col :span="8">
                <div
                  style="margin-left: 17px; padding: 0 0 0 0"
                  class="selectbox-root"
                >
                  <span class="selectbox-hint" style="width: 130px"
                  >市/县/郡<span style="color: red">*</span></span
                  >
                  <div class="selectbox-deliver" />
                  <el-cascader
                    style="width: 85%"
                    placeholder="全部"
                    class="selectbox-input"
                    v-model="form.city"
                    :options="cityList"
                    @change="changeCity"
                    clearable
                    size="medium"
                  >
                  </el-cascader>
                </div>
              </el-col>
            </el-row>

            <div style="clear: both; height: 40px"></div>
            <el-row>
              <el-col :span="12">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="width: 100px"
                  >项目地点<span style="color: red">*</span></span
                  >
                  <div class="selectbox-deliver" />
                  <el-input
                    size="medium"
                    style="width: 85%"
                    class="selectbox-input"
                    v-model="form.address"
                    placeholder="请输入项目地点"
                    clearable
                  />
                </div>
              </el-col>
              <el-col :span="12">
                <div class="selectbox-root">
                  <span class="selectbox-hint" style="width: 200px"
                  >资产开发机构名称<span style="color: red">*</span></span
                  >
                  <div class="selectbox-deliver" />
                  <el-autocomplete
                    v-model="form.assetsDevelopAgency"
                    size="medium"
                    style="width: 85%"
                    :fetch-suggestions="querySearchAsync"
                    placeholder="请输入内容"
                    @select="handleSelect"
                  ></el-autocomplete>
                </div>
              </el-col>
              <!-- <hr> -->
              <div style="clear: both; height: 20px"></div>
              <img
                src="@/assets/icon/icon_plant.png"
                alt=""
                class="icon"
                style="position: relative; right: 30px"
              />
              <span
                class="asset-little-title"
                style="position: relative; right: 30px"
              >项目介绍</span
              >
              <!-- <hr> -->
              <div style="clear: both; height: 20px"></div>
              <el-col :span="24">
                <div
                  class="basic-div"
                  style="height: 189px; padding: 20px 20px 20px 20px"
                >
                  <textarea
                    v-model="form.projectIntroduction"
                    placeholder="输入项目介绍"
                    name=""
                    id=""
                    cols="30"
                    rows="10"
                    class="projectInfo"
                  ></textarea>
                </div>
              </el-col>
            </el-row>
            <div style="clear: both; height: 20px"></div>
            <button
              style="width: 96px; float: right"
              class="light-green-btn"
              @click="toNext"
            >
              下一步
            </button>
            <button
              style="width: 96px; float: right; margin-right: 20px"
              class="light-green-btn"
              @click="onSave"
            >
              保存
            </button>
            <button
              style="width: 96px; float: right; margin-right: 20px"
              :class="isReturn()"
              @click="onCancel"
            >
              返回
            </button>
          </div>
        </div>
        <!-- 选择方法学 -->
        <el-dialog
          title="方法学列表"
          :visible.sync="dialogTableVisible"
          width="75%"
        >
          <el-row>
            <el-col :span="8">
              <div style="" class="selectbox-root">
                <a class="selectbox-hint" style="width: 95px">核证标准</a>
                <div class="selectbox-deliver" />
                <el-cascader
                  style="width: 85%"
                  size="medium"
                  placeholder="全部"
                  class="selectbox-input"
                  v-model="selectedCertification"
                  :options="Certification"
                  clearable
                  @change="update"
                >
                </el-cascader>
              </div>
            </el-col>
            <el-col :span="8">
              <div style="margin-left: 16px" class="selectbox-root">
                <a class="selectbox-hint" style="width: 60px">领域</a>
                <div class="selectbox-deliver" />
                <el-cascader
                  style="width: 80%"
                  placeholder="全部"
                  size="medium"
                  class="selectbox-input"
                  v-model="selectedAreaDict"
                  :options="projectAreaDict"
                  clearable
                  @change="update"
                >
                </el-cascader>
              </div>
            </el-col>
            <el-col :span="8">
              <div style="margin-left: 16px" class="selectbox-root">
                <a class="selectbox-hint" style="width: 90px">行业</a>
                <div class="selectbox-deliver" />
                <el-cascader
                  style="width: 80%"
                  size="medium"
                  placeholder="全部"
                  class="selectbox-input"
                  v-model="selectedIndustry"
                  :options="IndustryList"
                  clearable
                  @change="update"
                >
                </el-cascader>
              </div>
            </el-col>
          </el-row>
          <div style="width: 400px"></div>
          <el-row style="margin-top: 10px">
            <el-col :span="16">
              <div style="margin-right: 10px" class="selectbox-root">
                <a class="selectbox-hint" style="width: 120px">方法学搜索</a>
                <div class="selectbox-deliver" />
                <el-input
                  class="selectbox-input"
                  @keyup.enter.native="searchEnter"
                  v-model="searchKeyWord"
                  style="width: 80%"
                  size="medium"
                  placeholder="名称、编号、关键字等"
                  clearable
                />
              </div>
            </el-col>
            <el-col :span="8">
              <button
                class="light-green-btn"
                @click="onClickSearch"
                style="position: relative; top: 3px"
              >
                查询
              </button>
            </el-col>
          </el-row>
          <el-table :data="methodList" style="width: 100%" stripe>
            <el-table-column min-width="10"></el-table-column>
            <el-table-column
              label="编号"
              align="left"
              prop="methodCode"
              min-width="80"
            >
            </el-table-column>
            <el-table-column
              :show-overflow-tooltip="true"
              align="left"
              prop="name"
              label="名称"
              min-width="230"
            />
            <el-table-column
              align="left"
              prop="industryCodeName"
              label="行业"
              min-width="80"
            />
            <el-table-column
              align="left"
              prop="certificationCriteria"
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
                <a class="list-blue-text" @click="pickMethod(scope.row)"
                >选择</a
                >
              </template>
            </el-table-column>
          </el-table>
          <div style="margin-top: 30px; margin-bottom: 10px" class="pageBox">
            <div style="flex-grow: 1" />
            <a style="margin: auto" class="pageBox-total-num"
            >共{{ total }}条</a
            >
            <el-pagination
              style="margin: auto"
              background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="methodCurrent"
              :page-size="methodPageSize"
              :page-count="pageCount"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
            >
            </el-pagination>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import { readCarbonProject } from "@/api/carbonAssetApi";
import { loadMethodList } from "@/api/carbonAssetApi";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { getProvinceDict } from "@/config/dictHelper";
import { getCountryDict } from "@/config/dictHelper";
import { getCityDict } from "@/config/dictHelper";
import { getCertificationCriteriaDict } from "@/config/dictHelper";
import { getBusinessDict } from "@/config/dictHelper";
import { getProjectAreaDict } from "@/config/dictHelper";
import { getMethodologyDict } from "@/config/dictHelper";
import * as project from "@/api/carbonAssetApi";
import { number } from "echarts/lib/export";
import { log } from "console";
import { async } from "q";
import { string } from "clipboard";
export default {
  name: "projectEdit",
  data() {
    return {
      searchKeyWord: "",
      state: "",
      projectAreaDict: [], //项目领域字典
      selectedCertification: "", //被选中的标准
      selectedIndustry: "", //被选中的行业
      selectedAreaDict: "", //被选中的领域
      Certification: [], //核证标准字典
      IndustryList: [], //行业字典
      carbonMethodologyId: "",
      provinceList: [], //省份
      cityList: [], //城市
      dialogTableVisible: false, //是否显示方法学弹框
      methodList: [], //方法学列表
      countryList: [], //国家
      submit: false,
      isCreate: true, //是创建还是修改？
      // OwnerNameOptions: [
      //   {
      //     value: "北京我爱我家地产有限公司",
      //     label: "北京我爱我家地产有限公司",
      //   },
      //   {
      //     value: "上海我爱我家地产有限公司",
      //     label: "上海我爱我家地产有限公司",
      //   },
      //   {
      //     value: "河南我爱我家地产有限公司",
      //     label: "河南我爱我家地产有限公司",
      //   },
      // ], //搜索业主名称的提示框
      form: {
        id: "",
        name: "",
        carbonMethodology: "",
        projectName: "",
        ownerName: "",
        country: "",
        province: "",
        city: "",
        address: "",
        assetsDevelopAgency: "",
        projectIntroduction: "",
        projectStatus: "", //已创建
        projectType: "",
        approvalDate: "",
      },
      fromPath: "",
      list: [],
      next: false,
      pageCount: 0,
      total: 0,
      states: [],
      methodPageSize: 10,
      methodCurrent: 1,
    };
  },
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      vm.fromPath = from.path; //获取上一级路由的路径
    });
  },
  methods: {
    loadAll() {
      return [
        {
          value: "北京我爱我家地产有限公司",
          label: "北京我爱我家地产有限公司",
        },
        {
          value: "上海我爱我家地产有限公司",
          label: "上海我爱我家地产有限公司",
        },
        {
          value: "河南我爱我家地产有限公司",
          label: "河南我爱我家地产有限公司",
        },
      ];
    },

    isReturn() {
      if (this.fromPath == "/carbon/projectCreate/ownerAdd") {
        return "normal-white-btn-no";
      } else {
        return "normal-white-btn";
      }
    },
    //跳转到方法学管理
    toMethodManager() {
      // this.$router.push({
      //   path: "/carbon/methodology",
      // });
      this.dialogTableVisible = true;
      this.getMethodList(1);
    },
    handleCurrentChange(val) {
      this.methodCurrent = val;
      this.getMethodList(val);
    },
    handleSizeChange(val) {
      this.methodPageSize = val;
      this.getMethodList(1);
    },
    update() {
      const data = {
        asc: true,
        size: this.methodPageSize,
        searchKey: this.searchKeyWord,
        fieldCode: this.selectedAreaDict[0],
        industryCode: this.selectedIndustry[0],
        certificationCriteria: this.selectedCertification[0],
        current: this.methodCurrent,
      };
      loadMethodList(data)
        .then((res) => {
          this.methodList = res.data.records;
          this.total = res.data.total;
          this.methodCurrent = res.data.current;
          this.pageCount = Math.ceil(
            parseInt(res.data.total) / this.methodPageSize
          );
          this.methodList.map((v) => {
            v.checked = false;
            v.statusName = this.statusName(v.status);
            v.carbonMethodology = v.carbonMethodology
              ? v.carbonMethodology
              : "--";
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
    querySearchAsync(queryString, cb) {
      var restaurants = this.states;
      var results = queryString
        ? restaurants.filter(this.createStateFilter(queryString))
        : restaurants;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 1000 * Math.random());
    },
    createStateFilter(queryString) {
      return (state) => {
        return (
          state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect(item) {
      console.log(item);
    },
    toOnerPage() {
      // const data1 = {
      //   address: this.form.address,
      //   assetsDevelopAgency: this.form.assetsDevelopAgency,
      //   carbonMethodology: this.form.carbonMethodology,
      //   city: this.form.city,
      //   country: this.form.country,
      //   id: this.form.id,
      //   isApproval: false,
      //   ownerName: this.form.ownerName,
      //   projectIntroduction: this.form.projectIntroduction,
      //   projectName: this.form.projectName,
      //   projectType: this.form.projectType,
      //   province: this.form.province,
      // };
      // project.editCarbonProject(data1).then((res) => {
      //   this.$router.push({
      //     path: "/carbon/projectCreate/ownerAdd",
      //     query: { id: this.form.id },
      //   });
      // });
      this.$router.push({
        path: "/carbon/projectCreate/ownerAdd",
        query: { id: this.form.id },
      });
    },
    pickMethod(row) {
      this.form.name = row.name;
      this.form.carbonMethodology = row.methodCode;
      this.dialogTableVisible = false;
    },
    //搜索业主的提示框
    remoteMethod(query) {
      if (query !== "") {
        this.loading = true;
        if (this.OwnerNameOptions) {
          this.loading = false;
        }
      }
    },
    onSave() {
      sessionStorage.setItem(
        "projectEdit-" + this.form.id,
        JSON.stringify(this.form)
      );
      this.$message.success("保存成功");
    },
    onCancel() {
      if (this.fromPath == "/carbon/projectCreate/ownerAdd") {
        this.$message.warning("不能返回上一页");
      } else {
        this.$router.push({
          path: "/carbon/projectCreate",
          query: { page: this.current },
        });
      }
    },
    // onSubmit() {

    //   // if (this.onSave()) {
    //     this.$router.push({
    //       path: "/carbon/ownerAdd",
    //       query: { id: this.form.id },
    //     });
    //   // }

    // },
    onClickSearch() {
      const data = {
        asc: true,
        size: this.pageSize,
        searchKey: this.searchKeyWord,
        fieldCode: this.selectedAreaDict[0],
        industryCode: this.selectedIndustry[0],
        certificationCriteria: this.selectedCertification[0],
      };
      loadMethodList(data)
        .then((res) => {
          this.methodList = res.data.records;
          this.total = res.data.total;
          this.methodCurrent = res.data.current;
          this.pageCount = Math.ceil(
            parseInt(res.data.total) / this.methodPageSize
          );
          this.methodList.map((v) => {
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
    getMethodList(val) {
      const data = {
        asc: true,
        size: this.methodPageSize,
        current: val,
        // status: 1,
      };
      loadMethodList(data)
        .then((res) => {
          this.methodList = res.data.records;
          this.total = res.data.total;
          this.methodCurrent = res.data.current;
          this.pageCount = Math.ceil(
            parseInt(res.data.total) / this.methodPageSize
          );
          this.methodList.map((v) => {
            //遍历表格数据
            v.checked = false;
            v.statusName = this.statusName(v.status);
            v.carbonMethodology = v.carbonMethodology
              ? v.carbonMethodology
              : "--";
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
    // 格式化省字典
    formatProvince(data) {
      data.map((v) => {
        let CertificationItem = {
          value: "",
          label: "",
        };
        if (v.label == "全部") {
          CertificationItem.value = "";
          CertificationItem.label = v.name;
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.provinceList.push(CertificationItem);
      });
    },
    toNext() {
      sessionStorage.setItem(
        "projectEdit-" + this.form.id,
        JSON.stringify(this.form)
      );
      this.$router.push({
        path: "/carbon/projectCreate/ownerAdd",
        query: { form: this.form, isEdit: true },
      });
    },
    /**
     * 作者:
     * 时间: 2022-06-20 11:07:39
     * 功能: 切换页面
     */
    toChangePage() {
      sessionStorage.setItem("projectAdd", JSON.stringify(this.form));
      this.$router.push({
        path: "/carbon/projectCreate/ownerAdd",
        query: { form: this.form },
      });
    },
    changeCity() {
      this.form.city = this.form.city[0];
    },
    changeProvince() {
      this.form.province = this.form.province[0];
    },
    /*
     *@Description: 回车搜索
     *@MethodAuthor: liuboting
     *@Date: 2022-05-22 16:19:09
     */
    searchEnter() {
      this.onClickSearch();
    },
    // 格式化市字典
    formatCity(data) {
      data.map((v) => {
        let CertificationItem = {
          value: "",
          label: "",
        };
        if (v.label == "全部") {
          CertificationItem.value = "";
          CertificationItem.label = v.name;
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.cityList.push(CertificationItem);
      });
    },
    // 格式化国家字典
    formatCountry(data) {
      data.map((v) => {
        let CertificationItem = {
          value: "",
          label: "",
        };
        if (v.label == "全部") {
          CertificationItem.value = "";
          CertificationItem.label = v.name;
        } else {
          CertificationItem.value = v.value;
          CertificationItem.label = v.name;
        }
        this.countryList.push(CertificationItem);
      });
    },
    loadProject() {
      let id = this.$route.query;
      this.form.carbonMethodology = id.code;
      this.current = id.page;
      var method = getMethodologyDict(this.$store);
      console.log(method);
      method.map((v) => {
        if (v.value == this.form.carbonMethodology) {
          this.form.name = v.name;
        }
      });
      this.form.id = Number(id.id);
      var data = sessionStorage.getItem("projectEdit-" + this.form.id);
      if (data) {
        this.form = JSON.parse(data);
        this.form.id = Number(id.id);
      } else {
        if (id.id) {
          this.isCreate = false;
        }
        if (this.form.id) {
          readCarbonProject(this.form.id).then(
            (res) => {
              this.form = res.data;
              // this.form.name = this.form.carbonMethodologyName
            },
            (err) => {}
          );
        }
      }
    },
    // 格式化标准字典
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
        this.projectAreaDict.push(CertificationItem);
      });
    },
  },
  mounted() {
    this.formatProvince(getProvinceDict(this.$store));
    this.formatCity(getCityDict(this.$store));
    this.formatCountry(getCountryDict(this.$store));
    this.formatStatus(getCertificationCriteriaDict(this.$store));
    this.formatIndustry(getBusinessDict(this.$store));
    this.formatArea(getProjectAreaDict(this.$store));
    this.loadProject();
    this.form.country = this.countryList[0].value;
    this.states = this.loadAll();
    // this.list = this.states.map(item => {
    //   return { value: item, label: item };
    // });
    // console.log(this.methodList);
  },
};
</script>

<style lang="scss" scoped>
.root {
  background: #f2f5f7;
}

.asset-title {
  width: 396px;
  height: 18px;


  font-weight: 500;
  color: #242b35;

  margin-left: 20px;
}

.divBox {
  margin: 20px;
  background: #ffffff;
  box-shadow: 0px 2px 8px 0px #eaf0f3;
  border-radius: 8px;
}

// .container {
//   margin: 10px 0px 20px 0px;
//   display: flex;
//   flex-direction: row;
// }

.cardBody {
  margin: 30px 30px 30px 30px;
}

.header {
  overflow-wrap: break-word;
  color: rgba(36, 167, 118, 1);


}

.createTips {
  color: rgba(128, 142, 165, 1);

  float: right;
}

.updateTips {
  overflow-wrap: break-word;
  color: rgba(128, 142, 165, 1);

  float: right;
  margin-right: 20px;
}

.el-divider {
  margin-top: 20px;
}

.outer20 {
  background-color: rgba(38, 181, 129, 1);
  height: 38px;
  width: 194px;
  cursor: pointer;
  float: left;
  margin-top: 20px;

  .info5 {
    width: 123px;
    height: 14px;
    overflow-wrap: break-word;
    color: rgba(255, 255, 255, 1);

    text-align: center;
    white-space: nowrap;

    // display: block;
    margin: 20px 0 0 35px;
    position: relative;
    top: 12px;
  }
}

.basic-div {
  // width: 933px;
  height: 174px;
  border-radius: 6px;
  border: 1px solid #e3e7eb;
  // margin-left: 30px;
  // margin-top: 10px;
}

.outer21 {
  background-color: rgba(38, 181, 129, 0.5);
  height: 38px;
  width: 194px;
  float: left;
  cursor: pointer;
  margin-left: 20px;
  margin-top: 20px;

  .word9 {
    width: 126px;
    height: 14px;
    overflow-wrap: break-word;
    color: rgba(255, 255, 255, 1);

    text-align: center;
    white-space: nowrap;

    // display: block;
    margin: 20px 0 0 33px;
    position: relative;
    top: 12px;
  }
}

.changeLine {
  clear: both;
  height: 40px;
}

.icon {
  height: 16px;
  width: 22px;
}

.asset-little-title {
  width: 64px;
  height: 16px;


  font-weight: 500;
  color: #424c5c;

  margin-left: 10px;
}

.country-span {
  width: 200px;
}

// .left {
//   float: left;
//   // width: 10px;
//   height: auto;
// }

.right {
  // float: left;
  width: 100%;
  margin-left: 30px;
}

.pic2 {
  height: 180px;
  width: 2px;
  position: absolute;
  top: 335px;
  left: 80px;
}

.pic3 {
  height: 220px;
  width: 2px;
  position: absolute;
  top: 550px;
  left: 80px;
}

.projectInfo {
  height: 100%;
  width: 100%;
  border-radius: 6px;
  border: 0px solid;


  font-weight: 400;

  text-align: justify;
}

/* 按钮样式 */
</style>

