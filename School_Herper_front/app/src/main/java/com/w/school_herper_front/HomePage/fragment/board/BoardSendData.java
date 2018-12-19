package com.w.school_herper_front.HomePage.fragment.board;

import com.w.school_herper_front.R;
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
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.util.Log;


public class BoardSendData {
    private String url = new ServerUrl().getUrl();
    private Handler handler;

    public BoardSendData(Handler handler){
        this.handler = handler;
    }

    public List<board> getBoardList() throws IOException, JSONException {
        final List<board> boards = new ArrayList<>();
        final StringBuffer stringBuffer = new StringBuffer(url);
        stringBuffer.append("/School_Helper_Back/BoardItemServlet");
        final HttpURLConnection conn = (HttpURLConnection) new URL(stringBuffer.toString()).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("charset","UTF-8");

        new Thread(new Runnable() {
            @Override
            public void run() {

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
                        board1.setName(object.getString("name"));
                        board1.setTitle(object.getString("title"));
                        board1.setContent(object.getString("content"));
                        board1.setEndTime(object.getString("endTime"));
                        board1.setMoney("￥"+ object.getDouble("money"));
                        boards.add(board1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        return boards;
    }
}
