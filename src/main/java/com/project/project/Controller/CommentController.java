package com.project.project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.project.Entity.*;
import com.project.project.Service.Comment.CommentService;
import com.project.project.Service.User.UserService;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/premium/comment/{postid}")
    public Comment createComment(@RequestHeader("Authorization") String jwt ,  @RequestBody Comment comment , @PathVariable int postid) throws Exception{
        User user = userService.findUserbyJWT(jwt);

        Comment newComment = commentService.createComment(comment, postid, user.getId());

        return newComment;        
    }

    @PutMapping("/premium/like/{commentid}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable int commentid) throws Exception{
        User user = userService.findUserbyJWT(jwt);

        Comment likeComment = commentService.likeComment(commentid , user.getId());

        return likeComment;        
    }
}