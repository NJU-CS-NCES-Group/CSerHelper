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
