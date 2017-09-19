package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.mvp_view.OnOrderCancleListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/19.
 */

public class QuXiaoOrderPresonter {

    HttpApi httpApi;

    public QuXiaoOrderPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void quXiaoOrder(String user_id, String order_id, String timestamp, String sign, String key, final int position) {

        Call<PublicBean> publicBeanCall = httpApi.quXiaoOrder(user_id, order_id, timestamp, sign, key);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {

                    PublicBean body = response.body();

                    if (onOrderCancleListener != null && body != null) {

                        onOrderCancleListener.onOrderCancle(body,position);
                    }

                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });

    }

    OnOrderCancleListener onOrderCancleListener;

    public void setOnOrderCancleListener(OnOrderCancleListener onOrderCancleListener) {
        this.onOrderCancleListener = onOrderCancleListener;
    }
}
