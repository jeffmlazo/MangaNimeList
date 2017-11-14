package com.manganimelist.libraries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * DateTime with format library.
 *
 * @author jeprox
 */
public class DateTimeFormatter {

    /**
     * Format the long timestamp to human readable date, time and am/pm marker.
     *
     * @param epoch the timestamp
     * @return the formatted datetime with am/pm marker
     */
    public static String formatDateTime(long epoch) {
        Date d = new Date(epoch);
        DateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        return dateTimeFormat.format(d);
    }

    /**
     * Convert the date string with default format to local date with custom
     * date format.
     *
     * @param date the string date
     * @return the formatted date
     */
    public static String formatDate(String date) {
        LocalDate ld = LocalDate.parse(date);
        return ld.format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/uuuu"));
    }
}
