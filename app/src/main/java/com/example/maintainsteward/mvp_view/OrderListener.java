package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.OrderSucessBean;

/**
 * Created by Administrator on 2017/9/18.
 */

public interface OrderListener {

    void orderSucess(OrderSucessBean bean);

    void orderFaild(String message);
}
