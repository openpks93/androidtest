package com.example.user.start;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1= (EditText) findViewById(R.id.editText);
        edit2= (EditText) findViewById(R.id.editText2);
        result= (EditText) findViewById(R.id.Result);
    }

    public void add(View view){
        String s1 = edit1.getText().toString();
        String s2 = edit2.getText().toString();
        Integer n1 = Integer.parseInt(s1);
        Integer n2 = Integer.parseInt(s2);
        Integer sum = (n1 + n2);

        result.setText(""+ sum);
    }
    public void subtract(View view){

        String s1 = edit1.getText().toString();
        String s2 = edit2.getText().toString();
        Integer n1 = Integer.parseInt(s1);
        Integer n2 = Integer.parseInt(s2);
        Integer subtract = (n1 - n2);

        result.setText(""+ subtract);
    }

    public void multiply(View view){

        String s1 = edit1.getText().toString();
        String s2 = edit2.getText().toString();
        Integer n1 = Integer.parseInt(s1);
        Integer n2 = Integer.parseInt(s2);
        Integer multiply = (n1 * n2);

        result.setText(""+ multiply);
    }
    public void devide(View view){

        String s1 = edit1.getText().toString();
        String s2 = edit2.getText().toString();
        Integer n1 = Integer.parseInt(s1);
        Integer n2 = Integer.parseInt(s2);
        Integer devide = (n1 * n2);

        result.setText(""+ devide);
    }


}
