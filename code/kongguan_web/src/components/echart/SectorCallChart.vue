<template>
  <div class="home">
    <div style="margin-left:17px;margin-top: 18px; text-align:left;"> <b>各扇区通话饱和度</b></div>
    <el-row>
      <el-col :span="12">
        <div id="G_GaugeChart" class="chart" style="margin-left: 30px"/>
      </el-col>
      <el-col :span="12">
        <div id="K_GaugeChart" class="chart" style="margin-right: 30px"/>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="E_GaugeChart" class="chart" style="margin-left: 30px"/>
      </el-col>
      <el-col :span="12">
        <div id="P_GaugeChart" class="chart" style="margin-right: 30px"/>
      </el-col>
    </el-row> 
    <el-row>
      <el-col :span="12">
        <div id="S_GaugeChart" class="chart" style="margin-left: 30px"/>
      </el-col>
      <el-col :span="12">
        <div id="kong_GaugeChart" class="chart" style="margin-right: 30px"/>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import {findCallSaturation} from "../../api/chartdata/chartdata"
  export default {
    name: "SectorCallChart",
    data() {
      return {
        data: [],
        e_SectorNum: 0,
        k_SectorNum: 0,
        s_SectorNum: 0,
        g_SectorNum: 0,
        p_SectorNum: 0,
        e_sec_chart: {},
        k_sec_chart: {},
        s_sec_chart: {},
        g_sec_chart: {},
        p_sec_chart: {},
      }
    },
    mounted() {
      this.drawSectorChart();
      this.loadData();
    },
    methods: {
      //加载数据
      loadData() {
        findCallSaturation().then(data => {
          if (data.isSuccess) {
            this.formatData(data.result);
          } else {
            this.$message.error("数据获取失败");
          }
        });
      },
          formatData(param) {
        let e_SectorNum = param.E
        let k_SectorNum = param.K
        let s_SectorNum = param.S
        let g_SectorNum = param.G
        let p_SectorNum = param.P

        let e_opt = this.initSectorOption({text: "E扇区", value: e_SectorNum})
        let k_opt = this.initSectorOption({text: "K扇区", value: k_SectorNum})
        let s_opt = this.initSectorOption({text: "S扇区", value: s_SectorNum})
        let g_opt = this.initSectorOption({text: "G扇区", value: g_SectorNum})
        let p_opt = this.initSectorOption({text: "P扇区", value: p_SectorNum})

        this.e_sec_chart.setOption(e_opt);
        this.k_sec_chart.setOption(k_opt);
        this.s_sec_chart.setOption(s_opt);
        this.g_sec_chart.setOption(g_opt);
        this.p_sec_chart.setOption(p_opt);
      },
      drawSectorChart() {
        this.e_sec_chart = this.$echarts.init(document.getElementById("E_GaugeChart"));
        this.k_sec_chart = this.$echarts.init(document.getElementById("K_GaugeChart"));
        this.s_sec_chart = this.$echarts.init(document.getElementById("S_GaugeChart"));
        this.g_sec_chart = this.$echarts.init(document.getElementById("G_GaugeChart"));
        this.p_sec_chart = this.$echarts.init(document.getElementById("P_GaugeChart"));
      },
     initSectorOption(ChartObj) {
        let option = {

          title: [{
            text: ChartObj.text,
            top: '140',
            x: 'center',
            borderColor: '#fff',
            backgroundColor: '#337cde',
            borderWidth: 1,
            borderRadius: 15,
            padding: [7, 14],
            textStyle: {
              fontWeight: 'normal',
              fontSize: 12,
              color: '#fff'
            }
          }],
          angleAxis: {
            show: false,
            max: 100 * 360 / 270, //-45度到225度，二者偏移值是270度除360度
            type: 'value',
            startAngle: 225, //极坐标初始角度
            splitLine: {
              show: false
            }
          },
          barMaxWidth: 14, //圆环宽度
          radiusAxis: {
            show: false,
            type: 'category',
          },
          //圆环位置和大小
          polar: {
            center: ['50%', '50%'],
            radius: '150'
          },
          series: [{
            type: "gauge",
            // center: t.pos,
            radius: "56%",
            // splitNumber: t.splitNum || 10,
            min: 0,
            max: 100,
            startAngle: 225,
            endAngle: -45,
            axisLine: {show: !0, lineStyle: {width: 1, color: [[1, "rgba(0,0,0,0)"]]}},
            axisTick: {show: !0, lineStyle: {color: "rgba(53,125,224,0.6)", width: 1}, length: -5},
            splitLine: {show: !1, length: -20, lineStyle: {color: "rgba(53,125,224,0.6)"}},
            axisLabel: {show: !1, distance: -20, textStyle: {fontSize: "14", fontWeight: "bold"}},
            pointer: {show: 0},
            detail: {show: 0}
          }, {
            // name: t.name,
            type: "gauge",
            // center: t.pos,
            radius: "75%",
            startAngle: 225,
            endAngle: -45,
            min: 0,
            max: 100,
            axisLine: {
              show: !0,
              lineStyle: {
                width: 9,
                color: [[ChartObj.value[0] * 10 / 100, new this.$echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                  offset: 0,
                  color: "#5ce07e"
                }, {offset: .5, color: "#558ed7"}, {offset: 1, color: "#fec16b"}])], [1, "#dde8fe"]]
              }
            },
            axisTick: {show: 0},
            splitLine: {show: 0},
            axisLabel: {show: 0},
            pointer: {show: !0, width: "8%", length: "50%"},
            itemStyle: {normal: {color: "#357de0"}},
            detail: {
              show: !0,
              offsetCenter: [0, "60%"],
              textStyle: {fontSize: 14, color: "#44d96b"},
              formatter: ["{value} %" ].join("\n"),
              rich: {
                name: {fontSize: 14, lineHeight: 30, color: "#ddd"},
                score: {
                  backgroundColor: "#357de0",
                  padding: [2, 10, 5, 10],
                  lineHeight: 30,
                  borderRadius: 10,
                  color: "#fff",
                  fontFamily: "微软雅黑",
                  fontSize: 12
                }
              }
            },
            data: [ChartObj.value[0] * 10]
          }
          ]
        };
        return option;
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
    height: 181px;
  }
</style>
