package fr.lenny.backend.service;

import fr.lenny.backend.dto.RegisterDTO;
import fr.lenny.backend.dto.UserDTO;
import fr.lenny.backend.entity.User;
import fr.lenny.backend.exception.UserAlreadyExists;
import fr.lenny.backend.exception.UserNotFoundException;
import fr.lenny.backend.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = repo.findByEmail(email);
        if(user == null)
            throw new UserNotFoundException();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public void createUser(RegisterDTO registerDTO) {
        if(repo.existsByEmail(registerDTO.getEmail()))
            throw new UserAlreadyExists();

        User user = modelMapper.map(registerDTO, User.class);
        user.setPassword(BCrypt.hashpw(registerDTO.getPassword(), BCrypt.gensalt(12)));
        user.setCreated_at(new Date());
        user.setUpdated_at(new Date());
        repo.save(user);
    }
}
