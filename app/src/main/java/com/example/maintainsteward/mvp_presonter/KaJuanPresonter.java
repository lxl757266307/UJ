package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.KaJuanBean;
import com.example.maintainsteward.bean.KaJuanCountBean;
import com.example.maintainsteward.mvp_view.KaJuanListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/21.
 */

public class KaJuanPresonter {

    HttpApi httpApi;

    public KaJuanPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public static final String TAG = "KaJuanPresonter";

    public void getKaJuan(String id,
                          String type,
                          String page,
                          String timestamp,
                          String sign,
                          String key) {
        kaJuanListener.showDialog();

        Call<KaJuanBean> kaJuanBeanCall = httpApi.myCoupons(id, type, page, timestamp, sign, key);
        kaJuanBeanCall.enqueue(new Callback<KaJuanBean>() {
            @Override
            public void onResponse(Call<KaJuanBean> call, Response<KaJuanBean> response) {


                if (response.isSuccessful()) {

                    KaJuanBean body = response.body();
                    if (kaJuanListener != null) {
                        kaJuanListener.onGetKaJuanSucess(body);
                        kaJuanListener.hideDialog();
                    }

                }
            }

            @Override
            public void onFailure(Call<KaJuanBean> call, Throwable t) {
                kaJuanListener.hideDialog();
            }
        });

    }

    public void getCount(String id,
                         String timestamp,
                         String sign,
                         String key) {

        kaJuanListener.showDialog();

        Call<KaJuanCountBean> kaJuanCountBeanCall = httpApi.myCouponsCount(id, timestamp, sign, key);
        kaJuanCountBeanCall.enqueue(new Callback<KaJuanCountBean>() {
            @Override
            public void onResponse(Call<KaJuanCountBean> call, Response<KaJuanCountBean> response) {

                if (response.isSuccessful()) {

                    KaJuanCountBean body = response.body();
                    if (kaJuanListener != null) {
                        kaJuanListener.onGetKaJuanCountSucess(body);
                        kaJuanListener.hideDialog();
                    }


                }
            }

            @Override
            public void onFailure(Call<KaJuanCountBean> call, Throwable t) {
                kaJuanListener.hideDialog();
            }
        });


    }

    KaJuanListener kaJuanListener;

    public void setKaJuanListener(KaJuanListener kaJuanListener) {
        this.kaJuanListener = kaJuanListener;
    }
}
