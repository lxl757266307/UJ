package com.example.maintainsteward2.mvp_view;

import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.bean.YongJinJiLuBean;

/**
 * Created by Administrator on 2017/10/16.
 */

public interface OnGetYongJinStroryListener {
    void onGetTiXianStory(YongJinJiLuBean yongJinJiLuBean);

    void onUpdateUnionidScucess(PublicBean publicBean);

    void onTiXianScucess(PublicBean bean);
}
