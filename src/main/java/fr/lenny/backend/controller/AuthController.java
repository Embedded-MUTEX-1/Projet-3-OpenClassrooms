package fr.lenny.backend.controller;

import fr.lenny.backend.dto.AuthDTO;
import fr.lenny.backend.dto.AuthResponseDTO;
import fr.lenny.backend.dto.RegisterDTO;
import fr.lenny.backend.dto.UserDTO;
import fr.lenny.backend.service.JwtService;
import fr.lenny.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

@Tag(name = "Authentification", description = "Methodes permetant le login et la créaction d'un compte")
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

    @Operation(summary = "Login",
            description = "Permet d'authentificatier un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne un token JWT", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Mauvais identifiant", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Bad credentials"))})
    })
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody @Valid AuthDTO authDTO) {
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
        return new AuthResponseDTO(jwtService.generateToken(authDTO.getEmail()));
    }

    @Operation(summary = "Créer un compte",
            description = "Permet créer un compte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne un token JWT", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Bad request"))})
    })
    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody @Valid RegisterDTO registerDTO) {
        userService.createUser(registerDTO);
        return new AuthResponseDTO(jwtService.generateToken(registerDTO.getEmail()));
    }

    @Operation(summary = "Obtenir les informations de l'authentifié",
            description = "Permet d'authentificatier un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne l'utilisateur authentifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthDTO.class))),
            @ApiResponse(responseCode = "401", description = "Non autorisé", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Unauthorized"))})
    })
    @GetMapping("/me")
    public UserDTO getMe(Authentication authentication) {
        return userService.getUserByEmail(authentication.getName());
    }
}
