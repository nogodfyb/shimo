package com.fyb.shimo.service;

import com.fyb.shimo.entity.GraphiteDisc;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fyb.shimo.vo.StatisticVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fyb
 * @since 2020-06-26
 */
public interface IGraphiteDiscService extends IService<GraphiteDisc> {

    List<StatisticVo> listStatistics();
}
