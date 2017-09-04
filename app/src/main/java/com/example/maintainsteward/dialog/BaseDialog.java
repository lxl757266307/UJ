package com.example.maintainsteward.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.maintainsteward.R;

/**
 * Created by Administrator on 2017/8/2.
 */

public class BaseDialog {


    public static AlertDialog setDialog(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog alertDialog = builder.create();
////        alertDialog.show();
//        View view = View.inflate(activity, R.layout.dialog_bottom, null);
//        Window window = alertDialog.getWindow();
//        window.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.dialog_bottom_bg));
//        window.setWindowAnimations(R.style.alert_dialog);
//        window.setContentView(view);
//        /* 屏幕底部显示*/
//        window.setGravity(Gravity.BOTTOM);
//        WindowManager windowManager = activity.getWindowManager();
//        Display defaultDisplay = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        attributes.height = (int) (defaultDisplay.getHeight() * 0.6);
//        attributes.width = defaultDisplay.getWidth();
//        window.setAttributes(attributes);
//        alertDialog.setCanceledOnTouchOutside(true);

        return alertDialog;
    }
}
