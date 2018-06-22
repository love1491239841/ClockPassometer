package com.example.administrator.clockpassometer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.activity.GameListActivity;
import com.example.administrator.clockpassometer.adapter.GameAdapter;
import com.example.administrator.clockpassometer.bean.GameMessage;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.JsonParse;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class GameMessageFragment extends Fragment {
    private TextView messageName,messageZz,messageTs,messageRs;
    private Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(){
        messageName = (TextView)getActivity().findViewById(R.id.game_message_name);
        intent = getActivity().getIntent();
        messageName.setText(intent.getStringExtra("name"));
        messageZz = (TextView)getActivity().findViewById(R.id.game_message_zz);
        messageTs = (TextView)getActivity().findViewById(R.id.game_message_ts);
        messageRs = (TextView)getActivity().findViewById(R.id.game_message_rs);
        messageZz.setText("移动公司");
        messageTs.setText("15天");
        messageRs.setText("20人");
//        initData();
    }
    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendGameRequest(intent.getStringExtra("name"),Constants.GameMessage_URL,new okhttp3.Callback(){
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
                GameMessage gameMessage = JsonParse.gemeMessage(response);

            }
        });
    }
}
