package main.java.libraries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        return dateTimeFormat.format(d);
    }

}
