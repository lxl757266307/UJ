package com.example.maintainsteward.mvp_view.login;

import com.example.maintainsteward.bean.User;

/**
 * Created by Administrator on 2017/8/5.
 * <p>
 * 登录API
 */

public interface LoginView {

    String getUserPhone();

    String getPassword();

    void clearUserPhone();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
