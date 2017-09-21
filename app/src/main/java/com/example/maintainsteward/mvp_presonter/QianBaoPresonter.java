package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.LogListBean;
import com.example.maintainsteward.mvp_view.LogListListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/21.
 */

public class QianBaoPresonter {

    HttpApi httpApi;

    public QianBaoPresonter() {

        httpApi = BaseHttpApi.getInstanceof();

    }

    public void getMyCouponsCount(String id,
                                  String timestamp,
                                  String sign,
                                  String key) {

    }

    public void getMyWalletBalance(String id,
                                   String type,
                                   String page,
                                   String timestamp,
                                   String sign,
                                   String key) {
        logListListener.showDilog();

        Call<LogListBean> logListBeanCall = httpApi.myWalletBalance(id, type, page, timestamp, sign, key);

        logListBeanCall.enqueue(new Callback<LogListBean>() {
            @Override
            public void onResponse(Call<LogListBean> call, Response<LogListBean> response) {
                if (response.isSuccessful()) {

                    LogListBean body = response.body();

                    if (logListListener != null) {

                        logListListener.OnGetLogListSucess(body);
                        logListListener.hideDialog();
                    }
                }

            }

            @Override
            public void onFailure(Call<LogListBean> call, Throwable t) {
                logListListener.hideDialog();
            }
        });

    }


    LogListListener logListListener;

    public void setLogListListener(LogListListener logListListener) {
        this.logListListener = logListListener;
    }
}
