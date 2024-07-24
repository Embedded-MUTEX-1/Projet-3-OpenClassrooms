package fr.lenny.backend.repository;

import fr.lenny.backend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {
    @Modifying
    @Query("update Location l set l.name = :name, l.surface = :surface, l.price = :price, l.updated_at = :updated_at where l.id = :id")
    void updateLocation(
            @Param(value = "id") long id,
            @Param(value = "name") String name,
            @Param(value = "surface") String surface,
            @Param(value = "price") Float price,
            @Param(value = "updated_at") Date updated_at
            );

    @Modifying
    @Query("update Location l set l.picture = :url where l.id = :id")
    void updateImageLocation(@Param(value = "id") long id, @Param(value = "url") String url);
}
