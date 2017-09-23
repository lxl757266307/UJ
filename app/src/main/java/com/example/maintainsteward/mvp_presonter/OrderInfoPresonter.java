package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.OrderInfoBean;
import com.example.maintainsteward.mvp_view.OrderInfoListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/23.
 */

public class OrderInfoPresonter {

    HttpApi httpApi;

    public OrderInfoPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }


    public void getOrderInfo(String user_id,
                             String id,
                             String timestamp,
                             String sign,
                             String key) {

        if (orderInfoListener != null) {
            orderInfoListener.showDialog();
        }

        Call<OrderInfoBean> orderDetails = httpApi.getOrderDetails(user_id, id, timestamp, sign, key);
        orderDetails.enqueue(new Callback<OrderInfoBean>() {
            @Override
            public void onResponse(Call<OrderInfoBean> call, Response<OrderInfoBean> response) {

                if (response.isSuccessful()) {

                    OrderInfoBean body = response.body();

                    if (orderInfoListener != null) {

                        orderInfoListener.getOrderInfoSucess(body);
                        orderInfoListener.hideDialog();
                    }


                }
            }

            @Override
            public void onFailure(Call<OrderInfoBean> call, Throwable t) {

            }
        });


    }


    OrderInfoListener orderInfoListener;

    public void setOrderInfoListener(OrderInfoListener orderInfoListener) {
        this.orderInfoListener = orderInfoListener;
    }
}
