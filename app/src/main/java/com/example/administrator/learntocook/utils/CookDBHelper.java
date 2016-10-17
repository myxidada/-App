package com.example.administrator.learntocook.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/18.
 */
public class CookDBHelper extends SQLiteOpenHelper {
    //数据库库名
    public final static String COOK_DB="datas";
    //用户表表名
    public final static String COOK_TABLE_NAME="cook";
    //需要收藏的菜名和相关数据
    public final static String COOK_ID="cook_id";
    public final static String TITLE="title";
    public final static String TAGS="tags";
    public final static String IMTRO="imtro";
    public final static String INGREDIENTS="ingredients";
    public final static String BURDEN="burden";
    public final static String ALBUMS="albums";
    public final static String STEPS="strps";

    public CookDBHelper(Context context) {
        super(context,COOK_DB,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户表信息
        db.execSQL("create table" + COOK_TABLE_NAME + " ( " +
                COOK_ID + " varchar(255), " + TITLE + " varchar(255), " + TAGS + " varchar(255), " +
                IMTRO + " varchar(255), " +INGREDIENTS + " varchar(255), " + BURDEN + " varchar(255), " +
                ALBUMS + " varchar(255), " +
                " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
