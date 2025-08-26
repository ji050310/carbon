<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="cardBody">
          <span class="header">{{ title }}</span>
          <!-- <span class="createTips">张三 2021-10-10 10:00:00 创建</span>
          <span class="updateTips">李四 2021-10-10 10:00:00 更新</span> -->
          <el-divider></el-divider>
          <div class="outer20 flex-col" @click="toChangePage">
            <span class="info5">1&nbsp;填写项目基本信息</span>
          </div>
          <div class="outer21 flex-col">
            <span class="word9">2&nbsp;上传项目业主资料</span>
          </div>
          <div class="changeLine"></div>
          <img src="@/assets/icon/icon_plant.png" alt="" class="icon" />
          <span class="asset-little-title">基本信息</span>
          <br />
          <br />
          <div class="right">
            <el-row>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">业主法人姓名<span style="color: red">*</span></span>
                  <div class="selectbox-deliver" />
                  <input class="selectbox-input" v-model="fileForm.legalPersonName" placeholder="输入业主法人姓名" clearable />
                </div>
              </el-col>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">法人联系方式<span style="color: red">*</span></span>
                  <div class="selectbox-deliver" />
                  <input class="selectbox-input" v-model="fileForm.legalPersonPhone" placeholder="输入法人联系方式" clearable />
                </div>
              </el-col>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">法人营业执照</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file1Url)">{{ fileNames.file1 }}</span>
                  <el-upload class="upload-demo" ref="upload1" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file1"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <div style="clear: both; height: 40px"></div>
              <el-col :span="12">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">开发负责人姓名<span style="color: red">*</span></span>
                  <div class="selectbox-deliver" />
                  <input class="selectbox-input" v-model="fileForm.principalName" placeholder="输入负责人姓名" clearable />
                </div>
              </el-col>
              <el-col :span="12">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">开发负责人电话<span style="color: red">*</span></span>
                  <div class="selectbox-deliver" />
                  <input class="selectbox-input" v-model="fileForm.principalPhone" placeholder="输入负责人电话" clearable />
                </div>
              </el-col>
            </el-row>
          </div>

          <!-- <hr> -->
          <div style="clear: both; height: 20px"></div>
          <img src="@/assets/icon/icon_plant.png" alt="" class="icon" />
          <span class="asset-little-title">材料信息</span>
          <br />
          <br />
          <div class="right">
            <el-row>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 110px">项目核准/批复文件</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file2Url)">{{ fileNames.file2 }}</span>
                  <el-upload class="upload-demo" ref="upload2" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file2"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">可行性研究报告</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file3Url)">{{ fileNames.file3 }}</span>
                  <el-upload class="upload-demo" ref="upload3" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file3"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">环评批复文件</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file4Url)">{{ fileNames.file4 }}</span>
                  <el-upload class="upload-demo" ref="upload4" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file4"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <div style="clear: both; height: 40px"></div>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">环评报告表</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file5Url)">{{ fileNames.file5 }}</span>
                  <el-upload class="upload-demo" ref="upload5" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file5"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">节能评估报告</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file6Url)">{{ fileNames.file6 }}</span>
                  <el-upload class="upload-demo" ref="upload6" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file6"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 120px">项目开工建设证明文件</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file7Url)">{{ fileNames.file7 }}</span>
                  <el-upload class="upload-demo" ref="upload7" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file7"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <div style="clear: both; height: 40px"></div>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 110px; color: ">项目投产证明文件</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file8Url)">{{ fileNames.file8 }}</span>
                  <el-upload class="upload-demo" ref="upload8" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file8"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 80px">施工合同</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file9Url)">{{ fileNames.file9 }}</span>
                  <el-upload class="upload-demo" ref="upload9" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file9"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <el-col :span="8">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 110px">主要设备购买合同</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file10Url)">{{ fileNames.file10 }}</span>
                  <el-upload class="upload-demo" ref="upload10" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file10"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <div style="clear: both; height: 40px"></div>
              <el-col :span="12">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 110px">银行贷款合同/承诺书</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file11Url)">{{ fileNames.file11 }}</span>
                  <el-upload class="upload-demo" ref="upload11" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file11"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
              <el-col :span="12">
                <div style="margin-right: 15px" class="selectbox-root">
                  <span class="selectbox-hint" style="min-width: 100px">委托开发合同</span>
                  <div class="selectbox-deliver" />
                  <span class="selectbox-hint" style="color: #24a776; cursor: pointer"
                    @click="openUrl(fileNames.file12Url)">{{ fileNames.file12 }}</span>
                  <el-upload class="upload-demo" ref="upload12" :action="upLoadParams.url"
                    :headers="{ token: upLoadParams.token }" :auto-upload="true" :on-success="file12"
                    :show-file-list="false">
                    <img src="@/assets/icon/icon-upload.png" class="icon" slot="trigger"
                      style="position: relative; top: 8px; cursor: pointer" />
                  </el-upload>
                </div>
              </el-col>
            </el-row>
            <div style="clear: both; height: 20px"></div>
            <button style="width: 96px; float: right; margin-right: 17px" class="light-green-btn" @click="onSubmit">
              提交
            </button>
            <button style="width: 96px; float: right; margin-right: 20px" class="light-green-btn" @click="onSave(true)">
              保存
            </button>
            <button style="width: 96px; float: right; margin-right: 20px" class="normal-white-btn" @click="onCancel">
              上一步
            </button>
          </div>
          <div style="clear: both; height: 20px"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as credit from "@/api/carbonAssetApi";
