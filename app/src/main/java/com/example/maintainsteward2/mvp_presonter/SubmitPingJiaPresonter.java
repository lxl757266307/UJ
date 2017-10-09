package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.mvp_view.OnSubmitPingJiaListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/9.
 */

public class SubmitPingJiaPresonter {
    HttpApi httpApi;

    public SubmitPingJiaPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void submit(
            String id,
            String order_id,
            String worker_id,
            String star_level,
            String level1,
            String level2,
            String level3,
            String content,
            String img1,
            String img2,
            String img3,
            String timestamp,
            String sign,
            String key
    ) {

        Call<PublicBean> publicBeanCall = httpApi.orderEvaluate(id, order_id, worker_id, star_level, level1, level2, level3, content, img1, img2, img3, timestamp, sign, key);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {

                    PublicBean body = response.body();

                    if (onSubmitPingJiaListener != null) {
                        onSubmitPingJiaListener.submitPingJia(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });

    }

    OnSubmitPingJiaListener onSubmitPingJiaListener;

    public void setOnSubmitPingJiaListener(OnSubmitPingJiaListener onSubmitPingJiaListener) {
        this.onSubmitPingJiaListener = onSubmitPingJiaListener;
    }
}
