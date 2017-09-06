package com.example.maintainsteward.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.view.MyViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/6.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class SearchActivity extends BaseActivity {


    @BindView(R.id.edit_search_activity)
    EditText editSearchActivity;
    @BindView(R.id.img_qingkong_search_activity)
    ImageView imgQingkongSearchActivity;
    @BindView(R.id.txt_cancle_search_activity)
    TextView txtCancleSearchActivity;
    @BindView(R.id.vg_search_activity)
    MyViewGroup vgSearchActivity;


    @OnClick({R.id.txt_cancle_search_activity, R.id.img_qingkong_search_activity})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.txt_cancle_search_activity:
                finish();
                break;
            case R.id.img_qingkong_search_activity:
                editSearchActivity.setText("");
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    String[] str = {"空调清洗", "冰箱清洗", "油烟机清洗", "吸顶灯安装", "水龙头安装", "浴霸安装", "五金小挂件安装"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initViewGroup();

        initListener();
    }

    private void initListener() {
        for (int i = 0; i < btnArray.length; i++) {

            btnArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
    }

    TextView[] btnArray;

    private void initViewGroup() {
        btnArray = new TextView[str.length];
        for (int i = 0; i < str.length; i++) {
            TextView button = (TextView) LayoutInflater.from(this).inflate(R.layout.button, null);
            button.setText(str[i]);
            btnArray[i] = button;
            vgSearchActivity.addView(button);
        }


    }


}
