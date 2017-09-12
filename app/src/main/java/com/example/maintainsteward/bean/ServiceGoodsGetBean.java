package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ServiceGoodsGetBean {


    @Override
    public String toString() {
        return "ServiceGoodsGetBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : [{"id":"18","name":"LED圆贴片/白光/10W","price":"29.00","deal_desc":"","smeta":"http://os18w14e3.bkt.clouddn.com/595f4d800e15e.png"},{"id":"19","name":"LED圆贴片/白光/15W","price":"38.00","deal_desc":"","smeta":"http://os18w14e3.bkt.clouddn.com/595f4da481586.png"},{"id":"20","name":"LED圆贴片/白光/20W","price":"42.00","deal_desc":"","smeta":"http://os18w14e3.bkt.clouddn.com/595f4dd7299ae.png"},{"id":"21","name":"LED长条贴片/白光/15W","price":"32.50","deal_desc":"","smeta":"http://os18w14e3.bkt.clouddn.com/595f4e133f80d.png"},{"id":"22","name":"LED长条贴片/白光/18W","price":"37.00","deal_desc":"","smeta":"http://os18w14e3.bkt.clouddn.com/595f4e2d5f0b0.png"},{"id":"23","name":"LED长条贴片/白光/27W","price":"43.50","deal_desc":"","smeta":"http://os18w14e3.bkt.clouddn.com/595f4e5d78477.png"},{"id":"24","name":"分段开关（3端）","price":"30.00","deal_desc":"","smeta":"http://os18w14e3.bkt.clouddn.com/595f514d3e705.png"}]
     */

    private String status;
    private List<DataBean> data;

    public static ServiceGoodsGetBean objectFromData(String str) {

        return new Gson().fromJson(str, ServiceGoodsGetBean.class);
    }

    public static ServiceGoodsGetBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ServiceGoodsGetBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ServiceGoodsGetBean> arrayServiceGoodsGetBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ServiceGoodsGetBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ServiceGoodsGetBean> arrayServiceGoodsGetBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ServiceGoodsGetBean>>() {
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", price='" + price + '\'' +
                    ", deal_desc='" + deal_desc + '\'' +
                    ", smeta='" + smeta + '\'' +
                    '}';
        }

        /**
         * id : 18
         * name : LED圆贴片/白光/10W
         * price : 29.00
         * deal_desc :
         * smeta : http://os18w14e3.bkt.clouddn.com/595f4d800e15e.png
         */

        private String id;
        private String name;
        private String price;
        private String deal_desc;
        private String smeta;
        @Expose
        private int number;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDeal_desc() {
            return deal_desc;
        }

        public void setDeal_desc(String deal_desc) {
            this.deal_desc = deal_desc;
        }

        public String getSmeta() {
            return smeta;
        }

        public void setSmeta(String smeta) {
            this.smeta = smeta;
        }
    }
}
