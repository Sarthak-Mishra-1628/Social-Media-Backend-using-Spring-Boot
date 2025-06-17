package com.project.project.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.project.Entity.Comment;

public interface CommentRepo extends JpaRepository<Comment , Integer> {

}
