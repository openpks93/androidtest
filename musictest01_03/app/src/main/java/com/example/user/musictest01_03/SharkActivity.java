package com.example.user.musictest01_03;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class SharkActivity extends AppCompatActivity {

    int pos;
    SeekBar sb;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shark);


        final ImageButton sharkplay = (ImageButton) findViewById(R.id.sharkplay);
        final ImageButton sharkpause = (ImageButton) findViewById(R.id.sharkpause);
        final ImageButton gohome = (ImageButton) findViewById(R.id.backbutton);
        final MediaPlayer shark = MediaPlayer.create(this, R.raw.shark);
        final VusicView vusikView = (VusicView) findViewById(R.id.vusik);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        class MyThread extends Thread {
            @Override
            public void run() { // 쓰레드가 시작되면 콜백되는 메서드
                // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
                while(isPlaying) {
                    sb.setProgress(shark.getCurrentPosition());
                }
            }
        }
        sb = (SeekBar)findViewById(R.id.seekbar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                shark.seekTo(ttt);
                shark.start();
                new MyThread().start();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                shark.pause();
            }
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    sharkplay.setVisibility(View.VISIBLE);
                    sharkpause.setVisibility(View.INVISIBLE);
                    isPlaying = false;
                    shark.stop();
                }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        sharkplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shark.start();
                vusikView.start();
                int a = shark.getDuration(); // 노래의 재생시간(miliSecond)
                sb.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
                new MyThread().start(); // 씨크바 그려줄 쓰레드 시작
                isPlaying = true; // 씨크바 쓰레드 반복 하도록
                shark.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        shark.release();
                        shark.stop();
                        vusikView.stopNotesFall();
                    }
                });
            }
        });
        sharkpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shark.stop();
                vusikView.stopNotesFall();
            }
        });
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharkback = new Intent(SharkActivity.this, MainActivity.class);
                SharkActivity.this.startActivity(sharkback);
                shark.stop();
                vusikView.stopNotesFall();
            }
        });
    }
}
