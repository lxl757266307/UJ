package com.example.maintainsteward2.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class YongJinJiLuBean {


    @Override
    public String toString() {
        return "YongJinJiLuBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"commission":"0","data":[{"id":"17","user_id":"368","follow_id":"0","type":"8","length":"0","money":"5","remark":"","create_time":"1974-10-08 19:08:56","order_id":"0","status":"0","title":"提现"},{"id":"16","user_id":"368","follow_id":"0","type":"8","length":"0","money":"5","remark":"","create_time":"1974-10-08 19:08:54","order_id":"0","status":"0","title":"提现"}]}
     */


    private String status;
    private DataBeanX data;

    public static YongJinJiLuBean objectFromData(String str) {

        return new Gson().fromJson(str, YongJinJiLuBean.class);
    }

    public static YongJinJiLuBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), YongJinJiLuBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<YongJinJiLuBean> arrayYongJinJiLuBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<YongJinJiLuBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<YongJinJiLuBean> arrayYongJinJiLuBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<YongJinJiLuBean>>() {
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
                    "commission='" + commission + '\'' +
                    ", data=" + data +
                    '}';
        }

        /**
         * commission : 0
         * data : [{"id":"17","user_id":"368","follow_id":"0","type":"8","length":"0","money":"5","remark":"","create_time":"1974-10-08 19:08:56","order_id":"0","status":"0","title":"提现"},{"id":"16","user_id":"368","follow_id":"0","type":"8","length":"0","money":"5","remark":"","create_time":"1974-10-08 19:08:54","order_id":"0","status":"0","title":"提现"}]
         */

        private String commission;
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

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
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
                        ", user_id='" + user_id + '\'' +
                        ", follow_id='" + follow_id + '\'' +
                        ", type='" + type + '\'' +
                        ", length='" + length + '\'' +
                        ", money='" + money + '\'' +
                        ", remark='" + remark + '\'' +
                        ", create_time='" + create_time + '\'' +
                        ", order_id='" + order_id + '\'' +
                        ", status='" + status + '\'' +
                        ", title='" + title + '\'' +
                        '}';
            }

            /**
             * id : 17
             * user_id : 368
             * follow_id : 0
             * type : 8
             * length : 0
             * money : 5
             * remark :
             * create_time : 1974-10-08 19:08:56
             * order_id : 0
             * status : 0
             * title : 提现
             */

            private String id;
            private String user_id;
            private String follow_id;
            private String type;
            private String length;
            private String money;
            private String remark;
            private String create_time;
            private String order_id;
            private String status;
            private String title;

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

            public String getFollow_id() {
                return follow_id;
            }

            public void setFollow_id(String follow_id) {
                this.follow_id = follow_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
