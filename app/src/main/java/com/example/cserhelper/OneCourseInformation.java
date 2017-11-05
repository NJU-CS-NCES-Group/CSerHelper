package com.example.cserhelper;

/**
 * Created by wujiawei on 2017/11/5.
 */

public class OneCourseInformation{
    int oddOrEven; //0表示不限制，1表示单周，2表示双周
    String classroom;

    public int getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public OneCourseInformation(int oddOrEven, String classroom, int weekly, int startTime, int endTime) {

        this.oddOrEven = oddOrEven;
        this.classroom = classroom;
        this.weekly = weekly;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    int weekly,startTime,endTime;

    public int getOddOrEven() {
        return oddOrEven;
    }

    public void setOddOrEven(int oddOrEven) {
        this.oddOrEven = oddOrEven;
    }


    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

}
