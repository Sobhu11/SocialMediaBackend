package com.saurav.service;

import java.util.List;


import com.saurav.models.Chat;
import com.saurav.models.User;

public interface ChatService {
public  Chat createChat(User reqUser,User user2);
public Chat findChatById(Integer chatId) throws Exception;
public List<Chat>findUserChat(Integer userId);

}
