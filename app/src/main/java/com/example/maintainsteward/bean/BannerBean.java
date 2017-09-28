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
 * Created by Administrator on 2017/9/5.
 */

public class BannerBean   implements Serializable{


    @Override
    public String toString() {
        return "BannerBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"slide_posts":[{"slide_id":"4","slide_pic":"http://os18w14e3.bkt.clouddn.com/595e32de0c6bc.jpg"},{"slide_id":"5","slide_pic":"http://os18w14e3.bkt.clouddn.com/595e335d12dc0.jpg"}],"information_lists":[{"id":"8","title":"家装要避开的6个坑","add_time":"2017-07-06 19:45:03","url":"http://wx.cnncsh.com/wx/life_con.html?id=8"},{"id":"10","title":"99%的人都不会选择洗衣机","add_time":"2017-07-06 20:16:18","url":"http://wx.cnncsh.com/wx/life_con.html?id=10"},{"id":"12","title":"你真的了解空调病吗？","add_time":"2017-07-06 20:25:48","url":"http://wx.cnncsh.com/wx/life_con.html?id=12"},{"id":"13","title":"老司带你区别风冷、直冷","add_time":"2017-07-06 20:34:54","url":"http://wx.cnncsh.com/wx/life_con.html?id=13"}]}
     */



    private String status;
    private DataBean data;

    public static BannerBean objectFromData(String str) {

        return new Gson().fromJson(str, BannerBean.class);
    }

    public static BannerBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), BannerBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<BannerBean> arrayBannerBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<BannerBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<BannerBean> arrayBannerBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<BannerBean>>() {
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

    public static class DataBean  implements Serializable {
        @Override
        public String toString() {
            return "DataBean{" +
                    "slide_posts=" + slide_posts +
                    ", information_lists=" + information_lists +
                    '}';
        }

        private List<SlidePostsBean> slide_posts;
        private List<InformationListsBean> information_lists;

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

        public List<SlidePostsBean> getSlide_posts() {
            return slide_posts;
        }

        public void setSlide_posts(List<SlidePostsBean> slide_posts) {
            this.slide_posts = slide_posts;
        }

        public List<InformationListsBean> getInformation_lists() {
            return information_lists;
        }

        public void setInformation_lists(List<InformationListsBean> information_lists) {
            this.information_lists = information_lists;
        }

        public static class SlidePostsBean  implements Serializable {

            @Override
            public String toString() {
                return "SlidePostsBean{" +
                        "slide_id='" + slide_id + '\'' +
                        ", slide_pic='" + slide_pic + '\'' +
                        '}';
            }

            /**
             * slide_id : 4
             * slide_pic : http://os18w14e3.bkt.clouddn.com/595e32de0c6bc.jpg
             */

            private String slide_id;
            private String slide_pic;

            public static SlidePostsBean objectFromData(String str) {

                return new Gson().fromJson(str, SlidePostsBean.class);
            }

            public static SlidePostsBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), SlidePostsBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<SlidePostsBean> arraySlidePostsBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SlidePostsBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<SlidePostsBean> arraySlidePostsBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<SlidePostsBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getSlide_id() {
                return slide_id;
            }

            public void setSlide_id(String slide_id) {
                this.slide_id = slide_id;
            }

            public String getSlide_pic() {
                return slide_pic;
            }

            public void setSlide_pic(String slide_pic) {
                this.slide_pic = slide_pic;
            }
        }

        public static class InformationListsBean  implements Serializable{


            @Override
            public String toString() {
                return "InformationListsBean{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            /**
             * id : 8
             * title : 家装要避开的6个坑
             * add_time : 2017-07-06 19:45:03
             * url : http://wx.cnncsh.com/wx/life_con.html?id=8
             */

            private String id;
            private String title;
            private String add_time;
            private String url;

            public static InformationListsBean objectFromData(String str) {

                return new Gson().fromJson(str, InformationListsBean.class);
            }

            public static InformationListsBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), InformationListsBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<InformationListsBean> arrayInformationListsBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<InformationListsBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<InformationListsBean> arrayInformationListsBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<InformationListsBean>>() {
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
