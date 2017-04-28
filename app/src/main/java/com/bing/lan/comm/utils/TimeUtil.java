package com.bing.lan.comm.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Author: yxhuang
 * Date: 2017/4/1
 * Email: yxhuang@gmail.com
 */

public class TimeUtil {

    /**
     *  转换成时间
     * @param originTime
     * @return
     * @throws ParseException
     */
    public static String time(String originTime){
        if (!TextUtils.isEmpty(originTime)){
        //if (!Strings.isNullOrEmpty(originTime)){


            SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

            String d = format.format(Long.valueOf(originTime));

            return d;
        }

        return "";
    }


    /**
     *  转换成时间
     * @param originTime
     * @return
     */
    public static String LongToTime(long originTime){
        if (originTime != 0L){
            SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

            String d = format.format(originTime);

            return d;
        }

        return "";
    }

    /**
     * 获取当前日期的前七天
     * @return
     */
    public static String[] getCurrentDayBeforeSevenDay(int dayNum){
        String[] days = new String[dayNum];
        Calendar calendar = Calendar.getInstance();
        for (int i = dayNum - 1; i > -1; i--){
            calendar.add(Calendar.DATE, -1);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);

            days[i] = month + "月" + day +"日";

            //PayLog.i("TimeUtil", "getCurrentDayBeforeSevenDay i " + i + "  " + days[i] );
        }

        return days;
    }

}
