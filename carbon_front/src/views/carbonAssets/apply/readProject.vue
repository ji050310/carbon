<template>
  <div class="divBox">
    <el-card class="box-card">
      <el-form  class="formValidate mt20" label-width="120px" :model="formData">
        <el-row>
          <!-- 项目信息-->
           <el-col>
            <el-form-item label="ID：">
               <el-input v-model="formData.id" :options="formData.id"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="项目名称：">
             <el-input v-model="formData.name" :options="formData.name"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="项目类型：">
                <el-input v-model="formData.typeName" class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="申请减排量(tCO2)：">
              <el-input v-model="formData.amount" :options="formData.amount"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="状态：">
              <el-input v-model="formData.statusName"  class="selWidth" />
            </el-form-item>
          </el-col>
           <el-col>
            <el-form-item label="申请日期">
               <el-input v-model="formData.createdTime" :options="formData.createdTime"  class="selWidth" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" class="submission" @click="handleBack">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import {readCarbonProject } from '../../../api/carbonAssetApi';
export default ({
    data() {
        return {
            id: '',
            formData: {
              
            },
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
        handleBack() {
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
