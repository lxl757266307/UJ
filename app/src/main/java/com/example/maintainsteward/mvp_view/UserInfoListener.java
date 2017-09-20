package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.bean.UserInfoBean;

/**
 * Created by Administrator on 2017/9/20.
 */

public interface UserInfoListener {
    void getUserInfo(UserInfoBean bean);

    void editUserInfoSucess(PublicBean bean);
}
