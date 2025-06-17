package com.project.project.Service.Post;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.project.project.Entity.*;
import com.project.project.Repository.*;
import com.project.project.Service.User.UserService;

@Service
public class PostServiceImple implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;
    

    @Override
    public Post createPost(Post post, Integer id) throws Exception {
        User user = userService.GetByID(id);

        Post newPost = new Post();

        newPost.setCaption(post.getCaption());
        newPost.setId(post.getId());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreated(LocalDateTime.now());
        newPost.setUser(user);

        postRepo.save(newPost);

        return newPost;
    }


    @Override
    public List<Post> GetAllPost() {
        List<Post> posts = postRepo.findAll();
        return posts;
    }

    @Override
    public List<Post> GetAllPostByUserID(Integer id) {
        return postRepo.GetAllPostByUserID(id);
    }

    @Override
    public Post GetPostByPostID(Integer id) {
        Optional<Post> post = postRepo.findById(id); 
        return post.orElse(null);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception{
        Post post = GetPostByPostID(postId);
        User user = userService.GetByID(userId);

        if(post.getUser().getId()!=user.getId()) throw new Exception("Can't Delete");

        postRepo.delete(post);

        return "Done";
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception{
        Post post = GetPostByPostID(postId);
        User user = userService.GetByID(userId);

        if(post.getLiked().contains(user)) post.getLiked().remove(user);
        else post.getLiked().add(user);

        return postRepo.save(post);
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception{
        Post post = GetPostByPostID(postId);
        User user = userService.GetByID(userId);

        if(user.getSaved().contains(post)) user.getSaved().remove(post);
        else user.getSaved().add(post);
        userRepo.save(user);

        return post;
    }


    @Override
    public Page<Post> getUserFeed(User currentUser, int page, int size, String sort, String direction, String keyword) {
        List<Integer> followings = new ArrayList<>(currentUser.getFollowings());
        followings.add(currentUser.getId());

        Sort sortOrder = direction.equalsIgnoreCase("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        Pageable pageable = PageRequest.of(page, size, sortOrder);

        if (keyword != null && !keyword.isEmpty()) {
            return postRepo.findByUserIdInAndCaptionContainingIgnoreCase(followings, keyword, pageable);
        }

        return postRepo.findByUserIdIn(followings, pageable);
    }

}