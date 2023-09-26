package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import schule.bbs2.j2023.efi3b.computerroomreservation.ComputerRoomReservationApplication;

import java.io.Serializable;
import java.util.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="public_seq")
    @SequenceGenerator(name = "public_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "pw")
    private String password;
    private String role;
    private Boolean isLocked;
    @OneToMany(mappedBy = "user")
    private List<ComputerRoomReservation> computerRoomReservations = new ArrayList<>();

    public User() {
    }

    public void addComputerRoomReservation(ComputerRoomReservation reservation) {
        if (reservation != null) {
            computerRoomReservations.add(reservation);
            reservation.setUser(this);
        }
    }

    public void removeComputerRoomReservation(ComputerRoomReservation reservation) {
        if (reservation != null) {
            computerRoomReservations.remove(reservation);
            reservation.setUser(null);
        }
    }

    public String getUsername() {
        return String.format(
                "%s.%s",
                firstName,
                lastName
        );
    }
}
