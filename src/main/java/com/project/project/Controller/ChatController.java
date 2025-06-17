package com.project.project.Controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.project.Entity.Chat;
import com.project.project.Entity.User;
import com.project.project.Request.CreateChatRequest;
import com.project.project.Service.Chat.ChatService;
import com.project.project.Service.User.UserService;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/premium/NewChat")
    public Chat createChat(@RequestHeader("Authorization") String jwt , @RequestBody CreateChatRequest req) throws Exception{
        User Requser = userService.findUserbyJWT(jwt);
        User user = userService.GetByID(req.getUserId());
        Chat chat = chatService.createChat(Requser , user);
        
        return chat;
    }


    @GetMapping("/premium/Chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserbyJWT(jwt);

        return chatService.findUsersChat(user.getId());
    }


}
