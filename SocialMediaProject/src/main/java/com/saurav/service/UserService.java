package com.saurav.service;
import java.util.List;

import com.saurav.models.User;

public interface UserService {
public User registerUser(User user);
public User findUserById(Integer userId) throws Exception;
public User findUserByEmail(String email);
public User followUser(Integer userId1,Integer userId2) throws Exception;
public User updatUser(User user,Integer userId) throws Exception;
public List<User>searchUser(String query);
public User findUserByJwt(String jwt);
}
