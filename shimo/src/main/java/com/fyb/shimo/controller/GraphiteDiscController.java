package com.fyb.shimo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyb.shimo.common.CommonPage;
import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.common.Const;
import com.fyb.shimo.dto.GraphiteDiscPageParam;
import com.fyb.shimo.entity.GraphiteDisc;
import com.fyb.shimo.service.IGraphiteDiscService;
import com.fyb.shimo.util.OverTimeUtils;
import com.fyb.shimo.vo.StatisticVo;
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
 * @since 2020-06-26
 */
@RestController
@RequestMapping("/shimo/graphite-disc")
public class GraphiteDiscController {

    @Autowired
    private IGraphiteDiscService graphiteDiscService;

    //分页查询
    @GetMapping("/list")
    public CommonResult<CommonPage<GraphiteDisc>> list(GraphiteDiscPageParam pageParam, HttpSession session){
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        Page<GraphiteDisc> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
        //如果当前查询模式是查询超期的石墨盘
        if (pageParam.getOverTimeMode()) {
            graphiteDiscQueryWrapper.apply("TIMESTAMPDIFF(DAY,last_used_time,NOW())>=30");
        }
        if(!StringUtils.isEmpty(pageParam.getCode())){
            graphiteDiscQueryWrapper.like("code",pageParam.getCode());
        }
        if(!StringUtils.isEmpty(pageParam.getFengZhuang())){
            graphiteDiscQueryWrapper.eq("feng_zhuang",pageParam.getFengZhuang());
        }
        if(!StringUtils.isEmpty(pageParam.getReason())){
            graphiteDiscQueryWrapper.like("abandoned_reason",pageParam.getReason());
        }
        Page<GraphiteDisc> pageResult = graphiteDiscService.page(page,graphiteDiscQueryWrapper);
        List<GraphiteDisc> records = pageResult.getRecords();
        //存储当前查询记录到session中
        List<GraphiteDisc> graphiteDiscs = graphiteDiscService.list(graphiteDiscQueryWrapper);
        session.setAttribute(Const.CURRENT_EXPORT_GRAPHITE_EXCEL,graphiteDiscs);
        //判断每个石墨盘是否超时
        for (GraphiteDisc record : records) {
            OverTimeUtils.checkOverTime(record,LocalDateTime.now());
        }
        CommonPage<GraphiteDisc> graphiteDiscCommonPage = CommonPage.resetPage(pageResult);
        return CommonResult.success(graphiteDiscCommonPage);
    }

    //根据石墨盘id查询石墨盘
    @GetMapping("{code}")
    public CommonResult<GraphiteDisc> queryGraphiteDisc(@PathVariable String  code){
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        graphiteDiscQueryWrapper.eq("code",code);
        GraphiteDisc graphiteDisc = graphiteDiscService.getOne(graphiteDiscQueryWrapper);
        return CommonResult.success(graphiteDisc);
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
    public  CommonResult<Object> checkCodeExist(String code){
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        graphiteDiscQueryWrapper.eq("code",code);
        GraphiteDisc one = graphiteDiscService.getOne(graphiteDiscQueryWrapper);
        if(one!=null){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @GetMapping("/checkCodeAbandoned")
    public CommonResult<Object> checkCodeAbandoned(String code){
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        graphiteDiscQueryWrapper.eq("code",code).eq("is_used",0);
        GraphiteDisc one = graphiteDiscService.getOne(graphiteDiscQueryWrapper);
        if(one!=null){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    //校验是否有一个月未校验的石墨盘
    @GetMapping("/isOverTime")
    public CommonResult<Object> listOverTime(){
        QueryWrapper<GraphiteDisc> graphiteDiscQueryWrapper = new QueryWrapper<>();
        graphiteDiscQueryWrapper.apply("TIMESTAMPDIFF(DAY,last_used_time,NOW())>=30");
        List<GraphiteDisc> list = graphiteDiscService.list(graphiteDiscQueryWrapper);
        return list.size()>0?CommonResult.success(null):CommonResult.failed();
    }

    //列出石墨盘使用情况
    @GetMapping("/listStatistics")
    public CommonResult<List<StatisticVo>> listStatistics(){
        List<StatisticVo> statisticVos = graphiteDiscService.listStatistics();
        return CommonResult.success(statisticVos);
    }

    //导出当前查询条件下的所有数据
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response, HttpSession session) throws IOException {
        List<GraphiteDisc> excleVos =(List<GraphiteDisc>) session.getAttribute(Const.CURRENT_EXPORT_GRAPHITE_EXCEL);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(now.format(formatter)+"石墨盘列表", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), GraphiteDisc.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("石墨盘").doWrite(excleVos);
    }
}
