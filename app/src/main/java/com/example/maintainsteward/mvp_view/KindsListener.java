package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.FirstKindsBean;
import com.example.maintainsteward.bean.SecondKindsBean;

/**
 * Created by Administrator on 2017/9/11.
 */

public interface KindsListener {
    void getFirstKinds(FirstKindsBean bean);

    void getSecondKinds(SecondKindsBean bean);
}
