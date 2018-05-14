package com.example.user.dbtest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    //db를 커넥션 해주기 위해 필요한 메소드
    MySQLiteOpenHelper helper;
    String databaseName;
    String tableName;
    String searchName;
    Button button;
    EditText editText;
    Button button2;
    EditText editText2;
    Button button3;
    EditText editText3;
    Button button4;
    TextView textView;
    boolean databaseCreated = false;
    boolean tableCreated = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //첫번째는 현재화면의 context를 지정
        //두번째는 db 파일명
        //세번째는 버전 번호
        helper = new MySQLiteOpenHelper(MainActivity.this,
                "customer.db",
                null,
                1);

        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);

        button2 = (Button)findViewById(R.id.button2);
        editText2 = (EditText)findViewById(R.id.editText2);

        button3 = (Button)findViewById(R.id.button3);
        editText3 = (EditText)findViewById(R.id.editText3);

        button4 = (Button)findViewById(R.id.button4);
        textView = (TextView)findViewById(R.id.textView);

        //첫번째 버튼은 db를 생성하거나 열어주는 역할
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText를 받아서 변수에 넣어줌
                databaseName = editText.getText().toString();
                //그 변수를 실행
                createDatabase(databaseName);
            }

        });

        //두번째 버튼은 db에 테이블을 생성하는 역할
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText를 받아서 변수에 넣어줌
                tableName = editText2.getText().toString();
                //그 변수를 실행
                createTable(tableName);
                //레코드 입력 메소드를 호출함
                int count = insertRecord();
                println(count + "records inserted.");
            }

        });

        //세번째 버튼은 테이블을 검색 하는 역할
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText를 받아서 변수에 넣어줌
                searchName = editText3.getText().toString();
                //그 변수를 실행
                searchTable(searchName);
            }

        });

        //네번째 버튼은 로그인 화면으로 이동해주는 역할
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

    }

    //현재 상태가 어떻게 되는지 textview에 표현해주는 기능
    private void println(String s) {
        textView.append("\n" + s);
    }

    //데이터베이스를 만들어주는 기능
    private void createDatabase(String name) {
        //현재 상태가 어떻게 되는지 textview에 뿌려줌
        println("creating database [" + name + "].");
        //db를 name값으로 생성해줌 (name값은 위에 databaseName 값을 가져옴)
        db = openOrCreateDatabase(name, MODE_WORLD_WRITEABLE, null);
        databaseCreated = true;
    }

    //테이블을 만들어주는 기능
    private void createTable(String name) {
        //현재 상태가 어떻게 되는지 textview에 뿌려줌
        println("creating table [" + name + "].");
        //위에 tableName값을 name으로 받아와서 테이블을 생성
        db.execSQL("create table "
                + name
                + "("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " phone text);");

        tableCreated = true;
    }

    //테이블을 검색해주는 기능
    private void searchTable(String searchName) {
        //db객체를 얻어옴(읽기)
        db = helper.getReadableDatabase();
        //db에 들어있는 값들은 Cursor로 받아줘야됨
        //Cursor c = db.query(searchName, null, null, null, null, null, null); <<이렇게 해줘도됨 하지만 밑에게 더 간단함
        Cursor c = db.rawQuery("select * from " + searchName, null);

        //받아와서 뿌려주는 작업은 while문을 이용해야됨
        //while문 안의 조건에 들어가있는 c.moveToNext()는 위에 담아온 Cursor에 다음 값이 있으면 계속 실행
        //만약에 다음값이 없다면 멈춘다는 뜻
        while (c.moveToNext()){
            //각자의 값을 맡는 타입의 변수를 만들어 넣어줌
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String phone = c.getString(c.getColumnIndex("phone"));
            //그리고 값을 뿌려줌
            println(searchName+" 테이블의 값 =\n이름 : " + name + " 나이 : " + age + " 번호 : " + phone);
        }

    }
    //테이블에 값을 넘어줌 insert (테이블 검색기능을 테스트해보기 위해서)
    private int insertRecord(){
        println("inserting records.");

        int count = 3;

        db.execSQL("insert into employee(name, age, phone) values ('john', 20, '010-1234-5678');");

        return count;
    }

}