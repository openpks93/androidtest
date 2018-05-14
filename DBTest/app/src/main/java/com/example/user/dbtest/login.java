package com.example.user.dbtest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //db를 사용해야되기 때문에 helper에 연결시켜준다
        helper = new MySQLiteOpenHelper(login.this,
                "customer.db",
                null,
                1);

        final EditText id = (EditText)findViewById(R.id.editText4);
        final EditText pw = (EditText)findViewById(R.id.editText5);
        Button button = (Button)findViewById(R.id.button5);
        final TextView textView = (TextView)findViewById(R.id.textView5);
        //버튼을 클릭하게 됨과 동시에 db에서 확인을하게 된다
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getReadableDatabase();
                String name = id.getText().toString();
                String age = pw.getText().toString();
                String db_name = null;
                String db_age = null;
                //쿼리문 설정
                Cursor c = db.rawQuery("select * from employee" , null);
                //while문으로 역시 원하는값을 변수에 넣어줌
                while (c.moveToNext()){
                    db_name = c.getString(c.getColumnIndex("name"));
                    db_age = c.getString(c.getColumnIndex("age"));
                }
                //그다음 조건문을 걸어줘서 조건에 부합하다면 성공 메세지와 함께 다음 화면으로 이동
                if(name.equals(db_name) && age.equals(db_age)){
                    textView.append("\n로그인 성공!!!");
                    Intent intent = new Intent(getApplicationContext(), listview.class);
                    startActivity(intent);
                }else if(TextUtils.isEmpty(name) || TextUtils.isEmpty(age)){//유효성검사 해당 변수에 안에 값의 null이거나 비어 있는지를 체크
                    Toast.makeText(login.this,"ID와 PW를 입력하세요",Toast.LENGTH_LONG).show();
                }else{
                    //아니라면 실패라는 메세지와 함께 화면 이동은 발생하지 않음
                    textView.append("\n실패");
                }
            }
        });

    }
}