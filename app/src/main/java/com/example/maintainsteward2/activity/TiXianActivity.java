package com.example.maintainsteward2.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.YongJinJiLuAdapter;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.bean.YongJinJiLuBean;
import com.example.maintainsteward2.mvp_presonter.TiXianPresonter;
import com.example.maintainsteward2.mvp_view.OnGetYongJinStroryListener;
import com.example.maintainsteward2.utils.ToolUitls;
import com.example.maintainsteward2.view.MyListView;
import com.example.maintainsteward2.wxapi.WXEntryActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;

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
 * Created by Administrator on 2017/10/16.
 */

public class TiXianActivity extends BaseActivity implements PtrHandler2, OnGetYongJinStroryListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_ti_xian_money)
    EditText editOutMoney;
    @BindView(R.id.txt_yu_e)
    TextView txtYuE;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;
    @BindView(R.id.layout_list)
    LinearLayout layoutList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);
        initViews();
        initUserInfo();
        getMyCommission();
        register();
    }

    String id;
    TiXianPresonter tiXianPresonter;
    List<YongJinJiLuBean.DataBeanX.DataBean> dataBeanList;
    YongJinJiLuAdapter adapter;


    private void initUserInfo() {
        ptrFrame.setPtrHandler(this);
        adapter = new YongJinJiLuAdapter(this);
        dataBeanList = new ArrayList<>();
        tiXianPresonter = new TiXianPresonter();
        tiXianPresonter.setOnGetYongJinStroryListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
    }

    int page = 1;

    private void getMyCommission() {
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("timestamp", timeStamp);
        map.put("user_id", id);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MyCommission?" + "user_id=" + id + "&page=" + page + "&timestamp=" + timeStamp + "&key=" + Contacts.KEY + "&sign=" + sign);
        tiXianPresonter.getStrory(id, page + "", timeStamp, sign, Contacts.KEY);
    }

    private void initViews() {
        lvList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView = lvList.getChildAt(lvList.getChildCount() - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == lvList.getHeight()) {
                        canLoad = true;
                    } else {
                        canLoad = false;
                    }
                }
            }
        });
        editOutMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOutMoney.setFocusable(true);
                editOutMoney.setFocusableInTouchMode(true);
            }
        });

    }

    @OnClick(R.id.layout_back)
    public void onLayoutBackClicked() {
        finish();
    }

    String money;

    @OnClick(R.id.btn_sure)
    public void onBtnSureClicked() {


        if (!(Integer.parseInt(commission) > 0)) {
            ToolUitls.toast(this, "您当前余额为0，无法为您提现！");
            return;
        }

        money = editOutMoney.getText().toString();
        if (money.matches("\\d*") && Integer.parseInt(money) > 0 && Integer.parseInt(money) < Integer.parseInt(commission)) {
            WXEntryActivity.loginWeixin(this, MyApplication.api);
        } else {
            ToolUitls.toast(this, "输入有误，请重新输入！");
            return;
        }

    }

    boolean canLoad;

    @Override
    public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {

        return canLoad;
    }

    @Override
    public void onLoadMoreBegin(final PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {

                page++;
                getMyCommission();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    String commission = "0";

    @Override
    public void onGetTiXianStory(YongJinJiLuBean yongJinJiLuBean) {

        switch (yongJinJiLuBean.getStatus()) {
            case "1":
                YongJinJiLuBean.DataBeanX data = yongJinJiLuBean.getData();
                commission = data.getCommission();
                txtYuE.setText(commission);
                List<YongJinJiLuBean.DataBeanX.DataBean> dataBean = data.getData();
                dataBeanList.addAll(dataBean);
                adapter.setDataBeanList(dataBeanList);
                lvList.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120 * dataBeanList.size());
                layoutList.setLayoutParams(layoutParams);
                lvList.setLayoutParams(layoutParams);
                break;
        }
    }

    @Override
    public void onUpdateUnionidScucess(PublicBean publicBean) {
        switch (publicBean.getStatus()) {
            case "1":
                TreeMap<String, String> map = new TreeMap<>();
                String timeStamp = System.currentTimeMillis() + "";
                map.put("timestamp", timeStamp);
                map.put("user_id", id);
                map.put("out_money", money);
                String sign = ToolUitls.getSign(map);
                tiXianPresonter.tiXian(id, money, timeStamp, sign, Contacts.KEY);

                break;
        }
    }

    @Override
    public void onTiXianScucess(PublicBean bean) {

        switch (bean.getStatus()) {

            case "1":
                ToolUitls.toast(this, "申请成功，请等待客服处理");
                getMyCommission();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(tiXianReciver);
    }

    TiXianReciver tiXianReciver;

    public void register() {
        tiXianReciver = new TiXianReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contacts.TI_XIAN);
        registerReceiver(tiXianReciver, intentFilter);
    }

    class TiXianReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Contacts.TI_XIAN)) {

                String unionid = intent.getStringExtra("unionid");
                TreeMap<String, String> map = new TreeMap<>();
                String timeStamp = System.currentTimeMillis() + "";
                map.put("timestamp", timeStamp);
                map.put("user_id", id);
                map.put("open_id", unionid);
                String sign = ToolUitls.getSign(map);
                tiXianPresonter.updateUnionid(id, unionid, timeStamp, sign, Contacts.KEY);

            }

        }
    }
}
