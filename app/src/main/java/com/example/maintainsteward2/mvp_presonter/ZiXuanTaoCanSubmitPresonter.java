package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.mvp_view.OnOrderZiXuanTaoCanListener;
import com.example.maintainsteward2.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/10/8.
 */

public class ZiXuanTaoCanSubmitPresonter {

    HttpApi httpApi;

    public ZiXuanTaoCanSubmitPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void orderNow(
            String user_id,
            String address_id,
            String order_time,
            String service_item,
            String counts,
            String discount,
            String total_price,
            String final_price,
            String details,
            String img1,
            String img2,
            String img3,
            String img4,
            String img5,
            String img6,
            String timestamp,
            String sign,
            String key
    ) {


        Call<PublicBean> publicBeanCall = httpApi.orderSelfServiceBuy(user_id, address_id, order_time, service_item, counts, discount, total_price, final_price, details, img1, img2, img3, img4, img5, img6
                , timestamp, sign, key);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {

                ToolUitls.print("response","response=="+response+" boyd=="+response.body());
                if (response.isSuccessful()) {

                    PublicBean body = response.body();

                    if (onOrderZiXuanTaoCanListener != null) {

                        onOrderZiXuanTaoCanListener.orderSucess(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });

    }

    OnOrderZiXuanTaoCanListener onOrderZiXuanTaoCanListener;

    public void setOnOrderZiXuanTaoCanListener(OnOrderZiXuanTaoCanListener onOrderZiXuanTaoCanListener) {
        this.onOrderZiXuanTaoCanListener = onOrderZiXuanTaoCanListener;
    }
}
