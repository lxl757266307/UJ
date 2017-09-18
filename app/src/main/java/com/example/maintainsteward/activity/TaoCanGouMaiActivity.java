package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.TaoCanFragementPagerAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.fragment.Health365Fragment;
import com.example.maintainsteward.fragment.HelpSelfQingXiTaoCanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/18.
 */

public class TaoCanGouMaiActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.vp_taocan)
    ViewPager vpTaocan;
    @BindView(R.id.txt_wei_xuan_fu_wu_taocan)
    TextView txtWeiXuanFuWuTaocan;
    @BindView(R.id.txt_you_hui_jia_taocan)
    TextView txtYouHuiJiaTaocan;
    @BindView(R.id.txt_wei_yuan_jia_taocan)
    TextView txtWeiYuanJiaTaocan;
    @BindView(R.id.txt_li_ji_gou_mai_taocan)
    LinearLayout txtLiJiGouMaiTaocan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan);
        ButterKnife.bind(this);
        initViewPage();

    }

    @OnClick({R.id.layout_back, R.id.txt_li_ji_gou_mai_taocan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                break;
            case R.id.txt_li_ji_gou_mai_taocan:
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

    TaoCanFragementPagerAdapter adapter;

    public void initViewPage() {
        adapter = new TaoCanFragementPagerAdapter(getSupportFragmentManager());
        Health365Fragment health365Fragment = new Health365Fragment();
        HelpSelfQingXiTaoCanFragment helpSelfQingXiTaoCanFragment = new HelpSelfQingXiTaoCanFragment();

        String title[] = {"365健康套餐", "自选清洗套餐"};

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(health365Fragment);
        fragments.add(helpSelfQingXiTaoCanFragment);
        adapter.setFragments(fragments);
        adapter.setTitle(title);
        adapter.notifyDataSetChanged();

        vpTaocan.setAdapter(adapter);


    }
}
