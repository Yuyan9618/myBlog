package com.zzy.queryvo;

import com.zzy.entity.Type;

import java.util.Date;

/**
 * @ Description ：查询博客实体类
 * @ author zhangziyao
 * @ date 2021/9/8 10:06
 */
// 问：在查询文章列表的时候，前端页面需要显示分类名称，但博客数据表没有分类字段，这个要如何处理？
// 答：这里就要用到Mybatis的多表查询了，可以通过建立实体类的方式，在mapper定义专门的resultMap用于映射多对一的关系
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private boolean recommend;
    private boolean published;
    private Long typeId;
    private Type type;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public boolean isRecommend() {
        return recommend;
    }
    
    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
    
    public boolean isPublished() {
        return published;
    }
    
    public void setPublished(boolean published) {
        this.published = published;
    }
    
    public Long getTypeId() {
        return typeId;
    }
    
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    
    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "BlogQuery{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", updateTime=" + updateTime +
            ", recommend=" + recommend +
            ", published=" + published +
            ", typeId=" + typeId +
            ", type=" + type +
            '}';
    }
}
