package com.example.administrator.clockpassometer.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.GameFragmentPagerAdapter;
import com.example.administrator.clockpassometer.fragment.GameMessageFragment;
import com.example.administrator.clockpassometer.fragment.GameScoreFragment;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView game_xx,game_cj;
    private ViewPager game_viewPager;
    private List<Fragment>fragmentList;
    private GameFragmentPagerAdapter adapter;
    private ImageView game_fh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initData();
        initView();

    }
    private void initData(){
        fragmentList=new ArrayList<>();
        fragmentList.add(new GameMessageFragment());
        fragmentList.add(new GameScoreFragment());
    }
    private void initView(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        game_xx=(TextView)findViewById(R.id.game_xx);
        game_cj=(TextView)findViewById(R.id.game_cj);
        game_xx.setOnClickListener(this);
        game_cj.setOnClickListener(this);
        game_fh=(ImageView)findViewById(R.id.game_fh);
        game_fh.setOnClickListener(this);
        game_viewPager = (ViewPager)findViewById(R.id.game_viewPager);
        adapter = new GameFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        game_viewPager.setAdapter(adapter);
        game_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        game_xx.setTextColor(Color.parseColor("#000000"));
                        game_cj.setTextColor(Color.parseColor("#ffffff"));
                        game_xx.setBackgroundResource(R.drawable.gametext_shape);
                        game_cj.setBackgroundResource(R.drawable.gametext2_shape);
                        break;
                    case 1:
                        game_xx.setTextColor(Color.parseColor("#ffffff"));
                        game_cj.setTextColor(Color.parseColor("#000000"));
                        game_cj.setBackgroundResource(R.drawable.gametext_shape);
                        game_xx.setBackgroundResource(R.drawable.gametext2_shape);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.game_xx:
                game_viewPager.setCurrentItem(0);
                break;
            case R.id.game_cj:
                game_viewPager.setCurrentItem(1);
                break;
            case R.id.game_fh:
                finish();
                break;
        }
    }
}
