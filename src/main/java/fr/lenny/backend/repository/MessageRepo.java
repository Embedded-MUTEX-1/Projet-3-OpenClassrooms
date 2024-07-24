package fr.lenny.backend.repository;

import fr.lenny.backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    @Modifying
    @Query(value = "insert into Message (message, location_id, user_id) VALUES (:message, :rental_id, :user_id)", nativeQuery = true)
    @Transactional
    public void insertWithIds(@Param(value = "message") String message, @Param(value = "rental_id") long rental_id, @Param(value = "user_id") long user_id);
}
