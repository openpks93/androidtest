package com.example.user.dbtest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

//글쓰기페이지 역할
public class write extends AppCompatActivity {

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    private EditText sub;
    private EditText name;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);

        sub = (EditText)findViewById(R.id.editText6);
        name = (EditText)findViewById(R.id.editText7);
        content = (EditText)findViewById(R.id.editText8);
        Button button = (Button)findViewById(R.id.button6);

        //사용할 db를 연동 시켜준다
        helper = new MySQLiteOpenHelper(write.this, "customer.db", null, 1);
        db = helper.getWritableDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //글을 다 입력하고 클릭하는 동시에
                GregorianCalendar calendar = new GregorianCalendar();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);
                int sec = calendar.get(Calendar.SECOND);
                //변수에 각각의 값을 담아주고

                String now = year + "-" + month + "-" + day + "\n" + hour + ":" + min + ":" + sec; //글쓴시간을 저장

                db.execSQL("insert into bord values(null,'"+sub.getText().toString()+"','" +name.getText().toString()+ "','"+now+"','" +content.getText().toString()+ "');");
                //db에 insert시켜준다

                Intent intent = new Intent(getApplicationContext(), listview.class);
                startActivity(intent);
                finish();
                //그다음 값을 intent에 실어주고 intent를 실행시켜준후 finish로 페이지를 닫아준다
            }
        });

    }
}
