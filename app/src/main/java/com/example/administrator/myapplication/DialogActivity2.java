package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by ileem on 2016-08-18.
 */
public class DialogActivity2 extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_activity2);

        Button cancleBtn = (Button)findViewById(R.id.cancleBtn);
        Button OKBtn = (Button)findViewById(R.id.OKBtn);
        final CheckBox mon = (CheckBox)findViewById(R.id.mon);
        final CheckBox tue = (CheckBox)findViewById(R.id.tue);
        final CheckBox wed = (CheckBox)findViewById(R.id.wed);
        final CheckBox thu = (CheckBox)findViewById(R.id.thu);
        final CheckBox fri = (CheckBox)findViewById(R.id.fri);
        final CheckBox sat = (CheckBox)findViewById(R.id.sat);
        final CheckBox sun = (CheckBox)findViewById(R.id.sun);


        //취소버튼 클릭 구현
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogActivity2.this, SubActivity.class);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        //각 요일 체크여부를 최종 판단하기 위해 intent를 전역변수로 선언
        final Intent intent = new Intent(DialogActivity2.this, SubActivity.class);

        //월요일 체크여부 판단
        mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    String monText = mon.getText().toString();
                    intent.putExtra("월요일", monText);
                    intent.putExtra("월요일2", 2);
                }
                else if(!isChecked)
                {
                    intent.putExtra("월요일", "");
                }
            }
        });

        //화요일 체크여부 판단
        tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    String tueText = tue.getText().toString();
                    intent.putExtra("화요일", tueText);
                    intent.putExtra("화요일3", 3);
                }
                else if(!isChecked)
                {
                    intent.putExtra("화요일", "");
                }
            }
        });

        //수요일 체크여부 판단
        wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    String wedText = wed.getText().toString();
                    intent.putExtra("수요일", wedText);
                    intent.putExtra("수요일4", 4);
                }
                else if(!isChecked)
                {
                    intent.putExtra("수요일", "");
                }
            }
        });

        //목요일 체크여부 판단
        thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    String thuText = thu.getText().toString();
                    intent.putExtra("목요일", thuText);
                    intent.putExtra("목요일5", 5);
                }
                else if(!isChecked)
                {
                    intent.putExtra("목요일", "");
                }
            }
        });

        //금요일 체크여부 판단
        fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    String friText = fri.getText().toString();
                    intent.putExtra("금요일", friText);
                    intent.putExtra("금요일6", 6);
                }
                else if(!isChecked)
                {
                    intent.putExtra("금요일", "");
                }
            }
        });

        //토요일 체크여부 판단
        sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    String satText = sat.getText().toString();
                    intent.putExtra("토요일", satText);
                    intent.putExtra("토요일7", 7);
                }
                else if(!isChecked)
                {
                    intent.putExtra("토요일", "");
                }
            }
        });

        //일요일 체크여부 판단
        sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    String sunText = sun.getText().toString();
                    intent.putExtra("일요일", sunText);
                    intent.putExtra("일요일1", 1);
                }
                else if(!isChecked)
                {
                    intent.putExtra("일요일", "");
                }
            }
        });

        //확인버튼 클릭 구현
        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(DialogActivity2.this, MainActivity.class);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
