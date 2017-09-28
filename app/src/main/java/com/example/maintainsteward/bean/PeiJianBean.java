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

public class PeiJianBean {
    @Override
    public String toString() {
        return "PeiJianBean{" +
                "item_id='" + item_id + '\'' +
                ", item_cover='" + item_cover + '\'' +
                ", item_name='" + item_name + '\'' +
                ", item_price='" + item_price + '\'' +
                ", item_num='" + item_num + '\'' +
                '}';
    }

    public PeiJianBean() {
    }

    public PeiJianBean(String item_id, String item_cover, String item_name, String item_price, String item_num) {
        this.item_id = item_id;
        this.item_cover = item_cover;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_num = item_num;
    }

    /**
     * item_id : 配件id
     * item_cover : 缩略图
     * item_name : 配件名
     * item_price : 单价
     * item_num : 数量
     */

    private String item_id;
    private String item_cover;
    private String item_name;
    private String item_price;
    private String item_num;

    public static PeiJianBean objectFromData(String str) {

        return new Gson().fromJson(str, PeiJianBean.class);
    }

    public static PeiJianBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PeiJianBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<PeiJianBean> arrayPeiJianBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<PeiJianBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<PeiJianBean> arrayPeiJianBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<PeiJianBean>>() {
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

    public String getItem_cover() {
        return item_cover;
    }

    public void setItem_cover(String item_cover) {
        this.item_cover = item_cover;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String item_num) {
        this.item_num = item_num;
    }
}
