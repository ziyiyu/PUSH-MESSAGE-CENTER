package com.xinyan.message.center.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiahoujie on 2017/5/9.
 */
@Slf4j
public class DateUtil {
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_DATE_FORMAT4 = "yyyy-MM-dd";
    public static final String LONG_DATE_FORMAT3 = "yyyy-MM-dd HH:mm";
    public static final String LONG_DATE_FORMAT1 = "yyyyMMddHHmmss";
    public static final String LONG_DATE_FORMAT2 = "yyyyMMddHHmm";
    public static final String LONG_DATE_FORMAT0 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_MONTH_FORMAT = "yyyy-MM";
    public static final String SHORT_YEAR_FORMAT = "yyyy";
    public static final String YEAR_MONTH = "yyyyMM";
    public static final String MONTH_FORMAT = "MM";
    public static final String DATE_FORMAT = "dd";
    public static DateFormat dateTimeMinFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 字符串转日期
     */
    public static Date stringToDate(String fromat, String dateStr) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 取得n天前时间
     */
    public static String getTimeByDay(String fromat, String dateBefore, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        String tomorrow = "";
        try {
            Date myDate = sdf.parse(dateBefore);
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            cal.add(Calendar.DATE, day);
            tomorrow = new SimpleDateFormat(fromat).format(cal.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tomorrow;
    }

    /**
     * 取得n分钟前时间
     */
    public static String getTimeByMinute(String fromat, String dateBefore, int min) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        String tomorrow = "";
        try {
            Date myDate = sdf.parse(dateBefore);
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            cal.add(Calendar.MINUTE, min);
            tomorrow = new SimpleDateFormat(fromat).format(cal.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tomorrow;
    }

    /**
     * 取得n秒前时间
     * @throws ParseException
     */
    public static String getTimeBySecond(String fromat, String dateBefore, int second) {

        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        String tomorrow = "";
        try {
            Date myDate = sdf.parse(dateBefore);
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            cal.add(Calendar.SECOND, second);
            tomorrow = new SimpleDateFormat(fromat).format(cal.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tomorrow;
    }

    /**
     * 日期变为字符串
     */
    public static String dateToString(Date date, String iso) {
        SimpleDateFormat format = new SimpleDateFormat(iso);
        return format.format(date);
    }

    /**
     * 把Date格式化成yyyy-MM-dd HH:mm:ss
     *
     * @param dt
     * @return
     */
    public static String formatLongDate(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_FORMAT);

        if (dt == null) {
            return null;
        }
        return sdf.format(dt);
    }

    /**
     * 把Date格式化
     *
     * @param dt
     * @return
     */
    public static String formatLongDate(Date dt, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        if (dt == null) {
            return null;
        }
        return sdf.format(dt);
    }

    /**
     * 获取时间差
     * @param firstTime
     * @param endTime
     * @return 毫秒
     */
    public static String betweenTime(String firstTime, String endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat(LONG_DATE_FORMAT0);
        long between = 0;
        try {
            Date first = dfs.parse(firstTime);
            Date end = dfs.parse(endTime);
            between = (end.getTime() - first.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return between + "";
    }

    /**
     * 比较时间大小
     * @param d1
     * @param d2
     * @return 大的
     */
    public static String compareDateReturnBig(String d1, String d2) {
        Date dt1 = null;
        Date dt2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.LONG_DATE_FORMAT);
        try {
            dt1 = sdf.parse(d1);
        } catch (Exception e) {
            return d2;
        }
        try {
            dt2 = sdf.parse(d2);
        } catch (Exception e) {
            return d1;
        }
        if (dt1.getTime() > dt2.getTime()) {
            return d1;
        } else if (dt1.getTime() < dt2.getTime()) {
            return d2;
        } else {
            return d1;
        }
    }

    /**
     * 返回两个时间相同部分
     *
     * @param time1
     * @param time2
     * @return 毫秒
     */
    public static String getSamePart(String time1, String time2) {
        String msg = "";
        try {
            int length = time1.length()>time2.length()?time2.length():time1.length();
            for (int i = 0; i < length; i++) {
                if (time1.charAt(i) == time2.charAt(i)) {
                    msg += time1.charAt(i) + "";
                } else {
                    break;
                }
            }
        }catch (Exception e) {
          log.error(e.getMessage(), e);
        }
        return msg;
    }

    public static String getCurrent(String pattern) {
        return DateTime.now().toString(pattern);
    }

    public static String getCurrent() {
        return getCurrent("yyyyMMddHHmmss");
    }

    public static void main(String[] args) {
        //String a = getNSBefer(DateUtil.LONG_DATE_FORMAT, "2016-06-07 14:14:14", 56);
        //String a = getTimeBySecond(DateUtil.LONG_DATE_FORMAT, "2016-06-07 14:14:14", 126);
        //String a = getTimeBySecond(DateUtil.LONG_DATE_FORMAT, "2016-06-28 10:33:30", 10);
        //String b = compareDateReturnBig("2017-02-22 10:00:19.006904", "2017-02-22 10:00:10.995141");
        String c = getSamePart("2017-05-31", "2017-05-31");

        System.out.println("===------------------" + c);

    }
}
