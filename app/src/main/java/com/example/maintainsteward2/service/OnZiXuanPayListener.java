package com.example.maintainsteward2.service;

import com.example.maintainsteward2.bean.PayInfoBean;
import com.example.maintainsteward2.bean.PublicBean;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface OnZiXuanPayListener {

    void getZiXuanPayInfo(PayInfoBean payInfoBean);

    void payForNow(PublicBean publicBean);
}
