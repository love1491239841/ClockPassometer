package com.example.administrator.clockpassometer.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.NewsActivity;
import com.example.administrator.clockpassometer.bean.MyTask;
import com.example.administrator.clockpassometer.bean.News;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class MyTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyTask>MyTaskList;
    private Context context;

    public MyTaskAdapter(List<MyTask> MyTaskList, Context context) {
        this.MyTaskList = MyTaskList;
        this.context = context;
    }
    class TaskHolder extends RecyclerView.ViewHolder{
        private TextView school_name;
        private TextView school_time;
        private View view;
        public TaskHolder(View itemView) {
            super(itemView);
            school_name = (TextView) itemView.findViewById(R.id.school_item_name);
            school_time = (TextView) itemView.findViewById(R.id.school_item_time);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return MyTaskList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskHolder(LayoutInflater.from(context).inflate(R.layout.mytask_item, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyTask myTask = MyTaskList.get(position);
        ((TaskHolder)holder).school_name.setText(myTask.getName());
        ((TaskHolder)holder).school_time.setText(myTask.getTime());
        ((TaskHolder)holder).view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("确定删除这条计划" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DataSupport.deleteAll(MyTask.class,"name = ?",MyTaskList.get(position).getName());
                                MyTaskList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#248888"));
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#248888"));
                return false;
            }
        });
        ((TaskHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("计划")
                        .setMessage(MyTaskList.get(position).getContent())
                        .show();
            }
        });
    }
    public void resetDatas() {
        MyTaskList = new ArrayList<>();
    }
    public void updateList(List<MyTask> newDatas, boolean hasMore) {
        if (newDatas != null) {
            MyTaskList.addAll(newDatas);
        }
        notifyDataSetChanged();
    }
}

