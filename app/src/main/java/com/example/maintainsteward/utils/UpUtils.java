package com.example.maintainsteward.utils;

import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/8/2.
 */

public class UpUtils {




    /**
     * @param uploadManager
     * @param data          = <File对象、或 文件路径、或 字节数组>
     * @param key           key = <指定七牛服务上的文件名，或 null>;
     * @param token         token = <从服
     *                      务端SDK获取>;
     */
    public static void upFile(UploadManager uploadManager, String data, String key, String token) {

        uploadManager.put(data, key, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        if (info.isOK()) {
                            Log.i("qiniu", "Upload Success");
                        } else {
                            Log.i("qiniu", "Upload Fail");
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                        }
                        Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                    }
                }, null);
    }
}
