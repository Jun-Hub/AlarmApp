package com.example.administrator.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;
//manifest에 등록해야함.
public class MyService extends Service {

    //알림을 띄울 것이므로
    NotificationManager notiManager;
    //thread
    ServiceThread thread;
    //알림을 중복을 피하기 위한 상태값
    final int MyNoti = 0;

    @Override
    public IBinder onBind(Intent intent) {
        //할일 없음
        return null;
    }

    //서비스가 시작되면 onstartcommand가 호출된다.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
  /*
   * 서비스에서 수행할 작업을 수행시키고 재빨리 리턴한다.
   * 시간이 오래 걸리면 서비스가 취소된다.
   * 액티비티는 생명주기가 있지만 서비스는 생명주기가 없다(메모리가 부족하지 않다면 계속 백그라운드에 떠있다.)
   * ex. 카카오톡의 경우 새로운 메시지가 오는지 지속적으로 관찰하는 작업
   */

        //    NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //백그라운드에서 작업할 내용을 초기화 하고 시작한다.
        thread = new ServiceThread(handler);
        //스레드 시작하기
        thread.start();

        return START_STICKY;

    }
    //서비스가 종료될 때 할 작업
    public void onDestroy() {
        //스레드 종료시키기
        thread.stopForever();
        thread=null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    //백그라운드 스레드로부터 메세지를 받을 핸들러 객체
    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            //what으로 메시지를 보고 obj로 메시지를 받는다.(형변환필수)

            //notification 객체 생성(상단바에 보여질 아이콘, 메세지, 도착시간 정의)
            //  Notification noti = new Notification(R.drawable.asd22//알림창에 띄울 아이콘
            //          , "메시지 도착!", //간단 메세지
            //          System.currentTimeMillis()); //도착 시간
            //기본으로 지정된 소리를 내기 위해
            //   noti.defaults = Notification.DEFAULT_SOUND;
            //알림 소리를 한번만 내도록
            //   noti.flags = Notification.FLAG_ONLY_ALERT_ONCE;
            //확인하면 자동으로 알림이 제거 되도록
            //   noti.flags = Notification.FLAG_AUTO_CANCEL;
            //사용자가 알람을 확인하고 클릭했을때 새로운 액티비티를 시작할 인텐트 객체
            Intent intent = new Intent(MyService.this, MainActivity.class);
            //새로운 태스크(Task) 상에서 실행되도록(보통은 태스크1에 쌓이지만 태스크2를 만들어서 전혀 다른 실행으로 관리한다)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //
            NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            //인텐트 객체를 포장해서 전달할 인텐트 전달자 객체
            PendingIntent pendingI = PendingIntent.getActivity(MyService.this, 0, intent, 0);
            //받아온 메시지 담기
            String arrivedMsg = (String)msg.obj;
            //상단바를 드래그 했을때 보여질 내용 정의하기
            //    noti.setLatestEventInfo(MyService.this, "[새 메세지]", arrivedMsg, pendingI);
            Notification.Builder builder = new Notification.Builder(MyService.this)
                    .setContentIntent(pendingI)
                    .setContentTitle("알람 알리미")
                    .setSmallIcon(R.drawable.alldelete)
                    .setContentText(arrivedMsg)
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                    .setTicker("알람 알리미 - 일어나세요!");
            Notification noti = builder.build();
            //알림창 띄우기(알림이 여러개일수도 있으니 알림을 구별할 상수값, 여러개라면 상수값을 달리 줘야 한다.)
            nm.notify(MyNoti, noti);
            //토스트 띄우기
            Toast.makeText(MyService.this, arrivedMsg, Toast.LENGTH_SHORT).show();


        }

    };


}
