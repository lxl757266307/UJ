package com.example.maintainsteward.api;

import com.example.maintainsteward.bean.AddressBean;
import com.example.maintainsteward.bean.BannerBean;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.bean.AppIndexCategoryBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/8/4.
 */

public interface HttpApi {


    /*添加地址*/
    @FormUrlEncoded
    @POST("AddAddress")
    Call<AddressBean> addAddressCallBack(@Field("address") String address,
                                         @Field("city") String city,
                                         @Field("district") String district,
                                         @Field("timestamp") String timestamp,
                                         @Field("user_id") String user_id,
                                         @Field("user_name") String user_name,
                                         @Field("user_phone") String user_phone,
                                         @Field("sign") String sign,
                                         @Field("key") String key
    );


    /*获取主页面的banner 图*/
    @FormUrlEncoded
    @POST("AppIndexData")
    Call<BannerBean> getBanner(@Field("timestamp") String timestamp,
                               @Field("sign") String sign,
                               @Field("key") String key);

    /*获取主页面分类*/
    @FormUrlEncoded
    @POST("AppIndexCategory")
    Call<AppIndexCategoryBean> getAppIndexCategory(@Field("timestamp") String timestamp,
                                                   @Field("sign") String sign,
                                                   @Field("key") String key,
                                                   @Field("page") int page);


    /*获取城市列表*/
    @FormUrlEncoded
    @POST("CityListsGet")
    Call<CityListBean> getCityList(@Field("timestamp") String timestamp,
                                   @Field("sign") String sign,
                                   @Field("key") String key
    );

    /*删除地址*/
    @FormUrlEncoded
    @POST("DelAddress")
    Call<BannerBean> deleteAddress(@Field("timestamp") String timestamp,
                                   @Field("sign") String sign,
                                   @Field("key") String key,
                                   @Field("id") int id
    );

    /*我的粉丝*/
    @FormUrlEncoded
    @POST("ExtendOrderDetails")
    Call<BannerBean> getMyFenSi(@Field("timestamp") String timestamp,
                                @Field("sign") String sign,
                                @Field("key") String key,
                                @Field("m_id") int id,
                                @Field("type") int type,
                                @Field("page") int page
    );


    /*我的下级粉丝*/
    @FormUrlEncoded
    @POST("ExtendOrderDetails")
    Call<BannerBean> getMyXiaJiFenSi(@Field("timestamp") String timestamp,
                                     @Field("sign") String sign,
                                     @Field("key") String key,
                                     @Field("m_id") int id,
                                     @Field("type") int type,
                                     @Field("page") int page
    );

    /*我的下级粉丝*/
    @FormUrlEncoded
    @POST("ExtendSort")
    Call<BannerBean> getPaiHangBang(@Field("timestamp") String timestamp,
                                    @Field("sign") String sign,
                                    @Field("key") String key,
                                    @Field("type") int type,
                                    @Field("page") int page
    );

}
