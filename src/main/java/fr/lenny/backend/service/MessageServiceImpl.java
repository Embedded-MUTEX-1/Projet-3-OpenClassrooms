package fr.lenny.backend.service;

import fr.lenny.backend.entity.Message;
import fr.lenny.backend.exception.MessageNotFoundException;
import fr.lenny.backend.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{
    private MessageRepo repo;

    @Autowired
    public MessageServiceImpl(MessageRepo repo) {
        this.repo = repo;
    }

    @Override
    public void addMessage(Message message) {
        repo.save(message);
    }

    @Override
    public Message getMessage(Long messageId) {
        return repo.findById(messageId).orElseThrow(MessageNotFoundException::new);
    }
}
