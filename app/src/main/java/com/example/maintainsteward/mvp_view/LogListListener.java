package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.LogListBean;

/**
 * Created by Administrator on 2017/9/21.
 */

public interface LogListListener {

    void showDilog();

    void hideDialog();

    void OnGetLogListSucess(LogListBean bean);
}
