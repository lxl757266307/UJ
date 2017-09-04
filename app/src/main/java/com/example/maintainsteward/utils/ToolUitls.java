package com.example.maintainsteward.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

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
    public static String getSign (LinkedHashMap<String, String> map){
        String jsonStr ="";
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            if(map.get(key)!=null&&!map.get(key).isEmpty()){
                jsonStr += key+"="+map.get(key)+"&";
            }
        }
        jsonStr = jsonStr.substring(0,jsonStr.length()-1);
        //return jsonStr;
        return MD5.getMessageDigest(jsonStr.getBytes()).toUpperCase();
    }
}
