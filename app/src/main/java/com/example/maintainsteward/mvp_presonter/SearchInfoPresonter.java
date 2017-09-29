package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.HotWordBean;
import com.example.maintainsteward.bean.SearchKeyWordBean;
import com.example.maintainsteward.mvp_view.SearchHotWordListener;
import com.example.maintainsteward.mvp_view.SearchKeyWordListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/9/7.
 */

public class SearchInfoPresonter {

    Retrofit retrofit;
    HttpApi api;

    public SearchInfoPresonter() {
        retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(HttpApi.class);
    }


    public static final String TAG = "SearchInfoPresonter";

    public void serach(String keyword,
                       int page,
                       String timestamp,
                       String sign,
                       String key) {
        final Call<SearchKeyWordBean> searchKeyWordBeanCall = api.searchInfo(keyword, page, timestamp, sign, key);

        searchKeyWordBeanCall.enqueue(new Callback<SearchKeyWordBean>() {
            @Override
            public void onResponse(Call<SearchKeyWordBean> call, Response<SearchKeyWordBean> response) {


                if (response.isSuccessful()) {

                    SearchKeyWordBean body = response.body();
                    if (searchKeyWordListener != null && body != null) {
                        searchKeyWordListener.onSearchSucess(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchKeyWordBean> call, Throwable t) {
            }
        });
    }


    SearchKeyWordListener searchKeyWordListener;

    public void setSearchHotWordListener(SearchKeyWordListener searchKeyWordListener) {
        this.searchKeyWordListener = searchKeyWordListener;
    }
}
