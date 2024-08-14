package fr.lenny.backend.service;

import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.dto.LocationUploadDTO;
import fr.lenny.backend.dto.LocationsDTO;

import java.io.IOException;
import java.security.Principal;

public interface LocationService {
    public LocationsDTO getAllLocation();
    public LocationDTO getLocation(Long locationId);
    public void addLocation(LocationUploadDTO location, Principal principal) throws IOException;
    void updateLocation(Long id, LocationDTO location);
}
