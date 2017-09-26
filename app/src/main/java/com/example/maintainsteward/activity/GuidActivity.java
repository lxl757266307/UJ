package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.ImagesPagerAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.main.MainActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/23.
 */

public class GuidActivity extends BaseActivity {


    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.layout_next)
    LinearLayout layoutNext;
    SharedPreferences sharedPreferences;

    ArrayList<ImageView> imageViews;
    int ids[] = {R.mipmap.y1, R.mipmap.y2, R.mipmap.y3, R.mipmap.y4};

    public static final String TAG = "GuidActivity";
    @BindView(R.id.txt_yema)
    TextView txtYema;

    TextView[] textViews;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.txt4)
    TextView txt4;
    @BindView(R.id.btn_tiyan)
    Button btnTiyan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean("isFirst", true);

        if (isFirst) {
            setContentView(R.layout.activity_guid);
            ButterKnife.bind(this);

            textViews = new TextView[4];
            textViews[0] = txt1;
            textViews[1] = txt2;
            textViews[2] = txt3;
            textViews[3] = txt4;

            imageViews = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                ImageView imageView = new ImageView(this);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(ids[i]);
                imageViews.add(imageView);
            }

            ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(imageViews);
            vp.setAdapter(pagerAdapter);
            pagerAdapter.notifyDataSetChanged();
            vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    setTextBackground(position);
                    if (position == imageViews.size() - 1) {
                        btnTiyan.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        } else {
            startActivity(new Intent(this, QiDongActivity.class));
        }

    }

    @OnClick(R.id.layout_next)
    public void onViewClicked() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("isFirst", false);
        edit.commit();
        startActivity(new Intent(this, QiDongActivity.class));
        finish();

    }

    @OnClick(R.id.btn_tiyan)
    public void tiyan() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("isFirst", false);
        edit.commit();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    public void setTextBackground(int position) {
        txtYema.setText(position + 1 + "/4");
        for (int i = 0; i < textViews.length; i++) {
            if (i == position) {
                textViews[i].setBackgroundColor(Color.parseColor("#bbbbbb"));
            } else {
                textViews[i].setBackgroundColor(Color.parseColor("#ffffff"));
            }

        }
    }
}
