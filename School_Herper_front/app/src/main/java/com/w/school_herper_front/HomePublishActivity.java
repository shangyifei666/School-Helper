package com.w.school_herper_front;

/*
 * CONTENT: publish content
 * DEVELOPER: Zhangxixian
 * DATE: 18/12/11
 */

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomePublishActivity extends AppCompatActivity {
    TextView tvTime ;
    Button btnSelectTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_publish);

        getWidgets();
        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(tvTime);
            }
        });



    }
    /*
     * function:get all Widgets by id from layout ;
     * param: void ;
     * return: void ;
     * */
    private void getWidgets(){
        tvTime = findViewById(R.id.home_publish_tv_showtime);
        btnSelectTime = findViewById(R.id.home_publish_btn_timepicker);
    }


    /*
    * function:show time picked by user on TimePicker in Param ;
    * param: TextView tvTime;
    * return:void ;
    * */
    private void showTimePicker(final TextView tvTime){

        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd-HH:MM:SS");
                String format = simpleDateFormat.format(date);
                tvTime.setText(format);
//                Toast.makeText(HomePublishActivity.this, date.toString(), Toast.LENGTH_SHORT).show();
            }
        })
                .setDate(Calendar.getInstance())//设置默认时间
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .build();
//        pvTime.setDate(Calendar.getInstance());//设置默认时间
        pvTime.show();

    }



}
