package com.example.user.test0409_1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static final String Menu_Str = "선택";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu_Str);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(Menu_Str.equals(item.getTitle())){
            final Timer timer = new Timer();
            final ProgressDialog pro = new ProgressDialog(this);
            pro.setTitle("프로세스 다이얼로그");
            pro.setMax(1000);
            pro.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pro.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                }
            });
            pro.show();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    pro.incrementProgressBy(10);
                    if(pro.getProgress() >= pro.getMax()){
                        pro.dismiss();
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 1000, 1000);
        }
        return super.onOptionsItemSelected(item);
    }
}
