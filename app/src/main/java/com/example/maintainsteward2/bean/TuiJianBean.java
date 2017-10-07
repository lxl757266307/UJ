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

public class TuiJianBean {


    /**
     * status : 1
     * data : {"direct_adver":"http://os18w14e3.bkt.clouddn.com/59c3661d411d9.jpg","direct_desc":"在城市尽头，没有繁华的街市，闪亮的霓虹；\n在城市的尽头，只有破旧的棚户区，有饱经生活风霜的生命；\n在城市的尽头，有他们这样一群人    \n\n我们不能叫着他们带着泥土气的名字，\n我们要帮助他们  \n让他们变成理想中的自己\u2026\u2026\n\n\n就防守打法","my_fans":{"fans_num":"0","order_count":"0","total_price":"0"},"my_rank":{"month_rank":"0","day_rank":"0","all_rank":"0"}}
     */

    private String status;
    private DataBean data;

    @Override
    public String toString() {
        return "TuiJianBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public static TuiJianBean objectFromData(String str) {

        return new Gson().fromJson(str, TuiJianBean.class);
    }

    public static TuiJianBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), TuiJianBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TuiJianBean> arrayTuiJianBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<TuiJianBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<TuiJianBean> arrayTuiJianBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<TuiJianBean>>() {
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
                    "direct_adver='" + direct_adver + '\'' +
                    ", direct_desc='" + direct_desc + '\'' +
                    ", my_fans=" + my_fans +
                    ", my_rank=" + my_rank +
                    '}';
        }

        /**
         * direct_adver : http://os18w14e3.bkt.clouddn.com/59c3661d411d9.jpg
         * direct_desc : 在城市尽头，没有繁华的街市，闪亮的霓虹；
         在城市的尽头，只有破旧的棚户区，有饱经生活风霜的生命；
         在城市的尽头，有他们这样一群人

         我们不能叫着他们带着泥土气的名字，
         我们要帮助他们
         让他们变成理想中的自己……


         就防守打法
         * my_fans : {"fans_num":"0","order_count":"0","total_price":"0"}
         * my_rank : {"month_rank":"0","day_rank":"0","all_rank":"0"}
         */

        private String direct_adver;
        private String direct_desc;
        private MyFansBean my_fans;
        private MyRankBean my_rank;

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

        public String getDirect_adver() {
            return direct_adver;
        }

        public void setDirect_adver(String direct_adver) {
            this.direct_adver = direct_adver;
        }

        public String getDirect_desc() {
            return direct_desc;
        }

        public void setDirect_desc(String direct_desc) {
            this.direct_desc = direct_desc;
        }

        public MyFansBean getMy_fans() {
            return my_fans;
        }

        public void setMy_fans(MyFansBean my_fans) {
            this.my_fans = my_fans;
        }

        public MyRankBean getMy_rank() {
            return my_rank;
        }

        public void setMy_rank(MyRankBean my_rank) {
            this.my_rank = my_rank;
        }

        public static class MyFansBean {
            /**
             * fans_num : 0
             * order_count : 0
             * total_price : 0
             */

            private String fans_num;
            private String order_count;
            private String total_price;

            public static MyFansBean objectFromData(String str) {

                return new Gson().fromJson(str, MyFansBean.class);
            }

            public static MyFansBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), MyFansBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<MyFansBean> arrayMyFansBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<MyFansBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<MyFansBean> arrayMyFansBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<MyFansBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getFans_num() {
                return fans_num;
            }

            public void setFans_num(String fans_num) {
                this.fans_num = fans_num;
            }

            public String getOrder_count() {
                return order_count;
            }

            public void setOrder_count(String order_count) {
                this.order_count = order_count;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }
        }

        public static class MyRankBean {
            /**
             * month_rank : 0
             * day_rank : 0
             * all_rank : 0
             */

            private String month_rank;
            private String day_rank;
            private String all_rank;

            public static MyRankBean objectFromData(String str) {

                return new Gson().fromJson(str, MyRankBean.class);
            }

            public static MyRankBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), MyRankBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<MyRankBean> arrayMyRankBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<MyRankBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<MyRankBean> arrayMyRankBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<MyRankBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getMonth_rank() {
                return month_rank;
            }

            public void setMonth_rank(String month_rank) {
                this.month_rank = month_rank;
            }

            public String getDay_rank() {
                return day_rank;
            }

            public void setDay_rank(String day_rank) {
                this.day_rank = day_rank;
            }

            public String getAll_rank() {
                return all_rank;
            }

            public void setAll_rank(String all_rank) {
                this.all_rank = all_rank;
            }
        }
    }
}
