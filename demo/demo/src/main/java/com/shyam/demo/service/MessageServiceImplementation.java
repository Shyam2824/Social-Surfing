package com.shyam.demo.service;

import com.shyam.demo.models.Chat;
import com.shyam.demo.models.Message;
import com.shyam.demo.models.User;
import com.shyam.demo.repository.ChatRepository;
import com.shyam.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(User   user, Integer chatId, Message req) throws Exception {

        Chat chat= chatService.findChatById(chatId);
        Message message= new Message();

        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

        Message saveMessage =messageRepository.save(message);
        chat.getMessages().add(saveMessage);
        chatRepository.save(chat);
        return saveMessage ;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {

        Chat chat= chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
