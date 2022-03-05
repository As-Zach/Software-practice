<template>
  <div id="map" style="width: 100%;height:326px;">
    <div>
      <el-button icon="el-icon-plus" size="small" type="primary" @click="showAddData">添加</el-button>
    </div>
    <div style="margin-top: 10px">
            <el-table
                :data="data_all"
                stripe
                border
                v-loading="loading"
                element-loading-text="正在加载..."
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)"
                style="width: 100%">
              <el-table-column
                  type="selection"
                  width="55">
              </el-table-column>
              <el-table-column
                  prop="datax"
                  align="left"
                  label="日期">
              </el-table-column>
              <el-table-column
                  prop="datay"
                  align="left"
                  label="压力值(hPa)">
              </el-table-column>
              <el-table-column
                  prop="temp"
                  align="left"
                  label="温度(°C)">
              </el-table-column>
            </el-table>
    </div>
    <el-dialog title="增加监控数据" :visible.sync="dialogVisible_add" width="40%">
      <div>
        <div>
          <el-tag>日期</el-tag>
          <el-input type="month" class="addMapInput" size="small" v-model="AddData.datax"></el-input>
        </div>
        <div>
          <el-tag>压力值(hPa)</el-tag>
          <el-input class="addMapInput" size="small" v-model="AddData.datay"></el-input>
        </div>
        <div>
          <el-tag>温度(°C)</el-tag>
          <el-input class="addMapInput" size="small" v-model="AddData.temp"></el-input>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogVisible_add = false">取 消</el-button>
        <el-button size="small" type="primary" @click="addData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "ChartData",
  data() {

    return {
      data_all :[],
      AddData: {
        datax: '',
        datay: '',
        temp : ''
      },
      loading: false,
      dialogVisible_add: false
    };
  },
  mounted() {
    this.getAll();
  },
  methods: {
    showAddData() {
      this.AddData.datax = '';
      this.AddData.datay = '';
      this.AddData.temp = '';
      this.dialogVisible_add = true;
    },
    addData () {
      this.postRequest("/time/chart/", this.AddData).then(resp => {
        if (resp) {
          this.getAll();
          this.dialogVisible_add=false;
        }
      })
    },
    getAll() {
      this.loading = true;
      this.getRequest("/time/chart/").then(resp => {
        this.loading = false;
        if (resp) {
          this.data_all = resp;
        }
      })
    }
  }
}
</script>

<style>
.addMapInput {
  width: 200px;
  margin-left: 8px;
}

input[type="month"] {
  width: 158px;
  height: 50px;
  border: none;
  line-height: 1;
  appearance: none;
  -moz-appearance: none;
  -webkit-appearance: none;

  padding-left: 14px;
  font-size: 1rem;
  color: #525C66;
  outline: none;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  padding-right:-00px;


}

input[type="month"]::-webkit-inner-spin-button {
  visibility: hidden;
}

input[type="month"] {
  outline: none;
}

input[type="month"]:after {}



input[type="month"]::-webkit-calendar-picker-indicator {
  /*这是控制下拉小箭头的*/
  border: 0px solid #ccc;
  border-radius: 0px;
  color: #FFF;
}

</style>