package com.fyb.shimo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shimo.common.CommonPage;
import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.dto.WashRecordPageParam;
import com.fyb.shimo.entity.GraphiteDisc;
import com.fyb.shimo.entity.WashRecord;
import com.fyb.shimo.service.IGraphiteDiscService;
import com.fyb.shimo.service.IWashRecordService;
import com.fyb.shimo.util.ShiftUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


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
        LocalDateTime now = LocalDateTime.now();
        LocalTime localTime = now.toLocalTime();
        LocalDate localDate = now.toLocalDate();
        String shift = ShiftUtils.getShift(localTime);
        washRecord.setShift(shift);
        if("白".equals(shift)) {
            String groupBai = ShiftUtils.getGroupBai(localDate);
            washRecord.setShiftGroup(groupBai);
        }
        if("夜".equals(shift)){
            LocalDate localDateYe = ShiftUtils.getLocalDateYe(localTime, localDate);
            String groupYe = ShiftUtils.getGroupYe(localDateYe);
            washRecord.setShiftGroup(groupYe);
        }
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        graphiteDiscQueryWrapper.eq("code",washRecord.getCode());
        GraphiteDisc graphiteDisc = graphiteDiscService.getOne(graphiteDiscQueryWrapper);
        graphiteDisc.setLastUsedTime(now);
        graphiteDiscService.updateById(graphiteDisc);
        washRecord.setCreatedTime(now);
        boolean save = washRecordService.save(washRecord);
        if(save){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
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
