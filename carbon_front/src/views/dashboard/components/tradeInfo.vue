<template>
  <div class="root">
    <div class="fl">
      <div class="root-tab">
        <div>
          <a class="nav-title">碳交易行情</a>
          <!-- <a class="nav-subtitle">{{quotation.statDate}}</a> -->
        </div>
        <div style="margin-left: 22px;">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <!-- <el-tab-pane label="CCER" name="CCER"></el-tab-pane> -->
            <el-tab-pane label="VCS" name="VCS"></el-tab-pane>
          </el-tabs>
        </div>
        <!-- <a class="more-text" @click="openMoreNews" style="position: relative;left: 70px;">查看更多 ></a> -->
      </div>
      <div><a class="more-text" href="/trade/quotation" style="margin-right:22px;">查看更多 ></a></div>
    </div>
    <div class="card-container">
      <div class="chart-container">
        <i class="chart-top-text"><span>{{ activeName }}</span> 签发量：{{quotation.ccerCount}}</i>
        <div id="xunjian_echart" style="margin-left:10px;width:168.18px;height:168.18px" />
        <i class="chart-unit">单位： tCO2e</i>
        <div class="chart-info-bg">
          <div class="wrap-div">
            <div class="info-line">
              <div class="green-point" />
              <i class="hint-text">已核销，{{quotation.writtenOffCount}} ，{{percentWrittenOffCount}}%</i>
            </div>
            <div class="info-line">
              <div class="blue-point" />
              <i class="hint-text">存量，{{quotation.stockCount}} ，{{percentStockCount}}%</i>
            </div>
          </div>
        </div>
      </div>
      <div class="chart-container">
        <i class="chart-top-text"> {{ activeName }} 项目量： {{quotation.ccerProjectCount}}</i>
        <div id="lvxin_echart" style="margin-left:10px;width:168.18px;height:168.18px" />
        <i class="chart-unit">单位: 个</i>
        <div class="chart-info-bg">
          <div class="wrap-div">
            <div class="info-line">
              <div class="green-point" />
              <i class="hint-text">已审定，{{quotation.approvedCount}} 个，{{percentApprovedCount}}%</i>
            </div>
            <div class="info-line">
              <div class="blue-point" />
              <i class="hint-text">已备案，{{quotation.filingCount}} 个，{{percentFilingCount}}%</i>
            </div>
            <div class="info-line">
              <div class="lint-blue-point" />
              <i class="hint-text">已签发，{{quotation.singCount}} 个，{{percentSingCount}}%</i>
            </div>
          </div>
        </div>
      </div>

      <el-table
        :header-cell-style="{textAlign: 'center'}"
        :cell-style="{'text-align':'center'}"
        class="table-div"
        :data="quotation.projects"
        stripe
        style="width: 100%;"
        max-height="370"
      >
        <el-table-column
          prop="type"
          label="项目类型"
          width="180">
        </el-table-column>
        <el-table-column
          prop="singCount"
          label="签发量(tCO2e)"
          width="180">
        </el-table-column>
        <el-table-column
          prop="stockCount"
          label="存量(tCO2e)">
        </el-table-column>
        <el-table-column
          prop="singCount"
          label="项目数量(个)">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import * as echarts from 'echarts';
