<template>
  <div class="index">
    
    <el-row :gutter="30" v-show="isShow('/flight/airline')">
      <el-col :span=24 align="center">
        <AirLine/>
      </el-col>
    </el-row> 
    <el-row :gutter="30"  v-show="isShow('/flight/section')">
      <el-col :span="24" align="center">
        <Section/>
      </el-col>
    </el-row>
    <el-row :gutter="30" v-show="isShow('/flight/delay')">
      <el-col :span="16" align="center">
        <Delay/>
      </el-col>
      <el-col :span="8" align="center">
        <YearWarningChart/>
      </el-col>
    </el-row>
    <el-row :gutter="30" v-show="isShow('/section/warning')">
      <el-col :span="12" align="center">
        <AirPortCountChart/>
      </el-col>
      <el-col :span="12" align="center">
        <WarnStatistice/>
      </el-col>
    </el-row>
    <el-row :gutter="30" v-show="isShow('/section/detail')">
      <el-col :span="16" align="center">
        <SectorFlightChart/>
      </el-col>
      <el-col :span="8" align="center">
        <SectorCallChart/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import AirLine from "../../components/AirLine";
  import Section from "../../components/Section";
  import Delay from "../../components/Delay";
  import WarnStatistice from "../../components/WarnStatistice";
  import SectorFlightChart from "../../components/echart/SectorFlightChart";
  import SectorCallChart from "../../components/echart/SectorCallChart";
  import YearWarningChart from "../../components/echart/YearWarningChart";
  import AirPortCountChart from "../../components/echart/AirPortCountChart";
   
  import {hasPermission} from "../../utils/permission";
  export default {
    data() {
      return {
      };
    },
    mounted() {
    },
    components: {AirLine,Section,Delay,WarnStatistice,SectorFlightChart,SectorCallChart,YearWarningChart,AirPortCountChart},
    methods: {
      isShow(permission){
        return hasPermission(permission);   
      }
    }
  };
</script>

<style scoped>
  .index {
    height: 100%;
    overflow: auto;
    padding-left: 44px;
    padding-right: 44px
  }
  .index::-webkit-scrollbar {
    display: none;
  }

  .caseClass {
    background: url('../../assets/images/index-bg.png') no-repeat;
    background-size: cover;
    margin-top: 20px;
    height: 284px;
  }

  .el-button {
    background: transparent;
  }

</style>