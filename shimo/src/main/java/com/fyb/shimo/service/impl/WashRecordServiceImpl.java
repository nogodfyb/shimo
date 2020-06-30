package com.fyb.shimo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.entity.GraphiteDisc;
import com.fyb.shimo.entity.WashRecord;
import com.fyb.shimo.mapper.WashRecordMapper;
import com.fyb.shimo.service.IGraphiteDiscService;
import com.fyb.shimo.service.IWashRecordService;
import com.fyb.shimo.util.ShiftUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fyb
 * @since 2020-06-28
 */
@Service
@Transactional
public class WashRecordServiceImpl extends ServiceImpl<WashRecordMapper, WashRecord> implements IWashRecordService {

    @Autowired
    private IGraphiteDiscService graphiteDiscService;

    @Override
    public CommonResult<Object> add(WashRecord washRecord){
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
        boolean save = save(washRecord);
        if(save){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

}
