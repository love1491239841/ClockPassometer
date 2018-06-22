package com.example.administrator.clockpassometer.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.clockpassometer.R;

public class SignActivity extends AppCompatActivity {
    private TextView signTextView1,signTextView2;
    private Button signButton;
    private int day=0,score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        signTextView1=(TextView)findViewById(R.id.sign_text1);
        signTextView2=(TextView)findViewById(R.id.sign_text2);
        signButton=(Button)findViewById(R.id.sign_button);
        signButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                signTextView1.setText(day+"");
                signTextView2.setText(score+"");
                day++;
                score++;
                signButton.setBackgroundResource(R.drawable.button_shapee);
                signButton.setEnabled(false);

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
