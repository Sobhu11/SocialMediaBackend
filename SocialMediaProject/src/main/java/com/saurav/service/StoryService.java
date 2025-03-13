package com.saurav.service;

import java.util.List;

import com.saurav.models.Story;
import com.saurav.models.User;

public interface StoryService {
public Story createStory(Story story,User user);
public List<Story>findByUserId(Integer userId) throws Exception;
}
