package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ServiceBean {


    /**
     * item_id : 项目id
     * item_name : 项目名
     * item_num : 项目个数
     * item_price : 单价
     * item_unit : 单位
     * item_cover : 缩略图
     */

    private String item_id;
    private String item_name;
    private String item_num;
    private String item_price;
    private String item_unit;
    private String item_cover;

    public ServiceBean() {
    }

    public ServiceBean(String item_id, String item_name, String item_num, String item_price, String item_unit, String item_cover) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_num = item_num;
        this.item_price = item_price;
        this.item_unit = item_unit;
        this.item_cover = item_cover;
    }

    public static ServiceBean objectFromData(String str) {

        return new Gson().fromJson(str, ServiceBean.class);
    }

    public static ServiceBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ServiceBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ServiceBean> arrayServiceBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ServiceBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ServiceBean> arrayServiceBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ServiceBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String item_num) {
        this.item_num = item_num;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_unit() {
        return item_unit;
    }

    public void setItem_unit(String item_unit) {
        this.item_unit = item_unit;
    }

    public String getItem_cover() {
        return item_cover;
    }

    public void setItem_cover(String item_cover) {
        this.item_cover = item_cover;
    }
}
