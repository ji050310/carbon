<template>
  <div class="divBox">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <el-tabs v-model="tableFrom.type" @tab-click="seachList">
          <el-tab-pane :label="item.name +'('+item.count +')' " :name="item.type.toString()" v-for="(item,index) in headeNum" :key="index"/>
        </el-tabs>
        <div class="container">
          <el-form inline size="small">
            <el-form-item label="碳数据分类：">
              <el-cascader v-model="tableFrom.cateId" :options="dataSourceList" clearable class="selWidth mr10" @change="seachList" size="small"/>
            </el-form-item>
            <el-form-item label="碳数据搜索：">
              <el-input v-model="tableFrom.keywords" placeholder="请输入碳数据名称，关键字，ID" class="selWidth mr10" size="small" clearable>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="mr10" @click="seachList" size="small">搜索</el-button>
            </el-form-item>
          </el-form>
        </div>
        <el-button size="small" type="primary" class="mr10" @click="addSource">添加碳数据</el-button>
        <!-- <el-button size="small" type="success" class="mr10" @click="importSource">导入碳数据</el-button>
        <el-button size="small" @click="exports">导出</el-button> -->
      </div>
      <el-table
        v-loading="listLoading"
        :data="tableData"
        style="width: 100%"
        size="mini"
        highlight-current-row
      >
        <!-- <el-table-column
          prop="id"
          label="ID"
          align="center"
        /> -->
        <el-table-column
          width="120"
          prop="typeName"
          label="资产类型"
          align="center"
        />
        <el-table-column
          prop="amount"
          label="数量"
          align="center"
        />
        <el-table-column
          prop="uom"
          label="UOM"
          align="center"
        />
        <el-table-column
          prop="sourceChannelName"
          label="数据源"
          align="center"
        />
        <el-table-column
          prop="startDate"
          label="起始时间"
          align="center"
        />
        <el-table-column
          prop="endDate"
          label="截止时间"
          align="center"
        />
        <el-table-column
          prop="submittedTime"
          label="报送日期"
          align="center"
        />
        <el-table-column
          prop="auditTime"
          label="审核日期"
          align="center"
        />
        <el-table-column
          prop="statusName"
          label="审核状态"
          align="center"
        />
        <!-- <el-table-column
          prop="updateTime"
          label="更新时间"
          align="center"
        /> -->
        <el-table-column label="操作" min-width="150" fixed="right" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="sendSourceData(scope.row.id)">报送</el-button>
            <el-button type="text" size="small" @click="handleRead(scope.row.id)" class="mr10">查看</el-button>
            <el-button  type="text" size="small" @click="handleEdit(scope.row.id, scope.$index)">修改</el-button>
            <!-- <el-button type="text" size="small" @click="handleDelete(scope.row.id, tableFrom.type)">删除</el-button> -->
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          :page-size="tableFrom.pageSize"
          :current-page="tableFrom.currentPage"
          layout="total, sizes, prev, pager, next, jumper"
          :total="tableFrom.total"
          @size-change="handleSizeChange"
          @current-change="pageChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import {loadSourceDataList, deleteSourceData, submittedSourceData} from '../../../api/carbonAssetApi'
export default {
  data() {
    return {
      headeNum: [],
      listLoading: false,
      tableData:[],
      tableFrom: {
        currentPage: 0,
        pageSize: 10,
        total: 0,
        cateId: '',
        keywords: '',
        type: '1'
      },
      categoryList: [],
      dataSourceList: [
        {'label':'碳减排', 'value': 0},
        {'label':'碳配额', 'value': 1},
        {'label':'碳排放', 'value': 2},
      ],
    }
  },
  mounted() {
    this.getList(0)
  },
  methods: {
    seachList() {
      this.tableFrom.page = 1
      this.getList()
    },
    handleClose() {
      
    },
    handleCloseMod(item){
      
    },
    addSource() {
      this.$router.push('/dataRecord/add')
    },
    sendSourceData(id) {
      this.$confirm('报送近3个月碳数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
           submittedSourceData(id).then(res => {
              if (res.code == 200) {
                 this.$message({
                   type: 'success',
                   message: '删除成功!'
                   });
                 this.getList(0)
              }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });

    },
    handleEdit(id) {
      this.$router.push({
        path: '/dataRecord/edit',
        query: {
          'id': id
        }
      })
    },
    handleRead(id) {
      this.$router.push({
        path: '/dataRecord/read',
        query: {
          'id': id
        }
      })
    },
    handleDelete(id) {
      this.$confirm('确定要删除该碳数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
           deleteSourceData(id).then(res => {
              if (res.code == 200) {
                 this.$message({
                   type: 'success',
                   message: '删除成功!'
                   });
                 this.getList(0)
              }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    // 复制
    onCopy(){
      
    },
    // 导出
    exports () {
      // productExcelApi({cateId:this.tableFrom.cateId,keywords: this.tableFrom.keywords, type:this.tableFrom.type}).then((res) => {
      //   window.open(res.fileName)
      // })
    //  window.open(this.objectUrl + 'admin/export/excel/product?type=1&Authori-zation=' + getToken())
    },
    // 列表
    getList(page) {
      this.listLoading = false
      let data = {
        "asc": true,
        "current": page,
        "size": this.tableFrom.pageSize,
        "sortField": ""
      }

      loadSourceDataList(data).then(res => {
        if (res.code == 200) {
          this.tableData = res.data.records
          this.tableFrom.total = parseInt(res.data.total)
          this.tableFrom.currentPage = parseInt(res.data.current)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    pageChange(page) {
      this.tableFrom.page = page
      this.getList(page)
    },
    handleSizeChange(val) {
      this.tableFrom.pageSize = val
      this.getList(0)
    },
    onchangeIsShow(row) {
      // row.isShow
      //   ? putOnShellApi( row.id ).then(() => {

      //     this.getList()
      //   }).catch(()=>{
      //     row.isShow = !row.isShow
      //   }) : offShellApi(row.id).then(() => {

      //     this.getList()
      //   }).catch(()=>{
      //     row.isShow = !row.isShow
      //   })
    }
  }
}
</script>

<style scoped lang="scss">
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
    width: 250px !important;
  }
  .seachTiele{
    line-height: 30px;
  }
</style>
