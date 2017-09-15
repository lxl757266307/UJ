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
 * Created by Administrator on 2017/9/15.
 */

public class AddressListBean  implements Serializable {


    @Override
    public String toString() {
        return "AddressListBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : [{"id":"172","uid":"363","user_name":"app约谈","province":"0","city":"2","district":"20","community":"0","address":"西安市新城区","is_default":"0","add_time":"1505461664","is_vip":"0","tel":"15353738776","city_name":"西安市","district_name":"新城区","community_name":""},{"id":"173","uid":"363","user_name":"app约谈","province":"0","city":"2","district":"20","community":"0","address":"西安市新城区","is_default":"0","add_time":"1505462389","is_vip":"0","tel":"15353738776","city_name":"西安市","district_name":"新城区","community_name":""},{"id":"186","uid":"363","user_name":"办证","province":"0","city":"3","district":"7","community":"0","address":"空军建军节","is_default":"0","add_time":"1505466827","is_vip":"0","tel":"13891973035","city_name":"咸阳市","district_name":"秦都区","community_name":""},{"id":"187","uid":"363","user_name":"app约谈","province":"0","city":"2","district":"4","community":"0","address":"就凉快","is_default":"0","add_time":"1505466909","is_vip":"0","tel":"15353738776","city_name":"西安市","district_name":"未央区","community_name":""},{"id":"185","uid":"363","user_name":"办信用卡","province":"0","city":"3","district":"7","community":"0","address":"kill图","is_default":"0","add_time":"1505466753","is_vip":"0","tel":"18691882839","city_name":"咸阳市","district_name":"秦都区","community_name":""}]
     */


    private String status;
    private List<DataBean> data;

    public static AddressListBean objectFromData(String str) {

        return new Gson().fromJson(str, AddressListBean.class);
    }

    public static AddressListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AddressListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<AddressListBean> arrayAddressListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AddressListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<AddressListBean> arrayAddressListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<AddressListBean>>() {
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

    public static class DataBean implements Serializable  {
        /**
         * id : 172
         * uid : 363
         * user_name : app约谈
         * province : 0
         * city : 2
         * district : 20
         * community : 0
         * address : 西安市新城区
         * is_default : 0
         * add_time : 1505461664
         * is_vip : 0
         * tel : 15353738776
         * city_name : 西安市
         * district_name : 新城区
         * community_name :
         */

        private String id;
        private String uid;
        private String user_name;
        private String province;
        private String city;
        private String district;
        private String community;
        private String address;
        private String is_default;
        private String add_time;
        private String is_vip;
        private String tel;
        private String city_name;
        private String district_name;
        private String community_name;


        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    ", community='" + community + '\'' +
                    ", address='" + address + '\'' +
                    ", is_default='" + is_default + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", is_vip='" + is_vip + '\'' +
                    ", tel='" + tel + '\'' +
                    ", city_name='" + city_name + '\'' +
                    ", district_name='" + district_name + '\'' +
                    ", community_name='" + community_name + '\'' +
                    '}';
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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getCommunity() {
            return community;
        }

        public void setCommunity(String community) {
            this.community = community;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDistrict_name() {
            return district_name;
        }

        public void setDistrict_name(String district_name) {
            this.district_name = district_name;
        }

        public String getCommunity_name() {
            return community_name;
        }

        public void setCommunity_name(String community_name) {
            this.community_name = community_name;
        }
    }
}
