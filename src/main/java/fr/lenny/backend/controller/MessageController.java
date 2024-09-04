package fr.lenny.backend.controller;

import fr.lenny.backend.dto.HttpMessageDTO;
import fr.lenny.backend.dto.MessageDTO;
import fr.lenny.backend.entity.Message;
import fr.lenny.backend.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Message", description = "Methodes permetant des opérations CRUD sur les messages")
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @Operation(summary = "Obtenir un message",
            description = "Permet d'obtenir un message posté sur une location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne un message d'utilisateur", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDTO.class))),
            @ApiResponse(responseCode = "401", description = "Non autotirsé", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Unauthorized"))})
    })
    @GetMapping("/{id}")
    public MessageDTO getMessage(@PathVariable Long id) {
        return service.getMessage(id);
    }

    @Operation(summary = "Créer un message",
            description = "Permet de publié un message sur une location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne un message d'utilisateur"),
            @ApiResponse(responseCode = "401", description = "Non autotirsé", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Unauthorized"))})
    })
    @PostMapping("")
    public HttpMessageDTO createMessage(@RequestBody @Valid MessageDTO message) {
        service.addMessage(message);
        return new HttpMessageDTO("Message send with success");
    }
}
