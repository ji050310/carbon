<template>
   <el-card class="box-card">

    <div class="navicontainer">
       <div class="statussearch">
      <p class="statussearchTxt" v-if="dropBoxText">{{dropBoxText}}</p>
      <p class="statussearchHint" v-else>状态</p>
      <el-dropdown class="dropdown" @command="handleCommand">
      <i class="el-icon-arrow-down el-icon--right"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item :command="{label:'ss11s'}">显示</el-dropdown-item>
        <el-dropdown-item :command="{label:'scope.$index'}">不显示</el-dropdown-item>
        </el-dropdown-menu>
       </el-dropdown>
       </div>

       <el-input class="inputbar" placeholder="请输入身份昵称"  v-model="input">
       </el-input>
       <el-button class="searchbtn" type="primary">查询</el-button>
    </div>
    <el-button class="addRole" type="primary" @click="addRole">添加身份</el-button>   
    <a href="/addRole">sssss</a>
    <!-- 表格-->
    <el-table
    :data="records"
    style="width: 100%"
    max-height="250">
    <el-table-column
      fixed
      prop="roleName"
      label="身份昵称"
      style="width: 5%">
    </el-table-column>
    <el-table-column
      prop="roleCode"
      label="权限"
      style="width: 40%">
    </el-table-column>
    <el-table-column  label="状态" style="width: 5%">
      <template slot-scope="scope">
      <span v-if="scope.row.status == 0">显示</span> 
       <span v-else>不显示</span>
      </template>
    </el-table-column>
    <el-table-column
      prop="city"
      label="创建时间"
      style="width: 20%">
    </el-table-column>
    <el-table-column
      prop="address"
      label="更新时间"
      style="width: 20%">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      style="width: 10%">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
        <el-button type="text" size="small" @click="handleDelete(scope.$index)">删除</el-button>
      </template>
    </el-table-column>
   </el-table> 

   </el-card>
</template>

<script>
import { getRoleList } from '@/api/role';
// import { ElMessage, ElMessageBox } from "element-plus";
  export default {
    data() {
      return {
        dropBoxText:'',
        input: '',
        records: []
      }
    },
    methods: {
      deleteRow(index, rows) {
        rows.splice(index, 1);
      },
      getRoleListData() {
        const data = {
          "asc": true,
          "current": 0,
          "size": 0,
          "sortField": ""
        }
        getRoleList(data).then(res => {
          this.records = res.records
        }).catch (error => {
        })
      },
      handleCommand(command) {
        this.dropBoxText = command.label;
      },
       // 删除操作
      handleDelete(index) {
            // // 二次确认删除
            // ElMessageBox.confirm("确定要删除吗？", "提示", {
            //     type: "warning",
            // })
            //     .then(() => {
            //         ElMessage.success("删除成功");
            //         tableData.value.splice(index, 1);
            //     })
            //     .catch(() => {});
      },

     addRole() {
       this.$router.push({name: 'addRole'})
     }
    },
    mounted() {
      this.getRoleListData()
    }
  }
</script>
<style>
  .navicontainer{
    height: 50px;
    display: flex;
  }
  .statussearch {
    display: flex;
    border-radius: 4px;
    border:1px solid #DCDFE6;
    width:30%; 
    height: 36px;
    margin-right:30px
  }
  .statussearchHint{
    margin-top: auto;
    margin-bottom: auto;
    align-self: flex-end;
    margin-left: 20px;
    margin-right:auto;
    font-size: 14px;
    color: #c8cace;
  }
  .statussearchTxt{
    margin-top: auto;
    margin-bottom: auto;
    align-self: flex-end;
    margin-left: 20px;
    margin-right:auto;
    font-size: 14px;
    color: black;
  }
  .dropdown{
    margin-top: auto;
    margin-bottom: auto;
    align-self: flex-end;
    margin-left: auto;
    margin-right:20px
  }
  .inputbar{
    width: 30%;
  }
  .searchbtn{
    height: 36px;
    margin-left: 20px;
  }
  .addRole{
    height: 36px;
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .text {
    font-size: 14px;
  }
  .item {
    padding: 18px 0;
  }
  .box-card {
    margin: 20px;
    padding: 20px;
    width: auto;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
    margin-top: auto;
    margin-bottom: auto;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
