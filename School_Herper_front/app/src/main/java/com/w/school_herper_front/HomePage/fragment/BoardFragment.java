package com.w.school_herper_front.HomePage.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.w.school_herper_front.HomePage.fragment.board.BoardAdapter;
import com.w.school_herper_front.HomePage.fragment.board.board;
import com.w.school_herper_front.HomePublishActivity;
import com.w.school_herper_front.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {


    public BoardFragment() {
        // Required empty public constructor
    }

/*
*开发人：尚一飞
* 这是布告栏页
* */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board,container,false);
        /*
        * 悬浮按钮绑定点击事件
        * */
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HomePublishActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 布告栏内容
         * 数据库建立好后所有信息从数据库中存取
         * 预计每次遍历10个
         */
        final List<board> boards = new ArrayList<>();
        board board1 = new board(R.drawable.myhead,"我的名字","机场接人","有人这周日有时间吗？愿不愿意去机场接下人，顺便帮忙拎行李....","2018-1-1","￥6.00");
        boards.add(board1);
        board board2 = new board(R.drawable.myhead,"我的名字","机场接人",R.drawable.testimage,"有人这周日有时间吗？愿不愿意去机场接下人，顺便帮忙拎行李....","2018-1-1","￥6.00");
        boards.add(board2);
        ListView listView = view.findViewById(R.id.lv_boards);
        BoardAdapter boardAdapter = new BoardAdapter(getContext(),R.layout.board_list_item,boards);

        listView.setAdapter(boardAdapter);

        return view;
    }

}
