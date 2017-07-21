package com.example.cserhelper;



/**
 * Created by lightning on 2017/7/20.
 */

public class HomeworkRemindItem {
    private String CourseName;
    private String HomeworkName;
    private long RemindTime;   //以分钟数记录，精确到分钟
    private long SubmitTime;   //以秒数记录，精确到秒
    private boolean Enable;

    public HomeworkRemindItem(String courseName, String homeworkName, long remindTime, long submitTime, boolean enable) {
        CourseName = courseName;
        HomeworkName = homeworkName;
        RemindTime = remindTime;
        SubmitTime = submitTime;
        Enable = enable;
    }

    public String getFormatRemindTime()
    {
        String d,h,m;
        m = (RemindTime % 60)+"";
        h = ((RemindTime / 60) % 24) + "";
        d = (RemindTime / 60 / 24) + "";
        return d+"d"+h+"h"+m+"m";
    }

    public String getFormatSubmitTime()
    {
        String d,h,m,s;
        s = (RemindTime % 60) + "";
        m = (RemindTime / 60 % 60) + "";
        h = ((RemindTime / 60 / 60) % 24) + "";
        d = (RemindTime / 60 / 60 / 24) + "";
        return d+"d"+h+"h"+m+"m"+s+"s";
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
