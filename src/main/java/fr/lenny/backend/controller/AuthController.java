package fr.lenny.backend.controller;

import fr.lenny.backend.dto.AuthDTO;
import fr.lenny.backend.dto.AuthResponseDTO;
import fr.lenny.backend.dto.RegisterDTO;
import fr.lenny.backend.dto.UserDTO;
import fr.lenny.backend.service.JwtService;
import fr.lenny.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private JwtService jwtService;
    private AuthenticationProvider authenticationProvider;
    private UserService userService;

    @Autowired
    public AuthController(JwtService jwtService, AuthenticationProvider authenticationProvider, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody @Valid AuthDTO authDTO) {
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
        return new AuthResponseDTO(jwtService.generateToken(authDTO.getEmail()));
    }

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody @Valid RegisterDTO registerDTO) {
        userService.createUser(registerDTO);
        return new AuthResponseDTO(jwtService.generateToken(registerDTO.getEmail()));
    }

    @GetMapping("/me")
    public UserDTO getMe(Authentication authentication) {
        return userService.getUserByEmai(authentication.getName());
    }
}
