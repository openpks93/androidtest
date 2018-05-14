package com.example.user.musictest01_03;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.time.Year;

public class YearsActivity extends AppCompatActivity {

    int pos;
    SeekBar sb;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);

        final ImageButton yearsoldplay = (ImageButton) findViewById(R.id.yearsoldplay);
        final ImageButton yearsoldpause = (ImageButton) findViewById(R.id.yearsoldpause);
        final ImageButton gohome = (ImageButton) findViewById(R.id.backbutton);
        final MediaPlayer yearsold = MediaPlayer.create(this, R.raw.yearsold);
        final VusicView vusikView = (VusicView) findViewById(R.id.vusik);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        class MyThread extends Thread {
            @Override
            public void run() { // 쓰레드가 시작되면 콜백되는 메서드
                // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
                while(isPlaying) {
                    sb.setProgress(yearsold.getCurrentPosition());
                }
            }
        }
        sb = (SeekBar)findViewById(R.id.seekbar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                yearsold.seekTo(ttt);
                yearsold.start();
                new MyThread().start();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                yearsold.pause();
            }
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    yearsoldplay.setVisibility(View.VISIBLE);
                    yearsoldpause.setVisibility(View.INVISIBLE);
                    isPlaying = false;
                    yearsold.stop();
                }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        yearsoldplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearsold.start();
                vusikView.start();
                int a = yearsold.getDuration(); // 노래의 재생시간(miliSecond)
                sb.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
                new MyThread().start(); // 씨크바 그려줄 쓰레드 시작
                isPlaying = true; // 씨크바 쓰레드 반복 하도록
                yearsold.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        yearsold.release();
                        yearsold.stop();
                    }
                });
            }
        });
        yearsoldpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearsold.stop();
                vusikView.stopNotesFall();
            }
        });
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearsold.stop();
                vusikView.stopNotesFall();
                Intent yearsback = new Intent(YearsActivity.this, MainActivity.class);
                YearsActivity.this.startActivity(yearsback);
            }
        });

    }
}
