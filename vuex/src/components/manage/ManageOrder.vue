<template>
  <div style="width: 90%;margin-left:5%;margin-right:5%">

    <div style="margin-top:20px">
      <el-select v-model="type"  style="width:140px;margin-right:5px">
        <el-option
          v-for="item in types"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-input placeholder="请输入数据" v-model="input" style="width: 200px;"/>
    </div>

    <el-table :data="listOrder.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
     border height="calc(100vh - 300px)" style="margin-top:20px" >
      <el-table-column prop="num" label="编号" width="120px"/>
      <el-table-column prop="paymentUsername" label="缴费人"/>
      <el-table-column prop="paymentDate" label="缴费日期" width="140px"/>
      <el-table-column prop="expireDate" label="到期日期" width="140px"/>
      <el-table-column prop="cost" label="费用" width="100px"/>
      <el-table-column prop="type" label="类型" width="100px"/>
      <el-table-column prop="adminUsername" label="经办人"/>
      <el-table-column fixed="right" label="操作" width="250px">
        <template slot-scope="scope">
          <el-button size="mini" @click="renew(scope.row.num)">续费</el-button>
          <el-button size="mini" type="info" @click="order=scope.row,dialogUpdate=true">修改</el-button>
          <el-button type="danger" size="mini" @click="order=scope.row,dialogDelete=true">删除</el-button>
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
      :total="listOrder.length">
      
    </el-pagination>

    <el-dialog title="提示" :visible.sync="dialogDelete" width="30%" append-to-body>
      <span>确定要删除编号为：{{order.num}}</span>
      <span slot="footer">
        <el-button type="primary" @click="deleteOrder()">确 定</el-button>
        <el-button @click="dialogDelete = false">取 消</el-button>
      </span>
    </el-dialog>
    
    <!-- 修改订单界面 -->
    <el-dialog title="修改订单" :visible.sync="dialogUpdate" width="500px" center>
      <el-form :model="order" label-position="left" label-width="80px" >
          <el-form-item label="编号">
            <el-input v-model="order.num" disabled/>
          </el-form-item>
          <el-form-item label="付款人">
            <el-input v-model="order.paymentUsername"/>
          </el-form-item>
          <el-form-item label="付款日期">
            <el-date-picker
              v-model="order.paymentDate"
              type="date"
              placeholder="选择日期"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="到期日期">
            <el-date-picker
              v-model="order.expireDate"
              type="date"
              placeholder="选择日期"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="经办人">
            <el-input v-model="order.adminUsername"/>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button type="primary" @click="updateOrder">修改订单</el-button>
          <el-button @click="dialogUpdate = false">取 消</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data(){
    return{
      listOrder:[],
      order:{},
      input:'',
      types:[
        {value:'num',label:"按编号查询"},
        {value:'customer',label:"按付款人查询"},
        {value:'admin',label:"按经办人查询"},
      ],
      type:'num',
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
        url:"order/selectAll",
        method:"get"
      }).then(function(resp){
        if(resp.data!==''){
          _this.listOrder=resp.data
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
    findByNum(){
      var _this = this
      _this.$axios({
        url:"order/findOrderByNum",
        method:"post",
        data:(_this.input)
      }).then(function(resp){
        if(resp.data!==''){
          _this.listOrder=resp.data
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
    findByCustomer(){
      var _this = this
      _this.$axios({
        url:"order/findOrderByCustomer",
        method:"post",
        data:(_this.input)
      }).then(function(resp){
        if(resp.data!==''){
          _this.listOrder=resp.data
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
    findByAdmin(){
      var _this = this
      _this.$axios({
        url:"order/findOrderByAdmin",
        method:"post",
        data:(_this.input)
      }).then(function(resp){
        if(resp.data!==''){
          _this.listOrder=resp.data
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
    deleteOrder(){
      var _this = this
      _this.$axios({
        url:"order/delete",
        method:"post",
        data:(_this.order.num)
      }).then(function(resp){
        if(resp.data===true){
          _this.dialogDelete=false
          _this.selectAll()
          _this.$message.success("删除成功")
        }else{
          _this.$message.error("删除失败")
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
    updateOrder(){
      var _this = this
      _this.$axios({
        url:"order/update",
        method:"post",
        data:(_this.order)
      }).then(function(resp){
        if(resp.data===true){
          _this.dialogUpdate=false
          _this.selectAll()
          _this.$message.success("修改成功")
        }else{
          _this.$message.error("修改失败")
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
    renew(num){
      var _this = this
      _this.$axios({
        url:"order/renew",
        method:"post",
        data:({num:num,admin:window.sessionStorage.getItem("username")})
      }).then(function(resp){
        if(resp.data===true){
          _this.dialogUpdate=false
          _this.selectAll()
          _this.$message.success("续费成功")
        }else{
          _this.$message.error("续费失败")
        }
      }).catch(function(err){
        _this.$message.error(err.data)
      })
    },
  },
  watch:{
    input(n,o){
      if(n===''){
        this.selectAll()
      }else{
        if(this.type==="num"){
          this.findByNum()
        }
        if(this.type==="customer"){
          this.findByCustomer()
        }
        if(this.type==="admin"){
          this.findByAdmin()
        }
      }
      
    },
    type(n,o){
      if(this.input===''){
        this.selectAll()
      }else{
        if(n==="num"){
          this.findByNum()
        }
        if(n==="customer"){
          this.findByCustomer()
        }
        if(n==="admin"){
          this.findByAdmin()
        }
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