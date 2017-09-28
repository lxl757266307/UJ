package com.example.maintainsteward.bean;

/**
 * Created by Administrator on 2017/9/19.
 */

public class PublicBean {
    String status;
    String data;

    @Override
    public String toString() {
        return "PublicBean{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
