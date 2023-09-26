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
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="public_seq")
    @SequenceGenerator(name = "public_seq", allocationSize = 1)
    private Long id;
    private String name;
    @ManyToOne
    private ComputerRoom computerRoom;
    @ManyToMany
    @JoinTable(
            name = "computer_software",
            joinColumns = @JoinColumn(name = "computer_id"),
            inverseJoinColumns = @JoinColumn(name = "software_id")
    )
    private List<Software> software;
    private String notes;
    private Boolean isOutOfService;

    public Computer() {}

    public void addSoftware(Software software) {
        if (software != null) {
            this.software.add(software);
            software.getComputers().add(this);
        }
    }

    public void removeSoftware(Software software) {
        if (software != null) {
            this.software.remove(software);
            software.getComputers().remove(this);
        }
    }
}
