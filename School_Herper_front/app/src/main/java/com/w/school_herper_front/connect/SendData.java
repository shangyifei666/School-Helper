package com.w.school_herper_front.connect;


/**
 * CONTENT:SENDDATA
 * DEVELOPER:Zhangxixina
 * Date:18/12/18
 */
;
import android.os.Handler;

import com.w.school_herper_front.ServerUrl;
import com.w.school_herper_front.bean.RewardBean;

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

public class SendData{
    private static String url=new ServerUrl().getUrl();//服务器地址
    public static final int FAIL=0x123;
    public static final int SEND_SUCCESS=0x124;
    public static final int SEND_FAIL=0x125;
    public static final int CHANGE_SUCCESS=0x126;
    public static final int CHANGE_FAIL=0x127;
    public static final int CANCEL_SUCCESS=0x128;
    public static final int CANCEL_FAIL=0x129;
    public JSONObject object;
    public JSONArray array;
    private String servletUrl;
    private Handler handler;

    public SendData(Handler handler) {
        this.handler=handler;
    }

    /**
     * @function delete reward from back by rewardId
     * @param connect
     */
    public void deleteReward(final Map<String,String> connect ){
        servletUrl ="/School_Helper_Back/DeleteRewardServlet";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (sendGetRequest(connect,url,servletUrl)) {
                        if(object.getString("response").equals("success")){
                            handler.sendEmptyMessage(CANCEL_SUCCESS);//通知主线程数据发送成功
                        }else{
                            handler.sendEmptyMessage(CANCEL_FAIL);//将数据发送给服务器失败或者用户名不存在
                        }
                    }else {
                        handler.sendEmptyMessage(FAIL);//将数据发送给服务器失败
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * @function after poster ensure task
     * @param connect
     */
    public void changeState(final Map<String,String> connect ){
        servletUrl ="/School_Helper_Back/ChangeStateServlet";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (sendGetRequest(connect,url,servletUrl)) {
                        if(object.getString("response").equals("success")){
                            handler.sendEmptyMessage(CHANGE_SUCCESS);//通知主线程数据发送成功
                        }else{
                            handler.sendEmptyMessage(CHANGE_FAIL);//将数据发送给服务器失败或者用户名不存在
                        }
                    }else {
                        handler.sendEmptyMessage(FAIL);//将数据发送给服务器失败
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * @function send datas where come from showContent Page;
     * @param connect
     */
    public void SendDatasToServer(final Map<String,String> connect ){
        servletUrl ="/School_Helper_Back/GetTaskServlet";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (sendGetRequest(connect,url,servletUrl)) {
                        if(object.getString("response").equals("success")){
                            handler.sendEmptyMessage(SEND_SUCCESS);//通知主线程数据发送成功
                        }else{
                            handler.sendEmptyMessage(SEND_FAIL);//将数据发送给服务器失败或者用户名不存在
                        }
                    }else {
                        handler.sendEmptyMessage(FAIL);//将数据发送给服务器失败
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * @function send datas where come from publish Page;
     * @param reward
     */
    public void SendDatasToServer(RewardBean reward) {
        servletUrl ="/School_Helper_Back/PublishRewardServlet";
        final Map<String, String> map=new HashMap<>();
        map.put("posterId",reward.getPosterId()+"");
        map.put("rewardTitle",reward.getRewardTitle());
        map.put("rewardContent",reward.getRewardContent()) ;
        map.put("publishTime",reward.getPublishTime()+"");
        map.put("deadline",reward.getDeadline()+"");
        map.put("rewardMoney",reward.getRewardMoney()+"");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (sendGetRequest(map,url,servletUrl)) {

                        if(object.getString("response").equals("success")){
                            handler.sendEmptyMessage(SEND_SUCCESS);//通知主线程数据发送成功
                        }else{
                            handler.sendEmptyMessage(SEND_FAIL);//将数据发送给服务器失败或者用户名不存在
                        }
                    }else {
                        handler.sendEmptyMessage(FAIL);//将数据发送给服务器失败
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * @function package content ;
     * @param param
     * @param url
     * @param servletUrl
     * @return
     * @throws Exception
     */
    private  boolean sendGetRequest(Map<String, String> param, String url,String servletUrl) throws Exception {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer(url);
        if (!url.equals("")&!param.isEmpty()) {
//            sb.append("/School_Helper_Back/PublishRewardServlet");
            sb.append(servletUrl);
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
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String res=reader.readLine();
            object = new JSONObject(res);

            return true;
        }else{
            return false;
        }
    }


}

