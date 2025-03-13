package com.saurav.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurav.models.Story;
import com.saurav.models.User;
import com.saurav.repository.StryRepository;
import com.saurav.repository.UserRepository;
@Service
public class StoryServiceImplementation implements StoryService{
	@Autowired
private StryRepository storyRepository;
	@Autowired
	private UserService userService;
	@Override
	public Story createStory(Story story, User user) {
		// TODO Auto-generated method stub
		Story createdStory = new Story();
		createdStory.setCaption(story.getCaption());
		createdStory.setImage(story.getImage());
		createdStory.setUser(user);
		createdStory.setTimestamp(LocalDateTime.now());
		return storyRepository.save(createdStory);
	}

	@Override
	public List<Story> findByUserId(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		return storyRepository.findByUserId(userId);
		
	}

}
