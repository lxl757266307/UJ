package com.example.maintainsteward.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.example.maintainsteward.base.Contacts;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/2.
 */

public class ToolUitls {

    /*控制日志打印   不需要在relese 的时候注视日志代码*/
    public static boolean debug = true;

    public static void toast(Context context, String message) {
        if (debug) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

    }

    public static void print(String tag, String... message) {
        if (debug) {
            for (int i = 0; i < message.length; i++) {
                Log.e(tag, message[i]);
            }
        }

    }


    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString().toUpperCase();
    }


    /**
     * RSA加密
     */
    public static String getSign(TreeMap<String, String> map) {
        String jsonStr = "";
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            if (map.get(key) != null && !map.get(key).isEmpty()) {

                ToolUitls.print("key", "key===" + key);
                jsonStr += key + "=" + map.get(key) + "&";
            }
        }
        jsonStr = Contacts.SIGN + jsonStr.substring(0, jsonStr.length()) +
                "key=" + Contacts.KEY + Contacts.SIGN;
        ToolUitls.print("jsonStr", "jsonStr===" + jsonStr);
        return MD5.getMessageDigest(jsonStr.getBytes()).toUpperCase();
    }


    /**
     * 获取设备的唯一识别码
     *
     * @param context
     * @return
     */
    public static String getMyUUID(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        final String tmDevice, tmSerial, tmPhone, androidId;
//        tmDevice = "" + tm.getDeviceId();
//        tmSerial = "" + tm.getSimSerialNumber();
//        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
//        String uniqueId = deviceUuid.toString();
        return tm.getDeviceId();
    }


    public static void getCallBackStr(final String path) {
        ToolUitls.print("TEST", "path=" + path);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    if (urlConnection.getResponseCode() == 200) {

                        InputStream inputStream = urlConnection.getInputStream();

                        int len = 0;

                        byte[] bytes = new byte[1024];

                        StringBuilder builder = new StringBuilder();

                        while ((len = inputStream.read(bytes)) != -1) {

                            builder.append(new String(bytes, 0, len));
                        }

                        ToolUitls.print("TEST", "str=" + builder);


                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
