package com.project.project.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.project.Entity.*;
import com.project.project.Service.Message.MessageService;
import com.project.project.Service.User.UserService;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("premium/Msg/chat/{ChatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt ,  @RequestBody Message message , @PathVariable Integer ChatId) throws Exception{
        User user = userService.findUserbyJWT(jwt);

        return messageService.createMessage(user, ChatId, message);
    }


    @GetMapping("/premium/messages/{ChatId}")
    public List<Message> GetAllMsgs(@RequestHeader("Authorization") String jwt , @PathVariable Integer ChatId){
        return messageService.findChatMessages(ChatId);
    }

}
