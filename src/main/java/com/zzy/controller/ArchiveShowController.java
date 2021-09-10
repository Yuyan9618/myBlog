package com.zzy.controller;

import com.zzy.entity.Blog;
import com.zzy.queryvo.BlogQuery;
import com.zzy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ Description : 时间轴页面显示
 * @ author zhangziyao
 * @ date 2021/9/10 9:58
 */
@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;
    
    @GetMapping("/archives")
    public String archive(Model model) {
        List<BlogQuery> blogs = blogService.getAllBlog();
        model.addAttribute("blogs",blogs);
        return "archives";
    }
    
}
