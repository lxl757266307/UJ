package com.example.maintainsteward.mvp_presonter;


import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.LoginCallBackBean;
import com.example.maintainsteward.bean.YanZhengMaCallBackBean;
import com.example.maintainsteward.mvp_view.LoginListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/8/5.
 */

public class LoginPresonter {
    public static final String TAG = "LoginPresonter";

    Retrofit retrofit;

    HttpApi api;

    public LoginPresonter() {

        retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(HttpApi.class);
    }

    /*获取验证码*/
    public void getCode(String phone, String type, String timestamp, String sign, String key) {
        Call<YanZhengMaCallBackBean> verifyCode = api.getVerifyCode(phone, type, timestamp, sign, key);
        verifyCode.enqueue(new Callback<YanZhengMaCallBackBean>() {
            @Override
            public void onResponse(Call<YanZhengMaCallBackBean> call, Response<YanZhengMaCallBackBean> response) {
                if (response.isSuccessful()) {

                    YanZhengMaCallBackBean body = response.body();
                    if (loginListener != null && body != null) {
                        loginListener.getVerifyCode(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<YanZhengMaCallBackBean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /*登录*/
    public void login(String phone,
                      String code,
                      String chinaid,
                      String ioschinaid,
                      String login_type,
                      String timeStamp,
                      String sign,
                      String key) {


        Call<LoginCallBackBean> loginCallBackBeanCall = api.goLoginByCode(phone, code, chinaid, ioschinaid, login_type, timeStamp, sign, key);

        loginCallBackBeanCall.enqueue(new Callback<LoginCallBackBean>() {
            @Override
            public void onResponse(Call<LoginCallBackBean> call, Response<LoginCallBackBean> response) {
                if (response.isSuccessful()) {

                    LoginCallBackBean body = response.body();
                    if (loginListener != null && body != null) {
                        loginListener.getLoginCallBack(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginCallBackBean> call, Throwable t) {

            }
        });

    }

    LoginListener loginListener;

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
}
