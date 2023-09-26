package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.Computer;

@RepositoryRestResource
public interface ComputerRepository extends PagingAndSortingRepository<Computer, Long> {

    boolean existsByName(String name);
}
