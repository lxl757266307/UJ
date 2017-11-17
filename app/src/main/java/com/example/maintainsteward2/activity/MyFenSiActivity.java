package com.example.maintainsteward2.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.FensSiAdapter2;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.FenSiBean2;
import com.example.maintainsteward2.mvp_presonter.FenSiPresonter;
import com.example.maintainsteward2.mvp_view.OnFenSiListener;
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

public class MyFenSiActivity extends BaseActivity implements PtrHandler2, OnFenSiListener,
        ExpandableListView.OnGroupClickListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_count)
    TextView txtCount;

    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;
    @BindView(R.id.txt_sanji_numner)
    TextView txtSanjiNumner;
    @BindView(R.id.img_wufensi)
    ImageView imgWufensi;
    @BindView(R.id.txt_wufensi)
    TextView txtWufensi;
    @BindView(R.id.txt_tixian)
    TextView txtTixian;
    @BindView(R.id.txt_numnber_erji)
    TextView txtNumnberErji;
    @BindView(R.id.txt_jiangli_money)
    TextView txtJiangliMoney;
    @BindView(R.id.elv_list)
    ExpandableListView elvList;
    String jiangLiJin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        jiangLiJin = this.getIntent().getStringExtra("jiangLiJin");
        setContentView(R.layout.activity_fensi);
        ButterKnife.bind(this);
        initUserInfo();
        initRecycle();
        initInfo();
    }

    boolean canLoad;

    private void initRecycle() {


        elvList.setOnGroupClickListener(this);

        elvList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView = elvList.getChildAt(elvList.getChildCount() - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == elvList.getHeight()) {
                        canLoad = true;

                    } else {
                        canLoad = false;

                    }
                }
            }
        });
        ptrFrame.setPtrHandler(this);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }


    int page = 1;

    private void initInfo() {
        String type = "1";
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

    String id;
    FenSiPresonter fenSiPresonter;
    List<FenSiBean2.DataBeanX.DataBean> dataBeanList;
    FensSiAdapter2 fenSiAdapter;

    private void initUserInfo() {
        txtJiangliMoney.setText("奖励金额：￥" + jiangLiJin + "元");
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
        fenSiPresonter = new FenSiPresonter();
        fenSiPresonter.setOnFenSiListener(this);
        dataBeanList = new ArrayList<>();
        fenSiAdapter = new FensSiAdapter2(this);
        elvList.setAdapter(fenSiAdapter);
    }


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
    public void getFenSiBean(FenSiBean2 bean) {
        switch (bean.getStatus()) {
            case "1":
                List<FenSiBean2.DataBeanX.DataBean> data = bean.getData().getData();
                dataBeanList.addAll(data);

                if (dataBeanList.size() == 0) {
                    txtWufensi.setVisibility(View.VISIBLE);
                    imgWufensi.setVisibility(View.VISIBLE);

                } else {
                    fenSiAdapter.setDataBeanList(dataBeanList);
                    fenSiAdapter.notifyDataSetChanged();
                }

                int count = 0;

                for (int i = 0; i < data.size(); i++) {
                    count += Integer.parseInt(data.get(i).getChild_count());
                }

                txtCount.setText(bean.getData().getCount());
                txtNumnberErji.setText("(二级粉丝:" + count + "位)");


                break;
            default:
                txtWufensi.setVisibility(View.VISIBLE);
                imgWufensi.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

        ImageView imageView = (ImageView) v.findViewById(R.id.img_open);

        if (dataBeanList.get(groupPosition).getChild() != null && dataBeanList.get(groupPosition).getChild().size() > 0) {
            if (elvList.isGroupExpanded(groupPosition)) {
                RotateAnimation rotateAnimation = new RotateAnimation(90f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                imageView.startAnimation(rotateAnimation);

            } else {
                RotateAnimation rotateAnimation = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                imageView.startAnimation(rotateAnimation);
            }
        } else {
            return true;
        }
        return false;
    }

    @OnClick(R.id.txt_tixian)
    public void onTiXianClicked() {

        startActivity(new Intent(this, TiXianActivity.class));

    }




}
