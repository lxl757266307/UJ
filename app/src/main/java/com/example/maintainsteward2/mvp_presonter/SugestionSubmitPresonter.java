package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.bean.SugestionTypeBean;
import com.example.maintainsteward2.mvp_view.OnSubmitSugestionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/9.
 */

public class SugestionSubmitPresonter {
    HttpApi httpApi;

    public SugestionSubmitPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void submit(
            String id,
            String type_id,
            String contents,
            String type,
            String img1,
            String img2,
            String img3,
            String timeStamp,
            String sign,
            String key
    ) {

        Call<PublicBean> feedback = httpApi.feedback(id, type_id, contents, type, img1, img2, img3,timeStamp,sign,key);
        feedback.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {
                    PublicBean body = response.body();
                    if (submitSugestionListener != null) {
                        submitSugestionListener.submitSugestionCallBack(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });
    }

    OnSubmitSugestionListener submitSugestionListener;

    public void setSubmitSugestionListener(OnSubmitSugestionListener submitSugestionListener) {
        this.submitSugestionListener = submitSugestionListener;
    }
}
