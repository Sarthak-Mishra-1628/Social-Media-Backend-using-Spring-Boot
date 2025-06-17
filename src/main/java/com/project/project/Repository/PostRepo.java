package com.project.project.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;
import com.project.project.Entity.Post;
import java.util.*;

public interface PostRepo  extends JpaRepository<Post,Integer>{

    @Query("select p from Post p where p.user.id=:id")
    List<Post> GetAllPostByUserID(@Param("id") Integer id);

    Page<Post> findByUserIdIn(List<Integer> userIds, Pageable pageable);

    Page<Post> findByUserIdInAndCaptionContainingIgnoreCase(List<Integer> userIds, String keyword, Pageable pageable);

}