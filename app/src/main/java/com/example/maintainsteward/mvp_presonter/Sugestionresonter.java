package com.example.maintainsteward.mvp_presonter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.maintainsteward.api.HttpApi;
import com.example.maintainsteward.base.Contacts;
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
 * Created by Administrator on 2017/8/21.
 */

public class Sugestionresonter {

    public static final String TAG = "Sugestionresonter";

    Retrofit retrofit;
    HttpApi httpApi;

    public Sugestionresonter() {

        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Contacts.BASE_URL).build();
        httpApi = retrofit.create(HttpApi.class);
    }

    public void getToken() {
//        Call<QiNiuBean> call = httpApi.getToken();
//        call.enqueue(new Callback<QiNiuBean>() {
//            @Override
//            public void onResponse(Call<QiNiuBean> call, Response<QiNiuBean> response) {
////                Log.e(TAG, "response=" + response.toString());
////                Log.e(TAG, "QiNiuBean=" + response.body().getToken().toString());
//                if (response.isSuccessful()) {
//                    QiNiuBean body = response.body();
//
//                    if (onSugestionListener != null && body != null) {
//                        onSugestionListener.onTokenSucess(body.getToken());
//                    }
//
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<QiNiuBean> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }

    public void qiNiuYunUpload(Bitmap bitmap, String token) {

//        MyApplication.getQiNiuManager().put(Tools.bitmap2Byte(bitmap),
//                Tools.getTimeStamp() + ".jpg", token,
//                new UpCompletionHandler() {
//                    @Override
//                    public void complete(String key, ResponseInfo info, JSONObject response) {
//                        if (info.isOK()) {
//                            if (info.isOK()) {
//                              Log.e(TAG, "response=" + response.toString());
//                                if (onSugestionListener != null) {
//                                    try {
//                                        onSugestionListener.onUpSucess(Consts.QINIUYUN + response.getString("key"));
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }, new UploadOptions(null, "test-type", true, null, null));

    }


    public void getSugestionType() {

//        Call<SugestionTypeBean> call = httpApi.getSugestionType();
//
//        call.enqueue(new Callback<SugestionTypeBean>() {
//            @Override
//            public void onResponse(Call<SugestionTypeBean> call, Response<SugestionTypeBean> response) {
//
////                Log.e(TAG, "response=" + response.toString());
//                if (response.isSuccessful()) {
//                    SugestionTypeBean body = response.body();
//
//                    if (body != null && onSugestionListener != null) {
//                        onSugestionListener.getSugestionType(body);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SugestionTypeBean> call, Throwable t) {
//
//            }
//        });


    }


    public void submit(int id, int type, String content, String images) {
//        Call<SugestionCallBackBean> call = httpApi.subFeedBack("sub_feedback", id, type, content, images, 3);
//        call.enqueue(new Callback<SugestionCallBackBean>() {
//            @Override
//            public void onResponse(Call<SugestionCallBackBean> call, Response<SugestionCallBackBean> response) {
//
////                Log.e(TAG, "submit=" + response.toString());
//
////                Log.e(TAG, "body=" + response.body().toString());
//                if (response.isSuccessful()) {
//                    SugestionCallBackBean body = response.body();
//
//                    if (body != null && onSugestionListener != null) {
//                        onSugestionListener.onSubmitCallBack(body);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SugestionCallBackBean> call, Throwable t) {
//
//            }
//        });

    }


    OnSugestionListener onSugestionListener;

    public void setOnUpLoadBitmapListener(OnSugestionListener onUpLoadBitmapListener) {
        this.onSugestionListener = onUpLoadBitmapListener;
    }

    public interface OnSugestionListener {


        void onTokenSucess(String token);

        void onUpSucess(String url);

//        void getSugestionType(SugestionTypeBean sugestionTypeBean);
//
//        void onSubmitCallBack(SugestionCallBackBean sugestionCallBackBean);
    }
}
