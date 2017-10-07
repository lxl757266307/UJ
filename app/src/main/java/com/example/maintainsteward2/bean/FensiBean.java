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

public class FensiBean {


    @Override
    public String toString() {
        return "FensiBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"count":"3","next_child":"0","data":[{"id":"167","user_nicename":"18729005099","parentid":"368","topid":"0","count":"0","total_money":"0","child_count":"0"},{"id":"173","user_nicename":"刘小小","parentid":"368","topid":"0","count":"0","total_money":"0","child_count":"1"},{"id":"174","user_nicename":"15353684517","parentid":"368","topid":"0","count":"0","total_money":"0","child_count":"0"}]}
     */

    private String status;
    private DataBeanX data;

    public FensiBean(String status) {
        this.status = status;
    }

    public static FensiBean objectFromData(String str) {

        return new Gson().fromJson(str, FensiBean.class);
    }

    public static FensiBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), FensiBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<FensiBean> arrayFensiBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<FensiBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<FensiBean> arrayFensiBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<FensiBean>>() {
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
        @Override
        public String toString() {
            return "DataBeanX{" +
                    "count='" + count + '\'' +
                    ", next_child='" + next_child + '\'' +
                    ", data=" + data +
                    '}';
        }

        /**
         * count : 3
         * next_child : 0
         * data : [{"id":"167","user_nicename":"18729005099","parentid":"368","topid":"0","count":"0","total_money":"0","child_count":"0"},{"id":"173","user_nicename":"刘小小","parentid":"368","topid":"0","count":"0","total_money":"0","child_count":"1"},{"id":"174","user_nicename":"15353684517","parentid":"368","topid":"0","count":"0","total_money":"0","child_count":"0"}]
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

            @Override
            public String toString() {
                return "DataBean{" +
                        "id='" + id + '\'' +
                        ", user_nicename='" + user_nicename + '\'' +
                        ", parentid='" + parentid + '\'' +
                        ", topid='" + topid + '\'' +
                        ", count='" + count + '\'' +
                        ", total_money='" + total_money + '\'' +
                        ", child_count='" + child_count + '\'' +
                        '}';
            }

            /**
             * id : 167
             * user_nicename : 18729005099
             * parentid : 368
             * topid : 0
             * count : 0
             * total_money : 0
             * child_count : 0
             */

            private String id;
            private String user_nicename;
            private String parentid;
            private String topid;
            private String count;
            private String total_money;
            private String child_count;

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

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getTotal_money() {
                return total_money;
            }

            public void setTotal_money(String total_money) {
                this.total_money = total_money;
            }

            public String getChild_count() {
                return child_count;
            }

            public void setChild_count(String child_count) {
                this.child_count = child_count;
            }
        }
    }
}
