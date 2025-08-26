<template>
  <div class="divBox">
    <el-card class="box-card">
      <div class="container">
          <el-form inline size="small">
            <el-form-item label="项目名称：">
              <el-input v-model="searchKeyWord" placeholder="请输入项目名称" class="selWidth" size="small" clearable>
              </el-input>
            </el-form-item>
            <el-form-item label="项目类型：">
              <el-cascader 
                v-model="carbonMethodology" 
                :options="carbonMethodologys"
                clearable class="selWidth"  size="small"/>
            </el-form-item>
        </el-form>
      </div>
      <div class="container">
       <el-form inline size="small">
        <el-form-item label="申请日期：">
            <!-- <el-cascader 
                :options="options"
                clearable class="selWidth"  size="small"/> -->
            <el-date-picker
            v-model="value2"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="right">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态：">
              <el-cascader 
                v-model="status" 
                :options="statusList"
                clearable class="selWidth"  size="small"/>
        </el-form-item>
       <el-button class="btn"  @click="onClickSearch">查询</el-button>
       <el-button class="btn"  @click="onClickAdd">申请</el-button>
       </el-form>
      </div>
      <el-table
        v-loading="listLoading"
        :data="tableData"
        style="width: 100%;"
        size="mini"
        highlight-current-row
      >
        <!-- <el-table-column
          align="center"
          width="150px"
          prop="id"
          label="ID"
        /> -->
        <el-table-column
          align="center"
          prop="name"
          label="项目名称"
        />
        <el-table-column
          align="center"
          prop="typeName"
          label="项目类型"
        />
        <el-table-column
          align="center"
          prop="amount"
          label="预估年减排量(tCO2)"
        />
        <el-table-column
          align="center"
          prop="createdTime"
          label="申请日期"
        />
        <el-table-column
          align="center"
          prop="statusName"
          label="状态"
        />
        <el-table-column label="操作" fixed="right" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="handleRead(scope.row.id)">查看</el-button>
            <el-button  type="text" size="small" @click="handleEdit(scope.row.id)">修改</el-button>
            <el-button type="text" size="small" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          :page-size="pageSize"
          :current-page="currentPage"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalNumber"
          @size-change="handleSizeChange"
          @current-change="pageChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
  import { getCarbonProjectPageList, deleteCarbonProject } from '../../../api/carbonAssetApi';
export default {

  name: 'ProductList',
  data() {
    return {
      listLoading: false,
      searchKeyWord: '',
      carbonMethodologyName: '',
      status: 0,
      carbonMethodology: 0,
      carbonMethodologys: [
              {'label': 'CCER', 'value': 0},
              {'label': 'CDM', 'value': 1},
              {'label': 'VCS', 'value': 2},
      ],
      status: 0,
      statusList: [
              {'label': '申请中', 'value': 0},
              {'label': '审核中', 'value': 1},
              {'label': '已核证', 'value': 2},
      ],
      tableData:[

      ],
      pageSize: 10,
      currentPage: 0,
      totalNumber:0,
      pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
        value2: ''
    }
  },
  mounted() {
    this.loadPageList(0)
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val
      this.loadPageList(0)
    },
    pageChange(val) {
      this.loadPageList(val)
    },
    onClickAdd() {
      this.$router.push('/applyProject/add')
    },
    onClickSearch() {
      
    },
    handleRead(id) {
      this.$router.push({
        path: '/applyProject/read',
        query: {
          'id': id
        }
      })
    },
    handleEdit(id) {
       this.$router.push({
        path: '/applyProject/edit',
        query: {
          'id': id
        }
      })
    },
    handleDelete(id) {
          this.$confirm('确定要删除该项目, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
           deleteCarbonProject(id).then(res => {
              if (res.code == 200) {
                 this.$message({
                   type: 'success',
                   message: '删除成功!'
                   });
                 this.loadPageList(0)
              }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    loadPageList(currentPage) {
       let data = {
         "asc": true,
         "current": currentPage,
         "size": this.pageSize,
         "sortField": ""
       }
       getCarbonProjectPageList(data).then(res => {
         
         if (res.code == 200 && res.data.total > 0) {
           this.currentPage = parseInt(res.data.current)
           this.totalNumber = parseInt(res.data.total)
           this.tableData = res.data.records
         }
       })
    }
  }
}
</script>

<style scoped lang="scss">
  .container{
    display: flexbox;
    flex-direction: row;
  }
  .selWidth {
    width: 150px;
  }
  .seachTiele{
    line-height: 30px;
  }
  .btn{
    height: 32px;
    width: 80px;
    background: rgb(0, 128, 111);
    color: #fff;
    text-align: center;
    align-self: center;
    align-items: center;
  }
</style>
