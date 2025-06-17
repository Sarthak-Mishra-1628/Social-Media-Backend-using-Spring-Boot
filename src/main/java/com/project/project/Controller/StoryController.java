package com.project.project.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.project.Entity.*;
import com.project.project.Service.Story.StoryService;
import com.project.project.Service.User.UserService;

@RestController
public class StoryController {
    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/premium/story")
    public Story createStory(@RequestBody Story story , @RequestHeader("Authorization") String jwt){
        User user = userService.findUserbyJWT(jwt);

        return storyService.createStory(story, user);
    }


    @GetMapping("/premium/story/{userId}")
    public List<Story> getAllStory(@PathVariable int userId) throws Exception{
        return storyService.findStorybyUserId(userId);
    }
}
