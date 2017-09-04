package com.example.maintainsteward.mvp_presonter.login;

import com.example.maintainsteward.mvp_view.login.login.LoginIPaser;
import com.example.maintainsteward.mvp_view.login.login.LoginView;
import com.example.maintainsteward.mvp_view.login.login.OnLoginListener;

/**
 * Created by Administrator on 2017/8/5.
 */

public class LoginPresonter  {

    LoginView loginView;
    LoginIPaser loginIPaser;

    public LoginPresonter(LoginView loginView) {
        this.loginView = loginView;
        loginIPaser = new LoginPaser();
    }


    public void login() {
        new LoginIPaser() {
            @Override
            public void login(String phone, String password, OnLoginListener onLoginListener) {

            }
        }.login("", "", new OnLoginListener() {
            @Override
            public void sucess() {

            }

            @Override
            public void failed() {

            }
        });
    }

}
