package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by ileem on 2016-08-19.
 */
public class DialogActivity3 extends Activity {

    Uri ringtoneUri;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_activity3);

        //알람매니저 생성
        final AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Button cancleBtn = (Button)findViewById(R.id.cancleBtn);
        Button OKBtn = (Button)findViewById(R.id.OKBtn);
        Button secBtn = (Button)findViewById(R.id.sec);
        Button ringBtn = (Button)findViewById(R.id.ringBtn);


        //취소버튼 클릭 구현
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogActivity3.this, MainActivity.class);
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

        //10초 후 버튼 클릭 구현
        secBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                // 현재시각 long으로 가져올 때
                long now = calendar.getTimeInMillis();

                Intent intent = new Intent(DialogActivity3.this, AlarmDialogActivity.class);
                intent.putExtra("리잉톤", ringtoneUri); //링톤Uri 값을 인텐트에 태워 보낸댜
                PendingIntent pIntent = PendingIntent.getActivity(DialogActivity3.this, 0, intent, 0);
                // 1회 알람 시작
                System.out.println("지금 시간으로 부터 10초뒤~");
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, now + 10000, pIntent);

//                //인텐트 객체 생성
//                Intent intent = new Intent(DialogActivity3.this, MyService.class);
//                //서비스 시작시키기
//                startService(intent);
//
//                finish();
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
        Intent intent = new Intent(DialogActivity3.this, MainActivity.class);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
