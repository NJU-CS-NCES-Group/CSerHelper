package com.example.cserhelper;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;

import java.util.List;


/**
 * Created by lightning on 2017/7/20.
 */

public class HomeworkRemindAdapter extends BaseSwipListAdapter {
    private List<HomeworkRemindItem> list;
    private HomeworkRemindDBManager dbManeger;
    LayoutInflater inflater;
    Context context;

    public HomeworkRemindAdapter(List<HomeworkRemindItem> list,HomeworkRemindDBManager db,Context context) {
        this.list = list;
        inflater=LayoutInflater.from(context);
        this.context=context;
        dbManeger=db;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        viewHolder.tvSubmitTime.setText(list.get(position).getFormatSubmitTime());
        viewHolder.enable.setChecked(list.get(position).isEnable());
        //viewHolder.enable.setTag(position);
        viewHolder.enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeworkRemindItem temp=list.get(position);
                Toast.makeText(context,"clicked"+position+",it is "+temp.isEnable(), Toast.LENGTH_SHORT).show();
                dbManeger.changeItem(temp.getID(),!temp.isEnable());
                temp.setEnable(!temp.isEnable());
                list.set(position,temp);
            }
        });
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
