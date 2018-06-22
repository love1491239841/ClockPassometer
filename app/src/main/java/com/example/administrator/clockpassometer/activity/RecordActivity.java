package com.example.administrator.clockpassometer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.RecordAdapter;
import com.example.administrator.clockpassometer.bean.Record;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private RecyclerView record_recycler;
    private RecordAdapter adapter;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        record_recycler=(RecyclerView)findViewById(R.id.record_recycler);
        layoutManager = new LinearLayoutManager(this);
        record_recycler.setLayoutManager(layoutManager);
        adapter = new RecordAdapter(DataSupport.findAll(Record.class),RecordActivity.this);
        record_recycler.setAdapter(adapter);
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
