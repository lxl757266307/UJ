package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.PaiHangBean;
import com.example.maintainsteward2.mvp_view.OnPaiHangBangListener;
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
 * Created by Administrator on 2017/10/7.
 */

public class PaiHangBangPresonter {
    HttpApi httpApi;

    public PaiHangBangPresonter() {

        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("type");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void getExtendSort(String id,
                              String type,
                              String page,
                              String timestamp,
                              String sign,
                              String key) {

        Call<PaiHangBean> extendSort = httpApi.getExtendSort(id, type, page, timestamp, sign, key);
        extendSort.enqueue(new Callback<PaiHangBean>() {
            @Override
            public void onResponse(Call<PaiHangBean> call, Response<PaiHangBean> response) {

                if (response.isSuccessful()) {

                    PaiHangBean body = response.body();

                    if (onPaiHangBangListener != null) {

                        onPaiHangBangListener.getPaiHangBang(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<PaiHangBean> call, Throwable t) {

            }
        });

    }

    OnPaiHangBangListener onPaiHangBangListener;

    public void setOnPaiHangBangListener(OnPaiHangBangListener onPaiHangBangListener) {
        this.onPaiHangBangListener = onPaiHangBangListener;
    }
}
