package com.example.administrator.clockpassometer.utils;


import com.example.administrator.clockpassometer.bean.Commodity;
import com.example.administrator.clockpassometer.bean.CommodityPd;
import com.example.administrator.clockpassometer.bean.Exchang;
import com.example.administrator.clockpassometer.bean.Game;
import com.example.administrator.clockpassometer.bean.GameMessage;
import com.example.administrator.clockpassometer.bean.Inquire;
import com.example.administrator.clockpassometer.bean.Integral;
import com.example.administrator.clockpassometer.bean.LoginMessage;
import com.example.administrator.clockpassometer.bean.News;
import com.example.administrator.clockpassometer.bean.Ranking;
import com.example.administrator.clockpassometer.bean.Record;
import com.example.administrator.clockpassometer.bean.Score;
import com.example.administrator.clockpassometer.bean.Site;
import com.example.administrator.clockpassometer.bean.Task;
import com.example.administrator.clockpassometer.bean.User;
import com.example.administrator.clockpassometer.bean.Userinfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
/*
* Json解析
* */
public class JsonParse {
    public static LoginMessage loginMessage(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String msg = jsonObject.getString("msg");
        String data = jsonObject.getString("data");
        JSONObject jsonObject1 = new JSONObject(data);
        String id = jsonObject1.getString("id");
        return new LoginMessage(id,msg);
    }
    public static List<News> communityNews(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<News>>(){}.getType());
    }
    public static List<Commodity> communityCommodity(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Commodity>>(){}.getType());
    }
    public static List<Game> gameList(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Game>>(){}.getType());
    }
    public static GameMessage gemeMessage(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,GameMessage.class);
    }
    public static List<Score> gameScore(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,new TypeToken<List<Score>>(){}.getType());
    }
    public static List<Ranking> ranking(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Ranking>>(){}.getType());
    }
    public static Integral integral(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,Integral.class);
    }
    public static CommodityPd CommodityifOk(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,CommodityPd.class);
    }
    public static Task Task(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,Task.class);
    }
    public static List<Record> Record(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,new TypeToken<List<Record>>(){}.getType());
    }
    public static User User(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,User.class);
    }
    public static Userinfo Userinfo(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,Userinfo.class);
    }
    public static List<Exchang> Exchang(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Exchang>>(){}.getType());
    }
    public static List<Site> Site(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,new TypeToken<List<Site>>(){}.getType());
    }
    public static News News(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,News.class);
    }
    public static List<Inquire> inquire(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<Inquire>>(){}.getType());
    }

}
