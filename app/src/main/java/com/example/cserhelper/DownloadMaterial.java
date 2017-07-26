package com.example.cserhelper;

/**
 * Created by zfwang on 2017/7/25.
 */

public class DownloadMaterial {

    private String name;
    private String uploadtime;

    public DownloadMaterial(String name, String uploadtime) {
        this.name = name;
        this.uploadtime = uploadtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

}
