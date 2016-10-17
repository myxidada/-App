package com.example.administrator.learntocook.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.SparseArray;

import com.example.administrator.learntocook.entity.CookInfo;

import java.sql.SQLData;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/18.
 */
public class CookDBUtils  {
    private static CookDBHelper sInstance;

    private CookDBHelper helper;

    private SQLiteDatabase database;


    public CookDBUtils(CookDBHelper helper, SQLiteDatabase database) {
        this.helper = helper;
        this.database = database;
    }

    //设置单例
    public static CookDBHelper getsInstance(Context context){
        if(sInstance == null){
            synchronized (CookDBUtils.class){
                if(sInstance == null){
                    sInstance = new CookDBHelper(context);
                }
            }
        }
        return sInstance;
    }
    //像COOK_DB菜谱表中插入数据
    public void insetCook(CookInfo.ResultBean.DataBean info){
        ContentValues values = new ContentValues();
        values.put(CookDBHelper.COOK_ID,info.getId());
        values.put(CookDBHelper.TITLE,info.getTitle());
        values.put(CookDBHelper.TAGS,info.getTags());
        values.put(CookDBHelper.IMTRO,info.getImtro());
        values.put(CookDBHelper.INGREDIENTS,info.getIngredients());
        values.put(CookDBHelper.BURDEN,info.getBurden());
        values.put(CookDBHelper.ALBUMS, String.valueOf(info.getAlbums()));
        database.insert(CookDBHelper.COOK_TABLE_NAME,null,values);
    }

}
