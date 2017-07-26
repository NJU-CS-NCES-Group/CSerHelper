package com.example.cserhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lightning on 2017/7/26.
 */

public class HomeworkRemindDBManager {
    private HomeworkRemindDBHelper helper;
    private SQLiteDatabase db;

    public HomeworkRemindDBManager(Context context) {
        helper = new HomeworkRemindDBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    public long addItem(HomeworkRemindItem item) {
        db.beginTransaction();
        long id;
        try {
//            db.execSQL("INSERT INTO HomeworkRemind VALUES(null, ?, ?, ?, ?, ?)",
//                    new Object[]{item.getCourseName(),
//                            item.getHomeworkName(),
//                            item.getRemindTime().getTime(),
//                            item.getSubmitTime().getTime(),
//                            item.isEnable()
//            });
            ContentValues values=new ContentValues();
            values.put("CourseName",item.getCourseName());
            values.put("HomeworkName",item.getHomeworkName());
            values.put("RemindTime",item.getRemindTime().getTime());
            values.put("SubmitTime",item.getSubmitTime().getTime());
            values.put("Enable",item.isEnable());
            id=db.insert("HomeworkRemind",null,values);
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
        return id;
    }

    public  void deleteItem(long id){
        db.beginTransaction();
        try{
            db.delete("HomeworkRemind","Id = ?",new String[]{id+""});
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    public void changeItem(long id,boolean enable){
        ContentValues values=new ContentValues();
        values.put("Enable",enable);
        db.beginTransaction();
        try{
            db.update("HomeworkRemind",values,"Id = ?",new String[]{id+""});
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    public List<HomeworkRemindItem> getALL(){
        List<HomeworkRemindItem> list=new ArrayList<HomeworkRemindItem>();
        Cursor cursor=db.rawQuery("select * from HomeworkRemind",null);
        while(cursor.moveToNext()){
            HomeworkRemindItem temp=new HomeworkRemindItem(
                    cursor.getString(cursor.getColumnIndex("CourseName")),
                    cursor.getString(cursor.getColumnIndex("HomeworkName")),
                    new Date(cursor.getLong(cursor.getColumnIndex("RemindTime"))),
                    new Date(cursor.getLong(cursor.getColumnIndex("SubmitTime"))),
                    cursor.getInt(cursor.getColumnIndex("Enable"))==1
            );
            temp.setID(cursor.getLong(cursor.getColumnIndex("Id")));
            list.add(temp);
        }
        return list;
    }

}
