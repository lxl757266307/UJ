package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class WXErrorInfo {

    /**
     * errcode : 40029
     * errmsg : invalid code
     */

    private int errcode;
    private String errmsg;

    public static WXErrorInfo objectFromData(String str) {

        return new Gson().fromJson(str, WXErrorInfo.class);
    }

    public static WXErrorInfo objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), WXErrorInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<WXErrorInfo> arrayWXErrorInfoFromData(String str) {

        Type listType = new TypeToken<ArrayList<WXErrorInfo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<WXErrorInfo> arrayWXErrorInfoFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<WXErrorInfo>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
