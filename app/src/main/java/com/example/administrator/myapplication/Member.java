package com.example.administrator.myapplication;

import java.util.List;

/**
 * Created by ileem on 2016-08-23.
 */
public class Member {

    private List<TimeAndDay> timeAndDays;

    public Member(List<TimeAndDay> timeAndDays)
    {
        this.timeAndDays = timeAndDays;
    }

    public List<TimeAndDay> getTimeAndDays()
    {
        return timeAndDays;
    }

}
