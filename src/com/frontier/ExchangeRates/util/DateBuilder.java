package com.frontier.ExchangeRates.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DateBuilder {

    private static Calendar calendar = GregorianCalendar.getInstance();
    private static List<String> date = new ArrayList<>();

    private static String day;
    private static String month;
    private static String year;

    public static List<String> getDateList() {
        dataAssign();

        date.add(getDate(day, month, year));

        for (int i = 0; i < 30; i++) {
            int dayInt = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, dayInt-1);
            dataAssign();

            date.add(getDate(day, month, year));
        }
        return date;
    }

    private static void dataAssign() {
        day = String.valueOf(calendar.get(Calendar.DATE));
        month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        year = String.valueOf(calendar.get(Calendar.YEAR));
    }

    private static String getDate(String day, String month, String year) {
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        return day + "." + month + "." + year;
    }
}
