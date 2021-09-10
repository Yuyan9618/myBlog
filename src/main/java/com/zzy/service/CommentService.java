package com.zzy.service;

import com.zzy.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Description : 评论业务层接口
 * @ author zhangziyao
 * @ date 2021/9/9 14:28
 */

public interface CommentService {
    
    //根据博客id查询评论信息
    List<Comment> listCommentByBlogId(Long blogId);
    
    //添加保存评论
    int saveComment(Comment comment);
    
    //删除评论
    void deleteComment(Comment comment, Long id);
    
    // 查询评论通过id
    Comment getCommentById(Long id);
    
}
