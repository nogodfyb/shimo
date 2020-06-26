package com.fyb.shimo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shimo.common.CommonPage;
import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.dto.GraphiteDiscPageParam;
import com.fyb.shimo.entity.GraphiteDisc;
import com.fyb.shimo.service.IGraphiteDiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.annotation.*;

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
        Page<GraphiteDisc> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
        Page<GraphiteDisc> pageResult = graphiteDiscService.page(page);
        CommonPage<GraphiteDisc> graphiteDiscCommonPage = CommonPage.resetPage(pageResult);
        return CommonResult.success(graphiteDiscCommonPage);
    }

    @PostMapping("/add")
    public CommonResult<Object> add(@RequestBody GraphiteDisc graphiteDisc){
        boolean save = graphiteDiscService.save(graphiteDisc);
        if(save){
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
}
