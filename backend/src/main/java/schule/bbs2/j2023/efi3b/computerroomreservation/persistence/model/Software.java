package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Software implements Serializable {

    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="public_seq")
    @SequenceGenerator(name = "public_seq", allocationSize = 1)
    private Long id;
    private String version;
    private String name;
    @ManyToMany(mappedBy = "software")
    private List<Computer> computers;

    public Software() {
    }
}
