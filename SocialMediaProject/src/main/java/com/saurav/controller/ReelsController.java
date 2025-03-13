package com.saurav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.models.Reels;
import com.saurav.models.User;
import com.saurav.service.ReelService;
import com.saurav.service.UserService;

@RestController
public class ReelsController {
	@Autowired
 private ReelService reelService;
	@Autowired
	private UserService userService;
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel,@RequestHeader("Authorization")String jwt) {
		User reqUser = userService.findUserByJwt(jwt);
		Reels CreatedReels = reelService.createReels(reel, reqUser);
		return CreatedReels;
	}
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		List<Reels>reels = reelService.findAllReels();
		return reels;
	}
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception{
		List<Reels>reels = reelService.findUserReels(userId);
		return reels;
	}
}
