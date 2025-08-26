
export const Industry = "001" //   产业
export const Business = "002" //   行业
export const ProjectArea = "003" //	项目领域
export const ProjectType = "004" //	项目类型
export const WorldRegion = "005" //	世界地区
export const Country = "006" //	国家
export const CertificationCriteria = "007" //	项目核证标准
export const Company = "008" //	公司
export const Methodology = "009" // 自愿减排方法学
export const ProjectStatus = "010" //	项目状态
export const Province = "011" //	省/直辖市/自治区
export const City = "012" //	市/县/村
export const AssetStatus = "013" //	资产状态
export const AssetType = "014" //	资产类型
export const AssetUnit = "015" //	资产单位
export const AssetTradeStatus = "016" //	资产交易状态
export const Exchange = "017" //	交易所
export const NewsType = "018" //	资讯类型
export const DiliveryMethod = "019" //	资产交割方式
export const SystemRole = "020" //	系统角色
export const DocumentType = "021" //	项目文档类型
export const UserSource = "022" //	用户来源
export const ProjectOwnerInfo = "023" //	项目业主资料
export const Electric = "024" //	电网
export const CarType = "025" //	车型
export const NewsStatus = "026" //	资讯状态
export const TradeRole = "027" //	交易角色
export const InquiryQuotationDire = "028" //	询报价方向
export const IntendedTransactionDire = "029" //	意向成交方向
export const CertificationInstitution = "052" //	核证机构
export const accountType = "038" //	账户类型
export const accountStatus = "039" //	账户状态
export const ProductVersion = "040" //	产品版本
export const reportStatus = "031" //	报送状态
export const issueInstitution = "044" //	签发机构	
export const carFuel = "106" //	汽车燃料
export const methodStatus = "045" //	汽车燃料
export const projectYear = "056" //	项目年份
export const projectYearCount = "057" //	项目年头
export const CarNewType = "107" //	车型
export const CarbonEnterprise  = "062" //	全国碳市场控排企业
export const MethodologyQuestion = "046" //	碳资产方法学评估问卷项
export const MethodologyAnswers  = "059" //	碳资产方法学评估问卷项答案
export const PowerGrid  = "101" //	电网
export const ProjectIndicators  = "901" //	项目指标-电动汽车充电
export const Feishu  = "041" //	飞书appid
import { getAllDict } from '@/api/systemConfig'
var dircs = []
/**
 * 获取全部字典
 * @param {} pram 
 * @returns 
 */
export function getAllDiction(store) {
   getAllDict().then(res => {
      try {
         dircs = []
         localStorage.setItem('dircs', '');
         localStorage.setItem('dircs', JSON.stringify(res));
      } catch (error) {
      }
   }).catch(err => {

   })
}

/**
 * 电网
 * @param {*} store 
 * @returns 
 */
 export function getPowerGridDict(store) {
   return getDircByCode(store, PowerGrid)
}
/**
 * 飞书appid
 * @param {*} store 
 * @returns 
 */
 export function getAppId(store) {
   return getDircByCode(store, Feishu)
}
/**
 * 项目指标-电动汽车充电
 * @param {*} store 
 * @returns 
 */
 export function getProjectIndicatorsDict(store) {
   return getDircByCode(store, ProjectIndicators)
}
/**
 * 碳资产方法学评估问卷项答案
 * @param {*} store 
 * @returns 
 */
 export function getMethodologyAnswersDict(store) {
   return getDircByCode(store, MethodologyAnswers)
}
/**
 * 碳资产方法学评估问卷项
 * @param {*} store 
 * @returns 
 */
 export function getMethodologyQuestionDict(store) {
   return getDircByCode(store, MethodologyQuestion)
}
/**
 * 全国碳市场控排企业
 * @param {*} store 
 * @returns 
 */
 export function getCarbonEnterprise(store) {
   return getDircByCode(store, CarbonEnterprise)
}
/**
 * 项目年份
 * @param {*} store 
 * @returns 
 */
 export function getProjectYear(store) {
   return getDircByCode(store, projectYear)
}
/**
 * 作者: 
 * 时间: 2022-09-26 09:35:51
 * 功能: 项目年头字典
 */
