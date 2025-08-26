<template>
  <div class="divBox">
    <el-row :gutter="24" class="dashboard-console-grid">
        <el-col v-bind="grid" class="ivu-mb" >
            <el-card :bordered="false" style="padding:0px">
                <div class="link">
                    <!-- <i class="el-icon-user" style="color:#69c0ff;display: flexbox;justify-content: center;" /> -->
                    <p class="boxTitle">碳减排量</p>
                    <p class="boxCarbonReduce">{{carbonReduce}}</p>
                </div>
            </el-card>
        </el-col>
        <el-col v-bind="grid" class="ivu-mb">
            <el-card :bordered="false">
                <div class="link">
                    <!-- <i class="el-icon-setting" style="color:#95de64;display: flexbox;justify-content: center;" /> -->
                    <p class="boxTitle">资源减排核证</p>
                    <p class="boxCarbonCertificationCount">{{carbonCertificationCount}}</p>
                </div>
            </el-card>
        </el-col>
        <el-col v-bind="grid" class="ivu-mb">
            <el-card :bordered="false">
                <div class="link">
                    <!-- <i class="el-icon-goods" style="color:#ff9c6e;display: flexbox;justify-content: center;" /> -->
                    <p class="boxTitle">碳减排方法学</p>
                    <p class="boxCarbonMethodologyCount">{{carbonMethodologyCount}}</p>
                </div>
            </el-card>
        </el-col>
        <el-col v-bind="grid" class="ivu-mb">
            <el-card :bordered="false" style="padding:0px">
                <div class="link">
                    <!-- <i class="el-icon-s-order" style="color:#b37feb;display: flexbox;justify-content: center;" /> -->
                    <p class="boxTitle">碳排放权交易所</p>
                    <p class="boxCarbonExchangeCount">{{carbonExchangeCount}}</p>
                </div>
            </el-card>
        </el-col>
    </el-row>
    <div>
     <el-table
      :data="carbonAssetsList"
      height="250"
      style="width: 100%;font-size: 6px;">
      <el-table-column
        prop="amount"
        label="中和资产(CNC)"
        width="80px">
      </el-table-column>
      <el-table-column
        prop="typeName"
        label="资源类型"
        width="80px">
      </el-table-column>
      <el-table-column
        prop="uom"
        label="资产UOM"
        width="80px">
      </el-table-column>
      <el-table-column
        prop="carbonProjectName"
        label="资产开发项目"
        width="95px">
      </el-table-column>
      <el-table-column
        prop="carbonMethodologyName"
        label="资产开发方法学"
        width="prop.width">
      </el-table-column>
      <el-table-column
        prop="sourceChannelName"
        label="资产来源"
        width="prop.width">
      </el-table-column>
    </el-table>
    </div>
  </div>
</template>
<script>
    import { getOperationData } from '@/api/operationApi';
    export default {
        data () {
            return {
                grid: {
                    // xl: 3,
                    // lg: 6,
                    // md: 6,
                    // sm: 8,
                    // xs: 8
                },
                carbonReduce: 0,
                carbonCertificationCount: 0,
                carbonMethodologyCount: 0,
                carbonExchangeCount: 0,
                carbonAssetsList: [],
            }
        },
        methods: {
          loadData() {
            getOperationData().then(res => {
              this.carbonReduce = res.carbonReduce,
              this.carbonCertificationCount = res.carbonCertificationCount,
              this.carbonMethodologyCount = res.carbonMethodologyCount,
              this.carbonExchangeCount = res.carbonExchangeCount,
              this.carbonAssetsList = res.carbonAssetsList
            })
          }
        },
        mounted() {
          this.loadData()
        }
    }
</script>
<style lang="scss" scoped>
  .ivu-mb{
    display: flexbox;
    padding: 0;
    // flex-grow: 1;
    flex-shrink: 1;
    margin-bottom: 10px;
    padding: 0px;
    width: 25%;
    justify-content: center;
  }
  .el-card /deep/ .el-card__body {
    padding-left: 5px;
    padding-right: 5px;
  }
  .divBox {
    padding: 0 ;
  }
  .link{
    height:40px;
    padding: 0;
  }
  .boxTitle {
    padding: 0;
    font-size: 10px;
    color: #000;
  }
  .boxCarbonReduce {
    color: #f00;
    font-size: 20px;
  }
  .boxCarbonCertificationCount {
    color: rgb(17, 0, 255);
    font-size: 20px;
  }
  .boxCarbonMethodologyCount {
    color: rgb(30, 255, 0);
    font-size: 20px;
  }
  .boxCarbonExchangeCount {
    color: rgb(255, 196, 0);
    font-size: 20px;
  }

  .dashboard-console-grid {
    text-align: center;
    i {
      font-size: 32px;
    }
    a {
      display: block;
      color: inherit;
    }
    p {
      display: flexbox;
      justify-content: center;
      text-align:center;
      margin-top: 8px;
      padding: 0;
    }
  }
</style>
