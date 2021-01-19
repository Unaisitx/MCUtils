package com.zalyx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Time {

    public static String date(String string) {
        Date now = new Date();

        SimpleDateFormat format = new SimpleDateFormat(string);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        return format.format(now);
    }

}
