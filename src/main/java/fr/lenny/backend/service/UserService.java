package fr.lenny.backend.service;

import fr.lenny.backend.dto.RegisterDTO;
import fr.lenny.backend.dto.UserDTO;

public interface UserService {
    public UserDTO getUser(Long userId);
    public UserDTO getUserByEmail(String email);
    public void createUser(RegisterDTO registerDTO);
}
