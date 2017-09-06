package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6.
 */

public class SearInfoActivity extends BaseActivity {
    @BindView(R.id.edit_search_info_activity)
    EditText editSearchInfoActivity;
    @BindView(R.id.img_qingkong_search_info_activity)
    ImageView imgQingkongSearchInfoActivity;
    @BindView(R.id.txt_cancle_search_info_activity)
    TextView txtCancleSearchInfoActivity;
    @BindView(R.id.rv_search_info_activity)
    RecyclerView rvSearchInfoActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info);
        ButterKnife.bind(this);
    }
}
