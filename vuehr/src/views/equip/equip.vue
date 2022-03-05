<template>
  <div>
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="进站区" name="equipjz"><Equipjz></Equipjz></el-tab-pane>
      <el-tab-pane label="过滤区" name="equipgl"><Equipgl></Equipgl></el-tab-pane>
      <el-tab-pane label="计量区" name="equipjl"><Equipjl></Equipjl></el-tab-pane>
      <el-tab-pane label="调压区" name="equipty"><Equipty></Equipty></el-tab-pane>
      <el-tab-pane label="出站区" name="equipcz"><Equipcz></Equipcz></el-tab-pane>
      <el-tab-pane label="搜索" name="Searchname"><Searchname></Searchname></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Equipjz from '../../components/sys/basic/Equipjz'
import Equipgl from '../../components/sys/basic/Equipgl'
import Equipjl from '../../components/sys/basic/Equipjl'
import Equipty from '../../components/sys/basic/Equipty'
import Equipcz from '../../components/sys/basic/Equipcz'
import Searchname from '../../components/sys/basic/Searchname'
export default {
  name: "SysBasic",
  data(){
    return{
      activeName: 'equipjz',
    }
  },
  components:{
    Equipjz,
    Equipgl,
    Equipjl,
    Equipty,
    Equipcz,
    Searchname
  }
}
</script>

<style scoped>

</style>