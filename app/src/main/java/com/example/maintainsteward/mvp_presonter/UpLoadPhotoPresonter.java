package com.example.maintainsteward.mvp_presonter;

import android.graphics.Bitmap;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseHttpApi;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.QiNiuBean;
import com.example.maintainsteward.mvp_view.OnUpLoadPhotoListener;
import com.example.maintainsteward.utils.PhotoUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/16.
 */

public class UpLoadPhotoPresonter {

    HttpApi api;
    Retrofit retrofit;

    public UpLoadPhotoPresonter() {
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://139.129.110.219:805/").build();
        api = retrofit.create(HttpApi.class);
    }

    public void getToken() {
        Call<QiNiuBean> call = api.getToken("upload_token");
        call.enqueue(new Callback<QiNiuBean>() {
            @Override
            public void onResponse(Call<QiNiuBean> call, Response<QiNiuBean> response) {
                if (response.isSuccessful()) {

                    QiNiuBean body = response.body();

                    if (body != null) {
                        String token = body.getToken();
                        listener.getToken(token);
                    }


                }


            }

            @Override
            public void onFailure(Call<QiNiuBean> call, Throwable t) {

            }
        });


    }


    public void qiNiuYunUpload(Bitmap bitmap, String token) {

        MyApplication.getUploadManager().put(PhotoUtils.bitmap2Byte(bitmap),
                PhotoUtils.getTimeStamp() + ".jpg", token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject response) {
                        if (info.isOK()) {
                            if (info.isOK()) {

                                if (listener != null) {
                                    try {
                                        listener.onUpSucess(Contacts.QINIUYUN + response.getString("key"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }
                    }
                }, new UploadOptions(null, "test-type", true, null, null));

    }

    OnUpLoadPhotoListener listener;

    public void setListener(OnUpLoadPhotoListener listener) {
        this.listener = listener;
    }
}
