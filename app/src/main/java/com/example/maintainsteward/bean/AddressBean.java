package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class AddressBean {
    @Override
    public String toString() {
        return "AddressBean{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    /**
     * status : -9004
     * data : 用户不存在
     */


    private String status;
    private String data;

    public static AddressBean objectFromData(String str) {

        return new Gson().fromJson(str, AddressBean.class);
    }

    public static AddressBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AddressBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<AddressBean> arrayAddressBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AddressBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<AddressBean> arrayAddressBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<AddressBean>>() {
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
    // http://wxtest.cnncsh.com/app_user/index/index/op/
    // AddAddress?
    // address=侧时代走
    // &city=3
    // &community=0
    // &district=35
    // &timestamp=1504083125000
    // &user_id=442
    // &user_name=测试
    // &user_phone=13545454545
    // &sign=3D647F1372B16E47849BE3A8F0C439DC
    // &key=idf5nsi5t0qbemwo12hztbftm53tbv6pht


}
