package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.UpLoadPhotoCallBackBean;

/**
 * Created by Administrator on 2017/9/16.
 */

public interface OnUpLoadPhotoListener {


    void getToken(String token);

    void onUpSucess(String key);
}
