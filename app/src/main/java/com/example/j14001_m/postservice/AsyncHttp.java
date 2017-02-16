package com.example.j14001_m.postservice;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

/**
 * Created by J14001_M on 2017/02/10.
 */
public class AsyncHttp extends AsyncTask<String,Integer,Boolean>{

    HttpURLConnection urlConnection = null;
    Boolean flg = false;

    String lat;
    String lng;

    // コンストラクタ
    public AsyncHttp(String lat,String lng){
        this.lat = lat;
        this.lng = lng;
}

//非同期処理ここから
@Override
protected  Boolean doInBackground(String... contents){
    String urlinput = "http://10.250.0.37/upload/post.php";
    try{
        URL url = new URL(urlinput);
        urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        //POST用パラメータ
        String postDataSample = "name=" + lat + "&text=" + lng;

        //POSTパラメータ設定
        OutputStream out = urlConnection.getOutputStream();
        out.write(postDataSample.getBytes());
        out.flush();
        out.close();

        //レスポンスを受け取る
        urlConnection.getInputStream();

        flg = true;
    }catch(MalformedURLException e){
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();
    }
    return flg;
    }
}
