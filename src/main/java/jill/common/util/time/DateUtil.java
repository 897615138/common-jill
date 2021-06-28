package jill.common.util.time;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author Jill W
 * @date 2020/11/06
 */
@Slf4j
public class DateUtil {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 闰年判断
     */
    public static String isLeapYear(Integer year) {
        //能被4整除且不能被100整除的为普通闰年，能被400整除的为世纪闰年
        if (year % 400 == 0) {
            return (year + "是世纪闰年");
        } else {
            if (year % 4 == 0 && year % 100 != 0) {
                return (year + "是普通闰年");
            } else {
                return (year + "是平年");
            }
        }
    }


    public static String parseTimestampTen(Long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time * 1000));
    }

    public static String parseTimestampTen(String time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(time) * 1000));
    }

    public static String parseTimestampThirteen(Double time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static String parseTimestampThirteen(String time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(time));
    }

    /**
     * 转化为 年月日 时分秒
     *
     * @param time Wed Jan 27 16:53:49 CST 2021
     * @return 2021-01-27 16:53:49
     */
    public static String parseTimeCST2(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 14);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    public static LocalDate convertDate(String param) {
        try {
            return LocalDate.parse(param, TIME_FORMATTER);
        } catch (DateTimeParseException var2) {
            log.error("date parse error,param:{}", param);
            return null;
        }
    }

    public static String printToSecond(Date date) {
        if (Objects.isNull(date)) {
            return null;
        } else {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
            return TIME_FORMATTER.format(localDateTime);
        }
    }

    public static Date getStartOfDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        } else {
            Calendar dateStart = Calendar.getInstance();
            dateStart.setTime(date);
            dateStart.set(Calendar.HOUR_OF_DAY, 0);
            dateStart.set(Calendar.MINUTE, 0);
            dateStart.set(Calendar.SECOND, 0);
            return dateStart.getTime();
        }
    }

    public static Date getEndOfDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        } else {
            Calendar dateEnd = Calendar.getInstance();
            dateEnd.setTime(date);
            dateEnd.set(Calendar.HOUR_OF_DAY, 23);
            dateEnd.set(Calendar.MINUTE, 59);
            dateEnd.set(Calendar.SECOND, 59);
            return dateEnd.getTime();
        }
    }

    /**
     * 比较两个时间的大小
     *
     * @param beginTime beginTime
     * @param endTime   endTime
     * @return int
     */
    public int compareDate(Date beginTime, Date endTime) {
        return beginTime.compareTo(endTime);
    }

    /**
     * 计算日期加天数
     *
     * @param date date
     * @param days days
     * @return java.util.Date
     */
    public Date dateAddDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public boolean isAfter(Date expiredAt, Date now) {
        return compareDate(expiredAt, now) <= 0;
    }

    public boolean isAfterNow(Date expiredAt) {
        Date now = new Date();
        return isAfter(expiredAt, now);
    }

    public static void main(String[] args) {
        Date date = new Date("Wed Jan 27 16:53:49 CST 2021");
//        System.out.println(parseTimeCST(date));
        System.out.println(parseTimeCST2(date));
    }

}
