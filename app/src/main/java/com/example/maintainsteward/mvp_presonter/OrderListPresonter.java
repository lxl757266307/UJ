package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.mvp_view.GetOrderListListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/18.
 */

public class OrderListPresonter {

    HttpApi httpApi;

    public static final String TAG = "OrderListPresonter";

    public OrderListPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void showDialog() {
        orderListListener.showDialog();
    }

    public void getOrderList(String id, String order_type, String page, String timestamp, String sign, String key) {


        Call<OrderListBean> orderList = httpApi.getOrderList(id, order_type, page, timestamp, sign, key);
        orderList.enqueue(new Callback<OrderListBean>() {
            @Override
            public void onResponse(Call<OrderListBean> call, Response<OrderListBean> response) {

                ToolUitls.print(TAG, "response==" + response + "      response.body==" + response.body());


                if (response.isSuccessful()) {

                    OrderListBean body = response.body();
                    if (orderListListener != null && body != null) {
                        orderListListener.getAllList(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<OrderListBean> call, Throwable t) {
            }
        });
    }

    public void getWeiWanChengOrderList(String id, String order_type, String page, String timestamp, String sign, String key) {


        Call<OrderListBean> orderList = httpApi.getOrderList(id, order_type, page, timestamp, sign, key);
        orderList.enqueue(new Callback<OrderListBean>() {
            @Override
            public void onResponse(Call<OrderListBean> call, Response<OrderListBean> response) {



                if (response.isSuccessful()) {

                    OrderListBean body = response.body();
                    if (orderListListener != null && body != null) {
                        orderListListener.getWeiWanChengList(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<OrderListBean> call, Throwable t) {
            }
        });
    }


    public void getYiWanChengOrderList(String id, String order_type, String page, String timestamp, String sign, String key) {


        Call<OrderListBean> orderList = httpApi.getOrderList(id, order_type, page, timestamp, sign, key);
        orderList.enqueue(new Callback<OrderListBean>() {
            @Override
            public void onResponse(Call<OrderListBean> call, Response<OrderListBean> response) {



                if (response.isSuccessful()) {

                    OrderListBean body = response.body();
                    if (orderListListener != null && body != null) {
                        orderListListener.getYiWanChengList(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<OrderListBean> call, Throwable t) {
            }
        });
    }

    public void getYiQuXiaoOrderList(String id, String order_type, String page, String timestamp, String sign, String key) {


        Call<OrderListBean> orderList = httpApi.getOrderList(id, order_type, page, timestamp, sign, key);
        orderList.enqueue(new Callback<OrderListBean>() {
            @Override
            public void onResponse(Call<OrderListBean> call, Response<OrderListBean> response) {



                if (response.isSuccessful()) {

                    OrderListBean body = response.body();
                    if (orderListListener != null && body != null) {
                        orderListListener.getYiQuXiaoList(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<OrderListBean> call, Throwable t) {
            }
        });
    }

    public void dialogDismiss() {
        orderListListener.dialogDismiss();
    }


    GetOrderListListener orderListListener;

    public void setOrderListListener(GetOrderListListener orderListListener) {
        this.orderListListener = orderListListener;
    }
}
