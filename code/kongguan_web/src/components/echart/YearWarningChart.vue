<template>
  <div class="home">
    <div style="margin: 0px auto;height: 100%">
      <div id="pieChart" style="height: 450px"/>
    </div>
  </div>
</template>
<script>
  import {annualWarningStatisticsByCategory} from "../../api/chartdata/chartdata";

  export default {
    name: "YearWarningChart",
    data() {
      return {
        pieChartData: [],
        pieChartColor:['#51b9f9','#444349','#91ee7e','#dc8842','#636bd5','#f5587a']
      }
    },
    mounted() {
      this.loadData();
      this.drawPieChart();

    },
        methods: {
      loadData() {
        annualWarningStatisticsByCategory().then(data => {
          if (data.isSuccess) {
            this.formatData(data.result);
          } else {
            this.$message.error("数据获取失败");
          }
        });

      },
      formatData(data) {
        for (let i = 0; i < data.length; i++) {
          let itemstyle={color:this.pieChartColor[i%6]};
          this.pieChartData.push({value: data[i].gjCount, name: data[i].gjName,itemStyle: itemstyle},);
        }
        this.drawPieChart();
      },

      drawPieChart() {
        let pieChart = this.$echarts.init(document.getElementById("pieChart"))

        let optionPieChart = {
          title: {
            text: '冲突指令告警数据分析',
            top: 18,
            left: 26,
          },
          tooltip: {
            trigger: 'item'
          },
          series: [
            {
              name: '年度告警分类统计',
              type: 'pie',
              radius: '50%',
              center: ['50%', '55%'],//设置饼图位置
              data: this.pieChartData,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
              label:{
                normal : {
                  formatter: '{b}\n{d}%',
                  textStyle : {
                    fontWeight : 'normal',
                    fontSize : 15,
                    color:'#bcc7cc'
                  }
                }
              },
              labelLine:{
                normal:{
                  length:20
                }
              },
            }
          ]
        };
        pieChart.setOption(optionPieChart);
      }
    }
  }
</script>
<style scoped>
  .home {
    height: 490px;
    overflow: auto;
    margin: 0 auto;
    background-color: #ffffff;
    border: 1px solid #ebedf2;
    border-radius: 10px;
    box-shadow: 3px 3px 3px 3px #ebedf2;
  }
  .home::-webkit-scrollbar {
    display: none;
  }
  .chart {
    height: 560px;
  }
</style>
