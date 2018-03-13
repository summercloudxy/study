package time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DataUtil {
    public static Date toDate(LocalDateTime ldt){
        return new Date(ldt.atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(date.getTime()),
                ZoneId.systemDefault());
    }

    public static Calendar toCalendar(ZonedDateTime zdt) {
        TimeZone tz = TimeZone.getTimeZone(zdt.getZone());
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTimeInMillis(zdt.toInstant().toEpochMilli());
        return calendar;
    }

    public static ZonedDateTime toZonedDateTime(Calendar calendar) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(calendar.getTimeInMillis()),
                calendar.getTimeZone().toZoneId());
        return zdt;
    }

    /**
     * replace DateUtils.truncate(date, Calendar.SECOND)
     * @param date
     * @return
     */
    public Date truncateSecond(Date date) {
        Instant instant = date.toInstant();
        instant = instant.truncatedTo(ChronoUnit.SECONDS);
        return Date.from(instant);
    }

    public Date trancate(Date date,ChronoUnit chronoUnit){
        Instant instant = date.toInstant();
        instant = instant.truncatedTo(chronoUnit);
        return Date.from(instant);
    }


}
