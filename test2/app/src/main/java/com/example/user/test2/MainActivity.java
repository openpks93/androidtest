package com.example.user.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_onClick(View view){
        TextView text = (TextView) findViewById(R.id.textView);
        EditText edit = (EditText) findViewById(R.id.editText);
        Editable str1 = edit.getText();
        text.setText("안드로이드 앱 이름은 " + str1 + "이다");

    }
}
