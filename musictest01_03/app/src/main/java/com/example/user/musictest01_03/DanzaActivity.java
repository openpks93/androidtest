package com.example.user.musictest01_03;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class DanzaActivity extends AppCompatActivity {

    int pos;
    SeekBar sb;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danza);


        final ImageButton danzaplay = (ImageButton) findViewById(R.id.danzaplay);
        final ImageButton danzapause = (ImageButton) findViewById(R.id.danzapause);
        final ImageButton gohome = (ImageButton) findViewById(R.id.gohome);
        final MediaPlayer danza = MediaPlayer.create(this, R.raw.danzakuduro);
        final VusicView vusikView = (VusicView) findViewById(R.id.vusik);

/////////////////////////////////////////////////////////////////////////////////////////////////////////
        class MyThread extends Thread {
            @Override
            public void run() { // 쓰레드가 시작되면 콜백되는 메서드
                // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
                while(isPlaying) {
                    sb.setProgress(danza.getCurrentPosition());
                }
            }
        }
        sb = (SeekBar)findViewById(R.id.seekbar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                danza.seekTo(ttt);
                danza.start();
                new MyThread().start();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                danza.pause();
            }
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    danzaplay.setVisibility(View.VISIBLE);
                    danzapause.setVisibility(View.INVISIBLE);
                    isPlaying = false;
                    danza.stop();
                }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        danzaplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Context bc =  v.getContext();
                //final MediaPlayer beliver = MediaPlayer.create(bc, R.raw.believer);
                danza.start();
                vusikView.start();
                int a = danza.getDuration(); // 노래의 재생시간(miliSecond)
                sb.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
                new MyThread().start(); // 씨크바 그려줄 쓰레드 시작
                isPlaying = true; // 씨크바 쓰레드 반복 하도록
                danza.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        danza.release();
                        danza.stop();
                        vusikView.stopNotesFall();
                    }
                });
            }
        });
        //pause 버튼
        danzapause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                danza.stop();
                vusikView.stopNotesFall();

            }
        });
        //gohome버튼
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent danzaback = new Intent(DanzaActivity.this, MainActivity.class);
                DanzaActivity.this.startActivity(danzaback);
                danza.stop();
                vusikView.stopNotesFall();
            }
        });
    }
}
