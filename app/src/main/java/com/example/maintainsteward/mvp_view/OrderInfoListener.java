package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.OrderInfoBean;

/**
 * Created by Administrator on 2017/9/23.
 */

public interface OrderInfoListener {

    void showDialog();

    void hideDialog();

    void getOrderInfoSucess(OrderInfoBean orderInfoBean);


}
