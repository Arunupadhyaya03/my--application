package com.example.dynamicedittext;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String>
{
@Override
protected String doInBackground(String...params){

final String TAG= BackgroundWorker.class.getName();
        Log.v(TAG,"result gottttt");
        String type=params[0];
        Log.v(TAG,"ddddddddddddddd..."+type);
        String login_url="http://192.168.43.100/employee/number.php";
        if(type.equals("number")){
        try{
        Log.v(TAG,"result enetred...");
        String number1=params[1];

        Log.v(TAG,"result enetred2..."+number1);
        URL url=new URL(login_url);
        HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        Log.v(TAG,"result enetred4..."+httpURLConnection);
        OutputStream outputStream=httpURLConnection.getOutputStream();
        Log.v(TAG,"result enetred5...");
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        String post_data= URLEncoder.encode("number1","UTF-8")+"="+URLEncoder.encode(number1,"UTF-8");
        bufferedWriter.write(post_data);
        Log.v(TAG,"result enetred55555..."+post_data);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        InputStream inputStream=httpURLConnection.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
        String result="";
        String line="";
        while((line=bufferedReader.readLine())!=null){
        result+=line;
        }
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
        Log.v(TAG,"result"+result);
        return result;
        }catch(MalformedURLException e){
        e.printStackTrace();
        }catch(IOException e){
        e.printStackTrace();
        }
        }


    return null;
}
}