package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.SearviceInfoBean;
import com.example.maintainsteward.bean.SecondKindsContent;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;
import com.example.maintainsteward.mvp_view.ServiceInfoListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/11.
 */

public class SearviceInfoPresonter {

    HttpApi httpApi;

    public static final String TAG = "SearviceInfoPresonter";

    public SearviceInfoPresonter() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("number");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        httpApi = retrofit.create(HttpApi.class);
    }


    public void getSearviceInfo(String id,
                                String timestamp,
                                String sign,
                                String key) {
        final Call<SearviceInfoBean> searviceAll = httpApi.getSearviceAll(id, timestamp, sign, key);
        searviceAll.enqueue(new Callback<SearviceInfoBean>() {
            @Override
            public void onResponse(Call<SearviceInfoBean> call, Response<SearviceInfoBean> response) {


                if (response.isSuccessful()) {

                    SearviceInfoBean body = response.body();

                    if (serviceInfoListener != null && body != null) {
                        serviceInfoListener.getServiceAll(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<SearviceInfoBean> call, Throwable t) {

            }
        });

    }

    public void getSecondKindsContent(String id,
                                      String timestamp,
                                      String sign,
                                      String key) {
        Call<SecondKindsContent> secondKindsContent = httpApi.getSecondKindsContent(id, timestamp, sign, key);
        secondKindsContent.enqueue(new Callback<SecondKindsContent>() {
            @Override
            public void onResponse(Call<SecondKindsContent> call, Response<SecondKindsContent> response) {
                if (response.isSuccessful()) {


                    SecondKindsContent body = response.body();

                    if (serviceInfoListener != null && body != null) {
                        serviceInfoListener.getSecondKindsContent(body);
                    }


                }

            }

            @Override
            public void onFailure(Call<SecondKindsContent> call, Throwable t) {

            }
        });

    }


    /*服务配件*/
    public void getServiceGoodsGet(String id,
                                   String timestamp,
                                   String sign,
                                   String key) {

        Call<ServiceGoodsGetBean> serviceGoodsGet = httpApi.getServiceGoodsGet(id, timestamp, sign, key);
        serviceGoodsGet.enqueue(new Callback<ServiceGoodsGetBean>() {
            @Override
            public void onResponse(Call<ServiceGoodsGetBean> call, Response<ServiceGoodsGetBean> response) {
                if (response.isSuccessful()) {


                    ServiceGoodsGetBean body = response.body();

                    if (serviceInfoListener != null && body != null) {
                        serviceInfoListener.getServiceGoodsGet(body);
                    }


                }
            }

            @Override
            public void onFailure(Call<ServiceGoodsGetBean> call, Throwable t) {

            }
        });


    }


    ServiceInfoListener serviceInfoListener;

    public void setServiceInfoListener(ServiceInfoListener serviceInfoListener) {
        this.serviceInfoListener = serviceInfoListener;
    }
}
