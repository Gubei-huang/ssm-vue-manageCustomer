<template>
  <div style="width: 80%;margin-left:10%;margin-right:10%">

    <div style="margin-top:20px">
    <el-button type="primary" @click="dialogInsert = true" style="float:right">新增用户</el-button>
      <el-input placeholder="请输入用户名" v-model="input" style="width: 200px;"/>
    </div>

    <el-table :data="listUser.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
     border height="calc(100vh - 285px)" style="margin-top:20px" >
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="password" label="密码"/>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <span v-if="!scope.row.disable">正常使用</span>
          <span v-if="scope.row.disable">已禁用</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="danger" v-if="!scope.row.disable" size="mini" @click="banUser(scope.row)">禁用</el-button>
          <el-button type="info" v-if="scope.row.disable" size="mini" @click="banUser(scope.row)">解除</el-button>
          <el-button size="mini" @click="dialogUpdate=true,user=scope.row">修改密码</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页按钮 -->
    <el-pagination
      style="margin-top:20px;width:60%"
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[8, 16, 32]"
      :page-size="pagesize"
      layout="total, sizes, prev, pager, next"
      :total="listUser.length">
      
    </el-pagination>
    
    <el-dialog title="增加用户" :visible.sync="dialogInsert" width="500px" center>
      <el-form :model="form" label-position="left" label-width="80px" >
          <el-form-item label="用户名">
            <el-input v-model="form.username"/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password"/>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button type="primary" @click="insertUser">添加用户</el-button>
          <el-button @click="dialogInsert = false">取 消</el-button>
        </div>
    </el-dialog>

    <!-- 修改图书界面 -->
    <el-dialog title="修改客户" :visible.sync="dialogUpdate" width="500px" center>
      <el-form :model="user" label-position="left" label-width="80px" >
          <el-form-item label="用户名">
            <el-input v-model="user.username" disabled/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="user.password"/>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button type="primary" @click="updateUser">修改密码</el-button>
          <el-button @click="dialogUpdate = false">取 消</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data(){
    return{
      listUser:[],
      input:'',
      user:{},
      form:{
        username:'',
        password:'',
      },
      dialogInsert:false,   //控制显示增加界面
      dialogUpdate:false,   //控制显示修改界面
      disable:[
        {value:'0',label:"未禁用"},
        {value:'1',label:"已禁用"},
      ],
      currentPage: 1,       //初始页
      pagesize: 8,          // 每页的数据
      total: 0,             //总数
    }
  },
  methods:{
    //修改每页的数据
    handleSizeChange(size) {
      this.pagesize = size;
    },
    // 修改页数
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },
    selectAll(){
      var _this = this
      _this.$axios({
        url:"user/selectAll",
        method:"get",
      }).then((resp)=>{
        if(resp.data!==''){
          _this.listUser=resp.data
        }
      }).catch((err)=>{
        _this.$message.error(err)
      })
    },
    findUser(){
      var _this = this
      _this.$axios({
        url:"user/findUser",
        method:"post",
        data:(_this.input),
      }).then((resp)=>{
        if(resp.data!==''){
          _this.listUser=resp.data
        }
      }).catch((err)=>{
        _this.$message.error(err)
      })
    },
    insertUser(){
      var _this = this
      _this.$axios({
        url:"user/insert",
        method:"post",
        data:(_this.form),
      }).then((resp)=>{
        if(resp.data===true){
          _this.selectAll()
          _this.form={}
          _this.dialogInsert=false
          _this.$message.success("添加成功")
        }else{
          _this.$message.success("用户已存在")
        }
      }).catch((err)=>{
        _this.$message.error(err)
      })
    },
    updateUser(){
      var _this = this
      _this.$axios({
        url:"user/update",
        method:"post",
        data:(_this.user),
      }).then((resp)=>{
        if(resp.data===true){
          _this.selectAll()
          _this.dialogUpdate=false
          _this.$message.success("修改成功")
        }
      }).catch((err)=>{
        _this.$message.error(err)
      })
    },
    banUser(user){
      var _this = this
      _this.$axios({
        url:"user/ban",
        method:"post",
        data:(user),
      }).then((resp)=>{
        if(resp.data===true){
          _this.selectAll()
        }
      }).catch((err)=>{
        _this.$message.error(err)
      })
    },
  },
  watch:{
    input(newInput,oldInput){
      if(newInput!==''){
        this.findUser()
      }else{
        this.selectAll()
      }
    }
  },
  created:function(){
    this.selectAll()
  }
}
</script>

<style>

</style>