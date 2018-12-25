package com.w.school_herper_front.HomePage.fragment;

/*
 * 姓名：赵璐
 * 日期：2018.12.24
 * 说明：基本的页面跳转
 * */
//跳转到设置页面
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.w.school_herper_front.R;
import com.w.school_herper_front.Talk.MessageTalkActivity;
import com.w.school_herper_front.Talk.MessageUserActivity;
import com.w.school_herper_front.wallet.WalletActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private LinearLayout lna;
    private LinearLayout lnb;
    private LinearLayout lnc;
    private LinearLayout lnUser;


    public MessageFragment() {
        // Required empty public constructor
    }


    /*
    * 这是消息页
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        lna = view.findViewById(R.id.ln_a);
        lnb = view.findViewById(R.id.ln_b);
        lnc = view.findViewById(R.id.ln_c);
        lnUser = view.findViewById(R.id.ln_user);

        //跳转单独对话框
        lnUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MessageUserActivity.class);
                startActivity(intent);
            }
        });

        //跳转单独对话框
        lna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MessageTalkActivity.class);
                startActivity(intent);
            }
        });
        lnb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MessageTalkActivity.class);
                startActivity(intent);
            }
        });
        lnc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MessageTalkActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
        }
    }

}
