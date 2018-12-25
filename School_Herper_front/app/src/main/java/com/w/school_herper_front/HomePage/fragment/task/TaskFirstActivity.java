package com.w.school_herper_front.HomePage.fragment.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.w.school_herper_front.HomePage.fragment.board.board;
import com.w.school_herper_front.HomeShowContentActivity;
import com.w.school_herper_front.R;
import com.w.school_herper_front.SendDatesToServer;
import com.w.school_herper_front.ServerUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TaskFirstActivity extends AppCompatActivity {
    final List<board> boards = new ArrayList<>();
    ListView listView;
    private String url = new ServerUrl().getUrl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_first);

        /**
         * 开发人：尚一飞
         * 时间：12.17
         * 简介：用于Task子页的Adapter
         */


        /**
         * 布告栏内容
         * 数据库建立好后所有信息从数据库中存取
         * 预计每次遍历10个
         */
//        task task1 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
//        tasks.add(task1);
//        task task2 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
//        tasks.add(task2);
//        task task3 = new task(R.drawable.myhead,"我的名字","2018-12-12","代取快递","待验收");
//        tasks.add(task3);

        new TaskAsyncTask().execute();
        listView = findViewById(R.id.lv_task_first);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(TaskFirstActivity.this, HomeShowContentActivity.class);

                board Tboard = boards.get(position);
                intent.putExtra("poster",Tboard);

                startActivity(intent);
            }
        });
        boards.clear();
    }

    /**
     * 创建异步任务
     */
    class TaskAsyncTask extends AsyncTask<Void,Void,List<board>> {

        @Override
        protected List<board> doInBackground(Void... voids) {
            final StringBuffer stringBuffer = new StringBuffer(url);
            stringBuffer.append("/School_Helper_Back/BoardItemServlet");
            stringBuffer.append("?userId=");
            stringBuffer.append(URLEncoder.encode(String.valueOf(SendDatesToServer.user1.getUserId())));
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) new URL(stringBuffer.toString()).openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("charset","UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStream is = null;

            try {
                is = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                JSONArray array = new JSONArray(res);
                for (int i=0; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    board board1 = new board();
                    board1.setMyhead(R.drawable.myhead);
                    board1.setUserId(object.getInt("userId"));
                    board1.setRewardId(object.getInt("rewardId"));
                    board1.setName(object.getString("name"));
                    board1.setSex(object.getString("sex"));
                    board1.setTitle(object.getString("title"));
                    board1.setContent(object.getString("content"));
                    board1.setRewardTime(object.getString("rewardTime"));
                    board1.setEndTime(object.getString("endTime"));
                    board1.setMoney("￥"+ object.getDouble("money"));
                    board1.setState(object.getString("state"));
                    boards.add(board1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return boards;
        }

        protected void onPostExecute(List<board> boards){
            super.onPostExecute(boards);
            TaskAdapter taskAdapter = new TaskAdapter(TaskFirstActivity.this,R.layout.task_list_item,boards);
            listView.setAdapter(taskAdapter);
        }
    }
}
