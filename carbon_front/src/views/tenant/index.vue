<template>
    <div>
     <p>sssssss</p>
    </div>
</template>
<script>
import {getPageList} from '@/api/tenant.js'
export default {
    data() {
        return {

        }
    },
    methods: {
        getPageListData() {
            let params = {
                "asc": true,
                "current": 0,
                "size": 0,
                "sortField": ""
            }
            getPageList(params).then(res => {
                

            }).catch(error => {

            })
        }
    },

    mounted() {

    }
}
</script>