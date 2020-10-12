<template>
    <div>
      <!-- 面包屑导航 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>石墨盘管理</el-breadcrumb-item>
        <el-breadcrumb-item>使用统计</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card>
        <el-table :data="list" border stripe  height="500">
          <el-table-column type="index" fixed></el-table-column>
          <el-table-column label="石墨盘封装类型" prop="fengZhuang"></el-table-column>
          <el-table-column label="废弃数量" prop="abandon"></el-table-column>
          <el-table-column label="正常使用数量" prop="used"></el-table-column>
          <el-table-column label="总数" prop="total"></el-table-column>
        </el-table>
      </el-card>
    </div>
</template>

<script>
export default {
  name: 'Statistics',
  data () {
    return {
      list: []
    }
  },
  created () {
    this.getList()
  },
  methods: {
    async getList () {
      const { data: res } = await this.$http.get('graphite-disc/listStatistics')
      if (res.status !== 200) {
        return this.$message.error('获取石墨盘统计情况失败!')
      }
      this.list = res.data
    }
  }
}
</script>

<style scoped>

</style>
