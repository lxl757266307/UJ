package com.example.maintainsteward.mvp_view;

import com.example.maintainsteward.bean.SearviceInfoBean;
import com.example.maintainsteward.bean.SecondKindsContent;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;

/**
 * Created by Administrator on 2017/9/12.
 */

public interface ServiceInfoListener {

    void getServiceAll(SearviceInfoBean bean);

    void getSecondKindsContent(SecondKindsContent bean);

    void getServiceGoodsGet(ServiceGoodsGetBean bean);

}
