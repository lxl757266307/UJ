package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.TaoCanFragmentAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.fragment.Health365Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/26.
 */

public class TaoCanActivity extends BaseActivity {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.vip)
    ViewPager vip;

    List<Fragment> fragments;

    TaoCanFragmentAdapter taoCanFragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_365);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        Health365Fragment health365Fragment = new Health365Fragment();
        fragments.add(health365Fragment);
        taoCanFragmentAdapter = new TaoCanFragmentAdapter(getSupportFragmentManager());
        taoCanFragmentAdapter.setFragments(fragments);
        vip.setAdapter(taoCanFragmentAdapter);
        taoCanFragmentAdapter.notifyDataSetChanged();


    }

    @OnClick(R.id.layout_back)
    public void back() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
