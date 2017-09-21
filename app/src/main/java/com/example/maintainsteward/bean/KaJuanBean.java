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
 * Created by Administrator on 2017/9/21.
 */

public class KaJuanBean implements Serializable {

    @Override
    public String toString() {
        return "KaJuanBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"resultData":[{"id":"76","bonus_id":"15","uid":"363","do_uid":"0","status":"0","used_time":"0","add_time":"1505972949","bonus_type":"1","bonus_sn":"8aec2be6","bonus_amount":"98.00","bonus_condition":"99.00","bonus_desc":"","start_time":"1501639639","end_time":"1506910044","limit_num":"1","get_all_num":"2","cat_id":"0","cat_name":"","start_time_format":"2017-08-02","end_time_format":"2017-10-02"}],"totalcount":"1","totalpage":"1"}
     */

    private String status;
    private DataBean data;

    public static KaJuanBean objectFromData(String str) {

        return new Gson().fromJson(str, KaJuanBean.class);
    }

    public static KaJuanBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), KaJuanBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<KaJuanBean> arrayKaJuanBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<KaJuanBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<KaJuanBean> arrayKaJuanBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<KaJuanBean>>() {
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

    public static class DataBean  implements Serializable  {
        @Override
        public String toString() {
            return "DataBean{" +
                    "totalcount='" + totalcount + '\'' +
                    ", totalpage='" + totalpage + '\'' +
                    ", resultData=" + resultData +
                    '}';
        }

        /**
         * resultData : [{"id":"76","bonus_id":"15","uid":"363","do_uid":"0","status":"0","used_time":"0","add_time":"1505972949","bonus_type":"1","bonus_sn":"8aec2be6","bonus_amount":"98.00","bonus_condition":"99.00","bonus_desc":"","start_time":"1501639639","end_time":"1506910044","limit_num":"1","get_all_num":"2","cat_id":"0","cat_name":"","start_time_format":"2017-08-02","end_time_format":"2017-10-02"}]
         * totalcount : 1
         * totalpage : 1
         */

        private String totalcount;
        private String totalpage;
        private List<ResultDataBean> resultData;

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

        public String getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(String totalcount) {
            this.totalcount = totalcount;
        }

        public String getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(String totalpage) {
            this.totalpage = totalpage;
        }

        public List<ResultDataBean> getResultData() {
            return resultData;
        }

        public void setResultData(List<ResultDataBean> resultData) {
            this.resultData = resultData;
        }

        public static class ResultDataBean   implements Serializable {

            @Override
            public String toString() {
                return "ResultDataBean{" +
                        "id='" + id + '\'' +
                        ", bonus_id='" + bonus_id + '\'' +
                        ", uid='" + uid + '\'' +
                        ", do_uid='" + do_uid + '\'' +
                        ", status='" + status + '\'' +
                        ", used_time='" + used_time + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", bonus_type='" + bonus_type + '\'' +
                        ", bonus_sn='" + bonus_sn + '\'' +
                        ", bonus_amount='" + bonus_amount + '\'' +
                        ", bonus_condition='" + bonus_condition + '\'' +
                        ", bonus_desc='" + bonus_desc + '\'' +
                        ", start_time='" + start_time + '\'' +
                        ", end_time='" + end_time + '\'' +
                        ", limit_num='" + limit_num + '\'' +
                        ", get_all_num='" + get_all_num + '\'' +
                        ", cat_id='" + cat_id + '\'' +
                        ", cat_name='" + cat_name + '\'' +
                        ", start_time_format='" + start_time_format + '\'' +
                        ", end_time_format='" + end_time_format + '\'' +
                        '}';
            }

            /**
             * id : 76
             * bonus_id : 15
             * uid : 363
             * do_uid : 0
             * status : 0
             * used_time : 0
             * add_time : 1505972949
             * bonus_type : 1
             * bonus_sn : 8aec2be6
             * bonus_amount : 98.00
             * bonus_condition : 99.00
             * bonus_desc :
             * start_time : 1501639639
             * end_time : 1506910044
             * limit_num : 1
             * get_all_num : 2
             * cat_id : 0
             * cat_name :
             * start_time_format : 2017-08-02
             * end_time_format : 2017-10-02
             */

            private String id;
            private String bonus_id;
            private String uid;
            private String do_uid;
            private String status;
            private String used_time;
            private String add_time;
            private String bonus_type;
            private String bonus_sn;
            private String bonus_amount;
            private String bonus_condition;
            private String bonus_desc;
            private String start_time;
            private String end_time;
            private String limit_num;
            private String get_all_num;
            private String cat_id;
            private String cat_name;
            private String start_time_format;
            private String end_time_format;

            public static ResultDataBean objectFromData(String str) {

                return new Gson().fromJson(str, ResultDataBean.class);
            }

            public static ResultDataBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ResultDataBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ResultDataBean> arrayResultDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ResultDataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<ResultDataBean> arrayResultDataBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ResultDataBean>>() {
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

            public String getBonus_id() {
                return bonus_id;
            }

            public void setBonus_id(String bonus_id) {
                this.bonus_id = bonus_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getDo_uid() {
                return do_uid;
            }

            public void setDo_uid(String do_uid) {
                this.do_uid = do_uid;
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

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getBonus_type() {
                return bonus_type;
            }

            public void setBonus_type(String bonus_type) {
                this.bonus_type = bonus_type;
            }

            public String getBonus_sn() {
                return bonus_sn;
            }

            public void setBonus_sn(String bonus_sn) {
                this.bonus_sn = bonus_sn;
            }

            public String getBonus_amount() {
                return bonus_amount;
            }

            public void setBonus_amount(String bonus_amount) {
                this.bonus_amount = bonus_amount;
            }

            public String getBonus_condition() {
                return bonus_condition;
            }

            public void setBonus_condition(String bonus_condition) {
                this.bonus_condition = bonus_condition;
            }

            public String getBonus_desc() {
                return bonus_desc;
            }

            public void setBonus_desc(String bonus_desc) {
                this.bonus_desc = bonus_desc;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(String limit_num) {
                this.limit_num = limit_num;
            }

            public String getGet_all_num() {
                return get_all_num;
            }

            public void setGet_all_num(String get_all_num) {
                this.get_all_num = get_all_num;
            }

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public String getStart_time_format() {
                return start_time_format;
            }

            public void setStart_time_format(String start_time_format) {
                this.start_time_format = start_time_format;
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
