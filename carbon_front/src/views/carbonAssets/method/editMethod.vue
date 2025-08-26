<template>
  <div class="divBox">
    <el-card class="box-card">
      <el-form  class="formValidate mt20" label-width="120px" :model="formData">
        <el-row>
           <el-col>
            <el-form-item label="ID：">
               <el-input v-model="formData.id" :options="formData.id" :disabled='true' class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="方法学编号：">
             <el-input v-model="formData.number" :options="formData.number"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="方法学名称：">
                <el-cascader v-model="formData.name" :options="names"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="方法学状态：">
              <el-cascader v-model="formData.statusName" :options="statusNames"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="所属租户：">
              <el-input v-model="formData.tenantId" :disabled='true' class="selWidth" />
            </el-form-item>
          </el-col>
           <el-col>
            <el-form-item label="创建者ID">
               <el-input v-model="formData.creatorId" :options="formData.createdTime" :disabled='true' class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="创建时间">
               <el-input v-model="formData.createdTime" :options="formData.createdTime" :disabled='true' class="selWidth" />
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
import {readMethod, editMethod} from '../../../api/carbonAssetApi';
export default ({
    data() {
        return {
            formData: {
              id: '',
              name: 0,
              number: '',
              tenantId: 0,
              statusName: 0,
              createdTime:'',
              creatorId: '',
            },
            names: [
              {'label': '碳减排', 'value': 0},
              {'label': '碳配额', 'value': 1},
              {'label': '碳排放', 'value': 2},
            ],
            statusNames: [
              {'label': '申请中', 'value': 0},
              {'label': '审核中', 'value': 1},
              {'label': '已核证', 'value': 2},
              {'label': '核证失败', 'value': 3},
            ]
        }
    },

    methods: {
        loadData() {
            readMethod(this.id).then(res => {
                if (res != null && res.code == 200) {
                  this.formData = res.data
                }
            })     
        },
        handleSubmit() {
           let data = {
              description: "",
              id: this.formData.id,
              name: this.names[this.formData.name[0]].label,
              number: this.formData.number,
              status: this.formData.statusName[0]
            }
          editMethod(data).then(res => {
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
