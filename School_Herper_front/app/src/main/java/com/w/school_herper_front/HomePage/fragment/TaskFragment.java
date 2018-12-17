package com.w.school_herper_front.HomePage.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.w.school_herper_front.HomePage.fragment.task.TaskAdapter;
import com.w.school_herper_front.HomePage.fragment.task.task;
import com.w.school_herper_front.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {


    public TaskFragment() {
        // Required empty public constructor
    }

    /*
    * 这是任务页
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        /**
         * 绑定点击事件
         */
        LinearLayout linearLayout1 = view.findViewById(R.id.tab1);
        LinearLayout linearLayout2 = view.findViewById(R.id.tab2);
        LinearLayout linearLayout3 = view.findViewById(R.id.tab3);



        /**
         * 列出所有任务Adapter
         */
        final List<task> tasks = new ArrayList<>();

        task task1 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task1);
        task task2 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task2);
        task task3 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task3);

        ListView listView = view.findViewById(R.id.lv_task);

        TaskAdapter taskAdapter = new TaskAdapter(getContext(),R.layout.task_list_item,tasks);
        listView.setAdapter(taskAdapter);

        return view;
    }

}
