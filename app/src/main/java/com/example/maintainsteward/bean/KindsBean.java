package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class KindsBean {

    @Override
    public String toString() {
        return "KindsBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : [{"id":"67","name":"空调维修","logourl":"http://os18w14e3.bkt.clouddn.com/597afc310b951.png","third":[]},{"id":"68","name":"冰箱维修","logourl":"http://os18w14e3.bkt.clouddn.com/597afc0f2c3bf.png","third":[{"id":"112","name":"保洁"}]},{"id":"2","name":"空调清洗","logourl":"http://os18w14e3.bkt.clouddn.com/59755eff6a827.jpg","third":[{"id":"1","name":"挂式空调"},{"id":"2","name":"柜式空调"}]},{"id":"3","name":"冰箱清洗","logourl":"http://os18w14e3.bkt.clouddn.com/5975b88816f2b.jpg","third":[{"id":"3","name":"单开门冰箱"},{"id":"13","name":"三开门冰箱"},{"id":"12","name":"双开门冰箱"},{"id":"108","name":"对开门冰箱"}]}]
     */

    private String status;
    private List<DataBean> data;

    public static KindsBean objectFromData(String str) {

        return new Gson().fromJson(str, KindsBean.class);
    }

    public static KindsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), KindsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<KindsBean> arrayKindsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<KindsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<KindsBean> arrayKindsBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<KindsBean>>() {
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
                    ", logourl='" + logourl + '\'' +
                    ", third=" + third +
                    '}';
        }

        /**
         * id : 67
         * name : 空调维修
         * logourl : http://os18w14e3.bkt.clouddn.com/597afc310b951.png
         * third : []
         */

        private String id;
        private String name;
        private String logourl;
        private List<?> third;

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

        public String getLogourl() {
            return logourl;
        }

        public void setLogourl(String logourl) {
            this.logourl = logourl;
        }

        public List<?> getThird() {
            return third;
        }

        public void setThird(List<?> third) {
            this.third = third;
        }
    }
}
