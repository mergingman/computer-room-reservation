package schule.bbs2.j2023.efi3b.computerroomreservation.controller.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationWeekOverviewQueryParameters implements Validatable {

    private String date;
    private String roomName;

    public Instant parseDate() {
        LocalDate date = LocalDate.parse(this.date);
        Instant instant = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        return instant;
    }
    @Override
    public void validate() {
        if (date == null) throw new IllegalArgumentException("Datum darf nicht null sein");
        if (date.split("-").length != 3) {
            throw new IllegalArgumentException("Datum muss im format yyyy-mm-dd vorliegen");
        }
        try {
            parseDate();
        }
        catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("date could not be parsed", ex);
        }
    }
}
