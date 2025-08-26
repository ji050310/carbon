import request from '@/utils/request'

export function getHomePanelData(token) {
    return request({
        url: '/system/dataPanel/home',
        method: 'get',
        params: [],
        headers: {'token': token}
    })
}