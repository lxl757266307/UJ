package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

public class KaJuanCountBean {

    @Override
    public String toString() {
        return "KaJuanCountBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"totalcount1":"1","totalcount2":"0","totalcount3":"0"}
     */

    private String status;
    private DataBean data;

    public static KaJuanCountBean objectFromData(String str) {

        return new Gson().fromJson(str, KaJuanCountBean.class);
    }

    public static KaJuanCountBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), KaJuanCountBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<KaJuanCountBean> arrayKaJuanCountBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<KaJuanCountBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<KaJuanCountBean> arrayKaJuanCountBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<KaJuanCountBean>>() {
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

    public static class DataBean {

        @Override
        public String toString() {
            return "DataBean{" +
                    "totalcount1='" + totalcount1 + '\'' +
                    ", totalcount2='" + totalcount2 + '\'' +
                    ", totalcount3='" + totalcount3 + '\'' +
                    '}';
        }

        /**
         * totalcount1 : 1
         * totalcount2 : 0
         * totalcount3 : 0
         */

        private String totalcount1;
        private String totalcount2;
        private String totalcount3;

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

        public String getTotalcount1() {
            return totalcount1;
        }

        public void setTotalcount1(String totalcount1) {
            this.totalcount1 = totalcount1;
        }

        public String getTotalcount2() {
            return totalcount2;
        }

        public void setTotalcount2(String totalcount2) {
            this.totalcount2 = totalcount2;
        }

        public String getTotalcount3() {
            return totalcount3;
        }

        public void setTotalcount3(String totalcount3) {
            this.totalcount3 = totalcount3;
        }
    }
}
