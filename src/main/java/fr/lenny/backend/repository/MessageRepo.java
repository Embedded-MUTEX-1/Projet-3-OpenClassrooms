package fr.lenny.backend.repository;

import fr.lenny.backend.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
}
