package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.Computer;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.ComputerRoomReservation;

import java.time.Instant;
import java.util.List;

@RepositoryRestResource
public interface ComputerRoomReservationRepository extends JpaRepository<ComputerRoomReservation, Long> {

    List<ComputerRoomReservation> findAllByStartTimeAfterAndEndTimeBeforeAndComputerRoomName(Instant startTime,Instant endTime, String computerRoomName);
}
