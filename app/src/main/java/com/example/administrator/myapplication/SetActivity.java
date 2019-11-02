package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-08-02.
 */
public class SetActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity);

        ImageButton btn = (ImageButton) findViewById(R.id.imageButton);
        ImageButton mailBtn = (ImageButton) findViewById(R.id.imageButton2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetActivity.this, InforActivity.class);
                startActivity(intent);
            }
        });

        mailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SetActivity.this, "개발자에게 의견 보내기", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_SEND);
               intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"ileemail2@hanmail.net" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "알람 어플 관련 메일");
                intent.putExtra(Intent.EXTRA_TEXT, "문제가 생겼을 경우 어떤 상황에서 어떤 문제가 생겼는지 알려주시면 개선에 많은 도움이 됩니다 :)");
                startActivity(Intent.createChooser(intent, "Send e-mail"));
            }
        });


    }
}
