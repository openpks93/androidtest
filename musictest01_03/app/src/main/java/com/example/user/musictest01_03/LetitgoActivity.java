package com.example.user.musictest01_03;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class LetitgoActivity extends AppCompatActivity {

    int pos;
    SeekBar sb;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letitgo);

        final ImageButton letitplay = (ImageButton) findViewById(R.id.danzaplay);
        final ImageButton letitpause = (ImageButton) findViewById(R.id.letitpause);
        final ImageButton gohome = (ImageButton) findViewById(R.id.gohome);
        final MediaPlayer letitgo = MediaPlayer.create(this, R.raw.letitgo);
        final VusicView vusikView = (VusicView) findViewById(R.id.vusik);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        class MyThread extends Thread {
            @Override
            public void run() { // 쓰레드가 시작되면 콜백되는 메서드
                // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
                while(isPlaying) {
                    sb.setProgress(letitgo.getCurrentPosition());
                }
            }
        }
        sb = (SeekBar)findViewById(R.id.seekbar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                letitgo.seekTo(ttt);
                letitgo.start();
                new MyThread().start();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                letitgo.pause();
            }
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    letitplay.setVisibility(View.VISIBLE);
                    letitpause.setVisibility(View.INVISIBLE);
                    isPlaying = false;
                    letitgo.stop();
                }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        letitplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Context bc =  v.getContext();
                //final MediaPlayer beliver = MediaPlayer.create(bc, R.raw.believer);
                letitgo.start();
                vusikView.start();
                int a = letitgo.getDuration(); // 노래의 재생시간(miliSecond)
                sb.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
                new MyThread().start(); // 씨크바 그려줄 쓰레드 시작
                isPlaying = true; // 씨크바 쓰레드 반복 하도록
                letitgo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        letitgo.release();
                        letitgo.stop();
                        vusikView.stopNotesFall();
                    }
                });
            }
        });
        // pause 버튼
        letitpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letitgo.stop();
                vusikView.stopNotesFall();
            }
        });
        //gohome 버튼
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent letitback = new Intent(LetitgoActivity.this, MainActivity.class);
                LetitgoActivity.this.startActivity(letitback);
                letitgo.stop();
                vusikView.stopNotesFall();
            }
        });
    }
}
