package com.zzy.dao;

import com.zzy.entity.Blog;
import com.zzy.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sun.security.provider.SHA;

import java.util.List;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/8 9:47
 */
@Mapper
@Repository
public interface BlogDao {
    // 新增博客
    int saveBlog(Blog blog);
    
    // 删除博客
    void deleteBlog(Long id);
    
    // 修改博客
    int updateBlog(ShowBlog showBlog);
    // 查询出要修改的博客
    ShowBlog getBlogById(Long id);
    
    // 搜索博客
    List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog);
    
    // 查询博客管理列表(需要显示分类名称)
    List<BlogQuery> getAllBlogQuery();
    
    // 查询首页最新博客列表信息
    List<FirstPageBlog> getFirstPageBlog();
    
    // 查询首页最新推荐博客信息
    List<RecommendBlog> getAllRecommendBlog();
    
    // 搜索博客列表
    List<FirstPageBlog> getSearchBlog(String query);
    
    // 统计博客总数
    Integer getBlogTotal();
    
    // 统计访问总数
    Integer getBlogViewTotal();
    
    // 统计评论总数
    Integer getBlogCommentTotal();
    
    // 统计留言总数(没有写留言功能)
    // Integer getBlogMessageTotal();
    
    // 查询博客详情
    DetailedBlog getDetailedBlog(Long id);
    
    // 文章访问次数更新
    int updateViews(Long id);
    
    // 根据博客id查询出评论数量
    int getCommentCountById(Long id);
    
    // 根绝TypeId查询博客列表，显示在分类页面
    List<FirstPageBlog> getByTypeId(Long typeId);
}



