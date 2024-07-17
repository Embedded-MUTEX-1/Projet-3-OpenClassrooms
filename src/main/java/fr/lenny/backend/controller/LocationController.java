package fr.lenny.backend.controller;

import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> updateLocation(@PathVariable Long id, @RequestBody @Valid LocationDTO location) {
        service.updateLocation(id, location);
        return ResponseEntity.ok("Rental updated !");
    }

    @PostMapping("")
    public ResponseEntity<String> createLocation(@RequestBody @Valid LocationDTO location) {
        service.addLocation(location);
        return ResponseEntity.ok("Rental created !");
    }

    @GetMapping("")
    public List<LocationDTO> getLocations() {
        return service.getAllLocation();
    }
}
