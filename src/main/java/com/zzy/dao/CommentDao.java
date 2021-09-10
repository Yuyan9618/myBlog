package com.zzy.dao;

import com.zzy.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/9 14:02
 */
@Mapper
@Repository
public interface CommentDao {
    
    //查询父级评论
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);
    
    //查询一级回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);
    
    //查询二级回复
    List<Comment> findByBlogIdAndReplyId(@Param("blogId") Long blogId,@Param("childId") Long childId);
    
    //添加一个评论
    int saveComment(Comment comment);
    
    //删除评论
    void deleteComment(Long id);
    
    // 根据评论id获取评论
    Comment getCommentById(Long id);
    
}
