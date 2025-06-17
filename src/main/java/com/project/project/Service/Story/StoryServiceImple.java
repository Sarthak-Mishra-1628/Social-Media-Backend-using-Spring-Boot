package com.project.project.Service.Story;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.project.Entity.*;
import com.project.project.Repository.StoryRepo;
import com.project.project.Service.User.UserService;


@Service
public class StoryServiceImple implements StoryService {

    @Autowired
    private StoryRepo storyRepo;

    @Autowired
    private UserService userService;


    @Override
    public Story createStory(Story story, User user) {

        story.setUser(user);
        story.setTimeStamp(LocalDateTime.now());

        return storyRepo.save(story);
    }


    @Override
    public List<Story> findStorybyUserId(Integer userId) throws Exception{
        User user = userService.GetByID(userId);

        if(user==null) throw new Exception("User Not Found");

        List<Story> allStories = storyRepo.findByUserId(userId);

        LocalDateTime cutOff = LocalDateTime.now().minusHours(24);
        List<Story> latestStories = allStories.stream().filter(
            story -> story.getTimeStamp().isAfter(cutOff)).toList();

        return latestStories;
    }

}
