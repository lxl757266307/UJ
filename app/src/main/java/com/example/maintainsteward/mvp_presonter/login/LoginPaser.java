package com.example.maintainsteward.mvp_presonter.login;

import com.example.maintainsteward.mvp_view.login.login.LoginIPaser;
import com.example.maintainsteward.mvp_view.login.login.OnLoginListener;

/**
 * Created by Administrator on 2017/8/5.
 */

public class LoginPaser implements LoginIPaser {



    /* 在此登录*/
    @Override
    public void login(String phone, String password, OnLoginListener onLoginListener) {
        if (phone.equals("12346")&&password.equals("123456")) {
            onLoginListener.sucess();
        }else{
            onLoginListener.failed();
        }
    }
}
