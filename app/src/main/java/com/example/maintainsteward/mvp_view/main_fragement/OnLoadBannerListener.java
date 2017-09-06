package com.example.maintainsteward.mvp_view.main_fragement;

import com.example.maintainsteward.bean.AppIndexCategoryBean;
import com.example.maintainsteward.bean.BannerBean;

/**
 * Created by Administrator on 2017/9/5.
 */

public interface OnLoadBannerListener {

    void onLoadBanner(BannerBean body);

    void onLoadAppIndexCategory(AppIndexCategoryBean bean);
}
