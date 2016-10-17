package com.example.administrator.learntocook.typescourses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.learntocook.MainActivity;
import com.example.administrator.learntocook.R;
import com.example.administrator.learntocook.adapter.ItemAdapter;
import com.example.administrator.learntocook.adapter.ListViewAdapter;
import com.example.administrator.learntocook.adapter.RecyclerAllAdapter;
import com.example.administrator.learntocook.entity.AllCook;
import com.example.administrator.learntocook.entity.CookInfo;
import com.example.administrator.learntocook.network.CookClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/13.
 */
public class AllCookActivity extends Activity implements View.OnClickListener {

    private RecyclerView recycler_all,recycler_item;

    private RecyclerAllAdapter allAdapter;

    private ListView all_list;

    private ListViewAdapter listViewAdapter;

    private ItemAdapter itemAdapter;

    private List<AllCook.ResultBean> title;

    private List<AllCook.ResultBean> itemName;

    private Call<AllCook>  call;

    private final String API_KEY = "ee8b5bbe178e275ed746368fb5aa1333";

    private TextView title_cook,title_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcook);
        title_back= (TextView) findViewById(R.id.title_back);
        title_cook= (TextView) findViewById(R.id.title_cook);
        title_cook.setText("全部菜谱");
        title_back.setOnClickListener(this);
        itemName=new ArrayList<>();
        init();
    }
    private void init() {
        all_list= (ListView) findViewById(R.id.all_listview);
        itemName=new ArrayList<>();
        netWorking();
    }
    Callback<AllCook> allCookCallback  = new Callback<AllCook>() {
        @Override
        public void onResponse(Call<AllCook> call, Response<AllCook> response) {
            title=response.body().getResult();
            itemName=response.body().getResult();
            listViewAdapter=new ListViewAdapter(title,itemName,getBaseContext());
            Log.e("ffffff","saasdasd"+title.size());
            Log.e("ffffff","saasdasddddwqwe"+itemName.size());
            all_list.setAdapter(listViewAdapter);
        }
        @Override
        public void onFailure(Call<AllCook> call, Throwable t) {

        }
    };

    private void netWorking(){
        call= CookClient.getsInstance().getCookAll(API_KEY);
        call.enqueue(allCookCallback);
    }
    private void netWorking2(){
        call=CookClient.getsInstance().getCookAll(API_KEY);
        call.equals(allCookCallback);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.title_back:
                Intent intent = new Intent(AllCookActivity.this, MainActivity.class);
                startActivity(intent);
                notify();
                break;
        }
    }
}