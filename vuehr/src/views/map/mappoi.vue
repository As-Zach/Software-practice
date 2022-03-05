<template>
  <div id="allmap" > </div>
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
      var map = new BMap.Map("allmap");
      map.centerAndZoom(new BMap.Point(116.253981,40.224412),15);
      map.setCurrentCity("北京");
      map.enableScrollWheelZoom(true);
    },
  },
  mounted() {
    this.loadJScript();
  },
};
</script>

<style lang="scss" scoped>
#allmap{
  width: 100%;
  height: 90%;
  overflow: hidden;
  margin: 0;
  font-family: "微软雅黑",serif;

}

</style>