package com.project.project.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.project.Entity.Story;

public interface StoryRepo extends JpaRepository<Story , Integer> {
    public List<Story> findByUserId(Integer userId);
}
