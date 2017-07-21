package com.example.cserhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class HomeworkRemindFragment extends Fragment implements View.OnClickListener
{
    private SwipeMenuListView listView;
    private HomeworkRemindAdapter adapter;
    private List<HomeworkRemindItem> list;

    public HomeworkRemindFragment() {
    }
    public static HomeworkRemindFragment newInstance(String param1) {
        HomeworkRemindFragment fragment = new HomeworkRemindFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_homework_remind, container, false);
        Button button=(Button)view.findViewById(R.id.personalButton);
        button.setOnClickListener(this);
        Button plusButton=(Button)view.findViewById(R.id.addItem);
        plusButton.setOnClickListener(this);
        listView=(SwipeMenuListView) view.findViewById(R.id.listView);
        listView.addHeaderView(new ViewStub(this.getActivity()));
        list=getList();
        adapter=new HomeworkRemindAdapter(list,this.getActivity());
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(200);
                // set a icon
                //deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index)
                {
                    case 0:
                        //删除该item
                        list.remove(position);
                        int t=listView.getFirstVisiblePosition();
                        if(t!=list.size()-1) t++;
                        listView.setAdapter(adapter);
                        listView.setSelection(t);
                        break;
                }
                return false;
            }
        });
        return view;
    }

    //TODO:完成从本地读取数据
    public List<HomeworkRemindItem> getList(){
        ArrayList<HomeworkRemindItem> list=new ArrayList<HomeworkRemindItem>();
        list.add(new HomeworkRemindItem("数理逻辑","作业一",1000,60,true));
        list.add(new HomeworkRemindItem("数理逻辑","作业一",60,60,true));
        list.add(new HomeworkRemindItem("数理逻辑","作业一",60,60,true));
        return list;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.personalButton:
            {
                PersonalCenterActivity pca=(PersonalCenterActivity)getActivity();
                FragmentManager fm=pca.getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Fragment fragment=pca.getFragments().get(4);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commit();
                break;
            }
            case R.id.addItem:
                LayoutInflater addItemDialog;
                addItemDialog=LayoutInflater.from(this.getActivity());
                View view=addItemDialog.inflate(R.layout.dialog_add_remind_item,null);
                Spinner spCourseName=(Spinner) view.findViewById(R.id.courseName);
                Spinner spHomeworkName=(Spinner) view.findViewById(R.id.homeworkName);
                TimePicker tp=(TimePicker) view.findViewById(R.id.timePick);
                DatePicker dp=(DatePicker) view.findViewById(R.id.datePick);
                tp.setIs24HourView(true);
                new AlertDialog.Builder(this.getActivity())
                        .setTitle("添加提醒")
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("取消",null)
                        .create()
                        .show();
                ((ViewGroup) (((ViewGroup) dp.getChildAt(0)).getChildAt(0)))
                        .getChildAt(2).setVisibility(View.GONE);
        }
    }
}
