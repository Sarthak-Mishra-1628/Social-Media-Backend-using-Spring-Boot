package com.project.project.Service.Reels;
import java.util.*;
import com.project.project.Entity.Reels;
import com.project.project.Entity.User;

public interface ReelService {
    public Reels createreel(Reels reel , User user);

    public List<Reels> getAllReels();

    public List<Reels> findReelByUserId(Integer userId) throws Exception;
}
