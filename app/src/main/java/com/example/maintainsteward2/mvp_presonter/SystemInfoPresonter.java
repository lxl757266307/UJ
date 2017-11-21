package com.example.maintainsteward2.mvp_presonter;

import com.example.maintainsteward2.api.HttpApi;
import com.example.maintainsteward2.base.BaseHttpApi;
import com.example.maintainsteward2.bean.SystemInfoBean;
import com.example.maintainsteward2.mvp_view.SystemInfoView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/11/21.
 */

public class SystemInfoPresonter {


    HttpApi httpApi;

    public SystemInfoPresonter() {

        httpApi = BaseHttpApi.getInstanceof();

    }


    public void getSystemInfo(
            String user_id,
            String page,
            String timestamp,
            String sign,
            String key
    ) {

        Call<SystemInfoBean> userMsgList = httpApi.getUserMsgList(user_id, page, timestamp, sign, key);
        userMsgList.enqueue(new Callback<SystemInfoBean>() {
            @Override
            public void onResponse(Call<SystemInfoBean> call, Response<SystemInfoBean> response) {

                if (response.isSuccessful()) {
                    SystemInfoBean body = response.body();

                    systemInfoView.getUserMsgList(body);

                }

            }

            @Override
            public void onFailure(Call<SystemInfoBean> call, Throwable t) {

            }
        });


    }

    SystemInfoView systemInfoView;

    public void setSystemInfoView(SystemInfoView systemInfoView) {
        this.systemInfoView = systemInfoView;
    }
}
