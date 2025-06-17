package com.project.project.Service.Analytics;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.project.Entity.*;
import com.project.project.Payload.AnalyticsResponse;
import com.project.project.Repository.*;

@Service
public class AnalyticsServiceImple implements AnalyticsService{

    @Autowired
    private PostRepo postRepo;

    
    @Autowired
    private UserRepo userRepo;

    public int cmp(Post p1 , Post p2){
        return p2.getLiked().size() - p1.getLiked().size();
    }

    @Override
    public AnalyticsResponse getAnalyticsResponse(int userId) {
        User user = userRepo.findById(userId).orElseThrow();

        List<Post> userPosts = postRepo.GetAllPostByUserID(userId);

        int totalPosts=userPosts.size();

        int totalLikeReceived=0;
        for(Post post : userPosts){
            totalLikeReceived += post.getLiked().size();
        }

        int totalSavedPosts=0;
        for(Post post : user.getSaved()){
            if(post.getUser().getId() == userId) totalSavedPosts++;
        }

        Post mostLikedPost=null;
        int mxLikes=-1;
        for(Post post : userPosts){
            int like=post.getLiked().size();
            if(like>mxLikes){
                mxLikes=like;
                mostLikedPost=post;
            }
        }

        List<Post> top3Liked = new ArrayList<>(userPosts);
        top3Liked.sort( (p1,p2) -> cmp(p1,p2));

        if(top3Liked.size()>3) top3Liked=top3Liked.subList(0,3);

        return new AnalyticsResponse(totalPosts,
            totalLikeReceived,
            totalSavedPosts,
            mostLikedPost,
            top3Liked);
    }
}