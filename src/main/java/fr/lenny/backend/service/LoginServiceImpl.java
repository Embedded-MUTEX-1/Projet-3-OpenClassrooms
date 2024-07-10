package fr.lenny.backend.service;

import fr.lenny.backend.entity.User;
import fr.lenny.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private UserRepo repo;

    @Autowired
    public LoginServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public String authUser(String email, String password) {
        return "";
    }

    @Override
    public void registerUser(User user) {
        repo.save(user);
    }

    @Override
    public User getAuthUser(String email) {
        return repo.findByEmail();
    }
}
