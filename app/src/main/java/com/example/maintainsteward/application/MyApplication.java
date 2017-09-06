package com.example.maintainsteward.application;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.maintainsteward.bean.MapBean;
import com.example.maintainsteward.service.DemoIntentService;
import com.example.maintainsteward.service.DemoPushService;
import com.example.maintainsteward.utils.PermissionRegisterUtils;
import com.example.maintainsteward.utils.ToolUitls;
import com.igexin.sdk.PushManager;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class MyApplication extends Application {

    public static final String TAG = "MyApplication";

    private static UploadManager mUploadManager;
    private static ArrayList<Activity> mActivities;

    public static final String LOCATION = "location";

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。

                    int locationType = aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    double latitude = aMapLocation.getLatitude();//获取纬度
                    double longitude = aMapLocation.getLongitude();//获取经度
                    float accuracy = aMapLocation.getAccuracy();//获取精度信息
                    String address = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    String country = aMapLocation.getCountry();//国家信息
                    String province = aMapLocation.getProvince();//省信息
                    String city = aMapLocation.getCity();//城市信息
                    String district = aMapLocation.getDistrict();//城区信息
                    String street = aMapLocation.getStreet();//街道信息
                    String streetNum = aMapLocation.getStreetNum();//街道门牌号信息
                    String cityCode = aMapLocation.getCityCode();//城市编码
                    String adCode = aMapLocation.getAdCode();//地区编码
                    String aoiName = aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    String buildingId = aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    String floor = aMapLocation.getFloor();//获取当前室内定位的楼层
                    int gpsAccuracyStatus = aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    //获取定位时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    String format = df.format(date);

                    MapBean mapBean = new MapBean(locationType, latitude, longitude, accuracy, address, country, province, city, district, street, streetNum, cityCode, adCode, aoiName, buildingId, floor, gpsAccuracyStatus, format);

                    SharedPreferences sharedPreferences = getSharedPreferences(LOCATION, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("city", mapBean.getCity());
                    edit.putString("district", mapBean.getDistrict());
                    edit.commit();

                    ToolUitls.print(TAG, mapBean.toString());

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    ToolUitls.print("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };


    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    @Override
    public void onCreate() {
        super.onCreate();
//        PermissionRegisterUtils.registerPermission(this);


        /* 初始化个推*/
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);

        /* 初始化七牛云*/
        initQINIU();

        getActivitiesList();

        initLocation();

    }


    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(false);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }

    public static synchronized List<Activity> getActivitiesList() {
        if (mActivities == null) {
            mActivities = new ArrayList<>();
        }
        return mActivities;
    }


    /**
     * @param config
     * @return uploadManager  对象唯一
     */
    public static synchronized UploadManager getUploadManager(Configuration config) {

        if (mUploadManager == null) {
            return new UploadManager(config);
        }
        return mUploadManager;
    }


    public UploadManager getUploadManager() {
        return mUploadManager;
    }


    private void initQINIU() {
        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                //  .recorder(null)           // recorder分片上传时，已上传片记录器。默认null
                // .recorder(null, null)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        UploadManager uploadManager = getUploadManager(config);

    }


    public static void finishActivities() {
        if (mActivities != null && mActivities.size() > 0) {
            for (Activity activity : mActivities) {
                activity.finish();
            }
        }

    }
}
