package com.app.library.utils;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DateUtilsTest {

    private static Date testDate;

    @BeforeClass
    public static void setUpAll() {
        testDate = Date.from(LocalDate
            .of( 2019 , 5 , 6 )
            .atStartOfDay(
                ZoneId.of( "Europe/Warsaw" )
            )
            .toInstant());
    }

    @Test
    public void asDateFromLocalDate() {
        Calendar calendar = new GregorianCalendar();
        LocalDate testLocalDate = LocalDate.of( 2019 , 5 , 6 );

        Date result = DateUtils.asDate(testLocalDate);
        calendar.setTime(result);

        int expectedDay = 6;
        int expectedMonth = 4; // Months starts at 0
        int expectedYear = 2019;

        assertEquals(expectedDay, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(expectedMonth, calendar.get(Calendar.MONTH));
        assertEquals(expectedYear, calendar.get(Calendar.YEAR));
    }

//    @Test
//    public void asDateFromLocalDateTime() {
//        LocalDateTime.of(2019, 5, 6, 1, 12, 0);
//        Date dateToTest = DateUtils.asDate(LocalDateTime.now());
//
//        assertEquals();
//    }
//
//    @Test
//    public void asLocalDate() {
//        LocalDate localDate = DateUtils.asLocalDate(testDate);
//    }
//
//    @Test
//    public void asLocalDateTime() {
//        LocalDateTime localDateTime = DateUtils.asLocalDateTime(testDate);
//    } // TODO
}