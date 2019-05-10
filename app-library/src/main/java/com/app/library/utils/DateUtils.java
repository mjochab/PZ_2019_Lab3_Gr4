package com.app.library.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    private DateUtils() {
    }

    public static Date asDate(LocalDate localDate) {
        if (localDate != null) {
            return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

    public static Date asDate(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

    public static LocalDate asLocalDate(Date date) {
        if (date != null) {
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        if (date != null) {
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }
}
