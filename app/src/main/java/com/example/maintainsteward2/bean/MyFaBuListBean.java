package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class MyFaBuListBean {

    /**
     * status : 1
     * data : [{"id":"1","user_id":"368","service_des":"默默","service_img":[],"add_time":"2017-09-28 14:00:05","is_finish":"0"},{"id":"2","user_id":"368","service_des":"默默","service_img":[],"add_time":"2017-09-28 14:00:05","is_finish":"0"},{"id":"3","user_id":"368","service_des":"默默","service_img":[],"add_time":"2017-09-28 14:00:05","is_finish":"0"},{"id":"4","user_id":"368","service_des":"默默","service_img":[],"add_time":"2017-09-28 14:00:05","is_finish":"0"},{"id":"5","user_id":"368","service_des":"默默","service_img":[],"add_time":"2017-09-28 14:00:05","is_finish":"0"},{"id":"6","user_id":"368","service_des":"默默","service_img":[],"add_time":"2017-09-28 14:00:06","is_finish":"0"},{"id":"7","user_id":"368","service_des":"噢噢噢哦哦","service_img":[],"add_time":"2017-09-28 14:04:04","is_finish":"0"},{"id":"8","user_id":"368","service_des":"哦哦哦","service_img":[],"add_time":"2017-09-28 14:09:15","is_finish":"0"},{"id":"9","user_id":"368","service_des":"你看看","service_img":[],"add_time":"2017-09-28 14:10:43","is_finish":"0"},{"id":"10","user_id":"368","service_des":"黑化","service_img":[],"add_time":"2017-09-28 14:11:36","is_finish":"0"}]
     */

    private String status;
    private List<DataBean> data;

    public static MyFaBuListBean objectFromData(String str) {

        return new Gson().fromJson(str, MyFaBuListBean.class);
    }

    public static MyFaBuListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), MyFaBuListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<MyFaBuListBean> arrayMyFaBuListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MyFaBuListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<MyFaBuListBean> arrayMyFaBuListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<MyFaBuListBean>>() {
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * user_id : 368
         * service_des : 默默
         * service_img : []
         * add_time : 2017-09-28 14:00:05
         * is_finish : 0
         */

        private String id;
        private String user_id;
        private String service_des;
        private String add_time;
        private String is_finish;
        private List<String> service_img;

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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getService_des() {
            return service_des;
        }

        public void setService_des(String service_des) {
            this.service_des = service_des;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIs_finish() {
            return is_finish;
        }

        public void setIs_finish(String is_finish) {
            this.is_finish = is_finish;
        }

        public List<String> getService_img() {
            return service_img;
        }

        public void setService_img(List<String> service_img) {
            this.service_img = service_img;
        }
    }
}
