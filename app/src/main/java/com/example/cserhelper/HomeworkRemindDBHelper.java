package com.example.cserhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lightning on 2017/7/26.
 */

public class HomeworkRemindDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "HomeworkRemindDB.db";
    public static final String TABLE_NAME = "HomeworkRemind";

    public HomeworkRemindDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TABLE_NAME + " (Id integer primary key, " +
                "CourseName text, " +
                "HomeworkName text, " +
                "RemindTime integer," +
                "SubmitTime integer," +
                "Enable integer )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
