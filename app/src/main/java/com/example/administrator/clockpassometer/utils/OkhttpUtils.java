package com.example.administrator.clockpassometer.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class OkhttpUtils {
    public static void sendOkHttpRequest(final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendLoginOkHttpRequest(String name,String pass,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("no",name)
                .add("pwd",pass)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendGameRequest(String id,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("id",id)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendMyInRequest(String id,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id",id)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendinquireRequest(String id,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("student_id",id)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendSiteOkHttpRequest(String id,String name,String phone,String site,final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("id", id)
                .add("name",name)
                .add("phone", phone)
                .add("site", site)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendCommodityOkHttpRequest(int id, int number, int intg, int userId, Date date, final String address, final Callback callback) {
        OkHttpClient client = new OkHttpClient();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("1231565465"+df.format(date));
        RequestBody requestBody = new FormBody.Builder()
                .add("product_id", String.valueOf(id))
                .add("number", String.valueOf(number))
                .add("integral", String.valueOf(intg))
                .add("user_id", String.valueOf(userId))
                .add("add_time", df.format(date))
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendCompleteOkHttpRequest(String id, String time,double speed, double distance, Date date, final String address, final Callback callback) {
        OkHttpClient client = new OkHttpClient();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("1231565465"+df.format(date));
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", id)
                .add("sport_time", time)
                .add("sport_speed", String.valueOf(speed))
                .add("sport_distance", String.valueOf(distance))
                .add("end_time", df.format(date))
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}