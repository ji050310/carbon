<template>
  <div class="divBox">
    <el-card class="box-card">
      <el-form  class="formValidate mt20" label-width="120px" :model="formData">
        <el-row>
          <!-- 项目信息-->
           <el-col>
            <el-form-item label="ID：">
               <el-input v-model="formData.id" :options="formData.id"  class="selWidth" :disabled="true"/>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="项目名称：">
             <el-input v-model="formData.name" :options="formData.name"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="项目类型：">
                 <el-cascader v-model="formData.carbonMethodologyName" :options="carbonMethodologys"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="申请减排量(tCO2)：">
              <el-input v-model="formData.amount" :options="formData.amount"  class="selWidth"/>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="状态：">
              <el-cascader v-model="formData.statusName" :options="statusList"  class="selWidth" />
            </el-form-item>
          </el-col>
           <el-col>
            <el-form-item label="申请日期">
               <el-input v-model="formData.createdTime" :options="id"  class="selWidth" :disabled="true"/>
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
import {readCarbonProject, editCarbonProject } from '../../../api/carbonAssetApi';
export default ({
    data() {
        return {
            formData: {
              id: '',
              name: '',
              carbonMethodologyName: '',
              amount: 0,
              statusName: 0,
              createdTime:'',
            },
            name: '',
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
        loadData() {
            readCarbonProject(this.id).then(res => {
                if (res != null && res.code == 200) {
                  this.formData = res.data
                }
            })     
        },
        handleSubmit() {
           let data = {
                 "amount": this.formData.amount,
                  "name": this.formData.name,
                  "status": this.formData.statusName[0],
                  "type": this.formData.carbonMethodologyName[0],
                  "carbonMethodologyId": "",
                   "creatorId": "",
                   "id": this.formData.id,
                   "tenantId": "",
            }
          editCarbonProject(data).then(res => {
            if (res.code == 200) {
              this.$router.go(-1)
            }
            this.$message(res.msg)
          })
        },
        handleBack(){
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
