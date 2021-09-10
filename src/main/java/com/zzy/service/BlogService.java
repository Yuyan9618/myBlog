package com.zzy.service;

import com.zzy.entity.Blog;
import com.zzy.queryvo.*;

import java.util.List;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/8 9:49
 */
public interface BlogService {
    // 保存新增博客
    int saveBlog(Blog blog);
    // 删除博客
    void deleteBlog(Long id);
    // 查询要修改的博客
    ShowBlog getBlogById(Long id);
    // 修改博客
    int updateBlog(ShowBlog showBlog);
    // 搜索博客
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);
    // 查询博客管理列表
    List<BlogQuery> getAllBlog();
    // 查询首页最新博客列表信息
    List<FirstPageBlog> getAllFirstPageBlog();
    // 查询首页最新推荐博客信息
    List<RecommendBlog> getRecommendedBlog();
    // 搜索博客列表
    List<FirstPageBlog> getSearchBlog(String query);
    // 统计博客总数
    Integer getBlogTotal();
    // 统计访问总数
    Integer getBlogViewTotal();
    // 统计评论总数
    Integer getBlogCommentTotal();
    
    // 查询博客详情
    DetailedBlog getDetailedBlog(Long id);
    
    // 根据typeId查询博客列表，显示在分类页面
    List<FirstPageBlog> getByTypeId(Long typeId);
}


