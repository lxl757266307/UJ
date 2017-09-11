package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.FirstKindsBean;
import com.example.maintainsteward.bean.SecondKindsBean;
import com.example.maintainsteward.mvp_view.KindsListener;
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
 * Created by Administrator on 2017/9/11.
 */

public class KindsPresonter {

    public static final String TAG = "KindsPresonter";
    HttpApi httpApi;
    Retrofit retrofit;

    public KindsPresonter() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("isCheck");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    /*获取一级分类*/
    public void getFirstKinds(String timestamp, String sign, String key) {
        Call<FirstKindsBean> firstKinds = httpApi.getFirstKinds(timestamp, sign, key);
        firstKinds.enqueue(new Callback<FirstKindsBean>() {
            @Override
            public void onResponse(Call<FirstKindsBean> call, Response<FirstKindsBean> response) {
                if (response.isSuccessful()) {

                    FirstKindsBean body = response.body();

                    if (kindsListener != null && body != null) {

                        kindsListener.getFirstKinds(body);


                    }
                }

            }

            @Override
            public void onFailure(Call<FirstKindsBean> call, Throwable t) {

            }
        });

    }






    KindsListener kindsListener;

    public void setFirstKindsListener(KindsListener firstKindsListener) {
        this.kindsListener = firstKindsListener;
    }
}
