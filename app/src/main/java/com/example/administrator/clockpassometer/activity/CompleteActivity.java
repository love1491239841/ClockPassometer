package com.example.administrator.clockpassometer.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.clockpassometer.R;
import com.example.administrator.clockpassometer.bean.Record;
import com.example.administrator.clockpassometer.utils.Constants;
import com.example.administrator.clockpassometer.utils.OkhttpUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class CompleteActivity extends AppCompatActivity implements View.OnClickListener{
    private MapView mapView;
    public LocationClient mLocationClient;
    private  boolean isFirstLocate = true;
    private BaiduMap baiduMap;
    private TextView co_jl;
    private Button co_on_off;
    private TextView co_time,co_speed;
    private static double EARTH_RADIUS = 6378.137;
    //保存上一次的坐标点
    private double lastpointlat;
    private double lastpointlong;
    //获得坐标点的次数
    private int number;

//    移动的距离
    private double distance=0;
//    速度
//    位置管理
    private LocationManager lm;
//    开关状态
    private boolean ifOff=true;
//    小时
    private int hour=0;
//    分钟
    private int minute=0;
//    秒数
    private int second=0;
//    时间开关
    private boolean ifstop=false;
//    速度
    private double speed;
//    速度开关
    private boolean ifspeed=false;

    private int mCurrentZoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLicationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_complete);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(CompleteActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(CompleteActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(CompleteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissons = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(CompleteActivity.this,permissons,1);
        }
        initView();
    }
    private void initView(){
        mapView=(MapView)findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        mCurrentZoom = 18;
        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                mCurrentZoom = (int) mapStatus.zoom;

            }
        });
        baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.FOLLOWING, true, null));
        co_jl=(TextView)findViewById(R.id.co_jl);
        co_on_off=(Button)findViewById(R.id.co_on_off);
        co_on_off.setOnClickListener(this);
        co_time=(TextView)findViewById(R.id.co_time);
        co_speed=(TextView)findViewById(R.id.co_speed);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.co_on_off:
                if (ifOff){
                    lm=(LocationManager) getSystemService(LOCATION_SERVICE);
                    boolean ok=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    if (ok){
                        requestLocation();
                        ifstop=true;
                        time();
                        ifspeed=true;
                        speed();
                        co_on_off.setText("结束");
                        ifOff=false;
                    }else {
                        new AlertDialog.Builder(this)
                                .setTitle("提示")
                                .setMessage("是否前去开启GPS定位" )
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent=new Intent();
                                        intent.setAction(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                }else {
                    /*
                    * 结束此次打卡记录运动数据
                    * 上传数据到服务器
                    *
                    * */
                    new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("确定结束打卡吗？" )
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ifstop=false;
                                    ifspeed=false;
                                    setData();
                                    save();
                                    finish();

                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();

                }
                break;
        }
    }

    private void requestLocation(){
        mLocationClient.start();
        initLocation();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setScanSpan(3000);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int result :grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                }else {
                    Toast.makeText(this, "发生未知的错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
    }
    public class MyLicationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
        }
    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate){
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
        computedrange(location.getLatitude(),location.getLongitude());
    }


    /*
    * 存储点
    * */

    private void setData(){
        final String time = hour+"时"+minute+"分"+second+"秒";
        SharedPreferences preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        final String userId = preferences.getString("id",null);
        final Date date =new Date();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkhttpUtils.sendCompleteOkHttpRequest(userId,time,speed,distance,date,Constants.Constants_URL,new okhttp3.Callback(){
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
                System.out.println("546526545962"+response);
            }
        });
    }
    private void save(){
        final String time = hour+"时"+minute+"分"+second+"秒";
        Record record =new Record();
        record.setDistance(distance);
        record.setSpeed(speed);
        record.setTime(time);
        record.setState("完成");
        record.save();
    }
    /*
    * 计算距离并且显示
    * */
    private void computedrange(double latitude,double longitude){
        if (number==0){
            lastpointlat=latitude;
            lastpointlong =longitude;
            number+=1;
        }else {
            distance=distance+(int)getDistance(lastpointlong,lastpointlat,longitude,latitude);
            co_jl.setText(distance+"");
            lastpointlat=latitude;
            lastpointlong =longitude;
        }
    }
    /*
    * 时间
    * */
    private void time(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (ifstop){
                    second++;
                    if (second>=60){
                        minute++;
                        second=0;
                    }
                    if (minute>=60){
                        hour++;
                        minute=0;
                    }
                    Message msg =new Message();
                    msg.arg1=1;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private void speed(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (ifspeed){
                    speed=(double)distance/(double) (hour*360+minute*60+second);
                    Message msg = new Message();
                    msg.arg1=2;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case 1:
                    String time = hour+"："+minute+"："+second;
                    co_time.setText(time);
                    break;
                case 2:
                    BigDecimal bg = new BigDecimal(speed);
                    double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    co_speed.setText(f1+"");
                    break;
            }
        }
    };

    /*
    * 向服务器传送数据
    * */
    private void transmitinformation(){

    }
    /*
    * 计算两个点之间距离
    * */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double a = radLat1 - radLat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示：");
            builder.setMessage("您确定退出？");

            //设置确定按钮
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            //设置取消按钮
            builder.setNegativeButton("取消",null);
            //显示提示框
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
