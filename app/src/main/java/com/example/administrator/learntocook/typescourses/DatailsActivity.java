package com.example.administrator.learntocook.typescourses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learntocook.R;
import com.example.administrator.learntocook.adapter.DetailsAdapter;
import com.example.administrator.learntocook.entity.CookInfo;
import com.example.administrator.learntocook.network.CookClient;
import com.example.administrator.learntocook.utils.CookDBUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DatailsActivity extends Activity implements View.OnClickListener {
    //菜谱详情页面的图片ID
   private ImageView imag_datails,imag_cang1,share_a1,imag_cang2,share_a2,imag_back;
    //菜谱详情页面的TextID
   private TextView  details_title1,details_title2,details_imtro,details_ingredients,
            details_burden,details_title,title_back;
   private RecyclerView details_recycler;
    //适配器相关
   private DetailsAdapter detailsAdapter;

    private List<CookInfo.ResultBean.DataBean> data;

    private  List<CookInfo.ResultBean.DataBean> lisinfo;

    private String pn="3";
    private String rn="10";
    private String imtro="",ingredients="",burden="",menu="";

    private Call<CookInfo> call;

    private final String API_KEY = "ee8b5bbe178e275ed746368fb5aa1333";

    private CookDBUtils utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();

    }
    //控件的初始化
    private void init() {
        //图片
        imag_datails= (ImageView) findViewById(R.id.imag_details);
        imag_cang1= (ImageView) findViewById(R.id.imag_cang1);
        imag_cang2= (ImageView) findViewById(R.id.imag_cang2);
        share_a1= (ImageView) findViewById(R.id.share_a1);
        share_a2= (ImageView) findViewById(R.id.share_a2);
        imag_back= (ImageView) findViewById(R.id.image_back);
        //文字
        details_title1= (TextView) findViewById(R.id.details_title1);
        details_title2= (TextView) findViewById(R.id.details_title2);
        details_imtro= (TextView) findViewById(R.id.details_imtro);
        details_ingredients= (TextView) findViewById(R.id.details_ingredients);
        details_burden= (TextView) findViewById(R.id.details_burden);
        details_title= (TextView) findViewById(R.id.title_cook);
        imag_cang2= (ImageView) findViewById(R.id.imag_cang2);
        title_back= (TextView) findViewById(R.id.title_back);
        title_back.setOnClickListener(this);
        imag_back.setOnClickListener(this);
        imag_cang2.setOnClickListener(this);
        //Recycler的初始化
        data=new ArrayList<>();

        details_recycler= (RecyclerView) findViewById(R.id.details_recycler);
        details_recycler.setLayoutManager(new LinearLayoutManager(this));
        detailsAdapter=new DetailsAdapter(data,getBaseContext());
        menu=getIntent().getStringExtra("title");
        imtro=getIntent().getStringExtra("imtro");
        ingredients=getIntent().getStringExtra("ingredients");
        burden=getIntent().getStringExtra("burden");
        details_title.setText("菜谱详情");
        getImageAndText();
        call= CookClient.getsInstance().getCookInfoOne(API_KEY,menu,rn,pn);
        call.enqueue(callback);
        data= (List<CookInfo.ResultBean.DataBean>) getIntent().getSerializableExtra("steps");
        details_recycler.setAdapter(detailsAdapter);
        }
//设置数据
private void getImageAndText(){
        details_title1.setText(menu);
        details_title2.setText(menu);
        details_imtro.setText(imtro);
        details_ingredients.setText(ingredients);
        details_burden.setText(burden);
         Picasso.with(getBaseContext()).load(getIntent().getStringExtra("albums")).into(imag_datails);

    }

    Callback<CookInfo> callback = new Callback<CookInfo>() {
        @Override
        public void onResponse(Call<CookInfo> call, Response<CookInfo> response) {
            data=response.body().getResult().getData();
            //创建一个集合把本次页面详情的数据存到集合中去然后再存到数据库中
            lisinfo=response.body().getResult().getData();
            detailsAdapter.addData(data);

        }
        @Override
        public void onFailure(Call<CookInfo> call, Throwable t) {
            Toast.makeText(DatailsActivity.this,"联网失败",Toast.LENGTH_SHORT).show();
        }
    };
    private void netWorking(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                Intent intent = new Intent(DatailsActivity.this,HomeCooking.class);
                startActivity(intent);
                notify();
                break;
            case R.id.image_back:
                Intent intent1 =new Intent(DatailsActivity.this,HomeCooking.class);
                startActivity(intent1);
                notify();
            case R.id.imag_cang2:
                imag_cang2.setImageResource(R.drawable.cang_red);
                //当点击图片后改变图片然后把数据存入数据库中
                utils.insetCook((CookInfo.ResultBean.DataBean) lisinfo);
        }
    }
}
