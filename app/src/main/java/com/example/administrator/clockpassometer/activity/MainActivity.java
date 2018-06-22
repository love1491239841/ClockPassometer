package com.example.administrator.clockpassometer.activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.adapter.MyFragmentPagerAdapter;
import com.example.administrator.clockpassometer.fragment.CommunityFragment;
import com.example.administrator.clockpassometer.fragment.PedometerFragment;
import com.example.administrator.clockpassometer.fragment.SchoolFragment;
import com.example.administrator.clockpassometer.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment>fragmentList;
    private MyFragmentPagerAdapter adapter;
    private LinearLayout linearLayoutBasics,linearLayoutUi,linearLayoutFrame,linearLayoutUser;
    private TextView textBasics,textUi,textFrame,textUser;
    private ImageView imageBasics,imageUi,imageFrame,imageUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
        addcsh();
    }

    private void addFragment(){
        fragmentList = new ArrayList<Fragment>();
        Fragment fragment1=new PedometerFragment();
        Fragment fragment2=new CommunityFragment();
        Fragment fragment3=new SchoolFragment();
        Fragment fragment4=new UserFragment();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
    }
    private void addcsh(){
        linearLayoutBasics=(LinearLayout)findViewById(R.id.linearLayoutBasics);
        linearLayoutUi=(LinearLayout)findViewById(R.id.linearLayoutUi);
        linearLayoutFrame=(LinearLayout)findViewById(R.id.linearLayoutFrame);
        linearLayoutUser=(LinearLayout)findViewById(R.id.linearLayoutUser);
        imageBasics=(ImageView)findViewById(R.id.imageBasics);
        imageUi=(ImageView)findViewById(R.id.imageUi);
        imageFrame=(ImageView)findViewById(R.id.imageFrame);
        imageUser=(ImageView)findViewById(R.id.imageUser);
        textBasics=(TextView)findViewById(R.id.textBasics);
        textUi=(TextView)findViewById(R.id.textUi);
        textFrame=(TextView)findViewById(R.id.textFrame);
        textUser=(TextView)findViewById(R.id.textUser);
        linearLayoutBasics.setOnClickListener(new LinearClick());
        linearLayoutUi.setOnClickListener(new LinearClick());
        linearLayoutFrame.setOnClickListener(new LinearClick());
        linearLayoutUser.setOnClickListener(new LinearClick());
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        imageBasics.setImageResource(R.mipmap.jibu);
                        imageUi.setImageResource(R.mipmap.shequ1);
                        imageFrame.setImageResource(R.mipmap.school1);
                        imageUser.setImageResource(R.mipmap.user1);
                        textBasics.setTextColor(Color.parseColor("#248888"));
                        textUi.setTextColor(Color.parseColor("#707070"));
                        textFrame.setTextColor(Color.parseColor("#707070"));
                        textUser.setTextColor(Color.parseColor("#707070"));
                        break;
                    case 1:
                        imageBasics.setImageResource(R.mipmap.jibu1);
                        imageUi.setImageResource(R.mipmap.shequ);
                        imageFrame.setImageResource(R.mipmap.school1);
                        imageUser.setImageResource(R.mipmap.user1);
                        textBasics.setTextColor(Color.parseColor("#707070"));
                        textUi.setTextColor(Color.parseColor("#248888"));
                        textFrame.setTextColor(Color.parseColor("#707070"));
                        textUser.setTextColor(Color.parseColor("#707070"));
                        break;
                    case 2:
                        imageBasics.setImageResource(R.mipmap.jibu1);
                        imageUi.setImageResource(R.mipmap.shequ1);
                        imageFrame.setImageResource(R.mipmap.school);
                        imageUser.setImageResource(R.mipmap.user1);
                        textBasics.setTextColor(Color.parseColor("#707070"));
                        textUi.setTextColor(Color.parseColor("#707070"));
                        textFrame.setTextColor(Color.parseColor("#248888"));
                        textUser.setTextColor(Color.parseColor("#707070"));
                        break;
                    case 3:
                        imageBasics.setImageResource(R.mipmap.jibu1);
                        imageUi.setImageResource(R.mipmap.shequ1);
                        imageFrame.setImageResource(R.mipmap.school1);
                        imageUser.setImageResource(R.mipmap.user);
                        textBasics.setTextColor(Color.parseColor("#707070"));
                        textUi.setTextColor(Color.parseColor("#707070"));
                        textFrame.setTextColor(Color.parseColor("#707070"));
                        textUser.setTextColor(Color.parseColor("#248888"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private class LinearClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearLayoutBasics:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.linearLayoutUi:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.linearLayoutFrame:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.linearLayoutUser:
                    viewPager.setCurrentItem(3);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_item_jh:
                Intent intent = new Intent(this,MyTaskActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
