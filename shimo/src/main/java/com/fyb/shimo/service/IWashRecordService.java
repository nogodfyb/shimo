package com.fyb.shimo.service;

import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.entity.WashRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fyb
 * @since 2020-06-28
 */
public interface IWashRecordService extends IService<WashRecord> {

    CommonResult<Object> add(WashRecord washRecord);
}
