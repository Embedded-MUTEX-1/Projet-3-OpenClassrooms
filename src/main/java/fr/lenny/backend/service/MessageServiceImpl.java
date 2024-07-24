package fr.lenny.backend.service;

import fr.lenny.backend.dto.MessageDTO;
import fr.lenny.backend.entity.Message;
import fr.lenny.backend.exception.MessageNotFoundException;
import fr.lenny.backend.repository.MessageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{
    private MessageRepo repo;
    private ModelMapper modelMapper;

    @Autowired
    public MessageServiceImpl(MessageRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addMessage(MessageDTO message) {
        repo.insertWithIds(message.getMessage(), message.getRental_id(), message.getUser_id());
    }

    @Override
    public MessageDTO getMessage(Long messageId) {
        return modelMapper.map(repo.findById(messageId).orElseThrow(MessageNotFoundException::new), MessageDTO.class);
    }
}
