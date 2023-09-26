package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.loaddbexamples;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.ComputerRoom;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.ComputerRoomReservation;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.User;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository.*;

import java.util.List;

@Component
@AllArgsConstructor
public class LoadDbExamples implements CommandLineRunner {

    private final ComputerRoomRepository computerRoomRepository;
    private final ComputerRoomReservationRepository computerRoomReservationRepository;
    private final SoftwareRepository softwareRepository;
    private final ComputerRepository computerRepository;
    private final RealUserRepository userRepository;

    private final List<ComputerRoom> computerRoomExamples;
    private final List<User> userExamples;

    @Override
    public void run(String... args) throws Exception {
        for (ComputerRoom room : computerRoomExamples) {
            if (!computerRoomRepository.existsByName(room.getName())) {
                computerRoomRepository.save(room);
            }
        }
        for (User user : userExamples) {
            if (userRepository.findByUsername(user.getFirstName(), user.getLastName()).isEmpty()) {
                userRepository.save(user);
            }
        }
        createReservations();
    }

    private void createReservations() {
        List<ComputerRoom> computerRooms = computerRoomRepository.findAll();
        List<User> users = userRepository.findAll();
        int userIndex = 0;
        int roomIndex = 0;
        for (int i = 0; i < 1500; i++) {
            ComputerRoom room = computerRooms.get(roomIndex);
            if (userIndex == users.size() -1) userIndex = 0;
            if (roomIndex == computerRooms.size() -1) roomIndex = 0;
            User user = users.get(userIndex);
            ComputerRoomReservation reservation = DbExamples.createExampleComputerRoomReservation(user, room);
            computerRoomReservationRepository.save(reservation);
            userIndex++;
            roomIndex++;
        }
    }
}
