package com.example.user.musictest01_03;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView beliverImage = (ImageView) findViewById(R.id.beliverView);
        ImageView danzauImage = (ImageView) findViewById(R.id.dazuView);
        ImageView letitImage = (ImageView) findViewById(R.id.letitgoView);
        ImageView roundImage = (ImageView) findViewById(R.id.roundView);
        ImageView flowerimage = (ImageView) findViewById(R.id.flowerView);
        ImageView sharkimage = (ImageView) findViewById(R.id.sharkView);
        ImageView yearsimage = (ImageView) findViewById(R.id.yearsView);
        ImageView oneimage = (ImageView) findViewById(R.id.oneView);
        ImageView handsimage = (ImageView) findViewById(R.id.handsView);

        final MediaPlayer beliver = MediaPlayer.create(this, R.raw.believer);
        final MediaPlayer danzakuduro = MediaPlayer.create(this, R.raw.danzakuduro);
        final MediaPlayer roundandround = MediaPlayer.create(this, R.raw.roundandround);
        final MediaPlayer letitgo = MediaPlayer.create(this, R.raw.letitgo);


        ////////////////////////////beliver이미지 클릭 화면 전환
        beliverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent beliver = new Intent(MainActivity.this, BeliverActivity.class);
                MainActivity.this.startActivity(beliver);
            }
        });
        ////////////////////////////도깨비이미지 클릭 화면 전환
        roundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent round = new Intent(MainActivity.this, RoundActivity.class);
                MainActivity.this.startActivity(round);
            }
        });
        ////////////////////////////겨울왕국 이미지 클릭 화면 전환
        letitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent letitgo = new Intent(MainActivity.this, LetitgoActivity.class);
                MainActivity.this.startActivity(letitgo);
            }
        });
        ////////////////////////////분노의질주 클릭 화면 전환
        danzauImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent letitgo = new Intent(MainActivity.this, DanzaActivity.class);
                MainActivity.this.startActivity(letitgo);
            }
        });
        ////////////////////////////백만송이 이미지 클릭 화면 전환
        flowerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent flower = new Intent(MainActivity.this, FlowerActivity.class);
                MainActivity.this.startActivity(flower);
            }
        });
        sharkimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shark = new Intent(MainActivity.this, SharkActivity.class);
                MainActivity.this.startActivity(shark);
            }
        });
        yearsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent years = new Intent(MainActivity.this, YearsActivity.class);
                MainActivity.this.startActivity(years);
            }
        });
        oneimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent years = new Intent(MainActivity.this, OneActivity.class);
                MainActivity.this.startActivity(years);

            }
        });
        handsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent years = new Intent(MainActivity.this, HandsActivity.class);
                MainActivity.this.startActivity(years);

            }
        });
    }

}
