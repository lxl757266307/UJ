package com.example.maintainsteward.bean;

/**
 * Created by Administrator on 2017/9/16.
 */

public class UpLoadPhotoCallBackBean {

    @Override
    public String toString() {
        return "HelpOrderCallBackBean{" +
                "state=" + state +
                ", state_msg='" + state_msg + '\'' +
                ", qrcode='" + qrcode + '\'' +
                '}';
    }

    /**
     * state : 1
     * state_msg : ok
     * qrcode : http://app.cnncsh.com/Uploads/wxqrcode/2017080310133628245.png
     */

    private int state;
    private String state_msg;
    private String qrcode;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getState_msg() {
        return state_msg;
    }

    public void setState_msg(String state_msg) {
        this.state_msg = state_msg;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
