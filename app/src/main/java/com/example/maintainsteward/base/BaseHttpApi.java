package com.example.maintainsteward.base;

import com.example.maintainsteward.api.HttpApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/11.
 */

public class BaseHttpApi {

    private static HttpApi httpApi;
    private static Retrofit retrofit;

    public static synchronized HttpApi getInstanceof() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        if (httpApi == null) {
            return httpApi = retrofit.create(HttpApi.class);
        }
        return httpApi;
    }
}
