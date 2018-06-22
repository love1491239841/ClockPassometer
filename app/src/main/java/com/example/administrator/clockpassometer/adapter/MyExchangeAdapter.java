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
import com.example.administrator.clockpassometer.bean.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class MyExchangeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Exchang>ExchangsList;
    private Context context;

    public MyExchangeAdapter(List<Exchang> ExchangsList, Context context) {
        this.ExchangsList = ExchangsList;
        this.context = context;
    }
    class ExchangHolder extends RecyclerView.ViewHolder{
        private TextView Exchang_name;
        private TextView Exchang_price;
        private TextView Exchang_state;
        public ExchangHolder(View itemView) {
            super(itemView);
            Exchang_name=(TextView)itemView.findViewById(R.id.myexchange_item_name);
            Exchang_price=(TextView)itemView.findViewById(R.id.myexchange_item_price);
            Exchang_state=(TextView)itemView.findViewById(R.id.myexchange_item_state);
        }
    }

    @Override
    public int getItemCount() {
        return ExchangsList.size();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExchangHolder holder = new ExchangHolder(LayoutInflater.from(context).inflate(R.layout.myexchange_item, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Exchang Exchang = ExchangsList.get(position);
        ((ExchangHolder) holder).Exchang_name.setText(Exchang.getProductName());
        ((ExchangHolder) holder).Exchang_price.setText(Exchang.getIntegral()+"");
        ((ExchangHolder) holder).Exchang_state.setText("发货中");

    }
    public void resetDatas() {
        ExchangsList = new ArrayList<>();
    }
    public void updateList(List<Exchang> ExchangData, boolean hasMore) {
        if (ExchangData != null) {
            ExchangsList.addAll(ExchangData);
        }
        notifyDataSetChanged();
    }
}

