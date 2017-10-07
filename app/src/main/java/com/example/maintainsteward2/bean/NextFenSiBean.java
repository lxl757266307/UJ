package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */

public class NextFenSiBean {

    /**
     * status : 1
     * data : {"count":"1","next_child":"","data":[{"id":"349","user_nicename":"尼古拉斯","parentid":"173","topid":"0","create_time":"2017-08-30 10:07:55"}]}
     */

    private String status;
    private DataBeanX data;

    public static NextFenSiBean objectFromData(String str) {

        return new Gson().fromJson(str, NextFenSiBean.class);
    }

    public static NextFenSiBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), NextFenSiBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<NextFenSiBean> arrayNextFenSiBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<NextFenSiBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<NextFenSiBean> arrayNextFenSiBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<NextFenSiBean>>() {
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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * count : 1
         * next_child :
         * data : [{"id":"349","user_nicename":"尼古拉斯","parentid":"173","topid":"0","create_time":"2017-08-30 10:07:55"}]
         */

        private String count;
        private String next_child;
        private List<DataBean> data;

        public static DataBeanX objectFromData(String str) {

            return new Gson().fromJson(str, DataBeanX.class);
        }

        public static DataBeanX objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBeanX.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBeanX> arrayDataBeanXFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBeanX>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DataBeanX> arrayDataBeanXFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBeanX>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getNext_child() {
            return next_child;
        }

        public void setNext_child(String next_child) {
            this.next_child = next_child;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 349
             * user_nicename : 尼古拉斯
             * parentid : 173
             * topid : 0
             * create_time : 2017-08-30 10:07:55
             */

            private String id;
            private String user_nicename;
            private String parentid;
            private String topid;
            private String create_time;

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

            public String getUser_nicename() {
                return user_nicename;
            }

            public void setUser_nicename(String user_nicename) {
                this.user_nicename = user_nicename;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getTopid() {
                return topid;
            }

            public void setTopid(String topid) {
                this.topid = topid;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
