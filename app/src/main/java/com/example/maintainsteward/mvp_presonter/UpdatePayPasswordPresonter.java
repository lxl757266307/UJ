package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.mvp_view.OnUpdatePayPasswordListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/20.
 */

public class UpdatePayPasswordPresonter {
    HttpApi httpApi;

    public UpdatePayPasswordPresonter() {

        httpApi = BaseHttpApi.getInstanceof();
    }


    public void setPayPassword(String id,
                               String phone,
                               String pwd,
                               String type,
                               String timestamp,
                               String sign,
                               String key) {

        Call<PublicBean> publicBeanCall = httpApi.goSetPayPassword(id, phone, pwd, type, timestamp, sign, key);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {

                    PublicBean body = response.body();
                    if (onUpdatePayPasswordListener != null) {

                        onUpdatePayPasswordListener.onSetPasswordSucess(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });

    }

    public void updatePayPassword(String id,
                                  String phone,
                                  String newpwd,
                                  String oldpwd,
                                  String timestamp,
                                  String sign,
                                  String key) {

        Call<PublicBean> publicBeanCall = httpApi.goUpdatePayPassword(id, phone, newpwd, oldpwd, timestamp, sign, key);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {

                    PublicBean body = response.body();
                    if (onUpdatePayPasswordListener != null) {

                        onUpdatePayPasswordListener.onUpdatePasswordSucess(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });

    }

    OnUpdatePayPasswordListener onUpdatePayPasswordListener;

    public void setOnUpdatePayPasswordListener(OnUpdatePayPasswordListener onUpdatePayPasswordListener) {
        this.onUpdatePayPasswordListener = onUpdatePayPasswordListener;
    }
}
