package fr.lenny.backend.repository;

import fr.lenny.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
}
