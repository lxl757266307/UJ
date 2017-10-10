package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.MyFaBuListBean;
import com.example.maintainsteward2.mvp_view.OnMyFastFaBuListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/10.
 */

public class MyFaBuListPresonter {
    HttpApi httpApi;

    public MyFaBuListPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getFastOrder(
            String user_id,
            String page,
            String timestamp,
            String sign,
            String key
    ) {

        Call<MyFaBuListBean> fastOrder = httpApi.getFastOrder(user_id, page, timestamp, sign, key);
        fastOrder.enqueue(new Callback<MyFaBuListBean>() {
            @Override
            public void onResponse(Call<MyFaBuListBean> call, Response<MyFaBuListBean> response) {

                if (response.isSuccessful()) {

                    MyFaBuListBean body = response.body();
                    if (onMyFastFaBuListener != null) {
                        onMyFastFaBuListener.getFastFaBuList(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyFaBuListBean> call, Throwable t) {

            }
        });


    }

    OnMyFastFaBuListener onMyFastFaBuListener;

    public void setOnMyFastFaBuListener(OnMyFastFaBuListener onMyFastFaBuListener) {
        this.onMyFastFaBuListener = onMyFastFaBuListener;
    }
}
