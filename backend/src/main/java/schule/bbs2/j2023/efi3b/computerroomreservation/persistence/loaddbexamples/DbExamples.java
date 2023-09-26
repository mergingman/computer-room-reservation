package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.loaddbexamples;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.*;
import schule.bbs2.j2023.efi3b.computerroomreservation.security.authorization.Role;
import schule.bbs2.j2023.efi3b.computerroomreservation.service.ComputerRoomReservationStatus;
import schule.bbs2.j2023.efi3b.computerroomreservation.util.DateHelper;
import schule.bbs2.j2023.efi3b.computerroomreservation.util.TimeSlot;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DbExamples {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public List<ComputerRoom> computerRoomExamples() {
        List<Computer> computers = getComputerWithSoftwareList(getSoftwareList());
        return List.of(
                new ComputerRoom(null, computers, null, "Raum 1-01"),
                new ComputerRoom(null, computers, null, "Raum 1-02"),
                new ComputerRoom(null, computers, null, "Raum 1-03"),
                new ComputerRoom(null, computers, null, "Raum 1-04"),
                new ComputerRoom(null, computers, null, "Raum 1-05"),
                new ComputerRoom(null, computers, null, "Raum 1-09"),
                new ComputerRoom(null, computers, null, "Raum 1-11"),
                new ComputerRoom(null, computers, null, "Raum 1-02"),
                new ComputerRoom(null, computers, null, "Raum 2-05"),
                new ComputerRoom(null, computers, null, "Raum 2-08"),
                new ComputerRoom(null, computers, null, "Raum 2-11"),
                new ComputerRoom(null, computers, null, "Raum 2-12"),
                new ComputerRoom(null, computers, null, "Raum 2-13"),
                new ComputerRoom(null, computers, null, "Raum 2-19"),
                new ComputerRoom(null, computers, null, "Raum 2-24"),
                new ComputerRoom(null, computers, null, "Raum 2-34"),
                new ComputerRoom(null, computers, null, "Raum 3-01"),
                new ComputerRoom(null, computers, null, "Raum 3-05"),
                new ComputerRoom(null, computers, null, "Raum 3-09"),
                new ComputerRoom(null, computers, null, "Raum 3-11"),
                new ComputerRoom(null, computers, null, "Raum 3-17"),
                new ComputerRoom(null, computers, null, "Raum 3-18"),
                new ComputerRoom(null, computers, null, "Raum 3-21"),
                new ComputerRoom(null, computers, null, "Raum 3-25")
        );
    }

    @Bean
    public List<User> getUserList() {
        String encodedPass = passwordEncoder.encode("password");
        return List.of(
                new User(null, "Hans", "Meier", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Max", "Mustermann", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Sabine", "Garte", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Nadine", "Müller", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Peter", "Fox", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Hansi", "Flick", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Udo", "Lindenberg", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Lisa", "Graf", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Gisela", "Fischer", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Roman", "Otto", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Peter", "Hohlmann", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Tamara", "Becker", encodedPass, Role.ROLE_TEACHER.toString(), false, null),
                new User(null, "Bernd", "Schröder", encodedPass, Role.ROLE_TEACHER.toString(), false, null)
        );
    }

    public static ComputerRoomReservation createExampleComputerRoomReservation(User user, ComputerRoom computerRoom) {
        LocalTime[] timeSlot = getRandomTimeSlot();
        Instant randomDay = getRandomDay();
        Instant randomDayCopy = Instant.ofEpochMilli(randomDay.toEpochMilli());
        Instant startTime = randomDay.atZone(DateHelper.ZONE_ID).with(timeSlot[0]).toInstant();
        Instant endTime = randomDayCopy.atZone(DateHelper.ZONE_ID).with(timeSlot[1]).toInstant();
        String lessonString = TimeSlot.getLessonString(startTime, endTime);
        return new ComputerRoomReservation(
                null,
                user,
                computerRoom,
                startTime,
                endTime,
                "",
                Math.random() < 0.5 ? ComputerRoomReservationStatus.NEW.toString() : ComputerRoomReservationStatus.CONFIRMED.toString(),
                lessonString,
                lessonString.contains("-")
        );
    }

    private static LocalTime[] getRandomTimeSlot() {
        boolean isDoubleSlot = Math.random() < 0.85;
        if (!isDoubleSlot) {
            return TimeSlot.ALLE_STUNDEN.get((int) (Math.random() * 0.9));
        }
        return TimeSlot.ALLE_DOPPELSTUNDEN.get((int) (Math.random() * 10 / 2));
    }

    private static Instant getRandomDay() {
        Instant today = Instant.now().truncatedTo(ChronoUnit.DAYS);
        Instant randomDay;
        while (true) {
            boolean doPlus = Math.random() < 0.5;
            int daysToAddOrSubtract = (int) (Math.random() * 30);
            if (doPlus) {
                randomDay = today.plus(daysToAddOrSubtract, ChronoUnit.DAYS);
            }
            else {
                randomDay = today.minus(daysToAddOrSubtract, ChronoUnit.DAYS);
            }
            DayOfWeek day = Instant.ofEpochMilli(randomDay.toEpochMilli()).atZone(DateHelper.ZONE_ID).getDayOfWeek();
            if (!day.equals(DayOfWeek.SATURDAY) && !day.equals(DayOfWeek.SUNDAY)) {
                break;
            }
        }
        return randomDay;
    }


    private static List<Computer> getComputerWithSoftwareList(List<Software> software) {
        return List.of(
                new Computer(null, "PC-01", null, software, "", false),
                new Computer(null, "PC-02", null, software, "", false),
                new Computer(null, "PC-03", null, software, "", false),
                new Computer(null, "PC-04", null, software, "", false),
                new Computer(null, "PC-05", null, software, "", false),
                new Computer(null, "PC-06", null, software, "", false),
                new Computer(null, "PC-07", null, software, "", false),
                new Computer(null, "PC-08", null, software, "", false),
                new Computer(null, "PC-09", null, software, "", false),
                new Computer(null, "PC-10", null, software, "", false),
                new Computer(null, "PC-11", null, software, "", false),
                new Computer(null, "PC-12", null, software, "", false),
                new Computer(null, "PC-13", null, software, "", false),
                new Computer(null, "PC-14", null, software, "", false),
                new Computer(null, "PC-15", null, software, "", false)
        );
    }

    private static List<Software> getSoftwareList() {
        return List.of(
                new Software(null, "18.8.1", "Microsoft Word", null),
                new Software(null, "18.8.23", "Microsoft Excel", null),
                new Software(null, "18.8.2", "Microsoft Teams", null),
                new Software(null, "18.8.3", "Microsoft PowerPoint", null),
                new Software(null, "18.8.1", "Microsoft PowerPoint", null),
                new Software(null, "11", "Java Runtime", null),
                new Software(null, "9", "Apache Tomcat", null),
                new Software(null, "1.3.2", "Adobe Acrobat", null)
        );
    }
}
