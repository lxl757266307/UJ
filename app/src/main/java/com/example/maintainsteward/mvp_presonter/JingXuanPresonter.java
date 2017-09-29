package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.BannerBean;
import com.example.maintainsteward.bean.JingXuanBean;
import com.example.maintainsteward.mvp_view.JingXuanListener;
import com.example.maintainsteward.utils.ToolUitls;
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
 * Created by Administrator on 2017/9/23.
 */

public class JingXuanPresonter {
    HttpApi httpApi;

    public JingXuanPresonter() {

        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("index");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void getJingXuan(String page,
                            String timestamp,
                            String sign,
                            String key) {
        Call<JingXuanBean> carefullySelectedSort = httpApi.getCarefullySelectedSort(page, timestamp, sign, key);
        carefullySelectedSort.enqueue(new Callback<JingXuanBean>() {
            @Override
            public void onResponse(Call<JingXuanBean> call, Response<JingXuanBean> response) {

                if (response.isSuccessful()) {
                    JingXuanBean body = response.body();

                    if (jingXuanListener != null) {
                        jingXuanListener.getJingXuan(body);
                    }


                }
            }

            @Override
            public void onFailure(Call<JingXuanBean> call, Throwable t) {

            }
        });

    }

    JingXuanListener jingXuanListener;

    public void setJingXuanListener(JingXuanListener jingXuanListener) {
        this.jingXuanListener = jingXuanListener;
    }
}
