package com.example.lm.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenanlian
 */
public class TimeUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);

    public static final long DAY_MILLIS = 86400000L;
    public static final long HOUR_MILLIS = 3600000L;
    public static final long MINUTE_MILLIS = 60000L;

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";
    public static final String YYYY_MM_DDHHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DDHHMM_CN = "yyyy年MM月dd日 HH时mm分";
    public static final String YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DDHHMMSS_CN = "yyyy年MM月dd HH时mm分ss秒";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_CN = "yyyy年MM月";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY = "yyyy";
    public static final String YY_MM_DDHHMM = "yy-MM-dd HH:mm";
    public static final String YY_MM_DDHHMM_CN = "yy年MM月dd日 HH时mm分";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHH:mm:ss";
    public static final String YYYYMMDDHHMMSS_2 = "yyyyMMddHHmmss";
    private static final String DBL_NUM_PATTERN = "\\d{1,2}";
    
    private TimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 根据格式把时间转成秒
     *
     * @param time
     *            1小时10分钟1秒
     * @param pattern
     *            HH小时mm分钟ss秒
     * @return
     */
    public static int getSenconds(String time, String pattern) {
        TimeZone timeZoneChina = TimeZone.getTimeZone("GMT0");
        FastDateFormat sdf = FastDateFormat.getInstance(pattern, timeZoneChina);
        int senconds = 0;
        try {
            Date date = sdf.parse(time);
            senconds = (int) (date.getTime() / 1000);
        } catch (ParseException e) {
            LOGGER.error("Time = " + time + ",pattern =  " + pattern, e);
        }
        return senconds;
    }

    // 以当前月为标准计算yyyyMn
    public static String getYearOfMonth(int i, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, i);
        return format(calendar.getTime(), pattern);
    }


    /**
     * 获得日期
     *
     * @param date
     * @return
     */
    public static Integer getDateOfMonth(Date date) {
        Integer dateOfMonth = null;
        if (Objects.nonNull(date)){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            dateOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        }
        return dateOfMonth;
    }
    /**
     * 获得某月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 返回一个月的 第一天20160801
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String getFirstDayOfMonth(String date, String pattern) throws ParseException {
        Date dateMonth = parse(date, pattern);
        Date firstDayMonth = getFirstDayOfMonth(dateMonth);
        return format(firstDayMonth, pattern);
    }

    /**
     * 获得某月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 返回一个月的 最后一天20160831
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String getLastDayOfMonth(String date, String pattern) throws ParseException {
        Date dateMonth = parse(date, pattern);
        Date firstDayMonth = getLastDayOfMonth(dateMonth);
        return format(firstDayMonth, pattern);
    }

    /**
     * 将某个时间减一天
     *
     * @param date
     * @param i
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String addOrCutDate(String date, int i, String pattern) throws ParseException {
        Date dateMonth = parse(date, pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateMonth);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return format(cal.getTime(), pattern);
    }

    /**
     * @param date
     * @param dateType
     * @return
     * @Description: 将一个以某种时间格式的字符串转化为时间格式
     */
    public static Date getDate(String date, String dateType) {
        DateFormat df = new SimpleDateFormat(dateType);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * @param date
     * @param dateType
     * @return
     * @Description: 获取某个格式的时间字符串
     */
    public static String getDateString(Date date, String dateType) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateType);
        return sdf.format(date);
    }

    /*
     * @param date
     * @param month 正数为增加月份，负数为减少月份
     * @return
     */

    public static String changeMonth(String date, int month, String dateType) {
        DateFormat df = new SimpleDateFormat(dateType);
        try {

            Date dd = df.parse(date);

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(dd);

            calendar.add(Calendar.MONTH, month);

            return df.format(calendar.getTime());

        } catch (ParseException e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 时间转换为秒，如 1分37秒 转化为97
     *
     * @param timeStr
     * @return
     */
    public static int toSecond(String timeStr) {
        if (StringUtils.isBlank(timeStr)) {
            return 0;
        }
        
        int time = 0;
        String hour = "时";
        String min = "分";
        if (timeStr.contains("小时")) {
            hour = "小时";
        }
        if (timeStr.contains("分钟")) {
            min = "分钟";
        }
        
        int hourIndex = timeStr.indexOf(hour);
        if (hourIndex != -1) {
            time += Integer.parseInt(timeStr.substring(0, hourIndex)) * 3600; // 小时
        }
        
        int minIndex = timeStr.indexOf(min);
        if (minIndex != -1) {
            time += Integer.parseInt(timeStr.substring(timeStr.indexOf(hour) + hour.length(), minIndex)) * 60; // 分钟
        }
        
        int secondIndex = timeStr.indexOf('秒');
        if (secondIndex != -1) {
            time += Integer.parseInt(timeStr.substring(timeStr.indexOf(min) + min.length(), secondIndex)); // 秒
        }
        
        return time;
    }

    /**
     * 时间转换为秒，如 00:01:02 转化为62
     *
     * @param timeStr
     * @return
     */
    public static int toTransSecond(String timeStr) {
        return toTransSecond(timeStr, ":");
    }

    public static int toTransSecond(String timeStr, String splitStr) {
        int time = 0;
        String matchStr = DBL_NUM_PATTERN + splitStr + DBL_NUM_PATTERN + splitStr + DBL_NUM_PATTERN;
        if (!timeStr.matches(matchStr)) {
            return time;
        }
        String[] times = timeStr.split(splitStr);
        int[] second = new int[] { 3600, 60, 1 };
        for (int i = 0, count = times.length; i < count; i++) {
            String s = times[i];
            time += Integer.parseInt(s) * second[i];
        }

        return time;
    }

    /** 锁对象 */
    private static final Object lockObj = new Object();

    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     * @param pattern
     * @return
     */
    protected static SimpleDateFormat getDateFormatter(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // 此处的双重判断和同步是为了进一步防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
                    // SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    public static int daysBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 使用带缓存的方式格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return FastDateFormat.getInstance(pattern).format(date);
    }

    /**
     * 使用带缓存的方式格式化
     *
     * @param calendar
     * @param pattern
     * @return
     */
    public static String format(Calendar calendar, String pattern) {
        return FastDateFormat.getInstance(pattern).format(calendar);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        Date date = null;
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        date = FastDateFormat.getInstance(pattern).parse(dateStr.trim());
        return date;
    }

    /**
     * A、进件日期（年\月\日）-入网日期（年\月\日），取月份 ;
     * B、按30天为一个月，不足30天不算1个月
     *
     * @param initDate
     * @param queryDate
     * @return
     */
    public static Integer getValidMonth(Date initDate, Date queryDate) {
        int validMonth = 0;
        if (null != initDate && null != queryDate) {
            validMonth = daysBetween(initDate, queryDate) / 30;
        } else {
            validMonth = -1;
        }
        return validMonth;
    }

    /**
     * 比较compDate 是否在 nowDate 的 n 天之内
     * 
     * @param compDate 需要对比的时间
     * @param nowDate 当前匹配的时间
     * @param n 天数
     * @return
     */
    public static boolean belongDate(Date compDate, Date nowDate, int n) {
        if (compDate == null || nowDate == null) {
            return false;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, n);

        Date nowTime = calendar.getTime();

        return (n < 0 && nowTime.before(compDate)) || (n > 0 && compDate.before(nowTime));
    }

    public static Date getYearMonth(short year, byte month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1, 0, 0, 0);
        cal.setTimeInMillis(cal.getTimeInMillis() / 1000 * 1000);  //毫秒数设置为000
        return cal.getTime();
    }

}
