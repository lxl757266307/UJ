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

public class FenSiBean2 {


    /**
     * status : 1
     * data : {"count":"5","next_child":"2","data":[{"id":"173","user_nicename":"刘小小","parentid":"368","topid":"0","create_time":"2017-07-03 09:59:57","count":"0","total_money":"0","child_count":"0","child":[{"id":"349","user_nicename":"尼古拉斯","parentid":"173","topid":"0","create_time":"2017-08-30 10:07:55","count":"3","total_money":"0"}]},{"id":"174","user_nicename":"15353684517","parentid":"368","topid":"0","create_time":"2017-07-03 10:53:23","count":"0","total_money":"0","child_count":"1","child":[]},{"id":"370","user_nicename":"我的人都有自己","parentid":"368","topid":"0","create_time":"2017-09-29 18:12:36","count":"1","total_money":"0.01","child_count":"0","child":[{"id":"371","user_nicename":"18652364785","parentid":"370","topid":"368","create_time":"2017-09-30 09:17:45","count":"2","total_money":"0.02"},{"id":"372","user_nicename":"17629238203","parentid":"370","topid":"368","create_time":"2017-09-30 09:55:50","count":"0","total_money":""}]},{"id":"369","user_nicename":"18625156655","parentid":"368","topid":"0","create_time":"2017-09-29 13:58:38","count":"0","total_money":"0","child_count":"2","child":[]}]}
     */

    private String status;
    private DataBeanX data;

    public static FenSiBean2 objectFromData(String str) {

        return new Gson().fromJson(str, FenSiBean2.class);
    }

    public static FenSiBean2 objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), FenSiBean2.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<FenSiBean2> arrayFenSiBean2FromData(String str) {

        Type listType = new TypeToken<ArrayList<FenSiBean2>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<FenSiBean2> arrayFenSiBean2FromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<FenSiBean2>>() {
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
         * count : 5
         * next_child : 2
         * data : [{"id":"173","user_nicename":"刘小小","parentid":"368","topid":"0","create_time":"2017-07-03 09:59:57","count":"0","total_money":"0","child_count":"0","child":[{"id":"349","user_nicename":"尼古拉斯","parentid":"173","topid":"0","create_time":"2017-08-30 10:07:55","count":"3","total_money":"0"}]},{"id":"174","user_nicename":"15353684517","parentid":"368","topid":"0","create_time":"2017-07-03 10:53:23","count":"0","total_money":"0","child_count":"1","child":[]},{"id":"370","user_nicename":"我的人都有自己","parentid":"368","topid":"0","create_time":"2017-09-29 18:12:36","count":"1","total_money":"0.01","child_count":"0","child":[{"id":"371","user_nicename":"18652364785","parentid":"370","topid":"368","create_time":"2017-09-30 09:17:45","count":"2","total_money":"0.02"},{"id":"372","user_nicename":"17629238203","parentid":"370","topid":"368","create_time":"2017-09-30 09:55:50","count":"0","total_money":""}]},{"id":"369","user_nicename":"18625156655","parentid":"368","topid":"0","create_time":"2017-09-29 13:58:38","count":"0","total_money":"0","child_count":"2","child":[]}]
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
             * id : 173
             * user_nicename : 刘小小
             * parentid : 368
             * topid : 0
             * create_time : 2017-07-03 09:59:57
             * count : 0
             * total_money : 0
             * child_count : 0
             * child : [{"id":"349","user_nicename":"尼古拉斯","parentid":"173","topid":"0","create_time":"2017-08-30 10:07:55","count":"3","total_money":"0"}]
             */

            private String id;
            private String user_nicename;
            private String parentid;
            private String topid;
            private String create_time;
            private String count;
            private String total_money;
            private String child_count;
            private List<ChildBean> child;



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

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
                /**
                 * id : 349
                 * user_nicename : 尼古拉斯
                 * parentid : 173
                 * topid : 0
                 * create_time : 2017-08-30 10:07:55
                 * count : 3
                 * total_money : 0
                 */

                private String id;
                private String user_nicename;
                private String parentid;
                private String topid;
                private String create_time;
                private String count;
                private String total_money;

                public static ChildBean objectFromData(String str) {

                    return new Gson().fromJson(str, ChildBean.class);
                }

                public static ChildBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), ChildBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<ChildBean> arrayChildBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ChildBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<ChildBean> arrayChildBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<ChildBean>>() {
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
            }
        }
    }
}
