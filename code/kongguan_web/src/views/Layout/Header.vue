<template>
  <header class="common-head">
    <div class="fl">
</div>
    <div class="nav">
      <el-menu router :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
               background-color="#095392"
 text-color="#fff"
 active-text-color="#fff">
        <el-menu-item index="/home">首页</el-menu-item>
        <el-menu-item index="/map">航空实时监控</el-menu-item>
      </el-menu>
    </div>
    <div class="fr" style="margin-top: 13px">
      <el-dropdown @command="handleCommand">
        <span style="color: #ffffff">
          <i class="el-icon-user-solid"></i>
          <i class="el-icon-caret-bottom el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item icon="el-icon-close" command="LOGOUT">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </header>
</template>
<script>
  export default {
    name: "Header",
    data() {
      return {
        userName: '',
        activeIndex: '/home',
      };
    },
    mounted() {
      this.userName = localStorage.getItem('userName')
    },
    methods: {
      //菜单=点击触发的方法
      handleCommand: function (cmd) {
        if (cmd == 'LOGOUT') {
          this.removeStoryValue();
          this.$router.push("/login");
        } else if (cmd == 'HOME') {
          this.$router.push("/");
        } else {
          console.log("to caseList");
          this.$router.push("/caseList");
        }
      },
      //移除认证信息
      removeStoryValue() {
        localStorage.removeItem('userName')
        localStorage.removeItem('Authorization')
      },
      handleSelect(key, keyPath) {
        this.activeIndex=key;
      }
    }
  };
</script>
<style scoped>
  .common-head {
    height: 60px;
    background: #095392;
    background-size: cover;
    left: 0px;
  }
  .nav {
    width: 100%;
    height: 60px;
    position: fixed;
    justify-content: center;
    display: flex;
  }
  .el-menu-item.is-active {
    background-color: #436c95 !important;
    color: #fff;
  }
</style>