package com.example.administrator.learntocook.guid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import com.example.administrator.learntocook.MainActivity;
import com.example.administrator.learntocook.R;


/**
 * Created by Administrator on 2016/9/8.
 */
public class GuidActivity extends Activity {
    private ImageView image_welcome,imgae_logo,imgae_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        init();
        animation();
    }
    //初始化控件
    private void init() {
        image_welcome= (ImageView) findViewById(R.id.image_welcome);
        imgae_icon= (ImageView) findViewById(R.id.image_icon);
        imgae_logo= (ImageView) findViewById(R.id.image_logo);
    }

    //设置图片动画
    private void animation() {
        //设置图片起始和结束的对比度为0和1
        AlphaAnimation alphWelcome = new AlphaAnimation(0,1);
        //设置动画的时间为3s
        alphWelcome.setDuration(3000);
        alphWelcome.setFillAfter(true);
        //用欢迎的图片来设置动画
        image_welcome.setAnimation(alphWelcome);
        //设置image_ico的动画
        AlphaAnimation alphIcon = new AlphaAnimation(1,0);
        alphIcon.setDuration(3000);
        alphIcon.setFillAfter(true);
        imgae_icon.setAnimation(alphIcon);
        //接受Handler的消息
        handler.sendEmptyMessage(1);
    }
    int time=4;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if(time>0){
                handler.sendEmptyMessageDelayed(1,1000);
            }
            if(time <=0){
                Intent intent = new Intent(GuidActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
