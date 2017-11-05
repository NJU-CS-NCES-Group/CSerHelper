package com.example.cserhelper;

import android.content.Context;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.widget.TextView;

/**
 * Created by wujiawei on 2017/11/5.
 */

public class CourseTextView extends TextView {

    int startTime;
    int endTime;

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public CourseTextView(Context context, int startTime) {

        super(context);
        this.startTime = startTime;
    }

    public CourseTextView(Context context) {
        super(context);
    }
}
