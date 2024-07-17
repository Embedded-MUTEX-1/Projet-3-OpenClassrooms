package fr.lenny.backend.service;


import fr.lenny.backend.dto.UserDTO;

public interface LoginService {
    public String authUser(String email, String password);
    public void registerUser(UserDTO user);
    public UserDTO getAuthUser(String email);
}
