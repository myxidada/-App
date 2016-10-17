package com.example.administrator.learntocook.network;

import com.example.administrator.learntocook.entity.AllCook;
import com.example.administrator.learntocook.entity.CookInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface CookApi {
        //我的APIKEY://AppKey：ee8b5bbe178e275ed746368fb5aa1333
        // AppKey：ee8b5bbe178e275ed746368fb5aa1333
        @GET("cook/index")
        Call<CookInfo> getCookIo(@Query("cid")int cid,
                                 @Query("key") String apiKey);

        //搜索菜谱页面的拼接
        @GET("cook/query")
        Call<CookInfo> getCookInfoOne(@Query("key") String key,
                                      @Query("menu") String menu,
                                      @Query("rn") String rn,
                                      @Query("pn") String pn
                                      );
        //全部菜谱的拼接
        @GET("cook/category")
        Call<AllCook> getCookAll(@Query("key") String key);
}
