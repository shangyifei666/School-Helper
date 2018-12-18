package com.w.school_herper_front.HomePage.fragment.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.w.school_herper_front.R;

import java.util.ArrayList;
import java.util.List;

public class TaskSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_second);


        /**
         * 开发人：尚一飞
         * 时间：12.17
         * 简介：用于Task子页的Adapter
         */
        final List<task> tasks = new ArrayList<>();

        /**
         * 布告栏内容
         * 数据库建立好后所有信息从数据库中存取
         * 预计每次遍历10个
         */
        task task1 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task1);
        task task2 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task2);
        task task3 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task3);

        ListView listView = findViewById(R.id.lv_task_second);

        TaskAdapter taskAdapter = new TaskAdapter(this,R.layout.task_list_item,tasks);
        listView.setAdapter(taskAdapter);
    }
}
