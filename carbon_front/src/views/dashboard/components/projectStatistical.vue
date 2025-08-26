<template>
  <div class="root">
    <div>
      <a class="nav-title" href="/carbon/projectDo">项目统计</a>
      <!-- <a class="nav-subtitle">{{ quotation.statDate }}</a> -->
    </div>
    <div class="card-container">
      <div class="chart-container" style="width: 300px">
        <div class="bd19 flex-col">
          <div class="mod20 flex-row">
            <div class="TextGroup11 flex-col">
              <div class="mod51 flex-col justify-between">
                <span class="word78">{{ projectStat.reductionTotal }}</span>
                <span class="txt35">tCO2e</span>
              </div>
            </div>
            <!-- <div class="TextGroup11 flex-col">
              <div class="mod51 flex-col justify-between">
                <span class="word78">50,000</span>
                <span class="txt35">tCO2e</span>
              </div>
            </div> -->
          </div>
          <div class="mod21 flex-row">
            <span class="word79">{{ projectStat.approvedCount }}个</span>
            <span class="word80">预计减排量</span>
          </div>
          <div class="mod22 flex-row">
            <span class="word81">累计审定项目</span>
            <span class="word82">{{ projectStat.filingCount }}个</span>
          </div>
          <div class="mod23 flex-row">
            <span class="word83">{{ projectStat.singCount }}个</span>
            <span class="info40">累计备案项目</span>
          </div>
          <div class="TextGroup12 flex-col">
            <span class="info41">累计签发项目</span>
          </div>
        </div>
      </div>

      <el-table
        :header-cell-style="{ textAlign: 'center' }"
        class="table-div"
        :cell-style="{ 'text-align': 'center' }"
        :data="projectStat.projectList"
        stripe
        style="width: 100%"
        max-height="368"
      >
        <el-table-column prop="projectName" label="项目名称" width="180">
        </el-table-column>
        <el-table-column  prop="reduction" label="预计减排量(tCO2e)" width="180">
        </el-table-column>
        <el-table-column  prop="carbonValuation" label="碳资产估值(¥)">
        </el-table-column>
        <el-table-column prop="developmentStateName" label="项目状态">
        </el-table-column>
        <!-- <el-table-column prop="tradeStateName" label="交易状态"></el-table-column> -->
      </el-table>
    </div>
  </div>
</template>
<script>
import * as echarts from "echarts";
import { getHomePanelData } from "@/api/homeApi.js";
import { getToken } from "@/utils/auth";
export default {
  name: "projectStatistical",
  data() {
    return {
      // projectList: this.projectStat.projectList
    };
  },

  props: {
    projectStat: {},
  },
  mounted() {
    // this.projectList = this.projectStat.projectList
  },

  computed: {},

  methods: {},
  created() {},
};
</script>
<style lang="scss" scoped>
.root {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  margin-left: 20px;

  .card-container {
    margin-top: 6px;
    margin-right: 24px;
    display: flex;
    flex-direction: row;
    height: 368px;
    background: #ffffff;
    box-shadow: 0px 2px 8px 0px #eaf0f3;
    border-radius: 8px;
  }
}

.nav-title {
  margin-top: 20px;


  font-weight: 500;
  color: #080b0d;
  cursor: default;
}

.nav-subtitle {
  margin-left: 12px;


  font-weight: 400;
  color: #5e6c84;
}

.chart-container {
  display: flex;
  flex-direction: column;
  margin-left: 80px;
  margin-top: 30px;
  margin-bottom: 30px;
  width: 180px;
}

.chart-top-text {
  text-align: center;


  font-weight: 500;
  color: #424c5c;
}

.chart-unit {
  text-align: center;


  font-weight: 400;
  color: #5e6c84;
  // margin-left: 60px;
}

.chart-info-bg {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  width: 201px;
  height: 111px;
  background-image: url("../../../assets/imgs/bg_chart_info.png");
  background-repeat: no-repeat;
}

