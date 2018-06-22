package com.example.administrator.clockpassometer.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.WebViewActivity;
import com.example.administrator.clockpassometer.adapter.MyTaskAdapter;
import com.example.administrator.clockpassometer.bean.MyTask;

import org.litepal.crud.DataSupport;

public class SchoolFragment extends Fragment implements View.OnClickListener{
    private LinearLayout sc_schoolname;
    private ImageView co_jsj,co_dm,co_jz,co_jj,co_wy,co_sj,co_tw,co_gx;
    private RecyclerView sc_recycler;
    private LinearLayoutManager layoutManager;
    private MyTaskAdapter taskAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        sc_schoolname=(LinearLayout)getActivity().findViewById(R.id.sc_schoolname);
        sc_schoolname.setOnClickListener(this);
        co_jsj=(ImageView)getActivity().findViewById(R.id.co_jsj);
        co_dm=(ImageView)getActivity().findViewById(R.id.co_dm);
        co_jz=(ImageView)getActivity().findViewById(R.id.co_jz);
        co_jj=(ImageView)getActivity().findViewById(R.id.co_jj);
        co_wy=(ImageView)getActivity().findViewById(R.id.co_wy);
        co_sj=(ImageView)getActivity().findViewById(R.id.co_sj);
        co_tw=(ImageView)getActivity().findViewById(R.id.co_tw);
        co_gx=(ImageView)getActivity().findViewById(R.id.co_gx);
        co_jsj.setOnClickListener(this);
        co_dm.setOnClickListener(this);
        co_jz.setOnClickListener(this);
        co_jj.setOnClickListener(this);
        co_wy.setOnClickListener(this);
        co_sj.setOnClickListener(this);
        co_tw.setOnClickListener(this);
        co_gx.setOnClickListener(this);
        sc_recycler = (RecyclerView)getActivity().findViewById(R.id.sc_recycler);
        layoutManager= new LinearLayoutManager(getActivity());
        sc_recycler.setLayoutManager(layoutManager);
        sc_recycler.setNestedScrollingEnabled(false);
        taskAdapter = new MyTaskAdapter(DataSupport.findAll(MyTask.class),getActivity());
        sc_recycler.setAdapter(taskAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sc_schoolname:
                String url9 = "http://kb.kkyuyin.com/item/0d1a80f42f2cd8a1bd94d24a8646ce9f.html?from=smsc&uc_param_str=dnntnwvepffrgibijbpr";
                Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
                intent1.putExtra("url",url9);
                startActivity(intent1);
                break;
            case R.id.co_jsj:
                String url1 = "https://www.csdn.net/";
                Intent intent2 = new Intent(getActivity(), WebViewActivity.class);
                intent2.putExtra("url",url1);
                startActivity(intent2);
                break;
            case R.id.co_dm:
                String url2 = "https://www.lucydraw.com/manhua";
                Intent intent3 = new Intent(getActivity(), WebViewActivity.class);
                intent3.putExtra("url",url2);
                startActivity(intent3);
                break;
            case R.id.co_jz:
                String url3 = "http://www.archcollege.com/";
                Intent intent4 = new Intent(getActivity(), WebViewActivity.class);
                intent4.putExtra("url",url3);
                startActivity(intent4);
                break;
            case R.id.co_jj:
                String url4 = "http://bbs.pinggu.org/";
                Intent intent5 = new Intent(getActivity(), WebViewActivity.class);
                intent5.putExtra("url",url4);
                startActivity(intent5);
                break;
            case R.id.co_wy:
                String url5 = "http://www.hjenglish.com/";
                Intent intent6 = new Intent(getActivity(), WebViewActivity.class);
                intent6.putExtra("url",url5);
                startActivity(intent6);
                break;
            case R.id.co_sj:
                String url6 = "http://www.sj33.cn/article/";
                Intent intent7 = new Intent(getActivity(), WebViewActivity.class);
                intent7.putExtra("url",url6);
                startActivity(intent7);
                break;
            case R.id.co_tw:
                String url7 = "http://www.astron.ac.cn/index.htm";
                Intent intent8 = new Intent(getActivity(), WebViewActivity.class);
                intent8.putExtra("url",url7);
                startActivity(intent8);
                break;
            case R.id.co_gx:
                String url8 = "http://www.dgzj.com/";
                Intent intent9 = new Intent(getActivity(), WebViewActivity.class);
                intent9.putExtra("url",url8);
                startActivity(intent9);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        taskAdapter.notifyDataSetChanged();
    }
}
