package com.zzy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.entity.Blog;
import com.zzy.entity.Type;
import com.zzy.entity.User;
import com.zzy.queryvo.BlogQuery;
import com.zzy.queryvo.SearchBlog;
import com.zzy.queryvo.ShowBlog;
import com.zzy.service.BlogService;
import com.zzy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/8 9:50
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    
    // 跳转新增博客页面 博客新增后，会跳转到博客列表，需要传递博客对象和分类的信息， 因此除了博客的model还需要Type相关model
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }
    
    // 新增博客
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        // 新增博客时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        // 设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        // 设置blog中的typeId属性
        blog.setTypeId(blog.getType().getId());
        // 设置用户id
        blog.setUserId(blog.getUser().getId());
        
        int b = blogService.saveBlog(blog);
        if(b == 0) {
            attributes.addFlashAttribute("message", "新增失败！");
        } else {
            attributes.addFlashAttribute("message", "新增成功！");
        }
        return "redirect:/admin/blogs";
    }
    
    // 删除博客
    //     @GetMapping("/blogs/{id}/delete"):路径参数传递，{id}为需要传递进去的id值
    //     return "redirect:/admin/blogs"：用于controller之间的跳转，重定向到查询博客列表
    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        // 删除后要跳转到博客列表
        return "redirect:/admin/blogs";
    }
    
    // 跳转要修改的博客
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }
    
    // 修改博客
    @PostMapping("/blogs/{id}")
    public String editPost(@Valid ShowBlog showBlog, RedirectAttributes attributes) {
        int b = blogService.updateBlog(showBlog);
        if(b == 0) {
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            attributes.addFlashAttribute("message", "修改失败");
        }
        return "redirect:/admin/blogs";
    }
    
    // 搜索博客
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum,2);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList"; // 这是thymeleaf的一个模板片断，相当于返回admin/blogs中的某个片段
        // 这里指返回admin/blogs页面的id为“blogList”部分的页面，而不是整个页面。这样不用刷新整个页面。
        // 一方面可以加载的更快，另一方面，而且也是更重要的，不会丢失用户在admin/blogs页面交互的信息，例如在输入框中输入的文字，选择框选择的内容等等。
    }
    
    // 博客列表
    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }
    
}


