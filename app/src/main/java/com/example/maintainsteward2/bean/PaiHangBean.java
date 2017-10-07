package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */

public class PaiHangBean {


    /**
     * status : 1
     * data : {"data":[{"m_id":"328","user_nicename":"","counts":"1","avatar":"","rank":"1"}],"my_rank":"0"}
     */

    private String status;
    private DataBeanX data;

    public static PaiHangBean objectFromData(String str) {

        return new Gson().fromJson(str, PaiHangBean.class);
    }

    public static PaiHangBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PaiHangBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<PaiHangBean> arrayPaiHangBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<PaiHangBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<PaiHangBean> arrayPaiHangBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<PaiHangBean>>() {
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
         * data : [{"m_id":"328","user_nicename":"","counts":"1","avatar":"","rank":"1"}]
         * my_rank : 0
         */

        private String my_rank;
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

        public String getMy_rank() {
            return my_rank;
        }

        public void setMy_rank(String my_rank) {
            this.my_rank = my_rank;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * m_id : 328
             * user_nicename :
             * counts : 1
             * avatar :
             * rank : 1
             */

            private String m_id;
            private String user_nicename;
            private String counts;
            private String avatar;
            private String rank;
            @Expose
            private int type;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public String getM_id() {
                return m_id;
            }

            public void setM_id(String m_id) {
                this.m_id = m_id;
            }

            public String getUser_nicename() {
                return user_nicename;
            }

            public void setUser_nicename(String user_nicename) {
                this.user_nicename = user_nicename;
            }

            public String getCounts() {
                return counts;
            }

            public void setCounts(String counts) {
                this.counts = counts;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }
        }
    }
}
