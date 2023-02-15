<template>
  <div>
        <div class="wrap">
            <div class="container">
                <h1>Login</h1> 
                <el-input placeholder="Username" v-model="user.username"/>
                <el-input placeholder="Password" v-model="user.password" show-password/>
                <span class="type">
                    <el-radio v-model="type" label="用户">用户</el-radio>
                    <el-radio v-model="type" label="管理员">管理员</el-radio>
                </span>
                <el-button round @click="login">登录</el-button>
            </div>
            <!-- 动态气泡 -->
            <ul>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            user:{
                username:'',
                password:'',    
            },
            type:'用户',
        }
    },
    methods:{
        login:function(){
            var _this = this
            if(_this.type==='管理员'){
                _this.$axios({
                    url:"admin/login",
                    method:"post",
                    data:(_this.user)
                }).then(function(resp){
                    if(resp.data!=''){
                        _this.$store.state.login.isLogin = true
                        _this.$store.state.login.isAdmin=true
                        _this.$store.state.login.username=resp.data

                        window.sessionStorage.setItem('isLogin',true)
                        window.sessionStorage.setItem('isAdmin',true)
                        window.sessionStorage.setItem('username',resp.data)

                        _this.$message.success('管理员登录成功')
                        _this.$router.push('/main')
                    }else{
                        _this.$message.error('账号或密码错误')
                    }
                }).catch((err) => {
                    alert(err.message)
                });
            }
            if(_this.type==='用户'){
                _this.$axios({
                    url:"user/login",
                    method:"post",
                    data:(_this.user)
                }).then(function(resp){
                    if(resp.data!==''){
                        _this.$store.state.login.isLogin = true
                        _this.$store.state.login.isAdmin = false
                        _this.$store.state.login.username=resp.data

                        window.sessionStorage.setItem('isLogin',true)
                        window.sessionStorage.setItem('isAdmin',false)
                        window.sessionStorage.setItem('username',resp.data)

                        _this.$message.success("用户登录成功")
                        _this.$router.push("/main")
                    }else{
                        _this.$message.error('账号或密码错误')
                    }
                }).catch((err) => {
                    alert(err.message)
                });
            }
        }
    },
}
</script>
<style scoped>
@import url('../assets/login.css');

.container {
    position: relative;
    width: 60%;
    margin: 0 auto;
}
.container h1 {
    text-align: center;
    color: #FFFFFF;
    font-weight: 500;
    margin-bottom: 50px;
}
.el-input{
    display: block;
    width: 300px;
    margin-left: -150px;
    margin-bottom: 30px;
    left: 50%;
}
.el-input:focus-within{
    width: 400px;
    margin-left: -200px;
}
.type{
    display: block;
    text-align: center;
    margin-bottom: 30px;
}
.el-button{
    position: absolute;
    width: 300px;
    left: 50%;
    margin-left: -150px;
}
</style> 