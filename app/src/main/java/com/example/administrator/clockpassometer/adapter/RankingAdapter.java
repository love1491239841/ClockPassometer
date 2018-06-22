package com.example.administrator.clockpassometer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.NewsActivity;
import com.example.administrator.clockpassometer.bean.News;
import com.example.administrator.clockpassometer.bean.Ranking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class RankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Ranking>RankingList;
    private Context context;

    public RankingAdapter(List<Ranking> RankingList, Context context) {
        this.RankingList = RankingList;
        this.context = context;
    }
    class RankingHolder extends RecyclerView.ViewHolder{
        private ImageView ra_icon;
        private TextView ra_name;
        public RankingHolder(View itemView) {
            super(itemView);
            ra_icon=(ImageView)itemView.findViewById(R.id.ranking_item_icon);
            ra_name=(TextView)itemView.findViewById(R.id.ranking_item_name);
        }
    }
    @Override
    public int getItemCount() {
        return RankingList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RankingHolder(LayoutInflater.from(context).inflate(R.layout.ranking_item, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Ranking ranking = RankingList.get(position);
        if (position == 0){
            ((RankingHolder) holder).ra_icon.setImageResource(R.mipmap.diyi);
            ((RankingHolder) holder).ra_name.setText(ranking.getUserName());
        }else if (position == 1){
            ((RankingHolder) holder).ra_icon.setImageResource(R.mipmap.dier);
            ((RankingHolder) holder).ra_name.setText(ranking.getUserName());
        }else if (position == 2){
            ((RankingHolder) holder).ra_icon.setImageResource(R.mipmap.disan);
            ((RankingHolder) holder).ra_name.setText(ranking.getUserName());
        }else {
            ((RankingHolder) holder).ra_icon.setImageResource(R.mipmap.din);
            ((RankingHolder) holder).ra_name.setText(ranking.getUserName());
        }
    }
}
