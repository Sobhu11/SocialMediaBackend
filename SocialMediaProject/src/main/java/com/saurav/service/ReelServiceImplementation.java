package com.saurav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurav.models.Reels;
import com.saurav.models.User;
import com.saurav.repository.ReelsRepository;
@Service
public class ReelServiceImplementation implements ReelService {
	@Autowired
      private ReelsRepository reelsRepository;
	@Autowired
	private UserService userService;
	@Override
	public Reels createReels(Reels reel, User user) {
		// TODO Auto-generated method stub
		Reels createReel = new Reels();
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReels(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		userService.findUserById(userId);
		
		return reelsRepository.findByUserId(userId);
	}

}
