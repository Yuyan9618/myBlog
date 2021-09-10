package com.zzy.queryvo;

/**
 * @ Description : 搜索博客实体类
 * 这里的搜索是使用的MySQL的模糊查询，根据博客标题和博客分类查询出想要搜索的文章，需要创建有标题和分类属性的实体类做vo查询
 * 模糊查询如何操作：可以使用bind标签，bind标签可以使用OGNL表达式创建一个变量并将其绑定到上下文中
 * @ author zhangziyao
 * @ date 2021/9/8 15:17
 */
public class SearchBlog {
    private String title;
    private Long typeId;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Long getTypeId() {
        return typeId;
    }
    
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    
    @Override
    public String toString() {
        return "SearchBlog{" +
            "title='" + title + '\'' +
            ", typeId=" + typeId +
            '}';
    }
}
