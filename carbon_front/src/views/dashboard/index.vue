<template>
<div>
<!--头部-->
<company-package
  ref="companyPackage"
  :accountVo="accountVo"
  :assetsIncome="assetsIncome"
  :fundIncome="fundIncome"
  :carbonCredit="carbonCredit"
  :carbonQuota="carbonQuota"
  :greenScore="greenScore"
/>

<capital-stat
  ref="capitalStat"
  :monthSupply="monthSupply"
  :monthDevelopment="monthDevelopment"
  :monthSales="monthSales"
/>

<!--小方块-->
<apply-project />

<!-- <project-stat ref="projectStat" :projectStat="projectStat" /> -->
<projectStatistical v-if="(projectStat.projectList && projectStat.projectList.length > 0)" :projectStat="projectStat" />
<trade-info :quotation="quotation" />

<newsContaiert ref="newsList" />

<commom-usage-grid />
</div>
</template>

<script>
import {mapGetters} from "vuex";
import applyProject from "./components/applyProject";
import companyPackage from "./components/companyPackage.vue";
import capitalStat from "./components/capitalStat.vue";
import newsContaiert from "./components/newsContaiert.vue";
import tradeInfo from "./components/tradeInfo.vue";
import commomUsageGrid from "./components/commomUsageGrid.vue";
import {getHomePanelData} from "@/api/homeApi.js";
import {getToken} from "@/utils/auth";
import {getAllDiction} from "@/config/dictHelper";
import projectStatistical from "./components/projectStatistical.vue";
import {setLargeNumber} from "@/libs/public"

export default {
  name: "Dashboard",
  components: {
    applyProject,
    tradeInfo,
    companyPackage,
    capitalStat,
    newsContaiert,
    commomUsageGrid,
    projectStatistical,
  },
  data() {
    return {
      currentRole: "adminDashboard",
      accountVo: {},
      assetsIncome: {},
      carbonCredit: {},
      carbonQuota: {},
      fundIncome: {},
      greenScore: {},
      monthDevelopment: {},
      monthSales: {},
      monthSupply: {},
      projectStat: {},
      quotation: {},
    }
  },
  methods: {
    setDataNumber(obj) {
      for (let i in obj) {
        if (typeof (obj[i]) == "number") {
          obj[i] = setLargeNumber(obj[i]);
        }
      }
      return obj;
    },
    getNewsList() {
    },
    getAllDictionary() {
      getAllDiction(this.$store)
    },
    getLastMonth(date) {
      var dateArr = date.split("-");
      var m = parseInt(dateArr[1])
      if (m == 1) {
        return 12 + '月'
      } else {
        return (m - 1) + '月'
      }
    },
    getHomePanelData() {
      let token = getToken()
      getHomePanelData(token).then(res => {
          this.accountVo = res.accountVo
          if (!this.accountVo.avatar || this.accountVo.avatar == "" || (this.accountVo.avatar).match(/^[ ]*$/)) {
            this.accountVo.avatar = '/static/img/icon_accoun_logo.e4db51e4.jpg';
          } else {
            window.localStorage.setItem("avatar", this.accountVo.avatar);
          }
          this.assetsIncome = res.assetsIncome
          for (let i in this.assetsIncome) {
            if (typeof (this.assetsIncome[i]) == "number") {
              this.assetsIncome[i] = setLargeNumber(this.assetsIncome[i]);
            }
          }
          this.carbonCredit = setLargeNumber(res.carbonCredit)
          this.carbonQuota = setLargeNumber(res.carbonQuota)
          this.fundIncome = res.fundIncome
          this.greenScore = res.greenScore
          this.monthDevelopment = this.setDataNumber(res.monthDevelopment)
          this.monthSales = this.setDataNumber(res.monthSales)
          this.monthSupply = this.setDataNumber(res.monthSupply)
          this.projectStat = this.setDataNumber(res.projectStat)
          for (let i = 0; i < (this.projectStat.projectList).length; i++) {
            this.projectStat.projectList[i].reduction = setLargeNumber(this.projectStat.projectList[i].reduction)
            this.projectStat.projectList[i].carbonValuation = setLargeNumber(this.projectStat.projectList[i].carbonValuation)
          }
          this.quotation = this.setDataNumber(res.quotation)
          this.quotation.projects = this.setDataNumber(res.quotation.projects)
          this.carbonCredit = setLargeNumber(res.carbonCredit)
          this.carbonQuota = setLargeNumber(res.carbonQuota)
          this.greenScore = setLargeNumber(res.greenScore)
          this.assetsIncome.statDate = this.getLastMonth(this.assetsIncome.statDate)
          this.fundIncome.statDate = this.getLastMonth(this.fundIncome.statDate)
          this.monthSupply.statDate = this.getLastMonth(this.monthSupply.statDate)
          this.monthDevelopment.statDate = this.getLastMonth(this.monthDevelopment.statDate)
          this.monthSales.statDate = this.getLastMonth(this.monthSales.statDate)
        }
      ).catch((res) => {
      })
    },
  },
  mounted() {
    this.getAllDictionary()
    this.getHomePanelData()
    this.getNewsList()
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    if (!this.roles.includes('admin')) {
      this.currentRole = 'editorDashboard'
    }
  }
}
</script>
