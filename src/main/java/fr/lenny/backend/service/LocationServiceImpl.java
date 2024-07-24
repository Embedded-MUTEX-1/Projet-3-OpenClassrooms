package fr.lenny.backend.service;

import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.dto.LocationUploadDTO;
import fr.lenny.backend.dto.LocationsDTO;
import fr.lenny.backend.entity.Location;
import fr.lenny.backend.exception.LocationNotFoundException;
import fr.lenny.backend.repository.LocationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    private LocationRepo repo;
    private ModelMapper modelMapper;
    private StorageService storageService;

    @Autowired
    public LocationServiceImpl(LocationRepo repo, ModelMapper modelMapper, StorageService storageService) {
        this.repo = repo;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }

    @Override
    public LocationsDTO getAllLocation() {
        List<LocationDTO> locations = modelMapper.map(repo.findAll(), new TypeToken<List<LocationDTO>>(){}.getType());
        return new LocationsDTO(locations);
    }

    @Override
    public LocationDTO getLocation(Long locationId) {
        return modelMapper.map(repo.findById(locationId).orElseThrow(LocationNotFoundException::new), LocationDTO.class);
    }

    @Override
    @Transactional
    public void addLocation(LocationUploadDTO location) throws IOException {
        location.setCreated_at(new Date());
        location.setUpdated_at(new Date());
        Location locationSaved = repo.save(modelMapper.map(location, Location.class));
        String path = storageService.store(location.getPicture(), String.format("image-%d", locationSaved.getId()));
        repo.updateImageLocation(locationSaved.getId(), path);
    }

    @Override
    public void updateLocation(Long id, LocationDTO locationDTO) {

        repo.updateLocation(
                id,
                locationDTO.getName(),
                locationDTO.getDescription(),
                locationDTO.getPrice(),
                new Date()
        );
    }
}
