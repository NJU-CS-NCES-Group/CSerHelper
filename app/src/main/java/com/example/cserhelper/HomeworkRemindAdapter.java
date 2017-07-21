package com.example.cserhelper;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;


/**
 * Created by lightning on 2017/7/20.
 */

public class HomeworkRemindAdapter extends BaseAdapter {
    private List<HomeworkRemindItem> list;
    LayoutInflater inflater;

    public HomeworkRemindAdapter(List<HomeworkRemindItem> list,Context context) {
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

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
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_homework_remind,null);
            viewHolder.tvCourseName=(TextView)convertView.findViewById(R.id.courseName);
            viewHolder.tvHomeworkName=(TextView)convertView.findViewById(R.id.homeworkName);
            viewHolder.tvRemindTime=(TextView)convertView.findViewById(R.id.remindTime);
            viewHolder.tvSubmitTime=(TextView)convertView.findViewById(R.id.submitTime);
            viewHolder.enable=(Switch)convertView.findViewById(R.id.enable);
        }
        else
            viewHolder=(ViewHolder) convertView.getTag();
        viewHolder.tvCourseName.setText(list.get(position).getCourseName());
        viewHolder.tvHomeworkName.setText(list.get(position).getHomeworkName());
        viewHolder.tvRemindTime.setText(list.get(position).getFormatRemindTime());
        viewHolder.tvRemindTime.setText(list.get(position).getFormatSubmitTime());
        viewHolder.enable.setChecked(list.get(position).isEnable());
        return convertView;
    }
    public static class ViewHolder{
        public TextView tvCourseName;
        public TextView tvHomeworkName;
        public TextView tvRemindTime;
        public TextView tvSubmitTime;
        public Switch enable;
    }
}
