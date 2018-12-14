package com.w.school_herper_front.HomePage.fragment.task;

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

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private int itemLayout;
    private List<task> tasks = new ArrayList<>();

    public TaskAdapter(Context context, int itemLayout, List<task> tasks) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
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
        myHead.setImageResource(tasks.get(position).getMyhead());
        TextView name = convertView.findViewById(R.id.name);
        name.setText(tasks.get(position).getName());
        TextView endTime = convertView.findViewById(R.id.endtime);
        endTime.setText(tasks.get(position).getEndtime());
        TextView tName = convertView.findViewById(R.id.Tname);
        tName.setText(tasks.get(position).getTname());
        TextView condition = convertView.findViewById(R.id.condition);
        condition.setText(tasks.get(position).getCondition());


        return convertView;
    }
}
