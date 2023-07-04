<template>
  <div class="main">
    <Header></Header>
    <div class="common-right">
      <router-view/>
    </div>
  </div>
</template>
<script>
import Header from "@/views/Layout/Header";
export default {
  components: {
    Header
  },
  data() {
    return {
      levelList:[]
    }
  },
  computed: {
    isMenuCollapsed:function(){
            return this.$store.getters['menu/isMenuCollapsed'];
        }
  },
  created() {
  },
  mounted() {
  },
  methods: {
  }
};
</script>
<style scoped>

.common-right{
  /*background: url('../../assets/images/loginBg.png') no-repeat;*/
  background-size: cover;
  height: 100%;
  width: 100%;
}
.content{
  background: rgba(255, 255, 255, 0.7);
  padding: 20px 35px;
  border-radius: 10px;
}

.main{
  height: 100%;
}
</style>