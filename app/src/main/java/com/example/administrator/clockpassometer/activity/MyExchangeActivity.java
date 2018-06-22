package com.example.administrator.clockpassometer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.MyExchangeAdapter;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import org.json.JSONException;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;
public class MyExchangeActivity extends AppCompatActivity {
    private RecyclerView myex_recycler;
    private LinearLayoutManager layoutManager;
    private MyExchangeAdapter exchangeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exchange);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        myex_recycler=(RecyclerView)findViewById(R.id.myexchange_recycle);
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
                    OkhttpUtils.sendMyInRequest(name, Constants.ExchangSp_URL,new okhttp3.Callback(){
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
                    exchangeAdapter = new MyExchangeAdapter(JsonParse.Exchang(response),MyExchangeActivity.this);
                    myex_recycler.setAdapter(exchangeAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_myexchange,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_myexchange_dz:
                Intent intent1 = new Intent(MyExchangeActivity.this,SiteActivity.class);
                startActivity(intent1);
                break;
            case R.id.item_myexchange_ls:
                Intent intent = new Intent(this,ForRecordActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
