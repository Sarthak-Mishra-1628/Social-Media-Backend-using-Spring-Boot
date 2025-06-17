package com.project.project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.project.project.Entity.*;
import com.project.project.Service.Reels.ReelService;
import com.project.project.Service.User.UserService;

@RestController
public class ReelController {
    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;


    @GetMapping("/premium/reels")
    public List<Reels> getAllReels(){
        return reelService.getAllReels();
    }


    @PostMapping("/premium/reel")
    public Reels createReels(@RequestBody Reels reel , @RequestHeader("Authorization") String jwt){
        User user = userService.findUserbyJWT(jwt);
        reel.setUser(user);

        return reelService.createreel(reel, user);
    }


    @GetMapping("/premium/reel/user/{userid}")
    public List<Reels> getAllReelsByUserId(@PathVariable Integer userid) throws Exception{
        return reelService.findReelByUserId(userid);
    }
}
