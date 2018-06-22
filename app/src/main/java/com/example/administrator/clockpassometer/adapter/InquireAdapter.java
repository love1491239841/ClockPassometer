package com.example.administrator.clockpassometer.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.bean.Inquire;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class InquireAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Inquire>InquiresList;
    private Context context;

    public InquireAdapter(List<Inquire> InquiresList, Context context) {
        this.InquiresList = InquiresList;
        this.context = context;
    }
    class InquireHolder extends RecyclerView.ViewHolder{
        private View InquireView;
        private TextView Inquire_name;
        private TextView Inquire_courses;
        public InquireHolder(View itemView) {
            super(itemView);
            InquireView = itemView;
            Inquire_name=(TextView)itemView.findViewById(R.id.inquire_item_name);
            Inquire_courses=(TextView)itemView.findViewById(R.id.inquire_item_courses);
        }
    }
    @Override
    public int getItemCount() {
        return InquiresList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        InquireHolder holder = new InquireHolder(LayoutInflater.from(context).inflate(R.layout.inquire_item, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Inquire inquire = InquiresList.get(position);
        ((InquireHolder) holder).Inquire_name.setText(inquire.getTeacherName());
        ((InquireHolder) holder).Inquire_courses.setText(inquire.getCoursesName());
        ((InquireHolder) holder).InquireView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("分数")
                        .setMessage(inquire.getScore()+"")
                        .show();
            }
        });

    }
}
