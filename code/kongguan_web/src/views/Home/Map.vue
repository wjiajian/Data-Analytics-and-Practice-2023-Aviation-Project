<template>
  <div style="height: 100%">
	<baidu-map :center="center" :zoom="zoom"  style="height:100%" @click="getClickInfo"
               :scroll-wheel-zoom='true' :map-style="mapStyle">
      <bm-marker v-for="item in caseList" :key="item.id"
                 :position="{lng: item.radarLongtitude, lat: item.radarLatitude}" :rotation="Number(item.direction)"
                 :icon="{url: urlz(item.id), size: {width: 100, height: 75}}">
        <bm-label  :position="{lng: item.radarLongtitude, lat: item.radarLatitude}"
                  :content="item.acid"
                  :labelStyle="{color: 'gray', fontSize : '8px',backgroundColor: 'rgba(0,0,0,0)',border:0}"
                  title="Hover me"/>
 
      </bm-marker>
      <!-- 缩放控件，注册此组件才会显示拖放进度 -->
      <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
	</baidu-map>
    <div class="allStatistics box">
      <img src="../../assets/images/nl.png" width="45px" height="45px" style="position: absolute;right: 90px;top: 75px">
      <img src="../../assets/images/gj.png" width="45px" height="45px"
           style="position: absolute ;right: 290px;top: 70px">
      <div style=" margin-left: 70px;margin-top: 12px">
        <div>当前时间：{{new Date().getFullYear()}}-{{new Date().getMonth()}}-{{new Date().getDate()}} &ensp;{{new
          Date().getHours()}}:{{new Date().getMinutes()}}:{{new Date().getSeconds()}}
        </div>
        <div style="margin-left: -17px">当前位置：{{this.center.lng}} {{this.center.lat}}</div>
      </div>
      <div style=" font-weight: bold;position: absolute;top: 145px;left: 45px">
        <span style="color: #2a58f4">轨迹数：</span><span style="color: #2a58f4">{{count}}</span>
        <span style="color: #f17140;margin-left: 52px">告警数：</span><span style="color: #f17140">{{count1}}</span>
      </div>
    </div>
    <div class="sectors1 box">
      <div class="title">当前用户: <span class="npc">管理员 G</span></div>
      <div >
        <el-button :type="isActive==='G'?'success':'primary'" style="margin-left: 16px" @click="clickData('G')">G
        </el-button>
        <el-button :type="isActive==='K'?'success':'primary'" :class="isActive === 2?'active':''"
                   @click="clickData('K')">k
        </el-button>
        <el-button :type="isActive==='E'?'success':'primary'" :class="isActive === 3?'active':''"
                   @click="clickData('E')">E
        </el-button>
      </div>
    </div>
    <div class="sectors2 box">
      <div class="title">扇区状态栏</div>
      <div>
        <el-button :type="isActive==='G'?'success':'primary'" style="margin-left: 16px" @click="clickData('G')">G
        </el-button>
        <el-button :type="isActive==='K'?'success':'primary'" :class="isActive === 2?'active':''"
                   @click="clickData('K')">k
        </el-button>
        <el-button :type="isActive==='E'?'success':'primary'" :class="isActive === 3?'active':''"
                   @click="clickData('E')">E
        </el-button>
      </div>
    </div>
    <div class="simi_box box">
      <div class="similarity">
        <el-tag effect="dark"><span class="tag-group__title" style="">相似航班数提醒</span></el-tag>
        <div v-for="(it,index) in atcList" :key="index" style="height: 45px; font-weight: bold;"><span
          style="margin-left: 25px">{{it.gjSector}}</span><span
          style="margin-left: 45px">{{it.gj}}</span></div>
      </div>
      <div class="similarity">
        <el-tag effect="dark"><span class="tag-group__title" style="">管制指令纠错</span></el-tag>
        <div v-for="(it,index) in warnList" :key="index">
          <div style="height: 45px;text-align: center"><span
            style="width: 100%; font-weight: bold;">{{it.gj_acids}}</span></div>
          <div style="height: 45px;text-align: center;">
            <table style="height: 45px">
              <tr>
                <td style="width: 150px; border: #2a58f4 3px solid; border-left: none">{{it.gj_name}}</td>
                <td style="width: 83px; border: #2a58f4 3px solid">{{it.gj_track_num1}}</td>
                <td style="width: 83px; border: #2a58f4 3px solid">{{it.gj_track_num2}}</td>
                <td style="width: 83px; border: #2a58f4 3px solid; border-right: none">{{it.gj_distinct}}</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import {
    findLocusCount,
    findMultRadar,
    findWarnSimilarOfATC,
    findWarnSimilarOfATCCount,
    findWarnTp
  } from "@/api/map/map";
   export default {
    name: 'TestBaiDu',
    data() {
      return {
        center: {lng: 118.78995, lat: 36.62934},
        zoom: 8,
        url1: require("../../assets/images/fj.png"),
        url2: require("../../assets/images/hfj.png"),
        markerPoint: {lng: 116.404, lat: 39.915},
        caseList: [],
        warnList: [],
        atcList: [],
        count: 1,
        count1: 1,
        isActive: 'G',
        mapStyle: {
          styleJson: [
            {
              "featureType": "water",
              "elementType": "all",
              "stylers": {
                "color": "#285ea5"
              }
            },
            {
              "featureType": "land",
              "elementType": "all",
              "stylers": {
                "color": "#0c3c7f"
              }
            },
            {
              "featureType": "road",
              "elementType": "all",
              "stylers": {
                "visibility": "off"
              }
            },
            {
              "featureType": "point",
              "elementType": "all",
              "stylers": {
                "visibility": "off"
              }
            },
            {
              "featureType": "all",
              "elementType": "labels.text.fill",
              "stylers": {
                "color": "#2da0c6",
                "visibility": "off"
              }
            }
          ]
        },
        timer: null,
      }
    },
        mounted() {
      this.loadWarn();
      this.loadData();
      this.clickData();
      this.timeOut();
    },
    beforeDestroy() {    //页面关闭时清除定时器  
      window.clearInterval(this.timer);
      this.timer = null;
    },
    destroyed() {
      window.clearInterval(this.timer);
      this.timer = null; 
    },
    methods: {
      urlz(data){
        for(let i=0;i<this.warnList.length;i++){
          var value1 = this.warnList[i].gj_track_num1;
          var value2 = this.warnList[i].gj_track_num2;
          if(value1 == data || value2 == data){
            return  this.url2;
          }
        }
        return this.url1;
      },
      getClickInfo(e) {
        this.center.lng = e.point.lng
        this.center.lat = e.point.lat
      },
      loadData() {
        findMultRadar().then(data => {
          if (data.isSuccess) {
            this.caseList = data.result;
            this.caseList.forEach(it => {
              it.count = it.areaSource + "," + it.trackNumber
            })
          } else {
            this.$message.error("数据获取失败");
          }
        })
      },
      loadWarn(){
        findWarnTp().then(data => {
          if (data.isSuccess) {
            this.warnList = data.result;
          } else {
            this.warnList.error("数据获取失败");
          }
        })
      },
      clickData(data) {
        if (data == null) {
          this.isActive = 'G'
          data = 'G'
        } else {
          this.isActive = data
        }
        findLocusCount(data).then(data => {
          if (data.isSuccess) {
            this.count = data.result;
          } else {
            this.warnList.error("数据获取失败");
          }
        }),
          findWarnSimilarOfATC(data).then(data => {
            if (data.isSuccess) {
              this.atcList = data.result;
            } else {
              this.atcList.error("数据获取失败");
            }
          }),
          findWarnSimilarOfATCCount(data).then(data => {
            if (data.isSuccess) {
              this.count1 = data.result;
            } else {
              this.count1.error("数据获取失败");
            }
          })
      },
           timeOut(){
        // 需要在一开始就先调用一遍该方法，否则在开始的5s内是没有数据的 
        if (this.timer) {
          window.clearInterval(this.timer)
        } else {
          this.timer = window.setInterval(() => {
            this.loadData();
          }, 9000)
        }
      },
    }
  }
</script>
<style>
  .sectors1 { top: 20px; right: 450px; background: #fff; width: 220px; height: 80px; margin-top: 30px; }
  .sectors2 { top: 20px; right: 690px; background: #fff; width: 220px; height: 80px; margin-top: 30px; }
  .box { position: absolute; margin-top: 70px; }
  .allStatistics { background: #fff; width: 400px; height: 200px; top: 60px; right: 20px; margin-top: 30px; }
  .similarity { background: #fff; overflow: hidden; border-radius: 5px 5px 0 0; margin-bottom: 20px; }
  .simi_box { top: 300px; right: 20px; width: 400px; height: 400px; margin-top: 18px; }
  .npc { color: #2a58f4; }
  .title {  color: #575757; text-align: center; font-weight: bold;   }
  .active { background-color: #00b700; }
  .el-button { height: 25px; 
 }
  .el-tag { width: 400px; border-radius: 0px; }
  .common-right{ padding-top: 60px; padding-right: 0; padding-left: 0; }
</style>

