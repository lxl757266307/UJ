package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class FirstKindsBean {


    @Override
    public String toString() {
        return "FirstKindsBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : [{"id":"1","name":"家电清洗","desc":"1","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/595e1c98b730c.png","level":"1","sort":"0","add_time":"1488855820","is_show":"1","content":"","is_top":"0"},{"id":"66","name":"家电维修","desc":"","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/59793a563fd1e.png","level":"1","sort":"1","add_time":"1501117017","is_show":"1","content":"","is_top":"0"},{"id":"4","name":"插座","desc":"1","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/595e1cb796743.png","level":"1","sort":"2","add_time":"1488855820","is_show":"1","content":"","is_top":"0"},{"id":"5","name":"水路管路","desc":"1","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/595e1cc9c2ec4.png","level":"1","sort":"3","add_time":"1488855820","is_show":"1","content":"","is_top":"0"},{"id":"6","name":"门窗维修","desc":"1","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/595e1cd8c1d84.png","level":"1","sort":"4","add_time":"1488855820","is_show":"1","content":"","is_top":"0"},{"id":"7","name":"墙面地面","desc":"1","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/595e1ce8c4822.png","level":"1","sort":"5","add_time":"1488855820","is_show":"1","content":"","is_top":"0"},{"id":"63","name":"房屋检测","desc":"","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/595f26d884a0a.png","level":"1","sort":"6","add_time":"1499407582","is_show":"1","content":"","is_top":"0"},{"id":"72","name":"灯具安装","desc":"","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/597e95299a9a0.png","level":"1","sort":"9","add_time":"1501118002","is_show":"1","content":"","is_top":"0"},{"id":"85","name":"开关","desc":"","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/597af6ca46cc8.png","level":"1","sort":"10","add_time":"1501119930","is_show":"1","content":"","is_top":"0"},{"id":"97","name":"空开","desc":"","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/597afb9a37c62.png","level":"1","sort":"11","add_time":"1501121653","is_show":"1","content":"","is_top":"0"},{"id":"103","name":"电路","desc":"","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/59794fec374e9.png","level":"1","sort":"12","add_time":"1501122541","is_show":"1","content":"","is_top":"0"},{"id":"106","name":"厨卫洁具","desc":"","parentid":"0","logourl":"http://os18w14e3.bkt.clouddn.com/59795ae2d437f.png","level":"1","sort":"13","add_time":"1501125348","is_show":"1","content":"","is_top":"0"}]
     */

    private String status;
    private List<DataBean> data;

    public static FirstKindsBean objectFromData(String str) {

        return new Gson().fromJson(str, FirstKindsBean.class);
    }

    public static FirstKindsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), FirstKindsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<FirstKindsBean> arrayFirstKindsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<FirstKindsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<FirstKindsBean> arrayFirstKindsBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<FirstKindsBean>>() {
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

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", desc='" + desc + '\'' +
                    ", parentid='" + parentid + '\'' +
                    ", logourl='" + logourl + '\'' +
                    ", level='" + level + '\'' +
                    ", sort='" + sort + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", is_show='" + is_show + '\'' +
                    ", content='" + content + '\'' +
                    ", is_top='" + is_top + '\'' +
                    '}';
        }

        /**
         * id : 1
         * name : 家电清洗
         * desc : 1
         * parentid : 0
         * logourl : http://os18w14e3.bkt.clouddn.com/595e1c98b730c.png
         * level : 1
         * sort : 0
         * add_time : 1488855820
         * is_show : 1
         * content :
         * is_top : 0
         */

        private String id;
        private String name;
        private String desc;
        private String parentid;
        private String logourl;
        private String level;
        private String sort;
        private String add_time;
        private String is_show;
        private String content;
        private String is_top;

        @Expose
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getLogourl() {
            return logourl;
        }

        public void setLogourl(String logourl) {
            this.logourl = logourl;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }
    }
}


