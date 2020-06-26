package com.fyb.shimo.service;

import com.fyb.shimo.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fyb
 * @since 2020-06-26
 */
public interface IMenuService extends IService<Menu> {

    ArrayList<Menu> getAllMenuTree(List<Integer> idList);
}
