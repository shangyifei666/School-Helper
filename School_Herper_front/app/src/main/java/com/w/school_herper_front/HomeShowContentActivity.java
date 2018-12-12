package com.w.school_herper_front;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.w.school_herper_front.bean.RewardBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeShowContentActivity extends AppCompatActivity {
    TextView tvDay,tvHour1,tvHour2,tvMinute1,tvMinute2,tvSecond1,tvSecond2;
    private RewardBean reward;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_show_content);
        //获取leftTime
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
//            Date d1 = df.parse(reward.getPublishTime());
//            Date d2 = df.parse(reward.getDeadline());
            Date d1 = df.parse("2018-12-12 13:31:40");
            Date d2 = df.parse("2018-12-22 11:30:24");
            leftTime = (d2.getTime() - d1.getTime())/1000;
//            leftTime = d2.getTime() - d1.getTime();   //这样得到的差值是毫秒级别
        }catch (Exception e){
            e.printStackTrace();
        }
        getWidgets();
        countDown();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leftTime = 0;
        handler.removeCallbacks(update_thread);
    }
    /*
     * function:get all widgets
     * param:void
     * return:void
     * */
    private void getWidgets(){
        Log.e("test" ,"================getWidgets()=====================");
        tvDay = findViewById(R.id.showcontent_tv_day);
        tvHour1 = findViewById(R.id.showcontent_tv_hour1);
        tvMinute1 = findViewById(R.id.showcontent_tv_minute1);
        tvSecond1 = findViewById(R.id.showcontent_tv_second1);
        tvHour2 = findViewById(R.id.showcontent_tv_hour2);
        tvMinute2 = findViewById(R.id.showcontent_tv_minute2);
        tvSecond2 = findViewById(R.id.showcontent_tv_second2);
    }
    /*
     * function:count left time
     * param:void
     * return:void
     * */
    public void cutTime() {
//        Log.e("test" ,"================cutTime()=====================");

        day = leftTime / (60 * 60 * 24);
        hour = (leftTime - day*( 60 * 60 * 24))/( 60 * 60);
        minute = (leftTime-day*(60 * 60 * 24)-hour*(60 * 60))/60;
        second = leftTime - day*(24*60*60) - hour*(60*60) - minute*60;

//        day = leftTime / (1000*60 * 60 * 24);
//        hour = (leftTime - day*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
//        minute = (leftTime-day*(1000 * 60 * 60 * 24)-hour*(1000* 60 * 60))/(1000* 60);
//        second = (leftTime - day*24*60*60 - hour*60*60 - minute*60);
        Log.e("time" ,"========"+day+"======"+hour+"========"+minute+"========"+second+"===");

    }
    /*
    * function:count down
    * param:void
    * return:void
    * */
    private void countDown() {
//        Log.e("test", "================countDown()=====================");
        update_thread = new Runnable() {
            @Override
            public void run() {
//                Log.e("test", "================run()=====================");

                leftTime --;
                cutTime();
//                Log.e("leftTime=", "======"+String.valueOf(leftTime));

                if (leftTime > 0) {
//                    Log.e("test", "================if语句=====================");
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
                    Log.e("test", "================else语句=====================");

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



