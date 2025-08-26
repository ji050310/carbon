<template>
  <div class="divBox">
    <el-card class="box-card">
      <el-form  class="formValidate mt20" label-width="120px" :model="formData">
        <el-row>
           <el-col>
            <el-form-item label="ID：">
               <el-input v-model="formData.id" :options="formData.id"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="方法学编号：">
             <el-input v-model="formData.number" :options="formData.name"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="方法学名称：">
                <el-input v-model="formData.name" class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="方法学状态：">
              <el-input v-model="formData.statusName"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="所属租户：">
              <el-input v-model="formData.tenantId"  class="selWidth" />
            </el-form-item>
          </el-col>
           <el-col>
            <el-form-item label="创建者ID">
               <el-input v-model="formData.creatorId" :options="formData.createdTime"  class="selWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="创建时间">
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
import {readMethod} from '../../../api/carbonAssetApi';
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
            readMethod(this.id).then(res => {
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
