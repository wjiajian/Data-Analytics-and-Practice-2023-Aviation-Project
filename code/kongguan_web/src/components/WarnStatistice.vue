<template>
    <div>
       <div class="home">
         <div id="map-chart-05"></div>
       </div>
     </div>
</template>

<script>
  import echarts from "echarts";
  import {getAnnualWarningAreaStatistics} from "../api/user/api.js";
  export default {
    name: "Home",
    data() {
      return {
        chart: null,
        geoCoordMap: {},
        charVal:[],
        charValData:[]
      };
    },
    mounted() {
      this.loadData();
    },
    methods: {
      initChart() {
        const option = {
              title: {
                  text: '年度告警区域统计',
                top: 18,
                left: 26,
              },
              tooltip: {
                  trigger: 'axis',
                  axisPointer: {
                      type: 'shadow'
                  }
              },
              legend: {
                  data: ['2011年']
              },
              grid: {
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true
              },
              xAxis: {
                  type: 'value',
                  boundaryGap: [0, 0.01],
                  show:false,
              },
              yAxis: {
                  type: 'category',
                  data: this.charVal,
                  show:true,
                  axisLabel:{
                    color:'#bdcad3'
                  },
                  axisTick:{
                    show:false
                  },
                  axisLine:{
                    lineStyle:{
                      color:'#bdcad3'
                    },
                    show:true
                  }
              },
              series: [
                  {
                      name: '',
                      type: 'bar',
                      itemStyle:{
                        normal:{
                          color:'#7d93fe'
                        }
                      },
                      barGap:35,
                      barWidth:20,
                      data: this.charValData
                  }
              ]

        };
        this.chart = echarts.init(document.getElementById("map-chart-05"));
        this.chart.setOption(option);
      },
      loadData(){
        getAnnualWarningAreaStatistics().then(data => {

          if (data.isSuccess) {
            var res  = data.result;
            for(var i =0;i<res.length;i++){
              this.charVal[i] = res[i]['gjSector'];
              this.charValData[i] = res[i]['gjCount'];
            }
            this.initChart();
          } else {
            this.$message.error("数据获取失败");
          }
        });
      }
    }
  };
</script>

<style>
  #map-chart-05 {
    height: 700px;
    margin: 0 auto;
    background-color: #ffffff;
    border: 1px solid #ebedf2;
    border-radius: 10px;
    box-shadow: 3px 3px 3px 3px #ebedf2;
  }
</style>