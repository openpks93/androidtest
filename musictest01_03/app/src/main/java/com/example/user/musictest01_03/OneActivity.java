package com.example.user.musictest01_03;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class OneActivity extends AppCompatActivity {

    int pos;
    SeekBar sb;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        final ImageButton pmacplay = (ImageButton) findViewById(R.id.pmacplay);
        final  ImageButton pmacpause = (ImageButton) findViewById(R.id.pmacpause);
        final ImageButton gohome = (ImageButton) findViewById(R.id.backbutton);
        final MediaPlayer pmacone = MediaPlayer.create(this, R.raw.pmacone);
        final VusicView vusikView = (VusicView) findViewById(R.id.vusik);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        class MyThread extends Thread {
            @Override
            public void run() { // 쓰레드가 시작되면 콜백되는 메서드
                // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
                while(isPlaying) {
                    sb.setProgress(pmacone.getCurrentPosition());
                }
            }
        }
        sb = (SeekBar)findViewById(R.id.seekbar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                pmacone.seekTo(ttt);
                pmacone.start();
                new MyThread().start();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                pmacone.pause();
            }
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    pmacplay.setVisibility(View.VISIBLE);
                    pmacpause.setVisibility(View.INVISIBLE);
                    isPlaying = false;
                    pmacone.stop();
                }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        pmacplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pmacone.start();
                vusikView.start();
                int a = pmacone.getDuration(); // 노래의 재생시간(miliSecond)
                sb.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
                new MyThread().start(); // 씨크바 그려줄 쓰레드 시작
                isPlaying = true; // 씨크바 쓰레드 반복 하도록
                pmacone.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        pmacone.release();
                        pmacone.stop();
                    }
                });
            }
        });
        pmacpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pmacone.stop();
                vusikView.stopNotesFall();
            }
        });
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pmacone.stop();
                vusikView.stopNotesFall();
                Intent oneback = new Intent(OneActivity.this, MainActivity.class);
                OneActivity.this.startActivity(oneback);
            }
        });
    }
}
