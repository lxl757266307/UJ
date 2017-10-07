package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.FensiBean;
import com.example.maintainsteward2.mvp_view.OnFenSiListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/7.
 */

public class FenSiPresonter {
    HttpApi httpApi;

    public FenSiPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getFenSiBean(String id,
                             String type,
                             String page,
                             String timestamp,
                             String sign,
                             String key) {
        Call<FensiBean> myFenSi = httpApi.getMyFenSi(id, type, page, timestamp, sign, key);

        myFenSi.enqueue(new Callback<FensiBean>() {
            @Override
            public void onResponse(Call<FensiBean> call, Response<FensiBean> response) {
                if (response.isSuccessful()) {

                    FensiBean body = response.body();
                    if (onFenSiListener != null) {
                        onFenSiListener.getFenSiBean(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<FensiBean> call, Throwable t) {

            }
        });

    }

    OnFenSiListener onFenSiListener;

    public void setOnFenSiListener(OnFenSiListener onFenSiListener) {
        this.onFenSiListener = onFenSiListener;
    }
}
