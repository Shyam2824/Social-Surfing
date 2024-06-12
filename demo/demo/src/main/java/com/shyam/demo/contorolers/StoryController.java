package com.shyam.demo.contorolers;

import com.shyam.demo.models.Story;
import com.shyam.demo.models.User;
import com.shyam.demo.service.StoryService;
import com.shyam.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){

        User reqUser= userService.findUserByJwt(jwt);

        Story createStory= storyService.createStory(story, reqUser);
        return createStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List <Story> findUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser= userService.findUserByJwt(jwt);

        List <Story> stories= storyService.findStoryByUserId(userId);
        return stories;
    }
}
