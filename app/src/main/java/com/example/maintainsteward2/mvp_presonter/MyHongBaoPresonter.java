package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.MyHongBaoListBean;
import com.example.maintainsteward2.mvp_view.MyHongBaoListListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/24.
 */

public class MyHongBaoPresonter {
    HttpApi httpApi;


    public MyHongBaoPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }


    public void getHongBaoList(
            String user_id,
            String page,
            String timestamp,
            String sign,
            String key
    ) {

        Call<MyHongBaoListBean> myLuckMoney = httpApi.getMyLuckMoney(user_id, page, timestamp, sign, key);
        myLuckMoney.enqueue(new Callback<MyHongBaoListBean>() {
            @Override
            public void onResponse(Call<MyHongBaoListBean> call, Response<MyHongBaoListBean> response) {

                if (response.isSuccessful()) {

                    MyHongBaoListBean body = response.body();
                    if (myHongBaoListListener != null) {
                        myHongBaoListListener.getHongBaoListListtener(body);
                    }


                }
            }

            @Override
            public void onFailure(Call<MyHongBaoListBean> call, Throwable t) {

            }
        });

    }

    MyHongBaoListListener myHongBaoListListener;

    public void setMyHongBaoListListener(MyHongBaoListListener myHongBaoListListener) {
        this.myHongBaoListListener = myHongBaoListListener;
    }
}
