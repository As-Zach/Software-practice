<template>
  <div>
    <div>
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="请输入资料名称进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                     ></el-input>
          <el-button icon="el-icon-search" type="primary" @click="Search" >
            搜索
          </el-button>
        </div>

      </div>
      <el-dialog title="资料展示" :visible.sync="dialogVisible_show" width="80%">
        <div class="img-container">
          <img :src="m.url" :alt="m.name" :title="m.name" class="map-img">
        </div>
        <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogVisible_show = false">退出</el-button>
      </span>
      </el-dialog>
    </div>
    <div style="margin-top: 10px">
      <el-table
          :data="maps"
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
            prop="id"
            label="序号"
            align="left">
        </el-table-column>
        <el-table-column
            prop="name"
            align="left"
            label="资料名称">
        </el-table-column>

        <el-table-column
            label="操作">
          <template slot-scope="scope">
            <el-button type="success" @click="exportData(scope.$index, scope.row)" icon="el-icon-download" style="padding: 3px" size="mini" >下载</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="display: flex;justify-content: flex-end">
        <el-pagination
            background
            @current-change="currentChange"
            @size-change="sizeChange"
            layout="prev, pager, next, jumper, ->, total, slot"
            :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
function getFileUrl(obj) {
  let url;
  url = window.URL.createObjectURL(obj.files.item(0));
  return url;
}
export default {
  name: "ResManage",
  data() {
    return {
      m: {
        name: '',
        url: ''
      },
      dialogVisible_show: false,
      dialogVisible_add: false,
      dialogVisible: false,
      loading: false,
      keyword: "",
      AddMap: {
        name: '',
        url: ''
      },
      multipleSelection: [],
      total: 0,
      title: '',
      maps: []
    }
  },
  mounted() {
    this.initRes();
  },
  methods: {
    //
    showAddRes() {
      this.AddMap.name = '';
      this.AddMap.url = '';
      this.dialogVisible_add = true;
    },

    initRes() {
      this.loading = true;
      this.getRequest("/res/manage/").then(resp => {
        this.loading = false;
        if (resp) {
          this.maps = resp;
        }
      })
    },
    // 下载文件
    exportData(index, data) {
      window.open(data.url, '_parent');
    },
    handleNodeClick(data) {
      this.inputDepName = data.name;
      this.emp.departmentId = data.id;
      this.popVisible = !this.popVisible
    },
    showDepView() {
      this.popVisible = !this.popVisible
    },
    showDepView2() {
      this.popVisible2 = !this.popVisible2
    },
    sizeChange(currentSize) {
      this.size = currentSize;
      this.initEmps();
    },
    currentChange(currentPage) {
      this.page = currentPage;
      this.initEmps('advanced');
    },
    Search() {
      this.loading = true;
      let name = this.keyword;
      this.getRequest("/res/manage/"+name).then(resp => {
        this.loading = false;
        if (resp) {
          this.maps = resp;
        }
      });
    }
  }
}
</script>

<style>
/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-fade-enter-active {
  transition: all .8s ease;
}

.slide-fade-leave-active {
  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.map-img {
  width: 600px;
  height: 600px;
  border-radius: 0px;
}
.img-container {
  display: flex;
  justify-content: center;
}
.slide-fade-enter, .slide-fade-leave-to
  /* .slide-fade-leave-active for below version 2.1.8 */
{
  transform: translateX(10px);
  opacity: 0;
}
</style>
