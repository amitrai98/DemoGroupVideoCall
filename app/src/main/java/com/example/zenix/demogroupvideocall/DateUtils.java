package com.example.zenix.demogroupvideocall;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by amandeepsingh on 17/11/15.
 */
public class DateUtils {


    public static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static DateUtils mDateUtils;
    String daysList[] = {"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};

    /**
     * to get instance of DateUtils class.
     *
     * @return
     */
    public static DateUtils getInstance() {
        if (mDateUtils == null) {
            mDateUtils = new DateUtils();
        }
        return mDateUtils;
    }

    /**
     * convert given date's month number to text
     *
     * @param date String date in format of dd-MM-yyyy
     * @return Date with month as name
     */
    public String dateWithMonthName(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dateObj = sdf.parse(date);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateObj);
            String month = new DateFormatSymbols().getMonths()[getMonthFromDate(dateObj)];
            return calendar.get(Calendar.DAY_OF_MONTH) + " " + month + " " + calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


    public String getTime12Hours(String dateString) {
//        2 016-06-06 10:43:57
        DateFormat originalFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("hh:mm aa");
        Date date = null;
        try {
            date = originalFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return targetFormat.format(date);

    }


    public String getDateFromOrignalDate(String dateString) {
//        2 016-06-06 10:43:57
        DateFormat originalFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date = originalFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return targetFormat.format(date);
    }



    private int getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public int getDayFromDATE(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);

    }

    /**
     * get the month as string from date
     *
     * @param date
     * @return
     */
    public String getMonthStringFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new SimpleDateFormat("MMMM").format(cal.getTime());

    }

    /**
     * @param month int digit of month
     * @return Month name as String
     */
    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public int getYearFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.YEAR);


    }

    /**
     * create Date() from given String date into given format
     *
     * @param dateString Date which is converted to Date object
     * @param format     Format of date
     * @return Date Object
     */
    public Date getDateFromString(String dateString, String format) {
        Date convertedDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());

            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public int textMonthToInt(String month) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("MMM").parse(month));
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return cal.get(Calendar.MONTH) + 1;
    }

    public String getDayNameFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return daysList[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public Date getDateFromString(String time) {
//        Date dt = sourceFormat.parse(bookingStartTime);
//        "hh:mm aa"
        DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = parseFormat.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
//        String time1 = sdf.format(dt);
    }


    public Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }


    /**
     * @param time time in format of 01:00 PM
     * @return time in format of 13:00
     */
    public String get12HoursTo24Hour(String time) {
        DateFormat parseFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

        try {
            Date date = parseFormat.parse(time);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            String format = "%1$02d"; // two digits
            return calender.get(Calendar.HOUR_OF_DAY) + ":" + String.format(format, calender.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
            return time;
        }
    }


    public Date get12HoursTo24HourDate(String time) {
        DateFormat parseFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

        try {
            Date date = parseFormat.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String parseGMTDate(String datetime) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.DEFAULT_FORMAT);
        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        simpleDateFormat.setTimeZone(utcZone);
        Date myDate = simpleDateFormat.parse(datetime);
        //To display:

        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String formattedDate = simpleDateFormat.format(myDate);
        return formattedDate;
    }

    public String getTimeString(String datetime, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            return simpleDateFormat.format(getDateFromString(datetime, DateUtils.DEFAULT_FORMAT));
        } catch (Exception e) {
            return null;
        }
    }

    public String getOnlyDateString(String datetime, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.format(getDateFromString(datetime, DateUtils.DEFAULT_FORMAT));
        } catch (Exception e) {
            return null;
        }
    }

    public long getTimeInSeconds(Date date) {

        return date.getTime() / 1000;

    }
}

