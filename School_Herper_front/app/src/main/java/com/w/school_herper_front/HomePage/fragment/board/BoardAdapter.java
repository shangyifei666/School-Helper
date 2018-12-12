package com.w.school_herper_front.HomePage.fragment.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.w.school_herper_front.R;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends BaseAdapter {
    private Context context;
    private int itemLayout;
    private List<board> boards = new ArrayList<>();

    public BoardAdapter(Context context, int itemLayout, List<board> boards) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.boards = boards;
    }

    @Override
    public int getCount() {
        return boards.size();
    }

    @Override
    public Object getItem(int position) {
        return boards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(itemLayout,null);
        }
        ImageView myHead = convertView.findViewById(R.id.myhead);
        myHead.setImageResource(boards.get(position).getMyhead());
        TextView name = convertView.findViewById(R.id.name);
        name.setText(boards.get(position).getName());
        TextView title = convertView.findViewById(R.id.title);
        title.setText(boards.get(position).getTitle());
        ImageView image = convertView.findViewById(R.id.image);
        image.setImageResource(boards.get(position).getImage());
        TextView content = convertView.findViewById(R.id.content);
        content.setText(boards.get(position).getContent());
        TextView endTime = convertView.findViewById(R.id.endtime);
        endTime.setText(boards.get(position).getEndTime());
        TextView money = convertView.findViewById(R.id.money);
        money.setText(boards.get(position).getMoney());



        return convertView;
    }
}