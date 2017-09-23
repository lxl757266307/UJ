package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.BannerBean;
import com.example.maintainsteward.bean.JingXuanBean;
import com.example.maintainsteward.mvp_view.JingXuanListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/23.
 */

public class JingXuanPresonter {
    HttpApi httpApi;

    public JingXuanPresonter() {

        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getJingXuan(String page,
                            String timestamp,
                            String sign,
                            String key) {
        Call<JingXuanBean> carefullySelectedSort = httpApi.getCarefullySelectedSort(page, timestamp, sign, key);
        carefullySelectedSort.enqueue(new Callback<JingXuanBean>() {
            @Override
            public void onResponse(Call<JingXuanBean> call, Response<JingXuanBean> response) {

                if (response.isSuccessful()) {
                    JingXuanBean body = response.body();

                    if (jingXuanListener != null) {
                        jingXuanListener.getJingXuan(body);
                    }


                }
            }

            @Override
            public void onFailure(Call<JingXuanBean> call, Throwable t) {

            }
        });

    }

    JingXuanListener jingXuanListener;

    public void setJingXuanListener(JingXuanListener jingXuanListener) {
        this.jingXuanListener = jingXuanListener;
    }
}
