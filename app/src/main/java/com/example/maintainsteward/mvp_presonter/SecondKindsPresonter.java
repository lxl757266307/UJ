package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.SecondKindsBean;
import com.example.maintainsteward.mvp_view.KindsListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/11.
 */

public class SecondKindsPresonter {

    public static final String TAG = "SecondKindsPresonter";
    Retrofit retrofit;
    HttpApi httpApi;

    public SecondKindsPresonter() {
        retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void getSecondKinds(String id, String time, String sign, String key) {
        Call<SecondKindsBean> secondKinds = httpApi.getSecondKinds(id, time, sign, key);
        secondKinds.enqueue(new Callback<SecondKindsBean>() {
            @Override
            public void onResponse(Call<SecondKindsBean> call, Response<SecondKindsBean> response) {

                if (response.isSuccessful()) {

                    SecondKindsBean body = response.body();

                    if (kindsListener != null && body != null) {
                        kindsListener.getSecondKinds(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<SecondKindsBean> call, Throwable t) {

            }
        });

    }

    KindsListener kindsListener;

    public void setFirstKindsListener(KindsListener firstKindsListener) {
        this.kindsListener = firstKindsListener;
    }
}
