package com.example.maintainsteward.bean;

/**
 * Created by Administrator on 2017/9/7.
 */

public class YanZhengMaCallBackBean {

    String status;
    String data;


    @Override
    public String toString() {
        return "YanZhengMaCallBackBean{" +
                "status='" + status + '\'' +
                ", verifycode='" + data + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
