package com.project.project.Service.Chat;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.project.Entity.*;
import com.project.project.Repository.ChatRepo;

@Service
public class ChatServiceImple implements ChatService{

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public Chat createChat(User reqUser, User user) throws Exception {
        Chat isExists = chatRepo.findChatbyUsersId(user,reqUser);
        if(isExists!=null) return isExists;

        Chat chat = new Chat();

        chat.getUsers().add(user);
        chat.getUsers().add(reqUser);
        chat.setTimeStamp(LocalDateTime.now());

        return chatRepo.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRepo.findById(chatId);

        if(chat.isEmpty()) throw new Exception("Chat not found");
        
        return chat.get();
    }

    // @Override
    // public List<Chat> findUsersChat(Integer userId) {
    //     return chatRepo.findByUsersId(userId);
    // }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepo.findByUsersId(userId);
    }
}
