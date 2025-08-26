<template>
  <div class="root">
    <div class="divBox">
      <div class="content-container">
        <div class="cardBody">
          <span class="header">项目详情</span
          ><span class="asset-title">{{ assetDetail["projectName"] }}</span>
          <el-divider></el-divider>
          <img src="@/assets/icon/icon_plant.png" alt="" class="icon" />
          <span class="asset-little-title">基本信息</span>
          <div class="basic-div">
            <div class="item-div">
              <span class="item-title">国家</span> <br /><span
                class="item-content"
                >{{ assetDetail["projectCountry"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">省份</span> <br /><span
                class="item-content"
                >{{ assetDetail["projectProvince"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">项目领域</span> <br /><span
                class="item-content"
                >{{ assetDetail["projectScope"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">项目状态</span> <br /><span
                class="item-content"
                >{{ assetDetail["projectStatus"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">项目地点</span> <br /><span
                class="item-content"
                >{{ assetDetail["projectSite"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">签发日期</span> <br /><span
                class="item-content"
                >{{ assetDetail["issuingDate"] | formatDate }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">采用方法学</span> <br /><span
                class="item-content"
                >{{ assetDetail["methodologyName"] }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">开发机构</span> <br /><span
                class="item-content"
                >{{ assetDetail["projectDeveloper"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">开发负责人</span>
              <br /><span class="item-content">{{
                assetDetail["projectAuditor"] | format
              }}</span>
            </div>
            <div class="item-div">
              <span class="item-title">核证机构</span>
              <br /><span class="item-content">{{
                assetDetail["projectVerifierCodeName"] | format
              }}</span>
            </div>
            <div class="item-div">
              <span class="item-title">核证标准</span>
              <br /><span class="item-content">{{
                assetDetail["certifiedStandard"] | format
              }}</span>
            </div>
          </div>
          <br />
          <br />
          <img src="@/assets/icon/icon_plant.png" alt="" class="icon" />
          <span class="asset-little-title">项目信息</span
          ><span class="detail" @click="toDetail" v-if="Url"
            >查看详情<i class="el-icon-d-arrow-right"></i
          ></span>
          <div
            class="basic-div"
            style="padding: 20px 16px 20px 16px; height: auto"
          >
            <p class="paragraph">
              {{ assetDetail["content"] | format }}
            </p>
          </div>
          <br />
          <br />
          <img src="@/assets/icon/icon_plant.png" alt="" class="icon" />
          <span class="asset-little-title">业主信息</span
          ><span class="detail" @click="toDetail" v-if="Url"
            >查看详情<i class="el-icon-d-arrow-right"></i
          ></span>
          <div class="basic-div" style="height: 174px">
            <div class="item-div">
              <span class="item-title">业主名称</span> <br /><span
                class="item-content"
                >{{ assetDetail["projectOwner"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">业主法人姓名</span> <br /><span
                class="item-content"
                >{{ assetDetail["legalPersonName"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">法人联系方式</span> <br /><span
                class="item-content"
                >{{ assetDetail["legalPersonPhone"] | format }}</span
              >
            </div>
            <div class="item-div">
              <span class="item-title">业主营业执照</span>
              <br /><span class="item-content">--</span>
            </div>
          </div>
          <br />
          <br />
          <img src="@/assets/icon/icon_plant.png" alt="" class="icon" />
          <span class="asset-little-title">材料信息</span>
          <div class="fileDiv" style="">
            <div class="item-div">
              <span class="item-title">{{ assetDetail["type"] }}</span
              ><br /><span
                class="item-content"
                style="color: #126fff; cursor: pointer"
                @click="toFile(assetDetail.url)"
                >{{ assetDetail["title"] }}</span
              >
            </div>
            <div class="item-div" v-if="assetDetail['annexList']">
              <span class="item-title">--</span>
              <br /><span class="item-content"></span>
            </div>
          </div>
          <br />
          <div class="basic-div-bottom">
            <button
              style="width: 96px"
              class="normal-white-btn"
              @click="goback"
            >
              返回
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { readCarbonProject } from "@/api/carbonAssetApi";
import { openUrlInNewWindow } from "@/libs/OpenHelper";
import { getExchangeDict } from "@/config/dictHelper";
import { getDiliveryMethodeDict } from "@/config/dictHelper";
import { getProjectLibDetail } from "@/api/systemSetting";
import * as credit from "@/api/carbonAssetApi";
import { log } from "util";
export default {
  name: "",
  data() {
    return {
      assetId: 0,
      assetDetail: {},
      Url: "",
      dialogFormVisible: false, //场外报价显示与否
      exchangeList: [],
      tradeMethods: [],
      ownerRunCard: {
        fileName: "--",
        fileDictValueName: "--",
        fileDictValue: "--",
        url: "",
      }, //业主营业执照
      form: {
        id: 0,
        tradeQuantity: "", //出售数量
        negotiatedPrice: "", //出售单价
        expirationDate: "", //出售截止时间
        deliveryTime: "", //期望交割时间
        deliveryMethod: "", //期望交割方式
        deliveryExchange: "", //期望交割地点
        projectType: "", //项目类型
        tradeRole: "0270000002", //方向
        status: "",
        assetType: "0140000001", //资产类型
        institutionName: "",
      },
      annexList: [],
      //校验规则
      rules: {
        tradeQuantity: [
          { required: true, message: "请输入出售数量", trigger: "blur" },
        ],
        negotiatedPrice: [
          { required: true, message: "请输入出售单价", trigger: "blur" },
        ],
        expirationDate: [
          {
            type: "date",
            required: true,
            message: "请选择日期",
            trigger: "change",
          },
        ],
        deliveryTime: [
          {
            type: "date",
            required: true,
            message: "请选择时间",
            trigger: "change",
          },
        ],
        deliveryMethod: [
          {
            required: true,
            message: "请选择交割方式",
            trigger: "change",
          },
        ],
        deliveryExchange: [
          {
            required: true,
            message: "请选择交割场所",
            trigger: "change",
          },
        ],
      },
      title: "",
    };
  },
  filters: {
    formatDate(val) {
      if (val) {
        var day = val.split(" ");
        return day[0];
      } else {
        return val;
      }
    },
    format(val) {
      if (val == "") {
        return "--";
      }
      if (!val) {
        return "--";
      }
      return val;
    },
  },
  methods: {
    toDetail() {
      openUrlInNewWindow(this.Url);
    },
    toFile(url) {
      openUrlInNewWindow(url);
    },
    goback() {
      this.$router.go(-1);
    },
    loadDetail() {
      let id = this.$route.query;
      console.log(id);
      var refId = id.refId;
      var typeCode = id.typeCode;
      console.log(id);
      getProjectLibDetail(refId, typeCode).then(
        (res) => {
          this.assetDetail = res;
          for (var i in this.assetDetail) {
            this.assetDetail[i] = this.assetDetail[i]
              ? this.assetDetail[i]
              : "--";
            if (this.assetDetail[i] == " ") {
              this.assetDetail[i] = "--";
            }
          }
        },
        (err) => {
          this.$message.success("加载数据失败");
        }
      );
    },
    // // 场外报价按钮
    // ousideTransaction() {
    //   this.dialogFormVisible = true;
    //   this.form.id = this.assetDetail["id"];
    //   this.form.projectType = this.assetDetail["fieldChildCode"];
    //   this.form.assetType = this.assetDetail["transactionStatus"];
    //   this.form.institutionName = this.assetDetail["projectName"];
    //   this.title = this.assetDetail["projectName"];
    // },
  },
  mounted() {
    this.loadDetail();
    this.exchangeList = getExchangeDict(this.$store);
    this.tradeMethods = getDiliveryMethodeDict(this.$store);
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
  display: flex;
  flex-direction: row;
}

.cardBody {
  margin: 30px 30px 30px 30px;
}

.header {
  width: 95px;
  height: 18px;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #24a776;
  line-height: 18px;
}

.asset-title {
  width: 396px;
  height: 18px;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #242b35;
  line-height: 18px;
  margin-left: 20px;
}

.el-divider {
  margin-top: 30px;
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

.basic-div {
  width: 95%;
  height: 261px;
  background: #f7f9fc;
  border-radius: 6px;
  margin-left: 30px;
  margin-top: 30px;
}

.basic-div-bottom {
  width: 95%;
  height: 174px;
  text-align: right;
  margin-left: 30px;
  margin-top: 15px;
}

.item-title {
  width: 56px;
  height: 14px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #5e6c84;
  line-height: 14px;
}

.item-content {
  width: 42px;
  height: 15px;
  font-size: 15px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 15px;
  position: relative;
  top: 15px;
}

.item-div {
  float: left;
  margin: 24px 10px 20px 10px;
  width: 200px;
  height: 40px;
  // overflow: hidden;
}

.accessory {
  width: 28px;
  height: 14px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #126fff;
  line-height: 14px;
  margin-left: 10px;
  cursor: pointer;
}

.paragraph {
  width: 95%;
  height: 108px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #808ea5;
  line-height: 21px;
  text-align: justify;
}

.detail {
  width: 56px;
  height: 14px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #24a776;
  line-height: 14px;
  margin-left: 15px;
  cursor: pointer;
}

.fileDiv {
  width: 95%;
  min-height: 261px;
  background: #f7f9fc;
  border-radius: 6px;
  margin-left: 30px;
  margin-top: 30px;
}
</style>