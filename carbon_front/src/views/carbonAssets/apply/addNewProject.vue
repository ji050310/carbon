<template>
  <div class="divBox">
    <el-card class="box-card">
     
      <el-form  class="formValidate mt20" label-width="120px">
        <el-row>
          <!-- 项目信息-->
          <el-col>
            <el-form-item label="项目名称：">
              <el-input v-model="name" class="selWidth" placeholder="请输入项目名称" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="项目类型：">
                <el-cascader v-model="carbonMethodology" :options="carbonMethodologys"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="申请减排量(tCO2)：">
              <el-input v-model="amount" class="selWidth" placeholder="预估年减排量(tCO2)" />
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
          <el-button class="submission" @click="handleBack">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import { addCarbonProject } from '../../../api/carbonAssetApi';
export default ({
    data() {
        return {
            name: '',
            carbonMethodologyName: '',
            amount: 0,
            status: 0,
            carbonMethodologys: [
              {'label': 'CCER', 'value': 0},
              {'label': 'CDM', 'value': 1},
              {'label': 'VCS', 'value': 2},
            ],
            statusList: [
              {'label': '申请中', 'value': 0},
              {'label': '审核中', 'value': 1},
              {'label': '已核证', 'value': 2},
            ]
        }
    },

    methods: {
        handleSubmit() {
            let data = {
                "amount": this.amount,
                  "name": this.name,
                  "status": this.status[0],
                  "type": this.carbonMethodology[0],
                  "carbonMethodologyId": "",
                   "creatorId": "",
                   "id": "",
                   "tenantId": "",
            }

            addCarbonProject(data).then(res => {
              debugger
                if (res != null && res.code == 200) {
                    this.name = '',
                    this.carbonMethodologyName = '',
                    this.amount= 0,
                    this.status = 0
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
