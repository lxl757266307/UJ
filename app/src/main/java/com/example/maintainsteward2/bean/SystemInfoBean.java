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
 * Created by Administrator on 2017/11/21.
 */

public class SystemInfoBean  implements Serializable{

    @Override
    public String toString() {
        return "SystemInfoBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : [{"id":"41","title":"测试消息","content":"测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息","douid":"1","received_id":"368","add_time":"2017-09-23","is_read":"0"},{"id":"42","title":"测试消息","content":"测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息","douid":"1","received_id":"368","add_time":"2017-09-23","is_read":"0"},{"id":"43","title":"测试消息","content":"测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息","douid":"1","received_id":"368","add_time":"2017-09-23","is_read":"0"},{"id":"44","title":"测试消息","content":"测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息","douid":"1","received_id":"368","add_time":"2017-09-23","is_read":"0"},{"id":"45","title":"测试消息","content":"测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息","douid":"1","received_id":"368","add_time":"2017-09-23","is_read":"0"},{"id":"113","title":"测试消息","content":"测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息","douid":"1","received_id":"368","add_time":"2017-09-23","is_read":"0"},{"id":"226","title":"新版本上架啦","content":"新版本上架啦新版本上架啦新版本上架啦新版本上架啦","douid":"1","received_id":"368","add_time":"2017-09-23","is_read":"0"}]
     */


    private String status;
    private List<DataBean> data;

    public static SystemInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, SystemInfoBean.class);
    }

    public static SystemInfoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SystemInfoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SystemInfoBean> arraySystemInfoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SystemInfoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SystemInfoBean> arraySystemInfoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SystemInfoBean>>() {
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

    public static class DataBean  implements Serializable {

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", douid='" + douid + '\'' +
                    ", received_id='" + received_id + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", is_read='" + is_read + '\'' +
                    '}';
        }

        /**
         * id : 41
         * title : 测试消息
         * content : 测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息
         * douid : 1
         * received_id : 368
         * add_time : 2017-09-23
         * is_read : 0
         */

        private String id;
        private String title;
        private String content;
        private String douid;
        private String received_id;
        private String add_time;
        private String is_read;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDouid() {
            return douid;
        }

        public void setDouid(String douid) {
            this.douid = douid;
        }

        public String getReceived_id() {
            return received_id;
        }

        public void setReceived_id(String received_id) {
            this.received_id = received_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }
    }
}
