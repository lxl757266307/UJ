package com.example.maintainsteward2.mvp_presonter;

import android.content.Context;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.HongBaoBean;
import com.example.maintainsteward2.mvp_view.OnGetChooseHongBaoListener;
import com.example.maintainsteward2.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/25.
 */

public class ChooseHongBaoPresonter {


    HttpApi httpApi;

    public ChooseHongBaoPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getHongBaoList(
            String user_id,
            String service_money,
            String timestamp,
            String sign,
            String key
    ) {

        Call<HongBaoBean> hongBaoBeanCall = httpApi.chooseHongBao(user_id, service_money, timestamp, sign, key);
        hongBaoBeanCall.enqueue(new Callback<HongBaoBean>() {
            @Override
            public void onResponse(Call<HongBaoBean> call, Response<HongBaoBean> response) {

                ToolUitls.print("-----------", "response===" + response + "       body==" + response.body());
                if (response.isSuccessful()) {

                    HongBaoBean body = response.body();

                    if (onGetChooseHongBaoListener != null) {
                        onGetChooseHongBaoListener.getHongBaoListener(body);
                    }

                }


            }

            @Override
            public void onFailure(Call<HongBaoBean> call, Throwable t) {

            }
        });

    }

    OnGetChooseHongBaoListener onGetChooseHongBaoListener;

    public void setOnGetChooseHongBaoListener(OnGetChooseHongBaoListener onGetChooseHongBaoListener) {
        this.onGetChooseHongBaoListener = onGetChooseHongBaoListener;
    }
}
