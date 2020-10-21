package jill.common.util.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author JillW
 * @date 2020/10/20
 */
public class DateConverter {
    public DateConverter() {
    }
    public static LocalDateTime convertLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date convertDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
