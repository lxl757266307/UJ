package com.example.maintainsteward.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.maintainsteward.utils.ToolUitls;
import com.igexin.sdk.GTServiceManager;

/**
 * Created by Administrator on 2017/8/2.
 */

public class DemoPushService extends Service {
    public static final String TAG = "DemoPushService";

    @Override
    public void onCreate() {
        super.onCreate();
        GTServiceManager.getInstance().onCreate(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return GTServiceManager.getInstance().onStartCommand(this, intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return GTServiceManager.getInstance().onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GTServiceManager.getInstance().onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GTServiceManager.getInstance().onLowMemory();
    }
}
