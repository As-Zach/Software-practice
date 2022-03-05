<template>

  <div>
    <el-input placeholder="请输入设备名进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
              clearable
              style="width: 350px;margin-right: 10px" v-model="keyword"
    ></el-input>
    <el-button icon="el-icon-search" type="primary" @click="initJls" >
      搜索
    </el-button>
    <div style="margin-top: 10px">
      <el-table
          :data = "jls"
          border
          v-loading="loading"
          element-loading-text="正在加载..."
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.8)"
          size="small"
          @selection-change="handleSelectionChange"
          style="width: 80%">
        <el-table-column
            prop="id"
            label="编号"
            width="55">
        </el-table-column>
        <el-table-column
            prop="name"
            label="设备名称"
            width="150">
        </el-table-column>
        <el-table-column
            prop="kind"
            label="工艺区域">
        </el-table-column>
        <el-table-column
            prop="size"
            label="设备大小">
        </el-table-column>
      </el-table>
    </div>

  </div>
</template>

<script>
export default {
  name: "EquipMana",
  data() {
    return {
      dialogVisible: false,
      loading: false,
      multipleSelection: [],
      updateJl: {
        name: '',
        kind: '',
        size: '',
      },
      keyword:"",
      jls:[],
      jl: {
        name: '',
        kind: '',
        size: '',
      },
    }
  },
  mounted() {
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    showEditView(data) {
      Object.assign(this.updateJl, data);
      this.dialogVisible = true;
    },
    initJls() {
      let name=this.keyword;
      this.loading = true;
      this.getRequest("/equip/equip/"+ name).then(resp => {
        this.loading = false;
        if (resp) {
          this.jls = resp;
        }
      })
    }
  }
}
</script>

<style scoped>

</style>