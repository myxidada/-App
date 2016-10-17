package com.example.administrator.learntocook.network;

import com.example.administrator.learntocook.entity.AllCook;
import com.example.administrator.learntocook.entity.CookInfo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/8.
 */
public class CookClient implements CookApi{


    private final String BASE_URL = "http://apis.juhe.cn/";
    private CookApi cookApi;
    //单列
    private static CookApi sInstance;
    public static CookApi getsInstance(){
        if(sInstance==null){
            sInstance=new CookClient();
        }
        return sInstance;
    }

    public CookClient(){
        //拦截器
        //用HttpLoggingInterceptor弄出请求到的JSON字符串
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //拦截请求体
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //链接网络时用拦截器来打印
        //Okhttp的使用方式
        OkHttpClient client = new OkHttpClient.Builder()
                //使用拦截器打印日志
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                //Okhttp的基本连接
                .baseUrl(BASE_URL)
                .client(client)
                //使用Retrofit自带的转换工厂来进行数据的解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            //用Retrofit来创建一个接口
            cookApi =retrofit.create(CookApi.class);
    }
    @Override
    public Call<CookInfo> getCookIo(@Query("cid") int cid, @Query("key") String ApiKey) {
        return cookApi.getCookIo(cid,ApiKey);
    }

    @Override
    public Call<CookInfo> getCookInfoOne(@Query("key") String key, @Query("menu") String menu, @Query("rn") String rn, @Query("pn") String pn) {
        return cookApi.getCookInfoOne(key, menu, rn, pn);
    }

    @Override
    public Call<AllCook> getCookAll(@Query("key") String key) {
        return cookApi.getCookAll(key);
    }


}
