package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.maintainsteward.R;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.main.MainActivity;

/**
 * Created by Administrator on 2017/9/23.
 */

public class QiDongActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_qidong);

        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        boolean online = sharedPreferences.getBoolean("online", false);

        if (online) {

            handler.sendEmptyMessageDelayed(1, 2000);

        } else {
            handler.sendEmptyMessageDelayed(2, 2000);

        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    startActivity(new Intent(QiDongActivity.this, MainActivity.class));
                    finish();
                    break;
                case 2:
                    startActivity(new Intent(QiDongActivity.this, LoginActivity.class));
                    finish();
                    break;
            }

        }
    };
}
