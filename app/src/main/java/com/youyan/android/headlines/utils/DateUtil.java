package com.youyan.android.headlines.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import static com.youyan.android.headlines.utils.DateUtil.DatePattern.ONLY_DAY;
import static com.youyan.android.headlines.utils.DateUtil.DatePattern.ONLY_HOUR_AGO;
import static com.youyan.android.headlines.utils.DateUtil.DatePattern.ONLY_MINUTE_AGO;
import static com.youyan.android.headlines.utils.DateUtil.DatePattern.ONLY_MONTH_DAY;

/**
 * Created by android on 3/21/18.
 * 时间类
 */

public class DateUtil {

    /**
     * 枚举日期格式
     */
    public enum DatePattern {
        /**
         * 格式："yyyy-MM-dd HH:mm:ss"
         */
        ALL_TIME {
            public String getValue() {
                return "yyyy-MM-dd HH:mm:ss";
            }
        },
        /**
         * 格式："yyyy-MM"
         */
        ONLY_MONTH {
            public String getValue() {
                return "yyyy-MM";
            }
        },
        /**
         * 格式："yyyy-MM-dd"
         */
        ONLY_DAY {
            public String getValue() {
                return "yyyy-MM-dd";
            }
        },
        /**
         * 格式："yyyy-MM-dd HH"
         */
        ONLY_HOUR {
            public String getValue() {
                return "yyyy-MM-dd HH";
            }
        },
        /**
         * 格式："yyyy-MM-dd HH:mm"
         */
        ONLY_MINUTE {
            public String getValue() {
                return "yyyy-MM-dd HH:mm";
            }
        },
        /**
         * 格式："MM-dd"
         */
        ONLY_MONTH_DAY {
            public String getValue() {
                return "MM-dd";
            }
        },
        /**
         * 格式："MM-dd HH:mm"
         */
        ONLY_MONTH_SEC {
            public String getValue() {
                return "MM-dd HH:mm";
            }
        },
        /**
         * 格式："HH:mm:ss"
         */
        ONLY_TIME {
            public String getValue() {
                return "HH:mm:ss";
            }
        },
        /**
         * 格式："HH:mm"
         */
        ONLY_HOUR_MINUTE {
            public String getValue() {
                return "HH:mm";
            }
        },

        /**
         * 格式：HH前
         */
        ONLY_HOUR_AGO{
            public String getValue(){
                return "HH" + "前";
            }
        },

        /**
         * 格式：mm前
         */

        ONLY_MINUTE_AGO{
            public String getValue(){
                return "mm" + "前";
            }
        };

        public abstract String getValue();
    }

    /**
     * 获取当前时间
     *
     * @return 返回当前时间，格式2017-05-04 10:54:21
     */
    public static String getNowDate(DatePattern pattern) {
        String dateString = null;
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        dateString = sdf.format(dateNow);
        return dateString;
    }

    /**
     * 将一个日期字符串转换成Date对象
     *
     * @param dateString 日期字符串
     * @param pattern    转换格式
     * @return 返回转换后的日期对象
     */
    public static Date stringToDate(String dateString, DatePattern pattern) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将date转换成字符串
     *
     * @param date    日期
     * @param pattern 日期的目标格式
     * @return
     */
    public static String dateToString(Date date, DatePattern pattern) {
        String string = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        string = sdf.format(date);
        return string;
    }

    /**
     * 获取指定日期周几
     *
     * @param date 指定日期
     * @return 返回值为： "周日", "周一", "周二", "周三", "周四", "周五", "周六"
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0)
            week = 0;
        return weekDays[week];
    }

    /**
     * 获取指定日期对应周几的序列
     *
     * @param date 指定日期
     * @return 周一：1    周二：2    周三：3    周四：4    周五：5    周六：6    周日：7
     */
    public static int getIndexWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        if (index == 1) {
            return 7;
        } else {
            return --index;
        }
    }

    /**
     * 返回当前月份
     *
     * @return
     */
    public static int getNowMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static int getNowDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getNowYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取本月份的天数
     *
     * @return
     */
    public static int getNowDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return daysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 获取指定月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 对应天数
     */
    public static int daysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 == 0) || year % 400 != 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }


    //转换中文对应的时段
    public static String convertNowHour2CN(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hourString = sdf.format(date);
        int hour = Integer.parseInt(hourString);
        if (hour < 6) {
            return "凌晨";
        } else if (hour >= 6 && hour < 12) {
            return "早上";
        } else if (hour == 12) {
            return "中午";
        } else if (hour > 12 && hour <= 18) {
            return "下午";
        } else if (hour >= 19) {
            return "晚上";
        }
        return "";
    }

    //剩余秒数转换
    public static String convertSecond2Day(int time) {
        int DAY_SECOND = 24 * 60 * 60;
        int HOUR_SECOND = 60 * 60;
        int day = time / DAY_SECOND;
        int hour = (time - DAY_SECOND * day) / HOUR_SECOND;
        int min = (time - DAY_SECOND * day - HOUR_SECOND * hour) / 60;
        int sec = (time - DAY_SECOND * day - HOUR_SECOND * hour - 60 * min);
        StringBuilder sb = new StringBuilder();
        sb.append(day);
        sb.append("天");
        sb.append(hour);
        sb.append("时");
        sb.append(min);
        sb.append("分");
        sb.append(sec);
        sb.append("秒");
        return sb.toString();
    }

    //剩余秒数转换
    public static String convertSecond2Day(int time,Date compareTime) {
        if (compareTime == null){
            compareTime = new Date();
        }
        int DAY_SECOND = 24 * 60 * 60;
        int HOUR_SECOND = 60 * 60;
        int day = (int) ((compareTime.getTime()/1000 - time)/ DAY_SECOND);
        int hour = (int) ((compareTime.getTime()/1000 - time - DAY_SECOND * day) / HOUR_SECOND);
        int min = (int) ((compareTime.getTime()/1000 - time - DAY_SECOND * day - HOUR_SECOND * hour) / 60);
        StringBuilder sb = new StringBuilder();
        if (day >= 1){
            sb.append(dateToString(new Date(time), DatePattern.ONLY_MONTH_DAY));
        }
        else if (day < 1 && hour >=1){
            sb.append(hour);
            sb.append("小时前");
        }else{
            sb.append(min);
            sb.append("分前");
        }
        return sb.toString();
    }


}
