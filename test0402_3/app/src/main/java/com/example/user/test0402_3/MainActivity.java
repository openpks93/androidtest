package com.example.user.test0402_3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "Hello Toast........", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("다이얼로그 디스플레이");
                builder.setMessage("다이얼로그를 디스플레이 합니다 ");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(MainActivity.this, "다이얼로그를 닫는다", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(MainActivity.this, "다이얼로그를 닫는다", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                builder.show();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("아이템을 선택하세요");

                final String[] items = {"itme1", "item2", "item3"};
                builder.setItems((CharSequence[]) items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(MainActivity.this, items[which]+ "를 선택했습니다. ", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                builder.show();
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("아이템을 선택하세요");

                final String[] items = {"itme1", "item2", "item3"};

                builder.setSingleChoiceItems((CharSequence[]) items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        temp = which;
                        Toast toast = Toast.makeText(MainActivity.this, items[which]+ "를 선택했습니다. ", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(MainActivity.this, items[temp]+ "를 선택했습니다. ", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                builder.show();
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean[] checked = {false, false, false};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("아이템을 선택하세요");

                final String[] items = {"itme1", "item2", "item3"};

                builder.setMultiChoiceItems((CharSequence[]) items, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checked[which] = isChecked;
                    }
                });
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = "";
                        for(int i = 0; i<checked.length; i++){
                            if(checked[i]){
                                str += "".equals(str) ? "" : ", ";
                                str += items[i];
                            }
                        }
                        Toast toast = Toast.makeText(MainActivity.this, str+ "를 선택했습니다. ", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                builder.show();
            }
        });
    }
}
