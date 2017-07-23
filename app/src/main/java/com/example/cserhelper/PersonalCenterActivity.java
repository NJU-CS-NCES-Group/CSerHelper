package com.example.cserhelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

public class PersonalCenterActivity extends AppCompatActivity implements
        BottomNavigationBar.OnTabSelectedListener
{
    private ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        /*TODO: 这里是底部导航栏的背景，需要在res/color.xml对每个颜色的ID以及图片进行设置*/
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_table, "我的课程").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_talk, "我的作业").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_clock, "作业提醒").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_person, "联系我们").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        /*这里是底部导航栏的默认Fragment*/
        transaction.replace(R.id.layFrame, MyCourseFragment.newInstance("我的课程"));
        transaction.commit();
    }
    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        //TODO：这里是底部导航栏的Fragment，需要分别去完成对应功能
        fragments.add(MyCourseFragment.newInstance("我的课程"));
        fragments.add(MyHomeworkFragment.newInstance("我的作业"));
        fragments.add(HomeworkRemindFragment.newInstance("作业提醒"));
        fragments.add(ContactUsFragment.newInstance("联系我们"));
        fragments.add(PersonalInformationFragment.newInstance("个人信息"));
        return fragments;
    }
    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commit();
            }
        }
    }
    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

}
