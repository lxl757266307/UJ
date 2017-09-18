package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public class OrderSucessBean {


    @Override
    public String toString() {
        return "OrderSucessBean{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    /**
     * status : 1
     * data : 订单提交成功
     */

    private String status;
    private String data;

    public static OrderSucessBean objectFromData(String str) {

        return new Gson().fromJson(str, OrderSucessBean.class);
    }

    public static OrderSucessBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), OrderSucessBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<OrderSucessBean> arrayOrderSucessBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderSucessBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<OrderSucessBean> arrayOrderSucessBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<OrderSucessBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
