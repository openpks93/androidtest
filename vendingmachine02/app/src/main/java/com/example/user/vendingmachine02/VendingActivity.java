package com.example.user.vendingmachine02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VendingActivity extends AppCompatActivity {

    private Button candycount;
    private Button chokocount;
    private Button chokobarcount;
    private Button haribocount;
    private Button write;
    private  int candynum;
    private  int chokonum;
    private  int chokobarnum;
    private  int haribonum;
    private TextView candytext;
    private TextView chokotext;
    private TextView chokobartext;
    private TextView haribotext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vending);
        Button gohome = (Button)findViewById(R.id.button);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(VendingActivity.this, MainActivity.class);
                VendingActivity.this.startActivity(goHome);
            }
        });

        /////////////////////////////////candy click/////////////////////////////////////
        candynum = 10;
        ImageView cnady = (ImageView)findViewById(R.id.candy);
        cnady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                candycount = (Button)findViewById(R.id.candycountbutton);
                if (Integer.parseInt(candycount.getText().toString()) > 0)
                    candycount.setText(String.valueOf(--candynum));
            }
        });
        candytext = (TextView)findViewById(R.id.candytext);
        candytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                candycount = (Button)findViewById(R.id.candycountbutton);
                if (Integer.parseInt(candycount.getText().toString()) < 10)
                candycount.setText(String.valueOf(++candynum));
            }
        });

        /////////////////////////////////choko click/////////////////////////////////////
        ImageView choko = (ImageView)findViewById(R.id.choko);
        choko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chokocount = (Button)findViewById(R.id.chokocountbutton);
                if (Integer.parseInt(chokocount.getText().toString()) > 0)
                chokocount.setText(String.valueOf(--chokonum));
            }
        });
        chokotext = (TextView)findViewById(R.id.chokotext);
        chokotext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chokocount = (Button)findViewById(R.id.chokocountbutton);
                if (Integer.parseInt(chokocount.getText().toString()) < 10)
                chokocount.setText(String.valueOf(++chokonum));
            }
        });

        /////////////////////////////////chokobar click/////////////////////////////////////
        chokobarnum = 10;
        ImageView chokobar = (ImageView)findViewById(R.id.chokobar);
        chokobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chokobarcount = (Button)findViewById(R.id.chobarcountbutton);
                if (Integer.parseInt(chokobarcount.getText().toString()) > 0)
                chokobarcount.setText(String.valueOf(--chokobarnum));
            }
        });
        chokobartext = (TextView)findViewById(R.id.chokobartext);
        chokobartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chokobarcount = (Button)findViewById(R.id.chobarcountbutton);
                if (Integer.parseInt(chokobarcount.getText().toString()) < 10)
                chokobarcount.setText(String.valueOf(++chokobarnum));
            }
        });

        /////////////////////////////////haribo click/////////////////////////////////////
        haribonum = 10;
        ImageView haribo = (ImageView)findViewById(R.id.haribo);
        haribo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haribocount = (Button)findViewById(R.id.haribocountbutton);
                if (Integer.parseInt(haribocount.getText().toString()) > 0)
                haribocount.setText(String.valueOf(--haribonum));
            }
        });
        haribotext = (TextView)findViewById(R.id.haribotext);
        haribotext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haribocount = (Button)findViewById(R.id.haribocountbutton);
                if (Integer.parseInt(haribocount.getText().toString()) < 10)
                haribocount.setText(String.valueOf(++haribonum));
            }
        });
        write = (Button)findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent write = new Intent(VendingActivity.this, WriteActivity.class);
                VendingActivity.this.startActivity(write);
            }
        });
    }
}
