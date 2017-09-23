package com.example.maintainsteward.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.JingXuanBean;
import com.example.maintainsteward.mvp_presonter.JingXuanPresonter;
import com.example.maintainsteward.mvp_view.JingXuanListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/23.
 */

public class JingXuanPaiHangActivity extends BaseActivity implements JingXuanListener, OnScrollListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.img_fabu)
    ImageView imgFabu;
    @BindView(R.id.img_huidaodingbu)
    ImageView imgHuidaodingbu;

    JingXuanPresonter presonter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jingxuan);
        ButterKnife.bind(this);
        data = new ArrayList<>();
        lvList.setOnScrollListener(this);
        presonter = new JingXuanPresonter();
        presonter.setJingXuanListener(this);
        getList();
    }

    int page = 1;

    public void getList() {
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "CarefullySelectedSort?" + "page=" + page + "&sign=" + sign + "&timestamp=" + time + "&key=" + Contacts.KEY);

        presonter.getJingXuan(page + "", time, sign, Contacts.KEY);
    }

    @OnClick({R.id.img_fabu, R.id.img_huidaodingbu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_fabu:
                startActivity(new Intent(this, KuaiSuFaBuActivity.class));

                break;
            case R.id.img_huidaodingbu:
                lvList.setSelection(0);
                lvList.setSelectionAfterHeaderView();
                lvList.smoothScrollToPosition(0);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    List<JingXuanBean.DataBean> data;

    @Override
    public void getJingXuan(JingXuanBean bean) {

        switch (bean.getStatus()) {
            case "1":
                data.addAll(bean.getData());



                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
