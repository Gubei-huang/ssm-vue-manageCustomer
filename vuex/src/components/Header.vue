<template>
  <div>
    <el-header >
      <span class="title">后&nbsp;&nbsp;台&nbsp;&nbsp;管&nbsp;&nbsp;理</span>
      <span class="welcome">欢迎您：<b>{{username}}</b></span>
      <el-button type="info" round size="mini" @click="logout">退出登录</el-button>
    </el-header>
  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return {
      username:window.sessionStorage.getItem('username')
    }
  },
  methods:{
    logout(){
      var _this = this
      _this.$store.state.login={}
      window.sessionStorage.clear()
      _this.$router.push("/login")
    }
  }
}
</script>

<style scoped>
.el-header{
  position: relative;
  background-color: #0057b6;
  color: #333;
  line-height: 60px;
}
.title{
  position: absolute;
  color: #ffbcbc;
  font-size: 30px;
  left: 50%;
  margin-left: -81.63px;
}
.welcome{
  position: absolute;
  left: 40px;
  font-size: 20px;
  color: rgb(199, 251, 93);
}
.el-button{
  position: absolute;
  right: 40px;
  top: 20px;
}
</style>