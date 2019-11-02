package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

/**
 * Created by ileem on 2016-08-25.
 */
public class DialogActivity4 extends Activity {

    Uri ringtoneUri;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_activity4);

        Button cancleBtn = (Button)findViewById(R.id.cancleBtn);
        Button OKBtn = (Button)findViewById(R.id.OKBtn);
        Button secBtn = (Button)findViewById(R.id.sec);
        Button ringBtn = (Button)findViewById(R.id.ringBtn);

        //취소버튼 클릭 구현
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogActivity4.this, MainActivity.class);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        //벨소리 선택 버튼 클릭 구현
        ringBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(intent, 0);
            }
        });

        //확인버튼 클릭 구현
        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePicker timePicker = (TimePicker)findViewById(R.id.timePicker);
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                Calendar calendar = Calendar.getInstance();

                TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
                calendar.setTimeZone(timeZone);//한국시간으로 TimeZone설정

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                Date date = calendar.getTime();

                Intent intent = new Intent(DialogActivity4.this, AlarmDialogActivity.class);
                intent.putExtra("리잉톤", ringtoneUri); //링톤Uri 값을 인텐트에 태워 보낸댜
                PendingIntent pIntent = PendingIntent.getActivity(DialogActivity4.this, 1, intent, 0);

                Log.i("로그찍어 설정한 시간", ""+date);

                //알람매니저 생성
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);

                finish();

                Toast.makeText(getApplicationContext(), "알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            //벨소리 Uri만 받는댜
            ringtoneUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(DialogActivity4.this, MainActivity.class);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
