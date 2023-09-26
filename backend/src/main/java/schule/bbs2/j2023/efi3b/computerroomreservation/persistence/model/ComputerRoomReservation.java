package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
public class ComputerRoomReservation implements Serializable {

    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="public_seq")
    @SequenceGenerator(name = "public_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private ComputerRoom computerRoom;
    private Instant startTime;
    private Instant endTime;
    private String className;
    private String status;
    private String lessonNr;
    private Boolean isDoubleLesson;

    public ComputerRoomReservation() {
    }

}
