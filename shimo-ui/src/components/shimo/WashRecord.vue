<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>石墨盘管理</el-breadcrumb-item>
      <el-breadcrumb-item>清洗校验记录</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-button type="primary" @click="addDialogVisible=true">录入清洗校验记录</el-button>
      <!-- 石墨盘列表区域 -->
      <el-table :data="washRecordList" border stripe height="500">
        <el-table-column type="index" fixed></el-table-column>
        <el-table-column label="石墨盘编号" prop="code"></el-table-column>
        <el-table-column label="班组" prop="shiftGroup"></el-table-column>
        <el-table-column label="班次" prop="shift"></el-table-column>
        <el-table-column label="定位针直径1" prop="dwzDiameter1"></el-table-column>
        <el-table-column label="定位针直径2" prop="dwzDiameter2"></el-table-column>
        <el-table-column label="定位针直径3" prop="dwzDiameter3"></el-table-column>
        <el-table-column label="定位针直径4" prop="dwzDiameter4"></el-table-column>
        <el-table-column label="定位针直径5" prop="dwzDiameter5"></el-table-column>
        <el-table-column label="定位针直径6" prop="dwzDiameter6"></el-table-column>
        <el-table-column label="定位针直径7" prop="dwzDiameter7"></el-table-column>
        <el-table-column label="定位针直径8" prop="dwzDiameter8"></el-table-column>
        <el-table-column label="定位针直径9" prop="dwzDiameter9"></el-table-column>
        <el-table-column label="背面避让槽深度1" prop="bmbrcDepth1"></el-table-column>
        <el-table-column label="背面避让槽深度2" prop="bmbrcDepth2"></el-table-column>
        <el-table-column label="背面避让槽深度3" prop="bmbrcDepth3"></el-table-column>
        <el-table-column label="平整度1" prop="pzd1"></el-table-column>
        <el-table-column label="平整度2" prop="pzd2"></el-table-column>
        <el-table-column label="平整度3" prop="pzd3"></el-table-column>
        <el-table-column label="定位销孔直径1" prop="dwxkDiameter1"></el-table-column>
        <el-table-column label="定位销孔直径2" prop="dwxkDiameter2"></el-table-column>
        <el-table-column label="定位销孔直径3" prop="dwxkDiameter3"></el-table-column>
        <el-table-column label="定位销孔直径4" prop="dwxkDiameter4"></el-table-column>
        <el-table-column label="定位销孔深度1" prop="dwxkDepth1"></el-table-column>
        <el-table-column label="定位销孔深度2" prop="dwxkDepth2"></el-table-column>
        <el-table-column label="定位销孔深度3" prop="dwxkDepth3"></el-table-column>
        <el-table-column label="定位销孔深度4" prop="dwxkDepth4"></el-table-column>
        <el-table-column label="创建时间" prop="createdTime" width="200"></el-table-column>
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
    <!-- 添加清洗校验记录对话框 -->
    <el-dialog
      title="添加清洗校验记录"
      :visible.sync="addDialogVisible"
      width="70%" @close="addDialogClosed" :close-on-click-modal="false"
    >
      <el-form :inline="true" :model="addForm" :rules="addFormRules" ref="addFormRef" >
        <el-row>
          <el-form-item label="石墨盘编号" prop="code">
            <el-input v-model="addForm.code" placeholder="石墨盘编号"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位针直径1" prop="dwzDiameter1">
            <el-input v-model="addForm.dwzDiameter1" placeholder="定位针直径1"></el-input>
          </el-form-item>
          <el-form-item label="定位针直径2" prop="dwzDiameter2">
            <el-input v-model="addForm.dwzDiameter2" placeholder="定位针直径2"></el-input>
          </el-form-item>
          <el-form-item label="定位针直径3" prop="dwzDiameter3">
            <el-input v-model="addForm.dwzDiameter3" placeholder="定位针直径3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位针直径4" prop="dwzDiameter4">
            <el-input v-model="addForm.dwzDiameter4" placeholder="定位针直径4"></el-input>
          </el-form-item>
          <el-form-item label="定位针直径5" prop="dwzDiameter5">
            <el-input v-model="addForm.dwzDiameter5" placeholder="定位针直径5"></el-input>
          </el-form-item>
          <el-form-item label="定位针直径6" prop="dwzDiameter6">
            <el-input v-model="addForm.dwzDiameter6" placeholder="定位针直径6"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位针直径7" prop="dwzDiameter7">
            <el-input v-model="addForm.dwzDiameter7" placeholder="定位针直径7"></el-input>
          </el-form-item>
          <el-form-item label="定位针直径8" prop="dwzDiameter8">
            <el-input v-model="addForm.dwzDiameter8" placeholder="定位针直径8"></el-input>
          </el-form-item>
          <el-form-item label="定位针直径9" prop="dwzDiameter9">
            <el-input v-model="addForm.dwzDiameter9" placeholder="定位针直径9"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="背面避让槽深度1" prop="bmbrcDepth1">
            <el-input v-model="addForm.bmbrcDepth1" placeholder="背面避让槽深度1"></el-input>
          </el-form-item>
          <el-form-item label="背面避让槽深度2" prop="bmbrcDepth2">
            <el-input v-model="addForm.bmbrcDepth2" placeholder="背面避让槽深度2"></el-input>
          </el-form-item>
          <el-form-item label="背面避让槽深度3" prop="bmbrcDepth3">
            <el-input v-model="addForm.bmbrcDepth3" placeholder="背面避让槽深度3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="平整度1" prop="pzd1">
            <el-input v-model="addForm.pzd1" placeholder="平整度1"></el-input>
          </el-form-item>
          <el-form-item label="平整度2" prop="pzd2">
            <el-input v-model="addForm.pzd2" placeholder="平整度2"></el-input>
          </el-form-item>
          <el-form-item label="平整度3" prop="pzd3">
            <el-input v-model="addForm.pzd3" placeholder="平整度3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位销孔直径1" prop="dwxkDiameter1">
            <el-input v-model="addForm.dwxkDiameter1" placeholder="定位销孔直径1"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔直径2" prop="dwxkDiameter2">
            <el-input v-model="addForm.dwxkDiameter2" placeholder="定位销孔直径2"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔直径3" prop="dwxkDiameter3">
            <el-input v-model="addForm.dwxkDiameter3" placeholder="定位销孔直径3"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔直径4" prop="dwxkDiameter4">
            <el-input v-model="addForm.dwxkDiameter4" placeholder="定位销孔直径4"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位销孔深度1" prop="dwxkDepth1">
            <el-input v-model="addForm.dwxkDepth1" placeholder="定位销孔深度1"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔深度2" prop="dwxkDepth2">
            <el-input v-model="addForm.dwxkDepth2" placeholder="定位销孔深度2"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔深度3" prop="dwxkDepth3">
            <el-input v-model="addForm.dwxkDepth3" placeholder="定位销孔深度3"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔深度4" prop="dwxkDepth4">
            <el-input v-model="addForm.dwxkDepth4" placeholder="定位销孔深度4"></el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <!--      底部区域-->
      <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="addWashRecord">确 定</el-button>
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
    // 验证编号是否已存在和是否废弃
    const checkCodeExist = async (rule, value, cb) => {
      const exist = await this.checkCodeExist(value)
      if (!exist) {
        cb(new Error('石墨盘编号不存在，请重新输入！'))
      }
      const abandoned = await this.checkCodeAbandoned(value)
      if (abandoned) {
        cb(new Error('石墨盘编号已废弃，请重新输入！'))
      }
      return cb()
    }
    // 验证定位针直径数据范围
    const checkRange = (rule, value, cb) => {
      const regCode = /^[0-9]+(.[0-9]{1,10})?$/
      const number = parseFloat(value)
      if (number >= (1.465 - 0.025) && number <= (1.465 + 0.025) && regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的范围:1.465±0.025'))
    }
    // 验证背面避让槽深度数据范围
    const checkRange2 = (rule, value, cb) => {
      const regCode = /^[0-9]+(.[0-9]{1,10})?$/
      const number = parseFloat(value)
      if (number >= (0.85 - 0.05) && number <= (0.85 + 0.05) && regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的范围:0.85±0.05'))
    }
    // 验证平整度数据范围
    const checkRange3 = (rule, value, cb) => {
      const regCode = /^[0-9]+(.[0-9]{1,10})?$/
      const number = parseFloat(value)
      if (number > 0 && number < 100 && regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的范围:大于0小于100'))
    }
    // 验证定位销孔直径数据范围
    const checkRange4 = (rule, value, cb) => {
      const regCode = /^[0-9]+(.[0-9]{1,10})?$/
      const number = parseFloat(value)
      if (number >= (2.5 - 0.05) && number <= (2.5 + 0.05) && regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的范围:2.5±0.05'))
    }
    // 验证定位销孔深度数据范围
    const checkRange5 = (rule, value, cb) => {
      const regCode = /^[0-9]+(.[0-9]{1,10})?$/
      const number = parseFloat(value)
      if (number >= (7 - 0.2) && number <= (7 + 0.2) && regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的范围:7±0.2'))
    }
    return {
      total: 0,
      queryInfo: {
        // 当前的页数
        pageNum: 1,
        // 当前每页显示多少条数据
        pageSize: 5
      },
      addDialogVisible: false,
      addForm: {

      },
      washRecordList: [],
      addFormRules: {
        code: [
          { required: true, message: '请输入石墨盘编号', trigger: 'blur' },
          { validator: checkCode, trigger: 'blur' },
          { validator: checkCodeExist, trigger: 'blur' }
        ],
        dwzDiameter1: [
          { required: true, message: '请输入定位针直径1', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        dwzDiameter2: [
          { required: true, message: '请输入定位针直径2', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        dwzDiameter3: [
          { required: true, message: '请输入定位针直径3', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        dwzDiameter4: [
          { required: true, message: '请输入定位针直径4', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        dwzDiameter5: [
          { required: true, message: '请输入定位针直径5', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        dwzDiameter6: [
          { required: true, message: '请输入定位针直径6', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        dwzDiameter7: [
          { required: true, message: '请输入定位针直径7', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        dwzDiameter8: [
          { required: true, message: '请输入定位针直径8', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        dwzDiameter9: [
          { required: true, message: '请输入定位针直径9', trigger: 'blur' },
          { validator: checkRange, trigger: 'blur' }
        ],
        bmbrcDepth1: [
          { required: true, message: '请输入背面避让槽深度1', trigger: 'blur' },
          { validator: checkRange2, trigger: 'blur' }
        ],
        bmbrcDepth2: [
          { required: true, message: '请输入背面避让槽深度2', trigger: 'blur' },
          { validator: checkRange2, trigger: 'blur' }
        ],
        bmbrcDepth3: [
          { required: true, message: '请输入背面避让槽深度3', trigger: 'blur' },
          { validator: checkRange2, trigger: 'blur' }
        ],
        pzd1: [
          { required: true, message: '请输入平整度1', trigger: 'blur' },
          { validator: checkRange3, trigger: 'blur' }
        ],
        pzd2: [
          { required: true, message: '请输入平整度2', trigger: 'blur' },
          { validator: checkRange3, trigger: 'blur' }
        ],
        pzd3: [
          { required: true, message: '请输入平整度3', trigger: 'blur' },
          { validator: checkRange3, trigger: 'blur' }
        ],
        dwxkDiameter1: [
          { required: true, message: '请输入定位销孔直径1', trigger: 'blur' },
          { validator: checkRange4, trigger: 'blur' }
        ],
        dwxkDiameter2: [
          { required: true, message: '请输入定位销孔直径2', trigger: 'blur' },
          { validator: checkRange4, trigger: 'blur' }
        ],
        dwxkDiameter3: [
          { required: true, message: '请输入定位销孔直径3', trigger: 'blur' },
          { validator: checkRange4, trigger: 'blur' }
        ],
        dwxkDiameter4: [
          { required: true, message: '请输入定位销孔直径4', trigger: 'blur' },
          { validator: checkRange4, trigger: 'blur' }
        ],
        dwxkDepth1: [
          { required: true, message: '请输入定位销孔深度1', trigger: 'blur' },
          { validator: checkRange5, trigger: 'blur' }
        ],
        dwxkDepth2: [
          { required: true, message: '请输入定位销孔深度2', trigger: 'blur' },
          { validator: checkRange5, trigger: 'blur' }
        ],
        dwxkDepth3: [
          { required: true, message: '请输入定位销孔深度3', trigger: 'blur' },
          { validator: checkRange5, trigger: 'blur' }
        ],
        dwxkDepth4: [
          { required: true, message: '请输入定位销孔深度4', trigger: 'blur' },
          { validator: checkRange5, trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.getWashRecordList()
  },
  methods: {
    async getWashRecordList () {
      const { data: res } = await this.$http.get('wash-record/list', { params: this.queryInfo })
      if (res.status !== 200) {
        return this.$message.error('获取清洗记录列表失败！')
      }
      this.washRecordList = res.data.list
      this.total = res.data.total
      console.log(res)
    },
    addDialogClosed () {
      this.$refs.addFormRef.resetFields()
    },
    async checkCodeExist (code) {
      const { data: res } = await this.$http.get('graphite-disc/check', { params: { code: code } })
      if (res.status === 200) {
        return true
      }
      return false
    },
    async checkCodeAbandoned (code) {
      const { data: res } = await this.$http.get('graphite-disc/checkCodeAbandoned', { params: { code: code } })
      if (res.status === 200) {
        return true
      }
      return false
    },
    addWashRecord () {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) {
          return false
        }
        // 可以发起添加清洗记录的网络请求
        const { data: res } = await this.$http.post('wash-record/add', this.addForm)
        if (res.status !== 200) {
          return this.$message.error('添加清洗校验记录失败！')
        }
        this.$message.success('添加清洗校验记录成功！')
        // 隐藏添加清洗记录的对话框
        // this.addDialogVisible = false
        await this.getWashRecordList()
      })
    },
    handleSizeChange (newSize) {
      this.queryInfo.pageSize = newSize
      this.getWashRecordList()
    },
    handleCurrentChange (newPage) {
      this.queryInfo.pageNum = newPage
      this.getWashRecordList()
    }
  }
}
</script>

<style scoped>

</style>
