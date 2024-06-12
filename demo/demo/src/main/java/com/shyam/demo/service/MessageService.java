package com.shyam.demo.service;

import com.shyam.demo.models.Chat;
import com.shyam.demo.models.Message;
import com.shyam.demo.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage (User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
