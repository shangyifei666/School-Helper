package com.w.school_herper_front.Message;

import android.os.Handler;

import com.w.school_herper_front.ServerUrl;
import com.w.school_herper_front.bean.ReviseUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SetMessageToServer {
    private static String url=new ServerUrl().getUrl();//服务器地址
    public static final int SEND_SUCCESS=0x123;
    public static final int SEND_FAIL=0x124;
    private Handler handler;
    public JSONObject object;
    public JSONArray array;
    private String userId;
    private ReviseUser reviseUser = new ReviseUser();
    private String headphoto;
    private String username;
    private String names;
    private String autograph;
    private String sex;
    private String phone;
    private String authentication;

    public SetMessageToServer(Handler handler) {
        // TODO Auto-generated constructor stub
        this.handler=handler;
    }

    public ReviseUser SetMessageToServer(String id){
        // TODO Auto-generated method stub
        userId = id;
        final Map<String, String>map=new HashMap<String, String>();
        map.put("userId",userId);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
//                    Log.e("error",object.getString("error"));
                    if (sendGetRequest(map,url,"utf-8")) {
                        //数据导入
                        headphoto = object.getString("headphoto");
                        username = object.getString("username");
                        names = object.getString("names");
                        autograph = object.getString("autograph");
                        sex = object.getString("sex");
                        phone = object.getString("phone");
                        authentication = object.getString("authentication");
                        reviseUser.setAuthentication(authentication);
                        reviseUser.setAutograph(autograph);
                        reviseUser.setHeadPhoto(headphoto);
                        reviseUser.setNames(names);
                        reviseUser.setPhone(phone);
                        reviseUser.setSex(sex);
                        reviseUser.setUsername(username);
                        handler.sendEmptyMessage(SEND_SUCCESS);//通知主线程数据发送成功
                    }else {
                        handler.sendEmptyMessage(SEND_FAIL);//将数据发送给服务器失败
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
        return reviseUser;
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
