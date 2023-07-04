<template>
  <div class="home">
    <div id="barAirChart" class="chart" style="margin-left: 30px;height: 645px"/>
  </div>
</template>
<script>
  import {getAirPortCount} from '../../api/chartdata/chartdata'

  export default {
    name: "AirPortCountChart",
    data() {
      return {
        chartOption: {},
        myChart: {},
      }
    },
    mounted() {
      this.initChart();
      this.loadData();
    },
     methods: {
      initChart() {
        this.myChart = this.$echarts.init(document.getElementById("barAirChart"));
        this.chartOption = {
          title: {
            text: "机场当前负荷统计",
            subtext: "",
            top: 18,
            left: 26,
            textStyle: {
              color: "#000000"
            },
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
              type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },

          grid: {
            left: '3%',
            right: '8%',
            bottom: '3%',
            top:'15%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              axisTick: {
                alignWithLabel: true
              },
              axisLabel: {
                interval: 0,
                rotate: 45
              }

            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [

            {
              name: '起飞',
              type: 'bar',
              stack: '机场负荷',
              barWidth: 23,
              emphasis: {
                focus: 'series'
              },
            },
            {
              name: '降落',
              type: 'bar',
              stack: '机场负荷',
              barWidth: 23,
              emphasis: {
                focus: 'series'
              },
            }
          ]

        }

        this.myChart.setOption(this.chartOption);
      },
      loadData() {
        getAirPortCount().then(data => {
          if (data.isSuccess) {
            this.formatData(data.result);
          } else {
            this.$message.error("数据获取失败");
          }
        })
      },
      formatData(data) {

        let xAixArr = [];
        let adepArr = [];
        let adesArr = [];

        for (let i = 0; i < data.length; i++) {
          xAixArr.push(data[i].airCname);
          adepArr.push(data[i].adepCount);
          adesArr.push(data[i].adesCount);

        }

        this.chartOption.xAxis[0].data = xAixArr;
        this.chartOption.series[0].data = adepArr;
        this.chartOption.series[1].data = adesArr;

        this.refreshChart();

      },
      refreshChart() {
        this.myChart.setOption(this.chartOption);
      }


    }
  }
</script>
<style scoped>
  .home {
    height: 700px;
    overflow: auto;
    background-color: #ffffff;
    border: 1px solid #ebedf2;
    border-radius: 10px;
    box-shadow: 3px 3px 3px 3px #ebedf2;
  }


  .home::-webkit-scrollbar {
    display: none;
  }

  .chart {
    height: 700px;
  }
</style>