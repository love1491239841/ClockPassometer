package com.example.administrator.clockpassometer.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.bean.Commodity;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;
import java.io.IOException;
import java.util.Date;
import okhttp3.Call;
import okhttp3.Response;

public class CommodityActivity extends AppCompatActivity {
    private ImageView commodity_image;
    private TextView commodity_name,commodity_integral,commodity_inventory,commodity_description;
    private Commodity commodity;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();
    }
    private void initView(){
        commodity_image = (ImageView)findViewById(R.id.commodity_image);
        commodity_name = (TextView)findViewById(R.id.commodity_name);
        commodity_integral = (TextView)findViewById(R.id.commodity_integral);
        commodity_inventory = (TextView)findViewById(R.id.commodity_inventory);
        commodity_description = (TextView)findViewById(R.id.commodity_description);
        initData();

    }
    private void initData(){
        commodity = (Commodity) getIntent().getSerializableExtra("commodity");
        commodity_name.setText(commodity.getName());
        commodity_integral.setText("积分："+commodity.getIntegral());
        commodity_inventory.setText("库存："+commodity.getStorage());
        Glide.with(CommodityActivity.this)
                .load(Constants.Image_URL)
                .placeholder(R.mipmap.sb)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(commodity_image);
        commodity_description.setText(commodity.getContent());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_commodity,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_commodity_dh:
                AlertDialog dialog=new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("确认兑换商品" )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                initdhdata();
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#248888"));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#248888"));
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initdhdata(){
        int productId= commodity.getId();
        int number = 1;
        int integral=commodity.getIntegral();
        SharedPreferences preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        int userId = Integer.parseInt(preferences.getString("id",null));
        Date addTime = new Date();
        initdh(productId,number,integral,userId,addTime);

    }
    private void initdh(final int id, final int number, final int integral, final int userId, final Date date){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendCommodityOkHttpRequest(id,number,integral,userId,date,Constants.CommodityAdd_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showdhResponse(responseData);
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
    private void showdhResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1231565465"+response);
            }
        });
    }



}
