package com.eks.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;

public class DateUtils {
    public static String getNowDateString(){
        return DateFormatUtils.format(Calendar.getInstance(), "yyyyMMdd_HHmmss");
    }
}
