package fr.lenny.backend.service;

import fr.lenny.backend.dto.MessageDTO;

public interface MessageService {
    public void addMessage(MessageDTO message);
    public MessageDTO getMessage(Long messageId);
}
