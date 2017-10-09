package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.SugestionTypeBean;
import com.example.maintainsteward2.mvp_view.OnCheckSugestionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/9.
 */

public class SugestionCheckTypePresonter {
    HttpApi httpApi;

    public SugestionCheckTypePresonter() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contacts.CHECK_FEED_BACK_TYPE).addConverterFactory(GsonConverterFactory.create()).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void checkType() {
        Call<SugestionTypeBean> feedback_type = httpApi.getFeedback_type();
        feedback_type.enqueue(new Callback<SugestionTypeBean>() {
            @Override
            public void onResponse(Call<SugestionTypeBean> call, Response<SugestionTypeBean> response) {

                if (response.isSuccessful()) {

                    SugestionTypeBean body = response.body();

                    if (onCheckSugestionListener != null) {
                        onCheckSugestionListener.getSugestionListener(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<SugestionTypeBean> call, Throwable t) {

            }
        });
    }


    OnCheckSugestionListener onCheckSugestionListener;

    public void setOnCheckSugestionListener(OnCheckSugestionListener onCheckSugestionListener) {
        this.onCheckSugestionListener = onCheckSugestionListener;
    }
}
