<template>

  <div>
    <div>
      <el-input size="small" v-model="jl.name" style="width: 300px;" prefix-icon="el-icon-plus"
                placeholder="添加设备..."></el-input>
      <el-select v-model="jl.kind" placeholder="工艺区域" size="small"
                 style="margin-left: 5px;margin-right: 5px">
        <el-option
            v-for="item in kinds"
            :key="item"
            :label="item"
            :value="item">
        </el-option>
      </el-select>
      <el-input size="small" v-model="jl.size" style="width: 300px;" prefix-icon="el-icon-plus"
                placeholder="设备大小..."></el-input>
      <el-button icon="el-icon-plus" type="primary" size="small" @click="addEquip">添加</el-button>
    </div>
    <div style="margin-top: 10px">
      <el-table
          :data="jls"
          border
          v-loading="loading"
          element-loading-text="正在加载..."
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.8)"
          size="small"
          @selection-change="handleSelectionChange"
          style="width: 80%">
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
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
        <el-table-column
            label="操作">
          <template slot-scope="scope">
            <el-button size="small" @click="showEditView(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="deleteHandler(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="danger" size="small" style="margin-top: 10px" :disabled="multipleSelection.length==0"
                 @click="deleteMany">批量删除
      </el-button>

    </div>
    <el-dialog
        title="修改设备"
        :visible.sync="dialogVisible"
        width="30%">
      <div>
        <table>
          <tr>
            <td>
              <el-tag>设备名称</el-tag>
            </td>
            <td>
              <el-input size="small" v-model="updateJl.name"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>工艺区域</el-tag>
            </td>
            <td>
              <el-select v-model="updateJl.kind" placeholder="工艺区域" size="small"
                         style="margin-left: 5px;margin-right: 5px">
                <el-option
                    v-for="item in kinds"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
              </el-select>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>设备尺寸</el-tag>
            </td>
            <td>
              <el-input size="small" v-model="updateJl.size"></el-input>
            </td>
          </tr>
        </table>
      </div>
      <span slot="footer" class="dialog-footer">
    <el-button size="small" @click="dialogVisible = false">取 消</el-button>
    <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
  </span>
    </el-dialog>
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
      jl: {
        name: '',
        kind: '',
        size: '',
      },
      jls: [],
      kinds: [
        '进站区',
        '过滤区',
        '计量区',
        '调压区',
        '出站区',
      ]
    }
  },
  mounted() {
    this.initJls();
  },
  methods: {
    deleteMany() {
      this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】条记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ids = '?';
        this.multipleSelection.forEach(item => {
          ids += 'ids=' + item.id + '&';
        })
        this.deleteRequest("/equip/equip/" + ids).then(resp => {
          if (resp) {
            this.initJls();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    doUpdate() {
      this.putRequest("/equip/equip/", this.updateJl).then(resp => {
        if (resp) {
          this.initJls();
          this.dialogVisible = false;
        }
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    showEditView(data) {
      Object.assign(this.updateJl, data);
      this.dialogVisible = true;
    },
    deleteHandler(data) {
      this.$confirm('此操作将永久【' + data.name + '】职称, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/equip/equip/" + data.id).then(resp => {
          if (resp) {
            this.initJls();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    addEquip() {
      if (this.jl.name && this.jl.kind &&this.jl.size) {

        this.postRequest("/equip/equip/", this.jl).then(resp => {
          if (resp) {
            this.initJls();
          }
        });
      } else {
        this.$message.error("添加字段不可以为空!");
      }
    },
    initJls() {
      let kinds='过滤区'
      this.loading = true;
      this.getRequest("/equip/equip/"+kinds).then(resp => {
        this.loading = false;
        if (resp) {
          this.jls = resp;
          this.jl = {
            name: '',
            kind: '',
            size: ''
          };
        }
      })
    }
  }
}
</script>

<style scoped>

</style>