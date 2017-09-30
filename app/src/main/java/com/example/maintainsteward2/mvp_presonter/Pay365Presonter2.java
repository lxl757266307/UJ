package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.PayInfoBean;
import com.example.maintainsteward2.bean.PublicBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/30.
 */

public class Pay365Presonter2 {

    HttpApi httpApi;

    public Pay365Presonter2() {

        httpApi = BaseHttpApi.getInstanceof();
    }

    public void payForNow(String no, String type, String price) {

        Call<PublicBean> publicBeanCall = httpApi.payForNowNew(no, type, price);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {

                    if (onPriceZeroListener != null) {
                        onPriceZeroListener.onZeroListener(response.body());
                    }

                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });
    }


    OnPriceZeroListener onPriceZeroListener;

    public void setOnPriceZeroListener(OnPriceZeroListener onPriceZeroListener) {
        this.onPriceZeroListener = onPriceZeroListener;
    }

    public interface OnPriceZeroListener {

        void onZeroListener(PublicBean bean);
    }
}
