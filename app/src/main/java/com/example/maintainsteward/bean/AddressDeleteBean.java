package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */

public class AddressDeleteBean {

    @Override
    public String toString() {
        return "AddressDeleteBean{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    /**
     * status : 1
     * data : 删除成功
     */


    private String status;
    private String data;

    public static AddressDeleteBean objectFromData(String str) {

        return new Gson().fromJson(str, AddressDeleteBean.class);
    }

    public static AddressDeleteBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AddressDeleteBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<AddressDeleteBean> arrayAddressDeleteBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AddressDeleteBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<AddressDeleteBean> arrayAddressDeleteBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<AddressDeleteBean>>() {
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
