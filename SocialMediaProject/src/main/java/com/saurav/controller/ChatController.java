package com.saurav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.models.Chat;
import com.saurav.models.User;
import com.saurav.request.CreateChatRequest;
import com.saurav.service.ChatService;
import com.saurav.service.UserService;

@RestController
public class ChatController {
	@Autowired
private ChatService chatService;
	@Autowired
	private UserService userService;
@PostMapping("/api/chats")
public Chat createChat(@RequestBody CreateChatRequest req, @RequestHeader("Authorization")String jwt) throws Exception {
	User reqUser = userService.findUserByJwt(jwt);
	User user2 = userService.findUserById(req.getUserId());
	Chat chat = chatService.createChat(reqUser, user2);
	return chat;
}
@GetMapping("/api/chats")
public List<Chat> findUserChat(@RequestHeader("Authorization")String jwt) {
	User user = userService.findUserByJwt(jwt);
	List<Chat> chats = chatService.findUserChat(user.getId());
	return chats;
}
}
