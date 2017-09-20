package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.bean.UserInfoBean;
import com.example.maintainsteward.mvp_view.UserInfoListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/9/20.
 */

public class UserInfoPresonter {

    HttpApi httpApi;

    public UserInfoPresonter() {

        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getUserInfo(String user_id,
                            String timestamp,
                            String sign,
                            String key) {
        Call<UserInfoBean> userInfos = httpApi.getUserInfos(user_id, timestamp, sign, key);
        userInfos.enqueue(new Callback<UserInfoBean>() {
            @Override
            public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {

                if (response.isSuccessful()) {
                    UserInfoBean body = response.body();

                    if (userInfoListener != null) {

                        userInfoListener.getUserInfo(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<UserInfoBean> call, Throwable t) {

            }
        });

    }


    public void updateUserInfo(String user_id,
                               String type,
                               String values,
                               String timestamp,
                               String sign,
                               String key) {


        Call<PublicBean> userInfoBeanCall = httpApi.goUpdateUserInfo(user_id, type, values, timestamp, sign, key);

        userInfoBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {
                if (response.isSuccessful()) {

                    PublicBean body = response.body();

                    if (userInfoListener != null) {
                        userInfoListener.editUserInfoSucess(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });

    }


    public void updateUserPhone(String id,
                                String phone,
                                String timestamp,
                                String sign,
                                String key) {
        Call<PublicBean> publicBeanCall = httpApi.bindNewPhone(id, phone, timestamp, sign, key);
        publicBeanCall.enqueue(new Callback<PublicBean>() {
            @Override
            public void onResponse(Call<PublicBean> call, Response<PublicBean> response) {
                if (response.isSuccessful()) {

                    PublicBean body = response.body();

                    if (userInfoListener != null) {
                        userInfoListener.editUserInfoSucess(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<PublicBean> call, Throwable t) {

            }
        });

    }

    public void setUserInfoListener(UserInfoListener userInfoListener) {
        this.userInfoListener = userInfoListener;
    }

    UserInfoListener userInfoListener;
}
