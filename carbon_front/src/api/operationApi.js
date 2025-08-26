import request from '@/utils/request'
/**
 * 运营数据
 * @param pram
 */
 export function getOperationData(params) {
    return request({
      url: '/system/dataPanel/getCarbonAssets',
      method: 'get',
      params
    })
  }
  