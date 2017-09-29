package com.example.maintainsteward.base;

/**
 * Created by Administrator on 2017/8/2.
 */

public final class Contacts {

    //七牛云图片域名
    public static final String QINIUYUN = "http://os18w14e3.bkt.clouddn.com/";
    //public static final String QINIUYUN ="http://oscakuwph.bkt.clouddn.com/";//七牛云图片域名(测试)
    //public static final String QINIUYUN ="osenv7cs9.bkt.clouddn.com/";//七牛云图片域名(测试)


    /**
     * 微信支付所需参数
     */
//    public static final String APP_ID = "wx4c957187aba457ff";
    public static final String APP_ID = "wx4c957187aba457ff";
    //    public static final String APP_MCHID = "1485605282";//商户号
    public static final String APP_KEY = "ljfdEj485Rg96RnPA1W4P6XM4d3gk545";//key
    public static final String App_Secret = "2a0c8ece80d2a0d18a43f23aee492156";


    /*普通订单支付*/
    public static final String WX_PAY_URL = "http://wxtest.cnncsh.com/app_user/orderpaybywechat/";

    /*365套餐支付*/
    public static final String WX_PAY365_URL = "http://wxtest.cnncsh.com/app_user/Mealpaybywechat/";




/*
    应用APPID:wx4c957187aba457ff
    APPSECRET:2a0c8ece80d2a0d18a43f23aee492156
    key:ljfdEj485Rg96RnPA1W4P6XM4d3gk545*/


    public static final String BASE_URL = "http://wxtest.cnncsh.com/app_user/index/index/op/";
    public static final String TEST_BASE_URL = "http://wxtest.cnncsh.com/app_user/index/index/op/";

    public static final String KEY = "idf5nsi5t0qbemwo12hztbftm53tbv6pht";
    public static final String SIGN = "idf5nsi5t0qbemwo124213198as";

    /* 保存用户信息的 user文件*/
    public static final String USER = "USER";


    /*定位广播*/
    public static final String LOCATION = "location";


    /*添加地址成功*/
    public static final String ADD_ADDRESS_OK = "add_address_ok";

    /*订单刷新*/
    public static final String ORDER_REFRESH = "order_refresh";


    /*更新个人信息*/
    public static final String UPDATE_USER = "user_refresh";

    /*微信支付成功*/
    public static final String PAY_BY_WEI_XIN = "pay_sucess";


    /*判断是 普通订单支付 还是 套餐支付 的标记*/
    public static String PAY_FLAG = "normal";

    /*付款完刷新订单*/
    public static String STATUS_REFRESH = "status_refresh";


}
