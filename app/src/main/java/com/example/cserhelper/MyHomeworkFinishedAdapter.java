package com.example.cserhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wujiawei on 2017/11/5.
 */

public class MyHomeworkFinishedAdapter extends BaseExpandableListAdapter {
    List<MyHomeworkItem> myHomeworkItems;
    private Context mContext;

    public MyHomeworkFinishedAdapter(List<MyHomeworkItem> myHomeworkItems, Context mContext) {
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

        MyHomeworkFinishedAdapter.ViewHolderGroup groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.group_my_homework_finished, parent, false);
            groupHolder = new MyHomeworkFinishedAdapter.ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.homeworkName);
            groupHolder.tc_group_isgraded = (TextView) convertView.findViewById(R.id.isgraded);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (MyHomeworkFinishedAdapter.ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(myHomeworkItems.get(groupPosition).getName());
        if (myHomeworkItems.get(groupPosition).getGraded())
            groupHolder.tc_group_isgraded.setText("已评分");
        else
            groupHolder.tc_group_isgraded.setText("未评分");
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MyHomeworkFinishedAdapter.ViewHolderItem itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_my_homework, parent, false);
            itemHolder = new MyHomeworkFinishedAdapter.ViewHolderItem();
            itemHolder.tv_information = (TextView) convertView.findViewById(R.id.myHomeworkInformation);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (MyHomeworkFinishedAdapter.ViewHolderItem) convertView.getTag();
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
        private TextView tc_group_isgraded;
    }

    private static class ViewHolderItem {
        private TextView tv_information;
    }
}
