package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.TaoCallBackBean;
import com.example.maintainsteward.mvp_view.TaoCanPayLitener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/27.
 */

public class TaoCanPayPresonter {

    HttpApi httpApi;

    public TaoCanPayPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getTaoCanOrderNo(
            String user_id,
            String id,
            String paytype,
            String city,
            String district,
            String address,
            String msg,
            String tel,
            String timestamp,
            String sign,
            String key

    ) {
        Call<TaoCallBackBean> setMealBuyOrder = httpApi.getSetMealBuyOrder(user_id, id, paytype, city, district, address, msg, tel, timestamp, sign, key);

        setMealBuyOrder.enqueue(new Callback<TaoCallBackBean>() {
            @Override
            public void onResponse(Call<TaoCallBackBean> call, Response<TaoCallBackBean> response) {
                if (response.isSuccessful()) {

                    if (taoCanPayLitener != null) {
                        taoCanPayLitener.orderTaoCan(response.body());
                    }

                }
            }

            @Override
            public void onFailure(Call<TaoCallBackBean> call, Throwable t) {

            }
        });
    }

    TaoCanPayLitener taoCanPayLitener;

    public void setTaoCanPayLitener(TaoCanPayLitener taoCanPayLitener) {
        this.taoCanPayLitener = taoCanPayLitener;
    }
}
