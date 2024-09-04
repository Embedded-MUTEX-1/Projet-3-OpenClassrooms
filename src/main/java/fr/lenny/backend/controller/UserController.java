package fr.lenny.backend.controller;

import fr.lenny.backend.dto.UserDTO;
import fr.lenny.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Utilisateur", description = "Methode permetant d'obtenir les informations d'un utilisateur")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Obtenir un utilisateur",
            description = "Permet d'obtenir les informations d'un utilisateur d'un utilisateur à partir d'un Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne les informations de l'utilisateur", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "401", description = "Non autotirsé", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Unauthorized"))})
    })
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return service.getUser(id);
    }
}
