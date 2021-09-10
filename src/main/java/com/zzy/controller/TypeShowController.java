package com.zzy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.entity.Type;
import com.zzy.queryvo.FirstPageBlog;
import com.zzy.service.BlogService;
import com.zzy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/10 9:33
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
    
    // 分类查询页面
    // {id}：当id为-1时，表示从首页导航栏进入分类页面，默认第一个分类显示颜色
    // getAllTypeAndBlog：查询分类名称和博客信息，前端统计出该分类下博客数量
    // getByTypeId：查询博客列表
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.getAllTypeAndBlog();
        
        // id为-1表示从首页导航栏进入分类界面
        if(id == -1) {
            // 默认显示第一个分类
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);
    
        PageHelper.startPage(pageNum,10000); // 让所有该类博客显示在1页
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
