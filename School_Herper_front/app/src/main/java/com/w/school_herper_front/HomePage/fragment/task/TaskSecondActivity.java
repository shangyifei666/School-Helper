package com.w.school_herper_front.HomePage.fragment.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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

public class TaskSecondActivity extends AppCompatActivity {

    final List<board> boards = new ArrayList<>();
    ListView listView;
    ImageView back;
    private String url = new ServerUrl().getUrl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_second);


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
        listView = findViewById(R.id.lv_task_second);
        //点击返回上一页
        back = findViewById(R.id.img_back_second);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id",1);
                setResult(0,intent);
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(TaskSecondActivity.this, HomeShowContentActivity.class);

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
            stringBuffer.append("/School_Helper_Back/MyRecriveServlet");
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
                    board1.setUserId(object.optInt("userId"));
                    board1.setRewardId(object.optInt("rewardId"));
                    board1.setName(object.optString("name"));
                    board1.setSex(object.optString("sex"));
                    board1.setTitle(object.optString("title"));
                    board1.setContent(object.optString("content"));
                    board1.setRewardTime(object.optString("rewardTime"));
                    board1.setEndTime(object.optString("endTime"));
                    board1.setMoney("￥"+ object.optDouble("money"));
                    board1.setState(object.optString("state"));
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
            TaskAdapter taskAdapter = new TaskAdapter(TaskSecondActivity.this,R.layout.task_list_item,boards);
            listView.setAdapter(taskAdapter);
        }
    }
}
