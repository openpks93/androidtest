package com.example.user.test0409_4;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        final Activity activity = getActivity();
        final CheckBox check = (CheckBox) activity.findViewById(R.id.checkBox);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "";
                if(check.isChecked()){
                    str = "체크박스가 선택되었습니다.";
                }
                else
                    str = "체크박스가 해제되었습니다.";
                Toast toast = Toast.makeText(activity,str,Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