export function getProjectYearCount(store) {
   return getDircByCode(store, projectYearCount)
}
/**
 * 作者: 
 * 时间: 2022-06-16 09:35:51
 * 功能: 报送状态字典
 */
export function getReportStatus(store) {
   return getDircByCode(store, reportStatus)
}
/**
 * 作者: 
 * 时间: 2022-06-16 09:35:51
 * 功能: 签发机构
 */
export function getIssueInstitution(store) {
   return getDircByCode(store, issueInstitution)
}
/**
 * 产业字典
 * @param {*} store 
 * @returns 
 */
export function getIndustryDict(store) {
   return getDircByCode(store, Industry)
}

/**
 * 产业字典
 * @param {*} store 
 * @returns 
 */
export function getBusinessDict(store) {
   return getDircByCode(store, Business)
}

/**
* 项目领域字典
* @param {*} store 
* @returns 
*/
export function getProjectAreaDict(store) {
   return getDircByCode(store, ProjectArea)
}

/**
* 项目类型字典
* @param {*} store 
* @returns 
*/
export function getProjectTypeDict(store) {
   return getDircByCode(store, ProjectType)
}

/**
* 世界地区字典
* @param {*} store 
* @returns 
*/
export function getWorldRegionDict(store) {
   return getDircByCode(store, WorldRegion)
}

/**
 * 国家字典
 * @param {*} store 
 * @returns 
 */
export function getCountryDict(store) {
   return getDircByCode(store, Country)
}

/**
 * 项目核证标准字典
 * @param {*} store 
 * @returns 
 */
export function getCertificationCriteriaDict(store) {
   return getDircByCode(store, CertificationCriteria)
}

/**
 * 公司字典
 * @param {*} store 
 * @returns 
 */
export function getCompanyDict(store) {
   return getDircByCode(store, Company)
}

/**
 *  自愿减排方法学字典
 * @param {*} store 
 * @returns 
 */
export function getMethodologyDict(store) {
   return getDircByCode(store, Methodology)
}

/**
 * 项目状态字典
 * @param {*} store 
 * @returns 
 */
export function getProjectStatusDict(store) {
   return getDircByCode(store, ProjectStatus)
}

/**
 * 省/直辖市/自治区字典
 * @param {*} store 
 * @returns 
 */
export function getProvinceDict(store) {
   return getDircByCode(store, Province)
}


/**
 * 城市字典
 * @param {*} store 
 * @returns 
 */
export function getCityDict(store) {
   return getDircByCode(store, City)
}

/**
 * 资产状态字典
 * @param {*} store 
 * @returns 
 */
export function getAssetStatusDict(store) {
   return getDircByCode(store, AssetStatus)
}
/**
 * 作者: 
 * 时间: 2022-07-14 14:41:21
 * 功能: 方法学状态
 */
export function getMethodStatusDict(store) {
   return getDircByCode(store, methodStatus)
}
/**
 * 资产类型字典
 * @param {*} store 
 * @returns 
 */
export function getAssetTypeDict(store) {
   return getDircByCode(store, AssetType)
}

/**
 * 资产单元字典
 * @param {*} store 
 * @returns 
 */
export function getAssetUnitDict(store) {
   return getDircByCode(store, AssetUnit)
}

/**
 * 资产交易状态字典
 * @param {*} store 
 * @returns 
 */
export function getAssetTradeStatusDict(store) {
   return getDircByCode(store, AssetTradeStatus)
}

