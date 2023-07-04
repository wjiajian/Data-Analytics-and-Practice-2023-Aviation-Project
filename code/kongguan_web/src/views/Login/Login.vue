<template>
  <div class="login">
    <div class="top_logo">
    </div>
    <!-- 登录框区域 -->
    <div class="form_box">
      <div style="color: #f0f0f0;">大数据航空案例</div>
      <el-form style="margin-top: 60px" :model="loginForm" status-icon :rules="rules" ref="loginForm">
        <el-row type="flex" justify="left" :gutter="20">
          <el-col :span="3" align="center">
            <img src="../../assets/images/user-icon.png" style="margin-top: 3px"/>
          </el-col>
          <el-col :span="21" align="center">
            <el-form-item prop="account">
              <el-input  class="el-input__inner1" v-model="loginForm.account" placeholder="请输入用户名" maxlength="20"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex">
            <div class="tableTitle"/>
        </el-row>
        <el-row type="flex" justify="left" style="margin-top: 50px" :gutter="20">
          <el-col :span="3" align="center">
            <img src="../../assets/images/pwd-icon.png" style="margin-top: 3px"/>
          </el-col>
          <el-col :span="21">
            <el-form-item prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" maxlength="16"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex">
          <div class="tableTitle"/>
        </el-row>
        <el-form-item align="center" style="margin-top: 100px">
          <el-button class="el-button1" type="primary;" @click="submitForm('loginForm')">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 底部区域 -->
  </div>
</template>
 <script>
  import {mapMutations} from 'vuex';
  import {login} from "@/api/login/login";
  export default {
    data() {
      return {
        loginForm: {
          account: "",
          password: "",
        },
        otherQuery: {},
        rules: {
          account: [
            {required: true, message: "请输入登陆账号", trigger: "blur"}
          ],
          password: [
            {required: true, message: "请输入登陆密码", trigger: "blur"}
          ]
        }
      };
    },
    watch: {
      $route: {
        handler: function (route) {
          const query = route.query
          if (query) {
            this.redirect = query.redirect
            this.otherQuery = this.getOtherQuery(query)
          }
        },
        immediate: true
      }
    },
        methods: {
      ...mapMutations(['changeLogin']),
      submitForm(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            login(this.loginForm).then(data => {
              if (data.successful) {
                console.log("登录成功");
                // 将用户token保存到vuex中
                // this.changeLogin({Authorization: data.result.token});
                //localStorage.setItem('account', data.result.name)
                if (data.resultValue.Authorization != null) {
                  localStorage.setItem('Authorization', data.resultValue.Authorization);
                }
                if (data.resultValue.userAuth != null) {
                  localStorage.setItem('userAuth', data.resultValue.userAuth);
                }
                this.$router.push({path: this.redirect || '/', query: this.otherQuery});
                console.log("跳转");
              } else {
                console.log("登录失败");
                this.$message.error("登录失败");
              }
            });
          } else {
            return false;
          }
        });
      },
      //获取请求的地址，登录后，直接跳转到请求的地址
      getOtherQuery(query) {
        return Object.keys(query).reduce((acc, cur) => {
          if (cur !== 'redirect') {
            acc[cur] = query[cur]
          }
          return acc
        }, {})
      }
    }
  };
</script>
<style scoped>
  .login {
    background: url('../../assets/images/loginBg.png') no-repeat;
    background-size: cover;
    height: 100%;
  }
  .top_logo {
    height: 130px;
  }
  .form_box {
    text-align: center;
    width: 550px;
    margin: 0 auto;
  }
  .login-footer {
    width: 100%;
    text-align: center;
    color: #ffffff;
    position:fixed;
    bottom:0;
    margin-bottom: 64px;
    size: 23px;
    padding-top: 100px;
  }
  .el-button1{
    background: #0376bf;
    border-color: #0376bf;
    width: 100%;
    color: #f0f0f0;
    
    height: 62px;
  }
  .tableTitle {
    margin: 0 auto;
    margin-top: 10px;
    width: 550px;
    height: 1px;
    background-color: #d4d4d4;
  }
</style>
<style>
  .el-form-item {
    margin-bottom: 1px;
  }
  .el-form-item__error{
    
    margin-left: 15px;
    margin-top: 20px;
  }
  .el-input__inner {
    background: transparent;
    border: 0;
    color: #f0f0f0;
    
  }
</style>