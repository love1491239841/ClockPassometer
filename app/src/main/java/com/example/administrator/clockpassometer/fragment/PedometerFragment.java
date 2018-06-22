package com.example.administrator.clockpassometer.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.clockpassometer.activity.CompleteActivity;
import com.example.administrator.clockpassometer.activity.HistoryActivity;
import com.example.administrator.clockpassometer.activity.RecordActivity;
import com.example.administrator.clockpassometer.activity.SetPlanActivity;
import com.example.administrator.clockpassometer.activity.TaskActivity;
import com.example.administrator.clockpassometer.step.UpdateUiCallBack;
import com.example.administrator.clockpassometer.step.service.StepService;
import com.example.administrator.clockpassometer.step.utils.SharedPreferencesUtils;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.view.StepArcView;

public class PedometerFragment extends Fragment implements View.OnClickListener {
    private StepArcView cc;
    private TextView tv_isSupport;
    private SharedPreferencesUtils sp;
    private LinearLayout layoutdaka,layoutrw,layoutjl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedometer, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignViews();
        initData();
    }
    private void assignViews() {
        cc = (StepArcView) getActivity().findViewById(R.id.cc);
        tv_isSupport = (TextView) getActivity().findViewById(R.id.tv_isSupport);
        layoutdaka=(LinearLayout)getActivity().findViewById(R.id.fmpe_daka);
        layoutrw=(LinearLayout)getActivity().findViewById(R.id.fmpe_rw);
        layoutjl=(LinearLayout)getActivity().findViewById(R.id.fmpe_jl);
        layoutdaka.setOnClickListener(this);
        layoutrw.setOnClickListener(this);
        layoutjl.setOnClickListener(this);
    }

    private void initData() {
        sp = new SharedPreferencesUtils(getActivity());

        //获取用户设置的计划锻炼步数，没有设置过的话默认7000
        String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
        //设置当前步数为0
        cc.setCurrentCount(Integer.parseInt(planWalk_QTY), 0);
        tv_isSupport.setText("计步中...");
        setupService();
    }
    private boolean isBind = false;
    private void setupService() {
        Intent intent = new Intent(getActivity(), StepService.class);
        isBind = getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);
    }
    ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            //设置初始化数据
            String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
            cc.setCurrentCount(Integer.parseInt(planWalk_QTY), stepService.getStepCount());
            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
                    cc.setCurrentCount(Integer.parseInt(planWalk_QTY), stepCount);
                }
            });
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fmpe_daka:
                Intent intent1 = new Intent(getActivity(), CompleteActivity.class);
                startActivity(intent1);
                break;
            case R.id.fmpe_rw:
                Intent intent2 = new Intent(getActivity(), TaskActivity.class);
                startActivity(intent2);
                break;
            case R.id.fmpe_jl:
                Intent intent3 = new Intent(getActivity(), RecordActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isBind) {
            getActivity().unbindService(conn);
        }
    }
}
