package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.HotWordBean;
import com.example.maintainsteward.mvp_view.SearchHotWordListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/9/7.
 */

public class SearchPresonter {

    Retrofit retrofit;
    HttpApi api;

    public SearchPresonter() {
        retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(HttpApi.class);
    }

    public void getHotWord(String timestamp,
                           String sign,
                           String key) {

        Call<HotWordBean> hotWord = api.getHotWord(timestamp, sign, key);
        hotWord.enqueue(new Callback<HotWordBean>() {
            @Override
            public void onResponse(Call<HotWordBean> call, Response<HotWordBean> response) {

                if (response.isSuccessful()) {
                    HotWordBean body = response.body();
                    if (searchHotWordListener != null && body != null) {
                        searchHotWordListener.onSearchSucess(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<HotWordBean> call, Throwable t) {

            }
        });


    }

    SearchHotWordListener searchHotWordListener;

    public void setSearchHotWordListener(SearchHotWordListener searchHotWordListener) {
        this.searchHotWordListener = searchHotWordListener;
    }
}
