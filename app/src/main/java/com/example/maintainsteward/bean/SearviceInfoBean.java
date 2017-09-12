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
 * Created by Administrator on 2017/9/11.
 */

public class SearviceInfoBean {


    /**
     * status : 1
     * data : [{"id":"1","sort":"1","cat_id":"2","name":"挂式空调","cover":"http://os18w14e3.bkt.clouddn.com/596348f50ab1d.jpg","expenses":"99","unit":"/台","expenses_item":"","desc":"此价格仅针对于挂式空调室内机清洗服务，不含室外机。暂不提供家用和商用中央空调清洗服务（选购365套餐，价格更优惠）。","add_time":"2017-06-05 17:00:00","service_lable":[]},{"id":"2","sort":"2","cat_id":"2","name":"柜式空调","cover":"http://os18w14e3.bkt.clouddn.com/596348fe1268a.jpg","expenses":"119","unit":"/台","expenses_item":"","desc":"此价格仅针对于柜式空调室内机清洗服务，不含室外机。暂不提供家用和商用中央空调清洗服务（选购365套餐，价格更优惠）。","add_time":"2017-06-05 18:00:00","service_lable":[]}]
     */

    private String status;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "SearviceInfoBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public static SearviceInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, SearviceInfoBean.class);
    }

    public static SearviceInfoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SearviceInfoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SearviceInfoBean> arraySearviceInfoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SearviceInfoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SearviceInfoBean> arraySearviceInfoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SearviceInfoBean>>() {
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
                    ", sort='" + sort + '\'' +
                    ", cat_id='" + cat_id + '\'' +
                    ", name='" + name + '\'' +
                    ", cover='" + cover + '\'' +
                    ", expenses='" + expenses + '\'' +
                    ", unit='" + unit + '\'' +
                    ", expenses_item='" + expenses_item + '\'' +
                    ", desc='" + desc + '\'' +
                    ", add_time='" + add_time + '\'' +
                    '}';
        }

        /**
         * id : 1
         * sort : 1
         * cat_id : 2
         * name : 挂式空调
         * cover : http://os18w14e3.bkt.clouddn.com/596348f50ab1d.jpg
         * expenses : 99
         * unit : /台
         * expenses_item :
         * desc : 此价格仅针对于挂式空调室内机清洗服务，不含室外机。暂不提供家用和商用中央空调清洗服务（选购365套餐，价格更优惠）。
         * add_time : 2017-06-05 17:00:00
         * service_lable : []
         */

        private String id;
        private String sort;
        private String cat_id;
        private String name;
        private String cover;
        private String expenses;
        private String unit;
        private String expenses_item;
        private String desc;
        private String add_time;

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

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public String getExpenses_item() {
            return expenses_item;
        }

        public void setExpenses_item(String expenses_item) {
            this.expenses_item = expenses_item;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }


    }
}
