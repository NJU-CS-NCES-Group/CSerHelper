package com.example.cserhelper;

/**
 * Created by wujiawei on 2017/11/5.
 */

public class MessageItem {
    String name;
    int identity;   //0表示系统，1表示老师，2表示助教
    String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageItem(String name, int identity, String message) {

        this.name = name;
        this.identity = identity;
        this.message = message;
    }
}
