<template>
  <div class="divBox">
    <el-row :gutter="24" class="dashboard-console-grid">
      <radar :greenIndex="greenIndex"/>
        <!-- <echarts-from ref="visitChart" :yAxisData="yAxisData" :seriesData="series" :xAxis="xAxis" v-if="info" :legendData="legendData"></echarts-from> -->
    </el-row>
  </div>
</template>
<script>
// import VisitChart from './visitChart.vue'
import radar from '@/components/echarts/radar.vue';
import {loadGreenIndex} from '@/api/GreenApi'

export default {
        components: { radar},
        data () {
            return {
                grid: {
                    xl: 3,
                    lg: 6,
                    md: 6,
                    sm: 8,
                    xs: 8
                },
        infoList: null,
        visitDate: 'last30',
        series: [],
        xAxis: [],
        info: {},
        legendData: [],
        yAxisData: [],
        greenIndex:[],
        }
      },
      methods: {
        getGreenIndex() {
          loadGreenIndex().then(res => {
             this.greenIndex.carbonEmissionRate = res.carbonEmissionRate
             this.greenIndex.energyEfficiencyRate = res.energyEfficiencyRate
             this.greenIndex.environmentRate = res.environmentRate
          }).catch(e => {
  
          })
        },
      },

      mounted() {
        this.getGreenIndex()
      }
    }
</script>
<style lang="scss" scoped>
  .ivu-mb{
    margin-bottom: 10px;
  }
  .divBox {
    padding: 0;
  }

  .dashboard-console-grid {
    text-align: center;
    .ivu-card-body {
      padding: 0;
    }
    i {
      font-size: 32px;
    }
    a {
      display: block;
      color: inherit;
    }
  }
</style>
