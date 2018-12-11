package com.w.school_herper_front.HomePage.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.w.school_herper_front.Authen.AuthenNoActivity;
import com.w.school_herper_front.Message.MessageSetActivity;
import com.w.school_herper_front.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    
    private TextView setMessage;


    public MineFragment() {
        // Required empty public constructor
    }

    /*
    * 这是个人主页
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        setMessage = view.findViewById(R.id.tv_set);
        // Inflate the layout for this fragment

        //跳转到设置页面
        setMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MessageSetActivity.class);
                startActivity(intent);
            }
        });
        return view;


        
    }
}
