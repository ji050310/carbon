import request from '@/utils/newRequest'
import SettingMer from '@/utils/settingMer'
import store from '@/store'
/***********碳减排项目***************/
/* 项目开发实施分页列表 */
export function getCarbonProjectDoList(data) {
        return request({
                url: '/assets/carbonProject/getDevelopPageList',
                method: 'POST',
                data
        })
}
//供需行情全文检索
export function searchKeyword(data) {
        return request({
                url: '/trade/carbonTradeQuote/search',
                method: 'GET',
                params: data,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
//拿到城市字典
export function getCityDict() {
        return request({
                url: '/system/sysDict/getCityDict',
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
//添加碳项目文档
export function addCarbonProjectFile(data) {
        return request({
                url: '/assets/carbonProjectDoc/add',
                method: 'POST',
                data
        })
}
// 拿到碳信用面板数据
export function getCreditData() {
        return request({
                url: '/assets/carbonCreditAssets/assetsTotal',
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
// 拿到碳配额面板数据
export function getQuotaData() {
        return request({
                url: '/assets/carbonQuotaAssets/assetsTotal',
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
// 碳配额详情
export function carbonQuotaDetail(id) {
        return request({
                url: '/assets/carbonQuotaAssets/info/' + id,
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
// 删除碳信用
export function delCredit(id) {
        return request({
                url: '/assets/carbonCreditAssets/delete?id=' + id,
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
/**
 * 20220819 使用getCarbonMetaregistryPageList 接口
 * 碳减排项目列表
 * @returns 
 */
export function getCarbonProjectPageList(data) {
        return request({
                url: '/assets/carbonProject/getPageList',
                method: 'POST',
                data
        })
}
/**
 * 20221009 状态为非待审核状态 项目分页列表
 * 上传文档的碳减排项目列表
 * @returns 
 */
export function getCarbonProjectNoWaitExaminePageList(data) {
        return request({
                url: '/assets/carbonProject/getNoWaitExaminePageList',
                method: 'POST',
                data
        })
}
/**
 * 20220819 
 * 碳减排项目列表
 * @returns 
 */
export function getCarbonMetaregistryPageList(data) {
        return request({
                url: '/assets/carbonMetaregistry/getPageList',
                method: 'POST',
                data
        })
}
/**
 * 作者: 
 * 时间: 2022-06-06 10:03:48
 * 功能: 碳数据报送列表
 */
export function getCarbonProjectReportList(data) {
        return request({
                url: '/assets/carbonProject/dataSubmissionPageList',
                method: 'POST',
                data
        })
}
/**
 * 作者: 
 * 时间: 2022-06-24 16:15:02
 * 功能: 碳配额列表
 */
export function loadCarbonQuotaPageList(data) {
        return request({
                url: '/assets/carbonQuotaAssets/getPageList',
                method: 'POST',
                data
        })
}
/**
 * 申请碳减排项目
 * @returns 
 */
export function addCarbonProject(data) {
        return request({
                url: '/assets/carbonProject/add',
                method: 'POST',
                data
        })
}

/**
 * 查看碳减排项目
 * @returns 
 */
export function readCarbonProject(id) {
        return request({
                url: '/assets/carbonProject/info/' + id,
                method: 'GET',
                // params: {'id' : id},
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
export function addOwnerData(data) {
        let token = !store.getters.token ? sessionStorage.getItem('token') : store.getters.token;
        return request({
                url: '/assets/carbonProject/uploadOwnerData',
                method: 'POST',
                // params: {'id' : id},
                headers: { 'token': token },
                data: data
        })
}
export function getUpLoadFileParams() {
        let token = !store.getters.token ? sessionStorage.getItem('token') : store.getters.token;
        return {
                url: `${SettingMer.apiBaseURL}/assets/carbonProject/uploadOwnerData`,
                token: token
        }
}

/**
 * 删除碳减排项目
 * @returns 
 */
export function deleteCarbonProject(id) {
        return request({
                url: '/assets/carbonProject/delete/' + id,
                method: 'DELETE',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
/**
* 编辑碳减排项目
* @returns 
*/
export function editCarbonProject(data) {
        return request({
                url: '/assets/carbonProject/update',
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                data: data
        })
}

/*********************数据报送***************/
/**
 * 获取碳数据源列表
 * @param {*} data 
 * @returns 
 */
export function loadSourceDataList(data) {
        return request({
                url: '/assets/carbonSourceData/getPageList',
                method: 'POST',
                data
        })
}
/**
 * 作者: 
 * 时间: 2022-06-24 16:12:33
 * 功能: 添加碳配额
 */
export function addCarbonQuota(data) {
        return request({
                url: '/assets/carbonQuotaAssets/add',
                method: 'POST',
                data
        })
}
/**
 * 添加碳数据
 * @returns 
 */
export function addSourceData(data) {
        return request({
                url: '/assets/carbonSourceData/add',
                method: 'POST',
                data
        })
}

/**
 * 查看碳源数据
 * @returns 
 */
export function readSourceData(id) {
        return request({
                url: '/assets/carbonSourceData/info/' + id,
                method: 'GET',
                // params: {'id' : id},
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}

/**
 * 删除碳减源数据
 * @returns 
 */
export function deleteSourceData(id) {
        return request({
                url: '/assets/carbonSourceData/delete/' + id,
                method: 'DELETE',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
/**
* 修改碳源数据
* @returns 
*/
export function editSourceData(data) {
        return request({
                url: '/assets/carbonSourceData/update',
                method: 'PUT',
                data: data
        })
}

/**
 * 数据源报送
 * @param {*} id 
 * @returns 
 */
export function submittedSourceData(id) {
        return request({
                url: '/assets/carbonSourceData/submitted/' + id,
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
/**
 * 作者: 
 * 时间: 2022-05-25 11:10:45
 * 功能: 碳数据报送接口
 */
export function reportProject(data) {
        return request({
                url: '/assets/carbonProjectMonitoringData/add',
                method: 'POST',
                data: data
        })
}
/*********************中和资产***************/
/**
 * 上传业主资料
 * @param {*} data 
 * @returns 
 */
export function uploadOwnerFile(data) {
        return request({
                url: '/assets/carbonProject/uploadOwnerData',
                method: 'POST',
                data
        })
}
/**
 * 作者: 
 * 时间: 2022-05-25 11:22:20
 * 功能: 获取项目碳数据报送列表
 */
export function getProjectReport(id) {
        return request({
                url: '/assets/carbonProject/dataSubmissionPage/' + id,
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
/**
 * 作者: 
 * 时间: 2022-05-25 11:58:35
 * 功能: 修改碳数据报送列表
 */
export function changeProjectReport(data) {
        return request({
                url: '/assets/carbonProjectMonitoringData/update',
                method: 'PUT',
                data: data
        })
}
/*********************中和资产***************/
/**
 * 获取中和资产列表
 * @param {*} data 
 * @returns 
 */
export function loadCarbonAssetsList(data) {
        return request({
                url: '/assets/carbonAssets/getPageList',
                method: 'POST',
                data
        })
}
/**
 * 添加中和资产数据
 * @returns 
 */
export function addCarbonAssetsData(data) {
        return request({
                url: '/assets/carbonAssets/add',
                method: 'POST',
                data
        })
}

/**
 * 查看中和资产数据
 * @returns 
 */
export function readCarbonAssetsData(id) {
        return request({
                url: '/assets/carbonAssets/info/' + id,
                method: 'GET',
                // params: {'id' : id},
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}

/**
 * 删除中和资产源数据
 * @returns 
 */
export function deleteCarbonAssetsData(id) {
        return request({
                url: '/assets/carbonAssets/delete/' + id,
                method: 'DELETE',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
/**
* 修改中和资产数据
* @returns 
*/
export function editCarbonAssetsData(data) {
        return request({
                url: '/assets/carbonAssets/update',
                method: 'PUT',
                data: data
        })
}


/*********************方法学***************/
/**
 * 获取方法学列表
 * @param {*} data 
 * @returns 
 */
export function loadMethodList(data) {
        return request({
                url: '/assets/carbonMethodology/getPageList',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}

/*********************方法学全文检索***************/
/**
 * 获取方法学列表
 * @param {*} data 
 * @returns 
 */
export function getEscarbonMethodologyByKeyword(data) {
        return request({
                url: '/assets/EscarbonMethodology/getByKeyword',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data,
        })
}
/**
* 添加方法学
* @returns 
*/
export function addMethod(data) {
        return request({
                url: '/assets/carbonMethodology/add',
                method: 'POST',
                data
        })
}
/**
 * 20221018 16:50
* 添加方法学
* @returns 
*/
export function addCarbonMethodology(data) {
        return request({
                url: '/assets/carbonMethodology/upload',
                method: 'POST',
                data
        })
}
/**
 * 20221018 16:50
* 修改方法学
* @returns 
*/
export function updateCarbonMethodology(data) {
        return request({
                url: '/assets/carbonMethodology/update',
                method: 'PUT',
                data
        })
}
/**
 * 20221024 18:50
* 同步方法学内容
* @returns 
*/
export function synContentCarbonMethodology(data) {
        return request({
                url: '/assets/methodoloySyn/synContent',
                method: 'POST',
                data
        })
}

/**
 * 查看方法学
 * @returns 
 */
export function readMethod(id) {
        return request({
                url: '/assets/carbonMethodology/info/' + id,
                method: 'GET',
                // params: {'id' : id},
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}

/**
 * 删除方法学
 * @returns 
 */
export function deleteMethod(id) {
        return request({
                url: '/assets/carbonMethodology/delete/' + id,
                method: 'DELETE',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
/**
* 修改方法学
* @returns 
*/
export function editMethod(data) {
        return request({
                url: '/assets/carbonMethodology/update',
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                data: data
        })
}

/*********************交易所***************/
/**
* 获取交易所列表
* @param {*} data 
* @returns 
*/
export function loadCarbonExchangeList(data) {
        return request({
                url: '/assets/carbonExchange/getPageList',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
export function updateCarbonExchanger(data) {
        return request({
                url: '/assets/carbonExchange/update',
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
export function delCarbonExchanger(id) {
        return request({
                url: '/assets/carbonExchange/delete/' + id,
                method: 'DELETE',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        })
}
export function searchCarbonExchanger(id) {
        return request({
                url: '/assets/carbonExchange/info/' + id,
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        })
}
//碳信用模块
export function loadCarbonCreditPageList(data) {
        return request({
                url: '/assets/carbonCreditAssets/getPageList',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
export function carbonAssetDetail(id) {
        return request({
                url: '/assets/carbonCreditAssets/info/' + id,
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        })
}
/**
 * 作者: 
 * 时间: 2022-06-21 09:02:24
 * 功能: 添加碳信用
 */
export function addCarbonCredit(data) {
        return request({
                url: '/assets/carbonCreditAssets/add',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
//修改碳信用资产
export function changeCredit(data) {
        return request({
                url: '/assets/carbonCreditAssets/update',
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
// 
export function changeQuota(data) {
        return request({
                url: '/assets/carbonQuotaAssets/update',
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
//添加供需行情
export function addcarbonAssetMarket(data) {
        return request({
                url: "/trade/carbonTradeQuote/add",
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
/**
* 
* 交易账户-交易账户解绑
* @param {}  
* @returns 
*/
export function putCarbonExchangeUnbind(id) {
        return request({
                url: '/assets/exchangeAccount/unbind/' + id,
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                data: id

        })
}

//项目文档
export function loadcarbonProjectDoc(data) {
        return request({
                url: '/assets/carbonProjectDoc/getPageList',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
//项目对象文档
export function loadcarbonDoc(id) {
        return request({
                url: '/assets/carbonProjectDoc/info/' + id,
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        })
}

/**供需行情 **/
// 获取CarbonTradeQuote分页列表
export function getCarbonTradeQuoteList(data) {
        return request({
                url: '/trade/carbonTradeQuote/getPageList',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}

/** 询报价*/
export function startTrading(data) {
        return request({
                url: '/trade/carbonTradeQuote/startTrading',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}

/** 添加供需行情 */
export function pushQuote(data) {
        return request({
                url: '/trade/carbonTradeQuote/add',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}


/** 履约管理 */
// 获取履约管理列表
export function getCarbonTradePerfenceList(data) {
        return request({
                url: '/trade/carbonTradeContract/getPageList',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
// 获取履约详情
export function getCarbonTradePerfenceDetail(id) {
        return request({
                url: '/trade/carbonTradeContract/info/' + id,
                method: 'GET',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        })
}

// 更改履约详情
export function updateCarbonTradePerfenceDetail(data) {
        return request({
                url: '/trade/carbonTradeContract/update',
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}

/** 询报价管理 */
// 获取报价管理列表
export function getCarbonTradePriceList(data) {
        return request({
                url: '/trade/carbonTradePrice/getPageList',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}
// 获取报价详情
export function getCarbonTradePriceDetail(id) {
        return request({
                url: '/trade/carbonTradePrice/info/' + id,
                method: 'POST',
                headers: { 'Content-Type': 'x-www-form-urlencoded' },
                data
        })
}

// 更改报价详情
export function updateCarbonTradePriceDetail(data) {
        return request({
                url: '/trade/carbonTradePrice/update',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}

// 更改报价详情
export function intendedTransaction(data) {
        return request({
                url: '/trade/carbonTradePrice/intendedTransaction',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data
        })
}

// 下单
export function performance(id) {
        return request({
                url: '/trade/carbonTradeContract/performance/' + id,
                method: 'PUT',
                headers: { 'Content-Type': 'x-www-form-urlencoded' },
        })
}

/**
 * 作者: 
 * 时间: 2022-08-10
 * 功能: 获取项目仓库
 * 获取CarbonMetaregistry分页列表
 * @param {*} data 
 * @returns 
 */
export function getCarbonMetaregistryList(data) {
        return request({
                url: '/assets/carbonMetaregistry/getPageList',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data,
        })
}
/**
 * 添加CarbonMetaregistry对象
 * @param {*} data 
 * @returns 
 */
export function addCarbonMetaregistry(data) {
        return request({
                url: '/assets/carbonMetaregistry/add',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data,
        })
}

/**
 * 删除CarbonMetaregistry对象
 * @returns 
 */
export function deleteCarbonMetaregistry(id) {
        return request({
                url: '/assets/carbonMetaregistry/delete/' + id,
                method: 'DELETE',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
}
/**
 * 获取CarbonMetaregistry对象详情
 * @param {*} data 
 * @returns 
 */
export function getCarbonMetaregistryInfo(id) {
        return request({
                url: '/assets/carbonMetaregistry/info/' + id,
                method: 'GET',
                headers: { 'Content-Type': 'application/json' },
        })
}
/**
 * 修改CarbonMetaregistry对象
 * @param {*} data 
 * @returns 
 */
export function updateCarbonMetaregistry(data) {
        return request({
                url: '/assets/carbonMetaregistry/update',
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                data,
        })
}
/**
 * 项目文档全文检测列表
 * @param {*} data 
 * @returns 
 */
export function getEscarbonMetaregistryList(data) {
        return request({
                url: '/assets/EscarbonMetaregistry/getByKeyword',
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                data,
        })
}




