package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */

public class MySetMealBean implements Serializable {

    /**
     * status : 1
     * data : {"title":"365健康套餐","term":"2017年09月27日~2018年09月27日","set_meal":[{"id":"270","meal_log_id":"97","meal_id":"1","item_id":"8","cat_id":"2","limit_num":"1","number":"0","is_one_come":"0","not_use_num":"1","user_id":"368","service_id":"2","content_desc":"柜机空调1台","end_days":"365","name":"柜式空调","second_id":"2"},{"id":"271","meal_log_id":"97","meal_id":"1","item_id":"7","cat_id":"1","limit_num":"1","number":"0","is_one_come":"0","not_use_num":"1","user_id":"368","service_id":"1","content_desc":"挂机空调1-3台","end_days":"365","name":"挂式空调","second_id":"2"},{"id":"272","meal_log_id":"97","meal_id":"1","item_id":"9","cat_id":"16","limit_num":"1","number":"0","is_one_come":"0","not_use_num":"1","user_id":"368","service_id":"16","content_desc":"中式/欧式2选1","end_days":"365","name":"中式油烟机","second_id":"21"},{"id":"273","meal_log_id":"97","meal_id":"1","item_id":"17","cat_id":"3","limit_num":"1","number":"0","is_one_come":"0","not_use_num":"1","user_id":"368","service_id":"3","content_desc":"单开门冰箱1台","end_days":"365","name":"单开门冰箱","second_id":"3"},{"id":"274","meal_log_id":"97","meal_id":"1","item_id":"24","cat_id":"105","limit_num":"-1","number":"0","is_one_come":"0","not_use_num":"-1","user_id":"368","service_id":"105","content_desc":"整屋检测(不限次)","end_days":"365","name":"房屋检测","second_id":"64"}],"address":"西安市新城区西安市新城区哦哦哦","is_show":"1","is_commit":""}
     */

    private String status;
    private DataBean data;

    @Override
    public String toString() {
        return "MySetMealBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public static MySetMealBean objectFromData(String str) {

        return new Gson().fromJson(str, MySetMealBean.class);
    }

