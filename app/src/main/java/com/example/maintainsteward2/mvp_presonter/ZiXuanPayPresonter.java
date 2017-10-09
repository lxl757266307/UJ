package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.PayInfoBean;
import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.service.OnZiXuanPayListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/9.
 */

public class ZiXuanPayPresonter {

    HttpApi httpApi;

    public ZiXuanPayPresonter() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.WX_PAY_ZI_XUAN_URL).addConverterFactory(GsonConverterFactory.create()).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void getZiXuanPayInfo(String no) {
        Call<PayInfoBean> ziXuanPayInfo = httpApi.getZiXuanPayInfo(no);
        ziXuanPayInfo.enqueue(new Callback<PayInfoBean>() {
            @Override
            public void onResponse(Call<PayInfoBean> call, Response<PayInfoBean> response) {

                if (response.isSuccessful()) {
                    PayInfoBean body = response.body();

                    if (ziXuanPayListener != null) {
                        ziXuanPayListener.getZiXuanPayInfo(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<PayInfoBean> call, Throwable t) {

            }
        });

    }

    public void payForNow(String order_sn,
                          String paytpe,
                          String total_fee) {

        Call<PublicBean> publicBeanCall = httpApi.payForNowNew(order_sn, paytpe, total_fee);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {
                    PublicBean body = response.body();
                    if (ziXuanPayListener != null) {
                        ziXuanPayListener.payForNow(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });


    }

    OnZiXuanPayListener ziXuanPayListener;

    public void setZiXuanPayListener(OnZiXuanPayListener ziXuanPayListener) {
        this.ziXuanPayListener = ziXuanPayListener;
    }
}
