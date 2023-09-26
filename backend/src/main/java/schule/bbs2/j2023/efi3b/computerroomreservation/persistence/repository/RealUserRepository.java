package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.User;

import java.util.Optional;

@RepositoryRestResource(itemResourceRel = "realuser", collectionResourceRel = "realusers", path="realusers")
public interface RealUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USERS u " +
            "WHERE u.first_name = :firstName " +
            "AND u.last_name = :lastName",
            nativeQuery = true)
    Optional<User> findByUsername(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
    );
}
