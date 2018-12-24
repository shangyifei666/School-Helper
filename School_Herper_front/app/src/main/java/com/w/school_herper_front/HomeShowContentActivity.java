package com.w.school_herper_front;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.w.school_herper_front.bean.RewardBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeShowContentActivity extends AppCompatActivity {
    private TextView tvDay,tvHour1,tvHour2,tvMinute1,tvMinute2,tvSecond1,tvSecond2;
    private long leftTime ;      //剩余时间 = 截止时间 - 当前时间
    long day,hour,minute,second;
    private Handler handler = new Handler();
    private Runnable update_thread ;
    private final Handler handlerStop = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    leftTime = 0;
                    handler.removeCallbacks(update_thread);
                    break;
            }
            super.handleMessage(msg);
        }

    };
    private ImageView ivUserHead,ivUserSex;
    private TextView tvUserName,tvMoney,tvContent,tvPublishTime,tvName1;
    private LinearLayout llValue,llvalue1,llUser1;
    private RewardBean reward= null;
    private Button btnUserChat1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_show_content);
        getWidgets();

        Intent intent = getIntent();
        reward = (RewardBean) intent.getSerializableExtra("reward");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date d1 = df.parse(df.format(new Date()));//当前时间
            Date d2 = df.parse(reward.getDeadline());