.green-point {
  border-radius: 5px;
  width: 10px;
  height: 10px;
  margin-top: 5px;
  // margin-left: 20px;
  margin-right: 10px;
  background: linear-gradient(180deg, #4edbb7 0%, #26b581 100%);
}

.blue-point {
  border-radius: 5px;
  width: 10px;
  height: 10px;
  margin-top: 5px;
  // margin-left: 20px;
  margin-right: 10px;
  background: linear-gradient(180deg, #009eff 0%, #0065ff 100%);
}

.lint-blue-point {
  border-radius: 5px;
  width: 10px;
  height: 10px;
  margin-top: 5px;
  // margin-left: 20px;
  margin-right: 10px;
  background: linear-gradient(180deg, #2fd5e1 0%, #15aabf 100%);
}

.info-line {
  // margin-top: 10px;
  display: flex;
  flex-direction: row;

}

.hint-text {


  font-weight: 400;
  color: #424c5c;
}

.table-div {
  margin-left: 10px;
  margin-right: 24px;
  margin-top: 30px;
  margin-bottom: 30px;
}

.wrap-div {
  margin: auto;
}

.bd19 {
  height: 293px;
  background: url("../../../assets/imgs/bg_project_stati.png") 100% no-repeat;
  width: 239px;

  .mod20 {
    width: 68px;
    height: 39px;
    margin: 30px 0 0 156px;

    .TextGroup11 {
      height: 39px;
      width: 68px;

      .mod51 {
        width: 68px;
        height: 39px;

        .word78 {
          width: 68px;
          height: 22px;
          overflow-wrap: break-word;
          color: rgba(255, 255, 255, 1);


          text-align: center;
          white-space: nowrap;

          display: block;
        }

        .txt35 {
          width: 35px;
          height: 13px;
          overflow-wrap: break-word;
          color: rgba(255, 255, 255, 1);


          text-align: left;
          white-space: nowrap;

          display: block;
          margin: 4px 0 0 17px;
        }
      }
    }

    .TextGroup11 {
      height: 39px;
      width: 68px;

      .mod51 {
        width: 68px;
        height: 39px;

        .word78 {
          width: 68px;
          height: 22px;
          overflow-wrap: break-word;
          color: rgba(255, 255, 255, 1);


          text-align: center;
          white-space: nowrap;

          display: block;
        }

        .txt35 {
          width: 35px;
          height: 13px;
          overflow-wrap: break-word;
          color: rgba(255, 255, 255, 1);


          text-align: left;
          white-space: nowrap;

          display: block;
          margin: 4px 0 0 17px;
        }
      }
    }
  }

  .mod21 {
    width: 186px;
    height: 22px;
    margin: 31px 0 0 25px;

    .word79 {
      width: 33px;
      height: 22px;
      overflow-wrap: break-word;
      color: rgba(255, 255, 255, 1);


      text-align: center;
      white-space: nowrap;

      display: block;
    }

    .info38 {
      width: 12px;
      height: 12px;
      overflow-wrap: break-word;
      color: rgba(255, 255, 255, 1);

      text-align: left;
      white-space: nowrap;

      display: block;
      margin: 9px 0 0 4px;
    }

    .word80 {
      width: 70px;
      height: 14px;
      overflow-wrap: break-word;
      color: rgba(66, 76, 92, 1);

      text-align: left;
      white-space: nowrap;

      display: block;
      position: relative;
      bottom: 25px;
      left: 50px;
      margin: 4px 0 0 67px;
    }
  }

  .mod22 {
    width: 197px;
    height: 24px;
    margin: 46px 0 0 14px;

    .word81 {
      width: 84px;
      height: 14px;
      overflow-wrap: break-word;
      color: rgba(66, 76, 92, 1);

      text-align: left;
      white-space: nowrap;

      display: block;
    }

    .word82 {
      width: 24px;
      height: 22px;
      overflow-wrap: break-word;
      color: rgba(255, 255, 255, 1);


      text-align: center;
      white-space: nowrap;

      display: block;
      position: relative;
      left: 152px;
      bottom: 10px;
      // margin: 2px 0 0 72px;
    }

    .info39 {
      width: 13px;
      height: 13px;
      overflow-wrap: break-word;
      color: rgba(255, 255, 255, 1);

      text-align: left;
      white-space: nowrap;

      display: block;
      margin: 10px 0 0 4px;
    }
  }

  .mod23 {
    width: 197px;
    height: 22px;
    margin: 43px 0 36px 28px;

    .word83 {
      width: 25px;
      height: 22px;
      overflow-wrap: break-word;
      color: rgba(255, 255, 255, 1);


      text-align: center;
      white-space: nowrap;

      display: block;
      position: relative;
      right: 5px;
    }

    .txt36 {
      width: 13px;
      height: 13px;
      overflow-wrap: break-word;
      color: rgba(255, 255, 255, 1);

      text-align: left;
      white-space: nowrap;

      display: block;
      margin: 7px 0 0 4px;
    }

    .info40 {
      width: 84px;
      height: 14px;
      overflow-wrap: break-word;
      color: rgba(66, 76, 92, 1);

      text-align: left;
      white-space: nowrap;

      display: block;
      position: relative;
      left: 40px;
      bottom: 25px;
      margin: 3px 0 0 71px;
    }
  }
}

.info41 {
  width: 84px;
  height: 14px;
  overflow-wrap: break-word;
  color: rgba(66, 76, 92, 1);

  text-align: left;
  white-space: nowrap;

  display: block;
  position: relative;
  right: 55px;
  top: 10px;
  margin: 3px 0 0 71px;
}
</style>
