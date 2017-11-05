package com.example.cserhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyCourseFragment extends Fragment implements View.OnClickListener{

    List<LinearLayout> linearLayouts;
    List<MyCourseItem> myCourseItems;
    List<List<CourseTextView>> textViewList;
    List<String> weeks;
    int thisWeek;
    private static final int titleHeight = 60;
    private static final int courseHeight = 100;

    public class SortComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            CourseTextView a = (CourseTextView) lhs;
            CourseTextView b = (CourseTextView) rhs;

            return (b.getStartTime() - a.getStartTime());
        }
    }

    private List<MyCourseItem> getMyCourseItems(){
        List<MyCourseItem> ret=new ArrayList<MyCourseItem>();
        List<OneCourseInformation> course11=new ArrayList<OneCourseInformation>();
        course11.add(new OneCourseInformation(0,"仙一202",1,3,4));
        MyCourseItem course1=new MyCourseItem("数据结构", 1, 18, course11, new ArrayList<String>(){{this.add("王芷芙");}});
        ret.add(course1);

        List<OneCourseInformation> course22=new ArrayList<OneCourseInformation>();
        course22.add(new OneCourseInformation(0,"仙一202",1,7,8));
        MyCourseItem course2=new MyCourseItem("数据结构", 1, 18, course22, new ArrayList<String>(){{this.add("王芷芙");}});
        ret.add(course2);

        List<OneCourseInformation> course33=new ArrayList<OneCourseInformation>();
        course33.add(new OneCourseInformation(1,"仙一202",2,1,2));
        MyCourseItem course3=new MyCourseItem("数据结构", 1, 18, course33, new ArrayList<String>(){{this.add("王芷芙");}});
        ret.add(course3);

        List<OneCourseInformation> course44=new ArrayList<OneCourseInformation>();
        course44.add(new OneCourseInformation(2,"仙一202",3,5,6));
        MyCourseItem course4=new MyCourseItem("数据结构", 1, 18, course44, new ArrayList<String>(){{this.add("王芷芙");}});
        ret.add(course4);
        return ret;
    }

    private void setCourseTable(int week) {
        textViewList=new ArrayList<List<CourseTextView>>();
        for (int i=0;i<7;i++)
            textViewList.add(new ArrayList<CourseTextView>());
        for(LinearLayout i:linearLayouts)
            if(i.getChildCount()>1){
                for (int j=1;j<i.getChildCount();j++){
                    i.removeViewAt(j);
                    j--;
                }
            }
        //把每门课分成单独的课加入列表
        for(MyCourseItem i : myCourseItems){
            if (week>=i.getWeekMin()&&week<=i.getWeekMax()) {
                for(OneCourseInformation j:i.getCourseInformations()){
                    if(j.getOddOrEven()==0||(j.getOddOrEven()==1 && week%2==1)||(j.getOddOrEven()==2 && week%2==0)) {
                        CourseTextView t = new CourseTextView(this.getActivity(),j.getStartTime());
                        t.setEndTime(j.getEndTime());
                        t.setText(i.getName()+"@"+j.getClassroom());
                        textViewList.get(j.getWeekly()-1).add(t);
                    }
                }
            }
        }
        //对每天的课程排序
        Comparator comp = new SortComparator();
        for(List<CourseTextView> i:textViewList)
            Collections.sort(i,comp);
        //设置margin
        for(List<CourseTextView> i:textViewList){
            for(int j=0;j<i.size();j++) {
                i.get(j).setHeight((i.get(j).getEndTime()-i.get(j).getStartTime()+1)*courseHeight);
                i.get(j).setTextSize(12);
                LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if(j==0)
                    params.setMargins(0,(i.get(j).getStartTime()-1)*courseHeight,0,0);
                else
                    params.setMargins(0,(i.get(j).getStartTime()-i.get(j-1).getEndTime()-1)*courseHeight,0,0);
                i.get(j).setLayoutParams(params);
            }
        }
        //加入界面
        for(int i=0;i<linearLayouts.size();i++)
            for (CourseTextView j:textViewList.get(i)){
                linearLayouts.get(i).addView(j);
            }
    }

    public MyCourseFragment() {
        // Required empty public constructor
    }

    public static MyCourseFragment newInstance(String param1) {
        MyCourseFragment fragment = new MyCourseFragment();
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
        View view= inflater.inflate(R.layout.fragment_my_course, container, false);
        Button button=(Button)view.findViewById(R.id.personalButton);
        button.setOnClickListener(this);
        linearLayouts=new ArrayList<LinearLayout>();
        linearLayouts.add((LinearLayout) view.findViewById(R.id.week1));
        linearLayouts.add((LinearLayout) view.findViewById(R.id.week2));
        linearLayouts.add((LinearLayout) view.findViewById(R.id.week3));
        linearLayouts.add((LinearLayout) view.findViewById(R.id.week4));
        linearLayouts.add((LinearLayout) view.findViewById(R.id.week5));
        linearLayouts.add((LinearLayout) view.findViewById(R.id.week6));
        linearLayouts.add((LinearLayout) view.findViewById(R.id.week7));
        Spinner chooseWeek=(Spinner) view.findViewById(R.id.chooseWeek);
        weeks = new ArrayList<String>();
        for(int i=1;i<26;i++)
            weeks.add(i+"");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,weeks);
        chooseWeek.setAdapter(adapter);
        chooseWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                thisWeek=pos+1;
                setCourseTable(thisWeek);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        myCourseItems=getMyCourseItems();
        //setCourseTable(thisWeek);
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
            case R.id.addCourse:
                break;
        }
    }
}
