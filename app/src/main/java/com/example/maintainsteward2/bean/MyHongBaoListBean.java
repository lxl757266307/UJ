package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */

public class MyHongBaoListBean implements Serializable {

    /**
     * status : 1
     * data : [{"id":"2","user_id":"372","order_no":"20171009164215178","money":"5","create_time":"1508812215","end_time":"1509417000","status":"0","create_time_format":"2017-10-24","end_time_format":"2017-10-31"},{"id":"3","user_id":"372","order_no":"20171009164215178","money":"11","create_time":"1508812215","end_time":"1509417000","status":"0","create_time_format":"2017-10-24","end_time_format":"2017-10-31"}]
     */

    private String status;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "MyHongBaoListBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public static MyHongBaoListBean objectFromData(String str) {

        return new Gson().fromJson(str, MyHongBaoListBean.class);
    }

    public static MyHongBaoListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), MyHongBaoListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<MyHongBaoListBean> arrayMyHongBaoListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MyHongBaoListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<MyHongBaoListBean> arrayMyHongBaoListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<MyHongBaoListBean>>() {
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

    public static class DataBean  implements Serializable{
        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", order_no='" + order_no + '\'' +
                    ", money='" + money + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", end_time='" + end_time + '\'' +
                    ", status='" + status + '\'' +
                    ", create_time_format='" + create_time_format + '\'' +
                    ", end_time_format='" + end_time_format + '\'' +
                    '}';
        }

        /**
         * id : 2
         * user_id : 372
         * order_no : 20171009164215178
         * money : 5
         * create_time : 1508812215
         * end_time : 1509417000
         * status : 0
         * create_time_format : 2017-10-24
         * end_time_format : 2017-10-31
         */


        private String id;
        private String user_id;
        private String order_no;
        private String money;
        private String create_time;
        private String end_time;
        private String status;
        private String create_time_format;
        private String end_time_format;

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

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_time_format() {
            return create_time_format;
        }

        public void setCreate_time_format(String create_time_format) {
            this.create_time_format = create_time_format;
        }

        public String getEnd_time_format() {
            return end_time_format;
        }

        public void setEnd_time_format(String end_time_format) {
            this.end_time_format = end_time_format;
        }
    }
}
