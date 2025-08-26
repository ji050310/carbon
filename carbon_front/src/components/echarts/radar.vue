<template>
  <div>
    <div id="myChart" class="radar"></div>
  </div>
</template>
 
<script>
import echarts from 'echarts';
export default {
  name: 'Radar',
  data() {
    return {}
  },
  props: {
    greenIndex:[]
  },
  created() {
    window.addEventListener('resize', this.handleResize)
  },
  mounted() {
    this.drawLine()
  },
  methods: {
    handleResize(event) {
      // this.fullWidth = document.body.clientWidth
      this.drawLine()
    },

    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('myChart'))

      let dataMax = [
        { name: '碳减排指标(权重：40%)\n（满分100分）', max: '100'},
        { name: '碳中和指标(权重：30%)\n（满分100分）', max: '100' },
        { name: '碳普惠指标(权重：30%)\n（满分100分）', max: '100' },
      ]
      let option = {
        //配置维度的最大值
        radar: {
          name: {
            show: true,
            color: 'black',
          },
          xisLabel:{
            overflow:"all"
          },
          //   雷达图的指示器，用来指定雷达图中的多个变量（维度）
          indicator: dataMax,
        //   shape: 'circle', //对雷达图形设置成一个圆形,可选 circle:圆形,polygon:多角形(默认)
        },
        series: [
          {
            type: 'radar',
            label: {
              // show: true, //显示数值
            },
            areaStyle: {
                color:'rgba(46,139,87, 1)'
            }, //每个雷达图形成一个阴影的面积
            data: [
              {
                value: [65, 35, 35],
              },
              {
                value: [this.greenIndex.carbonEmissionRate, this.greenIndex.environmentRate, this.greenIndex.energyEfficiencyRate],
              },
            ],
          },
        ],
      }
      // 绘制图表
      myChart.setOption(option)
    },
  },
}
</script>
<style>
  .radar{
    /* width: 100%; */
    height: 355px;
    display: flexbox;
    flex-shrink: 1;
    flex-grow: 1;
    justify-content: center;
  }
</style>