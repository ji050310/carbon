import request from '@/utils/request'

export function loadGreenIndex(){
    return request({
            url: '/system/dataPanel/getGreenness',
            method: 'get'
    })
}