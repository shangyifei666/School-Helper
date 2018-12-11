package com.w.school_herper_front;

/*
 * 功能：通过GET方式向服务器发送注册用户数据
 * 开发人：赵璐
 * 开发时间：2018.11.27
 *
 * 描述：注册页面，相关xml：activity_main.xml
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendDateToServer {
    private static String url=new ServerUrl().getUrl();//服务器地址
    public static final int SEND_SUCCESS=0x123;
    public static final int SEND_FAIL=0x124;
    public static final int SEND_FAIL1=0x125;
    private Handler handler;
    public JSONObject object;
    public JSONArray array;
    public SendDateToServer(Handler handler) {
        // TODO Auto-generated constructor stub
        this.handler=handler;
    }


    public void SendDataToServer(User user) {
        // TODO Auto-generated method stub
        final Map<String, String>map=new HashMap<String, String>();
        map.put("phone",user.getPhone());
        map.put("password", user.getPassword());
        map.put("name", user.getName());
        map.put("school",user.getSchool());
        map.put("stuNumber",user.getStuNumber());
        new Thread(new Runnable() {
            @Override
            public void run() {

                // TODO Auto-generated method stub
                try {
//                    Log.e("error",object.getString("error"));
                    if (sendGetRequest(map,url,"utf-8")) {
                        if(object.getString("success").equals("注册成功")){
                            handler.sendEmptyMessage(SEND_SUCCESS);//通知主线程数据发送成功
                        }else if(object.getString("error").equals("用户已存在")){
                            handler.sendEmptyMessage(SEND_FAIL1);//将数据发送给服务器失败或者用户名不存在
                        }
                    }else {
                        handler.sendEmptyMessage(SEND_FAIL);//将数据发送给服务器失败
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private  boolean sendGetRequest(Map<String, String> param, String url,String encoding) throws Exception {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer(url);
        if (!url.equals("")&!param.isEmpty()) {
            sb.append("/School_Helper_Back/RegisterServlet");
            sb.append("?");
            for (Map.Entry<String, String>entry:param.entrySet()) {
                sb.append(entry.getKey()+"=");
                sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);//删除字符串最后 一个字符“&”
        }
        HttpURLConnection conn=(HttpURLConnection) new URL(sb.toString()).openConnection();
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("charset", "UTF-8");
        conn.setConnectTimeout(5000);
        conn.setDoInput(true);
        if (conn.getResponseCode()==200) {

            //数据接收
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String res=reader.readLine();
            array=new JSONArray(res);
            for(int i=0;i<array.length();i++) {
                object = array.getJSONObject(i);
            }
            return true;
        }
        return false;
    }
}

