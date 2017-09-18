package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.OrderListBean;

/**
 * Created by Administrator on 2017/9/18.
 */


public interface GetOrderListListener {


    void showDialog();

    void getAllList(OrderListBean listBean);


    void dialogDismiss();

}
