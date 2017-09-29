package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.mvp_view.KuaiSuFaBuListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/28.
 */

public class KuaiSuFaBuPresonter {
    HttpApi httpApi;

    public KuaiSuFaBuPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void kuaiSuFaBu(
            String id,
            String service_des,
            String img1,
            String img2,
            String img3,
            String img4,
            String img5,
            String img6,
            String add_time,
            String timestamp,
            String sign,
            String key
    ) {

        Call<PublicBean> publicBeanCall = httpApi.serviceOrderFastSubmit(id, service_des, img1, img2, img3, img4, img5, img6, add_time, timestamp, sign, key);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {


                if (response.isSuccessful()) {

                    PublicBean body = response.body();

                    if (kuaiSuFaBuListener != null) {

                        kuaiSuFaBuListener.onFaBuSucess(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });
    }

    KuaiSuFaBuListener kuaiSuFaBuListener;

    public void setKuaiSuFaBuListener(KuaiSuFaBuListener kuaiSuFaBuListener) {
        this.kuaiSuFaBuListener = kuaiSuFaBuListener;
    }
}
