package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class SecondKindsContent {


    /**
     * status : 1
     * data : {"content":"暂不提供家用和商用中央空调清洗服务（加入会员，选购365套餐，价格更优惠）。清洗流程：蒸汽机清洗，高温消毒，臭氧杀菌。服务质保：清洗类无质保。","url":"http://wxtest.cnncsh.com/app_user/appweb/servercontent/id/2.html"}
     */

    private String status;
    private DataBean data;

    @Override
    public String toString() {
        return "SecondKindsContent{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public static SecondKindsContent objectFromData(String str) {

        return new Gson().fromJson(str, SecondKindsContent.class);
    }

    public static SecondKindsContent objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SecondKindsContent.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SecondKindsContent> arraySecondKindsContentFromData(String str) {

        Type listType = new TypeToken<ArrayList<SecondKindsContent>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SecondKindsContent> arraySecondKindsContentFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SecondKindsContent>>() {
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
                    "content='" + content + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        /**
         * content : 暂不提供家用和商用中央空调清洗服务（加入会员，选购365套餐，价格更优惠）。清洗流程：蒸汽机清洗，高温消毒，臭氧杀菌。服务质保：清洗类无质保。
         * url : http://wxtest.cnncsh.com/app_user/appweb/servercontent/id/2.html
         */

        private String content;
        private String url;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
