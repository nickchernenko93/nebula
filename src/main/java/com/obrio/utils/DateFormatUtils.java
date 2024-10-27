package com.obrio.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
    private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    public String formatDayDate(Date date) {
        return dayFormat.format(date);
    }

    public String formatMonthDate(Date date) {
        return monthFormat.format(date);
    }

    public String formatYearDate(Date date) {
        return yearFormat.format(date);
    }
}
