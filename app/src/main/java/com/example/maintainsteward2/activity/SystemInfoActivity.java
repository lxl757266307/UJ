package com.example.maintainsteward2.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.SystemInfoAdapter;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.SystemInfoBean;
import com.example.maintainsteward2.mvp_presonter.SystemInfoPresonter;
import com.example.maintainsteward2.mvp_view.SystemInfoView;
import com.example.maintainsteward2.utils.ToolUitls;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

/**
 * Created by Administrator on 2017/11/21.
 */

public class SystemInfoActivity extends BaseActivity implements PtrHandler2, SystemInfoView {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    int page = 1;
    String id;

    SystemInfoPresonter systemInfoPresonter;
    List<SystemInfoBean.DataBean> systemData;

    SharedPreferences sharedPreferences;
    SystemInfoAdapter systemInfoAdapter;
    @BindView(R.id.layout_xiaoxi)
    LinearLayout layoutXiaoxi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
        recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ptrFrame.setPtrHandler(this);

        systemData = (List<SystemInfoBean.DataBean>) getIntent().getSerializableExtra("systemInfo");

        if (systemData != null && systemData.size() > 0) {
            systemInfoAdapter = new SystemInfoAdapter();
            systemInfoAdapter.setList(systemData);
            recycle.setAdapter(systemInfoAdapter);
            systemInfoAdapter.notifyDataSetChanged();
            layoutXiaoxi.setVerticalGravity(View.GONE);
        }

        systemInfoPresonter = new SystemInfoPresonter();
        systemInfoPresonter.setSystemInfoView(this);

    }

    public void getSystemInfos() {
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("page", page + "");
        map.put("user_id", id);
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);

        systemInfoPresonter = new SystemInfoPresonter();
        systemInfoPresonter.setSystemInfoView(this);

        systemInfoPresonter.getSystemInfo(id, page + "", time, sign, Contacts.KEY);

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
                getSystemInfos();
                frame.refreshComplete();

            }
        }, 1500);

    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void getUserMsgList(SystemInfoBean systemInfoBean) {

        switch (systemInfoBean.getStatus()) {
            case "1":
                List<SystemInfoBean.DataBean> data = systemInfoBean.getData();
                if (systemData != null && data != null) {
                    systemData.addAll(data);
                    systemInfoAdapter.setList(systemData);
                    systemInfoAdapter.notifyDataSetChanged();
                }


                break;
        }
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

}