    public static MySetMealBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), MySetMealBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<MySetMealBean> arrayMySetMealBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MySetMealBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<MySetMealBean> arrayMySetMealBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<MySetMealBean>>() {
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "title='" + title + '\'' +
                    ", term='" + term + '\'' +
                    ", address='" + address + '\'' +
                    ", is_show='" + is_show + '\'' +
                    ", is_commit='" + is_commit + '\'' +
                    ", set_meal=" + set_meal +
                    '}';
        }

        /**
         * title : 365健康套餐
         * term : 2017年09月27日~2018年09月27日
         * set_meal : [{"id":"270","meal_log_id":"97","meal_id":"1","item_id":"8","cat_id":"2","limit_num":"1","number":"0","is_one_come":"0","not_use_num":"1","user_id":"368","service_id":"2","content_desc":"柜机空调1台","end_days":"365","name":"柜式空调","second_id":"2"},{"id":"271","meal_log_id":"97","meal_id":"1","item_id":"7","cat_id":"1","limit_num":"1","number":"0","is_one_come":"0","not_use_num":"1","user_id":"368","service_id":"1","content_desc":"挂机空调1-3台","end_days":"365","name":"挂式空调","second_id":"2"},{"id":"272","meal_log_id":"97","meal_id":"1","item_id":"9","cat_id":"16","limit_num":"1","number":"0","is_one_come":"0","not_use_num":"1","user_id":"368","service_id":"16","content_desc":"中式/欧式2选1","end_days":"365","name":"中式油烟机","second_id":"21"},{"id":"273","meal_log_id":"97","meal_id":"1","item_id":"17","cat_id":"3","limit_num":"1","number":"0","is_one_come":"0","not_use_num":"1","user_id":"368","service_id":"3","content_desc":"单开门冰箱1台","end_days":"365","name":"单开门冰箱","second_id":"3"},{"id":"274","meal_log_id":"97","meal_id":"1","item_id":"24","cat_id":"105","limit_num":"-1","number":"0","is_one_come":"0","not_use_num":"-1","user_id":"368","service_id":"105","content_desc":"整屋检测(不限次)","end_days":"365","name":"房屋检测","second_id":"64"}]
         * address : 西安市新城区西安市新城区哦哦哦
         * is_show : 1
         * is_commit :
         */

        private String title;
        private String term;
        private String address;
        private String is_show;
        private String is_commit;
        private List<SetMealBean> set_meal;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getIs_commit() {
            return is_commit;
        }

        public void setIs_commit(String is_commit) {
            this.is_commit = is_commit;
        }

        public List<SetMealBean> getSet_meal() {
            return set_meal;
        }

        public void setSet_meal(List<SetMealBean> set_meal) {
            this.set_meal = set_meal;
        }

        public static class SetMealBean implements Serializable {

            @Override
            public String toString() {
                return "SetMealBean{" +
                        "id='" + id + '\'' +
                        ", meal_log_id='" + meal_log_id + '\'' +
                        ", meal_id='" + meal_id + '\'' +
                        ", item_id='" + item_id + '\'' +
                        ", cat_id='" + cat_id + '\'' +
                        ", limit_num='" + limit_num + '\'' +
                        ", number='" + number + '\'' +
                        ", is_one_come='" + is_one_come + '\'' +
                        ", not_use_num='" + not_use_num + '\'' +
                        ", user_id='" + user_id + '\'' +
                        ", service_id='" + service_id + '\'' +
                        ", content_desc='" + content_desc + '\'' +
                        ", end_days='" + end_days + '\'' +
                        ", name='" + name + '\'' +
                        ", second_id='" + second_id + '\'' +
                        '}';
            }

            /**
             * id : 270
             * meal_log_id : 97
             * meal_id : 1
             * item_id : 8
             * cat_id : 2
             * limit_num : 1
             * number : 0
             * is_one_come : 0
             * not_use_num : 1
             * user_id : 368
             * service_id : 2
             * content_desc : 柜机空调1台
             * end_days : 365
             * name : 柜式空调
             * second_id : 2
             */

            private String id;
            private String meal_log_id;
            private String meal_id;
            private String item_id;
            private String cat_id;
            private String limit_num;
            private String number;
            private String is_one_come;
            private String not_use_num;
            private String user_id;
            private String service_id;
            private String content_desc;
            private String end_days;
            private String name;
            private String second_id;

            public static SetMealBean objectFromData(String str) {

                return new Gson().fromJson(str, SetMealBean.class);
            }

            public static SetMealBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), SetMealBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<SetMealBean> arraySetMealBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SetMealBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<SetMealBean> arraySetMealBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<SetMealBean>>() {
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

            public String getMeal_log_id() {
                return meal_log_id;
            }

            public void setMeal_log_id(String meal_log_id) {
                this.meal_log_id = meal_log_id;
            }

            public String getMeal_id() {
                return meal_id;
            }

            public void setMeal_id(String meal_id) {
                this.meal_id = meal_id;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(String limit_num) {
                this.limit_num = limit_num;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getIs_one_come() {
                return is_one_come;
            }

            public void setIs_one_come(String is_one_come) {
                this.is_one_come = is_one_come;
            }

            public String getNot_use_num() {
                return not_use_num;
            }

            public void setNot_use_num(String not_use_num) {
                this.not_use_num = not_use_num;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getContent_desc() {
                return content_desc;
            }

            public void setContent_desc(String content_desc) {
                this.content_desc = content_desc;
            }

            public String getEnd_days() {
                return end_days;
            }

            public void setEnd_days(String end_days) {
                this.end_days = end_days;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSecond_id() {
                return second_id;
            }

            public void setSecond_id(String second_id) {
                this.second_id = second_id;
            }
        }
    }
}
