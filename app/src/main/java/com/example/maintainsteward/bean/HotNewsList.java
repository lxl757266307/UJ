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
 * Created by Administrator on 2017/9/28.
 */

public class HotNewsList implements Serializable {

    @Override
    public String toString() {
        return "HotNewsList{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : [{"id":"8","title":"家装要避开的6个坑","cover":"http://os18w14e3.bkt.clouddn.com/596471532fb6b.jpg","summary":"家居装修是一件既麻烦又耗时的事情，没有哪个业主有时间天天去新","add_time":"2017-07-06 19:45:03","url":"http://wx.cnncsh.com/wx/life_con.html?id=8"},{"id":"10","title":"99%的人都不会选择洗衣机","cover":"http://os18w14e3.bkt.clouddn.com/5964731bc5b5c.jpg","summary":"关于洗衣机大致就分为滚筒式洗衣机和波轮式洗衣机两大类，但是好","add_time":"2017-07-06 20:16:18","url":"http://wx.cnncsh.com/wx/life_con.html?id=10"},{"id":"12","title":"你真的了解空调病吗？","cover":"http://os18w14e3.bkt.clouddn.com/5964765748cc2.jpg","summary":"随着天气的炎热，空调已经成为了人们离不开的必须品，回到家中，","add_time":"2017-07-06 20:25:48","url":"http://wx.cnncsh.com/wx/life_con.html?id=12"},{"id":"13","title":"老司带你区别风冷、直冷","cover":"http://os18w14e3.bkt.clouddn.com/59647ccd51901.jpg","summary":"在冰箱选择风冷还是直冷好的前提下，我们必须清楚的知道，没有一","add_time":"2017-07-06 20:34:54","url":"http://wx.cnncsh.com/wx/life_con.html?id=13"}]
     */

    private String status;
    private List<DataBean> data;

    public static HotNewsList objectFromData(String str) {

        return new Gson().fromJson(str, HotNewsList.class);
    }

    public static HotNewsList objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), HotNewsList.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<HotNewsList> arrayHotNewsListFromData(String str) {

        Type listType = new TypeToken<ArrayList<HotNewsList>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<HotNewsList> arrayHotNewsListFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<HotNewsList>>() {
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean  implements Serializable{

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", cover='" + cover + '\'' +
                    ", summary='" + summary + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        /**
         * id : 8
         * title : 家装要避开的6个坑
         * cover : http://os18w14e3.bkt.clouddn.com/596471532fb6b.jpg
         * summary : 家居装修是一件既麻烦又耗时的事情，没有哪个业主有时间天天去新
         * add_time : 2017-07-06 19:45:03
         * url : http://wx.cnncsh.com/wx/life_con.html?id=8
         */

        private String id;
        private String title;
        private String cover;
        private String summary;
        private String add_time;
        private String url;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
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
