package com.example.administrator.learntocook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.administrator.learntocook.typescourses.AllCookActivity;
import com.example.administrator.learntocook.typescourses.HomeCooking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private ImageView image_jiachangcai,image_kuaishou,image_chuangyi,
                       image_sucai,image_liangcai,image_hongpei,image_mianshi,
                       image_tang,image_zzhi,ben_suosou;
    private EditText main_et;

    private String edStr="";

    private Button btn_classify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        main_et.addTextChangedListener(this);
    }
    private void init() {
        image_jiachangcai= (ImageView) findViewById(R.id.image_jiachangcai);
        image_chuangyi= (ImageView) findViewById(R.id.image_chaungyi);
        image_kuaishou= (ImageView) findViewById(R.id.image_kuaishou);
        image_sucai= (ImageView) findViewById(R.id.image_sucai);
        image_liangcai= (ImageView) findViewById(R.id.image_sucai);
        image_hongpei= (ImageView) findViewById(R.id.image_hongpei);
        image_mianshi= (ImageView) findViewById(R.id.image_mianshi);
        image_tang= (ImageView) findViewById(R.id.image_tang);
        image_zzhi= (ImageView) findViewById(R.id.image_zzhi);
        ben_suosou= (ImageView) findViewById(R.id.ben_suosou);
        btn_classify= (Button) findViewById(R.id.btn_classify);
        btn_classify.setOnClickListener(this);
        image_jiachangcai.setOnClickListener(this);
        image_kuaishou.setOnClickListener(this);
        image_sucai.setOnClickListener(this);
        image_liangcai.setOnClickListener(this);
        image_hongpei.setOnClickListener(this);
        image_mianshi.setOnClickListener(this);
        image_tang.setOnClickListener(this);
        image_zzhi.setOnClickListener(this);
        image_chuangyi.setOnClickListener(this);
        //搜索按钮
        ben_suosou.setOnClickListener(this);
        main_et= (EditText) findViewById(R.id.main_et);
    }
    //菜类的跳转
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.image_jiachangcai:
                intent.setClass(MainActivity.this,HomeCooking.class);
                int cid=1;
                intent.putExtra("index",cid);
                break;
            case R.id.image_chaungyi:
                intent.setClass(MainActivity.this,HomeCooking.class);
                cid = 3;
                intent.putExtra("index",cid);
                break;
            case R.id.image_kuaishou:
                intent.setClass(MainActivity.this,HomeCooking.class);
                cid = 2;
                intent.putExtra("index",cid);
                break;
            case R.id.image_sucai:
                intent.setClass(MainActivity.this,HomeCooking.class);
                cid = 4;
                intent.putExtra("index",cid);
                break;
            case R.id.image_liangcai:
                intent.setClass(MainActivity.this,HomeCooking.class);
                cid = 5;
                intent.putExtra("index",cid);
                break;
            case R.id.image_hongpei:
                intent.setClass(MainActivity.this,HomeCooking.class);
                cid = 6;
                intent.putExtra("index",cid);
                break;
            case R.id.image_mianshi:
                intent.setClass(MainActivity.this,HomeCooking.class);
                cid = 7;
                intent.putExtra("index",cid);
                break;
            case R.id.image_tang:
                intent.setClass(MainActivity.this,HomeCooking.class);
                cid = 8;
                intent.putExtra("index",cid);
                break;
            case R.id.image_zzhi:
                intent.setClass(MainActivity.this,HomeCooking.class);
                cid = 9;
                intent.putExtra("index",cid);
                break;
            case R.id.ben_suosou:
                intent.setClass(MainActivity.this,HomeCooking.class);
                int a =11;
                intent.putExtra("aba",a);
                intent.putExtra("et",edStr);
                break;
            case R.id.btn_classify:
                intent.setClass(MainActivity.this, AllCookActivity.class);
                break;
        }
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //EdiText的改变前
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    //EdiText发生改变时
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edStr=main_et.getText().toString();
        if(main_et.getText().toString().length()>0){
            ben_suosou.setVisibility(View.VISIBLE);
        }
        if(main_et.getText().toString().length()<2)
        {
            ben_suosou.setVisibility(View.INVISIBLE);
        }
    }
    //改变后
    @Override
    public void afterTextChanged(Editable s) {

    }
}