import { getUpLoadProjectParams } from "@/api/tenant";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { getFeiShuUpLoadProjectParams } from "@/api/tenant";
export default {
  name: "ownerADD",
  data() {
    return {
      methodList: [], //方法学字典
      provinceList: [], //省份
      cityList: [], //城市
      upLoadParams: {
        token: "",
        url: "",
      },
      // CorporateLicenseName: "", //法人营业执照文件名
      CorporateLicenseName: "",
      // fileListName:{},
      form: {
        id: "",
        name: "",
        carbonMethodology: "",
        projectName: "",
        ownerName: "",
        country: "",
        province: "",
        city: "",
        fileList: null,
        address: "",
        assetsDevelopAgency: "",
        projectIntroduction: "",
        projectStatus: "", //已创建
        projectType: "",
        approvalDate: "",
        legalPersonName: "",
        legalPersonPhone: "",
        principalName: "",
        principalPhone: "",
      },
      fromPath: "",
      id: 0,
      isEdit: false,
      title: "",
      fileNames: {
        file1: "",
        file1Url: "",
        file2: "",
        file2Url: "",
        file3: "",
        file3Url: "",
        file4: "",
        file4Url: "",
        file5: "",
        file5Url: "",
        file6: "",
        file6Url: "",
        file7: "",
        file7Url: "",
        file8: "",
        file8Url: "",
        file9: "",
        file9Url: "",
        file10: "",
        file10Url: "",
        file11: "",
        file11Url: "",
        file12: "",
        file12Url: "",
      },
      fileForm: {
        annexList: [],
        id: null,
        legalPersonName: "",
        legalPersonPhone: "",
        principalName: "",
        principalPhone: "",
      },
      fileList: [],
    };
  },
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      vm.fromPath = from.path; //获取上一级路由的路径
      if (from.path == "/carbon/projectCreate/projectEdit") {
        vm.title = "修改项目";
      } else {
        vm.title = "创建项目";
      }
    });
  },
  methods: {
    //上传成功处理区
    file1(res, file, fileList) {
      const f = {
        fileDictValue: "02300000013", //法人营业执照
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file1 = file.name;
      this.fileNames.file1Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file2(res, file, fileList) {
      const f = {
        fileDictValue: "0230000012", //项目核准/皮批复文件
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file2 = file.name;
      this.fileNames.file2Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file3(res, file, fileList) {
      const f = {
        fileDictValue: "0230000003", //可行性研究
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file3 = file.name;
      this.fileNames.file3Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    openUrl(url) {
      openUrlInNewWindow(url);
    },
    file4(res, file, fileList) {
      const f = {
        fileDictValue: "0230000004", //环评批复
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file4 = file.name;
      this.fileNames.file4Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    loadFiles(fileList) {
      if (fileList) {
        fileList.map((v) => {
          switch (v.fileDictValue) {
            case "0230000012":
              this.fileNames.file2 = v.fileName;
              this.fileNames.file2Url = v.url;
              break;
            case "0230000003":
              this.fileNames.file3 = v.fileName;
              this.fileNames.file3Url = v.url;
              break;
            case "0230000004":
              this.fileNames.file4 = v.fileName;
              this.fileNames.file4Url = v.url;
              break;
            case "0230000005":
              this.fileNames.file5 = v.fileName;
              this.fileNames.file5Url = v.url;
              break;
            case "0230000006":
              this.fileNames.file6 = v.fileName;
              this.fileNames.file6Url = v.url;
              break;
            case "0230000007":
              this.fileNames.file7 = v.fileName;
              this.fileNames.file7Url = v.url;
              break;
            case "0230000008":
              this.fileNames.file8 = v.fileName;
              this.fileNames.file8Url = v.url;
              break;
            case "0230000010":
              this.fileNames.file9 = v.fileName;
              this.fileNames.file9Url = v.url;
              break;
            case "0230000011":
              this.fileNames.file10 = v.fileName;
              this.fileNames.file10Url = v.url;
              break;
            case "0230000012":
              this.fileNames.file11 = v.fileName;
              this.fileNames.file11Url = v.url;
              break;
            case "0230000013":
              this.fileNames.file1 = v.fileName;
              this.fileNames.file1Url = v.url;
              break;
            case "0230000009":
              this.fileNames.file12 = v.fileName;
              this.fileNames.file12Url = v.url;
              break;
          }
        });
      }
    },
    file5(res, file, fileList) {
      const f = {
        fileDictValue: "0230000005", //环评报告
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file5 = file.name;
      this.fileNames.file5Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file6(res, file, fileList) {
      const f = {
        fileDictValue: "0230000006",
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file6 = file.name;
      this.fileNames.file6Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file7(res, file, fileList) {
      const f = {
        fileDictValue: "0230000007",
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file7 = file.name;
      this.fileNames.file7Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file8(res, file, fileList) {
      const f = {
        fileDictValue: "0230000008",
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file8 = file.name;
      this.fileNames.file8Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file9(res, file, fileList) {
      const f = {
        fileDictValue: "0230000010",
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file9 = file.name;
      this.fileNames.file9Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file10(res, file, fileList) {
      const f = {
        fileDictValue: "0230000011",
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file10 = file.name;
      this.fileNames.file10Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file11(res, file, fileList) {
      const f = {
        fileDictValue: "0230000012",
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file11 = file.name;
      this.fileNames.file11Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    file12(res, file, fileList) {
      const f = {
        fileDictValue: "0230000009",
        fileName: file.name,
        url: res.msg,
      };
      this.fileNames.file12 = file.name;
      this.fileNames.file12Url = res.msg;
      this.fileForm.annexList.push(f);
      this.$message.success("上传成功");
    },
    /**
     * 作者:
     * 时间: 2022-06-06 10:59:03
     * 功能: 保存
     */

    onSave(flag) {
      sessionStorage.setItem(
        "ownerData" + this.form.id,
        JSON.stringify(this.fileForm)
      );

      if (sessionStorage.getItem("ownerData" + this.form.id)) {
        if (flag) {
          this.$message.success("保存成功");
        }
      } else {
        this.$message.error("保存失败");
      }
    },
    toChangePage() {
      sessionStorage.setItem(
        "ownerData" + this.form.id,
        JSON.stringify(this.fileForm)
      );
      if (this.fromPath == "/carbon/projectCreate/projectEdit") {
        this.$router.push({
          path: "/carbon/projectCreate/projectEdit",
          query: { id: this.form.id },
        });
      } else {
        this.$router.push({
          path: "/carbon/projectCreate/projectAdd",
        });
      }
    },
    onCancel() {
      this.onSave(false);
      if (this.fromPath == "/carbon/projectCreate/projectEdit") {
        this.$router.push({
          path: "/carbon/projectCreate/projectEdit",
          query: { id: this.form.id },
        });
      } else {
        this.$router.push({
          path: "/carbon/projectCreate/projectAdd",
        });
      }
    },
    toProjectAdd() {
      this.onSave();
      if (this.fromPath == "/carbon/projectCreate/projectEdit") {
        this.$router.push({
          path: "/carbon/projectCreate/projectEdit",
          query: { id: this.form.id },
        });
      } else {
        this.$router.push({
          path: "/carbon/projectCreate/projectAdd",
        });
      }
    },
    getFile(event) {
      console.log(event.target.files[0]);
      // this.CorporateLicenseName = event.target.files[0].name;
      this.fileForm.push(event.target.files[0]);
    },
    addFile(file, fileList) {
      this.fileForm.push(file);
    },
    onSubmit() {
      sessionStorage.setItem(
        "ownerData" + this.form.id,
        JSON.stringify(this.fileForm)
      );
      if (this.isEdit) {
        if (
          this.fileForm.legalPersonName &&
          this.fileForm.legalPersonPhone &&
          this.fileForm.principalName &&
          this.fileForm.principalPhone
        ) {
          credit.editCarbonProject(this.form).then((res) => {
            this.$message.success("修改成功");
            this.fileForm.id = this.form.id;
            credit.uploadOwnerFile(this.fileForm).then((res) => {
              this.$router.push("/carbon/projectCreate");
            });
          });
        } else {
          this.$message.warning("必填项不能为空");
        }
      } else {
        if (
          this.fileForm.legalPersonName &&
          this.fileForm.legalPersonPhone &&
          this.fileForm.principalName &&
          this.fileForm.principalPhone
        ) {
          /* 20220826 */
          this.form.legalPersonName = this.fileForm.legalPersonName;
          this.form.legalPersonPhone = this.fileForm.legalPersonPhone;
          this.form.principalName = this.fileForm.principalName;
          this.form.principalPhone = this.fileForm.principalPhone;
          /* end */
          credit.addCarbonProject(this.form).then((res) => {
            this.fileForm.id = res.data.id;
            credit.uploadOwnerFile(this.fileForm).then((res) => {
              this.$message.success("提交成功");
              this.$router.push("/carbon/projectCreate");
            });
          });
        } else {
          this.$message.warning("必填项不能为空");
        }
      }
    },

    getOwnerList() {
      let data = this.$route.query;
      this.form = data.form;
      this.id = data.id;
      this.isEdit = data.isEdit;
      if (this.isEdit) {
        this.fileForm.legalPersonName = this.form.legalPersonName;
        this.fileForm.legalPersonPhone = this.form.legalPersonPhone;
        this.fileForm.principalName = this.form.principalName;
        this.fileForm.principalPhone = this.form.principalPhone;
        this.fileForm.annexList = this.form.annexList;
        this.fileForm.id = this.form.id;
      }
      var owner = sessionStorage.getItem("ownerData" + this.form.id);
      if (owner) {
        this.fileForm = JSON.parse(owner);
      }
      if (this.fileForm.annexList) {
        this.loadFiles(this.fileForm.annexList);
      }
    },
    onUpload() { },
  },

  mounted() {
    this.upLoadParams = getFeiShuUpLoadProjectParams();
    this.getOwnerList();
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
  margin: 10px 0px 20px 0px;
  // display: flex;
  flex-direction: row;
}

.cardBody {
  margin: 30px 30px 30px 30px;
}

.header {
  overflow-wrap: break-word;
  color: rgba(36, 167, 118, 1);
  font-size: 18px;
  font-family: PingFangSC-Medium;
}

.createTips {
  color: rgba(128, 142, 165, 1);
  font-size: 13px;
  float: right;
}

.updateTips {
  overflow-wrap: break-word;
  color: rgba(128, 142, 165, 1);
  font-size: 13px;
  float: right;
  margin-right: 20px;
}

.el-divider {
  margin-top: 20px;
}

.outer20 {
  background-color: rgba(38, 181, 129, 0.5);
  height: 38px;
  width: 194px;
  float: left;
  margin-top: 20px;
  cursor: pointer;

  .info5 {
    width: 123px;
    height: 14px;
    overflow-wrap: break-word;
    color: rgba(255, 255, 255, 1);
    font-size: 14px;
    text-align: center;
    white-space: nowrap;
    line-height: 14px;
    // display: block;
    margin: 20px 0 0 35px;
    position: relative;
    top: 12px;
  }
}

.basic-div {
  width: 1140px;
  height: 174px;
  background: #f7f9fc;
  border-radius: 6px;
  margin-left: 30px;
  // margin-top: 10px;
}

.outer21 {
  background-color: rgba(38, 181, 129, 1);
  height: 38px;
  width: 194px;
  float: left;
  margin-left: 20px;
  margin-top: 20px;

  .word9 {
    width: 126px;
    height: 14px;
    overflow-wrap: break-word;
    color: rgba(255, 255, 255, 1);
    font-size: 14px;
    text-align: center;
    white-space: nowrap;
    line-height: 14px;
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
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #424c5c;
  line-height: 16px;
  margin-left: 10px;
}

.country-span {
  width: 200px;
}

.selectbox-hint {
  text-align: left;
  // align-content: center;
  padding-left: 4px;
  padding-right: 4px;
  line-height: 36px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
}

// .left {
//   float: left;
//   width: 1px;
//   height: auto;
// }
.right {
  float: left;
  width: 100%;
  margin-left: 30px;
}

.pic2 {
  height: 110px;
  width: 2px;
  position: absolute;
  top: 220px;
  left: 80px;
}

.pic3 {
  height: 250px;
  position: absolute;
  top: 400px;
  width: 1px;
  left: 80px;
}

.projectInfo {
  height: 100%;
  width: 100%;
  background: #f7f9fc;
  border-radius: 6px;
  border: 0px solid;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
  line-height: 21px;
  text-align: justify;
}

/* 按钮样式 */
</style>