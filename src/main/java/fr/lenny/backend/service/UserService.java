package fr.lenny.backend.service;

import fr.lenny.backend.dto.UserDTO;

public interface UserService {
    public UserDTO getUser(Long userId);
}
