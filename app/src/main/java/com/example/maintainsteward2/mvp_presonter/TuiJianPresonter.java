package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.BannerBean;
import com.example.maintainsteward2.bean.TuiJianBean;
import com.example.maintainsteward2.mvp_view.OnTuiJianInfoListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/7.
 */

public class TuiJianPresonter {

    HttpApi httpApi;

    public TuiJianPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getTuiJianBean(String id,
                               String timestamp,
                               String sign,
                               String key) {
        Call<TuiJianBean> myExtendInfo = httpApi.getMyExtendInfo(id, timestamp, sign, key);
        myExtendInfo.enqueue(new Callback<TuiJianBean>() {
            @Override
            public void onResponse(Call<TuiJianBean> call, Response<TuiJianBean> response) {

                if (response.isSuccessful()) {

                    TuiJianBean body = response.body();

                    if (onTuiJianInfoListener != null) {
                        onTuiJianInfoListener.getTuiJianInfo(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<TuiJianBean> call, Throwable t) {

            }
        });


    }

    OnTuiJianInfoListener onTuiJianInfoListener;

    public void setOnTuiJianInfoListener(OnTuiJianInfoListener onTuiJianInfoListener) {
        this.onTuiJianInfoListener = onTuiJianInfoListener;
    }
}
