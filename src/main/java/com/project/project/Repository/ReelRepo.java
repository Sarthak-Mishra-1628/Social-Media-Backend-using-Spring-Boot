package com.project.project.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.project.Entity.Reels;
import java.util.*;

public interface ReelRepo extends JpaRepository<Reels , Integer>{

    public List<Reels> findByUserId(Integer userId);
    
}