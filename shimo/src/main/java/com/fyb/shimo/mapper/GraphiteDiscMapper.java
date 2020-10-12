package com.fyb.shimo.mapper;

import com.fyb.shimo.entity.GraphiteDisc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fyb.shimo.vo.StatisticVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fyb
 * @since 2020-06-26
 */
public interface GraphiteDiscMapper extends BaseMapper<GraphiteDisc> {

    //统计石墨盘使用情况
    List<StatisticVo> listStatistics();
}
