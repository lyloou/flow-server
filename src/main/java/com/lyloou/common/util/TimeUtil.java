package com.lyloou.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public interface TimeUtil {

    SimpleDateFormat SDF_ONE = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    SimpleDateFormat SDF_TWO = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
    SimpleDateFormat SDF_THREE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA);

    static int[] getValidTime(String time) {
        int hourOfDay;
        int minute;
        if (isInvalidTime(time)) {
            Calendar instance = Calendar.getInstance();
            hourOfDay = instance.get(Calendar.HOUR_OF_DAY);
            minute = instance.get(Calendar.MINUTE);
        } else {
            String[] ts = time.split(":");
            hourOfDay = Integer.parseInt(ts[0]);
            minute = Integer.parseInt(ts[1]);
        }
        return new int[]{hourOfDay, minute};
    }

    /**
     * 判断时间是否有效
     *
     * @param time 时间字符串，如：16:32
     * @return 是否有效的结果
     */
    static boolean isInvalidTime(String time) {
        if (time == null) {
            return true;
        }
        String[] split = time.split(":");
        if (split.length != 2) {
            return true;
        }

        if (!isDigitsOnly(split[0])) {
            return true;
        }
        int hour = Integer.parseInt(split[0]);
        if (hour < 0 || hour > 24) {
            return true;
        }

        if (!isDigitsOnly(split[1])) {
            return true;
        }
        int minute = Integer.parseInt(split[1]);
        if (minute < 0 || minute > 60) {
            return true;
        }

        return false;
    }

    static boolean isDigitsOnly(CharSequence str) {
        final int len = str.length();
        for (int cp, i = 0; i < len; i += Character.charCount(cp)) {
            cp = Character.codePointAt(str, i);
            if (!Character.isDigit(cp)) {
                return false;
            }
        }
        return true;
    }

    static String getTimeString(int h, int m) {
        String hour = h < 10 ? ("0" + h) : ("" + h);
        String minute = m < 10 ? ("0" + m) : ("" + m);
        return hour + ":" + minute;
    }


    static String getInterval(String timeStart, String timeEnd) {
        if (timeStart == null || timeEnd == null) {
            return null;
        }
        if (timeStart.compareTo(timeEnd) > 0) {
            return null;
        }
        int[] startArr = TimeUtil.getValidTime(timeStart);
        int[] endArr = TimeUtil.getValidTime(timeEnd);

        int sH = startArr[0];
        int sM = startArr[1];

        int eH = endArr[0];
        int eM = endArr[1];
        int spendAllMinute = (eH * 60 + eM) - (sH * 60 + sM);
        int spendHour = spendAllMinute / 60;
        int spendMinute = spendAllMinute % 60;
        return TimeUtil.getTimeString(spendHour, spendMinute);
    }

    static String transferTwoToOne(String day) {
        Date parse = null;
        try {
            parse = SDF_TWO.parse(day);
        } catch (ParseException e) {
            return null;
        }
        return SDF_ONE.format(parse);
    }

    static String transferThreeToOne(String day) {
        Date parse;
        try {
            parse = SDF_THREE.parse(day);
        } catch (ParseException e) {
            return null;
        }
        return SDF_ONE.format(parse);
    }

    static String today() {
        Date parse = Calendar.getInstance().getTime();
        return SDF_TWO.format(parse);
    }

    static String getFormatTime(String timeStr) {
        if (timeStr == null) {
            return "--:--";
        }
        return timeStr;
    }

    static String getDayWithFormatOne() {
        return SDF_ONE.format(Calendar.getInstance().getTime());
    }

    static String getDayWithFormatTwo() {
        return SDF_TWO.format(Calendar.getInstance().getTime());
    }


}
