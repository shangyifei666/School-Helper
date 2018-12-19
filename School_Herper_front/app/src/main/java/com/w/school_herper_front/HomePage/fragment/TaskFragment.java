package com.w.school_herper_front.HomePage.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.w.school_herper_front.HomePage.fragment.task.TaskAdapter;
import com.w.school_herper_front.HomePage.fragment.task.TaskFirstActivity;
import com.w.school_herper_front.HomePage.fragment.task.TaskSecondActivity;
import com.w.school_herper_front.HomePage.fragment.task.TaskThirdActivity;
import com.w.school_herper_front.HomePage.fragment.task.task;
import com.w.school_herper_front.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {
    private Banner myBanner;
    View myZhuYeView;
    List<Integer> ImageUrlData;
    List<String>ContentData;
    /*
    * 这是任务页
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        myZhuYeView=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_task,container,false);
        initBanner();


        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_task, container, false);

        /**
         * 绑定点击事件
         */
        LinearLayout linearLayout1 = myZhuYeView.findViewById(R.id.tab1);
        LinearLayout linearLayout2 = myZhuYeView.findViewById(R.id.tab2);
        LinearLayout linearLayout3 = myZhuYeView.findViewById(R.id.tab3);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaskFirstActivity.class);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaskSecondActivity.class);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaskThirdActivity.class);
                startActivity(intent);
            }
        });



        /**
         * 列出所有任务Adapter
         * 数据库建立后从数据库获取
         * 预计获取10个
         */
        final List<task> tasks = new ArrayList<>();

        task task1 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task1);
        task task2 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task2);
        task task3 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
        tasks.add(task3);

        ListView listView = myZhuYeView.findViewById(R.id.lv_task);

        TaskAdapter taskAdapter = new TaskAdapter(getContext(),R.layout.task_list_item,tasks);
        listView.setAdapter(taskAdapter);

        return myZhuYeView;
    }
    private void initBanner()
    {
        myBanner=(Banner)myZhuYeView.findViewById(R.id.banner);

        ImageUrlData=new ArrayList<>();
        ContentData=new ArrayList<>();
        ImageUrlData.add(R.drawable.home_publish_test1);
        ImageUrlData.add(R.drawable.home_publish_test2);
        ImageUrlData.add(R.drawable.home_publish_clock);
        ImageUrlData.add(R.drawable.home_publish_arrows_right);
        ContentData.add("我就是测试的什么也没有用，你就将就看吧1");
        ContentData.add("我就是测试的什么也没有用，2");
        ContentData.add("我我没有作用，你就将就看吧3");
        ContentData.add("实在没有什么测试了就写这个吧4");

        //设置样式
        myBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        myBanner.setImageLoader(new MyLoader());
        //设置图片加载地址
        myBanner.setImages(ImageUrlData);
        //轮播图片的文字
        myBanner.setBannerTitles(ContentData);
        //设置轮播的动画效果
        myBanner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        myBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        myBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        myBanner.setIndicatorGravity(BannerConfig.CENTER);
        //开始调用的方法，启动轮播图。
        myBanner.start();

    }


    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide设置图片
            Glide.with(getActivity()).load(path).into(imageView);

        }

    }

}
