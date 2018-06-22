package com.example.administrator.clockpassometer.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.MyExchangeAdapter;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class SetSiteActivity extends AppCompatActivity {
    private EditText name,phone,site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_site);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        name=(EditText)findViewById(R.id.setsite_name);
        phone=(EditText)findViewById(R.id.setsite_phone);
        site=(EditText)findViewById(R.id.setsite_site);
//        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
//        initData(preferences.getString("name",null));

    }
    private void initData(final String id){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendSiteOkHttpRequest(id,name.getText().toString(),phone.getText().toString(),site.getText().toString(), Constants.Exchangtj_URL,new okhttp3.Callback(){
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
        getMenuInflater().inflate(R.menu.menu_setsite,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setsite_menu_bc:
                if (name.length()==0 || phone.length()==0 || site.length()==0){
                    Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
