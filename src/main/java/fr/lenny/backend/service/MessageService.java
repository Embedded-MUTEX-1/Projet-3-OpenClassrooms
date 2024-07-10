package fr.lenny.backend.service;

import fr.lenny.backend.entity.Message;

public interface MessageService {
    public void addMessage(Message message);
    public Message getMessage(int messageId);
}
