package com.project.project.Payload;
import java.util.List;
import com.project.project.Entity.Post;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsResponse {

    private int totalPosts , totalLikeReceived , totalSavedPosts;

    private Post mostLikedPost;

    private List<Post> top3LikedPost;
}
