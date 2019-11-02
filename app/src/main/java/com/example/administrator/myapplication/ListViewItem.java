package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2016-08-07.
 */
public class ListViewItem {  //데이터 처리 클래스

    String alarmName;
    String btnName;
    int requestCode;//알람매니저의 RequestCode값

    public void setAlarmName(String alarmName)
    {
        this.alarmName = alarmName;
    }

    public void setBtnName(String btnName)
    {
        this.btnName = btnName;
    }

    public void setRequestCode(int requestCode)
    {
        this.requestCode = requestCode;
    }

    public String getAlarmName()
    {
        return this.alarmName;
    }

    public String getBtnName()
    {
        return this.btnName;
    }

    public int getRequestCode()
    {
      return this.requestCode;
    }

//     ListViewItem(String alarmName, String btnName)
//    {
//        this.alarmName = alarmName;
//        this.btnName = btnName;
//    }

}