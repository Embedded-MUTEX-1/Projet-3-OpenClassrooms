package fr.lenny.backend.service;

import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.entity.Location;
import fr.lenny.backend.exception.LocationNotFoundException;
import fr.lenny.backend.repository.LocationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;
import java.util.Date;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    private LocationRepo repo;
    private ModelMapper modelMapper;

    @Autowired
    public LocationServiceImpl(LocationRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LocationDTO> getAllLocation() {
        return modelMapper.map(repo.findAll(), new TypeToken<List<LocationDTO>>(){}.getType());
    }

    @Override
    public LocationDTO getLocation(Long locationId) {
        return modelMapper.map(repo.findById(locationId).orElseThrow(LocationNotFoundException::new), LocationDTO.class);
    }

    @Override
    public void addLocation(LocationDTO location) {
        location.setCreatedAt(new Date());
        repo.save(modelMapper.map(location, Location.class));
    }

    @Override
    public void updateLocation(Long id, LocationDTO locationDTO) {

        repo.updateLocation(
                id,
                locationDTO.getName(),
                locationDTO.getDescription(),
                locationDTO.getPrice(),
                locationDTO.getUpdatedAt()
        );
    }
}
