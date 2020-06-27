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
    </el-card>
    <!-- 添加清洗校验记录对话框 -->
    <el-dialog
      title="添加清洗校验记录"
      :visible.sync="addDialogVisible"
      width="70%" @close="addDialogClosed"
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
          <el-form-item label="背面避让槽深度2">
            <el-input v-model="addForm.user" placeholder="背面避让槽深度2"></el-input>
          </el-form-item>
          <el-form-item label="背面避让槽深度3">
            <el-input v-model="addForm.user" placeholder="背面避让槽深度3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="平整度1">
            <el-input v-model="addForm.user" placeholder="平整度1"></el-input>
          </el-form-item>
          <el-form-item label="平整度2">
            <el-input v-model="addForm.user" placeholder="平整度2"></el-input>
          </el-form-item>
          <el-form-item label="平整度3">
            <el-input v-model="addForm.user" placeholder="平整度3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位销孔直径1">
            <el-input v-model="addForm.user" placeholder="定位销孔直径1"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔直径2">
            <el-input v-model="addForm.user" placeholder="定位销孔直径2"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔直径3">
            <el-input v-model="addForm.user" placeholder="定位销孔直径4"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔直径4">
            <el-input v-model="addForm.user" placeholder="定位销孔直径4"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位销孔深度1">
            <el-input v-model="addForm.user" placeholder="定位销孔深度1"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔深度2">
            <el-input v-model="addForm.user" placeholder="定位销孔深度2"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔深度3">
            <el-input v-model="addForm.user" placeholder="定位销孔深度3"></el-input>
          </el-form-item>
          <el-form-item label="定位销孔深度4">
            <el-input v-model="addForm.user" placeholder="定位销孔深度4"></el-input>
          </el-form-item>
        </el-row>
      </el-form>
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
      if (exist) {
        return cb()
      } else cb(new Error('石墨盘编号不存在，请重新输入！'))
      console.log(exist)
    }
    // 验证定位针直径数据范围
    const checkRange = (rule, value, cb) => {
      console.log(rule.field)
      const regCode = /^[0-9]+(.[0-9]{1,10})?$/
      const number = parseFloat(value)
      if (number >= (1.465 - 0.025) && number <= (1.465 + 0.025) && regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的范围:1.465±0.025'))
    }
    // 验证背面避让槽深度数据范围
    const checkRange2 = (rule, value, cb) => {
      console.log(rule.field)
      const regCode = /^[0-9]+(.[0-9]{1,10})?$/
      const number = parseFloat(value)
      if (number >= (0.85 - 0.05) && number <= (0.85 + 0.05) && regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的范围:0.85±0.05'))
    }
    return {
      addDialogVisible: false,
      addForm: {

      },
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
        ]
      }
    }
  },
  methods: {
    addDialogClosed () {
    },
    async checkCodeExist (code) {
      const { data: res } = await this.$http.get('graphite-disc/check', { params: { code: code } })
      if (res.status === 200) {
        return true
      }
      return false
    }
  }
}
</script>

<style scoped>

</style>
