package com.example.administrator.myapplication;

/**
 * Created by ileem on 2016-08-22.
 */
public class TimeAndDay {

    String time;
    String day;
    int requestCode;

    public TimeAndDay(String t, String d, int requestCode)
    {
        time = t;
        day = d;
        this.requestCode = requestCode;
    }

    public String getTime()
    {
        return time;
    }

    public String getDay()
    {
        return day;
    }

    public int getRequestCode()
    {
        return requestCode;
    }
    
}
