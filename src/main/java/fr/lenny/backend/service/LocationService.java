package fr.lenny.backend.service;

import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.dto.LocationUploadDTO;
import fr.lenny.backend.dto.LocationsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LocationService {
    public LocationsDTO getAllLocation();
    public LocationDTO getLocation(Long locationId);
    public void addLocation(LocationUploadDTO location) throws IOException;
    void updateLocation(Long id, LocationDTO location);
}
