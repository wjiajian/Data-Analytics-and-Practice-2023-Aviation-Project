<template xmlns:el-col="http://www.w3.org/1999/html">
  <div class="home">

    <div id="barFlightChart" class="chart" />

  </div>
</template>

<script>
  import {findATCTime} from "../../api/chartdata/chartdata";

  export default {
    name: "SectorFlightChart",
    data() {
      return {
        sectorData: [],
        barChartData: [],
        barChartAxis: [],
        sectorCharData: [],
        sectorCharOneData: [],
        sectorChartAxis: ['K', 'S', 'E', 'P', 'G'],
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
        this.myChart = this.$echarts.init(document.getElementById("barFlightChart"));
        this.chartOption = {
          baseOption: {
            timeline: {
              axisType: 'category',
              // realtime: false,
              // loop: false,
              autoPlay: true,
              // currentIndex: 2,
              playInterval: 1000,
              // controlStyle: {
              //     position: 'left'
              // },
              lineStyle: {color: "#bcc9d7", width: 1},
              controlStyle: {showPlayBtn: !1, showPrevBtn: !1, showNextBtn: !1},
              checkpointStyle: {color: "#f19326", symbol: "circle", symbolSize: 10, borderWidth: 0},
              itemStyle: {normal: {color: "#419ae7"}},
            },
            title: {
              text: "扇区架次数动态循环展示",
              subtext: "",
              top:18,
              left: 26,
              textStyle: {
                color: "#000000"
              },            
            },
            tooltip: {
              trigger: "item",
              padding: 10,
              backgroundColor: "#222",
              borderColor: "#777",
              borderWidth: 1,

            },
            angleAxis: {
              type: "category",
              axisTick: {show: !1},
              axisLine: {show: !0, lineStyle: {color: "#d2dde7"}},
              axisLabel: {color: "#d2dde7"},
              data: ["G区", "K区", "E区", "P区", "S区"],
              z: 10
            },
            radiusAxis: {
              min: 0,
              axisLine: {show: !1, lineStyle: {color: "#000", opacity: .3}},
              axisLabel: {show: !1, color: "#000"},
              axisTick: {show: !1},
              splitLine: {lineStyle: {color: "#d2dde7"}},
              splitArea: {show: !1, areaStyle: {color: "rgb(1, 10, 63)", opacity: .8}}
            },
            grid: {left: "10%", right: "50%", top: "10%", bottom: "9%", containLabel: !1},
            polar: {center: ["75%", "45%"], radius: "50%"},
            xAxis: [{
              type: 'value',
              boundaryGap: [0, 0.01],
              splitLine: {
                show: false
              },
              show: false,
              axisLine: {   //横轴样式
                lineStyle: {},
              },
              position:'top'

            }],
            yAxis: [{
              type: 'category',
              data: this.barChartAxis,
              inverse:true,
              axisLine: {   //纵轴样式
                lineStyle: {
                  color: '#73777d'
                }
              },
              axisLabel: {
                rotate: -45
              }
            }],
            series: [{
              type: "bar",
              coordinateSystem: "polar",
              name: "扇区",
              center: ["75%", "45%"],
              stack: "a",
              itemStyle: {
                normal: {
                  color: function (t) {
                    return ["#51b8f9", "#7d92ff", "#5fccc3", "#f19326", "#f258b6"][t.dataIndex]
                  }, label: {show: !0, position: "top", formatter: "{b}\n{c}"}
                }
              }
            }, {
              name: "本日架次数",
              type: "bar",
              barWidth: 8,
              radius: 90,
              avoidLabelOverlap: !1,
              label: {
                normal: {show: !1, position: "outside", formatter: "{c}"},
                emphasis: {show: !0, textStyle: {fontSize: "12", fontWeight: "normal"}}
              },
              labelLine: {normal: {show: !1}},
              itemStyle: {normal: {color: "#51b8f9"}, emphasis: {color: "#f19326"}}
            }]
          }
        }
        this.myChart.setOption(this.chartOption);
      },
      //加载数据
      loadData() {
        findATCTime().then(data => {
          if (data.isSuccess) {
            this.formatData(data.result);
          } else {
            this.$message.error("数据获取失败");
          }
        });
      },
      //整理数据
      formatData(data) {
        let timeLineData = [];
        let barDataArr = [];
        let pieDataArr = [];
        let optionArr = [];
        for (let i = 0; i < data.length; i++) {
          let dayItemData = data[i];
          timeLineData.push(i + 1);

          let dayFlightSum = 0;
          let dayFlightDetail = [];
          for (let j = 0; j < dayItemData.length; j++) {
            dayFlightDetail.push(dayItemData[j][this.sectorChartAxis[j]]);
            dayFlightSum = dayFlightSum + parseInt(dayItemData[j][this.sectorChartAxis[j]]);
          }
          pieDataArr.push(dayFlightDetail);
          barDataArr.push(dayFlightSum);


        }
        //拼装 echart专用的options对象
        for (let i = 0; i < timeLineData.length; i++) {
          optionArr.push({
            series: [{data: pieDataArr[i]}, {data: barDataArr}],
            yAxis: [{data: timeLineData, nameTextStyle: {fontSize: 4, align: "center"},axisLabel:{formatter:'第{value}天'}}]
          })
        }
        this.chartOption.baseOption.timeline.data = timeLineData;
        this.chartOption.options = optionArr;
        this.refreshChart();
      }
      ,
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
    height: 680px;
  }
</style>