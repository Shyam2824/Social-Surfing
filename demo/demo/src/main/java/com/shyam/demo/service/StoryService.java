package com.shyam.demo.service;

import com.shyam.demo.models.Story;
import com.shyam.demo.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory (Story story, User user);
    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
