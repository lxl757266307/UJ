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
 * Created by Administrator on 2017/9/20.
 */

public class UserInfoBean implements Serializable {
    @Override
    public String toString() {
        return "UserInfoBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"id":"363","user_login":"13226925232","user_pass":"","user_phone":"13226925232","paypwd":"","is_set_paypwd":"0","user_nicename":"13226925232","user_name":"","user_email":"","avatar":"","sex":"0","birthday":"请设置出生日期","last_login_ip":"","last_login_time":"2017-09-07 15:07:14","create_time":"2017-09-07 15:07:14","user_activation_key":"","user_status":"1","user_balance":"0.00","score":"0","earn_points":"0","user_type":"2","yqm":"ce40f22e","parentid":"0","is_vip":"0","open_id":"","access_token":"","qrcode":"","focuse_num":"0","operate":"0","follow_type":"0","commission_money":"0","commission_balance":"0","topid":"0","chinaid":"","ioschinaid":"","bonus_count":"0","order_count":"11","is_buy":"0","msg_count":"0"}
     */

    private String status;
    private DataBean data;

    public static UserInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, UserInfoBean.class);
    }

    public static UserInfoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), UserInfoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<UserInfoBean> arrayUserInfoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<UserInfoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<UserInfoBean> arrayUserInfoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<UserInfoBean>>() {
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
                    "id='" + id + '\'' +
                    ", user_login='" + user_login + '\'' +
                    ", user_pass='" + user_pass + '\'' +
                    ", user_phone='" + user_phone + '\'' +
                    ", paypwd='" + paypwd + '\'' +
                    ", is_set_paypwd='" + is_set_paypwd + '\'' +
                    ", user_nicename='" + user_nicename + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", user_email='" + user_email + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", sex='" + sex + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", last_login_ip='" + last_login_ip + '\'' +
                    ", last_login_time='" + last_login_time + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", user_activation_key='" + user_activation_key + '\'' +
                    ", user_status='" + user_status + '\'' +
                    ", user_balance='" + user_balance + '\'' +
                    ", score='" + score + '\'' +
                    ", earn_points='" + earn_points + '\'' +
                    ", user_type='" + user_type + '\'' +
                    ", yqm='" + yqm + '\'' +
                    ", parentid='" + parentid + '\'' +
                    ", is_vip='" + is_vip + '\'' +
                    ", open_id='" + open_id + '\'' +
                    ", access_token='" + access_token + '\'' +
                    ", qrcode='" + qrcode + '\'' +
                    ", focuse_num='" + focuse_num + '\'' +
                    ", operate='" + operate + '\'' +
                    ", follow_type='" + follow_type + '\'' +
                    ", commission_money='" + commission_money + '\'' +
                    ", commission_balance='" + commission_balance + '\'' +
                    ", topid='" + topid + '\'' +
                    ", chinaid='" + chinaid + '\'' +
                    ", ioschinaid='" + ioschinaid + '\'' +
                    ", bonus_count='" + bonus_count + '\'' +
                    ", order_count='" + order_count + '\'' +
                    ", is_buy='" + is_buy + '\'' +
                    ", msg_count='" + msg_count + '\'' +
                    '}';
        }

        /**
         * id : 363
         * user_login : 13226925232
         * user_pass :
         * user_phone : 13226925232
         * paypwd :
         * is_set_paypwd : 0
         * user_nicename : 13226925232
         * user_name :
         * user_email :
         * avatar :
         * sex : 0
         * birthday : 请设置出生日期
         * last_login_ip :
         * last_login_time : 2017-09-07 15:07:14
         * create_time : 2017-09-07 15:07:14
         * user_activation_key :
         * user_status : 1
         * user_balance : 0.00
         * score : 0
         * earn_points : 0
         * user_type : 2
         * yqm : ce40f22e
         * parentid : 0
         * is_vip : 0
         * open_id :
         * access_token :
         * qrcode :
         * focuse_num : 0
         * operate : 0
         * follow_type : 0
         * commission_money : 0
         * commission_balance : 0
         * topid : 0
         * chinaid :
         * ioschinaid :
         * bonus_count : 0
         * order_count : 11
         * is_buy : 0
         * msg_count : 0
         */

        private String id;
        private String user_login;
        private String user_pass;
        private String user_phone;
        private String paypwd;
        private String is_set_paypwd;
        private String user_nicename;
        private String user_name;
        private String user_email;
        private String avatar;
        private String sex;
        private String birthday;
        private String last_login_ip;
        private String last_login_time;
        private String create_time;
        private String user_activation_key;
        private String user_status;
        private String user_balance;
        private String score;
        private String earn_points;
        private String user_type;
        private String yqm;
        private String parentid;
        private String is_vip;
        private String open_id;
        private String access_token;
        private String qrcode;
        private String focuse_num;
        private String operate;
        private String follow_type;
        private String commission_money;
        private String commission_balance;
        private String topid;
        private String chinaid;
        private String ioschinaid;
        private String bonus_count;
        private String order_count;
        private String is_buy;
        private String msg_count;

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

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getUser_pass() {
            return user_pass;
        }

        public void setUser_pass(String user_pass) {
            this.user_pass = user_pass;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getPaypwd() {
            return paypwd;
        }

        public void setPaypwd(String paypwd) {
            this.paypwd = paypwd;
        }

        public String getIs_set_paypwd() {
            return is_set_paypwd;
        }

        public void setIs_set_paypwd(String is_set_paypwd) {
            this.is_set_paypwd = is_set_paypwd;
        }

        public String getUser_nicename() {
            return user_nicename;
        }

        public void setUser_nicename(String user_nicename) {
            this.user_nicename = user_nicename;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUser_activation_key() {
            return user_activation_key;
        }

        public void setUser_activation_key(String user_activation_key) {
            this.user_activation_key = user_activation_key;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getUser_balance() {
            return user_balance;
        }

        public void setUser_balance(String user_balance) {
            this.user_balance = user_balance;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getEarn_points() {
            return earn_points;
        }

        public void setEarn_points(String earn_points) {
            this.earn_points = earn_points;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getYqm() {
            return yqm;
        }

        public void setYqm(String yqm) {
            this.yqm = yqm;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getFocuse_num() {
            return focuse_num;
        }

        public void setFocuse_num(String focuse_num) {
            this.focuse_num = focuse_num;
        }

        public String getOperate() {
            return operate;
        }

        public void setOperate(String operate) {
            this.operate = operate;
        }

        public String getFollow_type() {
            return follow_type;
        }

        public void setFollow_type(String follow_type) {
            this.follow_type = follow_type;
        }

        public String getCommission_money() {
            return commission_money;
        }

        public void setCommission_money(String commission_money) {
            this.commission_money = commission_money;
        }

        public String getCommission_balance() {
            return commission_balance;
        }

        public void setCommission_balance(String commission_balance) {
            this.commission_balance = commission_balance;
        }

        public String getTopid() {
            return topid;
        }

        public void setTopid(String topid) {
            this.topid = topid;
        }

        public String getChinaid() {
            return chinaid;
        }

        public void setChinaid(String chinaid) {
            this.chinaid = chinaid;
        }

        public String getIoschinaid() {
            return ioschinaid;
        }

        public void setIoschinaid(String ioschinaid) {
            this.ioschinaid = ioschinaid;
        }

        public String getBonus_count() {
            return bonus_count;
        }

        public void setBonus_count(String bonus_count) {
            this.bonus_count = bonus_count;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public String getIs_buy() {
            return is_buy;
        }

        public void setIs_buy(String is_buy) {
            this.is_buy = is_buy;
        }

        public String getMsg_count() {
            return msg_count;
        }

        public void setMsg_count(String msg_count) {
            this.msg_count = msg_count;
        }
    }
}
