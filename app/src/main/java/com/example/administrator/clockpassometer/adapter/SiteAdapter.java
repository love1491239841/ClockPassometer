package com.example.administrator.clockpassometer.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.bean.Site;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class SiteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Site>SiteList;
    private Context context;

    public SiteAdapter(List<Site> SiteList, Context context) {
        this.SiteList = SiteList;
        this.context = context;
    }
    class SiteHolder extends RecyclerView.ViewHolder{
        private TextView site_name;
        private TextView site_phone;
        private TextView site_site;
        private TextView site_id;
        private Button site_sc;

        public SiteHolder(View itemView) {
            super(itemView);
            site_name=(TextView)itemView.findViewById(R.id.site_item_name);
            site_phone=(TextView)itemView.findViewById(R.id.site_item_phone);
            site_site=(TextView)itemView.findViewById(R.id.site_item_site);
            site_id=(TextView)itemView.findViewById(R.id.site_item_id);
            site_sc=(Button)itemView.findViewById(R.id.site_item_sc);
        }
    }
    @Override
    public int getItemCount() {
        return SiteList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SiteHolder(LayoutInflater.from(context).inflate(R.layout.site_item, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position){
        Site site = SiteList.get(position);
        ((SiteHolder)holder).site_name.setText(site.getName());
        ((SiteHolder)holder).site_phone.setText(site.getPhone());
        ((SiteHolder)holder).site_site.setText(site.getSite());
        ((SiteHolder)holder).site_id.setText(site.getId());
        ((SiteHolder)holder).site_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog =new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("确定删除地址吗？" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SiteList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#248888"));
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#248888"));
            }
        });
    }
}
