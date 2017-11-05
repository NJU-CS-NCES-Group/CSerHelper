package com.example.cserhelper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MyHomeworkFragment extends Fragment implements View.OnClickListener{
    List<MyHomeworkItem> comingHomework;
    List<MyHomeworkItem> finishedHomework;
    MyHomeworkComingAdapter comingAdapter;
    MyHomeworkFinishedAdapter finishedAdapter;
    ExpandableListView listView;

    //格式为"MM-dd HH:mm"
    public Date String2Date(String s){
        Date date;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            date = simpleDateFormat.parse(s);
        }
        catch (Exception e)
        {
            date=null;
        }
        return date;
    }
    //TODO:完成数据传输
    private List<MyHomeworkItem> getComingHomeworkList(){
        List<MyHomeworkItem> ret = new ArrayList<MyHomeworkItem>();
        ret.add(new MyHomeworkItem("数据结构-作业一",String2Date("11-06 23:59"),"一堆解释解释解释解释解释解释解释解释解释解释解释解释解释"));
        ret.add(new MyHomeworkItem("数据结构-作业一",String2Date("11-06 23:59"),"一堆解释解释解释解释解释解释解释解释解释解释解释解释解释"));
        return ret;
    }

    private List<MyHomeworkItem> getFinishedHomeworkList(){
        List<MyHomeworkItem> ret = new ArrayList<MyHomeworkItem>();
        ret.add(new MyHomeworkItem("数据结构-作业一",true,"一堆解释解释解释解释解释解释解释解释解释解释解释解释解释"));
        ret.add(new MyHomeworkItem("数据结构-作业一",false,"一堆解释解释解释解释解释解释解释解释解释解释解释解释解释"));
        return ret;
    }

    public MyHomeworkFragment() {
        // Required empty public constructor
    }

    public static MyHomeworkFragment newInstance(String param1) {
        MyHomeworkFragment fragment = new MyHomeworkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //TODO：完成判断是否超时

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_homework, container, false);
        Button button=(Button)view.findViewById(R.id.personalButton);
        button.setOnClickListener(this);
        Button comingButton=(Button)view.findViewById(R.id.comingButton);
        Button finishedButton = (Button)view.findViewById(R.id.deadButton);
        comingButton.setOnClickListener(this);
        finishedButton.setOnClickListener(this);
        listView= (ExpandableListView)view.findViewById(R.id.homeworklistView);
        comingHomework=getComingHomeworkList();
        comingAdapter = new MyHomeworkComingAdapter(comingHomework,this.getActivity());
        finishedHomework=getFinishedHomeworkList();
        finishedAdapter= new MyHomeworkFinishedAdapter(finishedHomework,this.getActivity());
        //listView.setAdapter(comingAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.personalButton:
                PersonalCenterActivity pca=(PersonalCenterActivity)getActivity();
                FragmentManager fm=pca.getSupportFragmentManager();
                PersonalInformationFragment personalInformationFragment=new PersonalInformationFragment();
                personalInformationFragment.show(fm,"dialog");
                break;
            case R.id.comingButton:
                listView.setAdapter(comingAdapter);
                break;
            case R.id.deadButton:
                listView.setAdapter(finishedAdapter);
                break;
        }

    }
}
