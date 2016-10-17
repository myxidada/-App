package com.example.administrator.learntocook.typescourses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.learntocook.MainActivity;
import com.example.administrator.learntocook.R;
import com.example.administrator.learntocook.adapter.MyPagerAdapter;
import com.example.administrator.learntocook.adapter.RecyclerCokkAdapter;
import com.example.administrator.learntocook.entity.CookInfo;
import com.example.administrator.learntocook.network.CookClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/8.
 */
public class HomeCooking extends Activity implements View.OnClickListener {
    private ImageView imgae_back;
    private TextView title_back,title_cook;
    private TabLayout tabs;
    private ViewPager vp_view;
    private List<String> mTitleList = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view1,view2,view3;
    //视图集合
    private List<View> mViewList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerCokkAdapter recyclerCokkAdapter;
    //联网操作
    private List<CookInfo.ResultBean.DataBean> beanList;
    private Call<CookInfo> call;
    private final String API_KEY = "ee8b5bbe178e275ed746368fb5aa1333";
    private int cid=0;
    private String menu="";
    private String pn="3";
    private String rn="10";
    private int a=0;
    private SwipeRefreshLayout home_sw;

    private String w="",w1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_cooking);
        final Intent intent =getIntent();
        int index=intent.getIntExtra("index",cid);
        cid=index;
        int b = intent.getIntExtra("aba",a);
        a=b;
        init();
        title();
    recyclerCokkAdapter.setmOnItemClickListener(new RecyclerCokkAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Intent intent1 = new Intent(HomeCooking.this,DatailsActivity.class);
            intent1.putExtra("title",beanList.get(position).getTitle());
            intent1.putExtra("imtro",beanList.get(position).getImtro());
            intent1.putExtra("ingredients",beanList.get(position).getIngredients());
            intent1.putExtra("burden",beanList.get(position).getBurden());
            intent1.putExtra("albums",beanList.get(position).getAlbums().get(0));
            Bundle bundle = new Bundle();
            bundle.putSerializable("steps", (Serializable) beanList.get(position).getSteps());
            intent1.putExtras(bundle);
            startActivity(intent1);
        }
    });
    }
    private void title() {
        if(cid==1) {
            title_cook.setText("家常菜");
        }if(cid==2) {
            title_cook.setText("快手菜");
        } if(cid==3) {
            title_cook.setText("创意菜");
        }if(cid==4) {
            title_cook.setText("素菜");
        } if(cid==5) {
            title_cook.setText("凉菜");
        }if(cid==6) {
            title_cook.setText("烘焙");
        } if(cid==7) {
            title_cook.setText("面食");
        }if(cid==8) {
            title_cook.setText("汤");
        } if(cid==9) {
            title_cook.setText("自制调味料");
        }
    }
    private void init() {
        imgae_back= (ImageView) findViewById(R.id.image_back);
        title_back= (TextView) findViewById(R.id.title_back);
        title_cook= (TextView) findViewById(R.id.title_cook);
        home_sw= (SwipeRefreshLayout) findViewById(R.id.home_sw);
        imgae_back.setOnClickListener(this);
        title_back.setOnClickListener(this);
        tabs= (TabLayout) findViewById(R.id.tabs);
        vp_view= (ViewPager) findViewById(R.id.vp_view);
        initTabLayoutView();
        beanList=new ArrayList<>();
        recyclerView = (RecyclerView) view1.findViewById(R.id.cook_recycler);
        recyclerCokkAdapter = new RecyclerCokkAdapter(beanList,getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(a==11){
            //获取输入的菜谱名字
            menu=getIntent().getStringExtra("et");
            title_cook.setText(menu);
            Callback<CookInfo> callback = new Callback<CookInfo>() {
                @Override
                public void onResponse(Call<CookInfo> call, Response<CookInfo> response) {
                    beanList=response.body().getResult().getData();
                    recyclerCokkAdapter.addAllData(beanList);
                }
                @Override
                public void onFailure(Call<CookInfo> call, Throwable t) {
                }
            };
                call=CookClient.getsInstance().getCookInfoOne(API_KEY,menu,rn,pn);
                call.enqueue(callback);
        }else if(cid<11){
            netWorking();
        }
        recyclerView.setAdapter(recyclerCokkAdapter);
        home_sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                  recyclerCokkAdapter.notifyDataSetChanged();
                home_sw.setRefreshing(false);

            }
        });
    }
    //给TabLayout设置视图
    private void initTabLayoutView() {
        //添加标题
        mTitleList.add("全部菜谱");
        mTitleList.add("最近浏览");
        mTitleList.add("我的收藏");
        tabs.addTab(tabs.newTab().setText(mTitleList.get(0)));
        tabs.addTab(tabs.newTab().setText(mTitleList.get(1)));
        tabs.addTab(tabs.newTab().setText(mTitleList.get(2)));
        mInflater=LayoutInflater.from(this);
        view1=mInflater.inflate(R.layout.recycler_cook,null);
        view2=mInflater.inflate(R.layout.recycler_cook,null);
        view3=mInflater.inflate(R.layout.recycler_cook,null);
        //添加Tablayout的视图
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        tabs.setTabMode(tabs.MODE_FIXED);
        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList,mTitleList);
        //给ViewPager设置适配器
        vp_view.setAdapter(mAdapter);
        //将TabLayout和ViewPager关联起来。
        tabs.setupWithViewPager(vp_view);
        //给Tabs设置适配器
        tabs.setTabsFromPagerAdapter(mAdapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                Intent intent = new Intent(HomeCooking.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.title_back:
                Intent intent1 = new Intent(HomeCooking.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
    //联网操作
    private Callback<CookInfo> cookInfoCallback =new Callback<CookInfo>() {
        @Override
        public void onResponse(Call<CookInfo> call, Response<CookInfo> response) {
                beanList=response.body().getResult().getData();
                recyclerCokkAdapter.addAllData(beanList);
        }
        @Override
        public void onFailure(Call<CookInfo> call, Throwable t) {
                showErrInfo(t.getMessage());
        }
    };
    private void showErrInfo(String msg){
        Log.d("err","========"+msg);
    }
    private void netWorking(){
        call=CookClient.getsInstance().getCookIo(cid,API_KEY);
        call.enqueue(cookInfoCallback);
    }


}
