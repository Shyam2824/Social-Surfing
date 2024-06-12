package com.shyam.demo.contorolers;

import com.shyam.demo.models.Chat;
import com.shyam.demo.models.User;
import com.shyam.demo.request.CreateChatRequest;
import com.shyam.demo.service.ChatService;
import com.shyam.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat (@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {

        User reqUser=  userService.findUserByJwt(jwt);
        User user2= userService.findUserById(req.getUserId());
        Chat chat= chatService.createChat(reqUser, user2);

        return chat;
    }

    @GetMapping("/api/chats")
    public List <Chat> findUserChat (@RequestHeader("Authorization") String jwt){

        User user=  userService.findUserByJwt(jwt);
       List <Chat> chats= chatService.findUserChat(user.getId());

        return chats;
    }
}
