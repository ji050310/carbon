<template>
  <div class="divBox">
    <el-card class="box-card">
      <el-form  class="formValidate mt20" label-width="120px" :model="formData">
        <el-row>
          <!-- 项目信息-->
           <el-col>
            <el-form-item label="卖方账户id">
               <el-input v-model="formData.userIdFrom" placeholder="请输入卖方账户id"  class="tradeInputWidth"/>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="卖方机构名称">
             <el-input v-model="formData.orgNameFrom" placeholder="请输入卖方机构名称"  class="tradeInputWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="卖方tokenid">
                 <el-input v-model="formData.tokenFrom" placeholder="请输入卖方tokenid"  class="tradeInputWidth" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="买方机构名称">
              <el-input v-model="formData.orgNameTo" placeholder="请输入买方机构名称"  class="tradeInputWidth"/>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="交易量">
              <el-input input-style="number" v-model="formData.transferAmount" placeholder="请输入交易量"  class="tradeInputWidth" />
            </el-form-item>
          </el-col>
           <el-col>
            <el-form-item label="交易类型">
               <el-cascader v-model="formData.transferType" :options="transferTypes"  class="tradeInputWidth"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" class="btnWidth" @click="handleSubmit">下单</el-button>
          <el-button class="btnWidth" @click="handleBack">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import {orderTrade, getTradeInfo} from '../../api/tradeApi';
export default ({
    data() {
        return {
            formData: {
                userIdFrom: "",
                orgNameFrom: "",
                tokenFrom: "",
                orgNameTo: "",
                transferAmount: 0,
                transferType: 0,
            },
            transferTypes:[
              {'label': '单一交易', 'value': 0},
              {'label': '批次交易', 'value': 1},
            ],
        }
    },

    methods: {
        handleSubmit() {
           let data = {
                userIdFrom: this.formData.userIdFrom,
                orgNameFrom: this.formData.orgNameFrom,
                tokenFrom: this.formData.tokenFrom,
                orgNameTo: this.formData.orgNameTo,
                transferAmount: this.formData.transferAmount,
                transferType: this.formData.transferTypes,
                sign: this.formData.sign
            }
          orderTrade(data).then(res => {
            debugger
            if (res.code == 200) {
              this.formData = {
                userIdFrom: "",
                orgNameFrom: "",
                tokenFrom: "",
                orgNameTo: "",
                transferAmount: 0,
                transferType: "0",
              }
              this.$message('成功提交下单请求')
              this.$router.go(-1)
            } else {
              this.$message(res.msg)
            }
          })
        },
        handleBack(){
          this.$router.go(-1)
        },
        loadData() {
          getTradeInfo(this.id).then(res => {
            if (res.code == 200) {
              this.formData = res.data
            }
          })
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
<style scoped>
.tradeInputWidth {
  width: 400px;
}
.btnWidth{
     width: 193px;
}
</style>
