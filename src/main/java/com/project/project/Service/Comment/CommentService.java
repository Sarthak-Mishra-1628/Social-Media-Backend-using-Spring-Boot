package com.project.project.Service.Comment;
import com.project.project.Entity.Comment;

public interface CommentService {
    public Comment createComment(Comment comment , Integer postid, Integer userid) throws Exception;

    public Comment likeComment(Integer commentId , Integer userId) throws Exception;

    public Comment  getCommentById(Integer id) throws Exception;
}