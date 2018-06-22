package com.example.administrator.clockpassometer.fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.AccountActivity;
import com.example.administrator.clockpassometer.activity.HistoryActivity;
import com.example.administrator.clockpassometer.activity.InquireActivity;
import com.example.administrator.clockpassometer.activity.MyExchangeActivity;
import com.example.administrator.clockpassometer.activity.MyIntegralActivity;
import com.example.administrator.clockpassometer.activity.SetPlanActivity;
import com.example.administrator.clockpassometer.activity.SignActivity;
import com.example.administrator.clockpassometer.activity.UserinfoActivity;
import com.example.administrator.clockpassometer.adapter.CommunityTzAdapter;
import com.example.administrator.clockpassometer.bean.User;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class UserFragment extends Fragment {
    private TextView fm_name,fm_number;
    private LinearLayout fm_jl,fm_sz,fm_uinof,fm_qd,fm_dh,fm_jf,fm_zh,fm_cx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
    }

    /*
        * 初始化控件
        * */
    private void initialization(){
        fm_name = (TextView)getActivity().findViewById(R.id.fm_name);
        fm_number = (TextView)getActivity().findViewById(R.id.fm_number);
        SharedPreferences preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        initData(preferences.getString("id",null));
        fm_jl=(LinearLayout)getActivity().findViewById(R.id.fm_jl);
        fm_sz=(LinearLayout)getActivity().findViewById(R.id.fm_sz);
        fm_uinof=(LinearLayout)getActivity().findViewById(R.id.fm_uinof);
        fm_qd=(LinearLayout)getActivity().findViewById(R.id.fm_qiandao);
        fm_dh=(LinearLayout)getActivity().findViewById(R.id.fm_dh);
        fm_jf=(LinearLayout)getActivity().findViewById(R.id.fm_jf);
        fm_zh=(LinearLayout)getActivity().findViewById(R.id.fm_zh);
        fm_cx=(LinearLayout)getActivity().findViewById(R.id.fm_cx);
        fm_jl.setOnClickListener(new Click());
        fm_sz.setOnClickListener(new Click());
        fm_uinof.setOnClickListener(new Click());
        fm_qd.setOnClickListener(new Click());
        fm_dh.setOnClickListener(new Click());
        fm_jf.setOnClickListener(new Click());
        fm_zh.setOnClickListener(new Click());
        fm_cx.setOnClickListener(new Click());

    }
    private void initData(final String name){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendGameRequest(name,Constants.User_URL,new okhttp3.Callback(){
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
    private void showResponse(final String response) {
        //在子线程中更新UI
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run(){
                try {
                    User user = JsonParse.User(response);
                    fm_name.setText(user.getName());
                    fm_number.setText(user.getNo());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    /*
    * 控件添加点击事件
    * */
    private class Click implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fm_jl:
                    Intent intent1 = new Intent(getActivity(), HistoryActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.fm_sz:
                    Intent intent2 = new Intent(getActivity(), SetPlanActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.fm_uinof:
                    Intent intent3 = new Intent(getActivity(), UserinfoActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.fm_qiandao:
                    Intent intent4 = new Intent(getActivity(), SignActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.fm_dh:
                    Intent intent5 = new Intent(getActivity(), MyExchangeActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.fm_jf:
                    Intent intent6 = new Intent(getActivity(), MyIntegralActivity.class);
                    startActivity(intent6);
                    break;
                case R.id.fm_zh:
                    Intent intent7 = new Intent(getActivity(), AccountActivity.class);
                    startActivity(intent7);
                    break;
                case R.id.fm_cx:
                    Intent intent8 = new Intent(getActivity(), InquireActivity.class);
                    startActivity(intent8);
                    break;
            }
        }
    }
}
