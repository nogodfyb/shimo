<template>
    <div>
      <!-- 面包屑导航 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>石墨盘管理</el-breadcrumb-item>
        <el-breadcrumb-item>使用统计</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card>
        <div id="main" style="height: 300px;margin-top: 30px"></div>
        <el-table :data="list" border stripe  height="250">
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
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
export default {
  name: 'Statistics',
  data () {
    return {
      list: [],
      option: {
        title: {
          text: '石墨盘使用情况统计'
        },
        tooltip: {},
        legend: {
          data: ['废弃数量', '使用中数量']
        },
        xAxis: {
          data: ['SMA-FL', 'SMB-FL', 'SOD123FL', 'SOD323HE']
        },
        yAxis: {},
        series: [{
          name: '废弃数量',
          type: 'bar',
          data: [5, 20, 36, 10]
        }, {
          name: '使用中数量',
          type: 'bar',
          data: [5, 20, 36, 10]
        }]
      }
    }
  },
  async mounted () {
    await this.getList()
    this.initChart()
  },
  methods: {
    async getList () {
      const { data: res } = await this.$http.get('graphite-disc/listStatistics')
      if (res.status !== 200) {
        return this.$message.error('获取石墨盘统计情况失败!')
      }
      this.list = res.data
      this.option.series[0].data = this.list.map(item => item.abandon)
      this.option.series[1].data = this.list.map(item => item.used)
    },
    initChart () {
      this.chart = echarts.init(document.getElementById('main'), 'macarons')
      this.chart.setOption(this.option)
    }
  }
}
</script>

<style scoped>

</style>
