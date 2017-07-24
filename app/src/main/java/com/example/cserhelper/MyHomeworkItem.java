package com.example.cserhelper;

import java.util.Date;
import java.util.List;

/**
 * Created by lightning on 2017/7/22.
 */

public class MyHomeworkItem {
    private List<String> Courses;
    private List<List<String>> Homework;
    private List<List<Date>> HomeworkSubmitTime;
    private List<List<String>> HomeworkInformation;

    public void setHomeworkSubmitTime(List<List<Date>> homeworkSubmitTime) {
        HomeworkSubmitTime = homeworkSubmitTime;
    }

    public List<List<Date>> getHomeworkSubmitTime() {

        return HomeworkSubmitTime;
    }

    public MyHomeworkItem(List<String> courses, List<List<String>> homework, List<List<Date>> homeworkSubmitTime) {
        Courses = courses;
        Homework = homework;
        HomeworkSubmitTime = homeworkSubmitTime;
    }

    public MyHomeworkItem(List<String> courses, List<List<String>> homework, List<List<Date>> homeworkSubmitTime, List<List<String>> homeworkInformation) {
        Courses = courses;
        Homework = homework;
        HomeworkSubmitTime = homeworkSubmitTime;
        HomeworkInformation = homeworkInformation;
    }

    public void setCourses(List<String> courses) {

        Courses = courses;
    }

    public void setHomework(List<List<String>> homework) {
        Homework = homework;
    }

    public void setHomeworkInformation(List<List<String>> homeworkInformation) {
        HomeworkInformation = homeworkInformation;
    }

    public List<String> getCourses() {

        return Courses;
    }

    public List<List<String>> getHomework() {
        return Homework;
    }

    public List<List<String>> getHomeworkInformation() {
        return HomeworkInformation;
    }
}
