package com.example.maintainsteward.mvp_presonter.main_fragment;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.BannerBean;
import com.example.maintainsteward.mvp_view.main_fragement.OnLoadBannerListener;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/5.
 */

public class MainFragmentPresonter {
    Retrofit retrofit;
    HttpApi httpApi;

    public MainFragmentPresonter() {

        retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        httpApi = retrofit.create(HttpApi.class);


    }


    public void getBanner(String timestamp, String sign, String key) {

        Call<BannerBean> call = httpApi.getBanner(timestamp, sign, key);
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {

                if (response.isSuccessful()) {

                    BannerBean body = response.body();

                    if (onLoadBannerListener != null && body != null) {
                        onLoadBannerListener.onLoadBanner(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });


    }


    OnLoadBannerListener onLoadBannerListener;

    public void setOnLoadBannerListener(OnLoadBannerListener onLoadBannerListener) {
        this.onLoadBannerListener = onLoadBannerListener;
    }
}
