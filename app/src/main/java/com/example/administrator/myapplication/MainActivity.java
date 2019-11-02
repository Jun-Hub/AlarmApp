package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    final int REQUEST_ACT = 1;
    final int REQUEST_ACT2 = 1;

    //어댑터 생성
    ListViewAdapter MyAdapter = new ListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton clock = (ImageButton) findViewById(R.id.imageButton);
        ImageButton set = (ImageButton) findViewById(R.id.imageButton2);

        //커스텀 리스트뷰 생성
       ListView MyList=(ListView)findViewById(R.id.listView);
        //생성한 리스트뷰에 어댑터 적용
        MyList.setAdapter(MyAdapter);

        /*//GSON 객체생성
        Gson gson = new Gson();

        // 지난번 저장해놨던 사용자 입력값을 꺼내서 보여주기
        SharedPreferences sf = getSharedPreferences("test", MODE_PRIVATE);
        String ex = sf.getString("name", "빈값이라능 ㅠㅠ"); // 키값으로 꺼냄, 뒤에것은 디폴트값

        Member mee = gson.fromJson(ex, Member.class);  //파싱해놨던 값인 ex를 다시 클래스로 변환

        for(TimeAndDay timeAndDay : mee.getTimeAndDays())     //반복문 이용하여 저장해놨던 ex(스트링으로 변환한 클래스)값들을
        {                                                     //그대로 리스트뷰에 추가하며 복귀
            MyAdapter.addItem(timeAndDay.getTime(), timeAndDay.getDay(), timeAndDay.getRequestCode());
        }*/


//        //올삭제 확인여부 다이얼로그 구현
//        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//
//        dialogBuilder.setTitle("Question");
//        dialogBuilder.setMessage("알람을 전부 삭제하시겠습니까?");
//        dialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                new ProgressDlgTest(MainActivity.this).execute(MyAdapter.getCount());
//                MyAdapter.removeAll();
//                MyAdapter.notifyDataSetChanged();
//            }
//        });
//        dialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Negative Button을 생성
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss(); // "아니오" button이 받은 DialogInterface를 dismiss 하여 MainView로 돌아감
//            }
//        });
//
//        allDelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogBuilder.show();
//            }
//        });


        //리스트뷰 클릭시 구현
        MyList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int requestCode = MyAdapter.getItemRequestCode(position);

                //클릭된 위치 intent로 넘겨주기
                Intent intent = new Intent(MainActivity.this, AlterActivity.class);
                intent.putExtra("위치", position);
                intent.putExtra("리퀘코드", requestCode);

                startActivityForResult(intent, REQUEST_ACT2);
                overridePendingTransition(R.anim.start_enter2, R.anim.start_exit);
            }
        });



//        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                // get item
//                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
//
//                String alarmName = item.getAlarmName() ;
//                String btnName = item.getBtnName() ;
//
//                Toast.makeText(MainActivity.this, alarmName+btnName, Toast.LENGTH_SHORT).show();
//
//
//            }
//        }) ;


        // 알람 추가 버튼
        Button addBtn = (Button)findViewById(R.id.button) ;

        //알람 추가 버튼 클릭 구현
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(MainActivity.this, SubActivity.class);
                startActivityForResult(inten, REQUEST_ACT2);
                overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });




        //설정 버튼 클릭 구현
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(MainActivity.this, SetActivity.class);
                startActivity(inten);

//                    //인텐트 객체 생성
//                Intent intent = new Intent(MainActivity.this, MyService.class);
//                //서비스 종료시키기
//                stopService(intent);

            }
        });

        //간편알람 버튼클릭 구현
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialogActivity4.class);
                startActivity(intent);
            }
        });


        //TODO 알람 설정 토스트 구현하기



//        // Intent를 얻어오고 액션과 MIME 타입을 가져온다.
//        Intent intent0 = getIntent();
//        String action = intent0.getAction();
//        String type = intent0.getType();
//
//        // Intent의 Action 종류에 따라 비교 후 해당 함수 수행.
//        if (Intent.ACTION_SEND.equals(action) && type != null) {
//            if ("text/plain".equals(type)) {
//                handleSendText(intent0);
//
//            } else if (type.startsWith("image/")) {
//                handleSendImage(intent0);
//            }
//        }  else {
//
//        }

    }



