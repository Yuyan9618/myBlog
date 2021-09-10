package com.zzy.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/7 14:21
 */
public class Type {
    private Long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Blog> getBlogs() {
        return blogs;
    }
    
    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
    
    @Override
    public String toString() {
        return "Type{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", blogs=" + blogs +
            '}';
    }
}

// 因为分类与博客是一对多的关系，涉及到Mybatis的多对一和一对多的关系了，因此还需要定义blogs对象
