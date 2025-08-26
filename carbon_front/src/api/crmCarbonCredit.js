import request from '@/utils/request'

/**
* 碳积分详情
* @param {}  
* @returns
/system/crmCarbonCreditDetail/info/{userId}
*/
export function getCrmCarbonCreditDetailInfo(userId){
    return request({
        url:'/system/crmCarbonCreditDetail/info/' + userId,
        method:'GET',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}

/**
* 兑换记录
* @param {}  
* @returns
/system/crmExchangeRecord/info/{userId}
*/
export function getcrmExchangeRecordInfo(userId){
    return request({
        url:'/system/crmExchangeRecord/info/' + userId,
        method:'GET',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'}
    })
}
/**
* 提现记录
* @param {}  
* @returns
*/
export function getcrmWithdrawalRecordInfo(userId){
    return request({
        url:'/system/crmWithdrawalRecord/info/' + userId,
        method:'GET',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'}
    })
}
/**
* 刪除用戶
* @param {}  
* @returns
*/
export function getTenantUserDelete(userId){
    return request({
        url:'/system/tenantUser/delete/' + userId,
        method:'DELETE',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'}
    })
}
/**
* 碳信分详情模块 日期查询
* @param {}  
* @returns 
*/
export function getCarbonCreditDetail(data){
    return request({
        url:'/system/crmCarbonCreditDetail/date',
        method:'GET',
        params: data,
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })

}
/**
* 提现记录模块 日期查询
* @param {}  
* @returns 
*/
export function getCrmWithdrawalRecord(data){
    return request({
        url:'/system/crmWithdrawalRecord/date',
        method:'GET',
        params: data,
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })

}
/**
* 兑换记录模块 日期查询
* @param {}  
* @returns 
*/
export function getCrmExchangeRecord(data){
    return request({
        url:'/system/crmExchangeRecord/date',
        method:'GET',
        params: data,
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })

}



