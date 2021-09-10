package com.zzy.service.Impl;

import com.zzy.NotFoundException;
import com.zzy.dao.BlogDao;
import com.zzy.entity.Blog;
import com.zzy.queryvo.*;
import com.zzy.service.BlogService;
import com.zzy.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/8 9:49
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    
    // 保存新增博客
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogDao.saveBlog(blog);
    }
    
    // 删除博客
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }
    
    // 查询出要修改的博客
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }
    // 修改博客
    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }
    
    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleAndType(searchBlog);
    }
    
    // 查询博客管理列表
    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }
    
    //查询首页最新博客列表信息
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }
    
    //查询首页最新推荐信息
    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
        return allRecommendBlog;
    }
    
    //搜索博客列表
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }
    
    //统计博客总数
    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }
    
    //统计访问总数
    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }
    
    //统计评论总数
    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }
    
    // 查询博客详情
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if(detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        // 博客访问次数自增
        blogDao.updateViews(id);
        // 文章评论数量自增
        blogDao.getCommentCountById(id);
        return detailedBlog;
    }
    
    // 分类页面查询
    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }
}


// 在新增博客中需要初始化创建时间、更新时间、浏览数量、评论数量
// 调用持久层接口实现相关功能