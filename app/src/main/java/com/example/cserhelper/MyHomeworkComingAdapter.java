package com.example.cserhelper;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.List;

/**
 * Created by wujiawei on 2017/11/5.
 */

public class MyHomeworkComingAdapter extends BaseExpandableListAdapter {
    List<MyHomeworkItem> myHomeworkItems;
    private Context mContext;

    public MyHomeworkComingAdapter(List<MyHomeworkItem> myHomeworkItems, Context mContext) {
        this.myHomeworkItems = myHomeworkItems;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return myHomeworkItems.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public MyHomeworkItem getGroup(int i) {
        return myHomeworkItems.get(i);
    }

    @Override
    public MyHomeworkItem getChild(int i, int i1) {
        return myHomeworkItems.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.group_my_homework_coming, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.homeworkName);
            groupHolder.tc_group_ddl = (TextView) convertView.findViewById(R.id.ddl);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(myHomeworkItems.get(groupPosition).getName());
        groupHolder.tc_group_ddl.setText(myHomeworkItems.get(groupPosition).getFormatSubmitTime());
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_my_homework, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.tv_information = (TextView) convertView.findViewById(R.id.myHomeworkInformation);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.tv_information.setText(myHomeworkItems.get(groupPosition).getInformation());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    private static class ViewHolderGroup {
        private TextView tv_group_name;
        private TextView tc_group_ddl;
    }

    private static class ViewHolderItem {
        private TextView tv_information;
    }
}