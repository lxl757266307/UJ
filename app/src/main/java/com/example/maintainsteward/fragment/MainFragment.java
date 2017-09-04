package com.example.maintainsteward.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/15.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainFragment extends Fragment implements View.OnScrollChangeListener {
    @BindView(R.id.txt_main_test)
    TextView txtMainTest;
    @BindView(R.id.layout_content)
    LinearLayout layoutContent;
    @BindView(R.id.layout_main_content)
    LinearLayout layoutMainContent;
    @BindView(R.id.slv_main)
    ScrollView slvMain;
    @BindView(R.id.layout_main_header)
    LinearLayout layoutMainHeader;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentHeight();
        setListener();
        addItem();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

        int imageHeight = txtMainTest.getHeight();
         /* 使用scorllview  嵌套 动态设置内容的高度 实现滑动效果 因为 内容没有充满 则无法实现 滑动*/

         /*注意三种状态 1. 未滑动 2.滑动中没有超过 背景图片的 高度  3，超过背景图片高度后的状态*/

        if (scrollY <= 0) {// 未滑动
            layoutMainHeader.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
        } else if (scrollY > 0 && scrollY <= imageHeight) {//2.滑动中没有超过 背景图片的 高度
            float scale = (float) scrollY / imageHeight;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            layoutMainHeader.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
        } else {//3，超过背景图片高度后的状态
            layoutMainHeader.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
        }
    }


    /*动态设置滑动  如果内容高度 没有超过 屏幕的高度 则无法实现滑动  只需要动态设置 让内容高度 超过屏幕的高度即可*/
    public void setContentHeight() {

        int screenHeight = ScreenUtils.getScreenHeight(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.height = screenHeight + screenHeight / 3;
        layoutMainContent.setLayoutParams(layoutParams);


    }

    public void addItem() {

        for (int i = 0; i < 10; i++) {
            TextView textView = new TextView(getActivity());
            textView.setText("测试");
            layoutContent.addView(textView);
        }
    }

    private void setListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            slvMain.setOnScrollChangeListener(this);
        }
    }

}
