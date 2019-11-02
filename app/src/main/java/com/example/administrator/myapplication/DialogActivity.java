package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by ileem on 2016-08-18.
 */
public class DialogActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_activity);

        Button OKBtn = (Button) findViewById(R.id.OKBtn);
        Button cancelBtn = (Button) findViewById(R.id.cancleBtn);

        //확인버튼 클릭 구현
        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                Intent intent = new Intent(DialogActivity.this, SubActivity.class);
                intent.putExtra("시", hour);
                intent.putExtra("분", minute);

                setResult(RESULT_OK, intent);

                finish();
            }
        });

        //취소버튼 클릭 구현
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogActivity.this, SubActivity.class);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(DialogActivity.this, MainActivity.class);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
