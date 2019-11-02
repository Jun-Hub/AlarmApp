package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by ileem on 2016-08-24.
 */
public class AlarmDialogActivity extends Activity {

    Ringtone ringtone, ringtone1;
    Thread maxVolumeUpThread;
    Vibrator vib;
    int randomInt, randomInt2, randomInt3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alarm_dialog_activity);

        Intent intent = getIntent();  //SubActivity로부터 온 인텐트 받기
        Intent intent1 = getIntent(); //AlterActivity로부터 온 인텐트 받기

        int sun1 = intent.getIntExtra("일욜", 888); //SubActivity로부터 요일 반복여부 받기
        int mon2 = intent.getIntExtra("월욜", 888); //각 요일 값들이 null이면 반복 체크 안됬던 것
        int tue3 = intent.getIntExtra("화욜", 888);
        int wed4 = intent.getIntExtra("수욜", 888);
        int thu5 = intent.getIntExtra("목욜", 888);
        int fri6 = intent.getIntExtra("금욜", 888);
        int sat7 = intent.getIntExtra("토욜", 888);

        int sun11 = intent1.getIntExtra("일요일", 999); //AlterActivity로부터 요일 반복여부 받기
        int mon22 = intent1.getIntExtra("월요일", 999); //각 요일 값들이 null이면 반복 체크 안됬던 것
        int tue33 = intent1.getIntExtra("화요일", 999);
        int wed44 = intent1.getIntExtra("수요일", 999);
        int thu55 = intent1.getIntExtra("목요일", 999);
        int fri66 = intent1.getIntExtra("금요일", 999);
        int sat77 = intent1.getIntExtra("토요일", 999);

        Uri ringtoneUri = intent.getParcelableExtra("리잉톤");//Intent로 Uri값 받기 from SubActivity
        ringtone = RingtoneManager.getRingtone(this, ringtoneUri);  //받은 Uri로 링톤 생성
        ringtone.setStreamType(AudioManager.STREAM_ALARM);
        ringtone.play();

        Uri ringtoneUri1 = intent1.getParcelableExtra("링톤"); //Intent로 Uri값 받기 from AlterActivity
        ringtone1 = RingtoneManager.getRingtone(this, ringtoneUri1);  //받은 Uri로 링톤 생성
        ringtone1.setStreamType(AudioManager.STREAM_ALARM);
        ringtone1.play();


        int requestCode = intent.getIntExtra("리퀘코드", 6699);//확인용 리퀘스트코드(SubActivity로부터 받아옴)
        int newRequestCode = intent1.getIntExtra("리퀘스트코드", 66996699);//확인용 리퀘스트코드(AlterActivity로부터 받아옴)
        Log.e("알람이 울림!", "requestCode : "+requestCode+", newRequestCode : "+newRequestCode);


        //볼륨 계속 체크하며 최대로 높히기 위해 Thread구현
        Runnable maxVolumeUp = new Runnable() {
            @Override
            public void run() {
                try
                {
                    while (true)
                    {
                        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setSpeakerphoneOn(true);
                        int volume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);  // 현재 볼륨 가져오기
                        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);  //최대 볼륨 가져오기

                        if(volume<maxVolume)
                        {    //볼륨 최대로 만들기
                            audioManager.setStreamVolume(AudioManager.STREAM_ALARM, (int)(audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)*1.0), AudioManager.FLAG_PLAY_SOUND);
                        }
                        Thread.sleep(600);
                    }
                }
                catch (Exception e){}
            }
        };

        maxVolumeUpThread = new Thread(maxVolumeUp);
        maxVolumeUpThread.start();  //최대볼륨 체크 스레드 시작


        long[] pattern = {250, 90, 250, 90, 250}; //진동 패턴
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(pattern, 3); //패턴에 따라 진동 울리기


        Calendar calendar = Calendar.getInstance();//현재 시간알려줄 Calendar생성
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); //현재 요일 가져오기

        if(newRequestCode==66996699) //newRequestCode가 기본값(빈값)이라면,(SubActivity로부터 인텐트를 넘겨받았다면,
        {   Log.e("ㅛㅛ", " "+sun1+" "+mon2+" "+tue3+" "+wed4+" "+thu5+" "+fri6+" "+sat7);
            if ((sun1 == 6999 && mon2 == 6999 && tue3 == 6999 && wed4 == 6999 && thu5 == 6999 && fri6 == 6999 && sat7 == 6999)
                    || (sun1 == 0 && mon2 == 0 && tue3 == 0 && wed4 == 0 && thu5 == 0 && fri6 == 0 && sat7 == 0)) {
                //SubActivity로부터 반복안함으로 넘어왔다면 걍 패스시키고 바로 알람 울리기
            } else {
                if ((dayOfWeek == 1 && sun1 != 1)) {
                    ringtone.stop();    //오늘이 일욜이지만 일욜에 체크를 안했을 때
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                } else if ((dayOfWeek == 2 && mon2 != 2) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                } else if ((dayOfWeek == 3 && tue3 != 3) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                } else if ((dayOfWeek == 4 && wed4 != 4) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                } else if ((dayOfWeek == 5 && thu5 != 5) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                } else if ((dayOfWeek == 6 && fri6 != 6) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                } else if ((dayOfWeek == 7 && sat7 != 7) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                } else {
                }
            }
        }
        else   //newRequestCode가 기본값(빈값)이 아니라면,(AlterActivity로부터 인텐트를 넘겨받았다면)
        {   Log.e("ㅠㅠ", " "+sun11+" "+mon22+" "+tue33+" "+wed44+" "+thu55+" "+fri66+" "+sat77);
            if ((sun11 == 6999 && mon22 == 6999 && tue33 == 6999 && wed44 == 6999 && thu55 == 6999 && fri66 == 6999 && sat77 == 6999)
                    || (sun11 == 0 && mon22 == 0 && tue33 == 0 && wed44 == 0 && thu55 == 0 && fri66 == 0 && sat77 == 0)) {
                //AlterActivity로부터 반복안함으로 넘어왔다면 걍 패스시키고 바로 알람 울리기
            } else {
                if ((dayOfWeek == 1 && sun11 != 1) ) {
                    ringtone.stop();    //오늘이 일욜이지만 일욜에 체크를 안했을 때
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                    Log.e("올터", "1111111111111111111111111111");
                } else if ((dayOfWeek == 2 && mon22 != 2) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                    Log.e("올터", "22222222222222222222222222222");
                } else if ((dayOfWeek == 3 && tue33 != 3) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                    Log.e("올터", "33333333333333333333333333");
                } else if ((dayOfWeek == 4 && wed44 != 4) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                    Log.e("올터", "4444444444444444444444444444");
                } else if ((dayOfWeek == 5 && thu55 != 5) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                    Log.e("올터", "55555555555555555555555555");
                } else if ((dayOfWeek == 6 && fri66 != 6) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                    Log.e("올터", "66666666666666666666666666");
                } else if ((dayOfWeek == 7 && sat77 != 7) ) {
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();
                    finish();
                    Log.e("올터", "77777777777777777777777777777777777777777");
                } else {
                }
            }
        }

        try
        {
            Log.i("요일 확인여부를 위한 쓰레드", "1초만 쉬자");
            Thread.sleep(1000);
        }
        catch (Exception e) {
        }

        isScreenOn(this);//잠금화면 깨우는 메소드
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED //화면이 잠겨있을 때 윈도우를 띄운다.
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        //애니메이션 알람시계 구현
        ImageView alarmClock = (ImageView) findViewById(R.id.alarmClock);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alarm_animation);
        alarmClock.startAnimation(animation);

        //계산문제 생성
        TextView calProblem = (TextView)findViewById(R.id.calProblem);
        double randomValue = Math.random(); //난수 생성
        double randomValue2 = Math.random(); //각각의 값을 상이하게하기 위해 일부러 3번 생성
        double randomValue3 = Math.random(); //난수 생성
        randomInt = (int)(randomValue*8)+2; // 2~9
        randomInt2 = (int)(randomValue2*90)+10;// 10~99 (int)(randomValue*(최댓값-최솟값+1))+최솟값
        randomInt3 = (int)(randomValue3*89)+11;// 11~99
        calProblem.setText("( "+randomInt+" X "+randomInt2+" ) + "+randomInt3); //계산문제 셋팅

        //현재시간 표시해주기
        TextView currentTime = (TextView)findViewById(R.id.currentTime);
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        calendar.setTimeZone(timeZone);//한국시간으로 TimeZone설정
        int hour1 = calendar.get(Calendar.HOUR_OF_DAY); //현재시간의 '시간'값 가져오기
        int minute1 = calendar.get(Calendar.MINUTE); //현재시간의 '분'값 가져오기
        if(hour1<12)
        {//0시부터 ~ 11시라면 앞에 "오전"을 나타낸다
            if(minute1==0)
            {//0분이라는 표기값 없애주기 위한 설정
                currentTime.setText("오전 "+hour1+"시");
            }
            else
                currentTime.setText("오전 "+hour1+"시 "+minute1+"분");
        }
        else if(hour1>11)
        {//12시부터 23시라면 앞에 "오후"를 나타낸다.
            if(hour1==12)
            {
                if(minute1==0)
                {//0분이라는 표기값 없애주기 위한 설정
                    currentTime.setText("오후 12시");
                }
                else
                    currentTime.setText("오후 12시 " + minute1 + "분");
            }
            else {
                for (int i = 13; i < 24; i++) {//24시간제를 12시간제로 표시하기 위해 반복문을 돌려서 13시부터 24시까지 바꿔준다
                    if (hour1 == i) {
                        if(minute1==0)
                        {
                            currentTime.setText("오후 " + (i - 12) + "시");
                        }
                        else
                            currentTime.setText("오후 " + (i - 12) + "시 " + minute1 + "분");
                    }
                }
            }
        }




        //링톤자리




        String label = intent.getStringExtra("라벨");//SubActivity로부터 받아온 라벨
        TextView labelText = (TextView)findViewById(R.id.label);
        if(label != null){
        labelText.setText(label);}

        String label2 = intent1.getStringExtra("라벨2");//AlterActivity로부터 받아온 라벨
        if(label2 != null){
        labelText.setText(label2);}



        //리퀘스트코드 자리



        //스레드자리






        //바이브 자리





        //정답 확인버튼 클릭구현
        Button OKBtn = (Button)findViewById(R.id.OKBtn);
        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int correctAnswer = (randomInt*randomInt2)+randomInt3; //문제의 진짜 정답
                String correctAnswer2 = ""+correctAnswer; //진짜정답을 String값으로 바꿔줌(사용자 입력 정답이랑 비교하기 위해)

                EditText answerText = (EditText)findViewById(R.id.answerText);
                String answer = answerText.getText().toString(); //사용자가 적은 정답

                if(correctAnswer2.equals(answer)) //String의 동일 비교는 ==대신 .equals를 쓰도록 한다.
                { //정답이라면 알람해제
                    ringtone.stop();
                    ringtone1.stop();
                    maxVolumeUpThread.interrupt();
                    vib.cancel();

                    Intent intent = new Intent(AlarmDialogActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "알람이 해제되었습니다", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "정답이 아닙니다", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override //액티비티 바깥영역 클릭해도 안꺼지게 하기
    public boolean onTouchEvent(MotionEvent event)
    {
        return false;
    }

    @Override   //뒤로가기 키 안먹히게 하기
    public void onBackPressed()
    {

    }

    //잠금화면 깨우는 메소드
    public static boolean isScreenOn(Context context)
    {
        return ((PowerManager)context.getSystemService(Context.POWER_SERVICE)).isScreenOn();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.e("리스타트 시작!", "!!");
    }

    @Override
    protected void onStop(){
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("디스트로이!", "!!");
//        Intent intent = new Intent(AlarmDialogActivity.this, AlarmDialogActivity.class);
//        startActivity(intent);
    }
}
