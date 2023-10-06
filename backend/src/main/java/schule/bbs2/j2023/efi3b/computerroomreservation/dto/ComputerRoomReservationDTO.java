package schule.bbs2.j2023.efi3b.computerroomreservation.dto;

import lombok.Data;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.ComputerRoomReservation;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
public class ComputerRoomReservationDTO {

    private String weekday;
    private String teacher;
    private Instant startTime;
    private Instant endTime;
    private String lessonNr;
    private String status;
    private String roomName;
    private Integer availableComputers;
    private String className;

    public ComputerRoomReservationDTO(ComputerRoomReservation computerRoomReservation) {
        this.weekday = computerRoomReservation
                .getStartTime()
                .atZone(ZoneId.of("Europe/Berlin"))
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.GERMANY);
        this.teacher = computerRoomReservation.getUser().getLastName();
        this.startTime = computerRoomReservation.getStartTime();
        this.endTime = computerRoomReservation.getEndTime();
        this.lessonNr = computerRoomReservation.getLessonNr();
        this.status = computerRoomReservation.getStatus();
        this.roomName = computerRoomReservation.getComputerRoom().getName();
        this.availableComputers = computerRoomReservation.getComputerRoom().getComputers().size();
        this.className = computerRoomReservation.getClassName();
    }
}
