package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.mvp_view.ChooseLocationListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ChooseLocationPresonter {

    public static final String TAG = "ChooseLocationPresonter";

    HttpApi api;

    public ChooseLocationPresonter() {
        api = BaseHttpApi.getInstanceof();
    }

    public void getCityList(String timestamp, String sign, String key) {

        Call<CityListBean> cityList = api.getCityList(timestamp, sign, key);

        cityList.enqueue(new Callback<CityListBean>() {
            @Override
            public void onResponse(Call<CityListBean> call, Response<CityListBean> response) {

                ToolUitls.print(TAG, "response==" + response);

                if (response.isSuccessful()) {

                    CityListBean body = response.body();
                    if (chooseLocationListener != null && body != null) {
                        chooseLocationListener.getCityList(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<CityListBean> call, Throwable t) {

            }
        });


    }

    ChooseLocationListener chooseLocationListener;

    public void setChooseLocationListener(ChooseLocationListener chooseLocationListener) {
        this.chooseLocationListener = chooseLocationListener;
    }
}
