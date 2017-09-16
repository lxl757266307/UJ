package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21.
 */

public class QiNiuBean {


    /**
     * token : JhiflUyDaBJgrN3vTcGGrhuXBDWB6VUb8cg3B-Mq:XvoJenWAZ0T7H5MQ4QX3_h6G7Og=:eyJzY29wZSI6InVqaWFuZyIsImRlYWRsaW5lIjoxNTAzMjgzMTQxLCJ1cEhvc3RzIjpbImh0dHA6XC9cL3VwLnFpbml1LmNvbSIsImh0dHA6XC9cL3VwbG9hZC5xaW5pdS5jb20iLCItSCB1cC5xaW5pdS5jb20gaHR0cDpcL1wvMTgzLjEzMS43LjE4Il19
     */

    private String token;

    @Override
    public String toString() {
        return "QiNiuBean{" +
                "token='" + token + '\'' +
                '}';
    }

    public static QiNiuBean objectFromData(String str) {

        return new Gson().fromJson(str, QiNiuBean.class);
    }

    public static QiNiuBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), QiNiuBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<QiNiuBean> arrayQiNiuBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<QiNiuBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<QiNiuBean> arrayQiNiuBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<QiNiuBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
