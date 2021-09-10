package com.zzy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.entity.Type;
import com.zzy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @ Description : 分类控制器
 * @ author zhangziyao
 * @ date 2021/9/7 15:19
 */
// 为了有良好的体验，前端页面显示的时候需要能够分页显示，操作成功后在前端有信息提示，并需要做重复判断，这些要如何实现呢？
// 答：1.分页显示使用PageHelper插件
// 2.前端信息提示可以用model.addAttribute
// 3.重复添加使用@Valid注解：请求数据校验，再做一个逻辑判断就可以了
@Controller
@RequestMapping("/admin")
public class TypeController {
    
    @Autowired
    private TypeService typeService;
    
    //    分页查询分类列表
    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }
    
    //    返回新增分类页面
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }
    
    //  新增分类
    @PostMapping("/types")
    public String post(@Valid Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.saveType(type);
        if (t == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }
    
    // 跳转修改分类页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }
    
    // 编辑修改分类
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.updateType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/types";
    }
    
    // 删除分类
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
    
}

// @Controller注解：用于标注控制层组件
// @RequestMapping("/admin")：建立请求URL和处理方法之间的对应关系
// @GetMapping注解：一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写，用于将HTTP get请求映射到特定处理程序的方法注解
// @PostMapping注解：一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写，用于将HTTP post请求映射到特定处理程序的方法注解
// @Valid注解：请求数据校验，用来判断是否有重复的分类
// @PathVariable注解：获取URL中的数据
//  attributes.addFlashAttribute：相当于重定向后，在URL后面拼接了参数，这样在重定向之后的页面后者控制器再去获取URL后面的参数就可以了
