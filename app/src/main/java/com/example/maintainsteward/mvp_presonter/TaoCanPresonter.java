package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.TaoCanListBean;
import com.example.maintainsteward.mvp_view.TaoCanListener;
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
 * Created by Administrator on 2017/9/26.
 */

public class TaoCanPresonter {

    HttpApi httpApi;

    public TaoCanPresonter() {

        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("isCheck")||f.getName().contains("clickAble");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void taoCanList(String timestamp,
                           String sign,
                           String key) {

        Call<TaoCanListBean> setMealDetails = httpApi.getSetMealDetails(timestamp, sign, key);
        setMealDetails.enqueue(new Callback<TaoCanListBean>() {
            @Override
            public void onResponse(Call<TaoCanListBean> call, Response<TaoCanListBean> response) {

                if (response.isSuccessful()) {

                    TaoCanListBean body = response.body();

                    if (taoCanListener != null) {

                        taoCanListener.onLoadListSucess(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<TaoCanListBean> call, Throwable t) {

            }
        });

    }

    TaoCanListener taoCanListener;

    public void setTaoCanListener(TaoCanListener taoCanListener) {
        this.taoCanListener = taoCanListener;
    }
}
