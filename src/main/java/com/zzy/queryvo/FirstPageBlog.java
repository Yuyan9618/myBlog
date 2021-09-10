package com.zzy.queryvo;

import java.util.Date;

/**
 * @ Description : 定义一个查询实体类，这里是查询最新博客列表实体类
 * 首页最新博客列表除了需要显示博客信息外，还需要显示分类、作者等信息，所以还需要定义分类名称和用户名、用户头像属性
 * @ author zhangziyao
 * @ date 2021/9/8 16:35
 */
public class FirstPageBlog {
    // 博客信息
    private Long id;
    private String title;
    private String firstPicture;
    private String views;
    private Integer commentCount;
    private Date updateTime;
    private String description;
    
    // 分类名称
    private String typeName;
    
    // 用户名
    private String nickname;
    
    // 用户头像
    private String avatar;
    
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
    
    public String getFirstPicture() {
        return firstPicture;
    }
    
    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }
    
    public String getViews() {
        return views;
    }
    
    public void setViews(String views) {
        this.views = views;
    }
    
    public Integer getCommentCount() {
        return commentCount;
    }
    
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTypeName() {
        return typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    @Override
    public String toString() {
        return "FirstPageBlog{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", firstPicture='" + firstPicture + '\'' +
            ", views='" + views + '\'' +
            ", commentCount=" + commentCount +
            ", updateTime=" + updateTime +
            ", description='" + description + '\'' +
            ", typeName='" + typeName + '\'' +
            ", nickname='" + nickname + '\'' +
            ", avatar='" + avatar + '\'' +
            '}';
    }
}
