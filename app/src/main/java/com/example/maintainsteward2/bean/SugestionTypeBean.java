package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public class SugestionTypeBean {


    /**
     * state : 1
     * state_msg : ok
     * data : [{"id":"1","type_name":"功能异常","msg":"不愉快的使用功能，告诉我们将尽快解决"},{"id":"2","type_name":"体验问题","msg":"用的不爽快来告诉我们"},{"id":"3","type_name":"新功能建议","msg":"快把您想要的功能告诉我们~"},{"id":"4","type_name":"其他","msg":"可畅所欲言哦~~"}]
     */

    private int state;
    private String state_msg;
    private List<DataBean> data;

    public static SugestionTypeBean objectFromData(String str) {

        return new Gson().fromJson(str, SugestionTypeBean.class);
    }

    public static SugestionTypeBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SugestionTypeBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SugestionTypeBean> arraySugestionTypeBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SugestionTypeBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SugestionTypeBean> arraySugestionTypeBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SugestionTypeBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getState_msg() {
        return state_msg;
    }

    public void setState_msg(String state_msg) {
        this.state_msg = state_msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * type_name : 功能异常
         * msg : 不愉快的使用功能，告诉我们将尽快解决
         */

        private String id;
        private String type_name;
        private String msg;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DataBean> arrayDataBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
