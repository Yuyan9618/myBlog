package com.zzy.service.Impl;

import com.zzy.dao.BlogDao;
import com.zzy.dao.CommentDao;
import com.zzy.entity.Comment;
import com.zzy.service.BlogService;
import com.zzy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ Description : 评论业务层接口实现类
 * @ author zhangziyao
 * @ date 2021/9/9 14:34
 */
@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentDao commentDao;
    
    @Autowired
    private BlogDao blogDao;
 
    
    // 存放迭代查询出的所有子回复的集合
    private List<Comment> tempReplys = new ArrayList<>();
    
    // 查询评论
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        // 查询出父评论集合
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        // 循环每一个父评论
        for(Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            // 查询出一级子回复子集
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId,id);
            // 将一级子回复以及它的回复都放入到结果中
            combineChildren(blogId, childComments, parentNickname1);
            // 为该父评论设置回复子集
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }
    
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        // 判断是否有一级子回复
        if(childComments.size() > 0) {
            for(Comment childComment : childComments) {
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                // 递归查询出二级子回复，放入集合中
                recursive(blogId, childId, parentNickname);
            }
        }
    }
    
    // 根据一级子回复的id找到二级子回复（递归下去，通过二级子回复id找到三级子回复...）
    private void recursive(Long blogId, Long childId, String parentNickname1) {
        List<Comment> replyComments = commentDao.findByBlogIdAndReplyId(blogId, childId);
        
        if(replyComments.size() > 0) {
            for(Comment replyComment : replyComments) {
                String parentNickname = replyComment.getNickname();
                replyComment.setParentNickname(parentNickname1);
                Long replayId = replyComment.getId();
                tempReplys.add(replyComment);
                // 递归查询出二级子回复的回复，放入集合中
                recursive(blogId, replayId, parentNickname);
            }
        }
    }
    
    // 新增评论
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentDao.saveComment(comment);
        // 返回之前更新文章评论计数
        blogDao.getCommentCountById(comment.getBlogId());
        return comments;
    }
    
    // // 删除评论
    // @Override
    // public void deleteComment(Comment comment,Long id) {
    //     commentDao.deleteComment(id);
    //     blogDao.getCommentCountById(comment.getBlogId());
    // }
    
    
    @Override
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

    @Override
    // 前端传过来的comment对象，只拥有blogId和id，即只拥有url传过来的值，所以直接取parentCommentId属性会发生空指针异常
    public void deleteComment(Comment comment,Long id) {
        // 通过id拿到完整的comment
        Comment completeComment = getCommentById(id);
        if(completeComment == null) {
            return;
        }
        Long blogId = comment.getBlogId();
        if (completeComment.getParentCommentId().equals(Long.parseLong("-1"))) {
            // 如果是父评论，找到子回复并删除
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId,id);
            if(childComments.size() > 0) {
                for(Comment childComment : childComments) {
                    Long childId = childComment.getId();
                    // 删除此一级回复和它的子回复
                    recursionDelete(childComment, childId);
                }
            }
        }
        // 删除这条评论
        commentDao.deleteComment(id);
    }

    // 删除子回复和它的所有回复
    private void recursionDelete(Comment comment, Long childId) {
        Long blogId = comment.getBlogId();
        List<Comment> replyComments = commentDao.findByBlogIdAndReplyId(blogId, childId);
        if(replyComments.size() > 0) {
            for(Comment replyComment : replyComments) {
                Long id = replyComment.getId();
                recursionDelete(replyComment, id);
            }
        }
        commentDao.deleteComment(childId);
    }
}
