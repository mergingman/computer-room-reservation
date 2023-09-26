package schule.bbs2.j2023.efi3b.computerroomreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class RoomReservationWeekOverview {

    private String roomName;
    private Instant date;
    private String weekDay;
    private List<Reservation> weekReservationList;

    @Data
    @AllArgsConstructor
    public static class Reservation {

        private String weekday;
        private String teacher;
        private Instant startTime;
        private Instant endTime;
        private String lessonNr;
        private String status;
    }
}