//            Date d1 = df.parse("2018-12-12 13:31:40");
//            Date d2 = df.parse("2018-12-22 11:30:24");
            leftTime = (d2.getTime() - d1.getTime())/1000;
                countDown();
        }catch (Exception e){
            e.printStackTrace();
        }

        //判断以显示控件
        judge(intent);


    }

    /**
     * function:to judge whether to show widgets by intent
     *
     */
    private void judge(Intent intent){
        if(intent.getSerializableExtra("poser") != null){//我的接收
            User poster = (User)intent.getSerializableExtra("poser");
            llUser1.setVisibility(View.VISIBLE);
            tvName1.setText(poster.getName());
//            showValue(llvalue1,poster.getValue());
            showValue(llvalue1,10);
//            btnUserChat1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(HomeShowContentActivity.this,);
//                    startActivity(i);
//                }
//            });
            String state = reward.getRewardState();
            btnUserChat1.setVisibility(View.VISIBLE);
            if("待完成".equals(state)){
                btnUserChat1.setText("待完成");
                btnUserChat1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnUserChat1.setBackgroundColor(Color.GRAY);
                        btnUserChat1.setText("待确认");
                        btnUserChat1.setEnabled(false);//不可点击
                        //-----------------发送消息到发布者，请求确认
                    }
                });
            }else if("待确认".equals(state)){
                btnUserChat1.setText("待确认");
                btnUserChat1.setBackgroundColor(Color.GRAY);//灰色
                btnUserChat1.setEnabled(false);//不可点击

            }else if("已完成".equals(state)){
                btnUserChat1.setText("待已完成");
                btnUserChat1.setBackgroundColor(Color.GRAY);//灰色
                btnUserChat1.setEnabled(false);//不可点击
                Message message = new Message();//结束倒计时
                message.what = 1;
                handlerStop.sendMessage(message);

            }else{//已截止
                btnUserChat1.setText("已截止");
                btnUserChat1.setBackgroundColor(Color.GRAY);//灰色
                btnUserChat1.setEnabled(false);//不可点击
            }
        }else if(intent.getSerializableExtra("user")!=null){
            User user = (User)intent.getSerializableExtra("user");
            showContent(user);
        }else{
            if(intent.getSerializableExtra("receiver") != null){
                //有人接收，显示信息

            }else{
                //无人接收

            }
        }
    }
    /**
     * function:get all widgets
     * param:void
     * return:void
     * */
    private void getWidgets(){
        tvDay = findViewById(R.id.showcontent_tv_day);
        tvHour1 = findViewById(R.id.showcontent_tv_hour1);
        tvMinute1 = findViewById(R.id.showcontent_tv_minute1);
        tvSecond1 = findViewById(R.id.showcontent_tv_second1);
        tvHour2 = findViewById(R.id.showcontent_tv_hour2);
        tvMinute2 = findViewById(R.id.showcontent_tv_minute2);
        tvSecond2 = findViewById(R.id.showcontent_tv_second2);
        ivUserHead = findViewById(R.id.showcontent_iv_head);
        ivUserSex = findViewById(R.id.showcontent_iv_sex);
        tvUserName = findViewById(R.id.showcontent_tv_name);
        llValue = findViewById(R.id.showcontent_ll_value);
        tvMoney = findViewById(R.id.showcontent_tv_money);
        tvContent = findViewById(R.id.showcontent_tv_content);
        tvPublishTime = findViewById(R.id.showcontent_tv_publish_time);
        llUser1 = findViewById(R.id.showcontent_ll_user1);
        tvName1 = findViewById(R.id.showcontent_tv_name1);
        llvalue1 = findViewById(R.id.showcontent_ll_value1);
        btnUserChat1 = findViewById(R.id.showcontent_btn_user1_chat);

    }
    /**
     * function: show user informateion
     * return :User
     */
    private void showContent(User user){
//        ivUserHead.setImageResource();
        tvUserName.setText(user.getName());
        if("女".equals(user.getSex())){
            ivUserSex.setImageResource(R.drawable.girl);
        }else{
            ivUserSex.setImageResource(R.drawable.boy);
        }
        int value = user.getValue();
        showValue(llValue,value);
        tvMoney.setText("赏金："+reward.getRewardMoney()+" ￥");
        tvContent.setText(reward.getRewardContent());
        tvPublishTime.setText(reward.getPublishTime());

    }

    /**
     * function:show value
     * param:LinearLayout Layout; value
     */
    private void showValue(LinearLayout layout,int value){
        layout.removeAllViews();
        if(value<5){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(20,20));
            imageView.setImageResource(R.drawable.love);
            llValue.addView(imageView);
        }else if(value<10){
            for(int i =0 ;i<2;i++){
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20,20);
                lp.setMargins(10,0,0,0);
                imageView.setLayoutParams(lp);
                imageView.setImageResource(R.drawable.love);
                layout.addView(imageView);
            }
        }else if(value<15){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(20,20));
            imageView.setImageResource(R.drawable.star);
            layout.addView(imageView);
        }else if(value<20){
            for(int i =0 ;i<2;i++){
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20,20);
                lp.setMargins(10,0,0,0);
                imageView.setLayoutParams(lp);
                imageView.setImageResource(R.drawable.star);
                layout.addView(imageView);
            }
        }else{
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(20,20));
            imageView.setImageResource(R.drawable.diamond);
            layout.addView(imageView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leftTime = 0;
        handler.removeCallbacks(update_thread);
    }

    /**
     * function:count left time
     * param:void
     * return:void
     * */
    public void cutTime() {
        day = leftTime / (60 * 60 * 24);
        hour = (leftTime - day*( 60 * 60 * 24))/( 60 * 60);
        minute = (leftTime-day*(60 * 60 * 24)-hour*(60 * 60))/60;
        second = leftTime - day*(24*60*60) - hour*(60*60) - minute*60;

    }
    /**
    * function:count down
    * param:void
    * return:void
    * */
    private void countDown() {
        update_thread = new Runnable() {
            @Override
            public void run() {
                leftTime --;
                cutTime();
                if (leftTime > 0) {
                    tvDay.setText("距离任务截止还有" + String.valueOf(day) + "天");
                    tvHour1.setText(String.valueOf(hour / 10));
                    tvHour2.setText(String.valueOf(hour % 10));
                    tvMinute1.setText(String.valueOf(minute / 10));
                    tvMinute2.setText(String.valueOf(minute % 10));
                    tvSecond1.setText(String.valueOf(second / 10));
                    tvSecond2.setText(String.valueOf(second % 10));

                    handler.postDelayed(this, 1000);//每一秒执行一次

                } else {//倒计时结束
                    //处理业务流程
                    //发送消息，结束倒计时
                    Message message = new Message();
                    message.what = 1;
                    handlerStop.sendMessage(message);
                }
            }
        };
        update_thread.run();
    }






}



