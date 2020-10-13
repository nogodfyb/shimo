package com.fyb.shimo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shimo.common.CommonPage;
import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.common.Const;
import com.fyb.shimo.dto.WashRecordPageParam;
import com.fyb.shimo.entity.WashRecord;
import com.fyb.shimo.service.IGraphiteDiscService;
import com.fyb.shimo.service.IWashRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fyb
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/shimo/wash-record")
public class WashRecordController {

    @Autowired
    private IWashRecordService washRecordService;

    @Autowired
    private IGraphiteDiscService graphiteDiscService;

    @PostMapping("/add")
    public CommonResult<Object> add(@RequestBody WashRecord washRecord){
        return washRecordService.add(washRecord);
    }
    //分页查询
    @GetMapping("/list")
    public CommonResult<CommonPage<WashRecord>> list(WashRecordPageParam pageParam, HttpSession session){
        QueryWrapper<WashRecord> washRecordQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(pageParam.getCode())){
            washRecordQueryWrapper.like("code",pageParam.getCode());
        }
        Page<WashRecord> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
        Page<WashRecord> pageResult = washRecordService.page(page,washRecordQueryWrapper);
        CommonPage<WashRecord> washRecordCommonPage = CommonPage.resetPage(pageResult);
        //将当前查询存储到session中
        List<WashRecord> washRecords = washRecordService.list(washRecordQueryWrapper);
        session.setAttribute(Const.CURRENT_EXPORT_RECORD_EXCEL,washRecords);
        return CommonResult.success(washRecordCommonPage);
    }

    //导出当前查询条件下的所有数据
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response, HttpSession session) throws IOException {
        List<WashRecord> excleVos =(List<WashRecord>) session.getAttribute(Const.CURRENT_EXPORT_RECORD_EXCEL);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(now.format(formatter)+"清洗记录", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), WashRecord.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("清洗记录").doWrite(excleVos);
    }
}
