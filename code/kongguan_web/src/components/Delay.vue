<template>
  <div>
    <div class="home">
      <div id="map-chart-06"></div>
    </div>
  </div>
</template>

<script>
  import echarts from "echarts";
  import {getFindCompanyDelay} from "../api/user/api.js";

  export default {
    name: "Home",
    data() {
      return {
        chart: null,
        geoCoordMap: {},
        xData: [],        //x轴数值
        delayData: [],   //延误率
        sortieData: []   //架次

      };
    },
    mounted() {
      this.loadData();
    },
    methods: {
      initChart() {
        const option = {
          title: {
            text: '指挥航空公司架次与延误率占比',
            top: 18,
            left: 26,
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              crossStyle: {
                color: '#999'
              }
            }
          },
          grid: {
            left: '3%',
            right: '8%',
            bottom: '3%',
            top: '15%',
            containLabel: true
          },

          xAxis: [
            {
              type: 'category',
              data: this.xData,
              axisLabel: {
                color: '#bdcad3',
                interval: 0,
                rotate: 40
              },
              axisTick: {
                show: false
              },
              axisLine: {
                lineStyle: {
                  color: '#bdcad3'
                },
                show: false
              },
              axisPointer: {
                type: 'shadow'
              }
            }
          ],
          yAxis: [
            {
              type: 'value',
              name: '',
              min: 0,
              max: 120,
              interval: 12,
              axisLabel: {
                formatter: '{value}',
                color: '#bdcad3'
              },
              show: true,
              axisTick: {
                show: false
              },
              axisLine: {
                lineStyle: {
                  color: '#bdcad3'
                },
                show: false
              }
            },
            {
              type: 'value',
              name: '',
              min: 0,
              max: 100,
              interval: 10,
              axisLabel: {
                formatter: '{value} %'
              },
              show: true,
              axisTick: {
                show: false
              },
              axisLine: {
                lineStyle: {
                  color: '#bdcad3'
                },
                show: false
              }
            }
          ],
          series: [
            {
              name: '架次',
              type: 'bar',
              itemStyle: {
                normal: {
                  color: '#5fcec3'
                }
              },
              barWidth: 20,
              data: this.sortieData
            },

            {
              name: '延误率',
              type: 'line',
              itemStyle: {
                normal: {
                  color: '#f88f2f'
                }
              },
              yAxisIndex: 1,
              data: this.delayData
            }
          ]
        };
        this.chart = echarts.init(document.getElementById("map-chart-06"));
        this.chart.setOption(option);
      },
      loadData() {
        getFindCompanyDelay().then(data => {
          if (data.isSuccess) {
            var res = data.result;
            for (var i = 0; i < res.length; i++) {
              this.xData[i] = res[i]['companyName'];
              this.sortieData[i] = res[i]['count'];
              this.delayData[i] = res[i]['delayCount'];
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
  .home {
    height: 490px;
    margin: 0 auto;
    background-color: #ffffff;
    border: 1px solid #ebedf2;
    border-radius: 10px;
    box-shadow: 3px 3px 3px 3px #ebedf2;
  }

  #map-chart-06 {
    height: 490px;
    margin: 0 auto;

  }
</style>