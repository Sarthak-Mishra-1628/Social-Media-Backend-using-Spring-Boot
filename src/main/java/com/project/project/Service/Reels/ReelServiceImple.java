package com.project.project.Service.Reels;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.project.Entity.Reels;
import com.project.project.Entity.User;
import com.project.project.Repository.ReelRepo;
import com.project.project.Service.User.UserService;

@Service
public class ReelServiceImple implements ReelService{

    @Autowired
    private ReelRepo reelRepo;

    @Autowired
    private UserService userService;


    @Override
    public Reels createreel(Reels reel, User user) {
        return reelRepo.save(reel);
    }

    @Override
    public List<Reels> getAllReels() {
        return reelRepo.findAll();
    }

    @Override
    public List<Reels> findReelByUserId(Integer userId) throws Exception {
        User user = userService.GetByID(userId);

        if(user==null) throw new Exception("Error");

        return reelRepo.findByUserId(userId);
    }

}
