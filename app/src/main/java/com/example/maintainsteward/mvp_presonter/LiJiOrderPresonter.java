package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.AddressDeleteBean;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/9/16.
 */

public class LiJiOrderPresonter {
    HttpApi api;

    public LiJiOrderPresonter() {
        api = BaseHttpApi.getInstanceof();

    }

    public void order(String id,
                      String cat_id,
                      String name,
                      String address_id,
                      String service_con,
                      String material,
                      String content,
                      String img1,
                      String img2,
                      String img3,
                      String img4,
                      String img5,
                      String img6,
                      String order_time,
                      String timestamp,
                      String sign,
                      String key) {


        api.serviceOrderSubmit(id, cat_id, name, address_id, service_con, material, content, img1, img2, img3, img4, img5, img6, order_time, timestamp, sign, key);


    }
}
