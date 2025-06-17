package com.project.project.Service.Chat;
import java.util.List;
import com.project.project.Entity.*;

public interface ChatService {
    public Chat createChat(User reqUser , User user) throws Exception;
    
    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}