//    public void mOnClick( View v )
//    {
//        EditText eName = (EditText) findViewById( R.id.et_name );
//        EditText eAge = (EditText) findViewById( R.id.et_age );
//
//        String name = eName.getText().toString();
//        try
//        {
//            int age = Integer.parseInt( eAge.getText().toString() );
//
//            // 문자열은 ''로 감싸야 한다.
//            String query = String.format(
//                    "INSERT INTO %s VALUES ( null, '%s', %d );", TABLE_NAME, name, age );
//            db.execSQL( query );
//
//            // 아래 메서드를 실행하면 리스트가 갱신된다. 하지만 구글은 이 메서드를 deprecate한다. 고로 다른 방법으로 해보자.
//            // cursor.requery();
//            cursor = db.rawQuery( querySelectAll, null );
//            myAdapter.changeCursor( cursor );
//        }
//        catch( NumberFormatException e )
//        {
//            Toast.makeText( this, "나이는 정수를 입력해야 합니다", Toast.LENGTH_LONG).show();
//        }
//
//        eName.setText( "" );
//        eAge.setText( "" );
//
//        // 저장 버튼 누른 후 키보드 안보이게 하기
//        InputMethodManager imm =
//                (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
//        imm.hideSoftInputFromWindow( eAge.getWindowToken(), 0 );
//    }


//        // Intent의 Type이 text일 경우 수행
//        void handleSendText(Intent intent) {
//            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
//            if (sharedText != null) {
//                TextView text = (TextView)findViewById(R.id.getText);
//                text.setText(sharedText);
//            }
//        }
//
//        // Intent의 Type이 image일 경우 수행
//        void handleSendImage(Intent intent) {
//            Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
//            if (imageUri != null) {
//                ImageView image = (ImageView)findViewById(R.id.getImage);
//                image.setImageURI(imageUri);
//            }
//        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ACT)
        {
            if(resultCode == 0) {
                String text3 = data.getStringExtra("시간");
                String text4 = data.getStringExtra("요일");
                int num = data.getIntExtra("넘버",RESULT_OK);
                int rCode = data.getIntExtra("리퀘스트코드", 69696969);

                if (text3 == null || text4 == null) {
                    MyAdapter.modifyItem(text3, text4, rCode, num);
                    MyAdapter.notifyDataSetChanged();
                } else {
                    MyAdapter.modifyItem(text3, text4, rCode, num);
                    MyAdapter.notifyDataSetChanged();
                }
            }

            if(resultCode==1)
            {
                int num = data.getIntExtra("넘버",RESULT_OK);

                MyAdapter.removeItem(num);
                MyAdapter.notifyDataSetChanged();
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "REQUEST_ACT가 아님", Toast.LENGTH_SHORT).show();
        }

        if(requestCode == REQUEST_ACT2)
        {
            String text = data.getStringExtra("입력한 text");   //알람추가 Activity(SubActivity)의 시간
            String text2 = data.getStringExtra("입력한 text2");  //알람추가 Activity(SubActivity)의 요일
            int rCode = data.getIntExtra("리퀘스트코드", 696969);

            Log.e("여긴 MainActivity! 받아온 리퀘코드 값 : ", ""+rCode);

            if(text==null||text2==null){ }

            else {
                // 아이템 추가.
                MyAdapter.addItem(text, text2, rCode);
                // listview 갱신
                MyAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override

    protected void onDestroy() {

        super.onDestroy();

        //Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();

    }


    @Override

    protected void onPause() {

        super.onPause();

       // Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onRestart() {

        super.onRestart();
    }


    @Override

    protected void onResume() {

        super.onResume();

       // Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();

    }


    @Override

    protected void onStart() {

        super.onStart();


       // Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();

    }


    @Override

    protected void onStop() {

        super.onStop();

        Gson gson = new Gson();  //먼저 Gson객체를 생성한다. ​

        List<TimeAndDay> tnd = new ArrayList<TimeAndDay>();  //리스트뷰 아이템 클래스를 어레이리스트 객체로 형성

        for(int i=0; i<MyAdapter.getCount(); i++) {  //반복문 이용하여 리스트뷰 아이템들을 고스란히 ArrayList객체인 tnd에 저장
            tnd.add(0,new TimeAndDay(MyAdapter.getItemTime(i), MyAdapter.getItemDay(i), MyAdapter.getItemRequestCode(i)));
        }    //리스트뷰 추가 함수(addItem)이 역순으로 구성되어 있으므로, 저장도 역순으로 함

        String ex = gson.toJson(new Member(tnd));   //tnd객체를 GSON을 이용하여 String값으로 파싱

        // SharedPreferences 에 (SharedPreferences 이름, 모드)를 저장하기
        SharedPreferences sp = getSharedPreferences("test", MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();//저장하려면 editor가 필요하므로 editor 생성

        editor.putString("name", ex);  //name이란 key값으로, 파싱한 tnd객체를 value값으로 넣어주기
        editor.commit(); // 파일에 최종 반영함
    }
}



