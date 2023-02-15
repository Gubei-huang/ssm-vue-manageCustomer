<template>
  <div style="width: 80%;margin-left:10%;margin-right:10%">

    <div style="margin-top:20px">
      <el-button type="primary" @click="dialogInsert = true" style="float:right">新增客户</el-button>
      <el-select v-model="type"  style="width:100px;margin-right:5px">
        <el-option
          v-for="item in types"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>

      <el-input placeholder="请输入客户名" v-model="input" style="width: 200px;"/>
    </div>

    <el-table :data="listCustomer.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
     border height="calc(100vh - 285px)" style="margin-top:20px" >
      <el-table-column style="height:100px" prop="username" label="客户名"/>
      <el-table-column prop="phone" label="手机号"/>
      <el-table-column prop="type" label="类型"/>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button size="mini" @click="customer=scope.row,dialogUpdate=true"> 修改 </el-button>
          <el-button type="danger" size="mini" @click="customer=scope.row,dialogDelete=true"> 删除 </el-button>
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
      :total="listCustomer.length">
      
    </el-pagination>
    
    <el-dialog title="增加客户" :visible.sync="dialogInsert" width="500px" center>
      <el-form :model="form" label-position="left" label-width="80px" >
          <el-form-item label="用户名">
            <el-input v-model="form.username"/>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="form.phone"/>
          </el-form-item>
          <el-form-item label="付费类型">
            <el-select v-model="form.type" placeholder="请选择类型">
              <el-option label="按年" value="year"></el-option>
              <el-option label="按月" value="month"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button type="primary" @click="insertCustomer">添加客户</el-button>
          <el-button @click="dialogInsert = false">取 消</el-button>
        </div>
    </el-dialog>

    <!-- 删除提示界面 -->
    <el-dialog title="提示" :visible.sync="dialogDelete" width="30%" append-to-body>
      <span>确定要删除用户名为：{{customer.username}}</span>
      <span slot="footer">
        <el-button type="primary" @click="deleteCustomer(customer.username)">确 定</el-button>
        <el-button @click="dialogDelete = false">取 消</el-button>
      </span>
    </el-dialog>

    <!-- 修改客户界面 -->
    <el-dialog title="修改客户" :visible.sync="dialogUpdate" width="500px" center>
      <el-form :model="customer" label-position="left" label-width="80px" >
          <el-form-item label="客户名">
            <el-input v-model="customer.username" disabled/>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="customer.phone"/>
          </el-form-item>
          <el-form-item label="类型">
            <el-select v-model="customer.type"  style="width:100px;margin-right:5px">
              <el-option
                v-for="item in updateTypes"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button type="primary" @click="updateCustomer">修改客户</el-button>
          <el-button @click="dialogUpdate = false">取 消</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data(){
    return{
      listCustomer:[],
      customer:{},
      input:'',
      form:{
        username:'',
        phone:'',
        type:'',
      },
      types:[
        {value:'',label:"所有"},
        {value:'year',label:"按年"},
        {value:'month',label:"按月"},
      ],
      type:'',
      dialogInsert:false,   //控制显示增加界面
      dialogDelete:false,   //控制显示删除界面
      dialogUpdate:false,   //控制显示修改界面
      updateTypes:[
        {value:'year',label:"按年"},
        {value:'month',label:"按月"},
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
        url:"customer/selectAll",
        method:"get"
      }).then(function(resp){
        if(resp.data!==''){
          _this.listCustomer=resp.data
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
    findCustomer(){
      var _this = this
      _this.$axios({
        url:"customer/findCustomer",
        method:"post",
        data:({username:_this.input,type:_this.type})
      }).then(function(resp){
        if(resp.data!==''){
          _this.listCustomer=resp.data
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
    insertCustomer(){
      var _this = this
      if(_this.form.username===""){
        _this.$message.error("客户名不能为空")
        return false
      }
      _this.$axios({
        url:"customer/insert",
        method:"post",
        data:(_this.form)
      }).then((resp)=>{
        if(resp.data===true){
          _this.form={}
          _this.selectAll()
          _this.dialogInsert=false
          _this.$message.success("增加成功")
        }else{
          _this.$message.error("客户已存在")
        }
      }).catch((err)=>{
        _this.$message.error(err)
      })
    },
    deleteCustomer(username){
      var _this = this
      _this.$axios({
        url:"customer/delete",
        method:"post",
        data:(username)
      }).then((resp)=>{
        if(resp.data===true){
          _this.dialogDelete=false
          _this.$message.success("删除成功")
          _this.selectAll()
        }
      }).catch((err)=>{
        _this.$message.error(err.data)
      })
    },
    updateCustomer(){
      var _this = this
      _this.$axios({
        url:"customer/update",
        method:"post",
        data:(_this.customer)
      }).then((resp)=>{
        if(resp.data===true){
          _this.dialogUpdate=false
          _this.$message.success("修改成功")
          _this.selectAll()
        }
      }).catch((err)=>{
        _this.$message.error(err.data)
      })
    },
  },
  watch:{
    type(){
      this.findCustomer()
    },
    input(){
      this.findCustomer()
    },
  },
  created:function(){
    this.selectAll()
  }
}
</script>
