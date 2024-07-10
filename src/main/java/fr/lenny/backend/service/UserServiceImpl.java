package fr.lenny.backend.service;

import fr.lenny.backend.entity.User;
import fr.lenny.backend.exception.UserNotFoundException;
import fr.lenny.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo repo;

    @Autowired
    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User getUser(Long userId) {
        return repo.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
