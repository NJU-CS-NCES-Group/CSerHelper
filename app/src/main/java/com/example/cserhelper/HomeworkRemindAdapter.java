package com.example.cserhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by lightning on 2017/7/20.
 */

public class HomeworkRemindAdapter extends BaseAdapter {
    private List<HomeworkRemindItem> list;
    LayoutInflater inflater;
    @Override
    public int getCount() {
        if(list==null)
            return 0;
        else
            return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_homework_remind,parent,false);

        return null;
    }
}
