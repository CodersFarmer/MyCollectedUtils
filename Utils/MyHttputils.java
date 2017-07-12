package com.heima.yqz.futuretitles.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mryang on 2017/7/12.
 * Email：
 * Content：自己封装的网络请求框架，利用httpurlconnection
 */

public class MyHttputils {
    public interface HttpCallBackListener{
        void success(String response);
        void onError(Exception e);
    }
    public static void sendRequest(final String path,final HttpCallBackListener httpCallBackListener){
        //网络请求，耗时操作，放在子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder respone = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        respone.append(line);
                    }
                    if(httpCallBackListener != null){
                        httpCallBackListener.success(respone.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(httpCallBackListener != null){
                        httpCallBackListener.onError(e);
                    }
                }finally {
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
