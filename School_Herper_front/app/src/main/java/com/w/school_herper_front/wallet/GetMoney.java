package com.w.school_herper_front.wallet;
/*
 * 功能：余额提现
 * 开发人：杨旭辉
 * 开发时间：2018.12.24
 */
import android.os.Handler;
import android.util.Log;

import com.w.school_herper_front.SendDatesToServer;
import com.w.school_herper_front.ServerUrl;
import com.w.school_herper_front.User;

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

public class GetMoney {
    private static String url=new ServerUrl().getUrl();//服务器地址
    public static final int SEND_SUCCESS=0x123;
    public static final int SEND_FAIL1=0x124;
    public static final int SEND_FAIL=0x126;
    public JSONObject object;
    public JSONArray array;
    private Handler handler;
    public GetMoney(Handler handler) {
        // TODO Auto-generated constructor stub
        this.handler=handler;
    }


    public void GetMoney(User user) {
        // TODO Auto-generated method stub
        final Map<String, String> map=new HashMap<String, String>();
        map.put("phone",user.getPhone());
        map.put("money", ""+user.getMoney());
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    if (sendGetRequest(map,url,"utf-8")) {
                        if(object.getString("success").equals("提现成功")){
                            handler.sendEmptyMessage(SEND_SUCCESS);//通知主线程数据发送成功
                        }else if(object.getString("error").equals("提现失败")){
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
            sb.append("/School_Helper_Back/GetMoneyServlet");
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
        conn.setRequestProperty("charset","UTF-8");
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
                /*
                 * 功能：把数据库数据存到静态user中，以方便后续获取数据
                 * 开发人：杨旭辉
                 * 开发时间：2018.12.17
                 */
                SendDatesToServer.user1.setMoney(object.getDouble("money"));
                Log.e("money",SendDatesToServer.user1.getMoney()+"");
            }
            return true;
        }
        return false;
    }
}
