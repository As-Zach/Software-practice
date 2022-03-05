<template>
  <div id="map" style="width: 100%;height:100%;">
    <div style="margin-top: 10px">
    </div>
  </div>
</template>

<script>
let echarts = require('echarts')
export default {
  name: "TimeChart",
  data() {

    return {
      data_x :[],
      data_y :[],
      data_temp:[],
      data_all :[],
      loading: false

    };
  },
  mounted() {
    //this.getAll();
    //this.getData_x();
    //this.getData_y();
    this.getMap();
  },
  methods: {
    getAll(){
      this.loading = true;
      this.getRequest("/time/chart/").then(resp => {
        this.loading = false;
        if (resp) {
          this.data_all = resp;
          console.log(this.data_all)
          let test = JSON.parse(JSON.stringify(this.data_all))
          console.log(test);
          let arr=[];
          test.forEach((element) => {
            arr.push(element.datay);
            this.data_y=JSON.parse(JSON.stringify(arr));
          });
          console.log(arr);
          console.log(this.data_y);
        }
        //console.log(Object.values(this.data_all));
      })
    },
    getData_x(){
      console.log(this.data_y);
    },
    getData_y(){
      this.loading = true;
      let datay ='';
      this.getRequest("/time/chart/").then(resp => {
        this.loading = false;
        if (resp) {
          this.data_y = resp;
          console.log(this.data_y);
        }
      })
    },
    getMap() {
      this.loading = true;
      this.getRequest("/time/chart/").then(resp => {
        this.loading = false;
        if (resp) {
          this.data_all = resp;
          console.log(this.data_all)
          let test = JSON.parse(JSON.stringify(this.data_all))
          console.log(test);
          let arr=[];
          test.forEach((element) => {
            this.data_y.push(element.datay);
            this.data_x.push(element.datax);
            this.data_temp.push(element.temp);
          });
          // console.log(arr);
          // console.log(this.data_y);
        }
        var myChart = echarts.init(document.getElementById('map'))
        //console.log(this.data_all);
        let option = {
          legend: {
            selectedMode: true,//可点击
            data: ['压力值(hPa)',  '温度(°C)'],
            bottom: 0
          },
          toolbox: {
            show : true,
            feature : {
              magicType : {show: true, type: ['line', 'bar']},
            }
          },
          tooltip : {
            trigger: 'axis',
          },
          axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
          },
          calculable : true,
          title: {
            text: '天然气站场压力值数据监控',//主标题文本
            subtext: 'BeiJing',//副标题文本
            x: 'center',
            textStyle:{//主标题样式
              color: "#333",
              fontSize: 18
            },
            subtextStyle:{},//副标题样式
            itemGap: 10,//主副标题之间的间距
          },
          grid: {
            left: 100
          },
          xAxis: {
            type: 'category',
            data: this.data_x,
            boundaryGap: true, //控制日期是否在中间显示
            axisLabel: {
              show: true, //是否显示日期
              interval: 0, //强制显示全部 //
              rotate: 40,//倾斜的角度
              textStyle: {
                color: '#000', //日期的颜色
                fontSize: 12 //字体的大小
              }
            },
            axisLine: {
              lineStyle: {
                color: '#ccc' // x轴的颜色
              }
            }
          },
          yAxis: [{
            type: 'value',
            axisLabel: {
              formatter: '{value}',
              textStyle: {
                color: '#000' //数字的颜色
              },
              inside: false //控制数据是否在内侧还是外侧显示
            },
            axisLine: {
              lineStyle: {
                color: '#ccc' // 折线的颜色
              }
            },
            name:'(hPa)'
          },
            {
              type: 'value',
              axisLabel: {
                formatter: '{value}',
                textStyle: {
                  color: '#000' //数字的颜色
                },
                inside: false //控制数据是否在内侧还是外侧显示
              },
              axisLine: {
                lineStyle: {
                  color: '#ccc' // 折线的颜色
                }
              },
              name:'(°C)'
            }],
          series: [
            {
              name:'压力值(hPa)',
              data: this.data_y,
              type: 'line',
              symbol: 'circle', //是否显示实心的折线圆点
              smooth: true, //让折线有弧度
              symbolSize: 7, //折线圆点的大小
              itemStyle: {
                normal: {
                  color: '#d0482e', //折线点的颜色
                  lineStyle: {
                    color: '#002fa7' //折线的颜色
                  },
                  label: { show: true } //是否在折线点上显示数字
                }
              }
            },{
              name:'温度(°C)',
              data: this.data_temp,
              type: 'bar',
              yAxisIndex: 1,
              symbol: 'circle', //是否显示实心的折线圆点
              smooth: true, //让折线有弧度
              symbolSize: 7, //折线圆点的大小
              itemStyle: {
                normal: {
                  color: '#7cd02e', //折线点的颜色
                  lineStyle: {
                    color: '#b263fd' //折线的颜色
                  },
                  label: { show: true } //是否在折线点上显示数字
                }
              }
            }
          ]
        }
        myChart.setOption(option)
        //console.log(Object.values(this.data_all));
      });


    }
  }
}
</script>
<style scoped>

</style>