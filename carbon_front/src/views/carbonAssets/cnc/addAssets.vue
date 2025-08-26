<template>
  <div class="divBox">
    <el-card class="box-card">
     
      <el-form  class="formValidate mt20" label-width="120px">
        <el-row>
          <!-- 项目信息-->
          <el-col>
            <el-form-item label="类型：">
                <el-cascader v-model="type" :options="carbonMethodologys"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="渠道来源：">
                <el-cascader v-model="sourceChannel" :options="sourceChannels"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="资产数量：">
              <el-input v-model="amount"  input-style="number" class="selWidth" placeholder="请输入数量" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="资产单位	：">
              <el-cascader v-model="uomType" :options="uomTypes"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="状态：">
               <el-cascader v-model="status" :options="statusList"  class="selWidth" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" class="submission" @click="handleSubmit">提交</el-button>
          <el-button  class="submission" @click="handleBack">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import { addCarbonAssetsData } from '../../../api/carbonAssetApi';
export default ({
    data() {
        return {
            name: '',
            amount: 0,
            type: 0,
            carbonMethodologys: [
              {'label': '碳减排', 'value': 0},
              {'label': '碳配额', 'value': 1},
              {'label': '碳排放', 'value': 2},
            ],
            status: 0,
            statusList: [
              {'label': '未核证', 'value': 0},
              {'label': '申请中', 'value': 1},
              {'label': '审核中', 'value': 2},
              {'label': '已核证', 'value': 3},
            ],
            uomType: 0,
            uomTypes: [
              {'label': 'tCO2', 'value': 0},
              {'label': '兆千瓦时', 'value': 1},
            ],
            sourceChannel: 0,
            sourceChannels: [
              {'label': '碳惠宝平台', 'value': 0},
              {'label': '林业数据采集平台', 'value': 1},
              {'label': '智能家居物联网平台', 'value': 2},
              {'label': '企业手动导入', 'value': 3},
            ]
        }
    },

    methods: {
        handleSubmit() {
            let data = {
                amount: this.amount,
                sourceChannel: this.sourceChannel[0],
                status: this.status[0],
                type: this.type[0],
                uomType: this.uomType[0]
              }
            addCarbonAssetsData(data).then(res => {
                if (res != null && res.code == 200) {
                    this.$router.go(-1)
                }
                this.$message(res.msg)
            })     
        },
        handleBack(){
          this.$router.go(-1)
        }
    } 
})
</script>
