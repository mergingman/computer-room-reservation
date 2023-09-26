package schule.bbs2.j2023.efi3b.computerroomreservation.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schule.bbs2.j2023.efi3b.computerroomreservation.controller.params.RoomReservationWeekOverviewQueryParameters;
import schule.bbs2.j2023.efi3b.computerroomreservation.dto.RoomReservationWeekOverview;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.ComputerRoomReservation;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository.ComputerRoomRepository;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository.ComputerRoomReservationRepository;
import schule.bbs2.j2023.efi3b.computerroomreservation.util.DateHelper;
import schule.bbs2.j2023.efi3b.computerroomreservation.util.TimeSlot;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Transactional
public class ComputerRoomReservationService {

    private final ComputerRoomReservationRepository computerRoomReservationRepository;
    private final ComputerRoomRepository computerRoomRepository;

    public RoomReservationWeekOverview getComputerRoomReservationWeekOverview(RoomReservationWeekOverviewQueryParameters params) {
        Instant parsedDate = params.parseDate();
        String roomName = params.getRoomName();
        Instant[] weekPeriod = DateHelper.getWeekPeriodFromInstant(parsedDate);
        computerRoomRepository
                .findByName(roomName)
                .orElseThrow(() -> new EntityNotFoundException("Es existiert zurzeit kein Raum mit dem Namen: " + roomName));
        List<ComputerRoomReservation> reservations =
                computerRoomReservationRepository
                        .findAllByStartTimeAfterAndEndTimeBeforeAndComputerRoomName(
                                weekPeriod[0],
                                weekPeriod[1],
                                roomName
                        );
        return createRoomReservationWeekOverviewDTOFromComputerRoomReservations(reservations, parsedDate, roomName);
    }

    private RoomReservationWeekOverview createRoomReservationWeekOverviewDTOFromComputerRoomReservations(
            List<ComputerRoomReservation> reservations,
            Instant selectedDate,
            String roomName
    ) {
        List<RoomReservationWeekOverview.Reservation> reservationList =
        reservations
                .stream()
                .map(this::createReservationDTOFromComputerRoomReservation)
                .toList();
        return new RoomReservationWeekOverview(
                roomName,
                selectedDate,
                selectedDate.atZone(DateHelper.ZONE_ID).getDayOfWeek().getDisplayName(TextStyle.FULL, DateHelper.LOCALE),
                reservationList
        );
    }

    private RoomReservationWeekOverview.Reservation createReservationDTOFromComputerRoomReservation(ComputerRoomReservation reservation) {
        String weekday =
                reservation
                        .getStartTime()
                        .atZone(ZoneId.of("Europe/Berlin"))
                        .getDayOfWeek()
                        .getDisplayName(TextStyle.FULL, Locale.GERMANY);
        String teacherLastName = reservation.getUser().getLastName();
        String lessonNr = TimeSlot.getLessonString(reservation.getStartTime(), reservation.getEndTime());
        return new RoomReservationWeekOverview.Reservation(
                weekday,
                teacherLastName,
                reservation.getStartTime(),
                reservation.getEndTime(),
                lessonNr,
                reservation.getStatus()
        );
    }
}
