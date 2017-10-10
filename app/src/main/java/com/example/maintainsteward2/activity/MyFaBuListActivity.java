package com.example.maintainsteward2.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.KuaiSuFaBuListAdapter;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.MyFaBuListBean;
import com.example.maintainsteward2.mvp_presonter.MyFaBuListPresonter;
import com.example.maintainsteward2.mvp_view.OnMyFastFaBuListener;
import com.example.maintainsteward2.utils.ToolUitls;

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
 * Created by Administrator on 2017/10/10.
 */

public class MyFaBuListActivity extends BaseActivity implements PtrHandler2, OnMyFastFaBuListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfabu);
        ButterKnife.bind(this);
        ptrFrame.setPtrHandler(this);
        initViews();
        initUserInfo();
    }

    MyFaBuListPresonter myFaBuListPresonter;
    List<MyFaBuListBean.DataBean> list;
    KuaiSuFaBuListAdapter kuaiSuFaBuListAdapter;

    private void initViews() {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        myFaBuListPresonter = new MyFaBuListPresonter();
        myFaBuListPresonter.setOnMyFastFaBuListener(this);
        list = new ArrayList<>();
        recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        kuaiSuFaBuListAdapter = new KuaiSuFaBuListAdapter(this);
        recycle.setAdapter(kuaiSuFaBuListAdapter);

    }

    String id;
    int page = 1;

    private void initUserInfo() {


        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "FastOrder?" + "user_id=" + id + "&page=" + page + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
        myFaBuListPresonter.getFastOrder(id, page + "", time, sign, Contacts.KEY);

    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
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
                initUserInfo();
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

    @Override
    public void getFastFaBuList(MyFaBuListBean listBean) {
        switch (listBean.getStatus()) {

            case "1":
                List<MyFaBuListBean.DataBean> data = listBean.getData();
                list.addAll(data);
                kuaiSuFaBuListAdapter.setList(list);
                kuaiSuFaBuListAdapter.notifyDataSetChanged();

                break;
        }
    }
}
