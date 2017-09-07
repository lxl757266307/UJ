package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.BannerBean;
import com.example.maintainsteward.bean.AppIndexCategoryBean;
import com.example.maintainsteward.mvp_view.OnLoadBannerListener;

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

    public static final String TAG = "MainFragmentPresonter";

    public void getAppIndexCategory(final String timestamp, final String key, final String sign, final int page) {

        final Call<AppIndexCategoryBean> appIndexCategory = httpApi.getAppIndexCategory(timestamp, sign, key, page);

        appIndexCategory.enqueue(new Callback<AppIndexCategoryBean>() {
            @Override
            public void onResponse(Call<AppIndexCategoryBean> call, Response<AppIndexCategoryBean> response) {

                if (response.isSuccessful()) {
                    AppIndexCategoryBean body = response.body();

                    if (onLoadBannerListener != null && body != null) {
                        onLoadBannerListener.onLoadAppIndexCategory(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<AppIndexCategoryBean> call, Throwable t) {

            }
        });
    }


    OnLoadBannerListener onLoadBannerListener;

    public void setOnLoadBannerListener(OnLoadBannerListener onLoadBannerListener) {
        this.onLoadBannerListener = onLoadBannerListener;
    }
}
