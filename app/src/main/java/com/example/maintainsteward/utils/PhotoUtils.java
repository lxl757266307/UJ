package com.example.maintainsteward.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.maintainsteward.application.MyApplication;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/19.
 */

public class PhotoUtils {


    public static Uri getUri() {

        File file;
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".png");
            return Uri.parse(file.getAbsolutePath());
        }
        return null;
    }


    public static File compressBitmap(Bitmap bitmap) {

        File file = new File(getUri().toString());
        try {
            OutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }


    /*

      * 剪切图片

      */

    public static void crop(Uri uri, Activity activity, int REQUEST_CODE) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        activity.startActivityForResult(intent, REQUEST_CODE);
    }


    /*圆形图片*/

    public Bitmap getCircleBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(circleBitmap);
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            final RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight()));
            float roundPx = 0.0f;
            // 以较短的边为标准
            if (bitmap.getWidth() > bitmap.getHeight()) {
                roundPx = bitmap.getHeight() / 2.0f;
            } else {
                roundPx = bitmap.getWidth() / 2.0f;
            }
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.WHITE);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            final Rect src = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            canvas.drawBitmap(bitmap, src, rect, paint);
            return circleBitmap;
        } catch (Exception e) {
            return bitmap;
        }
    }


    /**
     * <br>功能简述:裁剪图片方法实现---------------------- 相册
     * <br>功能详细描述:
     * <br>注意:
     */
    private void cropImageUri(String path, String name, Activity context, int requestcode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 640);
        intent.putExtra("outputY", 640);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(path, name)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        context.startActivityForResult(intent, requestcode);
    }


    /**
     * <br>功能简述:4.4以上裁剪图片方法实现---------------------- 相册
     * <br>功能详细描述:
     * <br>注意:
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void selectImageUriAfterKikat(Activity activity, int requestcode) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        /*Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");*/
        activity.startActivityForResult(intent, requestcode);
    }

    /**
     * <br>功能简述:裁剪图片方法实现----------------------相机
     * <br>功能详细描述:
     * <br>注意:
     *
     * @param uri
     */
    private void cameraCropImageUri(Uri uri, Activity activity, int reuqestcode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/jpeg");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 640);
        intent.putExtra("outputY", 640);
        intent.putExtra("scale", true);
        //		if (mIsKitKat) {
        //			intent.putExtra("return-data", true);
        //			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //		} else {
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //		}
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, reuqestcode);
    }

    /**
     * <br>功能简述: 4.4及以上改动版裁剪图片方法实现 --------------------相机
     * <br>功能详细描述:
     * <br>注意:
     *
     * @param uri
     */
    private void cropImageUriAfterKikat(Uri uri, String name, String path, Activity activity, int requestcode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/jpeg");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 640);
        intent.putExtra("outputY", 640);
        intent.putExtra("scale", true);
        //		intent.putExtra("return-data", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(path, name)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, requestcode);
    }

    /**
     * <br>功能简述:
     * <br>功能详细描述:
     * <br>注意:
     *
     * @param uri
     * @return
     */
    private Bitmap decodeUriAsBitmap(Uri uri, Context context) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }


    public void qiNiuYunUpload(Bitmap bitmap, String token) {

        MyApplication.getUploadManager().put(PhotoUtils.bitmap2Byte(bitmap),
                PhotoUtils.getTimeStamp() + ".jpg", token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject response) {
                        if (info.isOK()) {
                            if (info.isOK()) {

                            }

                        }
                    }
                }, new UploadOptions(null, "test-type", true, null, null));

    }


    public static byte[] bitmap2Byte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }
}
