package fr.lenny.backend.service;

import fr.lenny.backend.entity.Location;

import java.util.List;

public interface LocationService {
    public List<Location> getAllLocation();
    public Location getLocation(int locationId);
    public void addLocation(Location location);
}
