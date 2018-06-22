package com.example.administrator.clockpassometer.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.MyExchangeAdapter;
import com.example.administrator.clockpassometer.bean.Exchang;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ForRecordActivity extends AppCompatActivity {
    private RecyclerView myex_recycler;
    private LinearLayoutManager layoutManager;
    private MyExchangeAdapter exchangeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_record);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        myex_recycler=(RecyclerView)findViewById(R.id.forRecord_recycler);
        layoutManager = new LinearLayoutManager(this);
        myex_recycler.setLayoutManager(layoutManager);
        SharedPreferences preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        initData(preferences.getString("id",null));
    }
    private void initData(final String name){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendMyInRequest(name, Constants.ExchangJl_URL,new okhttp3.Callback(){
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
            public void run(){
                try {
                    exchangeAdapter = new MyExchangeAdapter(JsonParse.Exchang(response),ForRecordActivity.this);
                    myex_recycler.setAdapter(exchangeAdapter);
                } catch (JSONException e) {
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
