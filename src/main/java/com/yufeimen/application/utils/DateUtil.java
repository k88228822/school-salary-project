package com.yufeimen.application.utils;

import java.util.Date;

public class DateUtil {

    public static long getSubMillisecond(Date newData,Date oldDate){
        return newData.getTime()-oldDate.getTime();
    }

    public static long getSubDays(Date newDate,Date oldDate){
        return getSubMillisecond(newDate,oldDate)/(1000 * 60 * 60 * 24);
    }

    public static long getSubHours(Date newDate,Date oldDate){
       return (getSubMillisecond(newDate,oldDate)-getSubDays(newDate,oldDate)*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
    }

    public static long getSubMinute(Date newDate,Date oldDate){
        return(getSubMillisecond(newDate,oldDate)-getSubDays(newDate,oldDate)*(1000 * 60 * 60 * 24)-getSubHours(newDate,oldDate)*(1000* 60 * 60))/(1000* 60);
    }

}
