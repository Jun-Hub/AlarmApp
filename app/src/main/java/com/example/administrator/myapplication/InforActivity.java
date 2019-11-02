package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016-08-03.
 */
public class InforActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_activity);

        Button uriBtn = (Button) findViewById(R.id.button2);
        Button recBtn = (Button) findViewById(R.id.button);

        uriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.google.android.googlequicksearchbox"));
                startActivity(intent);
            }
        });

        recBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendMsg = "*앱스토어 80개국 1위 알람*\n";
                String sendMsg2 = "더 잘 수 있으면 자 봐!\n";
                String sendMsg3 = "무조건 침대에서 나오게 만드는 알람\n";
                String sendMsg4 = "절대 익숙해지지 않는 강력한 알람 해제 방법\n";
                String sendMsg5 = "다운로드 --> https://play.google.com/store/apps/details?id=com.google.android.googlequicksearchbos";

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "[알람 어플]\n");
                intent.putExtra(Intent.EXTRA_TEXT, sendMsg+sendMsg2+sendMsg3+sendMsg4+sendMsg5);
               // intent.setPackage("com.kakao.talk");
                //intent.setPackage("com.facebook.orca");
              //  intent.setPackage("com.facebook.katana");
                Intent chooser = Intent.createChooser(intent, "추천하기");
                startActivity(chooser);

//                startActivity(intent);
            }
        });
    }
}
