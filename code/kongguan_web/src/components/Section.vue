<template>
  <div>
    <el-row>
        <el-col :xs="12" :sm="12" :lg="{span: '4'}" v-for="item in resultVal">
          <div class="grid-content bg-purple">
            <div class="top_div_css"></div>
            <div class="centen">
              <div class="to_titls">{{item.planSectorName}}扇区</div>
              <div  class="to_titls_two">{{item.count}}架</div>
            </div>
          </div>
        </el-col>
    </el-row>
  </div>
</template>
<style >
  /*.el-col-lg-4-8 {*/
  /*  width: 20%;*/
  /*}*/
  .centen{
 width: 100%;
 }
  .to_titls{
 margin-top: 15px;
 text-align: center;
 font-size: 20px;
 color: #676767;
 }
  .to_titls_two{
 margin-top: 15px;
 text-align: center;
 font-size: 25px;
 color: #307be3;
 }
  .top_div_css{
    width: 100%;
    height: 10px;
    background-color: #4eb9f8;
    border-top-left-radius: 2em;
    border-top-right-radius: 2em;
  }
.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
 border-radius: 2em;
 }
  .bg-purple-dark {
 background: #99a9bf;
 }
  .bg-purple {
 background: #ffffff;
 }
  .bg-purple-light {
 background: #e5e9f2;
 }
  
  .grid-content {
    margin-top: 13px;
    border-radius: 1em;
    min-height: 100px;
    margin-left: 15px;
    margin-right: 15px;
    /*width: 200px;*/
    background-color: #ffffff;
    border: 1px solid #ebedf2;
    box-shadow: 3px 3px 3px 3px #ebedf2;
  }
  .row-bg {
 padding: 10px 0;
 background-color: #f9fafc;
 }
</style>
<script>
    import echarts from "echarts";
    import {getSectionVal } from "../api/user/api.js";
    export default {
      name: "Home",
      data() {
        return {
          chart: null,
          OneSection:"G",
          TwoSection:"K",
          TwoSection:"E",
          TwoSection:"P",
          resultVal:{}
        };
      },
      mounted() {
        this.loadData();
      },
      methods: {
        initChart() {},
        loadData(){
          getSectionVal().then(data => {
            if (data.isSuccess) {
              var res  = data.result;
              this.resultVal =res;
            } else {
              this.$message.error("数据获取失败");
            }
          });
        }
      }
    };
</script>