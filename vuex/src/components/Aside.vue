<template>
  <div class="aside">
    <el-menu  style="min-height: calc(100vh - 60px)"
        :default-active="this.$router.path"
        class="el-menu-vertical-demo"
        background-color="#a0a0a0"
        text-color="#ffffff"
        active-text-color="#ffd04b"
        router>
        <el-menu-item-group>
          <el-menu-item index="/manageOrder"><i class="el-icon-menu"></i>订单管理</el-menu-item>
          <el-menu-item index="/manageCustomer"><i class="el-icon-menu"></i>客户管理</el-menu-item>
          <el-menu-item v-if="isAdmin" index="/manageUser"><i class="el-icon-menu"></i>用户管理</el-menu-item>
          <el-menu-item index="/manageTotal"><i class="el-icon-menu"></i>统计管理</el-menu-item>
        </el-menu-item-group>
    </el-menu>
  </div>
</template>

<script>
export default {
  data(){
    return{
      isAdmin:this.$store.state.login.isAdmin
    }
  },
}
</script>
