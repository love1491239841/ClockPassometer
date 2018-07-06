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

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.ScoreAdapter;
import com.example.administrator.clockpassometer.bean.GameMessage;
import com.example.administrator.clockpassometer.bean.Score;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class GameScoreFragment extends Fragment {
    private RecyclerView score_recycler;
    private LinearLayoutManager layoutManager;
    private ScoreAdapter scoreAdapter;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_score, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        intent = getActivity().getIntent();
        score_recycler =(RecyclerView)getActivity().findViewById(R.id.score_recycle);
        layoutManager=new LinearLayoutManager(getActivity());
        score_recycler.setLayoutManager(layoutManager);
        scoreAdapter = new ScoreAdapter(initdata(),getActivity());
        score_recycler.setAdapter(scoreAdapter);
//        initData();
    }
    private List<Score> initdata(){
        List<Score> scoreList = new ArrayList<>();
        Score score1 = new Score("杨西西","98");
        scoreList.add(score1);
        Score score2 = new Score("陈志刚","63");
        scoreList.add(score2);
        Score score3 = new Score("于家岚","100");
        scoreList.add(score3);
        Score score4 = new Score("严静","34");
        scoreList.add(score4);
        Score score5 = new Score("杨坤","98");
        scoreList.add(score5);
        return scoreList;
    }
    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendGameRequest(intent.getStringExtra("name"), Constants.GameScore_URL,new okhttp3.Callback(){
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
            public void run() {

            }
        });
    }
}
