package com.example.cserhelper;

import java.util.Date;
import java.util.List;

/**
 * Created by lightning on 2017/7/22.
 */

public class MyHomeworkItem {
    String name;
    String information;
    Date submitTime;
    Boolean isGraded = false;

    public Boolean getGraded() {
        return isGraded;
    }

    public String getFormatSubmitTime()
    {
        return (String) android.text.format.DateFormat.format("MM-dd HH:mm",submitTime);
    }

    public void setGraded(Boolean graded) {
        isGraded = graded;
    }

    public MyHomeworkItem(String name, String information, Date submitTime, Boolean isGraded) {

        this.name = name;
        this.information = information;
        this.submitTime = submitTime;
        this.isGraded = isGraded;
    }

    public MyHomeworkItem(String name, Date submitTime, String information) {
        this.name = name;
        this.information = information;
        this.submitTime = submitTime;
    }

    public MyHomeworkItem(String name, Date submitTime) {
        this.name = name;
        this.submitTime = submitTime;
    }

    public String getName() {
        return name;
    }

    public MyHomeworkItem(String name,Boolean isGraded, String information) {
        this.name = name;
        this.information = information;
        this.isGraded = isGraded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}
