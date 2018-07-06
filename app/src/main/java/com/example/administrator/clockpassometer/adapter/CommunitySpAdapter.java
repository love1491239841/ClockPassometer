package com.example.administrator.clockpassometer.adapter;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.CommodityActivity;
import com.example.administrator.clockpassometer.activity.NewsActivity;
import com.example.administrator.clockpassometer.bean.Commodity;
import com.example.administrator.clockpassometer.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class CommunitySpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Commodity>CommodityList;
    private Context context;

    public CommunitySpAdapter(List<Commodity> CommodityList, Context context) {
        this.CommodityList = CommodityList;
        this.context = context;
    }
    class NewsHolder extends RecyclerView.ViewHolder{
        private ImageView sp_image;
        private TextView sp_name;
        private TextView sp_price;
        private Button sp_dh;
        public NewsHolder(View itemView) {
            super(itemView);
            sp_image=(ImageView)itemView.findViewById(R.id.sp_image);
            sp_name=(TextView)itemView.findViewById(R.id.sp_name);
            sp_price=(TextView)itemView.findViewById(R.id.sp_price);
            sp_dh=(Button)itemView.findViewById(R.id.sp_dh);
        }
    }
    @Override
    public int getItemCount() {
        return CommodityList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsHolder holder = new NewsHolder(LayoutInflater.from(context).inflate(R.layout.community_sp_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Commodity news = CommodityList.get(position);
        ((NewsHolder) holder).sp_name.setText(news.getName());
        ((NewsHolder) holder).sp_price.setText(news.getIntegral()+"");
        int x = position+1;
        Glide.with(context)
                .load(Constants.Image_URL+x+".jpg")
                .placeholder(R.mipmap.sb)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(((NewsHolder) holder).sp_image);
        ((NewsHolder) holder).sp_dh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommodityActivity.class);
                intent.putExtra("commodity",news);
                context.startActivity(intent);
            }
        });
    }
    public void resetDatas() {
        CommodityList = new ArrayList<>();
    }
    public void updateList(List<Commodity> CommodityDatas, boolean hasMore) {
        if (CommodityDatas != null) {
            CommodityList.addAll(CommodityDatas);
        }
        notifyDataSetChanged();
    }
}
