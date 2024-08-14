package fr.lenny.backend.service;

import fr.lenny.backend.dto.LocationDTO;
import fr.lenny.backend.dto.LocationUploadDTO;
import fr.lenny.backend.dto.LocationsDTO;
import fr.lenny.backend.entity.Location;
import fr.lenny.backend.entity.User;
import fr.lenny.backend.exception.LocationNotFoundException;
import fr.lenny.backend.repository.LocationRepo;
import fr.lenny.backend.repository.UserRepo;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    private final UserRepo userRepo;
    private LocationRepo repo;
    private ModelMapper modelMapper;
    private StorageService storageService;

    @Autowired
    public LocationServiceImpl(LocationRepo repo, ModelMapper modelMapper, StorageService storageService, UserRepo userRepo) {
        this.repo = repo;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
        this.userRepo = userRepo;

        TypeMap<Location, LocationDTO> typeMap = modelMapper.createTypeMap(Location.class, LocationDTO.class);
        typeMap.addMappings(mapper -> mapper.map(src -> {
            User owner = src.getOwner();
            Hibernate.initialize(owner);
            return owner.getId();
        }, LocationDTO::setOwner_id));
    }

    @Override
    public LocationsDTO getAllLocation() {
        List<LocationDTO> locations = modelMapper.map(repo.findAll(), new TypeToken<List<LocationDTO>>(){}.getType());
        return new LocationsDTO(locations);
    }

    @Override
    public LocationDTO getLocation(Long locationId) {
        Location location = repo.findById(locationId).orElseThrow(LocationNotFoundException::new);
        LocationDTO dto = modelMapper.map(location, LocationDTO.class);
        User user = location.getOwner();
        Hibernate.initialize(user);
        dto.setOwner_id(user.getId());
        return dto;
    }

    @Override
    @Transactional
    public void addLocation(LocationUploadDTO locationDTO, Principal principal) throws IOException {
        locationDTO.setCreated_at(new Date());
        locationDTO.setUpdated_at(new Date());

        Location location = modelMapper.map(locationDTO, Location.class);
        location.setOwner(userRepo.findByEmail(principal.getName()));

        location = repo.save(location);

        String path = storageService.store(locationDTO.getPicture(), String.format("image-%d", location.getId()));

        repo.updateImageLocation(location.getId(), path);
    }

    @Override
    public void updateLocation(Long id, LocationDTO locationDTO) {

        repo.updateLocation(
                id,
                locationDTO.getName(),
                locationDTO.getSurface(),
                locationDTO.getPrice(),
                locationDTO.getDescription(),
                new Date()
        );
    }
}
