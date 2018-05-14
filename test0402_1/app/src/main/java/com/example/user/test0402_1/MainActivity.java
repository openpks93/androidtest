package com.example.user.test0402_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0,0,0, "새파일").setIcon(R.drawable.ic_launcher);
//        SubMenu subMenu = menu.addSubMenu("열기");
//        subMenu.add(0,10,0, "저장");
//        subMenu.add(0,20,0, "출력");
//        return super.onCreateOptionsMenu(menu);
        //getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem1 = menu.add("1. 열기");
        MenuItem menuItem2 = menu.add("2. 저장");
        MenuItem menuItem3 = menu.add("3. 닫기");

        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                setTitle("1. 열기 메뉴를 선택했습니다. ");
                return true;
            }
        });
        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                setTitle("2. 저장 메뉴를 선택했습니다. ");
                return true;
            }
        });
        menuItem3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                setTitle("3. 닫기 메뉴를 선택했습니다. ");
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        String str="";
//        Toast toast;
//        switch (item.getItemId()){
//            case 0 : str = item.getTitle().toString();
//            case 10 : str = item.getTitle().toString();
//            case 20 : str = item.getTitle().toString();
//        }
//        toast = Toast.makeText(MainActivity.this, str + "메뉴를 선택했습니다. ", Toast.LENGTH_LONG);
//        toast.show();
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
