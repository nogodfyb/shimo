package com.fyb.shimo.controller;


import com.fyb.shimo.common.CommonResult;
import com.fyb.shimo.entity.Menu;
import com.fyb.shimo.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/shimo/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/list")
    public CommonResult<List<Menu>> listAll(){
        ArrayList<Menu> allMenuTree = menuService.getAllMenuTree(null);
        if (0!=allMenuTree.size()){
            return CommonResult.success(allMenuTree);
        }
        else {
            return CommonResult.failed();
        }
    }

}
