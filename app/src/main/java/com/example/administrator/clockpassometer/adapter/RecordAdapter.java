package com.example.administrator.clockpassometer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.GameActivity;
import com.example.administrator.clockpassometer.bean.Exchang;
import com.example.administrator.clockpassometer.bean.Game;
import com.example.administrator.clockpassometer.bean.News;
import com.example.administrator.clockpassometer.bean.Record;
import com.example.administrator.clockpassometer.bean.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Record>RecordsList;
    private Context context;

    public RecordAdapter(List<Record> RecordsList, Context context) {
        this.RecordsList = RecordsList;
        this.context = context;
    }
    class RecordHolder extends RecyclerView.ViewHolder{
        private TextView Record_distance;
        private TextView Record_time;
        private TextView Record_speed;
        private TextView Record_state;
        public RecordHolder(View itemView) {
            super(itemView);
            Record_distance=(TextView)itemView.findViewById(R.id.record_item_distance);
            Record_time=(TextView)itemView.findViewById(R.id.record_item_time);
            Record_speed=(TextView)itemView.findViewById(R.id.record_item_speed);
            Record_state=(TextView)itemView.findViewById(R.id.record_item_state);
        }
    }

    @Override
    public int getItemCount() {
        return RecordsList.size();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecordHolder holder = new RecordHolder(LayoutInflater.from(context).inflate(R.layout.record_item, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Record Record = RecordsList.get(position);
        ((RecordHolder) holder).Record_distance.setText(Record.getDistance()+"");
        ((RecordHolder) holder).Record_time.setText(Record.getTime());
        ((RecordHolder) holder).Record_speed.setText(Record.getSpeed()+"");
        ((RecordHolder) holder).Record_state.setText(Record.getState());

    }
}

