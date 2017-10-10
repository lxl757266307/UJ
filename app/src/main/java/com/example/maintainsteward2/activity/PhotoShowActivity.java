package com.example.maintainsteward2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.maintainsteward2.R;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.utils.ToolUitls;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/10.
 */

public class PhotoShowActivity extends Activity {
    @BindView(R.id.img_photo)
    ImageView imgPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        String url = this.getIntent().getStringExtra("url");
        setContentView(R.layout.activity_photoshow);
        ButterKnife.bind(this);

        Window window = getWindow();

        WindowManager windowManager = window.getWindowManager();

        Display defaultDisplay = windowManager.getDefaultDisplay();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.height = (int) (defaultDisplay.getHeight()*0.6);
        layoutParams.width = (int) (defaultDisplay.getWidth()*0.8);
        imgPhoto.setLayoutParams(layoutParams);

        Glide.with(this).load(url).into(imgPhoto);
    }


}
