package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.FenSiBean2;
import com.example.maintainsteward2.bean.FensiBean;
import com.example.maintainsteward2.mvp_view.OnFenSiListener;
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

public class FenSiPresonter {
    HttpApi httpApi;

    public FenSiPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getFenSiBean(String id,
                             String type,
                             String page,
                             String timestamp,
                             String sign,
                             String key) {
        Call<FenSiBean2> myFenSi = httpApi.getMyFenSi(id, type, page, timestamp, sign, key);

        myFenSi.enqueue(new Callback<FenSiBean2>() {
            @Override
            public void onResponse(Call<FenSiBean2> call, Response<FenSiBean2> response) {
                if (response.isSuccessful()) {

                    FenSiBean2 body = response.body();
                    if (onFenSiListener != null) {
                        onFenSiListener.getFenSiBean(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<FenSiBean2> call, Throwable t) {

            }
        });

    }

    OnFenSiListener onFenSiListener;

    public void setOnFenSiListener(OnFenSiListener onFenSiListener) {
        this.onFenSiListener = onFenSiListener;
    }
}
