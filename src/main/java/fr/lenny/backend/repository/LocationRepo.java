package fr.lenny.backend.repository;

import fr.lenny.backend.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends CrudRepository<Location, Long> {
}
