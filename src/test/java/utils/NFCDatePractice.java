package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NFCDatePractice {

    public static void main(String[] args) {
        myMethod();
    }

    private static void myMethod() {
        myMethod(getTenDaysFromGivenDate());
    }

    private static void myMethod(String date) {
        System.out.println("date: " + date);
    }

    private static String getTenDaysFromGivenDate() {
        // Get the current date and add 10 days to it
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        Date expiryDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(expiryDate);
    }
}
