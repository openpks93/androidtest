package com.example.user.test0402_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv= (TextView) findViewById(R.id.textView);
        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                        View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,10,0, "1. 저장하기");
        menu.add(0,20,0, "2. 출력하기");
        menu.add(0,30,0, "3. 파일열기");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView tv= (TextView) findViewById(R.id.textView);
        switch (item.getItemId()){
            case 10 :
                tv.setText("저장하기 메뉴를 선택했습니다. ");
                tv.setTextColor(0xFFFF0000);
                return  true;
            case 20 :
                tv.setText("출력하기 메뉴를 선택했습니다. ");
                tv.setTextColor(0xFF00FF00);
                return  true;
            case 30 :
                tv.setText("파일열기 메뉴를 선택했습니다. ");
                tv.setTextColor(0xFF0000FF);
                return  true;
        }
        return super.onContextItemSelected(item);
    }

}
