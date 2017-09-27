package com.example.maintainsteward.api;

import com.example.maintainsteward.base.MySetMealBean;
import com.example.maintainsteward.bean.AddressBean;
import com.example.maintainsteward.bean.AddressDeleteBean;
import com.example.maintainsteward.bean.AddressListBean;
import com.example.maintainsteward.bean.BannerBean;
import com.example.maintainsteward.bean.CanUseYouHuiQuanBean;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.bean.AppIndexCategoryBean;
import com.example.maintainsteward.bean.FirstKindsBean;
import com.example.maintainsteward.bean.HotWordBean;
import com.example.maintainsteward.bean.JingXuanBean;
import com.example.maintainsteward.bean.KaJuanBean;
import com.example.maintainsteward.bean.KaJuanCountBean;
import com.example.maintainsteward.bean.LogListBean;
import com.example.maintainsteward.bean.LoginCallBackBean;
import com.example.maintainsteward.bean.OrderInfoBean;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.bean.OrderSucessBean;
import com.example.maintainsteward.bean.PayInfoBean;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.bean.QiNiuBean;
import com.example.maintainsteward.bean.SearchKeyWordBean;
import com.example.maintainsteward.bean.SearviceInfoBean;
import com.example.maintainsteward.bean.SecondKindsBean;
import com.example.maintainsteward.bean.SecondKindsContent;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;
import com.example.maintainsteward.bean.TaoCallBackBean;
import com.example.maintainsteward.bean.TaoCanListBean;
import com.example.maintainsteward.bean.UserInfoBean;
import com.example.maintainsteward.bean.YanZhengMaCallBackBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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


    /*获取验证码*/
    @FormUrlEncoded
    @POST("GetVerifyCode")
    Call<YanZhengMaCallBackBean> getVerifyCode(@Field("phone") String phone,
                                               @Field("type") String type,
                                               @Field("timestamp") String timestamp,
                                               @Field("sign") String sign,
                                               @Field("key") String key);

    /*登录*/
    @FormUrlEncoded
    @POST("GoLoginByCode")
    Call<LoginCallBackBean> goLoginByCode(@Field("phone") String phone,
                                          @Field("code") String code,
                                          @Field("chinaid") String chinaid,
                                          @Field("ioschinaid") String ioschinaid,
                                          @Field("login_type") String login_type,
                                          @Field("timestamp") String timestamp,
                                          @Field("sign") String sign,
                                          @Field("key") String key);


    /*热门搜素词汇*/
    @FormUrlEncoded
    @POST("ServiceKeywordsGet")
    Call<HotWordBean> getHotWord(@Field("timestamp") String timestamp,
                                 @Field("sign") String sign,
                                 @Field("key") String key);

    /*搜素关键字*/
    @FormUrlEncoded
    @POST("ServiceSearchByKeywords")
    Call<SearchKeyWordBean> searchInfo(@Field("keywords") String keyword,
                                       @Field("page") int page,
                                       @Field("timestamp") String timestamp,
                                       @Field("sign") String sign,
                                       @Field("key") String key);


    /*一级分类*/
    @FormUrlEncoded
    @POST("ServiceCategoryListsFirst")
    Call<FirstKindsBean> getFirstKinds(@Field("timestamp") String timestamp,
                                       @Field("sign") String sign,
                                       @Field("key") String key);

    /*二级分类*/
    @FormUrlEncoded
    @POST("ServiceCategoryListSecond")
    Call<SecondKindsBean> getSecondKinds(@Field("id") String id,
                                         @Field("timestamp") String timestamp,
                                         @Field("sign") String sign,
                                         @Field("key") String key);


    /*二级分类详细内容*/
    @FormUrlEncoded
    @POST("ServiceCategoryListSecondContent")
    Call<SecondKindsContent> getSecondKindsContent(@Field("id") String id,
                                                   @Field("timestamp") String timestamp,
                                                   @Field("sign") String sign,
                                                   @Field("key") String key);


    /*服务项目*/
    @FormUrlEncoded
    @POST("ServiceAll")
    Call<SearviceInfoBean> getSearviceAll(@Field("cat_id") String id,
                                          @Field("timestamp") String timestamp,
                                          @Field("sign") String sign,
                                          @Field("key") String key);


    /*服务配件*/
    @FormUrlEncoded
    @POST("ServiceGoodsGet")
    Call<ServiceGoodsGetBean> getServiceGoodsGet(@Field("cat_id") String id,
                                                 @Field("timestamp") String timestamp,
                                                 @Field("sign") String sign,
                                                 @Field("key") String key);


    /*获取用户地址*/
    @FormUrlEncoded
    @POST("GetAddress")
    Call<AddressListBean> getAddress(@Field("user_id") String id,
                                     @Field("page") String page,
                                     @Field("timestamp") String timestamp,
                                     @Field("sign") String sign,
                                     @Field("key") String key);  /*获取用户地址*/

    /*删除地址*/
    @FormUrlEncoded
    @POST("DelAddress")
    Call<AddressDeleteBean> deleteAddress(@Field("id") String id,
                                          @Field("timestamp") String timestamp,
                                          @Field("sign") String sign,
                                          @Field("key") String key);    /*删除地址*/    /*删除地址*/

    /*订单列表*/
    @FormUrlEncoded
    @POST("orderListByUser")
    Call<OrderListBean> getOrderList(@Field("user_id") String id,
                                     @Field("order_type") String order_type,
                                     @Field("page") String page,
                                     @Field("timestamp") String timestamp,
                                     @Field("sign") String sign,
                                     @Field("key") String key);

    /*订单列表*/
    @FormUrlEncoded
    @POST("OrderDetails")
    Call<OrderInfoBean> getOrderDetails(@Field("user_id") String user_id,
                                        @Field("id") String id,
                                        @Field("timestamp") String timestamp,
                                        @Field("sign") String sign,
                                        @Field("key") String key);

    /*提交订单*/
    @FormUrlEncoded
    @POST("ServiceOrderSubmit")
    Call<OrderSucessBean> serviceOrderSubmit(@Field("user_id") String id,
                                             @Field("cat_id") String cat_id,
                                             @Field("name") String name,
                                             @Field("address_id") String address_id,
                                             @Field("service_con") String service_con,
                                             @Field("material") String material,
                                             @Field("content") String content,
                                             @Field("img1") String img1,
                                             @Field("img2") String img2,
                                             @Field("img3") String img3,
                                             @Field("img4") String img4,
                                             @Field("img5") String img5,
                                             @Field("img6") String img6,
                                             @Field("order_time") String order_time,
                                             @Field("timestamp") String timestamp,
                                             @Field("sign") String sign,
                                             @Field("type") String type,
                                             @Field("key") String key);


    /*取消订单*/
    @FormUrlEncoded
    @POST("OrderCancel")
    Call<PublicBean> quXiaoOrder(@Field("user_id") String user_id,
                                 @Field("order_id") String order_id,
                                 @Field("timestamp") String timestamp,
                                 @Field("sign") String sign,
                                 @Field("key") String key);


    /*获取用户信息*/
    @FormUrlEncoded
    @POST("GetUserInfos")
    Call<UserInfoBean> getUserInfos(@Field("user_id") String user_id,
                                    @Field("timestamp") String timestamp,
                                    @Field("sign") String sign,
                                    @Field("key") String key);

    /*获取用户信息*/

    /**
     * @param user_id
     * @param type      avatar(头像), user_nicename(昵称), sex(性别)，birthday(生日)
     * @param values
     * @param timestamp
     * @param sign
     * @param key
     * @return
     */
    @FormUrlEncoded
    @POST("GoUpdateUserInfo")
    Call<PublicBean> goUpdateUserInfo(@Field("user_id") String user_id,
                                      @Field("type") String type,
                                      @Field("values") String values,
                                      @Field("timestamp") String timestamp,
                                      @Field("sign") String sign,
                                      @Field("key") String key);


    /*获取七牛云 token*/
    @FormUrlEncoded
    @POST("index.php")
    Call<QiNiuBean> getToken(@Field("code") String code
    );


    /*修改手机号*/
    @FormUrlEncoded
    @POST("bindNewPhone")
    Call<PublicBean> bindNewPhone(
            @Field("user_id") String id,
            @Field("phone") String phone,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key
    );


    /*设置支付密码*/
    @FormUrlEncoded
    @POST("bindNewPhone")
    Call<PublicBean> goSetPayPassword(
            @Field("user_id") String id,
            @Field("phone") String phone,
            @Field("pwd") String pwd,
            @Field("type") String type,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key
    );

    /*修改支付密码*/
    @FormUrlEncoded
    @POST("bindNewPhone")
    Call<PublicBean> goUpdatePayPassword(
            @Field("user_id") String id,
            @Field("phone") String phone,
            @Field("newpwd") String pwd,
            @Field("oldpwd") String oldpwd,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key
    );

    /*我的卡卷数量*/
    @FormUrlEncoded
    @POST("MyCouponsCount")
    Call<KaJuanCountBean> myCouponsCount(
            @Field("user_id") String id,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key
    );

    /*我的钱包余额*/
    @FormUrlEncoded
    @POST("MyWalletBalance")
    Call<LogListBean> myWalletBalance(
            @Field("user_id") String id,
            @Field("type") String type,
            @Field("page") String page,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key
    );


    /*我的卡卷*/
    @FormUrlEncoded
    @POST("MyCoupons")
    Call<KaJuanBean> myCoupons(
            @Field("user_id") String id,
            @Field("type") String type,
            @Field("page") String page,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key
    );

    /*订单支付可使用的优惠卷*/
    @FormUrlEncoded
    @POST("ServiceOrderUseCoupons")
    Call<CanUseYouHuiQuanBean> getServiceOrderUseCoupons(
            @Field("user_id") String id,
            @Field("order_no") String order_no,
            @Field("count") String count,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key
    );


    /*订单支付可使用的优惠卷*/
    @FormUrlEncoded
    @POST("pay")
    Call<PayInfoBean> getPayInfo(
            @Field("order_sn") String order_sn
    );


    /*订单支付可使用的优惠卷*/
    @FormUrlEncoded
    @POST("payForNowNew")
    Call<PublicBean> payForNowNew(
            @Field("out_trade_no") String order_sn,
            @Field("paytpe") String paytpe,
            @Field("total_fee") String total_fee

    );

    /*套餐列表*/
    @FormUrlEncoded
    @POST("SetMealDetails")
    Call<TaoCanListBean> getSetMealDetails(
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key

    );

    /*我的以购买过的套餐*/
    @FormUrlEncoded
    @POST("MySetMeal")
    Call<MySetMealBean> getMySetMeal(
            @Field("user_id") String user_id,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key

    );

    /*365套餐购买获取id*/
    @FormUrlEncoded
    @POST("SetMealBuyOrder")
    Call<TaoCallBackBean> getSetMealBuyOrder(
            @Field("user_id") String user_id,
            @Field("id") String id,
            @Field("paytype") String paytype,
            @Field("city") String city,
            @Field("district") String district,
            @Field("address") String address,
            @Field("msg") String msg,
            @Field("tel") String tel,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key

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

    /*精选排行*/
    @FormUrlEncoded
    @POST("CarefullySelectedSort")
    Call<JingXuanBean> getCarefullySelectedSort(
            @Field("page") String page,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign,
            @Field("key") String key
    );


    /*精选排行*/
    @FormUrlEncoded
    @POST("pay")
    Call<JingXuanBean> wxPay(
            @Field("order_sn") String page
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
