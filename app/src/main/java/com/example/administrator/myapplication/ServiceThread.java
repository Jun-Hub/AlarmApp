package com.example.administrator.myapplication;


import android.os.Handler;
import android.os.Message;
/*
 * 서비스에서 사용할 스레드 클래스
 */
public class ServiceThread extends Thread{
    //서비스 객체의 핸들러
    Handler handler;
    boolean isRun = true;
    //예제로 서비스할 메세지
    String msg[] = {"알람시간입니다!!", "알람시간입니다!", "알람시간입니다!~", "알람시간입니다~~", "일어나세요"};

    //생성자
    public ServiceThread(Handler handler){
        this.handler = handler;
    }
    //스레드 정지 시키는 메소드
    public void stopForever(){
        synchronized (this) {
            this.isRun = false;
            notify();
        }
    }
    int index;
    //스레드 본체
    public void run(){

        try{
            Thread.sleep(10000); //10초씩 쉰다.
        }catch (Exception e) {}
        //반복적으로 수행할 작업을 한다.
        for(int i=0; i<5; i++) {
            //핸들러로 들어오는 메시지별로 다르게 동작.
            String message = msg[index];
            index++;
            //핸들러에 전달할 Message 객체 생성하기
            Message m = new Message();

            if(index==4) index = 0;
            if(index==3) {
                m.what = 1;
                m.obj = message;
            }else{
                m.what = 0;
                m.obj = message;
            }
            //핸들러에 메세지를 보낸다.
            handler.sendMessage(m);
            try{
                Thread.sleep(1500); //10초씩 쉰다.
            }catch (Exception e) {}
        }
    }
}