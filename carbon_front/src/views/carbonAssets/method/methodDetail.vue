<template>
    <div class="root">
        <div class="box">
            <span class="title">方法学名称</span>
            <div class="grid">
                <div class="selectbox-root">
                    <a class="selectbox-hint">方法学搜索</a>
                    <div class="selectbox-deliver"/>
                    <input  class="selectbox-input" v-model="data.cdmCode" placeholder="输入方法学名称或编号" clearable/>
                </div>
                <div  class="selectbox-root">
                    <a class="selectbox-hint">领域</a>
                    <div class="selectbox-deliver"/>
                    <el-cascader style="width:120px" placeholder="全部" class="selectbox-input" :options="data.carbonFieldName" clearable>
                    </el-cascader>
                </div>
                 <div  class="selectbox-root">
                    <a class="selectbox-hint">行业</a>
                    <div class="selectbox-deliver"/>
                    <el-cascader style="width:120px" placeholder="全部" class="selectbox-input" :options="data.carbonIndustryName" clearable>
                    </el-cascader>
                </div>
                <div  class="selectbox-root">
                    <a class="selectbox-hint">核证标准</a>
                    <div class="selectbox-deliver"/>
                    <el-cascader style="width:120px" placeholder="CCER\VCS\CDM" class="selectbox-input" :options="data.certificationCriteria" clearable>
                    </el-cascader>
                </div>
                <div class="selectbox-root">
                    <a class="selectbox-hint">开发机构</a>
                    <div class="selectbox-deliver"/>
                    <input  class="selectbox-input" v-model="institutions" placeholder="data.creatorName" clearable/>
                </div>
                <div class="selectbox-root">
                    <a class="selectbox-hint">签发日期</a>
                    <div class="selectbox-deliver"/>
                    <input  class="selectbox-input" v-model="issueDate" placeholder="data.createdTime" clearable/>
                </div>
            </div>
            <span class="intruce">采用本方法学的项目活动，应遵循以下适用条件： (a) 实施项目活动的土地为符合国家规定的乔木林地，即郁闭度≥0.20，连续分布面积≥0.0667 公顷，树高≥2 米的乔木林。 (b) 本方法学（版本号 V.01.0）不适用于竹林和灌木林。 (c) 在项目活动开始时，拟实施项目活动的林地属人工幼、中龄林。项目参与方须基于国家森林资源连续清查技术规定（附件 2）、森林资源规划设计调查技术规程中的林组划分标准，并考虑立地条件和树种，来确定是否符合该条件。 (d) 项目活动符合国家和地方政府颁布的有关森林经营的法律、法规和政策措施以及相关的技术标准或规程。 (e) 项目地土壤为矿质土壤。 (f) 项目活动不涉及全面清林和炼山等有控制火烧。 (g) 除为改善林分卫生状况而开展的森林经营活动外，不移除枯死木和地表枯落物。 (h) 项目活动对土壤的扰动符合下列所有条件： (i) 符合水土保持的实践，如沿等高线进行整地； (ii) 对土壤的扰动面积不超过地表面积的 10%； (iii) 对土壤的扰动每 20 年不超过一次。采用本方法学的项目活动，还应遵循本方法学中所包含的工具以及项目活动所采用的工具的适用条件。面积不超过地表面积的 10%； (iii) 对土壤的扰动每 20 年不超过一次。采用本方法学的项目活动，还应遵循本方法学中所包含的工具以及项目活动</span>
            <div class="btn-container">
                <div class="empty-holder"/>
                <button  class="normal-white-btn center-vertical" @click="onClickBack">返回</button>
                <button  class="light-green-btn center-vertical" @click="onClickRead">查看原文</button>
                <button  class="light-green-btn center-vertical" @click="onClickCreate">创建项目</button>
            </div>
        </div>
    </div>
</template>

<script>
  import {readMethod} from '@/api/carbonAssetApi';
  import selectDropDownBox from '@/components/selectbox/selectDropDownBox.vue'

  export default {
    name: 'companyPackage',
    components: { selectDropDownBox},
    data() {
      return {
        id: '',
        searchKeyWord:'',
        institutions:'',
        issueDate:'',
        optionsStandard: [{
          value: 'CCER',
          label: 'CCER'
        },{
          value: 'VCS',
          label: 'VCS'
        },{
          value: 'CDM',
          label: 'CDM'
        }],
        optionsOnlines: [{
          value: '',
          label: ''
        }],
        optionsIndustory: [{
          value: 'all',
          label: '全部'
        },{
          value: 'tree',
          label: '林业'
        }],
        optionsScore: [{
          value: 'all',
          label: '全部'
        },{
          value: 'tree',
          label: '林业'
        }],
        value: '',
        pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        selectDate: '',
        data: {
            d: "2",
            methodCode: "",
            name: "REC",
            carbonFieldName: null,
            carbonIndustryName: null,
            certificationCriteria: "18",
            certificationCriteriaName: "CDM\n",
            status: 1,
            statusName: "有效",
            type: null,
            sourceFileUrl: null,
            cdmCode: "FFFF",
            creatorName: null,
            createdTime: "2021-08-21 09:07:44"
        }
      }
    },
    created() {
        debugger
       this.id = this.$route.query.id
    },
    mounted() {
        this.getDetail()

    },
    methods: {
       onClickBack() {
           this.$router.go(-1)
       },
       onClickRead() {
           
       },
       onClickAdd() {
           
       },
       getDetail() {
           readMethod(this.id).then(res => {
               this.data = res.data
               debugger
           })
       }
    }
  }
</script>

<style lang="scss" scoped>
.root{
    padding: 16px;
    background: #F5F7FA;
}
.box{
    display: flex;
    flex-direction: column;
    height: 595px;
    background: #FFFFFF;
    box-shadow: 0px 2px 8px 0px #EAF0F3;
    border-radius: 8px;
    padding: 30px;
}
.title {
    font-size: 18px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #24A776;
    line-height: 18px;
}
.grid{
    display: grid;
    grid: auto/ auto auto auto;
    grid-gap: 30px;
    margin-top: 30px;
}
.grid-item-bg{
    background: #F7F9FC;
}
>>>.el-cascader .el-input .el-input__inner,>>> .el-cascader .el-input.is-focus .el-input__inner{
  border-color: transparent 
}
.intruce{
    margin-top: 20px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #808EA5;
    line-height: 21px;
    background: #F7F9FC;
    border-radius: 6px;
    padding: 10px;
}
.btn-container{
    margin-top: 20px;
    display: flex;
    flex-direction: row;
}
.center-vertical{
    width: 150px;
    margin-left: 50px;
}
</style>