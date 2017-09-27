package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.base.MySetMealBean;
import com.example.maintainsteward.mvp_view.MySetMealListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/9/27.
 */

public class MySetMealPresonter {

    HttpApi httpApi;

    public MySetMealPresonter() {
        httpApi = BaseHttpApi.getInstanceof();

    }

    public void getMySetMeal(String user_id,
                             String timestamp,
                             String sign,
                             String key) {
        Call<MySetMealBean> mySetMeal = httpApi.getMySetMeal(user_id, timestamp, sign, key);
        mySetMeal.enqueue(new Callback<MySetMealBean>() {
            @Override
            public void onResponse(Call<MySetMealBean> call, Response<MySetMealBean> response) {
                if (response.isSuccessful()) {

                    MySetMealBean body = response.body();

                    if (mySetMealListener != null) {

                        mySetMealListener.onLoadMySetMeal(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<MySetMealBean> call, Throwable t) {

            }
        });

    }

    MySetMealListener mySetMealListener;

    public void setMySetMealListener(MySetMealListener mySetMealListener) {
        this.mySetMealListener = mySetMealListener;
    }
}
