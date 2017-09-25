package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/9/18.
 */

public class KuaiSuFaBuActivity extends BaseActivity {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.edit_description_kuaisufabu)
    EditText editDescriptionKuaisufabu;
    @BindView(R.id.rv_kuaisufabu)
    RecyclerView rvKuaisufabu;
    @BindView(R.id.txt_yuyue_kuaisufabu)
    TextView txtYuyueKuaisufabu;
    @BindView(R.id.img_kefu_kuaisufabu)
    CircleImageView imgKefuKuaisufabu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaisufabu);
        ButterKnife.bind(this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

}
