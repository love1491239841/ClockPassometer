package com.example.administrator.clockpassometer.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.CommunityTzAdapter;
import com.example.administrator.clockpassometer.bean.Task;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class TaskActivity extends AppCompatActivity {
    private TextView task_fs,task_jl,task_zt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        task_fs = (TextView)findViewById(R.id.task_fs);
        task_jl = (TextView)findViewById(R.id.task_jl);
        task_zt = (TextView)findViewById(R.id.task_zt);
//        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
//        initData(preferences.getString("name",null));
    }
    private void initData(final String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendGameRequest(name,Constants.Task_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showResponse(responseData);
                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void showResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Task task = JsonParse.Task(response);

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
