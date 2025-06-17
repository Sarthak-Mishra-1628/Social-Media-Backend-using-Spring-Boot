package com.project.project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.project.Entity.*;
import com.project.project.Payload.Response;
import com.project.project.Service.Post.PostService;
import com.project.project.Service.User.UserService;
import java.util.*;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @GetMapping("/premium/Posts")
    public ResponseEntity<Response> getAll() {
        List<Post> posts = postService.GetAllPost();
        return ResponseEntity.ok(new Response(true, "All posts fetched successfully", posts));
    }


    @GetMapping("/post/user/{id}")
    public ResponseEntity<Response> getAllByUserID(@PathVariable int id) {
        List<Post> posts = postService.GetAllPostByUserID(id);
        return ResponseEntity.ok(new Response(true, "Posts by user fetched successfully", posts));
    }


    @PostMapping("/premium/post")
    public ResponseEntity<Response> create(@RequestHeader("Authorization") String jwt , @RequestBody Post post) {
        try {
            User user = userService.findUserbyJWT(jwt);

            Post createdPost = postService.createPost(post, user.getId());

            return ResponseEntity.ok(new Response(true, "Post created successfully", createdPost));
        } 
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(false, e.getMessage(), null));
        }
    }


    @DeleteMapping("/premium/delete/{postId}")
    public ResponseEntity<Response> delete(@RequestHeader("Authorization") String jwt  , @PathVariable int postId) {
        try {
            User user = userService.findUserbyJWT(jwt);

            String result = postService.deletePost(postId, user.getId());

            return ResponseEntity.ok(new Response(true, result, null));
        } 
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(false, e.getMessage(), null));
        }
    }


    @GetMapping("/post/{id}")
    public ResponseEntity<Response> getById(@PathVariable int id) {
        Post post = postService.GetPostByPostID(id);

        if (post != null) return ResponseEntity.ok(new Response(true, "Post found", post));
        
        return ResponseEntity.badRequest().body(new Response(false, "Post not found", null));
    }


    @PutMapping("/premium/likepost/{postId}")
    public ResponseEntity<Response> likePost(@RequestHeader("Authorization") String jwt , @PathVariable int postId){
        try {
            User user = userService.findUserbyJWT(jwt);
            Post post = postService.likePost(postId, user.getId());

            return ResponseEntity.ok(new Response(true, "Post liked/unliked successfully", post));
        } 

        catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(false, e.getMessage(), null));
        }
    }
    

    @PutMapping("/premium/save/{postId}")
    public ResponseEntity<Response> savePost(@RequestHeader("Authorization") String jwt  , @PathVariable int postId){
        try {
            User user = userService.findUserbyJWT(jwt);

            Post post = postService.savedPost(postId, user.getId());

            return ResponseEntity.ok(new Response(true, "Post saved/unsaved successfully", post));
        } 

        catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(false, e.getMessage(), null));
        }
    }



    @GetMapping("/premium/Posts/feed")
    public ResponseEntity<Response> getUserFeed(
        @RequestHeader("Authorization") String jwt,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "created") String sort,
        @RequestParam(defaultValue = "desc") String direction,
        @RequestParam(required = false) String keyword) {

        User currentUser = userService.findUserbyJWT(jwt);

        Page<Post> posts = postService.getUserFeed(currentUser, page, size, sort, direction, keyword);

        return ResponseEntity.ok(new Response(true, "Feed fetched successfully", posts));
    }


}