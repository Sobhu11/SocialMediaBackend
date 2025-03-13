package com.saurav.service;

import java.util.List;

//import com.saurav.models.Chat;
import com.saurav.models.Message;
import com.saurav.models.User;

public interface MessageService {
public  Message createMessage(User user,Integer chatId,Message req) throws Exception;
public List<Message>findChatMessages(Integer chatId) throws Exception;
}
