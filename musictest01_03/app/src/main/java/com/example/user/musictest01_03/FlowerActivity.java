package com.example.user.musictest01_03;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class FlowerActivity extends AppCompatActivity {

    int pos;
    SeekBar sb;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);

        final ImageButton flowerplay = (ImageButton) findViewById(R.id.flowerplay);
        final ImageButton flowerpause = (ImageButton) findViewById(R.id.flowerpause);
        final ImageButton gohome = (ImageButton) findViewById(R.id.backbutton);
        final MediaPlayer flower = MediaPlayer.create(this, R.raw.flower);
        final VusicView vusikView = (VusicView) findViewById(R.id.vusik);

/////////////////////////////////////////////////////////////////////////////////////////////////////////
        class MyThread extends Thread {
            @Override
            public void run() { // 쓰레드가 시작되면 콜백되는 메서드
                // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
                while(isPlaying) {
                    sb.setProgress(flower.getCurrentPosition());
                }
            }
        }
        sb = (SeekBar)findViewById(R.id.seekbar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                flower.seekTo(ttt);
                flower.start();
                new MyThread().start();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                flower.pause();
            }
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    flowerplay.setVisibility(View.VISIBLE);
                    flowerpause.setVisibility(View.INVISIBLE);
                    isPlaying = false;
                    flower.stop();
                }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////버튼 동작
        flowerplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flower.start();
                vusikView.start();
                int a = flower.getDuration(); // 노래의 재생시간(miliSecond)
                sb.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
                new MyThread().start(); // 씨크바 그려줄 쓰레드 시작
                isPlaying = true; // 씨크바 쓰레드 반복 하도록
                flower.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        flower.release();
                        flower.stop();
                    }
                });
            }
        });

        flowerpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flower.stop();
                vusikView.stopNotesFall();
            }
        });

        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flower.stop();
                vusikView.stopNotesFall();
                Intent flowerback = new Intent(FlowerActivity.this, MainActivity.class);
                FlowerActivity.this.startActivity(flowerback);
            }
        });

    }
}
