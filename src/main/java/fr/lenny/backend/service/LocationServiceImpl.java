package fr.lenny.backend.service;

import fr.lenny.backend.entity.Location;
import fr.lenny.backend.exception.LocationNotFoundException;
import fr.lenny.backend.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    private LocationRepo repo;

    @Autowired
    public LocationServiceImpl(LocationRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Location> getAllLocation() {
        return repo.findAll();
    }

    @Override
    public Location getLocation(Long locationId) {
        return repo.findById(locationId).orElseThrow(LocationNotFoundException::new);
    }

    @Override
    public void addLocation(Location location) {
        repo.save(location);
    }
}
