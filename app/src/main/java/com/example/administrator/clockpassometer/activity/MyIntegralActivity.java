package com.example.administrator.clockpassometer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.CommunitySpAdapter;
import com.example.administrator.clockpassometer.bean.Commodity;
import com.example.administrator.clockpassometer.bean.GameMessage;
import com.example.administrator.clockpassometer.bean.Integral;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MyIntegralActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView integral_recycle;
    private StaggeredGridLayoutManager layoutManager;
    private CommunitySpAdapter spAdapter;
    private LinearLayout integral_dh,integral_hq;
    private TextView integral_jf,integral_dj;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_integral);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        preferences = getSharedPreferences("data",MODE_PRIVATE);
        integral_jf=(TextView)findViewById(R.id.integral_jf);
        integral_dj=(TextView)findViewById(R.id.integral_dj);
        initjf();
        integral_recycle=(RecyclerView)findViewById(R.id.integral_recycle);
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        integral_recycle.setLayoutManager(layoutManager);
        integral_recycle.setNestedScrollingEnabled(false);
        integral_recycle.setFocusable(false);
        commodityData();
        integral_dh=(LinearLayout)findViewById(R.id.integral_dh);
        integral_hq=(LinearLayout)findViewById(R.id.integral_hq);
        integral_dh.setOnClickListener(this);
        integral_hq.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.integral_dh:
                Intent intent1 = new Intent(this,MyExchangeActivity.class);
                startActivity(intent1);
                break;
            case R.id.integral_hq:
                Intent intent2 = new Intent(this,GainActivity.class);
                startActivity(intent2);
                break;
        }
    }
    private void initjf(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendMyInRequest(preferences.getString("id",null),Constants.IntegralJf_URL,new okhttp3.Callback(){
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
                    Integral integral = JsonParse.integral(response);
                    integral_jf.setText(integral.getAmount()+"");
                    if (integral.getAmount()>=500){
                        integral_dj.setText("金牌会员");
                    }else if (integral.getAmount()<500 && integral.getAmount()>=200){
                        integral_dj.setText("银牌会员");
                    }else {
                        integral_dj.setText("铜牌会员");
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void commodityData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendOkHttpRequest(Constants.IntegralSp_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showSpResponse(responseData);
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
    private void showSpResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    spAdapter = new CommunitySpAdapter(JsonParse.communityCommodity(response),MyIntegralActivity.this);
                    integral_recycle.setAdapter(spAdapter);
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
