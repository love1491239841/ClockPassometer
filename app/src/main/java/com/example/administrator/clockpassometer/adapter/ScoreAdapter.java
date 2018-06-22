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
import com.example.administrator.clockpassometer.bean.Game;
import com.example.administrator.clockpassometer.bean.News;
import com.example.administrator.clockpassometer.bean.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class ScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Score>scoresList;
    private Context context;

    public ScoreAdapter(List<Score> scoresList, Context context) {
        this.scoresList = scoresList;
        this.context = context;
    }
    class ScoreHolder extends RecyclerView.ViewHolder{
        private TextView score_name;
        private TextView score_score;
        public ScoreHolder(View itemView) {
            super(itemView);
            score_name=(TextView)itemView.findViewById(R.id.score_name);
            score_score=(TextView)itemView.findViewById(R.id.score_score);
        }
    }

    @Override
    public int getItemCount() {
        return scoresList.size();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ScoreHolder holder = new ScoreHolder(LayoutInflater.from(context).inflate(R.layout.score_item, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Score score = scoresList.get(position);
        ((ScoreHolder) holder).score_name.setText(score.getName());
        ((ScoreHolder) holder).score_score.setText(score.getScore());

    }
}