import {getHomePanelData} from '@/api/homeApi.js'
import { getToken } from '@/utils/auth'
export default {
  name: 'tradeInfo',
  data() {
    return {
      ccerCount:0,
      stockCount:0,  //	存量	integer
      writtenOffCount: 0,//	已核销
      percentStockCount: 0,
      percentWrittenOffCount: 0,

      ccerProjectCount: 0,
      singCount: 0,//	已签发
      filingCount: 0,//	已备案
      approvedCount: 0,//	已审定
      percentSingCount: 0,
      percentFilingCount: 0,
      percentApprovedCount: 0,
      isTabShow:0,
      quotation:{},
      activeName:"VCS"
    }
  },

  // props: {
  //   quotation:{}
  // },
  mounted() {
    // this.ccerCount = this.quotation.ccerCount
    // this.ccerProjectCoun = this.quotation.ccerProjectCoun
    let str = "VCS";
    this.cacultePercent(str)
    // this.drawRing()
  },

  computed: {
  },

  methods: {
    handleClick(tab, event) {
      this.cacultePercent(this.activeName)
    },
    cacultePercent(str) {
      let token = getToken()
      getHomePanelData(token).then( res => {
          let index = 0;
          for (let i = 0; i < res.quotation.length; i++) {
            if (res.quotation[i].type == str) {
              index = i;
            }
          }

          let quotation = res.quotation[index]
          this.quotation = quotation
          this.ccerCount = quotation.ccerCount
          this.stockCount = quotation.stockCount
          this.writtenOffCount = quotation.writtenOffCount

          this.ccerProjectCount = quotation.ccerProjectCount
          this.singCount = quotation.singCount
          this.filingCount = quotation.filingCount
          this.approvedCount = quotation.approvedCount

          this.percentStockCount = parseFloat(this.stockCount / this.ccerCount * 100).toFixed(2)
          this.percentWrittenOffCount = parseFloat(this.writtenOffCount / this.ccerCount * 100).toFixed(2)
          this.percentSingCount = parseFloat(this.singCount / this.ccerProjectCount* 100).toFixed(2)
          this.percentFilingCount = parseFloat(this.filingCount / this.ccerProjectCount * 100).toFixed(2)
          this.percentApprovedCount = parseFloat(this.approvedCount / this.ccerProjectCount * 100).toFixed(2)
          this.drawRing()
          // this.$forceUpdate();
        }
      ).catch((res) => {
      })
    },
    drawRing() {
      //  初始化统计图表
      var xj = document.getElementById('xunjian_echart');
      var xunjian = echarts.init(xj);

      var lv = document.getElementById('lvxin_echart');
      var lvxin = echarts.init(lv);
      //设置数据
      var option_xunjian = {
        color: ["#26B581", "#0065FF ", "#009EFF", "#4EDBB7"],
        tooltip: {
          trigger: 'item'
        },
        title: {
          show: true,
          text: this.ccerCount, // 当前写死
          left: 80.08,
          top: 66.08,
          textAlign: 'center',
          textStyle: {
            fontSize: '12',
            fontWeight: 'bold'
          },
        },
        series: [
          {
            // name: 'Access From',
            type: 'pie',
            radius: ['30%', '80%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '12',
                // fontWeight: 'bold'
              }
            },
            labelLine: {
              show: true
            },
            data: [
              { value: this.stockCount, name: '存量' },
              { value: this.writtenOffCount, name: '已核销' },
            ]
          }
        ]
      };
      var option_lvxin = {
        color: ["#26B581", "#0065FF ", "#009EFF", "#4EDBB7"],
        tooltip: {
          trigger: 'item'
        },
        title: {
          show: true,
          text: this.ccerProjectCount,
          left: 80.08,
          top: 66.08,
          textAlign: 'center',
          textStyle: {
            fontSize: '12',
            fontWeight: 'bold'
          },
        },
        series: [
          {
            type: 'pie',
            radius: ['30%', '80%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '12',
              }
            },
            labelLine: {
              show: true
            },
            data: [
              { value: this.approvedCount, name: '已审定' },
              { value: this.filingCount, name: '已备案' },
              { value: this.singCount, name: '已签发' },
            ]
          }
        ]
      };

      //实例化
      xunjian.setOption(option_xunjian);

      lvxin.setOption(option_lvxin);
      this.$forceUpdate();
    }
  },
  created() {

  }
}
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
    background: #FFFFFF;
    box-shadow: 0px 2px 8px 0px #EAF0F3;
    border-radius: 8px;
  }
}
.nav-title {
  margin-top: 20px;


  font-weight: 500;
  color: #080B0D;
  cursor: default;
}
.nav-subtitle {
  margin-left: 12px;


  font-weight: 400;
  color: #5E6C84;
  cursor: default;
}
.chart-container {
  display: flex;
  flex-direction: column;
  margin-left: 60px;
  margin-top: 37px;
  margin-bottom: 30px;
  width: 201px;
}
.chart-top-text {
  text-align: center;


  font-weight: 500;
  color: #424C5C;
}
.chart-unit {
  text-align: center;


  font-weight: 400;
  color: #5E6C84;
  // margin-left: 60px;
}
.chart-info-bg {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  width: 201px;
  height: 111px;
  background-image: url('../../../assets/imgs/bg_chart_info.png');
  background-repeat: no-repeat;
}
.green-point {
  border-radius: 5px;
  width: 10px;
  height: 10px;
  margin-top: 5px;
  // margin-left: 20px;
  margin-right: 10px;
  background: linear-gradient(180deg, #4EDBB7 0%, #26B581 100%);
}
.blue-point {
  border-radius: 5px;
  width: 10px;
  height: 10px;
  margin-top: 5px;
  // margin-left: 20px;
  margin-right: 10px;
  background: linear-gradient(180deg, #009EFF 0%, #0065FF 100%);
}
.lint-blue-point {
  border-radius: 5px;
  width: 10px;
  height: 10px;
  margin-top: 5px;
  // margin-left: 20px;
  margin-right: 10px;
  background: linear-gradient(180deg, #2FD5E1 0%, #15AABF 100%);
}
.info-line {
  // margin-top: 10px;
  display: flex;
  flex-direction: row;

}
.hint-text {


  font-weight: 400;
  color: #424C5C;
}
.table-div{
  margin-left: 10px;
  margin-right: 24px;
  margin-top: 30px;
  margin-bottom: 30px;
}
.root-tab{
  display: flex;;
  justify-content: start;
  align-items: center;
}
.tab-calss{
  border: 1px solid #0abc61;
  padding: 2px 8px;
}
.wrap-div {
  margin: auto;
}
/deep/ .el-tabs__header{
  margin: 0;
}
/deep/ .el-table__body-wrapper{
  padding-bottom: 20px;
}
.fl{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.more-text{
  color: #0abc61;
}
</style>
