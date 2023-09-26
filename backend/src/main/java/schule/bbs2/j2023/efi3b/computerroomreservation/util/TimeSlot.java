package schule.bbs2.j2023.efi3b.computerroomreservation.util;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;


public final class TimeSlot {

    public static final LocalTime[] FIRST_LESSON = {
            LocalTime.of(8, 0, 0),
            LocalTime.of(8, 45, 0),
    };

    public static final LocalTime[] SECOND_LESSON = {
            LocalTime.of(8, 45, 0),
            LocalTime.of(9, 30, 0),
    };

    public static final LocalTime[] THIRD_LESSON = {
            LocalTime.of(9, 45, 0),
            LocalTime.of(10, 30, 0),
    };

    public static final LocalTime[] FOURTH_LESSON = {
            LocalTime.of(10, 30, 0),
            LocalTime.of(11, 15, 0),
    };

    public static final LocalTime[] FIFTH_LESSON = {
            LocalTime.of(11, 30, 0),
            LocalTime.of(12, 15, 0),
    };

    public static final LocalTime[] SIXTH_LESSON = {
            LocalTime.of(12, 15, 0),
            LocalTime.of(13, 0, 0),
    };

    public static final LocalTime[] SEVENTH_LESSON = {
            LocalTime.of(13, 30, 0),
            LocalTime.of(14, 15, 0),
    };

    public static final LocalTime[] EIGHTH_LESSON = {
            LocalTime.of(14, 15, 0),
            LocalTime.of(15, 0, 0),
    };

    public static final LocalTime[] NINTH_LESSON = {
            LocalTime.of(15, 15, 0),
            LocalTime.of(16, 0, 0),
    };

    public static final LocalTime[] TENTH_LESSON = {
            LocalTime.of(16, 0, 0),
            LocalTime.of(16, 45, 0),
    };

    public static final LocalTime[] ERSTE_DOPPELSTUNDE = {
            LocalTime.of(8, 0, 0),
            LocalTime.of(9,30,0)
    };

    public static final LocalTime[] ZWEITE_DOPPELSTUNDE = {
            LocalTime.of(9, 45, 0),
            LocalTime.of(11,15,0)
    };
    public static final LocalTime[] DRITTE_DOPPELSTUNDE = {
            LocalTime.of(11, 30, 0),
            LocalTime.of(13,0,0)
    };
    public static final LocalTime[] VIERTE_DOPPELSTUNDE = {
            LocalTime.of(13, 30, 0),
            LocalTime.of(15,0,0)
    };
    public static final LocalTime[] FUENFTE_DOPPELSTUNDE = {
            LocalTime.of(15, 15, 0),
            LocalTime.of(16,45,0)
    };
    public static final List<LocalTime[]> ALLE_DOPPELSTUNDEN = List.of(
            ERSTE_DOPPELSTUNDE,
            ZWEITE_DOPPELSTUNDE,
            DRITTE_DOPPELSTUNDE,
            VIERTE_DOPPELSTUNDE,
            FUENFTE_DOPPELSTUNDE
    );
    public static final List<LocalTime[]> ALLE_STUNDEN = List.of(
            FIRST_LESSON,
            SECOND_LESSON,
            THIRD_LESSON,
            FOURTH_LESSON,
            FIFTH_LESSON,
            SIXTH_LESSON,
            SEVENTH_LESSON,
            EIGHTH_LESSON,
            NINTH_LESSON,
            TENTH_LESSON
    );

    public static String getLessonString(Instant startDate, Instant endDate) {
        LocalTime startTime = startDate.atZone(ZoneId.of("Europe/Berlin")).toLocalTime();
        LocalTime endTime = endDate.atZone(ZoneId.of("Europe/Berlin")).toLocalTime();
        boolean isDoubleLesson = isDoubleLesson(startTime, endTime);
        if (FIRST_LESSON[0].equals(startTime)) {
            return isDoubleLesson ? "1-2" : "1";
        }
        else if (SECOND_LESSON[0].equals(startTime)) {
            return "2";
        }
        else if (THIRD_LESSON[0].equals(startTime)) {
            return isDoubleLesson ? "3-4" : "3";
        }
        else if (FOURTH_LESSON[0].equals(startTime)) {
            return "4";
        }
        else if (FIFTH_LESSON[0].equals(startTime)) {
            return isDoubleLesson ? "5-6" : "5";
        }
        else if (SIXTH_LESSON[0].equals(startTime)) {
            return "6";
        }
        else if (SEVENTH_LESSON[0].equals(startTime)) {
            return isDoubleLesson ? "7-8" : "7";
        }
        else if (EIGHTH_LESSON[0].equals(startTime)) {
            return "8";
        }
        else if (NINTH_LESSON[0].equals(startTime)) {
            return isDoubleLesson ? "9-10" : "9";

        }
        else if (TENTH_LESSON[0].equals(startTime)) {
            return "10";
        }
        else {
            throw new IllegalArgumentException("Datum konnte nicht in gültige Schulstunde umgewandelt werden.");
        }

    }

    public static boolean isDoubleLesson(LocalTime startTime, LocalTime endTime) {
        if (startTime.plus(45, ChronoUnit.MINUTES).equals(endTime)) {
            return false;
        }
        else if (startTime.plus(90, ChronoUnit.MINUTES).equals(endTime)) {
            return true;
        }
        throw new IllegalStateException("invalid times. cannot map to lesson");
    }
}
