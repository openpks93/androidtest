package com.example.user.dbtest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ntsysMac01 on 2015-12-22.
 */

//BaseAdapter에 상속받기 때문에 extends 해줌
public class itemListAdapter extends BaseAdapter{

    private Context mContext;

    //어댑터를 사용할것이기 때문에 리스트를 생성해줌
    private List<Item> mItems = new ArrayList<Item>();

    itemListAdapter(Context context){
        mContext = context;
    }

    void additem(Item it){
        mItems.add(it);
    }

    private void setListItems(List<Item> list){
        mItems = list;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    //저번 예제에서 살펴봤던거와 동일하게 작업해주면 됨
    //퍼포먼스역할과 View 보여주는 역할을 해주는 기능
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView itemView;
        if(convertView == null) {
            itemView = new ItemView(mContext, mItems.get(position));
        } else {
            itemView = (ItemView) convertView;
            itemView.setText(0, mItems.get(position).borditemdate(0));
            itemView.setText(1, mItems.get(position).borditemdate(1));
            itemView.setText(2, mItems.get(position).borditemdate(2));
            itemView.setText(3, mItems.get(position).borditemdate(3));
        }

        return itemView;
    }
}
