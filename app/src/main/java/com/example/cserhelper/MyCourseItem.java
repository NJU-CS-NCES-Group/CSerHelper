package com.example.cserhelper;

import java.sql.SQLRecoverableException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lightning on 2017/7/27.
 */

public class MyCourseItem {
    public MyCourseItem(String name, List<MyHomeworkItem> homeworkList) {
        this.name = name;
        this.homeworkList = homeworkList;
    }

    String name;
    int weekMin;
    int weekMax;
    List<OneCourseInformation> courseInformations;
    List<String> teacherName;
    List<MyHomeworkItem> homeworkList;



    public List<String> getHomeworkNameList() {
        List<String> ret = new ArrayList<String>();
        for (int i = 0; i < homeworkList.size(); i++) {
            ret.add(homeworkList.get(i).getName());
        }
        return ret;
    }

    public String getName() {
        return name;
    }

    public List<OneCourseInformation> getCourseInformations() {
        return courseInformations;
    }

    public void setCourseInformations(List<OneCourseInformation> courseInformations) {
        this.courseInformations = courseInformations;
    }

    public MyCourseItem(String name, int weekMin, int weekMax, List<OneCourseInformation> courseInformations, List<String> teacherName, List<MyHomeworkItem> homeworkList) {

        this.name = name;
        this.weekMin = weekMin;
        this.weekMax = weekMax;
        this.courseInformations = courseInformations;
        this.teacherName = teacherName;
        this.homeworkList = homeworkList;
    }

    public MyCourseItem(String name, int weekMin, int weekMax, List<OneCourseInformation> courseInformations, List<String> teacherName) {

        this.name = name;
        this.weekMin = weekMin;
        this.weekMax = weekMax;
        this.courseInformations = courseInformations;
        this.teacherName = teacherName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeekMin() {
        return weekMin;
    }

    public void setWeekMin(int weekMin) {
        this.weekMin = weekMin;
    }

    public int getWeekMax() {
        return weekMax;
    }

    public void setWeekMax(int weekMax) {
        this.weekMax = weekMax;
    }

    public List<String> getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(List<String> teacherName) {
        this.teacherName = teacherName;
    }

    public List<MyHomeworkItem> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<MyHomeworkItem> homeworkList) {
        this.homeworkList = homeworkList;
    }
}
