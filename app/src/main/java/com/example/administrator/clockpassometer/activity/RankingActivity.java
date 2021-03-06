package com.example.administrator.clockpassometer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.CommunitySpAdapter;
import com.example.administrator.clockpassometer.adapter.RankingAdapter;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView ranking_recycler;
    private RankingAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        ranking_recycler=(RecyclerView)findViewById(R.id.ranking_recycler);
        layoutManager = new LinearLayoutManager(this);
        ranking_recycler.setLayoutManager(layoutManager);
        ranking_recycler.setNestedScrollingEnabled(false);
        ranking_recycler.setFocusable(false);
        initData();

    }
    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendOkHttpRequest(Constants.Ranking_URL,new okhttp3.Callback(){
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
                    adapter = new RankingAdapter(JsonParse.ranking(response),RankingActivity.this);
                    ranking_recycler.setAdapter(adapter);
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
