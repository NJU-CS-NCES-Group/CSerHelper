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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.AEADBadTagException;
import javax.sql.StatementEvent;

public class HomeworkRemindFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener
{
    private SwipeMenuListView listView;
    private HomeworkRemindAdapter adapter;
    private List<HomeworkRemindItem> list;
    private ArrayList<String> homeworkList;
    private ArrayList<String> courseList;
    private MyHomeworkItem myHomeworkItem;
    private List<Date> homeworkTimeList;
    private DatePicker dp;
    private TimePicker tp;
    private HomeworkRemindDBManager dbManager;

    public HomeworkRemindFragment() {
    }
    public static HomeworkRemindFragment newInstance(String param1) {
        HomeworkRemindFragment fragment = new HomeworkRemindFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //格式为"yyyy-MM-dd HH:mm"
    public Date String2Date(String s){
        Date date;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = simpleDateFormat.parse(s);
        }
        catch (Exception e)
        {
            date=null;
        }
        return date;
    }

    public void saveLocalList(){
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
        dbManager=new HomeworkRemindDBManager(this.getContext());
        plusButton.setOnClickListener(this);
        listView=(SwipeMenuListView) view.findViewById(R.id.listView);
        listView.addHeaderView(new ViewStub(this.getActivity()));
        list=getList();
        adapter=new HomeworkRemindAdapter(list,dbManager,this.getActivity());
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
                        dbManager.deleteItem(list.get(position).getID());
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
        listView.setOnItemClickListener(this);
        return view;
    }

    //TODO:完成从本地读取数据
    public List<HomeworkRemindItem> getList(){
        return dbManager.getALL();
    }

    //TODO:获取登录账号的全部课程和作业信息，此处可不获取作业具体描述，只要名称,具体数据结构参看 @MyHomeworkItem
    public MyHomeworkItem getHomeworkList() {
        List<String> courses=new ArrayList<String>();
        List<List<String>> homework=new ArrayList<List<String>>();
        List<List<Date>> submitTime=new ArrayList<List<Date>>();
        courses.add("操作系统");
        courses.add("数理逻辑");
        courses.add("算法");
        List<String> homeworkItem1=new ArrayList<String>();
        homeworkItem1.add("书面作业");
        homeworkItem1.add("oslab");
        List<String> homeworkItem2=new ArrayList<String>();
        homeworkItem2.add("课后作业");
        homeworkItem2.add("课堂作业");
        List<String> homeworkItem3=new ArrayList<String>();
        homeworkItem3.add("problemset_1");
        homeworkItem3.add("problemset_2");
        homework.add(homeworkItem1);
        homework.add(homeworkItem2);
        homework.add(homeworkItem3);

        List<Date> submitTime1=new ArrayList<Date>();
        submitTime1.add(String2Date("2017-08-01 21:00"));
        submitTime1.add(String2Date("2017-08-01 21:00"));
        List<Date> submitTime2=new ArrayList<Date>();
        submitTime2.add(String2Date("2017-08-01 21:00"));
        submitTime2.add(String2Date("2017-08-01 21:00"));
        List<Date> submitTime3=new ArrayList<Date>();
        submitTime3.add(String2Date("2017-08-01 21:00"));
        submitTime3.add(String2Date("2017-08-01 21:00"));
        submitTime.add(submitTime1);
        submitTime.add(submitTime2);
        submitTime.add(submitTime3);
        MyHomeworkItem myHomeworkItem=new MyHomeworkItem(courses,homework,submitTime);
        return myHomeworkItem;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.personalButton:
            {
                PersonalCenterActivity pca=(PersonalCenterActivity)getActivity();
                FragmentManager fm=pca.getSupportFragmentManager();
                PersonalInformationFragment personalInformationFragment=new PersonalInformationFragment();
                personalInformationFragment.show(fm,"dialog");

                //personalInformationFragment.dismiss();
                break;
            }
            case R.id.addItem:
                LayoutInflater addItemDialog;
                addItemDialog=LayoutInflater.from(this.getActivity());
                View view=addItemDialog.inflate(R.layout.dialog_add_remind_item,null);
                final Spinner spCourseName=(Spinner) view.findViewById(R.id.courseName);
                final Spinner spHomeworkName=(Spinner) view.findViewById(R.id.homeworkName);
                tp=(TimePicker) view.findViewById(R.id.timePick);
                dp=(DatePicker) view.findViewById(R.id.datePick);
                myHomeworkItem=getHomeworkList();
                courseList=(ArrayList<String>) myHomeworkItem.getCourses();
                homeworkList=new ArrayList<String>();
                homeworkList.addAll((ArrayList<String>) myHomeworkItem.getHomework().get(0));
                homeworkTimeList=myHomeworkItem.getHomeworkSubmitTime().get(0);
                ArrayAdapter<String> courseAdapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, courseList);
                final ArrayAdapter<String> homeworkAdapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_selectable_list_item,homeworkList);
                spCourseName.setAdapter(courseAdapter);
                spHomeworkName.setAdapter(homeworkAdapter);
                //禁止弹出键盘
                dp.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
                tp.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
                //根据课程名切换作业目录
                spCourseName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        homeworkList.clear();
                        homeworkList.addAll((ArrayList<String>) myHomeworkItem.getHomework().get(position));
                        homeworkTimeList= myHomeworkItem.getHomeworkSubmitTime().get(position);
                        homeworkAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //根据作业名切换作业截止时间
                spHomeworkName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Calendar c=Calendar.getInstance();
                        dp.setMinDate(c.getTimeInMillis());
                        dp.setMaxDate(homeworkTimeList.get(position).getTime());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                tp.setIs24HourView(true);
                new AlertDialog.Builder(this.getActivity())
                        .setTitle("添加提醒")
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String s;
                                s=dp.getYear()+"-"+dp.getMonth()+"-"+dp.getDayOfMonth()+" "+tp.getCurrentHour()+":"+tp.getCurrentMinute();
                                HomeworkRemindItem temp=new HomeworkRemindItem(
                                        (String)spCourseName.getSelectedItem(),
                                        (String) spHomeworkName.getSelectedItem(),
                                        String2Date(s),
                                        homeworkTimeList.get(spHomeworkName.getSelectedItemPosition()),
                                        true
                                );
                                long tempId = dbManager.addItem(temp);
                                temp.setID(tempId);
                                list.add(temp);
                                //Toast.makeText(getContext(),"ID is "+a, Toast.LENGTH_SHORT).show();
                                listView.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create()
                        .show();
                ((ViewGroup) (((ViewGroup) dp.getChildAt(0)).getChildAt(0)))
                        .getChildAt(2).setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
