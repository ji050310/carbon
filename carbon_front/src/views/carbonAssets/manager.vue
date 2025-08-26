<template>
  <div class="divBox">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <el-tabs v-model="tableFrom.type" @tab-click="seachList">
          <el-tab-pane :label="item.name +'('+item.count +')' " :name="item.type.toString()" v-for="(item,index) in headeNum" :key="index"/>
        </el-tabs>
        <div class="container">
          <el-form inline size="small">
            <el-form-item label="碳数据搜索：">
              <el-input v-model="tableFrom.keywords" placeholder="请输入碳数名称，关键字，ID" class="selWidth" size="small" clearable>
                <el-button slot="append" icon="el-icon-search" @click="seachList" size="small"/>
              </el-input>
            </el-form-item>
            <el-form-item label="碳数据分类：">
              <el-cascader v-model="tableFrom.cateId" :options="merCateList" :props="props" clearable class="selWidth mr20" @change="seachList" size="small"/>
            </el-form-item>
          </el-form>
        </div>
        <router-link :to=" { path:'/store/list/creatProduct' } ">
          <el-button size="small" type="primary" class="mr10">添加碳数据</el-button>
        </router-link>
        <el-button size="small" type="success" class="mr10" @click="onCopy">碳数据采集</el-button>
        <el-button size="small" @click="exports">导出</el-button>
      </div>
      <el-table
        v-loading="listLoading"
        :data="tableData.data"
        style="width: 100%"
        size="mini"
        highlight-current-row
      >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="商品分类：">
                <span v-for="(item, index) in props.row.cateValues.split(',')" :key="index" class="mr10">{{ item }}</span>
              </el-form-item>
              <el-form-item label="市场价：">
                <span>{{ props.row.otPrice }}</span>
              </el-form-item>
              <el-form-item label="成本价：">
                <span>{{ props.row.cost }}</span>
              </el-form-item>
              <el-form-item label="收藏：">
                <span>{{ props.row.collectCount }}</span>
              </el-form-item>
              <el-form-item label="虚拟销量：">
                <span>{{ props.row.ficti }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="id"
          label="ID"
          min-width="50"
        />
        <el-table-column
          prop="number"
          label="编号"
          min-width="200"
        />
        <el-table-column
          prop="status"
          label="状态"
          min-width="200"
        />
        <el-table-column
          prop="org"
          label="申请组织"
          min-width="90"
        />
        <el-table-column
          prop="cer"
          label="减排核证"
          min-width="90"
        />
        <el-table-column
          prop="reqTime"
          label="申请日期"
          min-width="90"
        />
        <el-table-column
          prop="updateTime"
          label="更新日期"
          min-width="90"
        />
        <el-table-column label="操作" min-width="150" fixed="right" align="center">
          <template slot-scope="scope">
            <router-link :to="{path: '/store/list/creatProduct/' + scope.row.id}">
              <el-button type="text" size="small" class="mr10">查看</el-button>
            </router-link>
            <el-button  type="text" size="small" @click="handleRestore(scope.row.id, scope.$index)">修改</el-button>
            <el-button type="text" size="small" @click="handleDelete(scope.row.id, tableFrom.type)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          :page-sizes="[20, 40, 60, 80]"
          :page-size="tableFrom.limit"
          :current-page="tableFrom.page"
          layout="total, sizes, prev, pager, next, jumper"
          :total="tableData.total"
          @size-change="handleSizeChange"
          @current-change="pageChange"
        />
      </div>
    </el-card>

    <!-- <el-dialog
      title="复制淘宝、天猫、京东、苏宁"
      :visible.sync="dialogVisible"
      width="1200px"
      :modal="false"
      class="taoBaoModal"
      :before-close="handleClose">
    </el-dialog> -->
  </div>
</template>

<script>
import { productLstApi, productDeleteApi, categoryApi, putOnShellApi, offShellApi, productHeadersApi, productExportApi, restoreApi, productExcelApi } from '@/api/store'
import { getToken } from '@/utils/auth'
export default {
  name: 'ProductList',
  data() {
    return {
      props: {
        children: 'child',
        label: 'name',
        value: 'id',
        emitPath: false
      },
      // roterPre: roterPre,
      headeNum: [],
      listLoading: true,
      tableData: {
        data: [],
        total: 0
      },
      tableFrom: {
        page: 1,
        limit: 20,
        cateId: '',
        keywords: '',
        type: '1'
      },
      categoryList: [],
      merCateList: [],
      objectUrl: process.env.VUE_APP_BASE_API,
      dialogVisible: false
    }
  },
  mounted() {
    this.goodHeade()
    this.getList()
    this.getCategorySelect()
  },
  methods: {
    handleRestore(id) {
      this.$modalSure("恢复商品").then(() => {
        restoreApi(id)
          .then((res) => {
            this.$message.success('操作成功');
            this.goodHeade();
            this.getList();
          })
      });
    },
    seachList() {
      this.tableFrom.page = 1
      this.getList()
    },
    handleClose() {
      this.dialogVisible = false
    },
    handleCloseMod(item){
      this.dialogVisible = item
      this.goodHeade();
      this.getList();
    },
    // 复制
    onCopy(){
      this.dialogVisible = true
    },
    // 导出
    exports () {
      productExcelApi({cateId:this.tableFrom.cateId,keywords: this.tableFrom.keywords, type:this.tableFrom.type}).then((res) => {
        window.open(res.fileName)
      })
    //  window.open(this.objectUrl + 'admin/export/excel/product?type=1&Authori-zation=' + getToken())
    },
    // 获取商品表单头数量
    goodHeade () {
      productHeadersApi().then(res => {
        this.headeNum = res
      }).catch(res => {
        this.$message.error(res.message);
      })
    },
    // 商户分类；
    getCategorySelect() {
      categoryApi({ status: -1, type: 1 }).then(res => {
        this.merCateList = res
      }).catch(res => {
        this.$message.error(res.message)
        this.$message.error(res.message)
      })
    },
    // 列表
    getList() {
      this.listLoading = true
      productLstApi(this.tableFrom).then(res => {
        this.tableData.data = res.list
        this.tableData.total = res.total
        this.listLoading = false
      }).catch(res => {
        this.listLoading = false
        this.$message.error(res.message)
      })
    },
    pageChange(page) {
      this.tableFrom.page = page
      this.getList()
    },
    handleSizeChange(val) {
      this.tableFrom.limit = val
      this.getList()
    },
    // 删除
    handleDelete(id, type) {
      this.$modalSure(`删除 id 为 ${id} 的商品`).then(() => {
        const deleteFlag = type == 5 ? 'delete':'recycle';
        productDeleteApi(id,deleteFlag).then(() => {
          this.$message.success('删除成功')
          this.getList()
          this.goodHeade();
        })
      })
    },
    onchangeIsShow(row) {
      row.isShow
        ? putOnShellApi( row.id ).then(() => {
          this.$message.success('上架成功')
          this.getList()
          this.goodHeade();
        }).catch(()=>{
          row.isShow = !row.isShow
        }) : offShellApi(row.id).then(() => {
          this.$message.success('下架成功')
          this.getList()
          this.goodHeade();
        }).catch(()=>{
          row.isShow = !row.isShow
        })
    }
  }
}
</script>

<style scoped lang="scss">
  .taoBaoModal{
    z-index: 333 !important;
  }
  .demo-table-expand{
    /deep/ label{
      width: 82px;
    }
  }
  .demo-table-expand{
    /deep/ .el-form-item__content{
      width: 77%;
    }
  }
  .selWidth{
    width: 350px !important;
  }
  .seachTiele{
    line-height: 30px;
  }
</style>