/*
 *@Description: 核证机构
 *@MethodAuthor: liuboting
 *@Date: 2022-06-19 11:01:54
*/
export function getCertificationInstitutionDict(store) {
   return getDircByCode(store, CertificationInstitution)
}
/**
 * 交易所字典
 * @param {*} store 
 * @returns 
 */
export function getExchangeDict(store) {
   return getDircByCode(store, Exchange)
}

/**
 * 资讯类型字典
 * @param {*} store 
 * @returns 
 */
export function getNewsTypeDict(store) {
   return getDircByCode(store, NewsType)
}

/**
 * 资产交割方式字典
 * @param {*} store 
 * @returns 
 */
export function getDiliveryMethodeDict(store) {
   return getDircByCode(store, DiliveryMethod)
}

/**
 * 系统角色字典
 * @param {*} store 
 * @returns 
 */
export function getSystemRoleDict(store) {
   return getDircByCode(store, SystemRole)
}

/**
 * 文档类型字典
 * @param {*} store 
 * @returns 
 */
export function getDocumentTypeDict(store) {
   return getDircByCode(store, DocumentType)
}

/**
 * 用户字典
 * @param {*} store 
 * @returns 
 */
export function getUserSourceDict(store) {
   return getDircByCode(store, UserSource)
}

/**
 * 项目业主资料字典
 * @param {*} store 
 * @returns 
 */
export function getProjectOwnerInfoDict(store) {
   return getDircByCode(store, ProjectOwnerInfo)
}

/**
 * 电网字典
 * @param {*} store 
 * @returns 
 */
export function getElectricDict(store) {
   return getDircByCode(store, Electric)
}

/**
 * 车型字典
 * @param {*} store 
 * @returns 
 */
export function getCarTypeDict(store) {
   return getDircByCode(store, CarType)
}
/**
 * 车型
 * @param {*} store 
 * @returns 
 */
export function getCarNewTypeDict(store) {
   return getDircByCode(store, CarNewType)
}

/**
 * 资讯状态字典
 * @param {*} store 
 * @returns 
 */
export function getNewsStatusDict(store) {
   return getDircByCode(store, NewsStatus)
}

/**
 * 交易角色字典
 * @param {*} store 
 * @returns 
 */
export function getTradeRoleDict(store) {
   return getDircByCode(store, TradeRole)
}

/**
 * 询报价方向字典
 * @param {*} store 
 * @returns 
 */
export function getInquiryQuotationDireDict(store) {
   return getDircByCode(store, InquiryQuotationDire)
}

/**
 * 意向成交方向字典
 * @param {*} store 
 * @returns 
 */
export function getIntendedTransactionDireDict(store) {
   return getDircByCode(store, IntendedTransactionDire)
}
/**
 * 作者: 
 * 时间: 2022-05-30 16:29:03
 * 功能: 账户类型字典
 */
export function getAccountTypeDict(store) {
   return getDircByCode(store, accountType)
}
/**
 * 作者: 
 * 时间: 2022-05-30 16:29:38
 * 功能: 账户状态字典
 */
export function getAccountStatusDict(store) {
   return getDircByCode(store, accountStatus)
}
/**
 * 作者: 
 * 时间: 2022-05-30 16:29:38
 * 功能: 汽车燃料
 */
export function getCarFuelDict(store) {
   return getDircByCode(store, carFuel)
}
/**
 * 作者: 
 * 时间: 2022-05-30 16:30:09
 * 功能: 产品版本字典
 */
export function getProductVerssionDict(store) {
   return getDircByCode(store, ProductVersion)
}
function getDircByCode(store, key) {
   if (Object.keys(dircs).length == 0) {
      let res = JSON.parse(localStorage.getItem('dircs'));
      res.map(e => {
         let data = dircs[e.dictCode]
         if (!data) {
            data = new Array()
            data.push(e)
            dircs[e.dictCode] = data
         } else {
            data.push(e)
            dircs[e.dictCode] = data
         }
      })
   }


   if (dircs) {
      return dircs[key]
   }
   return ""
}
