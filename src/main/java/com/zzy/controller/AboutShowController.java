package com.zzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ Description : 关于我页面控制器
 * @ author zhangziyao
 * @ date 2021/9/10 10:21
 */
@Controller
public class AboutShowController {
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
