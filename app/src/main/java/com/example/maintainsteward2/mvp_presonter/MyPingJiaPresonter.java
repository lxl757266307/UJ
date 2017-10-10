package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.MyPingJiaListBean;
import com.example.maintainsteward2.mvp_view.OnMyPingJiaListListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/10.
 */

public class MyPingJiaPresonter {
    HttpApi httpApi;

    public MyPingJiaPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getMyEvaluate(
            String user_id,
            String page,
            String timestamp,
            String sign,
            String key
    ) {
        Call<MyPingJiaListBean> myEvaluate = httpApi.getMyEvaluate(user_id, page, timestamp, sign, key);
        myEvaluate.enqueue(new Callback<MyPingJiaListBean>() {
            @Override
            public void onResponse(Call<MyPingJiaListBean> call, Response<MyPingJiaListBean> response) {

                if (response.isSuccessful()) {

                    MyPingJiaListBean body = response.body();

                    if (onMyPingJiaListListener != null) {

                        onMyPingJiaListListener.getPingJiaList(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyPingJiaListBean> call, Throwable t) {

            }
        });

    }

    OnMyPingJiaListListener onMyPingJiaListListener;

    public void setOnMyPingJiaListListener(OnMyPingJiaListListener onMyPingJiaListListener) {
        this.onMyPingJiaListListener = onMyPingJiaListListener;
    }
}
