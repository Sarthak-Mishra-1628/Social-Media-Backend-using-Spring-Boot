package com.project.project.Service.Message;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.project.project.Entity.*;
import com.project.project.Repository.ChatRepo;
import com.project.project.Repository.MessageRepo;
import com.project.project.Service.Chat.ChatService;

@Service
public class MessageServiceImple implements MessageService{

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public Message createMessage(User user, Integer chatId, Message message) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        message.setChat(chat);
        message.setTimeStamp(LocalDateTime.now());
        message.setUser(user);

        Message savedMessage = messageRepo.save(message);
        chat.getMessages().add(savedMessage);
        chatRepo.save(chat);

        return savedMessage;
    }

    @Override
    public List<Message> findChatMessages(Integer chatId){
        return messageRepo.findByChatId(chatId);
    }
}
