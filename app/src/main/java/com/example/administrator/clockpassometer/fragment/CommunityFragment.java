package com.example.administrator.clockpassometer.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.GameListActivity;
import com.example.administrator.clockpassometer.activity.InformActivity;
import com.example.administrator.clockpassometer.activity.LoginActivity;
import com.example.administrator.clockpassometer.activity.MainActivity;
import com.example.administrator.clockpassometer.activity.MyIntegralActivity;
import com.example.administrator.clockpassometer.activity.RankingActivity;
import com.example.administrator.clockpassometer.adapter.CommunitySpAdapter;
import com.example.administrator.clockpassometer.adapter.CommunityTzAdapter;
import com.example.administrator.clockpassometer.bean.Commodity;
import com.example.administrator.clockpassometer.bean.News;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class CommunityFragment extends Fragment implements View.OnClickListener{
    private LinearLayout fmco_gd,fmco_ss,co_gd,fmco_pm;
    private SwipeRefreshLayout co_swfl;
    private RecyclerView co_rctz;
    private CommunityTzAdapter tzAdapter;
    private CommunitySpAdapter spAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView co_rcsp;
    private StaggeredGridLayoutManager layoutGridManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_community, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView(){
        fmco_gd=(LinearLayout)getActivity().findViewById(R.id.fmco_gd);
        co_gd=(LinearLayout)getActivity().findViewById(R.id.co_gd);
        fmco_ss=(LinearLayout) getActivity().findViewById(R.id.fmco_ss);
        fmco_pm=(LinearLayout) getActivity().findViewById(R.id.fmco_pm);
        fmco_ss.setOnClickListener(this);
        fmco_pm.setOnClickListener(this);
        fmco_gd.setOnClickListener(this);
        co_gd.setOnClickListener(this);
        co_rctz=(RecyclerView)getActivity().findViewById(R.id.co_rctz);
        layoutManager = new LinearLayoutManager(getActivity());
        co_rctz.setLayoutManager(layoutManager);
        co_rctz.setNestedScrollingEnabled(false);
        newsData();
        layoutGridManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        co_rcsp=(RecyclerView)getActivity().findViewById(R.id.co_rcsp);
        co_rcsp.setLayoutManager(layoutGridManager);
        co_rcsp.setNestedScrollingEnabled(false);
        commodityData();
        co_swfl=(SwipeRefreshLayout)getActivity().findViewById(R.id.co_swfl);
        co_swfl.setDistanceToTriggerSync(400);
        co_swfl.setColorSchemeColors(Color.parseColor("#50C1E9"));
        co_swfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        co_swfl.setRefreshing(true);
                        tzAdapter.resetDatas();
                        spAdapter.resetDatas();
                        co_swfl.setRefreshing(false);
                    }
                },1000);
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fmco_gd:
                Intent intent1 = new Intent(getActivity(), MyIntegralActivity.class);
                startActivity(intent1);
                break;
            case R.id.fmco_ss:
                Intent intent2 = new Intent(getActivity(), GameListActivity.class);
                startActivity(intent2);
                break;
            case R.id.co_gd:
                Intent intent3 = new Intent(getActivity(), InformActivity.class);
                startActivity(intent3);
                break;
            case R.id.fmco_pm:
                Intent intent4 = new Intent(getActivity(), RankingActivity.class);
                startActivity(intent4);
                break;
        }
    }
    /*获得通知数据
    * */
    private void newsData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendOkHttpRequest(Constants.Community_Hd_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showResponse(responseData);
                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    /*更新通知数据
    * */

    private void showResponse(final String response) {
        //在子线程中更新UI
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    tzAdapter = new CommunityTzAdapter(JsonParse.communityNews(response),getActivity());
                    co_rctz.setAdapter(tzAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
    /*
    * 获得商品数据*/
    private void commodityData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendOkHttpRequest(Constants.Community_Sp_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showSpResponse(responseData);
                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void showSpResponse(final String response) {
        //在子线程中更新UI
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    spAdapter = new CommunitySpAdapter(JsonParse.communityCommodity(response),getActivity());
                    co_rcsp.setAdapter(spAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

}
