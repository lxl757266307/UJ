package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class SecondKindsBean {
    @Override
    public String toString() {
        return "SecondKindsBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"result":[{"id":"2","name":"空调清洗","desc":"暂不提供家用和商用中央空调清洗服务（选购365套餐，价格更优惠）。","parentid":"1","logourl":"http://os18w14e3.bkt.clouddn.com/59755eff6a827.jpg","level":"2","sort":"2","add_time":"1488855820","is_show":"1","content":"<p><\/p><div style=\"color:#72126\"><div style=\"color:#72126\"><p style=\"color:#A72126\">暂不提供家用和商用中央空调清洗服务（加入会员，选购365套餐，价格更优惠）。<\/p><p style=\"color:#A72126\">清洗流程：<\/p><p style=\"color:#A72126\">蒸汽机清洗，高温消毒，臭氧杀菌。<\/p><p style=\"color:#A72126\">服务质保：<\/p><p style=\"color:#A72126\">清洗类无质保。<\/p><p style=\"color:#A72126\"><br><\/p><\/div><\/div><p><\/p>","is_top":"1"},{"id":"3","name":"冰箱清洗","desc":"","parentid":"1","logourl":"http://os18w14e3.bkt.clouddn.com/5975b88816f2b.jpg","level":"2","sort":"2","add_time":"1488855820","is_show":"1","content":"<p>此价格仅针对于家用型冰箱，冰柜、商用型冰箱价格面议（选购365套餐，价格更优惠）。<\/p>","is_top":"1"},{"id":"21","name":"油烟机清洗","desc":"","parentid":"1","logourl":"http://os18w14e3.bkt.clouddn.com/597afe381faa7.jpg","level":"2","sort":"2","add_time":"1499392809","is_show":"1","content":"","is_top":"1"},{"id":"20","name":"洗衣机清洗","desc":"","parentid":"1","logourl":"http://os18w14e3.bkt.clouddn.com/597afe258e985.jpg","level":"2","sort":"3","add_time":"1499392794","is_show":"1","content":"","is_top":"0"}],"title":"家电清洗"}
     */

    private String status;
    private DataBean data;

    public static SecondKindsBean objectFromData(String str) {

        return new Gson().fromJson(str, SecondKindsBean.class);
    }

    public static SecondKindsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SecondKindsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SecondKindsBean> arraySecondKindsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SecondKindsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SecondKindsBean> arraySecondKindsBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SecondKindsBean>>() {
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
                    "title='" + title + '\'' +
                    ", result=" + result +
                    '}';
        }

        /**
         * result : [{"id":"2","name":"空调清洗","desc":"暂不提供家用和商用中央空调清洗服务（选购365套餐，价格更优惠）。","parentid":"1","logourl":"http://os18w14e3.bkt.clouddn.com/59755eff6a827.jpg","level":"2","sort":"2","add_time":"1488855820","is_show":"1","content":"<p><\/p><div style=\"color:#72126\"><div style=\"color:#72126\"><p style=\"color:#A72126\">暂不提供家用和商用中央空调清洗服务（加入会员，选购365套餐，价格更优惠）。<\/p><p style=\"color:#A72126\">清洗流程：<\/p><p style=\"color:#A72126\">蒸汽机清洗，高温消毒，臭氧杀菌。<\/p><p style=\"color:#A72126\">服务质保：<\/p><p style=\"color:#A72126\">清洗类无质保。<\/p><p style=\"color:#A72126\"><br><\/p><\/div><\/div><p><\/p>","is_top":"1"},{"id":"3","name":"冰箱清洗","desc":"","parentid":"1","logourl":"http://os18w14e3.bkt.clouddn.com/5975b88816f2b.jpg","level":"2","sort":"2","add_time":"1488855820","is_show":"1","content":"<p>此价格仅针对于家用型冰箱，冰柜、商用型冰箱价格面议（选购365套餐，价格更优惠）。<\/p>","is_top":"1"},{"id":"21","name":"油烟机清洗","desc":"","parentid":"1","logourl":"http://os18w14e3.bkt.clouddn.com/597afe381faa7.jpg","level":"2","sort":"2","add_time":"1499392809","is_show":"1","content":"","is_top":"1"},{"id":"20","name":"洗衣机清洗","desc":"","parentid":"1","logourl":"http://os18w14e3.bkt.clouddn.com/597afe258e985.jpg","level":"2","sort":"3","add_time":"1499392794","is_show":"1","content":"","is_top":"0"}]
         * title : 家电清洗
         */



        private String title;
        private List<ResultBean> result;

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

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {

            @Override
            public String toString() {
                return "ResultBean{" +
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
             * id : 2
             * name : 空调清洗
             * desc : 暂不提供家用和商用中央空调清洗服务（选购365套餐，价格更优惠）。
             * parentid : 1
             * logourl : http://os18w14e3.bkt.clouddn.com/59755eff6a827.jpg
             * level : 2
             * sort : 2
             * add_time : 1488855820
             * is_show : 1
             * content : <p></p><div style="color:#72126"><div style="color:#72126"><p style="color:#A72126">暂不提供家用和商用中央空调清洗服务（加入会员，选购365套餐，价格更优惠）。</p><p style="color:#A72126">清洗流程：</p><p style="color:#A72126">蒸汽机清洗，高温消毒，臭氧杀菌。</p><p style="color:#A72126">服务质保：</p><p style="color:#A72126">清洗类无质保。</p><p style="color:#A72126"><br></p></div></div><p></p>
             * is_top : 1
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

            public static ResultBean objectFromData(String str) {

                return new Gson().fromJson(str, ResultBean.class);
            }

            public static ResultBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ResultBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ResultBean> arrayResultBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ResultBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<ResultBean> arrayResultBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ResultBean>>() {
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
}
