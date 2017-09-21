package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.KaJuanBean;
import com.example.maintainsteward.bean.KaJuanCountBean;

/**
 * Created by Administrator on 2017/9/21.
 */

public interface KaJuanListener {

    void showDialog();

    void hideDialog();

    void onGetKaJuanSucess(KaJuanBean bean);

    void onGetKaJuanCountSucess(KaJuanCountBean bean);
}
