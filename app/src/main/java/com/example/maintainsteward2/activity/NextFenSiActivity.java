package com.example.maintainsteward2.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.NextFenSiAdapter;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.NextFenSiBean;
import com.example.maintainsteward2.mvp_presonter.NextFenSiPresonter;
import com.example.maintainsteward2.mvp_view.OnNextFenSiListener;
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
 * Created by Administrator on 2017/10/7.
 */

public class NextFenSiActivity extends BaseActivity implements PtrHandler2, OnNextFenSiListener {

    String id;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;
    @BindView(R.id.img_wufensi)
    ImageView imgWufensi;
    @BindView(R.id.txt_wufensi)
    TextView txtWufensi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        id = this.getIntent().getStringExtra("id");
        setContentView(R.layout.activity_next_fensi);
        ButterKnife.bind(this);
        initRecycle();
        initInfo();

    }

    NextFenSiPresonter fenSiPresonter;
    NextFenSiAdapter nextFenSiAdapter;
    List<NextFenSiBean.DataBeanX.DataBean> dataBeanList;

    private void initRecycle() {
        dataBeanList = new ArrayList<>();
        fenSiPresonter = new NextFenSiPresonter();
        fenSiPresonter.setOnNextFenSiListener(this);
        nextFenSiAdapter = new NextFenSiAdapter(this);
        recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycle.setAdapter(nextFenSiAdapter);
        ptrFrame.setPtrHandler(this);
    }

    int page = 1;

    private void initInfo() {
        String type = "2";
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("timestamp", timeStamp);
        map.put("m_id", id);
        map.put("type", type);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ExtendOrderDetails?" + "m_id=" + id + "&type=" + type + "&page=" + page + "&timestamp=" + timeStamp + "&key=" + Contacts.KEY + "&sign=" + sign);
        fenSiPresonter.getFenSiBean(id, type, page + "", timeStamp, sign, Contacts.KEY);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
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
                initInfo();
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
    public void getNextFenSiBean(NextFenSiBean bean) {
        switch (bean.getStatus()) {
            case "1":
                NextFenSiBean.DataBeanX data = bean.getData();
                List<NextFenSiBean.DataBeanX.DataBean> list = data.getData();
                dataBeanList.addAll(list);
                if (dataBeanList.size() > 0) {

                    nextFenSiAdapter.setList(dataBeanList);
                    nextFenSiAdapter.notifyDataSetChanged();
                } else {
                    txtWufensi.setVisibility(View.VISIBLE);
                    imgWufensi.setVisibility(View.VISIBLE);
                }


                break;
            default:
                txtWufensi.setVisibility(View.VISIBLE);
                imgWufensi.setVisibility(View.VISIBLE);
                break;
        }
    }
}
