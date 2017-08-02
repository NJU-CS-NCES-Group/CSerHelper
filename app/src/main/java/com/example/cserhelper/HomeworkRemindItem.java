package com.example.cserhelper;


import java.text.DateFormat;
import java.util.Date;

/**
 * Created by lightning on 2017/7/20.
 */

public class HomeworkRemindItem {
    private long ID;
    private String CourseName;
    private String HomeworkName;
    private Date RemindTime;
    private Date SubmitTime;
    private boolean Enable;

    public HomeworkRemindItem(String courseName, String homeworkName, Date remindTime, Date submitTime, boolean enable) {
        CourseName = courseName;
        HomeworkName = homeworkName;
        RemindTime = remindTime;
        SubmitTime = submitTime;
        Enable = enable;
    }

    public String getFormatRemindTime()
    {
        return (String) android.text.format.DateFormat.format("MM-dd HH:mm",getRemindTime());
    }

    public String getFormatSubmitTime()
    {
        return (String) android.text.format.DateFormat.format("MM-dd HH:mm",getRemindTime());
    }

    public String getCourseName() {
        return CourseName;
    }

    public String getHomeworkName() {
        return HomeworkName;
    }

    public Date getRemindTime() {
        return RemindTime;
    }

    public Date getSubmitTime() {
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

    public void setRemindTime(Date remindTime) {
        RemindTime = remindTime;
    }

    public void setSubmitTime(Date submitTime) {
        SubmitTime = submitTime;
    }

    public void setEnable(boolean enable) {
        Enable = enable;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
