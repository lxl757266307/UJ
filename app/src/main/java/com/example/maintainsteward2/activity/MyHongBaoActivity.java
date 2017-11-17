package com.example.maintainsteward2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.MyHongBaoListAdapter;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.MyHongBaoListBean;
import com.example.maintainsteward2.main.MainActivity;
import com.example.maintainsteward2.mvp_presonter.MyHongBaoPresonter;
import com.example.maintainsteward2.mvp_view.MyHongBaoListListener;
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
 * Created by Administrator on 2017/10/24.
 */

public class MyHongBaoActivity extends BaseActivity implements PtrHandler2, MyHongBaoListListener, MyHongBaoListAdapter.OnUseOnClickListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_number)
    TextView txtNumber;

    String id;
    String count;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_myhongbao);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
        count = sharedPreferences.getString("luck_money_count", null);
        txtNumber.setText(count);
        initPresonter();
        myHongBaoList();

    }

    MyHongBaoPresonter myHongBaoPresonter;
    List<MyHongBaoListBean.DataBean> data;
    MyHongBaoListAdapter myHongBaoListAdapter;

    private void initPresonter() {
        data = new ArrayList<>();
        myHongBaoPresonter = new MyHongBaoPresonter();
        myHongBaoPresonter.setMyHongBaoListListener(this);
        ptrFrame.setPtrHandler(this);
        myHongBaoListAdapter = new MyHongBaoListAdapter(this);
        myHongBaoListAdapter.setOnUseOnClickListener(this);

        recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycle.setAdapter(myHongBaoListAdapter);


    }

    int page = 1;

    public void myHongBaoList() {
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("timestamp", timeStamp);
        map.put("page", page + "");
        map.put("user_id", id);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MyLuckMoney?" + "user_id=" + id + "&page=" + page + "&timestamp=" + timeStamp + "&sign=" + sign + "&key=" + Contacts.KEY);

        myHongBaoPresonter.getHongBaoList(id, page + "", timeStamp, sign, Contacts.KEY);

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
    public void onLoadMoreBegin(PtrFrameLayout frame) {

        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                myHongBaoList();
                ptrFrame.refreshComplete();
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
    public void getHongBaoListListtener(MyHongBaoListBean myHongBaoListBean) {

        switch (myHongBaoListBean.getStatus()) {
            case "1":
                List<MyHongBaoListBean.DataBean> dataBeanList = myHongBaoListBean.getData();
                data.addAll(dataBeanList);
                myHongBaoListAdapter.setList(data);
                myHongBaoListAdapter.notifyDataSetChanged();

                break;
        }
    }

    @Override
    public void onUseClick(int position) {

        MyHongBaoListBean.DataBean dataBean = data.get(position);
//        ToolUitls.print("--------", "databean=" + dataBean);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("page", 1);
        startActivity(intent);



    }
}
