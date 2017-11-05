package com.example.cserhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;

import java.util.List;

/**
 * Created by wujiawei on 2017/11/5.
 */

public class MessageAdapter extends BaseSwipListAdapter {
    List<MessageItem> list;
    LayoutInflater inflater;
    Context context;

    public MessageAdapter(List<MessageItem> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

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
            convertView=inflater.inflate(R.layout.item_message,null);
            viewHolder.tvMessageSources=(TextView)convertView.findViewById(R.id.messageSource);
            viewHolder.tvMessageContent=(TextView)convertView.findViewById(R.id.messageContent);
        }
        else
            viewHolder=(ViewHolder) convertView.getTag();
        switch (list.get(position).getIdentity()){
            case 0:
                viewHolder.tvMessageSources.setText("系统提示");
                break;
            case 1:
                viewHolder.tvMessageSources.setText("教师"+"-"+list.get(position).getName());
                break;
            case 2:
                viewHolder.tvMessageSources.setText("助教"+"-"+list.get(position).getName());
                break;
            default:
                break;
        }
        viewHolder.tvMessageContent.setText(list.get(position).getMessage());
        return convertView;
    }
    public static class ViewHolder{
        public TextView tvMessageSources;
        public TextView tvMessageContent;

    }
}
