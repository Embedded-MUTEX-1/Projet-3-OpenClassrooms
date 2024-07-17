package fr.lenny.backend.service;

import fr.lenny.backend.dto.UserDTO;
import fr.lenny.backend.entity.User;
import fr.lenny.backend.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private UserRepo repo;
    private ModelMapper modelMapper;

    @Autowired
    public LoginServiceImpl(UserRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public String authUser(String email, String password) {
        return "";
    }

    @Override
    public void registerUser(UserDTO user) {
        repo.save(modelMapper.map(user, User.class));
    }

    @Override
    public UserDTO getAuthUser(String email) {
        return modelMapper.map(repo.findByEmail(email), UserDTO.class);
    }
}
