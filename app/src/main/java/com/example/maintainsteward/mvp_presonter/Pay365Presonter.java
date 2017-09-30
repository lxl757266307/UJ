package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.PayInfoBean;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.mvp_view.OnPayListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/27.
 */

public class Pay365Presonter {

    HttpApi httpApi;

    public Pay365Presonter() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.WX_PAY365_URL).addConverterFactory(GsonConverterFactory.create()).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void getPayInfo(String orderNo) {

        Call<PayInfoBean> payInfo = httpApi.getPayInfo(orderNo, "0");
        payInfo.enqueue(new Callback<PayInfoBean>() {
            @Override
            public void onResponse(Call<PayInfoBean> call, Response<PayInfoBean> response) {

                if (response.isSuccessful()) {
                    PayInfoBean body = response.body();
                    if (onPayListener != null) {
                        onPayListener.getYuZhiFuInfo(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<PayInfoBean> call, Throwable t) {

            }
        });

    }

    public void payForNowNew(String order_sn,
                             String paytpe,
                             String total_fee) {


        Call<PublicBean> publicBeanCall = httpApi.payForNowNew(order_sn, paytpe, total_fee);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {

                    PublicBean body = response.body();

                    if (onPayListener != null) {

                        onPayListener.onPaySucess(body.getStatus());
                    }

                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });


    }

    OnPayListener onPayListener;

    public void setOnPayListener(OnPayListener onPayListener) {
        this.onPayListener = onPayListener;
    }
}
