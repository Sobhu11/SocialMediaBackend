package com.saurav.service;

import java.util.List;

import com.saurav.models.Reels;
import com.saurav.models.User;

public interface ReelService {
public Reels createReels(Reels reel,User user);
public List<Reels>findAllReels();
public List<Reels>findUserReels(Integer userId) throws Exception;

}
