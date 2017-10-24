package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.bean.YongJinJiLuBean;
import com.example.maintainsteward2.mvp_view.OnGetYongJinStroryListener;
import com.example.maintainsteward2.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/16.
 */

public class TiXianPresonter {
    HttpApi httpApi;

    public TiXianPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getStrory(
            String user_id,
            String page,
            String timestamp,
            String sign,
            String key
    ) {
        Call<YongJinJiLuBean> myCommission = httpApi.getMyCommission(user_id, page, timestamp, sign, key);
        myCommission.enqueue(new Callback<YongJinJiLuBean>() {
            @Override
            public void onResponse(Call<YongJinJiLuBean> call, Response<YongJinJiLuBean> response) {

                if (response.isSuccessful()) {
                    YongJinJiLuBean body = response.body();
                    if (onGetYongJinStroryListener != null) {
                        onGetYongJinStroryListener.onGetTiXianStory(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<YongJinJiLuBean> call, Throwable t) {

            }
        });

    }

    public void updateUnionid(
            String user_id,
            String open_id,
            String unionid,
            String timestamp,
            String sign,
            String key
    ) {
        Call<PublicBean> publicBeanCall = httpApi.updateOpendId(user_id, open_id, unionid, timestamp, sign, key);

        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {
                    PublicBean body = response.body();
                    if (onGetYongJinStroryListener != null) {

                        onGetYongJinStroryListener.onUpdateUnionidScucess(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });
    }


    public void tiXian(
            String user_id,
            String out_money,
            String timestamp,
            String sign,
            String key
    ) {
        Call<PublicBean> publicBeanCall = httpApi.extensionWithdrawals(user_id, out_money, timestamp, sign, key);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                if (response.isSuccessful()) {

                    PublicBean body = response.body();
                    if (onGetYongJinStroryListener != null) {
                        onGetYongJinStroryListener.onTiXianScucess(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });

    }


    OnGetYongJinStroryListener onGetYongJinStroryListener;

    public void setOnGetYongJinStroryListener(OnGetYongJinStroryListener onGetYongJinStroryListener) {
        this.onGetYongJinStroryListener = onGetYongJinStroryListener;
    }
}
