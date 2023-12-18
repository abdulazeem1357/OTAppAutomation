package fundamentals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public static Date getCurrentDate() {
        return new Date();  // Returns the current date
    }

    public static Date modifyDaysToDate(Date date, int daysToModify) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, daysToModify);
        return calendar.getTime();
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static Date getCurrentTime() {
        return new Date();  // Returns the current time
    }

    public static Date modifyTime(Date date) {
        return modifyTime(date, 0, 0, 0);
    }

    public static Date modifyTime(Date date, int hoursToModify, int minutesToModify, int secondsToModify) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hoursToModify);
        calendar.add(Calendar.MINUTE, minutesToModify);
        calendar.add(Calendar.SECOND, secondsToModify);
        return calendar.getTime();
    }

    public static String formatTime(Date date) {
        return timeFormat.format(date);
    }
}
