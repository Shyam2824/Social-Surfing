package com.shyam.demo.contorolers;

import com.shyam.demo.models.Message;
import com.shyam.demo.models.User;
import com.shyam.demo.service.MessageService;
import com.shyam.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {

        User user= userService.findUserByJwt(jwt);
        Message message= messageService.createMessage(user,chatId,req);

        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List <Message> findChatsMessage( @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {

        User user= userService.findUserByJwt(jwt);
       List <Message> messages= messageService.findChatsMessages(chatId);

        return messages;
    }
}
