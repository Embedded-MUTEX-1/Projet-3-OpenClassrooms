package fr.lenny.backend.service;

import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.entity.Location;

import java.util.List;

public interface LocationService {
    public List<LocationDTO> getAllLocation();
    public LocationDTO getLocation(Long locationId);
    public void addLocation(LocationDTO location);
    void updateLocation(Long id, LocationDTO location);
}
