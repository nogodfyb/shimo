package com.fyb.shimo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shimo.common.CommonPage;
import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.dto.WashRecordPageParam;
import com.fyb.shimo.entity.WashRecord;
import com.fyb.shimo.service.IGraphiteDiscService;
import com.fyb.shimo.service.IWashRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/list")
    public CommonResult<CommonPage<WashRecord>> list(WashRecordPageParam pageParam){
        Page<WashRecord> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
        Page<WashRecord> pageResult = washRecordService.page(page);
        CommonPage<WashRecord> washRecordCommonPage = CommonPage.resetPage(pageResult);
        return CommonResult.success(washRecordCommonPage);
    }
}
