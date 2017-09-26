package com.example.maintainsteward.mvp_presonter;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.AddressBean;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.utils.ToolUitls;

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


    public static final String TAG = "AddAddressPresonter";

    /*添加地址*/
    public void addAddress(String address, String city, String district,
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

    /*获取城市列表*/
    public void getCityList(String timestamp, String sign, String key) {

        Call<CityListBean> cityList = httpApi.getCityList(timestamp, sign, key);
        cityList.enqueue(new Callback<CityListBean>() {
            @Override
            public void onResponse(Call<CityListBean> call, Response<CityListBean> response) {

                if (response.isSuccessful()) {

                    CityListBean body = response.body();

                    if (onAddAddressListener != null && body != null) {
                        onAddAddressListener.getCityLists(body);
                    }

                }
            }

            @Override
            public void onFailure(Call<CityListBean> call, Throwable t) {

            }
        });
    }


    OnAddAddressListener onAddAddressListener;

    public void setOnAddAddressListener(OnAddAddressListener onAddAddressListener) {
        this.onAddAddressListener = onAddAddressListener;
    }

    public interface OnAddAddressListener {

        void addAddressSucess(AddressBean body);

        void getCityLists(CityListBean body);

    }
}
