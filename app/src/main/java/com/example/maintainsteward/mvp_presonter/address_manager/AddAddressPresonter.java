package com.example.maintainsteward.mvp_presonter.address_manager;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.AddressBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/5.
 */

public class AddAddressPresonter {


    HttpApi httpApi;
    Retrofit retrofit;

    public AddAddressPresonter() {

        retrofit = new Retrofit.Builder().baseUrl(Contacts.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        httpApi = retrofit.create(HttpApi.class);
    }


    // http://wxtest.cnncsh.com/app_user/index/index/op/
    // AddAddress?
    // address=侧时代走
    // &city=3
    // &community=0
    // &district=35
    // &timestamp=1504083125000
    // &user_id=442
    // &user_name=测试
    // &user_phone=13545454545
    // &sign=3D647F1372B16E47849BE3A8F0C439DC
    // &key=idf5nsi5t0qbemwo12hztbftm53tbv6pht
    public void addAddress(String address, String city, String community, String district,
                           String timestamp, String user_id, String user_name, String user_phone,
                           String sign, String key) {


        Call<AddressBean> addressBeanCall = httpApi.addAddressCallBack(address, city, district, timestamp, user_id, user_name, user_phone, sign, key);
        addressBeanCall.enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {

                if (response.isSuccessful()) {

                    AddressBean body = response.body();

                    if (onAddAddressListener != null && body != null) {

                        onAddAddressListener.addAddressSucess(body);
                    }

                }

            }

            @Override
            public void onFailure(Call<AddressBean> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    OnAddAddressListener onAddAddressListener;

    public void setOnAddAddressListener(OnAddAddressListener onAddAddressListener) {
        this.onAddAddressListener = onAddAddressListener;
    }

    public interface OnAddAddressListener {

        void addAddressSucess(AddressBean body);

    }
}
