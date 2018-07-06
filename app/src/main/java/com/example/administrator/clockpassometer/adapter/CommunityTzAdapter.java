package com.example.administrator.clockpassometer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.NewsActivity;
import com.example.administrator.clockpassometer.bean.News;
import com.example.administrator.clockpassometer.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class CommunityTzAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<News>newsList;
    private Context context;

    public CommunityTzAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }
    class NewsHolder extends RecyclerView.ViewHolder{
        private TextView tz_name;
        private TextView tz_time;
        private ImageView tz_image;
        private View view;
        public NewsHolder(View itemView) {
            super(itemView);
            tz_name=(TextView)itemView.findViewById(R.id.tz_name);
            tz_time=(TextView)itemView.findViewById(R.id.tz_time);
            tz_image=(ImageView)itemView.findViewById(R.id.tz_image);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.community_tz_item, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final News news = newsList.get(position);
        ((NewsHolder) holder).tz_name.setText(news.getTitle());
        ((NewsHolder) holder).tz_time.setText(transferLongToDate(news.getAddTime()));
        Glide.with(context)
                .load(Constants.Image_URL+"news.jpg")
                .placeholder(R.mipmap.sb)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(((NewsHolder) holder).tz_image);
        ((NewsHolder) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("news",news);
                context.startActivity(intent);
            }
        });
    }
    public void resetDatas() {
        newsList = new ArrayList<>();
    }
    public void updateList(List<News> newDatas, boolean hasMore) {
        if (newDatas != null) {
            newsList.addAll(newDatas);
        }
        notifyDataSetChanged();
    }
    public String transferLongToDate(Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millSec);
        return sdf.format(date);
    }
    public void loadImage(String url,ImageView view) {
        Glide.with(context).load(url).into(view);
    }
}
