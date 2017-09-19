package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.bean.PublicBean;

/**
 * Created by Administrator on 2017/9/18.
 */


public interface GetOrderListListener {


    void showDialog();

    void getAllList(OrderListBean listBean);

    void getWeiWanChengList(OrderListBean listBean);

    void getYiWanChengList(OrderListBean listBean);

    void getYiQuXiaoList(OrderListBean listBean);


    void dialogDismiss();

}
