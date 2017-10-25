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
 * Created by Administrator on 2017/10/25.
 */

public class HongBaoBean implements Serializable {


    /**
     * status : 1
     * data : {"luck_money_ok":[{"id":"3","user_id":"372","order_no":"20171009164215178","money":"11","create_time":"1508812215","end_time":"1509417000","status":"0","used_time":"","create_time_format":"2017-10-24","end_time_format":"2017-10-31"}],"luck_money_no":[{"id":"4","user_id":"372","order_no":"0091642151","money":"50","create_time":"1508812215","end_time":"1509417000","status":"0","used_time":"","create_time_format":"2017-10-24","end_time_format":"2017-10-31"}]}
     */

    private String status;
    private DataBean data;

    public static HongBaoBean objectFromData(String str) {

        return new Gson().fromJson(str, HongBaoBean.class);
    }

    public static HongBaoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), HongBaoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<HongBaoBean> arrayHongBaoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<HongBaoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<HongBaoBean> arrayHongBaoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<HongBaoBean>>() {
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

    public static class DataBean implements Serializable {
        private List<LuckMoneyOkBean> luck_money_ok;
        private List<LuckMoneyNoBean> luck_money_no;

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

        public List<LuckMoneyOkBean> getLuck_money_ok() {
            return luck_money_ok;
        }

        public void setLuck_money_ok(List<LuckMoneyOkBean> luck_money_ok) {
            this.luck_money_ok = luck_money_ok;
        }

        public List<LuckMoneyNoBean> getLuck_money_no() {
            return luck_money_no;
        }

        public void setLuck_money_no(List<LuckMoneyNoBean> luck_money_no) {
            this.luck_money_no = luck_money_no;
        }

        public static class LuckMoneyOkBean implements Serializable {
            /**
             * id : 3
             * user_id : 372
             * order_no : 20171009164215178
             * money : 11
             * create_time : 1508812215
             * end_time : 1509417000
             * status : 0
             * used_time :
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
            private String used_time;
            private String create_time_format;
            private String end_time_format;

            public static LuckMoneyOkBean objectFromData(String str) {

                return new Gson().fromJson(str, LuckMoneyOkBean.class);
            }

            public static LuckMoneyOkBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), LuckMoneyOkBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<LuckMoneyOkBean> arrayLuckMoneyOkBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<LuckMoneyOkBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<LuckMoneyOkBean> arrayLuckMoneyOkBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<LuckMoneyOkBean>>() {
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

            public String getUsed_time() {
                return used_time;
            }

            public void setUsed_time(String used_time) {
                this.used_time = used_time;
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

        public static class LuckMoneyNoBean {
            /**
             * id : 4
             * user_id : 372
             * order_no : 0091642151
             * money : 50
             * create_time : 1508812215
             * end_time : 1509417000
             * status : 0
             * used_time :
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
            private String used_time;
            private String create_time_format;
            private String end_time_format;

            public static LuckMoneyNoBean objectFromData(String str) {

                return new Gson().fromJson(str, LuckMoneyNoBean.class);
            }

            public static LuckMoneyNoBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), LuckMoneyNoBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<LuckMoneyNoBean> arrayLuckMoneyNoBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<LuckMoneyNoBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<LuckMoneyNoBean> arrayLuckMoneyNoBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<LuckMoneyNoBean>>() {
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

            public String getUsed_time() {
                return used_time;
            }

            public void setUsed_time(String used_time) {
                this.used_time = used_time;
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
}
