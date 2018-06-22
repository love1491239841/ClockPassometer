package com.example.administrator.clockpassometer.activity;


import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.administrator.clockpassometer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.os.Handler;

public class WelComeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wel_come);
        handler.sendEmptyMessageDelayed(0,2000);
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };
    public void getHome(){
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        if (preferences.getBoolean("if",false)){
            Intent intent = new Intent(WelComeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(WelComeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
