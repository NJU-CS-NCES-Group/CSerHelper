package com.example.cserhelper;



/**
 * Created by lightning on 2017/7/20.
 */

public class HomeworkRemindItem {
    private String CourseName;
    private String HomeworkName;
    private long RemindTime;
    private long SubmitTime;
    private boolean Enable;

    public HomeworkRemindItem(String courseName, String homeworkName, long remindTime, long submitTime, boolean enable) {
        CourseName = courseName;
        HomeworkName = homeworkName;
        RemindTime = remindTime;
        SubmitTime = submitTime;
        Enable = enable;
    }

    public String getCourseName() {
        return CourseName;
    }

    public String getHomeworkName() {
        return HomeworkName;
    }

    public long getRemindTime() {
        return RemindTime;
    }

    public long getSubmitTime() {
        return SubmitTime;
    }

    public boolean isEnable() {
        return Enable;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public void setHomeworkName(String homeworkName) {
        HomeworkName = homeworkName;
    }

    public void setRemindTime(long remindTime) {
        RemindTime = remindTime;
    }

    public void setSubmitTime(long submitTime) {
        SubmitTime = submitTime;
    }

    public void setEnable(boolean enable) {
        Enable = enable;
    }
}
