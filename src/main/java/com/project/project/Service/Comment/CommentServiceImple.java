package com.project.project.Service.Comment;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.project.Entity.*;
import com.project.project.Repository.*;
import com.project.project.Service.Post.PostService;
import com.project.project.Service.User.UserService;


@Service
public class CommentServiceImple implements CommentService {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    PostRepo postRepo;


    @Override
    public Comment createComment(Comment comment, Integer postid, Integer userid) throws Exception{
        User user = userService.GetByID(userid);
        Post post = postService.GetPostByPostID(postid);

        if(user==null || post==null) throw new Exception("Error");

        comment.setContent(comment.getContent());
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepo.save(comment);  

        post.getComments().add(savedComment);
        postRepo.save(post);

        return savedComment;
    }


    @Override
    public Comment likeComment(Integer commentId, Integer userId)  throws Exception{

        Comment comment = getCommentById(commentId);
        if(comment==null) throw new Exception("Error");

        User user = userService.GetByID(userId);

        if(comment.getLiked().contains(user)) comment.getLiked().remove(user);
        else comment.getLiked().add(user);
        
        return commentRepo.save(comment);
    }


    @Override
    public Comment getCommentById(Integer id) throws Exception {
        Optional<Comment> comment = commentRepo.findById(id);

        if(comment.isEmpty()) throw new Exception("Error");

        return comment.get();
    }
}