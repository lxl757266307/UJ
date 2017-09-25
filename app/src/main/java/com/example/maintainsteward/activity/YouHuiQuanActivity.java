package com.example.maintainsteward.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.CanUseJuanListAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.CanUseYouHuiQuanBean;
import com.example.maintainsteward.bean.KaJuanBean;
import com.example.maintainsteward.bean.KaJuanCountBean;
import com.example.maintainsteward.mvp_presonter.KaJuanPresonter;
import com.example.maintainsteward.mvp_view.KaJuanListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.io.Serializable;
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
 * Created by Administrator on 2017/9/23.
 */

public class YouHuiQuanActivity extends BaseActivity implements PtrHandler2, KaJuanListener, CanUseJuanListAdapter.OnKaJuanItemClickListener {

    SharedPreferences sharedPreferences;
    String id;
    int page = 1;
    String type = "1";
    KaJuanPresonter kaJuanPresonter;
    List<CanUseYouHuiQuanBean.DataBean> resultData;
    CanUseJuanListAdapter kaJuanListAdapter;

    @BindView(R.id.recycle)
    RecyclerView recycle;

    @BindView(R.id.prt_frame)
    PtrClassicFrameLayout prtFrame;
    @BindView(R.id.img_youhuiquan)
    ImageView imgYouhuiquan;
    @BindView(R.id.txt_youhuijuan)
    TextView txt_youhuijuan;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();

        }

        return super.onKeyDown(keyCode, event);
    }

    String order_no;
    String count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        order_no = getIntent().getStringExtra("order_no");
        count = getIntent().getStringExtra("count");
        setContentView(R.layout.activity_youhuiquan);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        prtFrame.setPtrHandler(this);
        id = sharedPreferences.getString("id", null);
        recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        kaJuanPresonter = new KaJuanPresonter();
        kaJuanPresonter.setKaJuanListener(this);
        resultData = new ArrayList<>();
        kaJuanListAdapter = new CanUseJuanListAdapter(this, 0);
        kaJuanListAdapter.setOnKaJuanItemClickListener(this);
        getKaJuan();


    }

    public void getKaJuan() {


        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("user_id", id);
        map.put("order_no", order_no);
        map.put("count", count);
        map.put("timestamp", timeStamp);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ServiceOrderUseCoupons?"
//                + "user_id=" + id + "&order_no=" + order_no + "&count=" + 100 + "&timestamp="
//                + timeStamp + "&sign=" + sign + "&key=" + Contacts.KEY);


        kaJuanPresonter.getCanUseKaJuan(id, order_no, count, timeStamp, sign, Contacts.KEY);
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
                getKaJuan();
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

    ProgressDialog dialog;

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(this, "", "正在加载...");
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

    public static final String TAG = "YouHuiQuanActivity";

    @Override
    public void onGetKaJuanSucess(KaJuanBean bean) {
//        switch (bean.getStatus()) {
//            case "1":
//                ToolUitls.print(TAG, "KaJuanBean==" + bean);
//                KaJuanBean.DataBean data = bean.getData();
//                resultData.addAll(data.getResultData());
//                kaJuanListAdapter.setList(resultData);
//                recycle.setAdapter(kaJuanListAdapter);
//                kaJuanListAdapter.notifyDataSetChanged();
//
//
//                break;
//        }
    }

    @Override
    public void onGetKaJuanCountSucess(KaJuanCountBean bean) {

    }

    @Override
    public void onGetCanUseKaJuan(CanUseYouHuiQuanBean bean) {

        switch (bean.getStatus()) {
            case "1":
                List<CanUseYouHuiQuanBean.DataBean> data = bean.getData();
                resultData.addAll(data);
                kaJuanListAdapter.setList(resultData);
                recycle.setAdapter(kaJuanListAdapter);
                kaJuanListAdapter.notifyDataSetChanged();


                break;
            default:
                imgYouhuiquan.setVisibility(View.VISIBLE);
                txt_youhuijuan.setVisibility(View.VISIBLE);

                break;
        }
    }

    @Override
    public void showBlank() {
        imgYouhuiquan.setVisibility(View.VISIBLE);
        txt_youhuijuan.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemClickItem(int postion) {

        CanUseYouHuiQuanBean.DataBean resultDataBean = resultData.get(postion);

        Intent intent = new Intent();
        intent.putExtra("kajuan", (Serializable) resultDataBean);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
