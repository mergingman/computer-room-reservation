package schule.bbs2.j2023.efi3b.computerroomreservation.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Locale;

public class DateHelper {

    public static final ZoneId ZONE_ID = ZoneId.of("Europe/Berlin");
    public static final Locale LOCALE = Locale.GERMANY;

    /**
     * Figures out the first day of the work week (monday) and the last day of work week (friday) from given Instant
     * @param date - The Instant to find the week period for
     * @return - And Instant Array with exactly two values: the monday and the friday of the week that the given Instant lies within
     */
    public static Instant[] getWeekPeriodFromInstant(Instant date) {
        Instant firstDayOfWorkWeek = date.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime zonedDate = date.atZone(ZONE_ID);
        DayOfWeek dayOfWeek = zonedDate.getDayOfWeek();
        if (!dayOfWeek.equals(DayOfWeek.MONDAY)) {
            while (!dayOfWeek.equals(DayOfWeek.MONDAY)) {
                dayOfWeek = dayOfWeek.minus(1L);
                firstDayOfWorkWeek = firstDayOfWorkWeek.minus(1, ChronoUnit.DAYS);
            }
        }
        return new Instant[] {firstDayOfWorkWeek, firstDayOfWorkWeek.plus(4, ChronoUnit.DAYS)};
    }
}
