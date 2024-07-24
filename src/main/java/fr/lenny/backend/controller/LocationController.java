package fr.lenny.backend.controller;

import fr.lenny.backend.dto.HttpMessageDTO;
import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.dto.LocationUploadDTO;
import fr.lenny.backend.dto.LocationsDTO;
import fr.lenny.backend.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/rentals")
public class LocationController {
    private LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public LocationDTO getLocation(@PathVariable Long id) {
        return service.getLocation(id);
    }

    @PutMapping("/{id}")
    public HttpMessageDTO updateLocation(@PathVariable Long id, @RequestBody @Valid LocationDTO location) {
        service.updateLocation(id, location);
        return new HttpMessageDTO("Rental updated !");
    }

    @PostMapping(path = "")
    public HttpMessageDTO createLocation(@ModelAttribute @Valid LocationUploadDTO location) throws IOException {
        System.out.println(location.getPicture());
        service.addLocation(location);
        return new HttpMessageDTO("Rental created !");
    }

    @GetMapping("")
    public LocationsDTO getLocations() {
        return service.getAllLocation();
    }
}
