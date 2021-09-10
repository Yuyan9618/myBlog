package com.zzy.queryvo;

/**
 * @ Description : 推荐博客查询实体类
 * 最新推荐只要显示博客标题、首图信息，但要注意这里要体现出是否推荐到推荐栏来，所以还要有个boolean类型的变量recommend
 * @ author zhangziyao
 * @ date 2021/9/8 16:47
 */
public class RecommendBlog {
    
    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommend;
    
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
    
    public boolean isRecommend() {
        return recommend;
    }
    
    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
    
    @Override
    public String toString() {
        return "RecommendBlog{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", firstPicture='" + firstPicture + '\'' +
            ", recommend=" + recommend +
            '}';
    }
}
