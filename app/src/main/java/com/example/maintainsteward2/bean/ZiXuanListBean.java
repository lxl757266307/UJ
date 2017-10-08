package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */

public class ZiXuanListBean  implements Serializable{


    /**
     * status : 1
     * data : {"meal_id":"2","set_meal_data":[{"service_id":"1","name":"挂式空调","expenses":"109","unit":"/台","cover":"http://os18w14e3.bkt.clouddn.com/59a372f809c7e.png"},{"service_id":"2","name":"柜式空调","expenses":"129","unit":"/台","cover":"http://os18w14e3.bkt.clouddn.com/59a373058b7b0.png"},{"service_id":"3","name":"单开门冰箱","expenses":"99","unit":"/台","cover":"http://os18w14e3.bkt.clouddn.com/596349e9c0fec.jpg"},{"service_id":"32","name":"水晶灯(直径101-140cm)","expenses":"150","unit":"起","cover":"http://os18w14e3.bkt.clouddn.com/59634f0b66b9a.jpg"}]}
     */

    private String status;
    private DataBean data;

    public static ZiXuanListBean objectFromData(String str) {

        return new Gson().fromJson(str, ZiXuanListBean.class);
    }

    public static ZiXuanListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ZiXuanListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ZiXuanListBean> arrayZiXuanListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ZiXuanListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ZiXuanListBean> arrayZiXuanListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ZiXuanListBean>>() {
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  implements Serializable{
        /**
         * meal_id : 2
         * set_meal_data : [{"service_id":"1","name":"挂式空调","expenses":"109","unit":"/台","cover":"http://os18w14e3.bkt.clouddn.com/59a372f809c7e.png"},{"service_id":"2","name":"柜式空调","expenses":"129","unit":"/台","cover":"http://os18w14e3.bkt.clouddn.com/59a373058b7b0.png"},{"service_id":"3","name":"单开门冰箱","expenses":"99","unit":"/台","cover":"http://os18w14e3.bkt.clouddn.com/596349e9c0fec.jpg"},{"service_id":"32","name":"水晶灯(直径101-140cm)","expenses":"150","unit":"起","cover":"http://os18w14e3.bkt.clouddn.com/59634f0b66b9a.jpg"}]
         */

        private String meal_id;
        private List<SetMealDataBean> set_meal_data;

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

        public String getMeal_id() {
            return meal_id;
        }

        public void setMeal_id(String meal_id) {
            this.meal_id = meal_id;
        }

        public List<SetMealDataBean> getSet_meal_data() {
            return set_meal_data;
        }

        public void setSet_meal_data(List<SetMealDataBean> set_meal_data) {
            this.set_meal_data = set_meal_data;
        }

        public static class SetMealDataBean  implements Serializable {
            /**
             * service_id : 1
             * name : 挂式空调
             * expenses : 109
             * unit : /台
             * cover : http://os18w14e3.bkt.clouddn.com/59a372f809c7e.png
             */

            private String service_id;
            private String name;
            private String expenses;
            private String unit;
            private String cover;
            @Expose
            private int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public static SetMealDataBean objectFromData(String str) {

                return new Gson().fromJson(str, SetMealDataBean.class);
            }

            public static SetMealDataBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), SetMealDataBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<SetMealDataBean> arraySetMealDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SetMealDataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<SetMealDataBean> arraySetMealDataBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<SetMealDataBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getExpenses() {
                return expenses;
            }

            public void setExpenses(String expenses) {
                this.expenses = expenses;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }
    }
}
