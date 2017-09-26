package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class PayInfoBean implements Serializable{

    @Override
    public String toString() {
        return "PayInfoBean{" +
                "prepayid='" + prepayid + '\'' +
                ", appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", packageX='" + packageX + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                ", status=" + status +
                '}';
    }

    /**
     * prepayid : wx2017092609051706f8c715390063481246
     * appid : wx4c957187aba457ff
     * partnerid : 1489325262
     * package : Sign=WXPay
     * noncestr : w2uzbj6hhvor3qhip3iz1vcf5qp9pfqf
     * timestamp : 1506387917
     * sign : 0575369D1EDC6FB6B7705E547C7F9329
     * status : 1
     */

    private String prepayid;
    private String appid;
    private String partnerid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private int timestamp;
    private String sign;
    private int status;

    public static PayInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, PayInfoBean.class);
    }

    public static PayInfoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PayInfoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<PayInfoBean> arrayPayInfoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<PayInfoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<PayInfoBean> arrayPayInfoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<PayInfoBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
