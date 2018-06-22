package com.example.administrator.clockpassometer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.CommunitySpAdapter;
import com.example.administrator.clockpassometer.adapter.CommunityTzAdapter;
import com.example.administrator.clockpassometer.bean.Commodity;
import com.example.administrator.clockpassometer.bean.News;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class InformActivity extends AppCompatActivity{
    private RecyclerView inform_recycle;
    private LinearLayoutManager layoutManager;
    private CommunityTzAdapter spAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        inform_recycle=(RecyclerView)findViewById(R.id.inform_recycler);
        layoutManager = new LinearLayoutManager(this);
        inform_recycle.setLayoutManager(layoutManager);
        inform_recycle.setNestedScrollingEnabled(false);
        newsData();
    }
    private void newsData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendOkHttpRequest(Constants.Inform_URL,new okhttp3.Callback(){
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
                try {
                    spAdapter = new CommunityTzAdapter(JsonParse.communityNews(response),InformActivity.this);
                    inform_recycle.setAdapter(spAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

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
