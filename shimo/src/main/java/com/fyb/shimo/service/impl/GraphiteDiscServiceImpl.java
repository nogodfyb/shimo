package com.fyb.shimo.service.impl;

import com.fyb.shimo.entity.GraphiteDisc;
import com.fyb.shimo.mapper.GraphiteDiscMapper;
import com.fyb.shimo.service.IGraphiteDiscService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fyb.shimo.vo.StatisticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fyb
 * @since 2020-06-26
 */
@Service
public class GraphiteDiscServiceImpl extends ServiceImpl<GraphiteDiscMapper, GraphiteDisc> implements IGraphiteDiscService {


    @Autowired
    private GraphiteDiscMapper graphiteDiscMapper;


    @Override
    public List<StatisticVo> listStatistics(){
        return graphiteDiscMapper.listStatistics();
    }

}
