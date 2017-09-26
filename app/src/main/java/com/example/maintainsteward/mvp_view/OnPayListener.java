package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.PayInfoBean;

/**
 * Created by Administrator on 2017/9/26.
 */

public interface OnPayListener {

    void getYuZhiFuInfo(PayInfoBean bean);

    void onPaySucess(String status);

}
