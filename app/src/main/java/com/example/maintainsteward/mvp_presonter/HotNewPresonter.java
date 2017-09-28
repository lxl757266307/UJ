package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.HotNewsList;
import com.example.maintainsteward.mvp_view.HotNewsListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/9/28.
 */

public class HotNewPresonter {

    HttpApi httpApi;

    public HotNewPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getHotNewList(
            String page,
            String timestamp,
            String sign,
            String key
    ) {

        Call<HotNewsList> hotNewsListCall = httpApi.hotNewsInformationLists(page, timestamp, sign, key);
        hotNewsListCall.enqueue(new Callback<HotNewsList>() {
            @Override
            public void onResponse(Call<HotNewsList> call, Response<HotNewsList> response) {

                if (response.isSuccessful()) {

                    HotNewsList body = response.body();

                    if (hotNewsListener != null) {
                        hotNewsListener.getHotNewsSucess(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<HotNewsList> call, Throwable t) {

            }
        });

    }

    HotNewsListener hotNewsListener;

    public void setHotNewsListener(HotNewsListener hotNewsListener) {
        this.hotNewsListener = hotNewsListener;
    }
}
