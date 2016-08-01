package com.armxyitao.asynctaskdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private Button cancel;
    private ProgressBar pb;
    private TextView tv;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        cancel = (Button) findViewById(R.id.cancel);
        pb = (ProgressBar) findViewById(R.id.pb);
        tv = (TextView) findViewById(R.id.tv);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask = new MyAsyncTask(MainActivity.this);
                mTask.execute("http://www.baidu.com");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTask!=null) {
                    mTask.cancel(true);
                }
            }
        });

    }

    static class MyAsyncTask extends AsyncTask<String, Integer, String> {
        WeakReference<MainActivity> mWeakReference;

        MyAsyncTask(MainActivity mainActivity) {
            mWeakReference = new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String[] params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept-Charset","utf-8");
                conn.setRequestProperty("Content-Type", "application/x-form-urlencoded");
                conn.setRequestProperty("Accept-Encoding", "identity");
                int totalSize = conn.getContentLength();
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int count = 0;
                int length = -1;
                while ((length = is.read(buff)) != -1) {
                    os.write(buff, 0, length);
                    count += length;
                    publishProgress((int)(count*1.0f / totalSize * 100));
                    SystemClock.sleep(500);
                }
                return new String(os.toByteArray(), "utf-8");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onProgressUpdate(Integer[] values) {
            Integer progress = values[0];
            Log.d("geduo","progress ="+progress);
            if(mWeakReference.get()!=null) {
                mWeakReference.get().pb.setProgress(progress);
            }
        }

        @Override
        protected void onPostExecute(String o) {
            if(mWeakReference.get()!=null) {
                mWeakReference.get().tv.setText(o);
            }
        }
    }
}
