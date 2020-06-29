<template>
<div>
  <!-- 面包屑导航 -->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>石墨盘管理</el-breadcrumb-item>
    <el-breadcrumb-item>石墨盘列表</el-breadcrumb-item>
  </el-breadcrumb>
  <el-card>
    <!-- 搜索与添加区域  -->
    <el-row :gutter="20">
      <el-col :span="4">
        <el-input placeholder="石墨盘编号" v-model="queryInfo.code" clearable @clear="getShiMoList">
          <el-button slot="append" icon="el-icon-search" @click="getShiMoList"></el-button>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-input placeholder="废弃原因" v-model="queryInfo.reason" clearable @clear="getShiMoList">
          <el-button slot="append" icon="el-icon-search" @click="getShiMoList"></el-button>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-select v-model="queryInfo.fengZhuang" placeholder="封装类型" clearable @change="getShiMoList">
          <el-option
            v-for="item in productionOptions"
            :key="item.productId"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="dialogVisible=true">添加石墨盘</el-button>
      </el-col>
    </el-row>
    <!-- 石墨盘列表区域 -->
    <el-table :data="shimoList" border  height="500" :row-class-name="tableRowClassName">
      <el-table-column type="index"></el-table-column>
      <el-table-column label="石墨盘编号" prop="code"></el-table-column>
      <el-table-column label="封装类型" prop="fengZhuang"></el-table-column>
      <el-table-column label="是否启用">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isUsed" @change="shimoStateChanged(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="废弃原因" prop="abandonedReason"></el-table-column>
      <el-table-column label="创建时间" prop="createdTime"></el-table-column>
      <el-table-column label="废弃时间" prop="abandonedTime"></el-table-column>
      <el-table-column label="最近使用时间" prop="lastUsedTime"></el-table-column>
    </el-table>
    <!--    分页区域-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pageNum"
      :page-sizes="[3, 5, 10, 15]"
      :page-size="queryInfo.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </el-card>
  <!--    添加石墨盘的对话框-->
  <el-dialog
    title="添加石墨盘"
    :visible.sync="dialogVisible"
    width="50%" @close="addDialogClosed"
  >
    <!--      内容主体区域-->
    <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
      <el-form-item label="石墨盘编号" prop="code">
        <el-input v-model="addForm.code"></el-input>
      </el-form-item>
      <el-form-item label="封装类型" prop="fengZhuang">
        <el-select v-model="addForm.fengZhuang" placeholder="请选择">
          <el-option
            v-for="item in productionOptions"
            :key="item.productId"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <!--      底部区域-->
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="addShimo">确 定</el-button>
      </span>
  </el-dialog>
  <!--    修改石墨盘的对话框-->
  <el-dialog
    title="修改石墨盘"
    :visible.sync="editDialogVisible"
    width="50%" @close="editDialogClosed"
  >
    <!--      内容主体区域-->
    <el-form :model="editForm"  ref="editFormRef" label-width="100px" >
      <el-form-item label="废弃原因">
        <el-input v-model="editForm.abandonedReason"></el-input>
      </el-form-item>
    </el-form>
    <!--      底部区域-->
    <span slot="footer" class="dialog-footer">
    <el-button @click="editDialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="editShimo">确 定</el-button>
      </span>
  </el-dialog>
</div>
</template>

<script>
export default {
  data () {
    // 验证编号的规则
    const checkCode = (rule, value, cb) => {
      // 验证编号的正则表达式
      const regCode = /^[0-9]{1,4}$/
      if (regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的编号:1-4位数字'))
    }
    // 验证编号是否已存在
    const checkCodeExist = async (rule, value, cb) => {
      const exist = await this.checkCodeExist(value)
      if (!exist) {
        return cb()
      } else cb(new Error('石墨盘编号已存在，请重新输入新的编号！'))
      console.log(exist)
    }
    return {
      // 获取石墨列表的参数对象
      queryInfo: {
        // 当前的页数
        pageNum: 1,
        // 当前每页显示多少条数据
        pageSize: 5
      },
      shimoList: [],
      total: 0,
      dialogVisible: false,
      editDialogVisible: false,
      addForm: {
        code: '',
        fengZhuang: ''
      },
      editForm: {},
      addFormRules: {
        code: [
          { required: true, message: '请输入石墨盘编号', trigger: 'blur' },
          { validator: checkCode, trigger: 'blur' },
          { validator: checkCodeExist, trigger: 'blur' }
        ],
        fengZhuang: [{ required: true, message: '请选择石墨盘封装类型', trigger: 'blur' }]
      },
      productionOptions: [
        { productId: 1, label: 'SMB-FL', value: 'SMB-FL' },
        { productId: 2, label: 'SOD323HE', value: 'SOD323HE' },
        { productId: 3, label: 'SMA-FL', value: 'SMA-FL' },
        { productId: 4, label: 'SOD123FL', value: 'SOD123FL' }
      ]
    }
  },
  created () {
    this.getShiMoList()
  },
  methods: {
    tableRowClassName ({ row, rowIndex }) {
      if (row.overTime === true) {
        return 'warning-row'
      }
      return ''
    },
    async getShiMoList () {
      console.log(this.queryInfo)
      const { data: res } = await this.$http.get('graphite-disc/list', { params: this.queryInfo })
      if (res.status !== 200) {
        return this.$message.error('获取清洗记录列表失败！')
      }
      this.shimoList = res.data.list
      this.total = res.data.total
      console.log(res)
    },
    async checkCodeExist (code) {
      const { data: res } = await this.$http.get('graphite-disc/check', { params: { code: code } })
      if (res.status === 200) {
        return true
      }
      return false
    },
    handleSizeChange (newSize) {
      this.queryInfo.pageSize = newSize
      this.getShiMoList()
    },
    handleCurrentChange (newPage) {
      this.queryInfo.pageNum = newPage
      this.getShiMoList()
    },
    addDialogClosed () {
      this.$refs.addFormRef.resetFields()
    },
    addShimo () {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) {
          return false
        }
        // 可以发起添加石墨盘的网络请求
        const { data: res } = await this.$http.post('graphite-disc/add', this.addForm)
        if (res.status !== 200) {
          return this.$message.error('添加石墨盘失败！')
        }
        this.$message.success('添加石墨盘成功！')
        // 隐藏添加石墨盘的对话框
        this.dialogVisible = false
        // 重新获取石墨盘列表数据
        this.getShiMoList()
      })
    },
    shimoStateChanged (shimo) {
      if (shimo.isUsed) {
        this.editForm = shimo
        this.$confirm('此操作可将废弃的石墨盘恢复, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          // 可以发起修改石墨盘的网络请求
          const { data: res } = await this.$http.post('graphite-disc/edit', this.editForm)
          if (res.status !== 200) {
            this.editForm.isUsed = !this.editForm.isUsed
            return this.$message.error('恢复石墨盘状态失败！')
          }
          this.$message.success('修改石墨盘状态成功！石墨盘已恢复！')
          // 隐藏修改石墨盘的对话框
          this.editDialogVisible = false
          // 重新获取石墨盘列表数据
          await this.getShiMoList()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
          shimo.isUsed = !shimo.isUsed
        })
      } else {
        this.editDialogVisible = true
        this.editForm = shimo
        console.log(shimo)
      }
    },
    editDialogClosed () {
      this.editForm.isUsed = !this.editForm.isUsed
      this.$refs.editFormRef.resetFields()
    },
    async editShimo () {
      // 可以发起添加石墨盘的网络请求
      const { data: res } = await this.$http.post('graphite-disc/edit', this.editForm)
      if (res.status !== 200) {
        this.editForm.isUsed = !this.editForm.isUsed
        return this.$message.error('修改石墨盘状态失败！')
      }
      this.$message.success('修改石墨盘状态成功！石墨盘已废弃！')
      // 隐藏修改石墨盘的对话框
      this.editDialogVisible = false
      // 重新获取石墨盘列表数据
      this.getShiMoList()
    }
  }
}
</script>

<style>
  .el-table .warning-row {
    background: oldlace;
  }
</style>
