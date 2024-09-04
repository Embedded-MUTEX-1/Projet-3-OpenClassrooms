package fr.lenny.backend.controller;

import fr.lenny.backend.dto.HttpMessageDTO;
import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.dto.LocationUploadDTO;
import fr.lenny.backend.dto.LocationsDTO;
import fr.lenny.backend.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Tag(name = "Location", description = "Methodes permetant des opérations CRUD sur les locations")
@RestController
@RequestMapping("/api/rentals")
public class LocationController {
    private LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }

    @Operation(summary = "Obtenir une location",
            description = "Permet d'obtenir une location à partir d'un Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne une location", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationDTO.class))),
            @ApiResponse(responseCode = "401", description = "Non autotirsé", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Unauthorized"))})
    })
    @GetMapping("/{id}")
    public LocationDTO getLocation(@PathVariable Long id) {
        return service.getLocation(id);
    }

    @Operation(summary = "Modifier une location",
            description = "Permet de modifier une location à partir d'un Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne OK", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Rental updated !"))}),
            @ApiResponse(responseCode = "401", description = "Non autotirsé", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Unauthorized"))})
    })
    @PutMapping("/{id}")
    public HttpMessageDTO updateLocation(@PathVariable Long id, @ModelAttribute @Valid LocationDTO location) {
        service.updateLocation(id, location);
        return new HttpMessageDTO("Rental updated !");
    }

    @Operation(summary = "Créer une location",
            description = "Permet de créer une location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne OK", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Rental created !"))}),
            @ApiResponse(responseCode = "401", description = "Non autotirsé", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Unauthorized"))})
    })
    @PostMapping(path = "")
    public HttpMessageDTO createLocation(@ModelAttribute @Valid LocationUploadDTO location, Principal principal) throws IOException {
        System.out.println(location.getPicture());
        service.addLocation(location, principal);
        return new HttpMessageDTO("Rental created !");
    }

    @Operation(summary = "Obtenir toutes les locations",
            description = "Permet d'obtenir toutes les locations de l'application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne des locations", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationsDTO.class))),
            @ApiResponse(responseCode = "401", description = "Non autotirsé", content = { @Content(mediaType = "text/plain", examples = @ExampleObject("Unauthorized"))})
    })
    @GetMapping("")
    public LocationsDTO getLocations() {
        return service.getAllLocation();
    }
}
