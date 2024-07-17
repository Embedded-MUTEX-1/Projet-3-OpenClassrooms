package fr.lenny.backend.controller;

import fr.lenny.backend.dto.MessageDTO;
import fr.lenny.backend.entity.Message;
import fr.lenny.backend.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public MessageDTO getMessage(@PathVariable Long id) {
        return service.getMessage(id);
    }

    @PostMapping("")
    public ResponseEntity<String> createMessage(@RequestBody @Valid MessageDTO message) {
        service.addMessage(message);
        return ResponseEntity.ok("Message send with success");
    }
}
