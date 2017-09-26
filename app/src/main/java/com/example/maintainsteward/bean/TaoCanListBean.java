package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class TaoCanListBean implements Serializable {

    @Override
    public String toString() {
        return "TaoCanListBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"meal_id":"1","money":"399.00","set_meal_data":[{"group_id":"15","name":"挂机","set_meal":[{"item_id":"7","meal_id":"1","service_id":"1","content_desc":"挂机空调1-3台","limit_num":"1","group_id":"15","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"挂式空调","expenses":"109"}]},{"group_id":"14","name":"柜机","set_meal":[{"item_id":"8","meal_id":"1","service_id":"2","content_desc":"柜机空调1台","limit_num":"1","group_id":"14","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"柜式空调","expenses":"129"}]},{"group_id":"3","name":"分类项","set_meal":[{"item_id":"17","meal_id":"1","service_id":"3","content_desc":"单开门冰箱1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"单开门冰箱","expenses":"99"},{"item_id":"18","meal_id":"1","service_id":"12","content_desc":"双开门冰箱1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"双开门冰箱","expenses":"109"},{"item_id":"19","meal_id":"1","service_id":"13","content_desc":"三开门冰箱1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"三开门冰箱","expenses":"129"},{"item_id":"20","meal_id":"1","service_id":"14","content_desc":"全自动洗衣机1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"单波轮洗衣机","expenses":"119"},{"item_id":"21","meal_id":"1","service_id":"15","content_desc":"半自动洗衣机1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"双波轮洗衣机","expenses":"99"},{"item_id":"22","meal_id":"1","service_id":"50","content_desc":"换窗纱1次","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"窗纱更换","expenses":"30"}]},{"group_id":"16","name":"油烟机中式","set_meal":[{"item_id":"9","meal_id":"1","service_id":"16","content_desc":"中式/欧式2选1","limit_num":"1","group_id":"16","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"中式油烟机","expenses":"149"}]},{"group_id":"17","name":"房屋检测","set_meal":[{"item_id":"24","meal_id":"1","service_id":"105","content_desc":"整屋检测","limit_num":"-1","group_id":"17","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"房屋检测","expenses":"30"}]},{"group_id":"18","name":"油烟机欧式","set_meal":[{"item_id":"23","meal_id":"1","service_id":"17","content_desc":"中式/欧式油烟机2选1","limit_num":"1","group_id":"18","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"欧式油烟机","expenses":"149"}]}]}
     */


    private String status;
    private DataBean data;

    public static TaoCanListBean objectFromData(String str) {

        return new Gson().fromJson(str, TaoCanListBean.class);
    }

    public static TaoCanListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), TaoCanListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TaoCanListBean> arrayTaoCanListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<TaoCanListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<TaoCanListBean> arrayTaoCanListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<TaoCanListBean>>() {
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
                    "meal_id='" + meal_id + '\'' +
                    ", money='" + money + '\'' +
                    ", set_meal_data=" + set_meal_data +
                    '}';
        }

        /**
         * meal_id : 1
         * money : 399.00
         * set_meal_data : [{"group_id":"15","name":"挂机","set_meal":[{"item_id":"7","meal_id":"1","service_id":"1","content_desc":"挂机空调1-3台","limit_num":"1","group_id":"15","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"挂式空调","expenses":"109"}]},{"group_id":"14","name":"柜机","set_meal":[{"item_id":"8","meal_id":"1","service_id":"2","content_desc":"柜机空调1台","limit_num":"1","group_id":"14","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"柜式空调","expenses":"129"}]},{"group_id":"3","name":"分类项","set_meal":[{"item_id":"17","meal_id":"1","service_id":"3","content_desc":"单开门冰箱1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"单开门冰箱","expenses":"99"},{"item_id":"18","meal_id":"1","service_id":"12","content_desc":"双开门冰箱1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"双开门冰箱","expenses":"109"},{"item_id":"19","meal_id":"1","service_id":"13","content_desc":"三开门冰箱1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"三开门冰箱","expenses":"129"},{"item_id":"20","meal_id":"1","service_id":"14","content_desc":"全自动洗衣机1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"单波轮洗衣机","expenses":"119"},{"item_id":"21","meal_id":"1","service_id":"15","content_desc":"半自动洗衣机1台","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"双波轮洗衣机","expenses":"99"},{"item_id":"22","meal_id":"1","service_id":"50","content_desc":"换窗纱1次","limit_num":"1","group_id":"3","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"窗纱更换","expenses":"30"}]},{"group_id":"16","name":"油烟机中式","set_meal":[{"item_id":"9","meal_id":"1","service_id":"16","content_desc":"中式/欧式2选1","limit_num":"1","group_id":"16","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"中式油烟机","expenses":"149"}]},{"group_id":"17","name":"房屋检测","set_meal":[{"item_id":"24","meal_id":"1","service_id":"105","content_desc":"整屋检测","limit_num":"-1","group_id":"17","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"房屋检测","expenses":"30"}]},{"group_id":"18","name":"油烟机欧式","set_meal":[{"item_id":"23","meal_id":"1","service_id":"17","content_desc":"中式/欧式油烟机2选1","limit_num":"1","group_id":"18","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"欧式油烟机","expenses":"149"}]}]
         */

        private String meal_id;
        private String money;
        private List<SetMealDataBean> set_meal_data;

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

        public String getMeal_id() {
            return meal_id;
        }

        public void setMeal_id(String meal_id) {
            this.meal_id = meal_id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public List<SetMealDataBean> getSet_meal_data() {
            return set_meal_data;
        }

        public void setSet_meal_data(List<SetMealDataBean> set_meal_data) {
            this.set_meal_data = set_meal_data;
        }

        public static class SetMealDataBean implements Serializable {


            @Override
            public String toString() {
                return "SetMealDataBean{" +
                        "group_id='" + group_id + '\'' +
                        ", name='" + name + '\'' +
                        ", set_meal=" + set_meal +
                        '}';
            }

            /**
             * group_id : 15
             * name : 挂机
             * set_meal : [{"item_id":"7","meal_id":"1","service_id":"1","content_desc":"挂机空调1-3台","limit_num":"1","group_id":"15","sort":"0","end_days":"365","status":"1","number":"0","is_one_come":"0","name":"挂式空调","expenses":"109"}]
             */

            private String group_id;
            private String name;
            private List<SetMealBean> set_meal;

            public static SetMealDataBean objectFromData(String str) {

                return new Gson().fromJson(str, SetMealDataBean.class);
            }

            public static SetMealDataBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), SetMealDataBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<SetMealDataBean> arraySetMealDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SetMealDataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<SetMealDataBean> arraySetMealDataBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<SetMealDataBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
                            "item_id='" + item_id + '\'' +
                            ", meal_id='" + meal_id + '\'' +
                            ", service_id='" + service_id + '\'' +
                            ", content_desc='" + content_desc + '\'' +
                            ", limit_num='" + limit_num + '\'' +
                            ", group_id='" + group_id + '\'' +
                            ", sort='" + sort + '\'' +
                            ", end_days='" + end_days + '\'' +
                            ", status='" + status + '\'' +
                            ", number='" + number + '\'' +
                            ", is_one_come='" + is_one_come + '\'' +
                            ", name='" + name + '\'' +
                            ", expenses='" + expenses + '\'' +
                            '}';
                }

                /**
                 * item_id : 7
                 * meal_id : 1
                 * service_id : 1
                 * content_desc : 挂机空调1-3台
                 * limit_num : 1
                 * group_id : 15
                 * sort : 0
                 * end_days : 365
                 * status : 1
                 * number : 0
                 * is_one_come : 0
                 * name : 挂式空调
                 * expenses : 109
                 */

                @Expose
                private  boolean clickAble;

                @Expose
                private boolean isCheck;
                private String item_id;
                private String meal_id;
                private String service_id;
                private String content_desc;
                private String limit_num;
                private String group_id;
                private String sort;
                private String end_days;
                private String status;
                private String number;
                private String is_one_come;
                private String name;
                private String expenses;

                public boolean isClickAble() {
                    return clickAble;
                }

                public void setClickAble(boolean clickAble) {
                    this.clickAble = clickAble;
                }

                public boolean isCheck() {
                    return isCheck;
                }

                public void setCheck(boolean check) {
                    isCheck = check;
                }

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

                public String getItem_id() {
                    return item_id;
                }

                public void setItem_id(String item_id) {
                    this.item_id = item_id;
                }

                public String getMeal_id() {
                    return meal_id;
                }

                public void setMeal_id(String meal_id) {
                    this.meal_id = meal_id;
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

                public String getLimit_num() {
                    return limit_num;
                }

                public void setLimit_num(String limit_num) {
                    this.limit_num = limit_num;
                }

                public String getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(String group_id) {
                    this.group_id = group_id;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getEnd_days() {
                    return end_days;
                }

                public void setEnd_days(String end_days) {
                    this.end_days = end_days;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getExpenses() {
                    return expenses;
                }

                public void setExpenses(String expenses) {
                    this.expenses = expenses;
                }
            }
        }
    }
}
