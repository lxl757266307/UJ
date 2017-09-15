package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.mvp_view.ChooseLocationListener;
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
 * Created by Administrator on 2017/9/12.
 */

public class ChooseLocationPresonter {

    public static final String TAG = "ChooseLocationPresonter";

    HttpApi api;

    public ChooseLocationPresonter() {
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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        api = retrofit.create(HttpApi.class);
    }

    public void getCityList(String timestamp, String sign, String key) {

        Call<CityListBean> cityList = api.getCityList(timestamp, sign, key);

        cityList.enqueue(new Callback<CityListBean>() {
            @Override
            public void onResponse(Call<CityListBean> call, Response<CityListBean> response) {


                if (response.isSuccessful()) {

                    CityListBean body = response.body();
                    if (chooseLocationListener != null && body != null) {
                        chooseLocationListener.getCityList(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<CityListBean> call, Throwable t) {

            }
        });


    }

    ChooseLocationListener chooseLocationListener;

    public void setChooseLocationListener(ChooseLocationListener chooseLocationListener) {
        this.chooseLocationListener = chooseLocationListener;
    }
}
