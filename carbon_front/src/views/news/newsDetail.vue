<template>
 <div>
  <iframe src="https://www.cnblogs.com/fangcunjian/p/15716509.html" ></iframe>
 </div>
</template> 
<script>
  export default {
    name: 'companyPackage',
    data() {
      return {
        url: ''
      }
    },
    mounted() {

    },
    methods: {

    },
    created() {
      this.url = this.$route.query.url
    },
    mounted() {
      
    }
  }
</script>
<style lang="scss" scoped>


</style>