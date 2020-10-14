<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>石墨盘管理</el-breadcrumb-item>
      <el-breadcrumb-item>清洗校验记录</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input placeholder="石墨盘编号" v-model="queryInfo.code" clearable @clear="getWashRecordList">
            <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">录入清洗校验记录</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="info" @click="exportExcel">导出</el-button>
        </el-col>
      </el-row>
      <!-- 石墨盘列表区域 -->
      <el-table :data="washRecordList" border stripe :height="height" @cell-mouse-enter="onHoverCell">
        <el-table-column type="index" fixed></el-table-column>
        <el-table-column label="石墨盘编号" prop="code" width="100">
          <template slot-scope="scope">
            <el-popover
              placement="top-start"
              width="400"
              title="石墨盘信息"
              trigger="hover">
              <el-table :data="gridData">
                <el-table-column width="150" property="eventName" ></el-table-column>
                <el-table-column width="200" property="eventValue" ></el-table-column>
              </el-table>
              <el-button slot="reference">{{scope.row.code}}</el-button>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="班组" prop="shiftGroup"></el-table-column>
        <el-table-column label="班次" prop="shift"></el-table-column>
        <el-table-column label="创建时间" prop="createdTime" width="200"></el-table-column>
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
        <el-table-column label="定位销钉高度1" prop="dwxdHeight1"></el-table-column>
        <el-table-column label="定位销钉高度2" prop="dwxdHeight2"></el-table-column>
        <el-table-column label="定位销钉高度3" prop="dwxdHeight3"></el-table-column>
        <el-table-column label="定位销钉高度4" prop="dwxdHeight4"></el-table-column>
        <el-table-column label="定位针高度1" prop="dwzHeight1"></el-table-column>
        <el-table-column label="定位针高度2" prop="dwzHeight2"></el-table-column>
        <el-table-column label="定位针高度3" prop="dwzHeight3"></el-table-column>
        <el-table-column label="定位针高度4" prop="dwzHeight4"></el-table-column>
        <el-table-column label="定位针高度5" prop="dwzHeight5"></el-table-column>
        <el-table-column label="定位针高度6" prop="dwzHeight6"></el-table-column>
        <el-table-column label="定位针高度7" prop="dwzHeight7"></el-table-column>
        <el-table-column label="定位针高度8" prop="dwzHeight8"></el-table-column>
        <el-table-column label="定位针高度9" prop="dwzHeight9"></el-table-column>
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
          <el-form-item label="定位针直径" prop="dwzDiameter1">
            <el-input v-model="addForm.dwzDiameter1" placeholder="定位针直径1"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzDiameter2">
            <el-input v-model="addForm.dwzDiameter2" placeholder="定位针直径2"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzDiameter3">
            <el-input v-model="addForm.dwzDiameter3" placeholder="定位针直径3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位针直径" prop="dwzDiameter4">
            <el-input v-model="addForm.dwzDiameter4" placeholder="定位针直径4"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzDiameter5">
            <el-input v-model="addForm.dwzDiameter5" placeholder="定位针直径5"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzDiameter6">
            <el-input v-model="addForm.dwzDiameter6" placeholder="定位针直径6"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位针直径" prop="dwzDiameter7">
            <el-input v-model="addForm.dwzDiameter7" placeholder="定位针直径7"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzDiameter8">
            <el-input v-model="addForm.dwzDiameter8" placeholder="定位针直径8"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzDiameter9">
            <el-input v-model="addForm.dwzDiameter9" placeholder="定位针直径9"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="背面避让槽深度" prop="bmbrcDepth1">
            <el-input v-model="addForm.bmbrcDepth1" placeholder="背面避让槽深度1"></el-input>
          </el-form-item>
          <el-form-item  prop="bmbrcDepth2">
            <el-input v-model="addForm.bmbrcDepth2" placeholder="背面避让槽深度2"></el-input>
          </el-form-item>
          <el-form-item  prop="bmbrcDepth3">
            <el-input v-model="addForm.bmbrcDepth3" placeholder="背面避让槽深度3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="平整度" prop="pzd1">
            <el-input v-model="addForm.pzd1" placeholder="平整度1"></el-input>
          </el-form-item>
          <el-form-item  prop="pzd2">
            <el-input v-model="addForm.pzd2" placeholder="平整度2"></el-input>
          </el-form-item>
          <el-form-item  prop="pzd3">
            <el-input v-model="addForm.pzd3" placeholder="平整度3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位销孔直径" prop="dwxkDiameter1">
            <el-input v-model="addForm.dwxkDiameter1" placeholder="定位销孔直径1"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxkDiameter2">
            <el-input v-model="addForm.dwxkDiameter2" placeholder="定位销孔直径2"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxkDiameter3">
            <el-input v-model="addForm.dwxkDiameter3" placeholder="定位销孔直径3"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxkDiameter4">
            <el-input v-model="addForm.dwxkDiameter4" placeholder="定位销孔直径4"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位销孔深度" prop="dwxkDepth1">
            <el-input v-model="addForm.dwxkDepth1" placeholder="定位销孔深度1"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxkDepth2">
            <el-input v-model="addForm.dwxkDepth2" placeholder="定位销孔深度2"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxkDepth3">
            <el-input v-model="addForm.dwxkDepth3" placeholder="定位销孔深度3"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxkDepth4">
            <el-input v-model="addForm.dwxkDepth4" placeholder="定位销孔深度4"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位销钉高度" prop="dwxdHeight1">
            <el-input v-model="addForm.dwxdHeight1" placeholder="定位销钉高度1"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxdHeight2">
            <el-input v-model="addForm.dwxdHeight2" placeholder="定位销钉高度2"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxdHeight3">
            <el-input v-model="addForm.dwxdHeight3" placeholder="定位销钉高度3"></el-input>
          </el-form-item>
          <el-form-item  prop="dwxdHeight4">
            <el-input v-model="addForm.dwxdHeight4" placeholder="定位销钉高度4"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位针高度" prop="dwzHeight1">
            <el-input v-model="addForm.dwzHeight1" placeholder="定位针高度1"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzHeight2">
            <el-input v-model="addForm.dwzHeight2" placeholder="定位针高度2"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzHeight3">
            <el-input v-model="addForm.dwzHeight3" placeholder="定位针高度3"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位针高度" prop="dwzHeight4">
            <el-input v-model="addForm.dwzHeight4" placeholder="定位针高度4"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzHeight5">
            <el-input v-model="addForm.dwzHeight5" placeholder="定位针高度5"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzHeight6">
            <el-input v-model="addForm.dwzHeight6" placeholder="定位针高度6"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="定位针高度" prop="dwzHeight7">
            <el-input v-model="addForm.dwzHeight7" placeholder="定位针高度7"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzHeight8">
            <el-input v-model="addForm.dwzHeight8" placeholder="定位针高度8"></el-input>
          </el-form-item>
          <el-form-item  prop="dwzHeight9">
            <el-input v-model="addForm.dwzHeight9" placeholder="定位针高度9"></el-input>
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
import config from '../../util/config'
export default {
  data () {
    // 验证编号的规则
    const checkCode = (rule, value, cb) => {
      // 验证编号的正则表达式
      const regCode = /^[A-Z0-9]{1,12}$/
      if (regCode.test(value)) {
        return cb()
      } else cb(new Error('请输入合法的编号:1-12大写英文和数字的组合'))
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
    // 验证定位销钉高度数据范围
    const checkRange6 = (rule, value, cb) => {
      const regCode = /^([1-9][0-9]*)+(.[0-9]{1,5})?$/
      const number = parseFloat(value)
      if (regCode.test(value) && number > 0) {
        return cb()
      } else cb(new Error('请输入合法数值'))
    }
    return {
      height: 500,
      total: 0,
      queryInfo: {
        // 当前的页数
        pageNum: 1,
        // 当前每页显示多少条数据
        pageSize: 5,
        code: ''
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
        ],
        dwxdHeight1: [
          { required: true, message: '请输入定位销钉高度1', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwxdHeight2: [
          { required: true, message: '请输入定位销钉高度2', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwxdHeight3: [
          { required: true, message: '请输入定位销钉高度3', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwxdHeight4: [
          { required: true, message: '请输入定位销钉高度4', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight1: [
          { required: true, message: '请输入定位针高度1', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight2: [
          { required: true, message: '请输入定位针高度2', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight3: [
          { required: true, message: '请输入定位针高度3', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight4: [
          { required: true, message: '请输入定位针高度4', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight5: [
          { required: true, message: '请输入定位针高度5', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight6: [
          { required: true, message: '请输入定位针高度6', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight7: [
          { required: true, message: '请输入定位针高度7', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight8: [
          { required: true, message: '请输入定位针高度8', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ],
        dwzHeight9: [
          { required: true, message: '请输入定位针高度9', trigger: 'blur' },
          { validator: checkRange6, trigger: 'blur' }
        ]
      },
      gridData: [
        {
          eventName: '创建时间',
          eventValue: ''
        },
        {
          eventName: '废弃时间',
          eventValue: ''
        },
        {
          eventName: '废弃原因',
          eventValue: ''
        }]
    }
  },
  created () {
    if (window.screen.width < 1400) {
      this.height -= 120
    }
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
    search () {
      this.queryInfo.pageNum = 1
      this.getWashRecordList()
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
        this.addDialogVisible = false
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
    },
    async onHoverCell (row, column) {
      if (column.label === '石墨盘编号') {
        const { data: res } = await this.$http.get(`graphite-disc/${row.code}`)
        if (res.status !== 200) {
          return this.$message.error('获取石墨盘信息失败')
        }
        this.gridData[0].eventValue = res.data.createdTime
        this.gridData[1].eventValue = res.data.abandonedTime
        this.gridData[2].eventValue = res.data.abandonedReason
      }
    },
    exportExcel () {
      window.location.href = config.BASE_REQUEST_PATH + 'shimo/wash-record/exportExcel'
    }
  }
}
</script>

<style scoped>

</style>
