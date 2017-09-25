package com.example.maintainsteward.utils;

import android.content.Context;
import android.content.Intent;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/8/2.
 */

public final class PermissionRegisterUtils {
/*    <!-- iBeancon功能所需权限 -->;
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
<!-- 个推3.0电子围栏功能所需权限 -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />*/


    /* 个推所需要的权限*/
    public static final String BLUETOOTH = "android.permission.BLUETOOTH";
    public static final String BLUETOOTH_ADMIN = "android.permission.BLUETOOTH_ADMIN";
    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";

    /*微信支付*/
/*
    <uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>*/

    public static final String INTERNET = "android.permission.INTERNET";
    public static final String ACCESS_NETWORK_STATE = "android.permission.ACCESS_NETWORK_STATE";
    public static final String ACCESS_WIFI_STATE = "android.permission.ACCESS_WIFI_STATE";
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";


    /*
        <!--用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
        <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
        <!--用于申请调用A-GPS模块-->
        <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"></uses-permission>*/
    public static final String CHANGE_WIFI_STATE = "android.permission.CHANGE_WIFI_STATE";
    public static final String ACCESS_LOCATION_EXTRA_COMMANDS = "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS";


//     <!--读取通讯录-->
//    <uses-permission android:name="android.permission.READ_CONTACTS"/>

    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS";

    /*电话*/
    public static final String ACTION_CALL = "android.intent.action.CALL";

    //   <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
//    <uses-permission android:name="android.permission.CAMERA"/>
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String CAMERA = "android.permission.CAMERA";




/*     <!--微信分享-->
    <uses-permission android:name="android.permission.GET_TASKS" />
    <uses-permission android:name="android.permission.MANAGE_ACCOUNTS"/>
    <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
    <!-- 蓝牙分享所需的权限 -->
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />*/

    public static final String GET_TASKS = "android.permission.GET_TASKS";
    public static final String MANAGE_ACCOUNTS = "android.permission.MANAGE_ACCOUNTS";
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";


    public final static String[] PERMISSION = {
            PermissionRegisterUtils.ACCESS_COARSE_LOCATION,
            PermissionRegisterUtils.GET_TASKS,
            PermissionRegisterUtils.MANAGE_ACCOUNTS,
            PermissionRegisterUtils.GET_ACCOUNTS,
            PermissionRegisterUtils.ACCESS_FINE_LOCATION,
            PermissionRegisterUtils.BLUETOOTH,
            PermissionRegisterUtils.BLUETOOTH_ADMIN,
            PermissionRegisterUtils.INTERNET,
            PermissionRegisterUtils.ACCESS_NETWORK_STATE,
            PermissionRegisterUtils.ACCESS_WIFI_STATE,
            PermissionRegisterUtils.READ_PHONE_STATE,
            PermissionRegisterUtils.WRITE_EXTERNAL_STORAGE,
            PermissionRegisterUtils.CHANGE_WIFI_STATE,
            PermissionRegisterUtils.ACCESS_LOCATION_EXTRA_COMMANDS,
            PermissionRegisterUtils.READ_CONTACTS,
//            PermissionRegisterUtils.ACTION_CALL,
            PermissionRegisterUtils.READ_EXTERNAL_STORAGE,
            PermissionRegisterUtils.CAMERA,
    };


    public static final String TAG = "PermissionRegisterUtils";


    public static void registerPermission(Context context) {


        if (EasyPermissions.hasPermissions(context, PermissionRegisterUtils.PERMISSION)) {//检查是否获取该权限
            ToolUitls.print(TAG, "已注册");
        } else {
            //第二个参数是被拒绝后再次申请该权限的解释
            //第三个参数是请求码
            //第四个参数是要申请的权限
            EasyPermissions.requestPermissions(context, "必要的权限", 0, PermissionRegisterUtils.PERMISSION);
        }
    }

}
