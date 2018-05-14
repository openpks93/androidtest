package com.example.user.dbtest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class listview extends AppCompatActivity {

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    ListView listView;
    itemListAdapter adapter;
    String num;
    String sub;
    String name;
    String day;
    String content;
    Button writebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        //list를 불러와야하기 때문에 db를 연결시켜준다
        helper = new MySQLiteOpenHelper(listview.this,
                "customer.db",
                null,
                1);

        //그리고 listview와 adapter를 설정해준다
        listView = (ListView)findViewById(R.id.listView);
        adapter = new itemListAdapter(this);

        String[] arr= new String[5];

        //쿼리문을 설정해주고
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from bord", null);
        //while문으로 원하는값을 변수에 담아준다
        //그리고 곧바로 뿌려준다
        while (c.moveToNext()){

            arr[0] = num = c.getString(c.getColumnIndex("Num"));
            arr[1] = sub = c.getString(c.getColumnIndex("Subject"));
            arr[2] = name = c.getString(c.getColumnIndex("Name"));
            arr[3] = day = c.getString(c.getColumnIndex("Day"));
            arr[4] = content = c.getString(c.getColumnIndex("Content"));

            adapter.additem(new Item(arr));
            arr = new String[5];

        }

        listView.setAdapter(adapter);

        //해당 listView를 클릭하게 되면 상세보기 화면으로 넘어가게끔 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), look.class);

                Item curItem = (Item)adapter.getItem(position);
                String[] curData = curItem.borditemdate();
                intent.putExtra("Item",curData);
                startActivity(intent);
            }
        });

        //글쓰기 버튼을 클릭하게 되면 글쓰기 화면으로 넘어게가끔 설정
        writebtn = (Button)findViewById(R.id.button7);
        writebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), write.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
