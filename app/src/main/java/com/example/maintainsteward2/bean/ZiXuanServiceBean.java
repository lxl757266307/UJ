package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */

public class ZiXuanServiceBean {


    /**
     * name : 服务项名
     * num : 购买数量
     * price : 单价
     * unit : 单位
     * cat_id : 服务项id
     */

    private String name;
    private String num;
    private String price;
    private String unit;
    private String cat_id;

    public ZiXuanServiceBean(String name, String num, String price, String unit, String cat_id) {
        this.name = name;
        this.num = num;
        this.price = price;
        this.unit = unit;
        this.cat_id = cat_id;
    }

    public static ZiXuanServiceBean objectFromData(String str) {

        return new Gson().fromJson(str, ZiXuanServiceBean.class);
    }

    public static ZiXuanServiceBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ZiXuanServiceBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ZiXuanServiceBean> arrayZiXuanServiceBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ZiXuanServiceBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ZiXuanServiceBean> arrayZiXuanServiceBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ZiXuanServiceBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }
}
