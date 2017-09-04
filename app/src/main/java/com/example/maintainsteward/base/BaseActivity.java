package com.example.maintainsteward.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward.uj.R;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.utils.ToolUitls;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/2.
 */

public class BaseActivity extends FragmentActivity {

    ImageView mTxtBaseBack;
    TextView mTxtBaseTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        MyApplication.getActivitiesList().add(this);

        /*无标题*/
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*全屏*/
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /* 设置输入法打开后 其他组件不被 输入法托起*/
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_base);


    }

    public void back(final Activity activity) {
        mTxtBaseBack = (ImageView) activity.findViewById(R.id.txt_base_back);
        mTxtBaseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void setTitleContent(String title, Activity activity) {
        mTxtBaseTitle = (TextView) activity.findViewById(R.id.txt_base_title);
        mTxtBaseTitle.setText(title);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (System.currentTimeMillis() - exitTime > 2000) {
                ToolUitls.toast(this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                MyApplication.finishActivities();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /* 通过监听 触摸事件 动态 隐藏 输入法*/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /* 获取 点击的 当前的 view 的 范围 */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
