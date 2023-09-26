package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.ComputerRoom;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ComputerRoomRepository extends JpaRepository<ComputerRoom, Long> {

    @Query(value = "SELECT * FROM computer_room r WHERE r.name = :name", nativeQuery = true)
    Optional<ComputerRoom> findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM computer_room r", nativeQuery = true)
    List<ComputerRoom> findAll();

    boolean existsByName(String name);

}
