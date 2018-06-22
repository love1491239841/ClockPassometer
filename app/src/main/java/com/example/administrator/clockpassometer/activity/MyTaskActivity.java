package com.example.administrator.clockpassometer.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.bean.MyTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTaskActivity extends AppCompatActivity {
    private EditText task_name,task_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        task_name = (EditText)findViewById(R.id.record_name);
        task_content = (EditText)findViewById(R.id.record_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mytask,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.my_task_bc:
                save();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void save(){
        MyTask myTask = new MyTask();
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myTask.setName(task_name.getText().toString());
        myTask.setTime(df.format(day));
        myTask.setContent(task_content.getText().toString());
        myTask.save();
    }
}
