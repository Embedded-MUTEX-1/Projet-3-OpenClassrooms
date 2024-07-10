package fr.lenny.backend.service;


import fr.lenny.backend.entity.User;

public interface LoginService {
    public String authUser(String email, String password);
    public void registerUser(User user);
    public User getAuthUser(String email);
}
