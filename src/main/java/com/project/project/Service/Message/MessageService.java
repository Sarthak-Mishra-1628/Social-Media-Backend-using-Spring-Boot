package com.project.project.Service.Message;

import java.util.List;

import com.project.project.Entity.*;

public interface MessageService {
    public Message createMessage(User user , Integer chatId , Message message) throws Exception;    

    public List<Message> findChatMessages(Integer chatId);
} 
