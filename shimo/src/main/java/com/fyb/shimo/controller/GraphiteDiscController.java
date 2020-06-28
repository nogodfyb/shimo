package com.fyb.shimo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shimo.common.CommonPage;
import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.dto.GraphiteDiscPageParam;
import com.fyb.shimo.entity.GraphiteDisc;
import com.fyb.shimo.service.IGraphiteDiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fyb
 * @since 2020-06-26
 */
@RestController
@RequestMapping("/shimo/graphite-disc")
public class GraphiteDiscController {

    @Autowired
    private IGraphiteDiscService graphiteDiscService;

    @GetMapping("/list")
    public CommonResult<CommonPage<GraphiteDisc>> list(GraphiteDiscPageParam pageParam){
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        if(pageParam.getCode()!=null){
            graphiteDiscQueryWrapper.like("code",pageParam.getCode());
        }
        if(!StringUtils.isEmpty(pageParam.getFengZhuang())){
            graphiteDiscQueryWrapper.eq("feng_zhuang",pageParam.getFengZhuang());
        }
        if(!StringUtils.isEmpty(pageParam.getReason())){
            graphiteDiscQueryWrapper.like("abandoned_reason",pageParam.getReason());
        }
        Page<GraphiteDisc> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
        Page<GraphiteDisc> pageResult = graphiteDiscService.page(page,graphiteDiscQueryWrapper);
        CommonPage<GraphiteDisc> graphiteDiscCommonPage = CommonPage.resetPage(pageResult);
        return CommonResult.success(graphiteDiscCommonPage);
    }

    @PostMapping("/add")
    public CommonResult<Object> add(@RequestBody GraphiteDisc graphiteDisc){
        graphiteDisc.setIsUsed(true);
        graphiteDisc.setCreatedTime(LocalDateTime.now());
        boolean save = graphiteDiscService.save(graphiteDisc);
        if(save){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @PostMapping("/edit")
    public CommonResult<Object> edit(@RequestBody GraphiteDisc graphiteDisc){
        //从启用状态到废弃状态
        if(!graphiteDisc.getIsUsed()){
            graphiteDisc.setAbandonedTime(LocalDateTime.now());
        }else {
            graphiteDisc.setAbandonedReason(null);
            graphiteDisc.setAbandonedTime(null);
        }
        boolean update = graphiteDiscService.updateById(graphiteDisc);
        if(update){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @GetMapping("/check")
    public  CommonResult<Object> checkCodeExist(Integer code){
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        graphiteDiscQueryWrapper.eq("code",code);
        GraphiteDisc one = graphiteDiscService.getOne(graphiteDiscQueryWrapper);
        if(one!=null){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @GetMapping("/checkCodeAbandoned")
    public CommonResult<Object> checkCodeAbandoned(Integer code){
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        graphiteDiscQueryWrapper.eq("code",code).eq("is_used",0);
        GraphiteDisc one = graphiteDiscService.getOne(graphiteDiscQueryWrapper);
        if(one!=null){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
}
