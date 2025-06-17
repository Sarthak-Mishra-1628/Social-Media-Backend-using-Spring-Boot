package com.project.project.Service.Post;
import com.project.project.Entity.*;
import java.util.*;
import org.springframework.data.domain.Page;

public interface PostService {
    Post createPost(Post post , Integer id) throws Exception;
    
    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> GetAllPostByUserID(Integer id);

    Post GetPostByPostID(Integer id);

    List<Post> GetAllPost();

    Post savedPost(Integer postId, Integer userId) throws Exception;

    Post likePost(Integer postId, Integer userId) throws Exception;

    Page<Post> getUserFeed(User currentUser, int page, int size, String sort, String direction, String keyword);

}
