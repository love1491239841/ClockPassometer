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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class GameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Game>gamesList;
    private Context context;

    public GameAdapter(List<Game> gamesList, Context context) {
        this.gamesList = gamesList;
        this.context = context;
    }
    class NewsHolder extends RecyclerView.ViewHolder{
        private View gameView;
        private TextView game_name;
        private TextView game_state;
        private TextView game_time;
        public NewsHolder(View itemView) {
            super(itemView);
            gameView = itemView;
            game_name=(TextView)itemView.findViewById(R.id.game_item_name);
            game_state=(TextView)itemView.findViewById(R.id.game_item_state);
            game_time=(TextView) itemView.findViewById(R.id.game_item_time);
        }
    }
    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsHolder holder = new NewsHolder(LayoutInflater.from(context).inflate(R.layout.game_item, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Game game = gamesList.get(position);
        ((NewsHolder) holder).game_name.setText(game.getName());
        ((NewsHolder) holder).game_state.setText("进行中");
        ((NewsHolder) holder).game_time.setText(transferLongToDate(game.getBeginTime())+"到"+transferLongToDate(game.getOverTime()));
        ((NewsHolder) holder).gameView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra("name",game.getName());
                context.startActivity(intent);
            }
        });

    }
    public String transferLongToDate(Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(millSec);
        return sdf.format(date);
    }
}
