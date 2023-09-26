package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
public class ComputerRoom implements Serializable {

    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="public_seq")
    @SequenceGenerator(name = "public_seq", allocationSize = 1)
    private Long id;
    @OneToMany(mappedBy = "computerRoom")
    private List<Computer> computers = new ArrayList<>();
    @OneToMany(mappedBy = "computerRoom")
    private List<ComputerRoomReservation> computerRoomReservations;
    private String name;

    public ComputerRoom() {
    }

    public void addComputer(Computer computer) {
        if (computer != null) {
            computers.add(computer);
            computer.setComputerRoom(this);
        }
    }

    public void removeComputer(Computer computer) {
        if (computer != null) {
            computers.remove(computer);
            computer.setComputerRoom(null);
        }
    }

    public void addComputerRoomReservation(ComputerRoomReservation reservation) {
        if (reservation != null) {
            computerRoomReservations.add(reservation);
            reservation.setComputerRoom(this);
        }
    }

    public void removeComputerRoomReservation(ComputerRoomReservation reservation) {
        if (reservation != null) {
            computerRoomReservations.remove(reservation);
            reservation.setComputerRoom(null);
        }
    }
}
