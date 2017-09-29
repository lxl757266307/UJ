package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.bean.AddressDeleteBean;
import com.example.maintainsteward.bean.AddressListBean;
import com.example.maintainsteward.mvp_view.GetAddressListListener;
import com.example.maintainsteward.utils.ToolUitls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/15.
 */

public class AddressManagerPresonter {
    HttpApi httpApi;

    public AddressManagerPresonter() {
        httpApi = BaseHttpApi.getInstanceof();
    }

    public void getAddressList(String id, String page, String timestamp, String sign, String key) {

        Call<AddressListBean> address = httpApi.getAddress(id, page, timestamp, sign, key);

        address.enqueue(new Callback<AddressListBean>() {
            @Override
            public void onResponse(Call<AddressListBean> call, Response<AddressListBean> response) {

                if (response.isSuccessful()) {
                    AddressListBean body = response.body();

                    if (body != null && listListener != null) {
                        listListener.getAddressList(body);
                    }


                }

            }

            @Override
            public void onFailure(Call<AddressListBean> call, Throwable t) {

            }
        });


    }


    public void deleteAddress(String id, String timeStamp, String sign, String key, final int position) {
        Call<AddressDeleteBean> addressDeleteBeanCall = httpApi.deleteAddress(id, timeStamp, sign, key);
        addressDeleteBeanCall.enqueue(new Callback<AddressDeleteBean>() {
            @Override
            public void onResponse(Call<AddressDeleteBean> call, Response<AddressDeleteBean> response) {
                if (response.isSuccessful()) {

                    AddressDeleteBean body = response.body();

                    if (listListener != null && body != null) {
                        listListener.deleteAddressSucess(body,position);
                    }

                }

            }

            @Override
            public void onFailure(Call<AddressDeleteBean> call, Throwable t) {

            }
        });


    }


    GetAddressListListener listListener;

    public void setListListener(GetAddressListListener listListener) {
        this.listListener = listListener;
    }
}
