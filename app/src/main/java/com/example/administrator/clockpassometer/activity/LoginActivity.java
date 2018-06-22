package com.example.administrator.clockpassometer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity{
    private Button login;
    private EditText editTextuser,editTextpass;
    private TextView textViewzc,textViewwj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialization();
    }
    /*
    * 控件的初始化
    * */
    private void initialization(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new Click());
        editTextuser=(EditText)findViewById(R.id.loginUser);
        editTextpass=(EditText)findViewById(R.id.loginpass);
        textViewzc=(TextView)findViewById(R.id.loginzc);
        textViewzc.setOnClickListener(new Click());
        textViewwj=(TextView)findViewById(R.id.loginwj);
        textViewwj.setOnClickListener(new Click());
    }

    /*
    * 按钮的点击事件
    * */
    private class Click implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login:
//                    Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent1);
//                    finish();
                    sendRequestWithOkHttp();
                    break;
                case R.id.loginzc:
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.loginwj:
                    Intent intent2 = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    }
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendLoginOkHttpRequest(editTextuser.getText().toString(),editTextpass.getText().toString(),Constants.LOGIN_URL,new okhttp3.Callback(){
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
                    if (JsonParse.loginMessage(response).getMsg().equals("操作成功")){
                        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("name",editTextuser.getText().toString());
                        editor.putString("id",JsonParse.loginMessage(response).getId());
                        editor.putBoolean("if",true);
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
