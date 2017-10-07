package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.FensiBean;
import com.example.maintainsteward2.bean.NextFenSiBean;
import com.example.maintainsteward2.mvp_view.OnNextFenSiListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/7.
 */

public class NextFenSiPresonter {
    HttpApi httpApi;

    public NextFenSiPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getNextFenSiBean() {

    }

    public void getFenSiBean(String id,
                             String type,
                             String page,
                             String timestamp,
                             String sign,
                             String key) {
        Call<NextFenSiBean> myFenSi = httpApi.getNextMyFenSi(id, type, page, timestamp, sign, key);

        myFenSi.enqueue(new Callback<NextFenSiBean>() {
            @Override
            public void onResponse(Call<NextFenSiBean> call, Response<NextFenSiBean> response) {
                if (response.isSuccessful()) {

                    NextFenSiBean body = response.body();
                    if (onNextFenSiListener != null) {

                        onNextFenSiListener.getNextFenSiBean(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<NextFenSiBean> call, Throwable t) {

            }
        });

    }

    OnNextFenSiListener onNextFenSiListener;

    public void setOnNextFenSiListener(OnNextFenSiListener onNextFenSiListener) {
        this.onNextFenSiListener = onNextFenSiListener;
    }
}
