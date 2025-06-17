package com.project.project.Service.Story;
import java.util.List;
import com.project.project.Entity.Story;
import com.project.project.Entity.User;

public interface StoryService {
    public Story createStory(Story story , User user);

    public List<Story> findStorybyUserId(Integer userId) throws Exception;
}