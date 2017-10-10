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

public class MyPingJiaListBean {

    /**
     * status : 1
     * data : [{"worker_id":"11","worker_name":"ddd","icon":"http://os18w14e3.bkt.clouddn.com/20170808122454692.jpg","phone_number":"18149460916","star_level":"2","add_time":"2017-10-10 08:44:48","content":"默默","smeta":["http://os18w14e3.bkt.clouddn.com/20171010084448647.jpg"],"evaluation":""}]
     */

    private String status;
    private List<DataBean> data;

    public static MyPingJiaListBean objectFromData(String str) {

        return new Gson().fromJson(str, MyPingJiaListBean.class);
    }

    public static MyPingJiaListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), MyPingJiaListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<MyPingJiaListBean> arrayMyPingJiaListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MyPingJiaListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<MyPingJiaListBean> arrayMyPingJiaListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<MyPingJiaListBean>>() {
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
         * worker_id : 11
         * worker_name : ddd
         * icon : http://os18w14e3.bkt.clouddn.com/20170808122454692.jpg
         * phone_number : 18149460916
         * star_level : 2
         * add_time : 2017-10-10 08:44:48
         * content : 默默
         * smeta : ["http://os18w14e3.bkt.clouddn.com/20171010084448647.jpg"]
         * evaluation :
         */

        private String worker_id;
        private String worker_name;
        private String icon;
        private String phone_number;
        private String star_level;
        private String add_time;
        private String content;
        private String evaluation;
        private List<String> smeta;

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

        public String getWorker_id() {
            return worker_id;
        }

        public void setWorker_id(String worker_id) {
            this.worker_id = worker_id;
        }

        public String getWorker_name() {
            return worker_name;
        }

        public void setWorker_name(String worker_name) {
            this.worker_name = worker_name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getStar_level() {
            return star_level;
        }

        public void setStar_level(String star_level) {
            this.star_level = star_level;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(String evaluation) {
            this.evaluation = evaluation;
        }

        public List<String> getSmeta() {
            return smeta;
        }

        public void setSmeta(List<String> smeta) {
            this.smeta = smeta;
        }
    }
}
