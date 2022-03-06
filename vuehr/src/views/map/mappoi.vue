<template>

  <div>
    <el-container>
      <el-header  height="300px" id ="allmap1"></el-header>
      <el-header  height="300px" id ="allmap2"></el-header>
    </el-container>

  </div>

</template>

<script>
export default {
  methods:{
    loadJScript(){
      var script=document.createElement("script");
      script.type="text/javascript";
      script.src=
          "https://api.map.baidu.com/getscript?v=3.0&ak=ZTEDIjzu0s5V4Fccv9Gsy32NWAKsc8LS";
      script.onload=() =>{
        this.init();
      };
      document.body.appendChild(script);
    },
    init(){
      let BMap=window.BMap;
      let A='116.403963';
      let B='39.915119';
      let C='15';
      var map1 = new BMap.Map("allmap1");
      map1.centerAndZoom(new BMap.Point(A,B),C);
      map1.addControl(new BMap.MapTypeControl({
        mapTypes:[
          BMAP_NORMAL_MAP,
          BMAP_HYBRID_MAP
        ]}));
      //map1.setCurrentCity("南京");
      map1.enableScrollWheelZoom(true);
      map1.addEventListener('click', function (e) {
        A=e.point.lng;
        B=e.point.lat;
        C=map1.getZoom();
        map2.centerAndZoom(new BMap.Point(A,B),C);
      });

      var map2 = new BMap.Map("allmap2",{mapType:BMAP_HYBRID_MAP});
      map2.centerAndZoom(new BMap.Point(A,B),C);
      map2.addControl(new BMap.MapTypeControl({
        mapTypes:[
          BMAP_NORMAL_MAP,
          BMAP_HYBRID_MAP
        ]}));
      map2.enableScrollWheelZoom(true);
      map2.addEventListener('click', function (e) {
        A=e.point.lng;
        B=e.point.lat;
        C=map2.getZoom();
        map1.centerAndZoom(new BMap.Point(A,B),C);
      });
    },
  },
  mounted() {
    this.loadJScript();
  },
};
</script>

<style lang="scss" scoped>
#allmap1{
  width: 100%;
  height: 100%;
  overflow: hidden;
  margin: 0;
  font-family: "微软雅黑",serif;
}
#allmap2{
  width: 100%;
  height: 100%;
  overflow: hidden;
  margin: 0;
  font-family: "微软雅黑",serif;
}
</style>