package com.example.administrator.clockpassometer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.SiteAdapter;
import com.example.administrator.clockpassometer.bean.Site;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Response;

public class SiteActivity extends AppCompatActivity {
    private RecyclerView site_recycler;
    private SiteAdapter siteAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        
    }
    private void initView(){
        site_recycler=(RecyclerView)findViewById(R.id.site_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        site_recycler.setNestedScrollingEnabled(false);
        site_recycler.setLayoutManager(layoutManager);
        siteAdapter = new SiteAdapter(init(),SiteActivity.this);
        site_recycler.setAdapter(siteAdapter);
//        SharedPreferences preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
//        initData(preferences.getString("name",null));
        
    }
    private List<Site> init(){
        List<Site> siteList = new ArrayList<>();
        for (int i = 0;i<5;i++){
            Site site = new Site("刘健","18751308870","南通职业大学大学生公寓","地址"+i);
            siteList.add(site);
        }
        return siteList;
    }
    private void initData(final String name){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendGameRequest(name, Constants.Exchangdz_URL,new okhttp3.Callback(){
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

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_site,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.site_menu_tj:
                Intent intent = new Intent(SiteActivity.this,SetSiteActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
