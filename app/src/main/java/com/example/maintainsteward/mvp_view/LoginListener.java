package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.LoginCallBackBean;
import com.example.maintainsteward.bean.User;
import com.example.maintainsteward.bean.YanZhengMaCallBackBean;

/**
 * Created by Administrator on 2017/8/5.
 * <p>
 * 登录API
 */

public interface LoginListener {

    void getVerifyCode(YanZhengMaCallBackBean backBean);

    void getLoginCallBack(LoginCallBackBean body);
}
