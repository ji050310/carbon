<template>
  <div class="divBox">
    <el-card class="box-card">
     
      <el-form  class="formValidate mt20" label-width="120px">
        <el-row>
          <!-- 项目信息-->
          <el-col>
            <el-form-item label="id：">
                <el-input v-model="id" class="selWidth" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="类型：">
                <el-cascader v-model="data.type" :options="carbonMethodologys"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="渠道来源：">
                <el-cascader v-model="data.sourceChannel" :options="sourceChannels"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="资产数量：">
              <el-input v-model="data.amount"  input-style="number" class="selWidth" placeholder="请输入数量" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="资产单位	：">
              <el-cascader v-model="data.uomType" :options="uomTypes"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="状态：">
               <el-cascader v-model="data.status" :options="statusList"  class="selWidth" />
            </el-form-item>
          </el-col>
           <el-col>
            <el-form-item label="起始日期：" class="selWidth">
             <el-date-picker
              v-model="data.startDate"
              type="datetime"
              placeholder="选择日期时间">
             </el-date-picker>
            </el-form-item>
          </el-col>
           <el-col>
            <el-form-item label="截止日期	：" class="selWidth">
              <el-date-picker
              v-model="data.endDate"
              type="datetime"
              placeholder="选择日期时间">
             </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="审核时间	：" class="selWidth">
              <el-date-picker
              v-model="data.auditTime"
              type="datetime"
              placeholder="选择日期时间">
             </el-date-picker>
            </el-form-item>
          </el-col>
           <el-col>
            <el-form-item label="报送时间：" class="selWidth">
              <el-date-picker
              v-model="data.submittedTime"
              type="datetime"
              placeholder="选择日期时间">
             </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" class="submission" @click="handleSubmit">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import {readSourceData } from '../../../api/carbonAssetApi';
export default ({
    data() {
        return {
           id: "",
           data: {
              amount: 0,
              auditTime: "",
              endDate: "",
              sourceChannel: 0,
              startDate: "",
              status: 0,
              submittedTime: "",
              type: 0,
              uomType: 0
            },
            carbonMethodologys: [
              {'label': '碳减排', 'value': 0},
              {'label': '碳配额', 'value': 1},
              {'label': '碳排放', 'value': 2},
            ],
            statusList: [
              {'label': '审核中', 'value': 0},
              {'label': '已审核', 'value': 1},
              {'label': '审核失败', 'value': 2},
            ],
            uomTypes: [
              {'label': 'tCO2', 'value': 0},
              {'label': '兆千瓦时', 'value': 1},
            ],
            sourceChannels: [
              {'label': '碳惠宝平台', 'value': 0},
              {'label': '林业数据采集平台', 'value': 1},
              {'label': '智能家居物联网平台', 'value': 2},
              {'label': '企业手动导入', 'value': 3},
            ]
        }
    },

    methods: {
        loadData() {
            readSourceData(this.id).then(res => {
                if (res != null && res.code == 200) {
                  this.data = res.data
                }
            })     
        },
        handleSubmit() {
          this.$router.go(-1)
        }
    },
    mounted() {
       this.loadData()
    },
    created() {
      this.id = this.$route.query.id
      this.loadData()
    },
})
</script>