package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.ZiXuanListBean;
import com.example.maintainsteward2.mvp_view.OnZiXuanListListener;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/8.
 */

public class ZiXuanListPresonter {
    HttpApi httpApi;

    public ZiXuanListPresonter() {

        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("count");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void getZiXuanList(String timestamp,
                              String sign,
                              String key) {
        Call<ZiXuanListBean> selfService = httpApi.getSelfService(timestamp, sign, key);
        selfService.enqueue(new Callback<ZiXuanListBean>() {
            @Override
            public void onResponse(Call<ZiXuanListBean> call, Response<ZiXuanListBean> response) {

                if (response.isSuccessful()) {

                    ZiXuanListBean body = response.body();

                    if (onZiXuanListListener != null) {

                        onZiXuanListListener.getZiXuanBean(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<ZiXuanListBean> call, Throwable t) {

            }
        });


    }

    OnZiXuanListListener onZiXuanListListener;

    public void setOnZiXuanListListener(OnZiXuanListListener onZiXuanListListener) {
        this.onZiXuanListListener = onZiXuanListListener;
    }
}
