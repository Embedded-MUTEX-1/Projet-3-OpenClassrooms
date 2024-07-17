package fr.lenny.backend.service;

import fr.lenny.backend.dto.UserDTO;
import fr.lenny.backend.entity.User;
import fr.lenny.backend.exception.UserNotFoundException;
import fr.lenny.backend.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo repo;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO getUser(Long userId) {
        return modelMapper.map(repo.findById(userId).orElseThrow(UserNotFoundException::new), UserDTO.class);
    }
}
