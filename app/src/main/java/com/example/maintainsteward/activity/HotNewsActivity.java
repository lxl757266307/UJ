package com.example.maintainsteward.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.HotNewListAdapter;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.HotNewsList;
import com.example.maintainsteward.mvp_presonter.HotNewPresonter;
import com.example.maintainsteward.mvp_view.HotNewsListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

/**
 * Created by Administrator on 2017/9/28.
 */

public class HotNewsActivity extends BaseActivity implements HotNewsListener, HotNewListAdapter.OnItemClickListener, PtrHandler2 {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    List<HotNewsList.DataBean> data;
    HotNewPresonter hotNewPresonter;
    List<HotNewsList.DataBean> dataBeanList;
    HotNewListAdapter hotNewListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        data = (List<HotNewsList.DataBean>) this.getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_hotnew);
        ButterKnife.bind(this);
        init();
        setData();
    }

    private void setData() {
        if (data != null) {
            dataBeanList.addAll(data);
            hotNewListAdapter.setList(dataBeanList);
            hotNewListAdapter.notifyDataSetChanged();
        } else {
            getHotNews();
        }
    }

    private void init() {
        ptrFrame.setPtrHandler(this);
        dataBeanList = new ArrayList<>();
        hotNewPresonter = new HotNewPresonter();
        hotNewPresonter.setHotNewsListener(this);
        hotNewListAdapter = new HotNewListAdapter(this);
        hotNewListAdapter.setOnItemClickListener(this);
        recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycle.setAdapter(hotNewListAdapter);
    }

    int page = 1;

    public void getHotNews() {

        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("page", page + "");
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);
        hotNewPresonter.getHotNewList(page + "", time, sign, Contacts.KEY);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "InformationLists?page=1" + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
    }

    @OnClick(R.id.layout_back)
    public void back() {
        finish();
    }

    @Override
    public void getHotNewsSucess(HotNewsList hotNewsList) {
        switch (hotNewsList.getStatus()) {
            case "1":
                List<HotNewsList.DataBean> data = hotNewsList.getData();
                dataBeanList.addAll(data);
                hotNewListAdapter.setList(dataBeanList);
                hotNewListAdapter.notifyDataSetChanged();

                break;
            default:
                ToolUitls.toast(this, "暂无数据");
                break;
        }
    }

    @Override
    public void onItemClick(int postion) {
        HotNewsList.DataBean dataBean = dataBeanList.get(postion);
        Intent intent = new Intent(this, NewsWebActivity.class);
        intent.putExtra("data", dataBean);
        startActivity(intent);

    }

    @Override
    public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recycle.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recycle.getScrollState();
        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == recycle.SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onLoadMoreBegin(final PtrFrameLayout frame) {

        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                getHotNews();
                frame.refreshComplete();
            }
        }, 1000);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }
}
