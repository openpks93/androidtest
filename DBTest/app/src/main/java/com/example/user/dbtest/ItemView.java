package com.example.user.dbtest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ntsysMac01 on 2015-12-22.
 */
public class ItemView extends LinearLayout {//ItemView는 보여지는 역할을 해주는 클래스

    //값을 보여줘야 하기 때문에 보여줄때 필요한 타입을 변수로 선언
    private TextView numtext;//번호
    private TextView subtext;//제목
    private TextView nametext;//이름
    private TextView daytext;

    public ItemView(Context context, Item date) {
        super(context);

        LayoutInflater inflater = (LayoutInflater)//컨텐츠가 보여질 xml을 내가만든 xml로 설정하기 위해 inflater을 설정
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bordlist_content, this, true);

        //어느 변수에 무엇이 어떤값이 들어가야 되는지 각각 설정
        numtext = (TextView)findViewById(R.id.textView6);
        numtext.setText(date.borditemdate(0));

        subtext = (TextView)findViewById(R.id.textView7);
        subtext.setText(date.borditemdate(1));

        nametext = (TextView)findViewById(R.id.textView8);
        nametext.setText(date.borditemdate(2));

        daytext = (TextView)findViewById(R.id.textView9);
        daytext.setText(date.borditemdate(3));


    }

    //배열로 처리된 값들을 setText로 쪼개줌
    //위에서 쓰게되는것을 보면 이해가 쉬움
    public void setText(int index, String data){

        if (index == 0 ){

            numtext.setText(data);

        } else if (index == 1 ){

            subtext.setText(data);

        } else if (index == 2){

            nametext.setText(data);

        } else if (index == 3){

            daytext.setText(data);

        } else {

            throw new IllegalArgumentException();

        }

    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

