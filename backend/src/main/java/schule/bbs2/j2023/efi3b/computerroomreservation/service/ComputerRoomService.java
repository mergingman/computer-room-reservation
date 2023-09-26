package schule.bbs2.j2023.efi3b.computerroomreservation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.ComputerRoom;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository.ComputerRoomRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ComputerRoomService {

    private final ComputerRoomRepository computerRoomRepository;

    public List<String> getRoomNames() {
        return computerRoomRepository
                .findAll()
                .stream()
                .map(ComputerRoom::getName)
                .toList();
    }
}
